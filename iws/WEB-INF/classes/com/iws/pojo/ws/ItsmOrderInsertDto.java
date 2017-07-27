package com.iws.pojo.ws;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.iws.pojo.itsmorder.ItsmOrderInfoBean;

public class ItsmOrderInsertDto {
	private List<ItsmOrderInfoBean> itsmOrderList=new ArrayList<ItsmOrderInfoBean>();
	private Map<String,String> parameterMap=new HashMap<String,String>();
	public List<ItsmOrderInfoBean> getItsmOrderList() {
		return itsmOrderList;
	}
	public void setItsmOrderList(List<ItsmOrderInfoBean> itsmOrderList) {
		this.itsmOrderList = itsmOrderList;
	}
	public Map<String, String> getParameterMap() {
		return parameterMap;
	}
	public void setParameterMap(Map<String, String> parameterMap) {
		this.parameterMap = parameterMap;
	}


}
