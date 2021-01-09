package com.naztech.lms.service;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ibm.icu.text.DateFormat;
import com.ibm.icu.text.SimpleDateFormat;
import com.nazdaqTechnologies.core.message.Message;
import com.nazdaqTechnologies.core.message.MessageBuilder;
import com.nazdaqTechnologies.core.message.MessageHeader;
import com.nazdaqTechnologies.core.service.AbstractService;
import com.nazdaqTechnologies.jdbc.JdbcResult;
import com.nazdaqTechnologies.jdbc.JdbcService;
import com.nazdaqTechnologies.jdbc.StoredProcedure.JdbcStoredProcedure;
import com.nazdaqTechnologies.jdbc.util.JdbcUtils;
import com.naztech.lms.constants.ActionType;
import com.naztech.lms.constants.RSType;
import com.naztech.lms.constants.SPName;
import com.naztech.lms.constants.Str;
import com.naztech.lms.model.Customer;
import com.naztech.lms.model.Loan;
import com.naztech.lms.model.Report;

@PropertySource("classpath:app.properties")
public class ReportService extends AbstractService<ReportService> {

	private static Logger log = LogManager.getLogger(LoanService.class);
	private static final DateFormat df = new SimpleDateFormat("yyMM");
	private final static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	@Autowired
	JdbcService jdbcService;

	@Autowired
	private LoanService loanService;

	@Value("${loan.column.name}")
	String loanColumns;

	@Value("${loan.ppc.excel.report.columns}")
	String loanPpcExcelReportColumns;

	@Value("${loan.column.organization}")
	String organization;

	static Gson gson;
	static {
		gson = new Gson();
	}

	@Override
	@SuppressWarnings("rawtypes")
	public Message<?> serviceSingle(Message msg) throws Exception {

		MessageHeader msgHeader = null;
		Message<?> msgResponse = null;

		try {

			msgHeader = msg.getHeader();

			String action = msgHeader.getActionType();
			log.debug("Processing ACTION [{}]", action);

			if (action.equals(ActionType.PPC_EXCEL_REPORT.toString())) {
				boolean ppcReport = loanGridPPCExcelReport(msg);
				msgResponse = MessageBuilder.withPayload(ppcReport).copyHeadersIfAbsent(msgHeader).build();
			}
			else {
				throw new Exception("Unknown action " + action);
			}
		}
		catch (Exception ex) {
			log.error("Error {}", ex);
			throw ex;
		}

		return msgResponse;
	}

	public JdbcResult selectSingleLoanForReport(Report report, String action, String sp) throws Exception {
		try {
			JdbcResult jdbcResult = new JdbcResult();
			Map<String, Object> spArgsMap = JdbcService.createSqlMap(report, Report.getSql2BeanMap());

			JdbcStoredProcedure jdbcStoredProcedure = jdbcService.getJdbcStoredProcedure(sp);
			jdbcResult.setFilteredOutputParamMap(jdbcStoredProcedure.getSpOutputParamMap());
			jdbcResult.setProcessWarnings(true);
			return jdbcService.executeSP(action, sp, spArgsMap, jdbcResult);
		}
		catch (Exception e) {
			log.error("Exception getting loan [{}]", e);
			throw e;
		}
	}

	public String findHourAndMinute(String minutes) {
		String result = minutes;
		if (minutes != null && !minutes.isEmpty()) {
			Integer min = Integer.parseInt(minutes);
			int minute = min % 60;
			int hour = (min / 60) % 24;
			int day = ((min / 60) / 24) % 30;
			int month = (((min / 60) / 24) / 30) % 12;
			int year = (((min / 60) / 24) / 30) / 12;
			if (year == 0 && month == 0 && day == 0 && hour == 0) {
				result = minute + " Minute(s)";
			}
			else if (year == 0 && month == 0 && day == 0 && hour != 0) {
				result = hour + " Hour(s) and " + minute + " Minute(s)";
			}
			else if (year == 0 && month == 0 && day != 0 && hour != 0) {
				result = day + " Day(s), " + hour + " Hour(s) and " + minute + " Minute(s)";
			}
			else if (year == 0 && month != 0 && day != 0 && hour != 0) {
				result = month + " Month(s), " + day + " Day(s), " + hour + " Hour(s) and " + minute + " Minute(s)";
			}
			else {
				result = year + " Year(s), " + month + " Month(s), " + day + " Day(s), " + hour + " Hour(s) and " + minute + " Minute(s)";
			}
		}
		return result;
	}

