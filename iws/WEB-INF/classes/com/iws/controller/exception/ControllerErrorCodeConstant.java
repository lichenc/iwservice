package com.iws.controller.exception;

public class ControllerErrorCodeConstant {
	
	//for All
	public static final String ALL_SUCCESS_CODE = "0000";//Successful operation.
	public static final String ALL_FAIL_CODE = "9999";//Other error.
	
	//for insert
	public static final String INSERT_JSON_FORMAT_ERROR_CODE = "0101";//ItsmOrderInfoBeans JSON format error. 
	public static final String INSERT_LIST_NOT_ALLOW_EMPTY_CODE = "0102";//List not allow empty. 
	public static final String INSERT_ORID_NOT_ALLOW_EMPTY_CODE = "0103";//orId not allow empty.
	public static final String INSERT_UUID_NOT_ALLOW_HASVALUE_CODE = "0104";//uuId not allow have value.
	//for query
	public static final String QUERY_ApiItsmOrderQueryDto_JSON_FORMAT_ERROR_CODE = "0201";//ApiItsmOrderQueryDto JSON format error. 
	public static final String QUERY_itsmOrderSearchParameter_NOT_ALLOW_EMPTY_CODE = "0202";//itsmOrderSearchParameter number not allow empty
	public static final String QUERY_NUMBER_NOT_ALLOW_OVERBORDER_CODE = "0203";//ItsmOrderSearchParameter number must be greater than 0 and less than 10000.
	public static final String QUERY_ATTRIBUTE_NOT_ALLOW_EMPTY_CODE = "0204";//ItsmOrderInfoBean isn't have such attribute.
	public static final String QUERY_NUMBER_NOT_ALLOW_EMPTY_CODE = "0205";//ItsmOrderSearchParameter number or start is null.
	public static final String QUERY_ItsmOrderInfoBeanList_NOT_ALLOW_EMPTY_CODE = "0206";//ItsmOrderInfoBeanList number not allow empty
	
	//for edit
	public static final String EDIT_ItsmOrderSearchParameter_NUMBER_EORRO_CODE = "0301";//ItsmOrderSearchParameter number not right.
//	public static final String EDIT_TIMEFORMAT_ERROR_CODE = "0302";//Time format error.
	public static final String EDIT_ItsmOrderDto_JSON_FORMAT_ERROR_CODE = "0303";//ItsmOrderSearchParameter JSON format error.
//	orid is not null. 
	
	
}
 