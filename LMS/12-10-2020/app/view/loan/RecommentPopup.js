Ext.define('Desktop.view.loan.RecommentPopup', {
	extend : 'Ext.panel.Panel',
	title : "Comment",
	requires: [
	    'Desktop.view.loan.LoanController',
		'Ext.tab.Panel',
		'Ext.tab.Tab',
		'Ext.grid.Panel',
		'Ext.grid.column.Boolean',
		'Ext.toolbar.Toolbar',
		'Ext.form.field.Text',
		'Ext.tree.Panel',
		'Ext.tree.View',
		'Ext.grid.filters.filter.Number',
		'Ext.grid.column.Date',
		'Ext.grid.filters.filter.Date',
		'Ext.grid.filters.filter.String',
		'Ext.grid.filters.filter.Boolean',
		'Ext.selection.CheckboxModel',
		'Ext.grid.filters.Filters',
		'Ext.grid.feature.Summary',
	],

	controller: 'loanPanel',

	closable : true,
	floatable : true,
	floating : true,
	draggable : true,
	width : 520,
	height :200,
	modal : true,

	itemId: 'loanActionComment',

	items : [
	{
		xtype : "panel",
		name : 'CommentAdd',
		itemId : 'commentPanelId',
		reference: 'commentPanelRef',
		width : 490,
		border:false,
		items:[
			{
				xtype: 'form',
				border: false,
				width: 490,
				layout: {
					type: 'vbox',
					align : 'stretch',
					pack  : 'start'
				},

				margin : '15 15 15 15',
				items:[{
					border:false,
					defaultType: 'textfield',
					items: [ 
						{
		                    xtype : 'textareafield',
		                    grow : true,		           
		                    emptyText: 'Please write your comment here',
		                    reference: 'newComment',
		                    anchor : '100%',
		                    width: 450,
		                    height: 90,
		                    margin: '10 20 10 20'
		                },
		                {
		                    xtype: 'displayfield',
		                    reference: 'loginUser',
		                    hidden: true
		                },
		                {
		                    xtype: 'displayfield',
		                    reference: 'userId',
		                    hidden: true
		                },
		                {
		                    xtype: 'displayfield',
		                    reference: 'firstName',
		                    hidden: true
		                },
		                {
		                    xtype: 'displayfield',
		                    reference: 'lastName',
		                    hidden: true
		                },
		                {
		                    xtype: 'displayfield',
		                    reference: 'loginName',
		                    hidden: true
		                },
		                {
		                    xtype: 'displayfield',
		                    reference: 'legalEntityKey',
		                    hidden: true
		                },
		                {
		                    xtype: 'displayfield',
		                    reference: 'primaryGroupId',
		                    hidden: true
		                },
		                {
		                    xtype: 'displayfield',
		                    reference: 'userModKey',
		                    hidden: true
		                },
		                {
		                    xtype: 'displayfield',
		                    reference: 'roleName',
		                    hidden: true
		                },
		                {
		                    xtype: 'displayfield',
		                    reference: 'roleId',
		                    hidden: true
		                },
		                {
		                    xtype: 'displayfield',
		                    reference: 'commentType',
		                    hidden: true
		                },
		                {
		                    xtype: 'displayfield',
		                    reference: 'loanId',
		                    hidden: true
		                },
		                {
		                    xtype: 'displayfield',
		                    reference: 'actionType',
		                    hidden: true
		                },
		                {
		                    xtype: 'displayfield',
		                    reference: 'stateName',
		                    hidden: true
		                },
		                {
		                    xtype: 'displayfield',
		                    reference: 'stateId',
		                    hidden: true
		                },
		                {
		                    xtype: 'displayfield',
		                    reference: 'uiActionName',
		                    hidden: true
		                },
		                {
		                    xtype: 'displayfield',
		                    reference: 'loanDetailsPanel',
		                    hidden: true
		                }
					]
				}]
			}
		]
	}],
	dockedItems: [{
		xtype: 'toolbar',
		dock: 'bottom',
		itemId:'btnPanel',
		items : ['->',
			{
				itemId: 'btnCommentCancel',
				text: 'Close',
				reference : 'btnCommentCancel',
				iconCls: 'remove',
				width : 80,
				name: 'btnCancel',
				handler:'onCancelCommentTypePopup'
				
			},
			{
				itemId  : 'btnCommentAdd',
				reference : 'btnCommentAdd',
				text    : 'Yes',
				name    : 'btnWLAdd',
				width : 80,
				hidden  :  false,
				listeners : {
				   click : 'onClickSaveLoanActionComment'
				}
			},
		]
	}],

});