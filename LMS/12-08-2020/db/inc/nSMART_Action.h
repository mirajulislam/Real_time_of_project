/* __CC_INFO__  : */
/******************************************************************************
* File Name   : nSMART_Action.h
* Description : GeneralSQL Header File
* Author      : Naz Ahmed
******************************************************************************/


#ifndef NSMART_ACTION_H
#define NSMART_ACTION_H	{};


#define _ACTION_NEW									{'NEW'};
#define _ACTION_UPDATE								{'UPDATE'};
#define _ACTION_UPDATE_WITHOUT_TRANSITION			{'UPDATE_WITHOUT_TRANSITION'};
#define _ACTION_SUBMIT								{'REQUEST_APPROVAL'};
#define _ACTION_APPROVE								{'APPROVE'};
#define _ACTION_REJECT								{'REJECT'};
#define _ACTION_AUTHORIZE							{'AUTHORIZE'};

#define _ACTION_MANUAL_MATCH						{'MANUAL_MATCH'};
#define _ACTION_AUTO_MATCH							{'AUTO_MATCH'};
#define _ACTION_MANUAL_UNMATCH						{'MANUAL_UNMATCH'};
#define _ACTION_AUTO_UNMATCH						{'AUTO_UNMATCH'};

#define _ACTION_MAP_CORP_TO_SSI						{'MAP_CORP_TO_SSI'};
#define _ACTION_UNMAP_CORP_TO_SSI					{'UNMAP_CORP_TO_SSI'};
#define _ACTION_MODIFY_CORP_TO_SSI_MAP				{'MODIFY_CORP_TO_SSI_MAP'};
#define _ACTION_SSI_INSTANCE_SWITCH_TEMPLATE		{'SSI_INSTANCE_SWITCH_TEMPLATE'};


#define _ACTION_SYS_MAP_CORP_TO_SSI					{'SYS_MAP_CORP_TO_SSI'};
#define _ACTION_SYS_UNMAP_CORP_TO_SSI				{'SYS_UNMAP_CORP_TO_SSI'};

#define _ACTION_SYS_MAP_VALUABLE_TO_PERSON			{'SYS_MAP_VALUABLE_TO_PERSON'};
#define _ACTION_SYS_UNMAP_VALUABLE_TO_PERSON		{'SYS_UNMAP_VALUABLE_TO_PERSON'};


#define _ACTION_MAP_VALUABLE_TO_SSI					{'MAP_VALUABLE_TO_SSI'};
#define _ACTION_UNMAP_VALUABLE_TO_SSI				{'UNMAP_VALUABLE_TO_SSI'};
#define _ACTION_MODIFY_VALUABLE_TO_SSI_MAP			{'MODIFY_VALUABLE_TO_SSI_MAP'};


/* ACT _valuable */
#define _ACTION_MAP_VALUABLE_TO_PERSON				{'MAP_VALUABLE_TO_PERSON'};
#define _ACTION_UNMAP_VALUABLE_TO_PERSON			{'UNMAP_VALUABLE_TO_PERSON'};
#define _ACTION_MODIFY_VALUABLE_TO_PERSON_MAP		{'MODIFY_VALUABLE_TO_PERSON_MAP'};



/* ACT_person */
#define _ACTION_MAP_PERSON_TO_VALUABLE				{'MAP_PERSON_TO_VALUABLE'};
#define _ACTION_UNMAP_PERSON_TO_VALUABLE			{'UNMAP_PERSON_TO_VALUABLE'};
#define _ACTION_MODIFY_PERSON_TO_VALUABLE_MAP		{'MODIFY_PERSON_TO_VALUABLE_MAP'};



#define _ACTION_SYS_MAP_VALUABLE_TO_SSI				{'SYS_MAP_VALUABLE_TO_SSI'};
#define _ACTION_SYS_UNMAP_VALUABLE_TO_SSI			{'SYS_UNMAP_VALUABLE_TO_SSI'};


#define _ACTION_MAP_USER							{'MAP_USER'};
#define _ACTION_UNMAP_USER							{'UNMAP_USER'};

#define _ACTION_TREE								{'TREE'};

