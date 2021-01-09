/* __CC_INFO__  :  */
/******************************************************************************
* File Name   : Type.h
* Description : Type File
* Author      : Naz Ahmed
* Date        : 14 June 2011
* Copyright	  : nazdaqTechnologies Inc.,
******************************************************************************/

#ifndef NSMART_MAP_TYPE_H
#define NSMART_MAP_TYPE_H {};

#define _TYPE_CORP							{'CORP'};
#define _TYPE_PERSON						{'PERSON'};
#define _TYPE_VALUABLE						{'VALUABLE'};
#define _TYPE_SSI							{'SSI'};
#define _TYPE_BANK_ACCOUNT					{'BANK_ACCOUNT'};
#define _TYPE_PAYMENT_TYPE					{'PAYMENT_TYPE'};
#define _TYPE_SOURCE_PAYMENT				{'SOURCE_PAYMENT'};
#define _TYPE_PAYMENT						{'PAYMENT'};
#define _TYPE_PAYMENT_ORIG					{'PAYMENT_ORIG'};
#define _TYPE_PAYMENT_PAS					{'PAYMENT_PAS'};
#define _TYPE_PAYMENT_JSHEET				{'PAYMENT_JSHEET'};
#define _TYPE_PAYMENT_DTC					{'PAYMENT_TYPE_DTC'};

/* PAS PAYMENT RELATED */
#define _TYPE_PAS_PAYMENT					{'PAS'};
#define _TYPE_JSHEET_PAYMENT				{'JSHEET'};
#define _TYPE_NSMART_PAYMENT				{'nSMART'};
#define _TYPE_SWIFT_PAYMENT					{'SWIFT'};

/* All about Product type [for valuable gic] */

#define _PRODUCT_TYPE_MGIC					{'MGIC'};
#define _PRODUCT_TYPE_SWAP					{'SWAP'};
#define _PRODUCT_TYPE_CASH					{'CASH'};
#define _PRODUCT_TYPE_INVESTMENT			{'INVESTMENT'};
#define _PRODUCT_TYPE_FUTURES				{'FUTURES'};
#define _PRODUCT_TYPE_FED_FUNDS				{'FED_FUNDS'};
#define _PRODUCT_TYPE_SWAP_COLL_IN			{'SWAP_COLL-IN'};
#define _PRODUCT_TYPE_SWAP_COLL_OUT			{'SWAP_COLL-OUT'};

#define _PAYMENT_TYPE_TRD					{'TRD'};
#define _PAYMENT_TYPE_PRI					{'PRI'};
#define _PAYMENT_TYPE_INT					{'INT'};
#define _PAYMENT_TYPE_DRW					{'DRW'};
#define _PAYMENT_TYPE_FEE					{'FEE'};
#define _PAYMENT_TYPE_ALL					{'ALL'};
#define _PAYMENT_TYPE_FUT					{'FUT'};


#define _CCY_ANY							{'ANY'};
#define _CCY_USD							{'USD'};

#define _TYPE_OUTBOUND						{'OUTBOUND'};
#define _TYPE_INBOUND						{'INBOUND'};
#define _TYPE_OTHER_PAYMENT_TYPE			{'PAYMENT_OTHER'};
#define _TYPE_NETTED_PAYMENT				{'NETTED_PAYMENT'};
#define _TYPE_UNNETTED_PAYMENT				{'UNNETTED_PAYMENT'};

#define _EXCLUDE_NEG_INT_CUSIP_EQUALS_POS_PRI	{'EXCLUDE_NEG_INT_CUSIP_EQUALS_POS_PRI'};
#define _EXCLUDE_NEG_PRI_EQUALS_POS_PRI		{'EXCLUDE_NEG_PRI_EQUALS_POS_PRI'};
#define _EXCLUDE_NEG_INT_EQUALS_POS_PRI		{'EXCLUDE_NEG_INT_EQUALS_POS_PRI'};
#define _COMMENT_375A_WITH_POS_AMOUNT		{'COMMENT_375A_WITH_POS_AMOUNT'};  /*Rule name*/

#define _EXCLUDE_POS_TRD_EQUALS_NEG_PRI		{'EXCLUDE_POS_TRD_EQUALS_NEG_PRI'};
#define _EXCLUDE_NEG_TRD_EQUALS_POS_PRI		{'EXCLUDE_NEG_TRD_EQUALS_POS_PRI'};
#define _EXCLUDE_POS_PRI_EQUALS_NEG_PRI		{'EXCLUDE_POS_PRI_EQUALS_NEG_PRI'};
#define _INVESTMENT_NETTING_RULE			{'INVESTMENT_NETTING_RULE'};
#define _CASH_NETTING_RULE					{'CASH_NETTING_RULE'};
#define _SWAP_NETTING_RULE					{'SWAP_NETTING_RULE'};
#define _JSHEET_NETTING_RULE				{'JSHEET_NETTING_RULE'};

#define _NETTING_COMMENT					{'SENT FAX TO OLGA'};
#define _ACCOUNT_NAME_375A					{'375A'};