	public File loanGridPPCExcelReportV2(HttpServletRequest request) throws Exception {

		String reportName = request.getParameter("reportName");
		String reportReqTime = request.getParameter("reportReqTime");
		Integer userId = Integer.parseInt(request.getParameter("userId"));
		String userName = request.getParameter("userName");
		String loanId = request.getParameter("loanId");

		if (loanId == null || loanId.length() == 0) {
			throw new Exception("Empty Request.");
		}
		String[] loanIds = loanId.split(",");
		JdbcResult jdbcResult = new JdbcResult();
		Report report = new Report();
		Customer customer = new Customer();
		List<Report> loanList = new ArrayList<Report>();
		String actionType = ActionType.PPC_EXCEL_REPORT.toString();
		List<Customer> customerList = new ArrayList<Customer>();

		for (int i = 0; i < loanIds.length; i++) {

			report.setLoanId(Integer.parseInt(loanIds[i]));
			report.setUserModKey(userId);

			jdbcResult = selectSingleLoanForReport(report, actionType, SPName.ACT_REPORT.toString());
			loanList.addAll(JdbcUtils.mapRows(Report.class, Report.getRs2BeanMap(),
			        jdbcResult.getRsTypeMap(RSType.RS_TYPE_LMS_MIS_PPC_EXCEL_REPORT.toString())));
			customerList
			        .addAll(JdbcUtils.mapRows(Customer.class, Customer.getRs2BeanMap(), jdbcResult.getRsTypeMap(RSType.RS_TYPE_CUSTOMER.toString())));

		}

		// Create a Workbook
		Workbook workbook = new XSSFWorkbook(); // new HSSFWorkbook() for generating `.xls` file

		// CreationHelper helps us create instances of various things like DataFormat,
		// Hyperlink, RichTextString etc, in a format (HSSF, XSSF) independent way
		CreationHelper createHelper = workbook.getCreationHelper();

		// Create a Sheet
		Sheet sheet = workbook.createSheet("Loan");

		// Create a Font for styling header cells
		Font headerFont = workbook.createFont();
		headerFont.setBold(true);
		headerFont.setFontHeightInPoints((short) 14);
		headerFont.setColor(IndexedColors.BLUE.getIndex());

		// Create a CellStyle with the font
		CellStyle headerCellStyle = workbook.createCellStyle();
		headerCellStyle.setFont(headerFont);

		// Create a Row
		Row headerRow = sheet.createRow(0);

		String[] loanColumn = loanPpcExcelReportColumns.split(",");

		boolean isGpfLoanIncluded = false;

		for (int count = 0; count < loanList.size(); count++) {
			Report reportCls = loanList.get(count);
			if (reportCls.getLoanType().equals(Str.GPF_LOAN)) {
				isGpfLoanIncluded = true;
			}
		}
			for (int i = 0; i < loanColumn.length; i++) {
				Cell cell = headerRow.createCell(i);
				cell.setCellValue(loanColumn[i]);
				cell.setCellStyle(headerCellStyle);
			}

		Map<String, Object[]> data = new HashMap<String, Object[]>();
		for (int count = 0; count < loanList.size(); count++) {
			Report reportCls = loanList.get(count);

			if (customerList != null && customerList.size() > 0) {
				reportCls.setCustomer(customerList.get(count));
			}

			String numberColumn = null;
			boolean b = false;
			if (reportCls.getCustomer().getBpNo() != null && !reportCls.getCustomer().getBpNo().isEmpty()) {
				for (char c : reportCls.getCustomer().getBpNo().toCharArray()) {
					if (b == Character.isDigit(c)) {
						for (char cc : reportCls.getCustomer().getBpNo().substring(2).toCharArray()) {
							if (b == Character.isDigit(cc)) {
								break;
							}
							numberColumn = reportCls.getCustomer().getBpNo().substring(2);
						}
						break;
					}
					else {
						numberColumn = reportCls.getCustomer().getBpNo();
					}
				}
			}
			String currentStatus = reportCls.getStatus();
			if (currentStatus == "Declined") {
				currentStatus = "Cancel";
			}

			String processStatus = reportCls.getStatus();
			if (reportCls.getStatus() == "Approved") {
				processStatus = "Done";
			}
			else if (reportCls.getStatus() == "Deferred") {
				processStatus = "Pending";
			}
			String submitUnit = reportCls.getSubmitUnit() + "-" + reportCls.getBranchName();
			if (reportCls.getSubmitUnit() == null) {
				submitUnit = "";
			}
			Double gPFAmount = reportCls.getgPFAmount();
			if (gPFAmount != null && gPFAmount == -2147483648) {
				gPFAmount = null;
			}

			String sourceTat = findHourAndMinute(reportCls.getSourceTat());
			String crmTat = findHourAndMinute(reportCls.getCrmTat());
			String cadTat = findHourAndMinute(reportCls.getCadTat());

				/*fetch value from db row by model class*/
				data.put(String.valueOf(count),
				        new Object[] { (count + 1), reportCls.getLoanTrackingId(), reportCls.getCustomer().getBpNo(), numberColumn,
				                reportCls.getCustomer().getCustomerName(), reportCls.getBranchName(), reportCls.getFileRefNo(), currentStatus,
				                reportCls.getSendToCadDate(), reportCls.getApprovedAmount(), gPFAmount, reportCls.getApprovedDate(),
				                reportCls.getReceivedByCrm(), reportCls.getCibGenerationDate(), processStatus, reportCls.getMail(),
				                reportCls.getDistricName(), submitUnit, reportCls.getBranchName(), reportCls.getUnitName2(), reportCls.getLoanType(),
				                reportCls.getPpcReceivedDate(), reportCls.getMailSendDate(), reportCls.getPpcReceivedDate(),
				                reportCls.getReceivedCibFromCadDate(), reportCls.getSubmitToCrm(), sourceTat, reportCls.getSendToCadDate(),
				                reportCls.getSubmitToCrm(), reportCls.getFileRefNo(), reportCls.getCrmStatus(),// reportCls.getApprovedAmtDate(),
				                reportCls.getSendToCadDate(), crmTat, reportCls.getSendToCadDate(), reportCls.getCadQuriesDate(),
				                reportCls.getCadStatus(), reportCls.getDisbursedDate(), cadTat,reportCls.getCustomer().getMobile(),reportCls.getUserName(),reportCls.getCustomer().getDesignation(),reportCls.getTenorYear(), reportCls.getGuarantorNid()
				                ,reportCls.getMobileOfGuarantor(), reportCls.getDistrictDivision()				              
						});
		}

		// Create Cell Style for formatting Date
		CellStyle dateCellStyle = workbook.createCellStyle();
		dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));

		// Create Other rows and cells with employees data

		int rownum = 1;
		for (int key = 0; key < loanList.size(); key++) {

			headerRow = sheet.createRow(rownum++);

			Object[] objArr = data.get(String.valueOf(key));
			int cellnum = 0;
			for (Object obj : objArr) {
				Cell cell = headerRow.createCell(cellnum++);
				if (obj instanceof Date) {
					cell.setCellValue(sdf.format((Date) obj));
					cell.setCellStyle(dateCellStyle);
				}
				else if (obj instanceof Boolean)
					cell.setCellValue((Boolean) obj);
				else if (obj instanceof String)
					cell.setCellValue((String) obj);
				else if (obj instanceof Double)
					cell.setCellValue((Double) obj);
				else if (obj instanceof Number)
					cell.setCellValue(((Number) obj).doubleValue());
				else if (obj == null)
					cell.setCellValue("");
				else if (obj instanceof Integer) cell.setCellValue((Integer) obj);

			}
		}
		// Resize all columns to fit the content size
		for (int j = 0; j < loanColumn.length; j++) {
			sheet.autoSizeColumn(j);
		}

		File tempFile = File.createTempFile("MIS-PPC-EXCEL", ".xlsx");
		FileOutputStream fileOut = new FileOutputStream(tempFile);
		workbook.write(fileOut);
		fileOut.close();

		workbook.close();
		return tempFile;
	}

	public boolean loanGridPPCExcelReport(Message<List<Report>> msg) throws Exception {

		List<Report> payloadList = msg.getPayload();
		Report reportClass = payloadList.get(0);
		if (payloadList == null || payloadList.size() == 0) {
			throw new Exception("Empty Request.");
		}
		JdbcResult jdbcResult = new JdbcResult();
		Report report = new Report();
		Customer customer = new Customer();
		List<Report> loanList = new ArrayList<Report>();
		String actionType = ActionType.PPC_EXCEL_REPORT.toString();
		List<Customer> customerList = new ArrayList<Customer>();

		for (int i = 0; i < reportClass.getLoandIdList().size(); i++) {

			report.setLoanId(reportClass.getLoandIdList().get(i));
			report.setUserModKey(reportClass.getUserId());

			jdbcResult = selectSingleLoanForReport(report, actionType, SPName.ACT_REPORT.toString());
			loanList.addAll(JdbcUtils.mapRows(Report.class, Report.getRs2BeanMap(),
			        jdbcResult.getRsTypeMap(RSType.RS_TYPE_LMS_MIS_PPC_EXCEL_REPORT.toString())));
			customerList
			        .addAll(JdbcUtils.mapRows(Customer.class, Customer.getRs2BeanMap(), jdbcResult.getRsTypeMap(RSType.RS_TYPE_CUSTOMER.toString())));

		}

		// Create a Workbook
		Workbook workbook = new XSSFWorkbook(); // new HSSFWorkbook() for generating `.xls` file

		// CreationHelper helps us create instances of various things like DataFormat,
		// Hyperlink, RichTextString etc, in a format (HSSF, XSSF) independent way
		CreationHelper createHelper = workbook.getCreationHelper();

		// Create a Sheet
		Sheet sheet = workbook.createSheet("Loan");

		// Create a Font for styling header cells
		Font headerFont = workbook.createFont();
		headerFont.setBold(true);
		headerFont.setFontHeightInPoints((short) 14);
		headerFont.setColor(IndexedColors.BLUE.getIndex());

		// Create a CellStyle with the font
		CellStyle headerCellStyle = workbook.createCellStyle();
		headerCellStyle.setFont(headerFont);

		// Create a Row
		Row headerRow = sheet.createRow(0);

		String[] loanColumn = loanPpcExcelReportColumns.split(",");

		// Create cells
		for (int i = 0; i < loanColumn.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(loanColumn[i]);
			cell.setCellStyle(headerCellStyle);
		}

		Map<String, Object[]> data = new HashMap<String, Object[]>();
		for (int count = 0; count < loanList.size(); count++) {
			Report reportCls = loanList.get(count);

			if (customerList != null && customerList.size() > 0) {
				reportCls.setCustomer(customerList.get(count));
			}

			String numberColumn = null;
			boolean b = false;
			if (reportCls.getCustomer().getBpNo() != null && !reportCls.getCustomer().getBpNo().isEmpty()) {
				for (char c : reportCls.getCustomer().getBpNo().toCharArray()) {
					if (b == Character.isDigit(c)) {
						for (char cc : reportCls.getCustomer().getBpNo().substring(2).toCharArray()) {
							if (b == Character.isDigit(cc)) {
								break;
							}
							numberColumn = reportCls.getCustomer().getBpNo().substring(2);
						}
						break;
					}
					else {
						numberColumn = reportCls.getCustomer().getBpNo();
					}
				}
			}
			String currentStatus = reportCls.getStatus();
			if (currentStatus == "Declined") {
				currentStatus = "Cancel";
			}

			String processStatus = reportCls.getStatus();;
			if (reportCls.getStatus() == "Approved") {
				processStatus = "Done";
			}
			else if (reportCls.getStatus() == "Deferred") {
				processStatus = "Pending";
			}
			String submitUnit = reportCls.getSubmitUnit() + "-" + reportCls.getBranchName();
			String sourceTat = findHourAndMinute(reportCls.getSourceTat());
			String crmTat = findHourAndMinute(reportCls.getCrmTat());
			String cadTat = findHourAndMinute(reportCls.getCadTat());

			/*fetch value from db row by model class*/
			data.put(String.valueOf(count),
			        new Object[] { (count + 1), reportCls.getLoanTrackingId(), reportCls.getCustomer().getBpNo(), numberColumn,
			                reportCls.getCustomer().getCustomerName(), reportCls.getBranchName(), reportCls.getFileRefNo(), currentStatus,
			                reportCls.getSendToCadDate(), reportCls.getApprovedAmount(), reportCls.getReceivedByCrm(),
			                reportCls.getCibGenerationDate(), processStatus, reportCls.getMail(), reportCls.getDistricName(), submitUnit,
			                reportCls.getBranchName(), reportCls.getUnitName2(), reportCls.getLoanType(), reportCls.getPpcReceivedDate(),
			                reportCls.getMailSendDate(), reportCls.getPpcReceivedDate(), reportCls.getReceivedCibFromCadDate(),
			                reportCls.getSubmitToCrm(), sourceTat, reportCls.getSendToCadDate(), reportCls.getSubmitToCrm(), reportCls.getFileRefNo(),
			                reportCls.getCrmStatus(), reportCls.getApprovedAmtDate(), reportCls.getSendToCadDate(), crmTat,
			                reportCls.getSendToCadDate(), reportCls.getCadQuriesDate(), reportCls.getCadStatus(), reportCls.getDisbursedDate(), cadTat	,reportCls.getDistrictDivision()		               

					});

		}

		// Create Cell Style for formatting Date
		CellStyle dateCellStyle = workbook.createCellStyle();
		dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));

		// Create Other rows and cells with employees data

		int rownum = 1;
		for (int key = 0; key < loanList.size(); key++) {

			headerRow = sheet.createRow(rownum++);

			Object[] objArr = data.get(String.valueOf(key));
			int cellnum = 0;
			for (Object obj : objArr) {
				Cell cell = headerRow.createCell(cellnum++);
				if (obj instanceof Date) {
					cell.setCellValue(sdf.format((Date) obj));
					cell.setCellStyle(dateCellStyle);
				}
				else if (obj instanceof Boolean)
					cell.setCellValue((Boolean) obj);
				else if (obj instanceof String)
					cell.setCellValue((String) obj);
				else if (obj instanceof Double)
					cell.setCellValue((Double) obj);
				else if (obj instanceof Number)
					cell.setCellValue(((Number) obj).doubleValue());
				else if (obj == null)
					cell.setCellValue("");
				else if (obj instanceof Integer) cell.setCellValue((Integer) obj);

			}
		}
		// Resize all columns to fit the content size
		for (int j = 0; j < loanColumn.length; j++) {
			sheet.autoSizeColumn(j);
		}

		// Write the output to a file
		String home = System.getProperty("user.home");
		File file = new File(home + "/Downloads/" + "MIS-PPC-EXCEL" + ".xlsx");
		FileOutputStream fileOut = new FileOutputStream(file);
		workbook.write(fileOut);
		fileOut.close();

		// open file
		//		try {
		//			Desktop.getDesktop().open(file.getAbsoluteFile());
		//		} catch (IOException e) {
		//			// TODO Auto-generated catch block
		//			e.printStackTrace();
		//		}
		//		// Closing the workbook
		workbook.close();
		return true;
	}

	public File loanGridMISExcelReport(HttpServletRequest request) throws Exception {

		String reportName = request.getParameter("reportName");
		String reportReqTime = request.getParameter("reportReqTime");
		Integer userId = Integer.parseInt(request.getParameter("userId"));
		String userName = request.getParameter("userName");
		String loanId = request.getParameter("loanId");
		JdbcResult jdbcResult = new JdbcResult();
		Loan loan = new Loan();
		Customer customer = new Customer();
		List<Loan> loanList = new ArrayList<Loan>();
		String actionType = ActionType.LMS_LOAN_EXCEL_REPORT.toString();
		String[] idList = request.getParameterValues("loanId");
		String[] loanIdList = idList[0].split(",");
		List<Customer> customerList = new ArrayList<Customer>();

		for (int i = 0; i < loanIdList.length; i++) {

			loan.setLoanId(Integer.parseInt(loanIdList[i]));
			loan.setUserModKey(userId);

			jdbcResult = loanService.selectSingle(loan, actionType, SPName.ACT_REPORT.toString());
			loanList.addAll(
			        JdbcUtils.mapRows(Loan.class, Loan.getRs2BeanMap(), jdbcResult.getRsTypeMap(RSType.RS_TYPE_LMS_LOAN_EXCEL_REPORT.toString())));
			customerList
			        .addAll(JdbcUtils.mapRows(Customer.class, Customer.getRs2BeanMap(), jdbcResult.getRsTypeMap(RSType.RS_TYPE_CUSTOMER.toString())));

		}

		// Create a Workbook
		Workbook workbook = new XSSFWorkbook(); // new HSSFWorkbook() for generating `.xls` file

		// CreationHelper helps us create instances of various things like DataFormat,
		// Hyperlink, RichTextString etc, in a format (HSSF, XSSF) independent way
		CreationHelper createHelper = workbook.getCreationHelper();

		// Create a Sheet
		Sheet sheet = workbook.createSheet("Loan");

		// Create a Font for styling header cells
		Font headerFont = workbook.createFont();
		headerFont.setBold(true);
		headerFont.setFontHeightInPoints((short) 14);
		headerFont.setColor(IndexedColors.BLUE.getIndex());

		// Create a CellStyle with the font
		CellStyle headerCellStyle = workbook.createCellStyle();
		headerCellStyle.setFont(headerFont);

		// Create a Row
		Row headerRow = sheet.createRow(0);

		String[] loanColumn = loanColumns.split(",");
		
		for (int count = 0; count < loanList.size(); count++) {
			Loan loanClass = loanList.get(count);
				// Create cells
				for (int i = 0; i < loanColumn.length; i++) {
					Cell cell = headerRow.createCell(i);
					cell.setCellValue(loanColumn[i]);
					cell.setCellStyle(headerCellStyle);
				}
		}

		Map<String, Object[]> data = new HashMap<String, Object[]>();
		for (int count = 0; count < loanList.size(); count++) {
			Loan loanClass = loanList.get(count);

			if (customerList != null && customerList.size() > 0) {
				loanClass.setCustomer(customerList.get(count));
			}

			String organizationName = null;
			String districtDivision=null;
			String district = loanClass.getCustomer().getOfficeDistrict();
			String division = loanClass.getCustomer().getOfficeDivision();
			
		    if(!(district==null||district=="")&& !(division==null||division=="")) {
				districtDivision= district+"/" + division;
			}else if((district==null||district=="")&&(!(division==null||division==""))) {
				districtDivision = division;
			}else if((division==null||division=="")&&(!(district==null||district==""))) {
				districtDivision = district;
			}else {
				districtDivision="";
			}
			if (loanClass.getCustomer().getBpNo() != null) {
				organizationName = organization;
			}
			Double approveAmt = loanClass.getRecommendedForApproval();
			if (approveAmt != null && approveAmt == -2147483648) {
				approveAmt = null;
			}
			Double gPFAmount = loanClass.getgPFAmount();
			if (gPFAmount != null && gPFAmount == -2147483648) {
				gPFAmount = null;
			}
				/*fetch value from db row by model class*/
				data.put(String.valueOf(count), new Object[] { loanClass.getLoanTrackingId(), loanClass.getApplicationNo(),
				        loanClass.getCustomer().getBpNo(), loanClass.getLoanType(), loanClass.getCustomer().getCustomerName(), organizationName,
				        loanClass.getCustomer().getDesignation(), loanClass.getCustomerType(), loanClass.getCustomer().getCurrentPlaceofPosting(),
				        loanClass.getCustomer().getCif(), loanClass.getCustomer().getAccountNo(), loanClass.getCustomer().getNid(),
				        loanClass.getCustomer().getTin(), loanClass.getCustomer().getDateOfBirth(), loanClass.getCustomer().getFatherName(),
				        loanClass.getCustomer().getMotherName(), loanClass.getCustomer().getSpouse(), loanClass.getCustomer().getJoiningDate(),
				        loanClass.getCibGenerationDate(), loanClass.getCrmReceivedDate(), loanClass.getAnalyst(), loanClass.getLoanCurrentStatus(),
				        loanClass.getDateOfQuery(), loanClass.getReturnToSourceDate(), loanClass.getReSubmitDate(), loanClass.getAnalystComments(),
				        loanClass.getAppliedLoanAmount(), gPFAmount, approveAmt, loanClass.getApprovedDate(), loanClass.getSentToCad(),
				        loanClass.getTenorYear(), loanClass.getInterestRate(), loanClass.getTotalEMI(), loanClass.getCustomer().getMobile(),
				        loanClass.getCustomer().getAlternativeMobile(), loanClass.getCustomer().getEmerPhone(), loanClass.getAllowedDBR(),
				        loanClass.getCustomer().getMaritalStatus(), loanClass.getSourcingBrc(), loanClass.getCbsUserId(), loanClass.getInputBy(),
				        loanClass.getNameOfGuarantor(), loanClass.getGuarantorNid(), loanClass.getCustomer().getPermanentAddr(),
				        loanClass.getCustomer().getOfficeAddr(), "" /*In version-1 It will be ready for manual input */
				        , loanClass.getCustomer().getBanglaNameOfBorrower(), loanClass.getInputBy(),loanClass.getUserName(),
				        loanClass.getMobileOfGuarantor(),districtDivision});
		}

		// Create Cell Style for formatting Date
		CellStyle dateCellStyle = workbook.createCellStyle();
		dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));

		// Create Other rows and cells with employees data

		int rownum = 1;
		for (int key = 0; key < loanList.size(); key++) {

			headerRow = sheet.createRow(rownum++);

			Object[] objArr = data.get(String.valueOf(key));
			int cellnum = 0;
			for (Object obj : objArr) {
				Cell cell = headerRow.createCell(cellnum++);
				if (obj instanceof Date) {
					cell.setCellValue(sdf.format((Date) obj));
					cell.setCellStyle(dateCellStyle);
				}
				else if (obj instanceof Boolean)
					cell.setCellValue((Boolean) obj);
				else if (obj instanceof String)
					cell.setCellValue((String) obj);
				else if (obj instanceof Double)
					cell.setCellValue((Double) obj);
				else if (obj instanceof Number)
					cell.setCellValue(((Number) obj).doubleValue());
				else if (obj == null)
					cell.setCellValue("");
				else if (obj instanceof Integer) cell.setCellValue((Integer) obj);

			}
		}
		// Resize all columns to fit the content size
		for (int j = 0; j < loanColumn.length; j++) {
			sheet.autoSizeColumn(j);
		}

		//	// Write the output to a file
		/*String home = System.getProperty("user.home");
		File file = new File(home + "/Downloads/" + "MIS-CRM-excel" + ".xlsx");
		FileOutputStream fileOut = new FileOutputStream(file);
		workbook.write(fileOut);
		fileOut.close();*/

		File tempFile = File.createTempFile("MIS-CRM-excel", ".xlsx");
		FileOutputStream fileOut = new FileOutputStream(tempFile);
		workbook.write(fileOut);
		fileOut.close();

		workbook.close();
		return tempFile;
	}

	public File lmsGridExcelReport(HttpServletRequest request) throws Exception {

		String reportName = request.getParameter("reportName");
		String userName = request.getParameter("userName");
		String headerListStr = request.getParameter("headerList");
		String dataListStr = request.getParameter("dataList");
		String dataIndexMapStr = request.getParameter("dataIndexMap");

		log.debug("Received GUI request to generate Excel Report for [{}] with report name [{}] with Excel Header [{}] and Excel data [{}]", userName,
		        reportName, headerListStr, dataListStr);

		log.debug("Taking new workbook to prepare Excel");
		Workbook workbook = new XSSFWorkbook();

		File tempFile = File.createTempFile(reportName + "-" + userName, ".xlsx");

		try {
			Type listType = new TypeToken<List<String>>() {
			}.getType();
			Type listOfMapType = new TypeToken<List<Map<String, Object>>>() {
			}.getType();
			Type mapType = new TypeToken<Map<String, Integer>>() {
			}.getType();

			log.debug("Binding data of Excel Header and Excel data");
			List<String> headerList = gson.fromJson(headerListStr, listType);
			List<Map<String, Object>> dataList = gson.fromJson(dataListStr, listOfMapType);
			Map<String, Integer> dataIndexMap = gson.fromJson(dataIndexMapStr, mapType);

			log.debug("Taking new excel sheet on reportName to write data");
			Sheet sheet = workbook.createSheet(reportName);

			// Create a Font for styling header cells
			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			headerFont.setFontHeightInPoints((short) 14);
			headerFont.setColor(IndexedColors.BLUE.getIndex());

			// Create a CellStyle with the font
			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);

			log.debug("Writing data of header in Excel");
			Row headerRow = sheet.createRow(0);
			for (int i = 0; i < headerList.size(); i++) {
				Cell cell = headerRow.createCell(i);
				cell.setCellValue(headerList.get(i));
				cell.setCellStyle(headerCellStyle);
			}

			int rowNum = 1;
			for (int i = 0; i < dataList.size(); i++) {
				Map<String, Object> rowData = dataList.get(i);

				Row row = sheet.createRow(rowNum++);
				log.debug("Writing data on Row [{}]", rowNum);

				for (Map.Entry<String, Object> entry : rowData.entrySet()) {
					Cell cell = row.createCell(dataIndexMap.get(entry.getKey()));
					cell.setCellValue(entry.getValue() != null ? entry.getValue().toString() : "");
				}
			}

			// Resize all columns to fit the content size
			for (int i = 0; i < headerList.size(); i++) {
				sheet.autoSizeColumn(i);
			}

			FileOutputStream fileOut = new FileOutputStream(tempFile);
			workbook.write(fileOut);
			fileOut.close();
		}
		catch (Exception e) {
			log.error("Error generating excel report [{}]", e);
		}
		finally {
			workbook.close();
		}

		return tempFile;
	}

}
