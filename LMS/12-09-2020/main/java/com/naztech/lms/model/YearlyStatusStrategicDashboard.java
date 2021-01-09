/**
 * 
 */
package com.naztech.lms.model;

import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author mirajul.islam
 *
 */
public class YearlyStatusStrategicDashboard {
	private Integer year;
	private String  monthStr;
	private Integer monthInt;
	private Integer totalCreate;
	private Integer avgCreate;

	private static Map<String, String> rs2BeanMap = null;
	
	public static Map<String, String> getRs2BeanMap() {
		if (rs2BeanMap == null) {
			rs2BeanMap = new LinkedHashMap<String, String>();
			rs2BeanMap.put("int_year", "year");
			rs2BeanMap.put("tx_month", "monthStr");
			rs2BeanMap.put("int_month", "monthInt");
			rs2BeanMap.put("total_create", "totalCreate");
			rs2BeanMap.put("avg_create", "avgCreate");
		}
		return rs2BeanMap;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getMonthStr() {
		return monthStr;
	}

	public void setMonthStr(String monthStr) {
		this.monthStr = monthStr;
	}

	public Integer getMonthInt() {
		return monthInt;
	}

	public void setMonthInt(Integer monthInt) {
		this.monthInt = monthInt;
	}

	public Integer getAvgCreate() {
		return avgCreate;
	}

	public void setAvgCreate(Integer avgCreate) {
		this.avgCreate = avgCreate;
	}

	public Integer getTotalCreate() {
		return totalCreate;
	}

	public void setTotalCreate(Integer totalCreate) {
		this.totalCreate = totalCreate;
	}

	
}
