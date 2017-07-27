package com.iws.pojo.ws;

import java.util.Map;

import com.iws.pojo.itsmorder.ItsmOrderInfoBean;
import com.iws.pojo.itsmorder.ItsmOrderSearchParameter;

public class ItsmOrderEditDto {
	private ItsmOrderSearchParameter itsmOrderSearchParameter;
	private ItsmOrderInfoBean itsmOrderInfoBean;
	
	private Map<String,String> parameterMap;

	public ItsmOrderSearchParameter getItsmOrderSearchParameter() {
		return itsmOrderSearchParameter;
	}

	public void setItsmOrderSearchParameter(
			ItsmOrderSearchParameter itsmOrderSearchParameter) {
		this.itsmOrderSearchParameter = itsmOrderSearchParameter;
	}

	public ItsmOrderInfoBean getItsmOrderInfoBean() {
		return itsmOrderInfoBean;
	}

	public void setItsmOrderInfoBean(ItsmOrderInfoBean itsmOrderInfoBean) {
		this.itsmOrderInfoBean = itsmOrderInfoBean;
	}

	public Map<String, String> getParameterMap() {
		return parameterMap;
	}

	public void setParameterMap(Map<String, String> parameterMap) {
		this.parameterMap = parameterMap;
	}
	
	


}
