package com.iws.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.iws.controller.exception.ControllerErrorCodeConstant;
import com.iws.controller.exception.ControllerException;
import com.iws.pojo.apilog.ApiLogInfoBean;
import com.iws.pojo.itsmorder.ItsmOrderDto;
import com.iws.pojo.itsmorder.ItsmOrderInfoBean;
import com.iws.pojo.itsmorder.ItsmOrderSearchParameter;
import com.iws.pojo.ws.ItsmOrderEditDto;
import com.iws.pojo.ws.ItsmOrderInsertDto;
import com.iws.pojo.ws.ItsmOrderQueryDto;
import com.iws.pojo.ws.ResultPojo;
import com.iws.service.QueryService;
import com.iws.service.UpdateService;
import com.iws.util.BaseUtil;
import com.iws.util.DataGrid;

@Controller
@RequestMapping(value = "/ws")
public class WebServiceController {

	@Autowired
	private QueryService queryService;

	@Autowired
	private UpdateService updateService;

	private static final Logger log = Logger
			.getLogger(WebServiceController.class);

	String logcategory = "api";
	String loglevel = "debug";

	ObjectMapper jsonMapper = new ObjectMapper();
	
	@RequestMapping(value = "/deleteItsmOrderByorId", method = RequestMethod.POST)
	public @ResponseBody
	ResultPojo<String> deleteItsmOrderByorId(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		log.info("start [deleteItsmOrderByorId] method");

		/*
		 * get Json from client.
		 */
		String inJson = IOUtils.toString(request.getInputStream(),"utf-8" );
		//System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"+inJson);
		/*
		 * init api log data.
		 */
		String logmodule = "deleteItsmOrderByorId";
		String actip = "";
		String actmac = "";
		request.setAttribute("logmodule", logmodule);

		System.out.println("inJson=" + inJson);
		/*
		insertAPilog(logcategory, logmodule, ControllerErrorCodeConstant.ALL_SUCCESS_CODE, loglevel, "pass",
				"successful in, json:" + inJson, "", "", actip, actmac);*/
		log.debug("inJson:" + inJson);

		/*
		 * json change to Object.
		 */
		ItsmOrderInsertDto itsmOrderInsertDto = null;
		try {

			// itsmOrderInsertDto = jsonMapper.readValue(
			// inJson,
			// jsonMapper.getTypeFactory().constructParametricType(
			// List.class, ItsmOrderInsertDto.class));
			itsmOrderInsertDto = jsonMapper.readValue(inJson,
					ItsmOrderInsertDto.class);
			/*
			insertAPilog(
					logcategory,
					logmodule,
					ControllerErrorCodeConstant.ALL_SUCCESS_CODE,
					loglevel,
					"pass",
					"successful translate json to object:" + itsmOrderInsertDto,
					"", "", actip, actmac);*/
			log.debug("translated itsmOrderInfoBeans:" + itsmOrderInsertDto);
		} catch (Exception e) {
			/*
			insertAPilog(logcategory, logmodule, ControllerErrorCodeConstant.INSERT_JSON_FORMAT_ERROR_CODE, loglevel, "FAIL",
					"fail to translate json to object:" + inJson, "", "",
					actip, actmac);*/
			log.debug(e.getMessage(), e);
			throw new ControllerException(
					ControllerErrorCodeConstant.INSERT_JSON_FORMAT_ERROR_CODE,
					"");
		}

		Map<String, String> parameterMap = itsmOrderInsertDto.getParameterMap();
		actip = parameterMap.get("ip");
		actmac = parameterMap.get("mac");

		request.setAttribute("actip", actip);
		request.setAttribute("actmac", actmac);

		List<ItsmOrderInfoBean> itsmOrderInfoBeans = itsmOrderInsertDto
				.getItsmOrderList();

		/*
		 * Check list size.
		 */
		/*
		if (itsmOrderInfoBeans.size() <= 0) {
			insertAPilog(logcategory, logmodule, ControllerErrorCodeConstant.INSERT_LIST_NOT_ALLOW_EMPTY_CODE, loglevel, "FAIL",
					"list not allow empty, json:" + inJson, "", "", actip,
					actmac);
			log.debug("list not allow empty,size:" + itsmOrderInfoBeans.size());
			throw new ControllerException(
					ControllerErrorCodeConstant.INSERT_LIST_NOT_ALLOW_EMPTY_CODE,
					"");
		} else {
			insertAPilog(logcategory, logmodule, ControllerErrorCodeConstant.ALL_SUCCESS_CODE, loglevel, "pass",
					"list is not empty. ", "", "", actip, actmac);
			log.debug("list is not empty,size:" + itsmOrderInfoBeans.size());

		}*/

		/*
		 * Check Orid empty.		 * 
		 */
		/*
		for (ItsmOrderInfoBean itsmOrderInfoBean : itsmOrderInfoBeans) {
			String curOrid = itsmOrderInfoBean.getOrId();
			String curuuid = itsmOrderInfoBean.getUuid();
			System.out.println("curuuid=" + curuuid);
			if ( BaseUtil.getInstance().isEmpty(curuuid) ) {
				insertAPilog(logcategory, logmodule, ControllerErrorCodeConstant.ALL_SUCCESS_CODE, loglevel, "pass",
						"uuid  allow empty", "", "", actip, actmac);
				log.debug("uuid  allow empty");

			} else {
				insertAPilog(logcategory, logmodule, ControllerErrorCodeConstant.INSERT_UUID_NOT_ALLOW_HASVALUE_CODE, loglevel, "FAIL",
						"uuid is not allow have value.json " + inJson, "", "",
						actip, actmac);
				log.debug("uuid is not allow have value:" + curOrid);
				throw new ControllerException(
						ControllerErrorCodeConstant.INSERT_UUID_NOT_ALLOW_HASVALUE_CODE,
						"");
			}

			if (BaseUtil.getInstance().isEmpty(curOrid) ) {
				insertAPilog(logcategory, logmodule, ControllerErrorCodeConstant.INSERT_ORID_NOT_ALLOW_EMPTY_CODE, loglevel, "FAIL",
						"orid not allow empty, json:" + inJson, "", "", actip,
						actmac);
				log.debug("orid is not empty,size:" + inJson);
				throw new ControllerException(
						ControllerErrorCodeConstant.INSERT_ORID_NOT_ALLOW_EMPTY_CODE,
						"");
			} else {
				insertAPilog(logcategory, logmodule, ControllerErrorCodeConstant.ALL_SUCCESS_CODE, loglevel, "pass",
						"orid is not empty. ", "", "", actip, actmac);
				log.debug("orid is not empty:" + curOrid);
			}
		}*/

		/*
		 * Do insert.
		 */
		//itsmOrderInfoBeans
		updateService.deleteItsmOrderByorId(itsmOrderInfoBeans);
		/*insertAPilog(logcategory, logmodule, ControllerErrorCodeConstant.ALL_SUCCESS_CODE, loglevel, "pass",
				"Insert successful. ", "", "", actip, actmac);*/
		log.debug("Insert successful. ");

		/*
		 * Return code.
		 */
		ResultPojo<String> curResult = new ResultPojo<String>();
		curResult.setR_code(ControllerErrorCodeConstant.ALL_SUCCESS_CODE);
		log.debug("return object:" + jsonMapper.writeValueAsString(curResult));
		/*
		insertAPilog(
				logcategory,
				logmodule,
				ControllerErrorCodeConstant.ALL_SUCCESS_CODE,
				loglevel,
				"pass",
				"successful out, json:"
						+ jsonMapper.writeValueAsString(curResult), "", "",
				actip, actmac);*/
		log.info("end [deleteItsmOrderByorId] method");

		return curResult;
	}

