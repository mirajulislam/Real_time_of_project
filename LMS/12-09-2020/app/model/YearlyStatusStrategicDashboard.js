Ext.define('Desktop.model.YearlStatusStrategicDashboard', {
	extend: 'Ext.data.Model',

	requires: [
		'Ext.data.field.Field'
	],

	fields	: [
		{name : 'year', type: 'int'},
		{name : 'monthStr', type: 'string'},
		{name : 'monthInt', type: 'int'},
		{name : 'totalCreate', type: 'int'},
		{name : 'avgCreate', type: 'int'},	
		{
			name : 'montYear',
			type: 'string',
			convert: function( v, record ) {
				var montYear =record.get('monthStr').substr(0,3)  + "'" + record.get('year') % 100; //'\''
				return montYear;
			}
		}
	]
});