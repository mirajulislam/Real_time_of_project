package com.naztech.lms.constants;

/**
 * @author md.kamruzzaman
 */
public enum RSType {

	RS_TYPE_PREFERENCE("RS_TYPE_PREFERENCE"),
	RS_TYPE_LOAN("RS_TYPE_LOAN"),
	RS_TYPE_COMMENT("RS_TYPE_COMMENT"),
	RS_TYPE_LOAN_CONFIG("RS_TYPE_LOAN_CONFIG"),
	RS_TYPE_CONFIGURATION("RS_TYPE_CONFIGURATION"),
	RS_TYPE_LOAN_DOC_MAP("RS_TYPE_LOAN_DOC_MAP"),
	RS_TYPE_CUSTOMER("RS_TYPE_CUSTOMER"),
	RS_TYPE_EXISTING_LIABILITY("RS_TYPE_EXISTING_LIABILITY"),
	RS_TYPE_CIB_STATUS("RS_TYPE_CIB_STATUS"),
	RS_TYPE_ANALYSTS_COMMENTS("RS_TYPE_ANALYSTS_COMMENTS"),
	RS_TYPE_EXCEPTION_DETAILS("RS_TYPE_EXCEPTION_DETAILS"),
	RS_TYPE_INSTRUCTION_TO_CAD("RS_TYPE_INSTRUCTION_TO_CAD"),
	RS_TYPE_LOAN_FOR_GRID("RS_TYPE_LOAN_FOR_GRID"),
	RS_TYPE_LOAN_GROUP_FOR_GRID("RS_TYPE_LOAN_GROUP_FOR_GRID"),
	RS_TYPE_COMMENTS_JUSTIFICATION("RS_TYPE_COMMENTS_JUSTIFICATION"),
	RS_TYPE_COMMENTS_WAIVER_SOUGHT("RS_TYPE_COMMENTS_WAIVER_SOUGHT"),
	RS_TYPE_BM_RECOMMENDATION("RS_TYPE_BM_RECOMMENDATION"),
	RS_TYPE_SO_RECOMMENDATION("RS_TYPE_SO_RECOMMENDATION"),
	RS_TYPE_LOAN_DOCUMENT("RS_TYPE_LOAN_DOCUMENT"),
	RS_TYPE_LOAN_DOC_CIB_STATUS("RS_TYPE_LOAN_DOC_CIB_STATUS"),
	RS_TYPE_LOAN_DOCUMENT_NEW("RS_TYPE_LOAN_DOCUMENT_NEW"),
	RS_TYPE_LOAN_DOCUMENT_EXIST("RS_TYPE_LOAN_DOCUMENT_EXIST"),
	RS_TYPE_LOAN_2("RS_TYPE_LOAN_2"),
	RS_TYPE_USER("RS_TYPE_USER"),
	RS_TYPE_ROLE("RS_TYPE_ROLE"),
	RS_TYPE_TRACKING_ID("RS_TYPE_TRACKING_ID"),
	RS_TYPE_CONDITION("RS_TYPE_CONDITION"),
	RS_TYPE_BRANCH_NAME_STUFF_ID("RS_TYPE_BRANCH_NAME_STUFF_ID"),
	RS_TYPE_WAIVER_SOUGHT("RS_TYPE_WAIVER_SOUGHT"),

	RS_TYPE_ASSIGNED_STATE("RS_TYPE_ASSIGNED_STATE"),
	RS_TYPE_AVAILABLE_STATE("RS_TYPE_AVAILABLE_STATE"),
	RS_TYPE_REMOVE_ASSIGNED_STATE("RS_TYPE_REMOVE_ASSIGNED_STATE"),
	RS_TYPE_APPROVAL_CONDITION("RS_TYPE_APPROVAL_CONDITION"),
	RS_TYPE_LMS_LOAN_EXCEL_REPORT("RS_TYPE_LMS_LOAN_EXCEL_REPORT"),
	RS_TYPE_LMS_MIS_PPC_EXCEL_REPORT("RS_TYPE_LMS_MIS_PPC_EXCEL_REPORT"),
	RS_TYPE_ADD_AVAILABLE_STATE("RS_TYPE_ADD_AVAILABLE_STATE"),
	RS_TYPE_DASHBOARD_DATA("RS_TYPE_DASHBOARD_DATA"),
	RS_TYPE_DATA("RS_TYPE_DATA"),
	RS_TYPE_USER_LIST("RS_TYPE_USER_LIST"),
	RS_TYPE_DATE_LIST("RS_TYPE_DATE_LIST"),
	RS_TYPE_MANAGER("RS_TYPE_MANAGER"),
	RS_TYPE_LOAN_TYPE("RS_TYPE_LOAN_TYPE"),
	RS_TYPE_STAFF_ID("RS_TYPE_STAFF_ID"),
	RS_TYPE_DATA_SOURCE("RS_TYPE_DATA_SOURCE"),
	RS_LAST_CREDIT_ANAYLST_RECOMMAND("RS_LAST_CREDIT_ANAYLST_RECOMMAND"),

	RS_TYPE_WORK_HISTORY("RS_TYPE_WORK_HISTORY"),

	RS_TYPE_LOAN_VIEW_MEMO("RS_TYPE_LOAN_VIEW_MEMO"),
	RS_TYPE_LOAN_SEARCH_FOR_GRID("RS_TYPE_LOAN_SEARCH_FOR_GRID"),
	RS_TYPE_TOTAL_AMOUNT_FILE("RS_TYPE_TOTAL_AMOUNT_FILE"),
	RS_TYPE_YEAR_OF_REMAINING("RS_TYPE_YEAR_OF_REMAINING"),
	RS_TYPE_LOAN_TRACKER_DEPT_DATA("RS_TYPE_LOAN_TRACKER_DEPT_DATA"),
	RS_TYPE_FIELD_OFFICER_COUNT("RS_TYPE_FIELD_OFFICER_COUNT"),
	RS_TYPE_PPC_COUNT("RS_TYPE_PPC_COUNT"),
	RS_TYPE_CRM_COUNT("RS_TYPE_CRM_COUNT"),
	RS_TYPE_CAD_COUNT("RS_TYPE_CAD_COUNT"),
	RS_TYPE_TOTAL_COUNT("RS_TYPE_TOTAL_COUNT");

	private final String rsTypeName;

	private RSType(String rsTypeName) {
		this.rsTypeName = rsTypeName;
	}

	@Override
	public String toString() {
		return rsTypeName;
	}

}