#define _ACTION_AUTHORIZE							{'AUTHORIZE'};
#define _ACTION_ACKNOWLEDGE							{'ACKNOWLEDGE'};
#define _ACTION_PEND_ACKNOWLEDGE					{'PEND_ACKNOWLEDGE'};
#define _ACTION_BAD									{'BAD'};
#define _ACTION_HIT									{'HIT'};
#define _ACTION_CONFIRM								{'CONFIRM'};
#define _ACTION_BONY_ACKNOWLEDGE					{'BONY_ACKNOWLEDGE'};
#define _ACTION_BONY_REJECT							{'BONY_REJECT'};


#define _ACTION_CLEAR								{'CLEAR'};
#define _ACTION_PROCESS								{'PROCESS'};
#define _ACTION_PROCESS_DTC							{'PROCESS_DTC'};
#define _ACTION_PROCESS_FED							{'PROCESS_FED'};
#define _ACTION_PROCESS_MT_950						{'PROCESS_MT_950'};

#define _ACTION_PROCESS_NSMART_RECONCILE			{'PROCESS_NSMART_RECONCILE'};
#define _ACTION_UPATE_TEMPLATE						{'UPDATE_TEMPLATE'};
#define _ACTION_SAVE_TEMPLATE_AS_NEW				{'SAVE_TEMPLATE_AS_NEW'};

#define _ACTION_AUDIT_FILE_HISTORY					{'AUDIT_FILE_HISTORY'};

#define _ACTION_SYSTEM_AUTO_APPROVE					{'SYS_AUTO_APPROVE'};

/* ACT_payment */
#define _ACTION_SELECT_SOURCE_PAYMENT				{'SELECT_SOURCE_PAYMENT'};
#define _ACTION_PAYMENT_HISTORY						{'SELECT_PAYMENT_HISTORY'};
#define _ACTION_SELECT_PAS_UNNETTED_PAYMENT			{'SELECT_PAS_UNNETTED_PAYMENT'};

#define _ACTION_PAYMENT_INCLUDE						{'PAYMENT_INCLUDE'};
#define _ACTION_PAYMENT_EXCLUDE						{'PAYMENT_EXCLUDE'};

#define _ACTION_PAYMENT_CHECK						{'PAYMENT_CHECK'};
#define _ACTION_PAYMENT_UNCHECK						{'PAYMENT_UNCHECK'};

#define _ACTION_PAYMENT_SSI_MOD						{'PAYMENT_SSI_MOD'};

#define _ACTION_SEL_PROCESSED_FILE_HISTORY			{'FILE_HISTORY'};

#define _ACTION_PAYMENT_MATCH						{'PAYMENT_MATCH'};
#define _ACTION_PAYMENT_MANUAL_MATCH				{'PAYMENT_MANUAL_MATCH'};
#define _ACTION_PAYMENT_AUTO_UNMATCH				{'PAYMENT_AUTO_UNMATCH'};
#define _ACTION_PAYMENT_MANUAL_UNMATCH				{'PAYMENT_MANUAL_UNMATCH'};
#define _ACTION_PAYMENT_AUTO_MATCH_MT910			{'PAYMENT_AUTO_MATCH_MT910'};
#define _ACTION_PAYMENT_AUTO_MATCH_MT900			{'PAYMENT_AUTO_MATCH_MT900'};
#define _ACTION_PAYMENT_AUTO_MATCH_FED_DTC			{'PAYMENT_AUTO_MATCH_FED_DTC'};
#define _ACTION_PAYMENT_AUTO_MATCH_MT_545			{'PAYMENT_AUTO_MATCH_MT_545'};
#define _ACTION_PAYMENT_AUTO_MATCH_MT_547			{'PAYMENT_AUTO_MATCH_MT_547'};
#define _ACTION_PAYMENT_AUTO_MATCH_MT_950_NSEC		{'PAYMENT_AUTO_MATCH_MT_950_NSEC'};
#define _ACTION_PAYMENT_AUTO_MATCH_CUSIP			{'PAYMENT_AUTO_MATCH_CUSIP'};
#define _ACTION_PAYMENT_AUTO_MATCH_MT700			{'PAYMENT_AUTO_MATCH_MT700'};
#define _ACTION_PAYMENT_AUTO_MATCH_MT740			{'PAYMENT_AUTO_MATCH_MT740'};