/* ACT_stage_payment_futures.sp */
#define _PAYMENT_TYPE_OTHER_FUTURES			{'FUTURES'};
#define _TXN_TYPE_INTRADAY					{'TXN_INTRADAY'};
#define _TXN_TYPE_EOD						{'TXN_EOD'};
#define _BANK_BALANCE_TYPE_DRAFT			{'DRAFT'};
#define _BANK_BALANCE_TYPE_FINAL			{'FINAL'};
#define _TXN_TYPE_MODE_DR					{'DR'};
#define _TXN_TYPE_MODE_CR					{'CR'};


#define _CATEGORY_TYPE_PAYMENT_OTHER		{'PAYMENT_OTHER'};

#define _PAYMENT_SUB_TYPE_CATEGORY			{'PAYMENT_SUB_TYPE'};
#define _PAYMENT_SUB_TYPE_EXPECTED			{'PAYMENT_SUB_TYPE_EXPECTED'};
#define _PAYMENT_SUB_TYPE_ACTUAL			{'PAYMENT_SUB_TYPE_ACTUAL'};

/* V_PAYMENT_ORIG_MAPPING.vw */
#define _TYPE_PAYMENT_ORIG					{'PAYMENT_ORIG'};
#define _TYPE_PAYMENT						{'PAYMENT'};
#define _TYPE_BANK_BALANCE					{'BANK_BALANCE_TYPE'};

#define _SWIFT_MSG_REQUEST					{'SWIFT_MSG_REQUEST'};
#define _SWIFT_MSG_RESPONSE					{'SWIFT_MSG_RESPONSE'};

#define _TYPE_SWIFT_950_TXN					{'SWIFT_950_TXN'};
#define _TYPE_BANK_TRANSACTION				{'BANK_TRANSACTION'};
#define _TYPE_SWIFT_MESSAGE					{'SWIFT_MESSAGE'};

#define _TYPE_LOW_BALANCE_THRESHOLD			{'THRESHOLD'};
#define _TYPE_OVERNIGHT_DIFF				{'OVERNIGHT_DIFF'};
#define _TYPE_PEND_AUTHORIZE				{'PEND_AUTHORIZE'};
#define _TYPE_UNMATCH_PAYMENTS				{'UNMATCH_PAYMENTS'};
#define _TYPE_SWIFT_STATE_PAYMENTS			{'SWIFT_STATE'};
#define _TYPE_OFAC_HIT_STATE_PAYMENTS		{'OFAC_HIT'};
#define _TYPE_PAS_UPDATE_PAYMENTS			{'PAS_UPDATE'};
#define _TYPE_DASHBOARD_ALERT				{'DASHBOARD_ALERT'};
#define _TYPE_EOD_STATUS					{'EOD_STATUS'};

#define _TYPE_LEGAL_ENTITY_BIC				{'LEGAL_ENTITY_BIC'};
#define _TYPE_SENDER						{'SENDER'};
#define _TYPE_RECEIVER						{'RECEIVER'};

#define _FILE_ID_MT950_FSA					{'MT950_FSA'};
#define _FILE_ID_MT950_FAC					{'MT950_FAC'};
#define _FILE_ID_MT950_GBP					{'MT950_GBP'};

#define _TYPE_KYC							{'KYC'};
#define _TYPE_REMITTANCE					{'REMITTANCE'};
#define _TYPE_SWIFT_SEARCH					{'SWIFT_SEARCH'};
#define _TYPE_SWIFT_MODIFICATION			{'SWIFT_MODIFICATION'};
#define _TYPE_SWIFT_VERIFICATION			{'SWIFT_VERIFICATION'};
#define _TYPE_SWIFT_AUTHORIZATION			{'SWIFT_AUTHORIZATION'};
#define _TYPE_SWIFT_HIT						{'SWIFT_HIT'};
#define _TYPE_SWIFT_HIT_DETAILS				{'SWIFT_HIT_DETAILS'};
#define _TYPE_KYC_HIT						{'KYC_HIT'};

#define _TYPE_BULK_SSI						{'BULK_SSI'};
#define _TYPE_BULK_STATEMENT				{'BULK_STATEMENT'};
#define _TYPE_BULK_STATEMENT_LITE			{'BULK_STATEMENT_LITE'};
#define _TYPE_SWIFT_TEXT_SEARCH 			{'SWIFT_TEXT_SEARCH'};

#define _TYPE_SEARCH_EXACT 					{'EXACT'};
#define _TYPE_SEARCH_LIKE 					{'LIKE'};
#define _TYPE_SEARCH_SOUNDEX 				{'SOUNDEX'};

#define _TYPE_OFAC_KYC 						{'OFAC_KYC'};
#define _TYPE_OFAC_SWIFT 					{'OFAC_SWIFT'};

#define _TYPE_VESSEL 						{'VESSEL'};
#define _TYPE_AIRCRAFT 						{'AIRCRAFT'};
#define _TYPE_SWIFT_REMIANING_HIT_COUNT 	{'SWIFT_REMIANING_HIT_COUNT'};
#define _TYPE_SWIFT_REPORT 					{'SWIFT_REPORT'};
#define _TYPE_SWIFT_HIT_PREVIEW 			{'SWIFT_HIT_PREVIEW'};


#endif
