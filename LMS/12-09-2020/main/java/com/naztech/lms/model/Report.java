package com.naztech.lms.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Report extends BaseModel{
	
	Customer customer;
	
	private Integer loanId;
	private Integer loanVer;
	private String loanTrackingId;
	private Integer userId;
	private Integer serial;
	private Integer number;
	private String branchName;
	private String fileRefNo;
	private String status;
	private Date sendToCadDate;
	private Double approvedAmount;
	private Date approvedDate;
	private Date receivedByCrm;
	private Date cibGenerationDate;
	private String mail;
	private String districName;
	private String submitUnit;
	private String unitName;
	private String unitName2;
	private String loanType;
	private Date mailSendDate;
	private Date ppcReceivedDate;
	private Date receivedCibFromCadDate;
	private Date submitToCrm;
	private String sourceTat;
	private Date sendToCrm;
	private String crmStatus;
	private Date approvedAmtDate;
	private String crmTat;
	private Date cadQuriesDate;
	private String cadStatus;
	private Date disbursedDate;
	private String cadTat;
	private Double gPFAmount;
	private String userName;
	private Double tenorYear;
	private String mobileOfGuarantor;
	private String guarantorNid;
	private String districtDivision;
	
	List<Integer> loandIdList = new ArrayList<Integer>();
	
	private static Map<String, String> sql2BeanMap = null;
	private static Map<String, String> rs2BeanMap = null;

	public static Map<String, String> getSql2BeanMap() {
		if (sql2BeanMap == null) {
			sql2BeanMap = new LinkedHashMap<String, String>();
			sql2BeanMap.putAll(getSql2BaseBeanMap());

			sql2BeanMap.put("@id_loan_key", "loanId");
			sql2BeanMap.put("@id_loan_ver", "loanVer");

			sql2BeanMap.put("@id_userId", "userId");

		}
		return sql2BeanMap;
	}

	public static Map<String, String> getRs2BeanMap() {
		if (rs2BeanMap == null) {
			rs2BeanMap = new LinkedHashMap<String, String>();
			rs2BeanMap.putAll(getRs2BaseBeanMap());

			rs2BeanMap.put("id_loan_key", "loanId");
			rs2BeanMap.put("id_loan_ver", "loanVer");

			rs2BeanMap.put("tx_loan_tracking_id", "loanTrackingId");
			rs2BeanMap.put("id_userId", "userId");
			rs2BeanMap.put("id_serial", "serial");
			rs2BeanMap.put("id_number", "number");
			rs2BeanMap.put("tx_legal_entity_name", "branchName");
			rs2BeanMap.put("tx_application_no", "fileRefNo");
			rs2BeanMap.put("tx_loan_current_status", "status");
			rs2BeanMap.put("dtt_sent_to_cad", "sendToCadDate");
			rs2BeanMap.put("dec_applied_loan_amount", "approvedAmount");
			rs2BeanMap.put("dtt_approved_date", "approvedDate");
			rs2BeanMap.put("dtt_crm_received_date", "receivedByCrm");
			rs2BeanMap.put("dtt_cib_generation_date", "cibGenerationDate");
			rs2BeanMap.put("tx_progress_status", "progressStatus");
			rs2BeanMap.put("tx_verification_email", "mail");
			rs2BeanMap.put("tx_distric_name", "districName");
			rs2BeanMap.put("tx_loan_creator", "submitUnit");
			rs2BeanMap.put("tx_unit_name2", "unitName2");
			rs2BeanMap.put("tx_loan_type", "loanType");
			rs2BeanMap.put("dtt_ppc_received_date", "fileReceivedDate");
			rs2BeanMap.put("dtt_mail_send_date", "mailSendDate");
			rs2BeanMap.put("dtt_ppc_received_date", "ppcReceivedDate");
			rs2BeanMap.put("dtt_cib_upload_date", "receivedCibFromCadDate");
			rs2BeanMap.put("dtt_sent_to_crm", "submitToCrm");
			rs2BeanMap.put("tx_source_tat", "sourceTat");
			rs2BeanMap.put("dtt_send_to_cad", "sendToCad");
			rs2BeanMap.put("tx_crm_status", "crmStatus");
			rs2BeanMap.put("dtt_submit_to_crm_date", "approvedAmtDate");
			rs2BeanMap.put("tx_crm_tat", "crmTat");
			rs2BeanMap.put("dtt_cad_quries_date", "cadQuriesDate");
			rs2BeanMap.put("tx_cad_satatus", "cadStatus");
			rs2BeanMap.put("dtt_disbursed_date", "disbursedDate");
			rs2BeanMap.put("tx_cad_tat", "cadTat");
			rs2BeanMap.put("dec_gPF_amount", "gPFAmount");
			rs2BeanMap.put("tx_user_name", "userName");
			rs2BeanMap.put("dec_tenor_year", "tenorYear");
			rs2BeanMap.put("tx_guarantor_nid", "guarantorNid");
			rs2BeanMap.put("tx_mobile_guarantor", "mobileOfGuarantor");
			rs2BeanMap.put("tx_district_division", "districtDivision");

		}
		return rs2BeanMap;
	}

	public Double getgPFAmount() {
		return gPFAmount;
	}

	public void setgPFAmount(Double gPFAmount) {
		this.gPFAmount = gPFAmount;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Integer getLoanId() {
		return loanId;
	}

	public void setLoanId(Integer loanId) {
		this.loanId = loanId;
	}

	public Integer getLoanVer() {
		return loanVer;
	}

	public void setLoanVer(Integer loanVer) {
		this.loanVer = loanVer;
	}
	
	public String getLoanTrackingId() {
		return loanTrackingId;
	}

	public void setLoanTrackingId(String loanTrackingId) {
		this.loanTrackingId = loanTrackingId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getSerial() {
		return serial;
	}

	public void setSerial(Integer serial) {
		this.serial = serial;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getFileRefNo() {
		return fileRefNo;
	}

	public void setFileRefNo(String fileRefNo) {
		this.fileRefNo = fileRefNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getSendToCadDate() {
		return sendToCadDate;
	}

	public void setSendToCadDate(Date sendToCadDate) {
		this.sendToCadDate = sendToCadDate;
	}

	public Double getApprovedAmount() {
		return approvedAmount;
	}

	public void setApprovedAmount(Double approvedAmount) {
		this.approvedAmount = approvedAmount;
	}
	
	public Date getApprovedDate() {
		return approvedDate;
	}

	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
	}

	public Date getReceivedByCrm() {
		return receivedByCrm;
	}

	public void setReceivedByCrm(Date receivedByCrm) {
		this.receivedByCrm = receivedByCrm;
	}

	public Date getCibGenerationDate() {
		return cibGenerationDate;
	}

	public void setCibGenerationDate(Date cibGenerationDate) {
		this.cibGenerationDate = cibGenerationDate;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getDistricName() {
		return districName;
	}

	public void setDistricName(String districName) {
		this.districName = districName;
	}

	public String getSubmitUnit() {
		return submitUnit;
	}

	public void setSubmitUnit(String submitUnit) {
		this.submitUnit = submitUnit;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getUnitName2() {
		return unitName2;
	}

	public void setUnitName2(String unitName2) {
		this.unitName2 = unitName2;
	}

	public String getLoanType() {
		return loanType;
	}

	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}

	public Date getMailSendDate() {
		return mailSendDate;
	}

	public void setMailSendDate(Date mailSendDate) {
		this.mailSendDate = mailSendDate;
	}

	public Date getPpcReceivedDate() {
		return ppcReceivedDate;
	}

	public void setPpcReceivedDate(Date ppcReceivedDate) {
		this.ppcReceivedDate = ppcReceivedDate;
	}

	public Date getReceivedCibFromCadDate() {
		return receivedCibFromCadDate;
	}

	public void setReceivedCibFromCadDate(Date receivedCibFromCadDate) {
		this.receivedCibFromCadDate = receivedCibFromCadDate;
	}

	public Date getSubmitToCrm() {
		return submitToCrm;
	}

	public void setSubmitToCrm(Date submitToCrm) {
		this.submitToCrm = submitToCrm;
	}

	public String getSourceTat() {
		return sourceTat;
	}

	public void setSourceTat(String sourceTat) {
		this.sourceTat = sourceTat;
	}

	public Date getSendToCrm() {
		return sendToCrm;
	}

	public void setSendToCrm(Date sendToCrm) {
		this.sendToCrm = sendToCrm;
	}

	public String getCrmStatus() {
		return crmStatus;
	}

	public void setCrmStatus(String crmStatus) {
		this.crmStatus = crmStatus;
	}

	public Date getApprovedAmtDate() {
		return approvedAmtDate;
	}

	public void setApprovedAmtDate(Date approvedAmtDate) {
		this.approvedAmtDate = approvedAmtDate;
	}

	public String getCrmTat() {
		return crmTat;
	}

	public void setCrmTat(String crmTat) {
		this.crmTat = crmTat;
	}

	public Date getCadQuriesDate() {
		return cadQuriesDate;
	}

	public void setCadQuriesDate(Date cadQuriesDate) {
		this.cadQuriesDate = cadQuriesDate;
	}

	public String getCadStatus() {
		return cadStatus;
	}

	public void setCadStatus(String cadStatus) {
		this.cadStatus = cadStatus;
	}

	public Date getDisbursedDate() {
		return disbursedDate;
	}

	public void setDisbursedDate(Date disbursedDate) {
		this.disbursedDate = disbursedDate;
	}

	public String getCadTat() {
		return cadTat;
	}

	public void setCadTat(String cadTat) {
		this.cadTat = cadTat;
	}

	public List<Integer> getLoandIdList() {
		return loandIdList;
	}

	public void setLoandIdList(List<Integer> loandIdList) {
		this.loandIdList = loandIdList;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Double getTenorYear() {
		return tenorYear;
	}

	public void setTenorYear(Double tenorYear) {
		this.tenorYear = tenorYear;
	}

	public String getMobileOfGuarantor() {
		return mobileOfGuarantor;
	}

	public void setMobileOfGuarantor(String mobileOfGuarantor) {
		this.mobileOfGuarantor = mobileOfGuarantor;
	}

	public String getGuarantorNid() {
		return guarantorNid;
	}

	public void setGuarantorNid(String guarantorNid) {
		this.guarantorNid = guarantorNid;
	}

	public String getDistrictDivision() {
		return districtDivision;
	}

	public void setDistrictDivision(String districtDivision) {
		this.districtDivision = districtDivision;
	}
	
	
}