#define _ACTION_PAYMENT_MATCH_SWIFT_MSG				{'PAYMENT_MATCH_SWIFT_MSG'};
#define _ACTION_PAYMENT_UNMATCH						{'PAYMENT_UNMATCH'};
#define _ACTION_PAYMENT_ALLOCATE					{'ALLOCATE'};

#define _ACTION_SEL_PAYMENT_ACTUAL_ALL				{'SEL_PAYMENT_ACTUAL_ALL'};
#define _ACTION_SEL_PAYMENT_MATCHED					{'SEL_PAYMENT_MATCHED'};
#define _ACTION_SEL_PAYMENT_UNMATCHED				{'SEL_PAYMENT_UNMATCHED'};
#define _ACTION_SEL_PAYMENT_ALLOCATION				{'SEL_PAYMENT_ALLOCATION'};


#define _ACTION_SEL_PEND_AUTHORIZE_PAYMENT			{'SEL_PEND_AUTHORIZE_PAYMENT'};
#define _ACTION_SEL_SWIFT_PAYMENT					{'SEL_SWIFT_PAYMENT'};




#define _ACTION_SELECT_BSA_VALUABLE					{'SELECT_BSA_VALUABLE'};
#define _ACTION_SELECT_BSA_CORP						{'SELECT_BSA_CORP'};

#define _ACTION_UNMAP_MULTILOT						{'UNMAP_MULTILOT'};
#define _ACTION_MULTILOT							{'MULTILOT'};
#define _ACTION_PAYMENT_AUTO_MATCH_DTC_FED			{'PAYMENT_AUTO_MATCH_DTC_FED'};

#define _ACTION_SYS_AUTO_APPROVE					{'SYS_AUTO_APPROVE'};

#define _ACTION_SELECT_PAYMENT_ACTUAL_MATCHED		{'SELECT_PAYMENT_ACTUAL_MATCHED'};
#define _ACTION_SELECT_PAYMENT_MATCHED_ALL			{'SELECT_PAYMENT_MATCHED_ALL'};
#define _ACTION_SELECT_PAYMENT_UNMATCHED_ALL		{'SELECT_PAYMENT_UNMATCHED_ALL'};
#define _ACTION_SELECT_PAYMENT_EXPECTED_MATCHED		{'SELECT_PAYMENT_EXPECTED_MATCHED'};
#define _ACTION_SELECT_PAYMENT_MATCHED_ALL			{'SELECT_PAYMENT_MATCHED_ALL'};
#define _ACTION_SELECT_PAYMENT_UNMATCHED_ALL		{'SELECT_PAYMENT_UNMATCHED_ALL'};
#define _ACTION_SELECT_PAS_UPDATED_PAYMENT			{'SELECT_PAS_UPDATED_PAYMENT'};
#define _ACTION_SELECT_BANK_ACCOUNT_TRANSACTION		{'SELECT_BANK_ACCOUNT_TRANSACTION'};
#define _ACTION_SELECT_DRAFT_BALANCE_FOR_INCLUDE	{'SELECT_DRAFT_BALANCE_FOR_INCLUDE'};

#define _ACTION_SELECT_BANK_ACCOUNT_REPORT			{'SELECT_BANK_ACCOUNT_REPORT'};
#define _ACTION_SELECT_EXPECTED_ACTUAL_REPORT		{'SELECT_EXPECTED_ACTUAL_REPORT'};


#define _ACTION_PROCESS_NSEC_TRANSACTION			{'PROCESS_NSEC_TRANSACTION'};
#define _ACTION_RECON_BANK_ACCOUNT_TRANSACTION		{'RECON_BANK_ACCOUNT_TRANSACTION'};

#define _ACTION_SELECT_SWIFT_MESSAGES				{'SELECT_SWIFT_MESSAGES'};
#define _ACTION_SELECT_PAYMENT_SWIFT_MESSAGES		{'SELECT_PAYMENT_SWIFT_MESSAGES'};

