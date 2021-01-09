package com.naztech.lms.model;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Md. Kamruzzaman
 * @since 2019-12-29
 */
public class NConfiguration extends BaseModel {

	Integer configurationId;
	Integer configurationVer;

	Integer readOnly;
	String group;
	String subGroup;
	String name;
	String value1;
	String value2;
	String value3;
	String desc;
	String comment;
	String valueType;
	String modifiedBy;

	List<Integer> configList;

	private static Map<String, String> sql2BeanMap = null;
	private static Map<String, String> rs2BeanMap = null;

	public static Map<String, String> getSql2BeanMap() {
		if (sql2BeanMap == null) {
			sql2BeanMap = new LinkedHashMap<String, String>();
			// Follow this pattern all over the application
			sql2BeanMap.putAll(getSql2BaseBeanMap());

			sql2BeanMap.put("@id_configuration_key", "configurationId");
			sql2BeanMap.put("@id_configuration_ver", "configurationVer");

			sql2BeanMap.put("@tx_group", "group");
			sql2BeanMap.put("@tx_sub_group", "subGroup");
			sql2BeanMap.put("@tx_name", "name");
			sql2BeanMap.put("@tx_value1", "value1");
			sql2BeanMap.put("@tx_value2", "value2");
			sql2BeanMap.put("@tx_value3", "value3");
			sql2BeanMap.put("@tx_desc", "desc");
			sql2BeanMap.put("@tx_comment", "comment");
			sql2BeanMap.put("@tx_value_type", "valueType");
			sql2BeanMap.put("@int_read_only", "readOnly");
			sql2BeanMap.put("@tx_modified_by", "modifiedBy");
		}
		return sql2BeanMap;
	}

	public static Map<String, String> getRs2BeanMap() {
		if (rs2BeanMap == null) {
			rs2BeanMap = new LinkedHashMap<String, String>();

			rs2BeanMap.putAll(getRs2BaseBeanMap());

			rs2BeanMap.put("id_configuration_key", "configurationId");
			rs2BeanMap.put("id_configuration_ver", "configurationVer");

			rs2BeanMap.put("tx_group", "group");
			rs2BeanMap.put("tx_sub_group", "subGroup");
			rs2BeanMap.put("tx_name", "name");
			rs2BeanMap.put("tx_value1", "value1");
			rs2BeanMap.put("tx_value2", "value2");
			rs2BeanMap.put("tx_value3", "value3");
			rs2BeanMap.put("tx_desc", "desc");
			rs2BeanMap.put("tx_comment", "comment");
			rs2BeanMap.put("tx_value_type", "valueType");
			rs2BeanMap.put("int_read_only", "readOnly");
			rs2BeanMap.put("tx_modified_by", "modifiedBy");

		}
		return rs2BeanMap;
	}

	public Integer getConfigurationId() {
		return configurationId;
	}

	public void setConfigurationId(Integer configurationId) {
		this.configurationId = configurationId;
	}

	public Integer getConfigurationVer() {
		return configurationVer;
	}

	public void setConfigurationVer(Integer configurationVer) {
		this.configurationVer = configurationVer;
	}

	public Integer getReadOnly() {
		return readOnly;
	}

	public void setReadOnly(Integer readOnly) {
		this.readOnly = readOnly;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getSubGroup() {
		return subGroup;
	}

	public void setSubGroup(String subGroup) {
		this.subGroup = subGroup;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue1() {
		return value1;
	}

	public void setValue1(String value1) {
		this.value1 = value1;
	}

	public String getValue2() {
		return value2;
	}

	public void setValue2(String value2) {
		this.value2 = value2;
	}

	public String getValue3() {
		return value3;
	}

	public void setValue3(String value3) {
		this.value3 = value3;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getValueType() {
		return valueType;
	}

	public void setValueType(String valueType) {
		this.valueType = valueType;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public List<Integer> getConfigList() {
		return configList;
	}

	public void setConfigList(List<Integer> configList) {
		this.configList = configList;
	}

}
