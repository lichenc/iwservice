package com.iws.pojo.ws;

import java.util.List;
import java.util.Map;

import com.iws.pojo.itsmorder.ItsmOrderSearchParameter;

public class ItsmOrderQueryDto {
	private ItsmOrderSearchParameter itsmOrderSearchParameter;
	private List<String> itsmOrderInfoBeanList;
	
	private Map<String,String> parameterMap;
	
	public ItsmOrderSearchParameter getItsmOrderSearchParameter() {
		return itsmOrderSearchParameter;
	}
	public void setItsmOrderSearchParameter(
			ItsmOrderSearchParameter itsmOrderSearchParameter) {
		this.itsmOrderSearchParameter = itsmOrderSearchParameter;
	}
	public List<String> getItsmOrderInfoBeanList() {
		return itsmOrderInfoBeanList;
	}
	public void setItsmOrderInfoBeanList(List<String> itsmOrderInfoBeanList) {
		this.itsmOrderInfoBeanList = itsmOrderInfoBeanList;
	}
	public Map<String, String> getParameterMap() {
		return parameterMap;
	}
	public void setParameterMap(Map<String, String> parameterMap) {
		this.parameterMap = parameterMap;
	}
	


}