	@RequestMapping(value = "/insertItsmOrder", method = RequestMethod.POST)
	public @ResponseBody
	ResultPojo<String> insertItemOrder(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		log.info("start [insertItemOrder] method");

		/*
		 * get Json from client.
		 */
		String inJson = IOUtils.toString(request.getInputStream(),"UTF-8");

		/*
		 * init api log data.
		 */
		String logmodule = "insertItsmOrder";
		String actip = "";
		String actmac = "";
		request.setAttribute("logmodule", logmodule);

		System.out.println("inJson=" + inJson);
		insertAPilog(logcategory, logmodule, ControllerErrorCodeConstant.ALL_SUCCESS_CODE, loglevel, "pass",
				"successful in, json:" + inJson, "", "", actip, actmac);
		log.debug("inJson:" + inJson);

		/*
		 * json change to Object.
		 */
		ItsmOrderInsertDto itsmOrderInsertDto = null;
		try {

			// itsmOrderInsertDto = jsonMapper.readValue(
			// inJson,
			// jsonMapper.getTypeFactory().constructParametricType(
			// List.class, ItsmOrderInsertDto.class));
			itsmOrderInsertDto = jsonMapper.readValue(inJson,
					ItsmOrderInsertDto.class);

			insertAPilog(
					logcategory,
					logmodule,
					ControllerErrorCodeConstant.ALL_SUCCESS_CODE,
					loglevel,
					"pass",
					"successful translate json to object:" + itsmOrderInsertDto,
					"", "", actip, actmac);
			log.debug("translated itsmOrderInfoBeans:" + itsmOrderInsertDto);
		} catch (Exception e) {
			insertAPilog(logcategory, logmodule, ControllerErrorCodeConstant.INSERT_JSON_FORMAT_ERROR_CODE, loglevel, "FAIL",
					"fail to translate json to object:" + inJson, "", "",
					actip, actmac);
			log.debug(e.getMessage(), e);
			throw new ControllerException(
					ControllerErrorCodeConstant.INSERT_JSON_FORMAT_ERROR_CODE,
					"");
		}

		Map<String, String> parameterMap = itsmOrderInsertDto.getParameterMap();
		actip = parameterMap.get("ip");
		actmac = parameterMap.get("mac");

		request.setAttribute("actip", actip);
		request.setAttribute("actmac", actmac);

		List<ItsmOrderInfoBean> itsmOrderInfoBeans = itsmOrderInsertDto
				.getItsmOrderList();

		/*
		 * Check list size.
		 */
		if (itsmOrderInfoBeans.size() <= 0) {
			insertAPilog(logcategory, logmodule, ControllerErrorCodeConstant.INSERT_LIST_NOT_ALLOW_EMPTY_CODE, loglevel, "FAIL",
					"list not allow empty, json:" + inJson, "", "", actip,
					actmac);
			log.debug("list not allow empty,size:" + itsmOrderInfoBeans.size());
			throw new ControllerException(
					ControllerErrorCodeConstant.INSERT_LIST_NOT_ALLOW_EMPTY_CODE,
					"");
		} else {
			insertAPilog(logcategory, logmodule, ControllerErrorCodeConstant.ALL_SUCCESS_CODE, loglevel, "pass",
					"list is not empty. ", "", "", actip, actmac);
			log.debug("list is not empty,size:" + itsmOrderInfoBeans.size());

		}

		/*
		 * Check Orid empty.
		 */
		for (ItsmOrderInfoBean itsmOrderInfoBean : itsmOrderInfoBeans) {
			String curOrid = itsmOrderInfoBean.getOrId();
			String curuuid = itsmOrderInfoBean.getUuid();
			System.out.println("curuuid=" + curuuid);
			if ( BaseUtil.getInstance().isEmpty(curuuid) ) {
				insertAPilog(logcategory, logmodule, ControllerErrorCodeConstant.ALL_SUCCESS_CODE, loglevel, "pass",
						"uuid  allow empty", "", "", actip, actmac);
				log.debug("uuid  allow empty");

			} else {
				insertAPilog(logcategory, logmodule, ControllerErrorCodeConstant.INSERT_UUID_NOT_ALLOW_HASVALUE_CODE, loglevel, "FAIL",
						"uuid is not allow have value.json " + inJson, "", "",
						actip, actmac);
				log.debug("uuid is not allow have value:" + curOrid);
				throw new ControllerException(
						ControllerErrorCodeConstant.INSERT_UUID_NOT_ALLOW_HASVALUE_CODE,
						"");
			}

			if (BaseUtil.getInstance().isEmpty(curOrid) ) {
				insertAPilog(logcategory, logmodule, ControllerErrorCodeConstant.INSERT_ORID_NOT_ALLOW_EMPTY_CODE, loglevel, "FAIL",
						"orid not allow empty, json:" + inJson, "", "", actip,
						actmac);
				log.debug("orid is not empty,size:" + inJson);
				throw new ControllerException(
						ControllerErrorCodeConstant.INSERT_ORID_NOT_ALLOW_EMPTY_CODE,
						"");
			} else {
				insertAPilog(logcategory, logmodule, ControllerErrorCodeConstant.ALL_SUCCESS_CODE, loglevel, "pass",
						"orid is not empty. ", "", "", actip, actmac);
				log.debug("orid is not empty:" + curOrid);
			}
		}

		/*
		 * Do insert.
		 */
		updateService.batchInsertItsmOrder(itsmOrderInfoBeans);
		insertAPilog(logcategory, logmodule, ControllerErrorCodeConstant.ALL_SUCCESS_CODE, loglevel, "pass",
				"Insert successful. ", "", "", actip, actmac);
		log.debug("Insert successful. ");

		/*
		 * Return code.
		 */
		ResultPojo<String> curResult = new ResultPojo<String>();
		curResult.setR_code(ControllerErrorCodeConstant.ALL_SUCCESS_CODE);
		log.debug("return object:" + jsonMapper.writeValueAsString(curResult));

		insertAPilog(
				logcategory,
				logmodule,
				ControllerErrorCodeConstant.ALL_SUCCESS_CODE,
				loglevel,
				"pass",
				"successful out, json:"
						+ jsonMapper.writeValueAsString(curResult), "", "",
				actip, actmac);
		log.info("end [insertItemOrder] method");

		return curResult;
	}

	

	
	
