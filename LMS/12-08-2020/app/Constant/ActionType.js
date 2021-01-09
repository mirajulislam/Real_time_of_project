Ext.define('nApp.constants.ActionType', {
	alias: 'appActionType',
	alternateClassName: 'appActionType',
	statics: {
		ACTION_TYPE_SELECT: 'SELECT',
		ACTION_TYPE_NEW: 'NEW',
		ACTION_TYPE_UPDATE: 'UPDATE',
		ACTION_TYPE_NEW_UPDATE_LIABILITY: 'NEW_UPDATE_LIABILITY',
		ACTION_TYPE_DELETE: 'DELETE',
		ACTION_TYPE_SELECT_CUSTOMER: 'SELECT_CUSTOMER',
		ACTION_TYPE_SELECT_LOAN: 'SELECT_LOAN',
		ACTION_TYPE_SELECT_ALL_LOAN: 'SELECT_ALL_LOAN',
		ACTION_TYPE_SELECT_SAVE: 'SAVE',
		ACTION_TYPE_SAVE_APPLICATION: 'SAVE_APPLICATION',
		ACTION_TYPE_SELECT_LOAN_PERCENT: 'SELECT_LOAN_PERCENT',
		ACTION_TYPE_UPDATE_APPLICATION: 'UPDATE_APPLICATION',
		ACTION_TYPE_SELECT_FULL_LOAN: 'SELECT_FULL_LOAN',
		ACTION_TYPE_SELECT_LOAN_CONFIG: 'SELECT_LOAN_CONFIG',
		ACTION_TYPE_RECOMMEND: 'RECOMMEND',
		ACTION_TYPE_MIS_RECEIVE: 'MIS_RECEIVE',
		ACTION_TYPE_UH_APPROVE: 'UH_APPROVE',
		ACTION_TYPE_MIS_RECEIVE: 'MIS_RECEIVE',
		ACTION_STATE_TRANSITION: 'STATE_TRANSITION',
		ACTION_ALLOCATE: 'ALLOCATE',
		ACTION_TYPE_SELECT_DOC_MAP: 'SELECT_DOC_MAP',
		SELECT_RECOMMEND_TO_ROLE_USER: 'SELECT_RECOMMEND_TO_ROLE_USER',
		SELECT_RETURN_TO_ROLE_USER: 'SELECT_RETURN_TO_ROLE_USER',
		ACTION_TYPE_REFETC_N_CONFIGURATION: 'REFETCH_N_CONFIGURATION',
		ACTION_TYPE_DELETE_FULL_LOAN: 'DELETE_FULL_LOAN',
		ACTION_TYPE_SELECT_DOC_FOR_EXISTING_LOAN: 'SELECT_DOC_FOR_EXISTING_LOAN',
		ACTION_TYPE_SELECT_LOAN_ALL_DOCUMENT: 'SELECT_LOAN_ALL_DOCUMENT',
		SELECT_CIB_STATUS_DOC: 'SELECT_CIB_STATUS_DOC',
		SELECT_WORK_HISTORY: 'SELECT_WORK_HISTORY',
		LOAN_STATUS_DATE_WISE: 'LOAN_STATUS_DATE_WISE',
		LOAN_STATUS_DEPT_WISE: 'LOAN_STATUS_DEPT_WISE',
		LOAN_TRACKER_DEPT_WISE: 'LOAN_TRACKER_DEPT_WISE',
		ACTION_TYPE_SELECT_LOAN_TRACKER_DEPT_WISE: 'SELECT_LOAN_TRACKER_DEPT_WISE',


		// Field Officer actions
		ACTION_TYPE_FO_CREATE: 'FO_CREATE',
		ACTION_TYPE_FO_UPDATE: 'FO_UPDATE',
		ACTION_TYPE_FO_DELETE: 'FO_DELETE',
		ACTION_TYPE_FO_SUBMIT: 'FO_SUBMIT',

		// Source Officer actions
		ACTION_TYPE_SO_CREATE: 'SO_CREATE',
		ACTION_TYPE_SO_UPDATE: 'SO_UPDATE',
		ACTION_TYPE_SO_DELETE: 'SO_DELETE',
		ACTION_TYPE_SO_RECOMMEND: 'SO_RECOMMEND',
		ACTION_TYPE_SO_RE_RECOMMEND: 'SO_RE_RECOMMEND',

		// BRANCH_MANAGER action
		ACTION_TYPE_BM_RECOMMEND	: 'BM_RECOMMEND',
		ACTION_TYPE_BM_RETURN		: 'BM_RETURN',

		// BRANCH_OPERATION_MANAGER actions
		ACTION_TYPE_BOM_RECOMMEND	: 'BOM_RECOMMEND',
		ACTION_TYPE_BOM_RETURN		: 'BOM_RETURN',

		// POLICE_PORTFOLIO_COORDINATOR actions
		ACTION_TYPE_PPC_RECOMMEND	: 'PPC_RECOMMEND',
		ACTION_TYPE_PPC_RETURN		: 'PPC_RETURN',

		//MD's actions
		ACTION_TYPE_MD_APPROVE 		: "MD_APPROVE",
		ACTION_TYPE_MD_C_APPROVE 	: "MD_C_APPROVE",
		ACTION_TYPE_MD_RETURN 		: "MD_RETURN",
		ACTION_TYPE_MD_DECLINE 		: "MD_DECLINE",
		ACTION_TYPE_MD_DEFER 		: "MD_DEFER",

		//CEO's actions
		ACTION_TYPE_CEO_APPROVE 	: "CEO_APPROVE",
		ACTION_TYPE_CEO_C_APPROVE 	: "CEO_C_APPROVE",
		ACTION_TYPE_CEO_RETURN 		: "CEO_RETURN",
		ACTION_TYPE_CEO_DECLINE 	: "CEO_DECLINE",
		ACTION_TYPE_CEO_DEFER 		: "CEO_DEFER",

		//HOCRM's actions
		ACTION_TYPE_HOCRM_APPROVE 		: "HOCRM_APPROVE",
		ACTION_TYPE_HOCRM_C_APPROVE 	: "HOCRM_C_APPROVE",
		ACTION_TYPE_HOCRM_RECOMMEND 	: "HOCRM_RECOMMEND",
		ACTION_TYPE_HOCRM_RE_RECOMMEND 	: "HOCRM_RE_RECOMMEND",
		ACTION_TYPE_HOCRM_RETURN 		: "HOCRM_RETURN",
		ACTION_TYPE_HOCRM_DECLINE 		: "HOCRM_DECLINE",
		ACTION_TYPE_HOCRM_DEFER 		: "HOCRM_DEFER",
		ACTION_TYPE_HOCRM_SEND_TO_CAD	: "HOCRM_SEND_TO_CAD",
		ACTION_TYPE_BULK_HOCRM_SEND_TO_CAD	: "BULK_HOCRM_SEND_TO_CAD",

		//Unit Head'sacions
		ACTION_TYPE_UH_APPROVE 		: "UH_APPROVE",
		ACTION_TYPE_UH_C_APPROVE 	: "UH_C_APPROVE",
		ACTION_TYPE_UH_RETURN 		: "UH_RETURN",
		ACTION_TYPE_UH_RECOMMEND	: "UH_RECOMMEND",
		ACTION_TYPE_UH_RE_RECOMMEND	: "UH_RE_RECOMMEND",
		ACTION_TYPE_UH_DECLINE 		: "UH_DECLINE",
		ACTION_TYPE_UH_DEFER 		: "UH_DEFER",

		//Risk Manager's acions
		ACTION_TYPE_RM_APPROVE 		: "RM_APPROVE",
		ACTION_TYPE_RM_C_APPROVE 	: "RM_C_APPROVE",
		ACTION_TYPE_RM_RECOMMEND 	: "RM_RECOMMEND",
		ACTION_TYPE_RM_RE_RECOMMEND : "RM_RE_RECOMMEND",
		ACTION_TYPE_RM_RETURN 		: "RM_RETURN",
		ACTION_TYPE_RM_DECLINE 		: "RM_DECLINE",
		ACTION_TYPE_RM_DEFER 		: "RM_DEFER",

		//Credit Analyst's acions
		ACTION_TYPE_CA_RECEIVE		: "CA_RECEIVE",
		ACTION_TYPE_CA_UPDATE 		: "CA_UPDATE",
		ACTION_TYPE_CA_RETURN 		: "CA_RETURN",
		ACTION_TYPE_CA_RECOMMEND 	: "CA_RECOMMEND",
		ACTION_TYPE_CA_RE_RECOMMEND : "CA_RE_RECOMMEND",
		ACTION_TYPE_CA_SEND_QUERY 	: "CA_SEND_QUERY",
		ACTION_TYPE_CA_C_FULFILL 	: "CA_C_FULFILL",

		//MIS's acions
		ACTION_TYPE_MIS_RECEIVE 	: "MIS_RECEIVE",
		ACTION_TYPE_MIS_UPDATE 		: "MIS_UPDATE",
		ACTION_TYPE_MIS_SEND_TO_CAD : "SEND_TO_CAD",
		ACTION_TYPE_MIS_SEND_TO_CIB	: "SEND_TO_CIB",
		ACTION_TYPE_CA_SEND_TO_CIB	: "SEND_TO_CIB",
		ACTION_TYPE_MIS_RETURN		: "MIS_RETURN",
		ACTION_TYPE_MAIL_TO_POLICE	: "MAIL_TO_POLICE",
		ACTION_TYPE_MIS_ALLOCATE	: "MIS_ALLOCATE",
		ACTION_TYPE_MIS_RE_ALLOCATE	: "MIS_RE_ALLOCATE",

		ACTION_TYPE_SELECT_ROLE_TYPE		: "SELECT_ROLE_TYPE",
		ACTION_TYPE_SELECT_ASSIGNED_STATE    : "SELECT_ASSIGNED_STATE",
		ACTION_TYPE_SELECT_AVAILABLE_STATE    : "SELECT_AVAILABLE_STATE",
		ACTION_TYPE_ADD_AVAILABLE_STATE    : "ADD_AVAILABLE_STATE",
		ACTION_TYPE_REMOVE_AVAILABLE_STATE    : "REMOVE_ASSIGNED_STATE",

		ACTION_TYPE_SELECT_ALL_QUERY: 'SELECT_ALL_QUERY',
		ACTION_TYPE_PPC_EXCEL_REPORT: 'PPC_EXCEL_REPORT',

		ACTION_TYPE_EXECUTIVE_DASHBOARD_ALL_DATA: 'EXECUTIVE_DASHBOARD_ALL_DATA',
		ACTION_TYPE_ON_PROCESS_STATUS: 'ON_PROCESS_STATUS',
		ACTION_TYPE_FILE_RECEIVED_STATUS: 'FILE_RECEIVED_STATUS',
		ACTION_TYPE_STATUS_WISE_LOAN_COUNT: 'STATUS_WISE_COUNT_LATEST',
		ACTION_TYPE_DEPARTMENT_WISE_LOAN_COUNT: 'DEPARTMENT_WISE_COUNT',
		ACTION_TYPE_FO_BULK_SUBMIT: 'FO_BULK_SUBMIT',
		ACTION_TYPE_SELECT_LOAN_FOR_DEPT_BY_DATE: 'SELECT_LOAN_FOR_DEPT_BY_DATE',
		ACTION_TYPE_SEARCH_LOAN_FOR_DASHBOARD_VIEW: 'SEARCH_LOAN_FOR_DASHBOARD_VIEW',
		ACTION_TYPE_SELECT_ALL_CUSTOMER: 'SELECT_ALL_CUSTOMER',
		ACTION_TYPE_SELECT_STAFF_ID: 'SELECT_STAFF_ID',	
		ACTION_TYPE_LAST_CREDIT_ANAYLST_RECOMMAND: 'LAST_CREDIT_ANAYLST_RECOMMAND',		
		ACTION_TYPE_DASHBOARD_STATUS: 'DASHBOARD_STATUS',

		// approved to return action
		 ACTION_TYPE_APPROVED_RETURNED : 'APPROVED_RETURNED',
		 ACTION_TYPE_CAD_RETURNED : 'CAD_RETURNED',
		 ACTION_TYPE_CAD_RETURN		: "CAD_RETURN",
		 ACTION_TYPE_LOAN_DATA_SOURCE		: "LOAD_DATA_SOURCE",
		 ACTION_TYPE_CREATE_LOAN_GROUP		: "CREATE_LOAN_GROUP",
		 ACTION_TYPE_SELECT_ALL_LOAN_GROUP_DATA		: "SELECT_ALL_LOAN_GROUP_DATA",
		 ACTION_TYPE_ADD_LOAN_TO_LOAN_GROUP	: "ADD_LOAN_TO_LOAN_GROUP",
		 ACTION_TYPE_REMOVE_LOAN_FROM_LOAN_GROUP	: "REMOVE_LOAN_FROM_LOAN_GROUP",
		 ACTION_TYPE_SELECT_FOR_ADD_TO_LOAN_GROUP	: "SELECT_FOR_ADD_TO_LOAN_GROUP",
		 ACTION_TYPE_SEARCH_LOAN_GROUP_DATA    : "SEARCH_LOAN_GROUP_DATA",
		 ACTION_TYPE_SEARCH_LOAN_TRACKER_DEPT_WISE: "SEARCH_LOAN_TRACKER_DEPT_WISE",

	}
});


