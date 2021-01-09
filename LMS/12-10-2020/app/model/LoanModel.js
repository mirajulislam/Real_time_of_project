Ext.define('Desktop.model.LoanModel', {
	extend: 'Desktop.model.BaseModel',

	requires: [
		'Ext.data.field.Field'
	],

	fields	: [
		{name : 'loanId'				, type: 'int'},
		{name : 'customerIdKey'			, type: 'int'},
		{name : 'creatorId'				, type: 'int'},
		{name : 'createDate'			, type: 'date'},
		{name : 'applicationNo'			, type: 'string'},
		{name : 'idLoanTypeKey'			, type: 'int'},
		{name : 'idCustomerTypeKey'		, type: 'int'},
		{name : 'appliedLoanAmount'		, type: 'number'},
		{name : 'purposeOfLoan'			, type: 'string'},
		{name : 'overLoan'				, type: 'number'},
		{name : 'netMonthlyIncome'		, type: 'number'},
		{name : 'tenorYear'				, type: 'number'},
		{name : 'existingLoanAmount'	, type: 'number'},
		{name : 'interestRate'			, type: 'number'},
		{name : 'totalEMI'				, type: 'number'},
		{name : 'monthlyInstallment'	, type: 'number'},
		{name : 'disposableIncome'		, type: 'number'},
		{name : 'proposeEMIDate'		, type: 'string'},
		{name : 'duplications'			, type: 'string'},
		{name : 'cibGenerationDate'		, type: 'date'},
		{name : 'allowedDBR'			, type: 'number'},
		{name : 'cibStatus'				, type: 'string'},
		{name : 'proposedDBR'			, type: 'number'},
		{name : 'priceQuotationAmount'	, type: 'number'},
		{name : 'bankParticipation'		, type: 'string'},
		{name : 'accountNo'				, type: 'string'},

		{name : 'security'				, type: 'string'},
		{name : 'guarantorElibiblity'	, type: 'string'},
		{name : 'dobOfPg'		        , type: 'date'},
		{name : 'remainingAmtAftEMI'	, type: 'number'},
		{name : 'grossSalaryPerMonth'	, type: 'number'},
		{name : 'dobOfPgYear'	        , type: 'string'},
		{name : 'borrowerParticipation' , type: 'string'},
		{name : 'nameOfGuarantor'       , type: 'string'},
		{name : 'stateDisplayLabel'     , type: 'string'},
		{name : 'loanTrackingId'       	, type: 'string'},
		{name : 'relationshipWithApplicant' , type: 'string'},
		{name : 'relationshipWithPg' 	, type: 'string'},
		{name : 'recommendedForApproval', type: 'number'},
		{name : 'locked'				, type:'number'},
		{name : 'permission'			, type:'number'},
		{name : 'condition' 			, type: 'string'},
		{name : 'gPFAmount'				, type: 'number'},
		{name : 'creatorName'    		, type: 'string'},
		{name : 'dataSource' 			, type: 'string'},
		{name : 'districtDivision'    	, type: 'string'},
		{name : 'mobileOfGuarantor'	    , type: 'string'},


	]
});