	@ResponseBody
	@RequestMapping(value = "/queryItsmOrder", method = RequestMethod.POST)
	public ResultPojo<String> queryItsmOrder(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		log.info("start [queryItsmOrder] method");
		/*
		 * Check number null.
		 */
		/*
		 * init api log data.
		 */

		/*
		 * get Json from client.
		 */
		String inJson = IOUtils.toString(request.getInputStream(),"utf-8");
		System.out.println("inJson" + inJson);

		String logmodule = "queryItsmOrder";
		String actip = "";
		String actmac = "";
		request.setAttribute("logmodule", logmodule);

		insertAPilog(logcategory, logmodule, ControllerErrorCodeConstant.ALL_SUCCESS_CODE, loglevel, "pass",
				"successful in, json:" + inJson, "", "", actip, actmac);
		log.debug("inJson:" + inJson);

		/*
		 * json change to Object.
		 */
		ItsmOrderQueryDto itsmOrderQueryDto = null;
		try {
			itsmOrderQueryDto = jsonMapper.readValue(inJson,
					ItsmOrderQueryDto.class);
			insertAPilog(logcategory, logmodule, ControllerErrorCodeConstant.ALL_SUCCESS_CODE, loglevel, "pass",
					"successful translate json to object:" + itsmOrderQueryDto,
					"", "", actip, actmac);
			log.debug("translated itsmOrderInfoBeans:" + itsmOrderQueryDto);
		} catch (Exception e) {
			insertAPilog(logcategory, logmodule, ControllerErrorCodeConstant.QUERY_ApiItsmOrderQueryDto_JSON_FORMAT_ERROR_CODE, loglevel, "FAIL",
					"fail to translate json to object:" + inJson, "", "",
					actip, actmac);
			log.debug(e.getMessage(), e);
			throw new ControllerException(
					ControllerErrorCodeConstant.QUERY_ApiItsmOrderQueryDto_JSON_FORMAT_ERROR_CODE,
					"");
		}

		Map<String, String> parameterMap = itsmOrderQueryDto.getParameterMap();
		actip = parameterMap.get("ip");
		actmac = parameterMap.get("mac");

		request.setAttribute("actip", actip);
		request.setAttribute("actmac", actmac);

		ItsmOrderSearchParameter itsmOrderSearchParameter = itsmOrderQueryDto
				.getItsmOrderSearchParameter();
		List<String> itsmOrderInfoBeanList = itsmOrderQueryDto
				.getItsmOrderInfoBeanList();

		if ( BaseUtil.getInstance().isEmpty(itsmOrderSearchParameter.getNumber())) {
			insertAPilog(logcategory, logmodule, ControllerErrorCodeConstant.QUERY_NUMBER_NOT_ALLOW_EMPTY_CODE, loglevel, "FAIL",
					"number not allow empty, json:" + inJson, "", "", actip,
					actmac);
			throw new ControllerException(
					ControllerErrorCodeConstant.QUERY_NUMBER_NOT_ALLOW_EMPTY_CODE,
					"");
		} else {
			insertAPilog(logcategory, logmodule, ControllerErrorCodeConstant.ALL_SUCCESS_CODE, loglevel, "pass",
					"number is not empty", "", "", actip, actmac);
		}

		/*
		 * Check number limit scope.
		 */

		if (!(itsmOrderSearchParameter.getNumber() > 0 && itsmOrderSearchParameter
				.getNumber() < 10000)) {
			insertAPilog(logcategory, logmodule, ControllerErrorCodeConstant.QUERY_NUMBER_NOT_ALLOW_OVERBORDER_CODE, loglevel, "FAIL",
					"number not allow beyond, json:" + inJson, "", "", actip,
					actmac);

			throw new ControllerException(
					ControllerErrorCodeConstant.QUERY_NUMBER_NOT_ALLOW_OVERBORDER_CODE,
					"");
		} else {
			insertAPilog(logcategory, logmodule, ControllerErrorCodeConstant.ALL_SUCCESS_CODE, loglevel, "pass",
					"number is not beyond", "", "", actip, actmac);
		}
		/*
		 * Default start to first record.
		 */
		itsmOrderSearchParameter.setStart(1);

		if (log.isDebugEnabled()) {
			log.debug("start:" + itsmOrderSearchParameter.getStart());
		}

		/*
		 * Init Info bean.
		 */
		// ？？
		// if(apiItsmOrderQueryDto.getItsmOrderInfoBeanList().size()<=0)
		// {
		// insertAPilog(logcategory, logmodule, "0206", loglevel, "FAIL",
		// "ItsmOrderInfoBeanList  not allow empty , json:" + inJson, "", "",
		// actip,
		// actmac);
		//
		// throw new ControllerException(
		// ControllerErrorCodeConstant.QUERY_ItsmOrderInfoBeanList_NOT_ALLOW_EMPTY_CODE,
		// "");
		// }
		// else
		// {
		// insertAPilog(logcategory, logmodule, "0000", loglevel, "pass",
		// "ItsmOrderInfoBeanList is not empty:" , "", "", actip, actmac);
		// }
		// List<String> itsmOrderInfoBeanList
		// =apiItsmOrderQueryDto.getItsmOrderInfoBeanList();
		//
		// System.out.println("itsmOrderInfoBeanList.size()="+itsmOrderInfoBeanList.size());

		ItsmOrderInfoBean itsmOrderInfoBean = new ItsmOrderInfoBean();

		initItsmOrderInfoBean(itsmOrderInfoBeanList, itsmOrderInfoBean,
				logmodule, actip, actmac, inJson);

		if (log.isDebugEnabled()) {
			log.debug("itsmOrderInfoBean:");
		}
		log.info("InitItsmOrder:" + "orId" + itsmOrderInfoBean.getOrId()
				+ "orCategory=" + itsmOrderInfoBean.getOrCategory() + ",title="
				+ itsmOrderInfoBean.getTitle() + ",workContent="
				+ itsmOrderInfoBean.getWorkContent() + ",applicant="
				+ itsmOrderInfoBean.getApplicant() + ",applyTime="
				+ itsmOrderInfoBean.getApplyTime() + ",performer="
				+ itsmOrderInfoBean.getPerformer() + ",performerTime"
				+ itsmOrderInfoBean.getPerformTime() + ",isowner="
				+ itsmOrderInfoBean.getIsowner() + ",businessSystem="
				+ itsmOrderInfoBean.getBusinessSystem() + ",performMode="
				+ itsmOrderInfoBean.getPerformMode() + ",annotation="
				+ itsmOrderInfoBean.getAnnotation() + ",dataState="
				+ itsmOrderInfoBean.getDataState() + ",status="
				+ itsmOrderInfoBean.getStatus() + ",creator="
				+ itsmOrderInfoBean.getCreator() + ",createTime="
				+ itsmOrderInfoBean.getCreateTime() + ",modifier="
				+ itsmOrderInfoBean.getModifier() + ",modifyTime="
				+ itsmOrderInfoBean.getModifyTime() + ",att1="
				+ itsmOrderInfoBean.getAtt1() + ",att2="
				+ itsmOrderInfoBean.getAtt2() + ",att3="
				+ itsmOrderInfoBean.getAtt3());
		/*
		 * Init query dto.
		 */
		// ？？需要判断
		ItsmOrderDto itsmOrderDto = new ItsmOrderDto();
		itsmOrderDto.setItsmOrderSearchParameter(itsmOrderSearchParameter);
		itsmOrderDto.setItsmOrderInfoBean(itsmOrderInfoBean);

		/*
		 * Do query.
		 */
		DataGrid<ItsmOrderInfoBean> dataGrid = queryService
				.queryItsmOrderByParameter(itsmOrderDto);
		insertAPilog(logcategory, logmodule, ControllerErrorCodeConstant.ALL_SUCCESS_CODE, loglevel, "pass",
				"Query successful. ", "", "", actip, actmac);
		/* log.debug("Query successful. "); */

		if (log.isDebugEnabled()) {
			log.debug("itsmOrderList.size:" + dataGrid.getRows().size());
		}

//		ItsmOrderInfoBean itsmOrderInfoBean2 = dataGrid.getRows().get(0);
//		System.out.println("itsmOrderInfoBean2.getOrId()="
//				+ itsmOrderInfoBean2.getOrId());
//		log.info("queryItsmOrder:" + "uuid" + itsmOrderInfoBean2.getUuid()
//				+ "orId" + itsmOrderInfoBean2.getOrId() + "orCategory="
//				+ itsmOrderInfoBean2.getOrCategory() + ",title="
//				+ itsmOrderInfoBean2.getTitle() + ",workContent="
//				+ itsmOrderInfoBean2.getWorkContent() + ",applicant="
//				+ itsmOrderInfoBean2.getApplicant() + ",applyTime="
//				+ itsmOrderInfoBean2.getApplyTime() + ",performer="
//				+ itsmOrderInfoBean2.getPerformer() + ",performerTime"
//				+ itsmOrderInfoBean2.getPerformTime() + ",isowner="
//				+ itsmOrderInfoBean2.getIsowner() + ",businessSystem="
//				+ itsmOrderInfoBean2.getBusinessSystem() + ",performMode="
//				+ itsmOrderInfoBean2.getPerformMode() + ",annotation="
//				+ itsmOrderInfoBean2.getAnnotation() + ",dataState="
//				+ itsmOrderInfoBean2.getDataState() + ",status="
//				+ itsmOrderInfoBean2.getStatus() + ",creator="
//				+ itsmOrderInfoBean2.getCreator() + ",createTime="
//				+ itsmOrderInfoBean2.getCreateTime() + ",modifier="
//				+ itsmOrderInfoBean2.getModifier() + ",modifyTime="
//				+ itsmOrderInfoBean2.getModifyTime() + ",att1="
//				+ itsmOrderInfoBean2.getAtt1() + ",att2="
//				+ itsmOrderInfoBean2.getAtt2() + ",att3="
//				+ itsmOrderInfoBean2.getAtt3());
		/*
		 * Remove number & start.
		 */
		// List<ItsmOrderInfoBean> itsmOrderInfoBeans = new
		// ArrayList<ItsmOrderInfoBean>();
		// for (ItsmOrderInfoBean its : dataGrid.getRows()) {
		//
		// // JSONObject jsonObject = JSON.parseObject(JSON.toJSON(its)
		// // .toString());
		// // jsonObject.remove("number");
		// // jsonObject.remove("start");
		// // ItsmOrderInfoBean ib = JSON.parseObject(jsonObject.toString(),
		// // ItsmOrderInfoBean.class);
		// // itsmOrderInfoBeans.add(ib);
		// }

		/*
		 * Return result.
		 */
		ResultPojo<String> curResult = new ResultPojo<String>();
		curResult.setR_code(ControllerErrorCodeConstant.ALL_SUCCESS_CODE);
		insertAPilog(
				logcategory,
				logmodule,
				ControllerErrorCodeConstant.ALL_SUCCESS_CODE,
				loglevel,
				"pass",
				"successful out, json:"
						+ jsonMapper.writeValueAsString(curResult), "", "",
				actip, actmac);
		curResult
				.setR_result(jsonMapper.writeValueAsString(dataGrid.getRows()));
		if (log.isDebugEnabled()) {
			log.debug("code:" + curResult.getR_code());
			log.debug("result:" + curResult.getR_result());
		}

		log.info("end [queryItsmOrder] method");
		return curResult;
	}