#define _ACTION_CLEAR_ALL							{'ALL'};
#define _ACTION_CLEAR_EXPECTED						{'EXPECTED'};
#define _ACTION_CLEAR_ACTUAL						{'ACTUAL'};

#define _ACTION_ROLL_PAYMENT						{'ROLL_PAYMENT'};
#define _ACTION_SUPRESS_ROLL_OVER					{'SUPRESS_ROLL_OVER'};
#define _ACTION_UNSUPRESS_ROLL_OVER					{'UNSUPRESS_ROLL_OVER'};

#define _ACTION_RECALC_BALANCE						{'RECALC_BALANCE'};
#define _ACTION_RECALC_BALANCE_EACH_DAY				{'RECALC_BALANCE_EACH_DAY'};

#define _ACTION_SELECT_NSMART_VS_JSHEET				{'SELECT_NSMART_VS_JSHEET'};
#define _ACTION_SELECT_NSMART_VS_PAS				{'SELECT_NSMART_VS_PAS'};
#define _ACTION_SELECT_CASH_IN_OUT					{'SELECT_CASH_IN_OUT'};

/* SEL ACTIVITY RECORD */
#define _ACTION_SELECT_CORPORATION					{'SELECT_CORPORATION'};
#define _ACTION_SELECT_PERSON						{'SELECT_PERSON'};
#define _ACTION_SELECT_SSI							{'SELECT_SSI'};
#define _ACTION_SELECT_VALUABLE						{'SELECT_VALUABLE'};
#define _ACTION_SELECT_BANK_ACCOUNT					{'SELECT_BANK_ACCOUNT'};
#define _ACTION_SELECT_EXPECTED_PAYMENT				{'SELECT_EXPECTED_PAYMENT'};
#define _ACTION_SELECT_ACTUAL_PAYMENT				{'SELECT_ACTUAL_PAYMENT'};

#define _ACTION_DEXIA_NSMART_MANUAL_MAP				{'DEXIA_NSMART_MANUAL_MAP'};

#define _ACTION_SELECT_APPROVED_BIC					{'SELECT_APPROVED_BIC'};

/* SEL mapping for Export */
#define _ACTION_SELECT_CORP_SSI_MAP					{'SELECT_CORP_SSI_MAP'};
#define _ACTION_SELECT_VALUABLE_PERSON_MAP			{'SELECT_VALUABLE_PERSON_MAP'};
#define _ACTION_SELECT_VALUABLE_SSI_MAP				{'SELECT_VALUABLE_SSI_MAP'};

/* Wire Memo report */
#define _ACTION_SELECT_WIRE_MEMO					{'SELECT_WIRE_MEMO'};

/* Cash Recap report */
#define _ACTION_SELECT_AMC_CASH_RECAP				{'SELECT_AMC_CASH_RECAP'};
#define _ACTION_SELECT_CMU_CASH_RECAP				{'SELECT_CMU_CASH_RECAP'};

/* Kyc Action*/

#define _ACTION_REQUEST_AUTHORIZE					{'REQUEST_AUTHORIZE'};
#define _ACTION_HIT									{'HIT'};
#define _ACTION_CLEAR								{'CLEAR'};
#define _ACTION_BLOCK								{'BLOCK'};
#define _ACTION_CHECK								{'CHECK'};

#define _ACTION_KYC_CHECK							{'KYC_CHECK'};
#define _ACTION_KYC_AUTO_RECHECK					{'KYC_AUTO_RECHECK'};

#define _ACTION_SELECT_COUNT						{'SELECT_COUNT'};

/* last poll action */
#define _ACTION_UPDATE_LAST_POLL_STATUS 			{'UPDATE_LAST_POLL_STATUS'};

/* swift msg action */
#define _ACTION_REQUEST_BLOCK 						{'REQUEST_BLOCK'};

/*swift file action*/
#define _ACTION_GET_STATUS 							{'GET_STATUS'};

#define _ACTION_KYC_HIT_SUMMARY 					{'KYC_HIT_SUMMARY'};
#define _ACTION_KYC_HEAD_OFFICE_RPT 				{'KYC_HEAD_OFFICE_RPT'};