	@RequestMapping(value = "/editItsmOrder", method = RequestMethod.POST)
	public @ResponseBody
	ResultPojo<String> editItsmOrder(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		log.info("start [editStatusItsmOrder] method");

		/*
		 * get Json from client.
		 */
		String inJson = IOUtils.toString(request.getInputStream());

		/*
		 * init api log data.
		 */

		String logmodule = "editItsmOrder";
		String actip = "";
		String actmac = "";
		request.setAttribute("logmodule", logmodule);

		insertAPilog(logcategory, logmodule, ControllerErrorCodeConstant.ALL_SUCCESS_CODE, loglevel, "pass",
				"successful in, json:" + inJson, "", "", actip, actmac);
		log.debug("inJson:" + inJson);

		/*
		 * json change to Object.
		 */
		ItsmOrderEditDto itsmOrderEditDto = null;
		try {
			itsmOrderEditDto = jsonMapper.readValue(inJson,
					ItsmOrderEditDto.class);
			insertAPilog(logcategory, logmodule, ControllerErrorCodeConstant.ALL_SUCCESS_CODE, loglevel, "pass",
					"successful translate json to object:" + itsmOrderEditDto,
					"", "", actip, actmac);
			log.debug("translated itsmOrderInfoBeans:" + itsmOrderEditDto);
		} catch (Exception e) {
			insertAPilog(logcategory, logmodule,ControllerErrorCodeConstant.EDIT_ItsmOrderDto_JSON_FORMAT_ERROR_CODE, loglevel, "FAIL",
					"fail to translate json to object:" + inJson, "", "",
					actip, actmac);
			log.debug(e.getMessage(), e);
			throw new ControllerException(
					ControllerErrorCodeConstant.EDIT_ItsmOrderDto_JSON_FORMAT_ERROR_CODE,
					"");
		}

		Map<String, String> parameterMap = itsmOrderEditDto.getParameterMap();
		actip = parameterMap.get("ip");
		actmac = parameterMap.get("mac");

		request.setAttribute("actip", actip);
		request.setAttribute("actmac", actmac);

		ItsmOrderInfoBean itsmOrderInfoBean = itsmOrderEditDto
				.getItsmOrderInfoBean();
		ItsmOrderSearchParameter itsmOrderSearchParameter = itsmOrderEditDto
				.getItsmOrderSearchParameter();

		// /*
		// * Check info bean empty.
		// */
		// if (isObjectGetAttrNull(itsmOrderInfoBean)) {
		// insertAPilog(logcategory, logmodule, "0302", loglevel, "FAIL",
		// "ItsmOrderInfoBean  not allow empty , json:" + inJson, "",
		// "", actip, actmac);
		//
		// throw new ControllerException(
		// ControllerErrorCodeConstant.EDIT_ItsmOrderInfoBean_NOT_ALLOW_EMPTY_CODE,
		// "");
		// } else {
		// insertAPilog(
		// logcategory,
		// logmodule,
		// "0302",
		// loglevel,
		// "pass",
		// "ItsmOrderInfoBean is not  empty:"
		// + itsmOrderDto.getItsmOrderInfoBean(), "", "",
		// actip, actmac);
		// }

		ItsmOrderDto itsmOrderDto = new ItsmOrderDto();
		itsmOrderDto.setItsmOrderSearchParameter(itsmOrderSearchParameter);
		itsmOrderDto.setItsmOrderInfoBean(itsmOrderInfoBean);

		/*
		 * Do modify.
		 */
		updateService.batchModifyItsmOrder(itsmOrderDto);
		insertAPilog(logcategory, logmodule, ControllerErrorCodeConstant.ALL_SUCCESS_CODE, loglevel, "pass",
				"update successful:" + itsmOrderDto.getItsmOrderInfoBean(), "",
				"", actip, actmac);
		// ItsmOrderInfoBean itsmOrderInfoBean = itsmOrderDto
		// .getItsmOrderInfoBean();
		log.info("modifyItsmOrder:" + "uuid" + itsmOrderInfoBean.getUuid()
				+ "orId" + itsmOrderInfoBean.getOrId() + "orCategory="
				+ itsmOrderInfoBean.getOrCategory() + ",title="
				+ itsmOrderInfoBean.getTitle() + ",workContent="
				+ itsmOrderInfoBean.getWorkContent() + ",applicant="
				+ itsmOrderInfoBean.getApplicant() + ",applyTime="
				+ itsmOrderInfoBean.getApplyTime() + ",performer="
				+ itsmOrderInfoBean.getPerformer() + ",performerTime"
				+ itsmOrderInfoBean.getPerformTime() + ",isowner="
				+ itsmOrderInfoBean.getIsowner() + ",businessSystem="
				+ itsmOrderInfoBean.getBusinessSystem() + ",performMode="
				+ itsmOrderInfoBean.getPerformMode() + ",annotation="
				+ itsmOrderInfoBean.getAnnotation() + ",dataState="
				+ itsmOrderInfoBean.getDataState() + ",status="
				+ itsmOrderInfoBean.getStatus() + ",creator="
				+ itsmOrderInfoBean.getCreator() + ",createTime="
				+ itsmOrderInfoBean.getCreateTime() + ",modifier="
				+ itsmOrderInfoBean.getModifier() + ",modifyTime="
				+ itsmOrderInfoBean.getModifyTime() + ",att1="
				+ itsmOrderInfoBean.getAtt1() + ",att2="
				+ itsmOrderInfoBean.getAtt2() + ",att3="
				+ itsmOrderInfoBean.getAtt3());

		// Get return counts.
		int effectCounts = 0;

		/*
		 * Return result.
		 */
		ResultPojo<String> curResult = new ResultPojo<String>();
		curResult.setR_code(ControllerErrorCodeConstant.ALL_SUCCESS_CODE);
		insertAPilog(
				logcategory,
				logmodule,
				ControllerErrorCodeConstant.ALL_SUCCESS_CODE,
				loglevel,
				"pass",
				"successful out, json:"
						+ jsonMapper.writeValueAsString(curResult), "", "",
				actip, actmac);
		curResult.setR_result(effectCounts + "");

		if (log.isDebugEnabled()) {
			log.debug("code:" + curResult.getR_code());
			log.debug("result:" + curResult.getR_result());
		}

		log.info("end [editStatusItsmOrder] method");
		return curResult;
	}
	
	@RequestMapping(value = "/initItsmOrder", method = RequestMethod.POST)
	public @ResponseBody
	ResultPojo<String> initItsmOrder(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		log.info("start [initItsmOrder] method");

		/*
		 * get Json from client.
		 */
		String inJson = IOUtils.toString(request.getInputStream());

		/*
		 * init api log data.
		 */
		String logmodule = "initItsmOrder";
		String actip = "";
		String actmac = "";
		request.setAttribute("logmodule", logmodule);

		System.out.println("inJson=" + inJson);
		insertAPilog(logcategory, logmodule, ControllerErrorCodeConstant.ALL_SUCCESS_CODE, loglevel, "pass",
				"successful in, json:" + inJson, "", "", actip, actmac);
		log.debug("inJson:" + inJson);

		//Do here

		/*
		 * Return code.
		 */
		ResultPojo<String> curResult = new ResultPojo<String>();
		curResult.setR_code(ControllerErrorCodeConstant.ALL_SUCCESS_CODE);
		log.debug("return object:" + jsonMapper.writeValueAsString(curResult));

		insertAPilog(
				logcategory,
				logmodule,
				ControllerErrorCodeConstant.ALL_SUCCESS_CODE,
				loglevel,
				"pass",
				"successful out, json:"
						+ jsonMapper.writeValueAsString(curResult), "", "",
				actip, actmac);
		log.info("end [initItsmOrder] method");

		return curResult;
	}