#define _ACTION_ROUTE_TO_BRANCH 					{'ROUTE_TO_BRANCH'};

#define _ACTION_SWIFT_HEAD_OFFICE_RPT 				{'SWIFT_HEAD_OFFICE_RPT'};

#define _ACTION_REFER_FOR_AUTHORIZE 				{'REFER_FOR_AUTHORIZE'};
#define _ACTION_REFER_FOR_BLOCK 					{'REFER_FOR_BLOCK'};
#define _ACTION_GRID_EXPORT 						{'GRID_EXPORT'};
#define _ACTION_INBOUND_COMPLETE 					{'COMPLETE'};
#define _ACTION_GET_PROCESSED_SWIFT_MESSAGES		{'GET_PROCESSED_SWIFT_MESSAGES'};
#define _ACTION_GET_UNPROCESSED_SWIFT_MESSAGES		{'GET_UNPROCESSED_SWIFT_MESSAGES'};

#define _ACTION_UPDATE_STATE 						{'UPDATE_STATE'};
#define _ACTION_NON_NSMART_ACK 						{'NON_NSMART_ACK'};
#define _ACTION_NON_NSMART_NACK 					{'NON_NSMART_NACK'};
#define _ACTION_SELECT_NACK_REASON 					{'SELECT_NACK_REASON'};
#define _CLEAR_STAGE_TABLE 							{'CLEAR_STAGE_TABLE'};

#define _ACTION_UPDATE_WITH_CORRECTION_CHECK 		{'UPDATE_WITH_CORRECTION_CHECK'};
#define _ACTION_CHECK_BASE_TXN 						{'CHECK_BASE_TXN'};
#define _ACTION_GET_FROM_DATE_BY_ACC				{'GET_FROM_DATE_BY_ACC'};
#define _ACTION_UPD_FIELD_EDT_OPTION 				{'UPD_FIELD_EDT_OPTION'};
#define _ACTION_WEEKLY_REPORT 						{'WEEKLY_REPORT'};
#define _ACTION_SELECT_AUDIT 						{'SELECT_AUDIT'};
#define _ACTION_MONTHLY_REPORT 						{'MONTHLY_REPORT'};
#define _ACTION_MONTHLY_REPORT_FROM_1_MONTH 		{'MONTHLY_REPORT_FROM_1_MONTH'};
#define _ACTION_SELECT_PAYMENT_FROM_AUDIT 			{'SELECT_PAYMENT_FROM_AUDIT'};
#define _ACTION_SELECT_RERUN 						{'SELECT_RERUN'};
#define _ACTION_SEL_ACK_NACK_MAIL 					{'SEL_ACK_NACK_MAIL'};
#define _ACTION_HANDLE_CBS_CALLBACK 				{'HANDLE_CBS_CALLBACK'};
#define ACTION_SEND_TO_CBS 							{'SEND_TO_CBS'};
#define ACTION_INACTIVE_ALL_HS_CODE 				{'INACTIVE_ALL_HS_CODE'};
#define ACTION_INACTIVE_ALL 						{'INACTIVE_ALL'};
#define _ACTION_OK_CALLBACK 						{'OK_CALLBACK'};
#define _ACTION_CBS_PAYMENT_REJECT 					{'CBS_PAYMENT_REJECT'};
#define _ACTION_PEND_DELETE 						{'PEND_DELETE'};
#define _ACTION_SWIFT_ACKNOWLEDGE 					{'ACKNOWLEDGE'};
#define _ACTION_BEN_MAIL 							{'ACT_BEN_MAIL'};
#define _ACTION_SELECT_FAIL_MT 						{'SELECT_FAIL_MT'};
#define _ACTION_SSI_TEMPLATE_DELETE 				{'SSI_TEMPLATE_DELETE'};



#define _ACTION_ALLOCATE 				{'ALLOCATE'};
#define _ACTION_MIS_RECEIVE 			{'MIS_RECEIVE'};
#define _ACTION_UH_APPROVE 				{'UH_APPROVE'};

#endif