	private void initItsmOrderInfoBean(List<String> itsmOrderInfoBeanList,
			ItsmOrderInfoBean itsmOrderInfoBean, String logmodule,
			String actip, String actmac, String inJson) throws Exception {

		if (itsmOrderInfoBeanList.size() > 0) {
			Field[] field = ItsmOrderInfoBean.class.getDeclaredFields();
			int checkAttr = field.length;
			for (String key : itsmOrderInfoBeanList) {
				int thischeck = 0;
				for (Field att : field) {

					if (log.isDebugEnabled()) {
						log.debug("key:" + att);
					}

					if (att.getName().equalsIgnoreCase(key)) {
						String first = att.getName().substring(0, 1)
								.toUpperCase();
						String rest = att.getName().substring(1,
								att.getName().length());
						String attMethodName = new StringBuffer(first).append(
								rest).toString();
						String setMethodName = "set" + attMethodName;
						String getMethodName = "get" + attMethodName;
						String param = "";
						// 表示执行itsmOrderInfoBean里面的setMethod方法，参数值为param
						itsmOrderInfoBean.getClass()
								.getMethod(setMethodName, param.getClass())
								.invoke(itsmOrderInfoBean, param);
						Object obj = itsmOrderInfoBean.getClass()
								.getMethod(getMethodName, null)
								.invoke(itsmOrderInfoBean, null);
						System.out.println("set" + attMethodName + ":"
								+ obj.toString());
						System.out.println("get" + attMethodName + ":"
								+ obj.toString());
					} else {
						thischeck++;
					}
				}

				if (log.isDebugEnabled()) {
					log.debug("thischeck:" + thischeck);
				}

				if (thischeck >= checkAttr || key.equalsIgnoreCase("uuid")) {
					insertAPilog(logcategory, logmodule, ControllerErrorCodeConstant.QUERY_ATTRIBUTE_NOT_ALLOW_EMPTY_CODE, loglevel,
							"FAIL",
							"ItsmOrderInfoBean not allow have such attribute, json:"
									+ inJson, "", "", actip, actmac);
					throw new ControllerException(
							ControllerErrorCodeConstant.QUERY_ATTRIBUTE_NOT_ALLOW_EMPTY_CODE,
							"");
				} else {
					insertAPilog(logcategory, logmodule, ControllerErrorCodeConstant.ALL_SUCCESS_CODE, loglevel,
							"pass",
							"ItsmOrderInfoBean  not  have such attribute:"
									+ itsmOrderInfoBean, "", "", actip, actmac);

				}
			}
		}
	}

//	public static boolean isObjectGetAttrNull(Object obj) throws Exception {
//		Field[] field = obj.getClass().getDeclaredFields();
//		int check = 0;
//		for (Field key : field) {
//
//			if (log.isDebugEnabled()) {
//				log.debug("key:" + key);
//			}
//
//			String first = key.getName().substring(0, 1).toUpperCase();
//			String rest = key.getName().substring(1, key.getName().length());
//			String getMethodName = "get"
//					+ new StringBuffer(first).append(rest).toString();
//			System.out.println("methodName:" + getMethodName);
//			Object invokeParameter = obj.getClass()
//					.getMethod(getMethodName, null).invoke(obj, null);
//			if (invokeParameter == null || "".equals(invokeParameter)) {
//				check++;
//			}
//		}
//		if (check >= field.length) {
//			return true;
//		}
//		if (log.isDebugEnabled()) {
//			log.debug("checknum:" + check);
//		}
//		return false;
//	}

	@ExceptionHandler
	public @ResponseBody
	ResultPojo<String> handleException(HttpServletRequest request,
			HttpServletResponse response, Exception e) {
		ResultPojo<String> curResult = new ResultPojo<String>();

		String logmodule = (String) request.getAttribute("logmodule");
		String actip = (String) request.getAttribute("actip");
		String actmac = (String) request.getAttribute("actmac");

		if (e instanceof ControllerException) {
			curResult.setR_code(((ControllerException) e).getControllerErrorCode());
		} else {
			curResult.setR_code(ControllerErrorCodeConstant.ALL_FAIL_CODE);
			insertAPilog(logcategory, logmodule, ControllerErrorCodeConstant.ALL_FAIL_CODE, loglevel, "FAIL",
					"other error. ", "", e.getMessage(), actip, actmac);
		}

//		ModelAndView mv = new ModelAndView();
//		// 设置状态码,注意这里不能设置成500，设成500JQuery不会出错误提示
//		// 并且不会有任何反应
//		response.setStatus(HttpStatus.OK.value());
//		// 设置ContentType
//		// APPLICATION_JSON_VALUE
//		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//		// 避免乱码
//		response.setCharacterEncoding("UTF-8");
//		response.setHeader("Cache-Control", "no-cache, must-revalidate");
//		try {
//			e.printStackTrace();
//			PrintWriter writer = response.getWriter();
//			writer.write(JSON.toJSONString(result, false));
//			writer.close();
//		} catch (IOException ee) {
//			ee.printStackTrace();
//
//		}
//		return mv;
		
		return curResult;
	}

	private void insertAPilog(String logcategory, String logmodule,
			String logcode, String loglevel, String logtitle,
			String logcontent, String extlogcode, String extlogcontent,
			String actip, String actmac) {

		// Timestamp createTime = new Timestamp(System.currentTimeMillis());
		// itsmOrderInfoBean.setCreateTime(TimeUtil.getInstance().timestampToString(curTime));

		ApiLogInfoBean apiLogInfoBean = new ApiLogInfoBean();

		apiLogInfoBean.setLogcategory(logcategory);
		apiLogInfoBean.setLogmodule(logmodule);
		apiLogInfoBean.setLogcode(logcode);
		apiLogInfoBean.setLoglevel(loglevel);
		apiLogInfoBean.setLogtitle(logtitle);
		apiLogInfoBean.setLogcontent(logcontent);
		apiLogInfoBean.setExtlogcode(extlogcode);
		apiLogInfoBean.setExtlogcontent(extlogcontent);
		apiLogInfoBean.setActip(actip);
		apiLogInfoBean.setActmac(actmac);
		// apiLogInfoBean.setCreateTime(TimeUtil.getInstance().timestampToString(createTime));

		updateService.insertApiLog(apiLogInfoBean);

	}
}
