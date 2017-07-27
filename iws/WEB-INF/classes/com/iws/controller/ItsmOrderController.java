package com.iws.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.iws.pojo.itsmorder.ItsmOrderDto;
import com.iws.pojo.itsmorder.ItsmOrderInfoBean;
import com.iws.pojo.itsmorder.ItsmOrderSearchParameter;
import com.iws.service.DataTransportService;
import com.iws.service.QueryService;
import com.iws.service.UpdateService;
import com.iws.util.BaseUtil;
import com.iws.util.DataGrid;
import com.iws.util.TimeUtil;

@Controller
@RequestMapping(value = "/itsmorder")
public class ItsmOrderController {

	@Autowired
	private QueryService queryService;

	@Autowired
	private UpdateService updateService;

	private static final Logger log = Logger
			.getLogger(ItsmOrderController.class);

	@RequestMapping(value = "/fowardQueryItsmOrder")
	public ModelAndView fowardQueryItsmOrder(HttpServletRequest request,
			HttpServletResponse response) {
		log.info("start [fowardQueryItsmOrder] method.");

		String foward = "admin/itsmorder/ItsmOrder";

		log.info("end [fowardQueryItsmOrder] method.");
		return new ModelAndView(foward);
	}

	/*
     * 
     * 
     * */
	@RequestMapping(value = "/fowardItsmCreate")
	public ModelAndView fowardItsmCreate(HttpServletRequest request,
			HttpServletResponse response) {
		log.info("start [fowardQueryItsmOrder] method.");

		String foward = "admin/itsmorder/FowardItsmCreate";

		log.info("end [fowardQueryItsmOrder] method.");
		return new ModelAndView(foward);
	}

	@RequestMapping(value="/queryItsmOrderId")
    @ResponseBody
    public String queryItsmOrderIdDo()
    {
    	log.info("start [queryItsmOrderIdDo] method");
    	/*
    	 *Get ItsmOrderId from ItsmOrder 
    	 **/
    	
    	List<String> orIds = queryService.queryOrId();
    	String orId ;
    	StringBuffer sb = new StringBuffer();
    	for(int i = 0;i < orIds.size();i++)
    	{
    		if(orIds.get(i) == null || orIds.get(i) == "" )
    		{
    			continue;
    		}else if(i != orIds.size() - 1){
    			sb.append(orIds.get(i));
    			sb.append(",");
    		}else{
    			sb.append(orIds.get(i));
    		}
    	}
    	orId = sb.toString();
    	log.info("end [queryItsmOrderIdDo] method");
    	return orId;
    }
	
	@RequestMapping(value = "/queryItsmOrderDo")
	@ResponseBody
	public DataGrid<ItsmOrderInfoBean> queryItsmOrderDo(
			HttpServletRequest request, HttpServletResponse response,
			ItsmOrderSearchParameter itsmOrderSearchParameter) {
		log.info("start [queryItsmOrderDo] method.");

		/*
		 * Get easyui table page info.
		 */
		int pageint = ServletRequestUtils.getIntParameter(request, "page", 1);// ��ǰҳ
		int rowsint = ServletRequestUtils.getIntParameter(request, "rows", 10);// ����
		int start = ((pageint - 1) * rowsint) + 1;
		itsmOrderSearchParameter.setStart(start);
		itsmOrderSearchParameter.setNumber(rowsint);

		if (log.isDebugEnabled()) {
			log.debug("pageint:" + pageint);
			log.debug("rowsint:" + rowsint);
			log.debug("start:" + start);
		}

		if (log.isDebugEnabled()) {
			log.debug("sort:" + itsmOrderSearchParameter.getSort());
			log.debug("order:" + itsmOrderSearchParameter.getOrder());
		}

		/*
		 * Generate return bean.
		 */
		ItsmOrderInfoBean curItsmOrderInfoBean = new ItsmOrderInfoBean();
		curItsmOrderInfoBean.setUuid("");
		curItsmOrderInfoBean.setOrId("");
		curItsmOrderInfoBean.setOrCategory("");
		curItsmOrderInfoBean.setTitle("");
		curItsmOrderInfoBean.setWorkContent("");
		curItsmOrderInfoBean.setApplicant("");
		curItsmOrderInfoBean.setApplyTime("");
		curItsmOrderInfoBean.setPerformer("");
		curItsmOrderInfoBean.setPerformTime("");
		curItsmOrderInfoBean.setIsowner("");
		curItsmOrderInfoBean.setBusinessSystem("");
		curItsmOrderInfoBean.setPerformMode("");
		curItsmOrderInfoBean.setAnnotation("");
		curItsmOrderInfoBean.setDataState("");

		/*
		 * Collect dto.
		 */
		ItsmOrderDto curItsmOrderDto = new ItsmOrderDto();
		curItsmOrderDto.setItsmOrderSearchParameter(itsmOrderSearchParameter);
		curItsmOrderDto.setItsmOrderInfoBean(curItsmOrderInfoBean);

		log.info("queryItsmOrder:" + "uuid" + curItsmOrderInfoBean.getUuid()
				+ ",orId=" + curItsmOrderInfoBean.getOrId() + ",orCategory="
				+ curItsmOrderInfoBean.getOrCategory() + ",title="
				+ curItsmOrderInfoBean.getTitle() + ",workContent="
				+ curItsmOrderInfoBean.getWorkContent() + ",applicant="
				+ curItsmOrderInfoBean.getApplicant() + ",applyTimeBefore="
				+ itsmOrderSearchParameter.getApplyTimeBefore()
				+ ",applyTimeAfter="
				+ itsmOrderSearchParameter.getApplyTimeAfter() + ",performer="
				+ curItsmOrderInfoBean.getPerformer() + ",performTimeBefore="
				+ itsmOrderSearchParameter.getPerformTimeBefore()
				+ ",performTimeAfter="
				+ itsmOrderSearchParameter.getPerformTimeAfter() + ",isowner="
				+ curItsmOrderInfoBean.getIsowner() + ",businessSystem="
				+ curItsmOrderInfoBean.getBusinessSystem() + ",performMode="
				+ curItsmOrderInfoBean.getPerformMode() + ",annotation="
				+ curItsmOrderInfoBean.getAnnotation() + ",dataState="
				+ curItsmOrderInfoBean.getDataState() + ",status="
				+ curItsmOrderInfoBean.getStatus() + ",creator="
				+ curItsmOrderInfoBean.getCreator() + ",createTime"
				+ curItsmOrderInfoBean.getCreateTime() + ",modifier="
				+ curItsmOrderInfoBean.getModifier() + ",modifyTime="
				+ curItsmOrderInfoBean.getModifyTime() + ",att1="
				+ curItsmOrderInfoBean.getAtt1() + ",att2="
				+ curItsmOrderInfoBean.getAtt2() + ",att3="
				+ curItsmOrderInfoBean.getAtt3());

		/*
		 * Do query.
		 */
		DataGrid<ItsmOrderInfoBean> returnDataGrid = queryService
				.queryItsmOrderByParameter(curItsmOrderDto);

		if (log.isDebugEnabled()) {
			log.debug("itsmOrderList.size:" + returnDataGrid.getRows().size());
		}
		// if(returnDataGrid.getRows().size() > 0)
		// {
		// for(int i = 0 ; i < returnDataGrid.getRows().size();i++)
		// {
		// log.info("queryItsmOrder:" +" uuid"
		// + returnDataGrid.getRows().get(i).getUuid() + ",orId="
		// + returnDataGrid.getRows().get(i).getOrId() + ",orCategory="
		// + returnDataGrid.getRows().get(i).getOrCategory() + ",title="
		// + returnDataGrid.getRows().get(i).getTitle() + ",workContent="
		// + returnDataGrid.getRows().get(i).getWorkContent() + ",applicant="
		// + returnDataGrid.getRows().get(i).getApplicant() + ",applicantTime="
		// + returnDataGrid.getRows().get(i).getApplyTime() + ",performer="
		// + returnDataGrid.getRows().get(i).getPerformer() + ",performerTime="
		// + returnDataGrid.getRows().get(i).getPerformTime() + ",isowner="
		// + returnDataGrid.getRows().get(i).getIsowner() + ",businessSystem="
		// + returnDataGrid.getRows().get(i).getBusinessSystem() +
		// ",performMode="
		// + returnDataGrid.getRows().get(i).getPerformMode() + ",annotation="
		// + returnDataGrid.getRows().get(i).getAnnotation() + ",dataState="
		// + returnDataGrid.getRows().get(i).getDataState() + ",status="
		// + returnDataGrid.getRows().get(i).getStatus() + ",creator="
		// + returnDataGrid.getRows().get(i).getCreator() + ",createTime="
		// + returnDataGrid.getRows().get(i).getCreateTime() + ",modifier="
		// + returnDataGrid.getRows().get(i).getModifier() + ",modifyTime="
		// + returnDataGrid.getRows().get(i).getModifyTime() + ",att1="
		// + returnDataGrid.getRows().get(i).getAtt1() + ",att2="
		// + returnDataGrid.getRows().get(i).getAtt2() + ",att3="
		// + returnDataGrid.getRows().get(i).getAtt3());
		// }
		// }
		log.info("end [queryItsmOrderDo] method.");
		return returnDataGrid;

	}

	/**
	 * bacthDelete itsmorder
	 * */
	@RequestMapping(value = "/bacthDeleteItsmOrderDo")
	public void bacthDeleteItsmOrderDo(HttpServletRequest request,
			HttpServletResponse response, String uuids) {

		log.info("start [bacthDeleteItsmOrderDo] method.");
		/*
		 * Generate uuid list.
		 */
		List<String> uuIds = new ArrayList<String>();
		if (!BaseUtil.getInstance().isEmpty(uuids)) {

			String[] ss = uuids.split(",");
			for (int i = 0; i < ss.length; i++) {
				uuIds.add(ss[i]);
			}
		}
		if (log.isDebugEnabled()) {
			log.debug("uuIds.value:" + uuids);
			log.debug("uuIds.size:" + uuIds.size());
		}

		/*
		 * Do batch delete.
		 */
		updateService.batchDeleteItsmOrder(uuIds);
		log.info("end [bacthDeleteItsmOrderDo] method.");
	}

	/**
	 * foward add itsmorder
	 * */
	@RequestMapping(value = "/fowardEditItsmOrder")
	public ModelAndView fowardEditItsmOrder(HttpServletRequest request,
			ItsmOrderSearchParameter itsmOrderSearchParameter) {
		log.info("start [fowardEditItsmOrder] method.");
		String foward = "admin/itsmorder/InsertItsmOrder";
		/*
		 * Search parameter.
		 */
		int pageint = ServletRequestUtils.getIntParameter(request, "page", 1);// ��ǰҳ
		int rowsint = ServletRequestUtils.getIntParameter(request, "rows", 1);// ����
		int start = ((pageint - 1) * rowsint) + 1;

		itsmOrderSearchParameter.setStart(start);
		itsmOrderSearchParameter.setNumber(rowsint);

		if (log.isDebugEnabled()) {
			log.debug("pageint:" + pageint);
			log.debug("rowsint:" + rowsint);
			log.debug("start:" + start);
		}

		/*
		 * Set return bean.
		 */
		ItsmOrderInfoBean curItsmOrderInfoBean = new ItsmOrderInfoBean();
		curItsmOrderInfoBean.setUuid("");
		curItsmOrderInfoBean.setOrId("");
		curItsmOrderInfoBean.setOrCategory("");
		curItsmOrderInfoBean.setTitle("");
		curItsmOrderInfoBean.setWorkContent("");
		curItsmOrderInfoBean.setApplicant("");
		curItsmOrderInfoBean.setApplyTime("");
		curItsmOrderInfoBean.setPerformer("");
		curItsmOrderInfoBean.setPerformTime("");
		curItsmOrderInfoBean.setIsowner("");
		curItsmOrderInfoBean.setBusinessSystem("");
		curItsmOrderInfoBean.setPerformMode("");
		curItsmOrderInfoBean.setAnnotation("");
		curItsmOrderInfoBean.setDataState("");

		/*
		 * Collect dto.
		 */
		ItsmOrderDto itsmOrderDto = new ItsmOrderDto();
		itsmOrderDto.setItsmOrderSearchParameter(itsmOrderSearchParameter);
		itsmOrderDto.setItsmOrderInfoBean(curItsmOrderInfoBean);

		log.info("queryItsmOrder:" + "uuid=" + curItsmOrderInfoBean.getUuid()
				+ ",orId=" + curItsmOrderInfoBean.getOrId() + ",orCategory="
				+ curItsmOrderInfoBean.getOrCategory() + ",title="
				+ curItsmOrderInfoBean.getTitle() + ",workContent="
				+ curItsmOrderInfoBean.getWorkContent() + ",applicant="
				+ curItsmOrderInfoBean.getApplicant() + ",applyTimeBefore="
				+ itsmOrderSearchParameter.getApplyTimeBefore()
				+ ",applyTimeAfter="
				+ itsmOrderSearchParameter.getApplyTimeAfter() + ",performer="
				+ curItsmOrderInfoBean.getPerformer() + ",performTimeBefore="
				+ itsmOrderSearchParameter.getPerformTimeBefore()
				+ ",performTimeAfter="
				+ itsmOrderSearchParameter.getPerformTimeAfter() + ",isowner="
				+ curItsmOrderInfoBean.getIsowner() + ",businessSystem="
				+ curItsmOrderInfoBean.getBusinessSystem() + ",performMode="
				+ curItsmOrderInfoBean.getPerformMode() + ",annotation="
				+ curItsmOrderInfoBean.getAnnotation() + ",dataState="
				+ curItsmOrderInfoBean.getDataState() + ",status="
				+ curItsmOrderInfoBean.getStatus() + ",creator="
				+ curItsmOrderInfoBean.getCreator() + ",createTime"
				+ curItsmOrderInfoBean.getCreateTime() + ",modifier="
				+ curItsmOrderInfoBean.getModifier() + ",modifyTime="
				+ curItsmOrderInfoBean.getModifyTime() + ",att1="
				+ curItsmOrderInfoBean.getAtt1() + ",att2="
				+ curItsmOrderInfoBean.getAtt2() + ",att3="
				+ curItsmOrderInfoBean.getAtt3());
		/*
		 * Do query.
		 */
		DataGrid<ItsmOrderInfoBean> dateGrid = queryService
				.queryItsmOrderByParameter(itsmOrderDto);
		if (log.isDebugEnabled()) {
			log.debug("itsmOrderList.size:" + dateGrid.getRows().size());
		}

		/*
		 * Check if DB have record.
		 */
		if (dateGrid.getRows().size() > 0) {
			/*
			 * For edit.
			 */
			request.getSession().setAttribute("uuid",
					itsmOrderSearchParameter.getUuid_equ());
			request.setAttribute("itsmOrderInfoBean", dateGrid.getRows().get(0));

			/*
			 * ItsmOrderInfoBean itsmOrderInfoBean = dateGrid.getRows().get(0);
			 * log.info("queryItsmOrder:" +"uuid" + itsmOrderInfoBean.getUuid()
			 * + ",orId=" + itsmOrderInfoBean.getOrId() + ",orCategory=" +
			 * itsmOrderInfoBean.getOrCategory() + ",title=" +
			 * itsmOrderInfoBean.getTitle() + ",workContent=" +
			 * itsmOrderInfoBean.getWorkContent() + ",applicant=" +
			 * itsmOrderInfoBean.getApplicant() + ",applyTime=" +
			 * itsmOrderInfoBean.getApplyTime() + ",performer=" +
			 * itsmOrderInfoBean.getPerformer() + ",isowner=" +
			 * itsmOrderInfoBean.getIsowner() + ",businessSystem=" +
			 * itsmOrderInfoBean.getBusinessSystem() + ",performMode=" +
			 * itsmOrderInfoBean.getPerformMode() + ",annotation=" +
			 * itsmOrderInfoBean.getAnnotation() + ",dataState=" +
			 * itsmOrderInfoBean.getDataState() + ",status=" +
			 * itsmOrderInfoBean.getStatus() + ",creator=" +
			 * itsmOrderInfoBean.getCreator() + ",createTime" +
			 * itsmOrderInfoBean.getCreateTime() + ",modifier=" +
			 * itsmOrderInfoBean.getModifier() + ",modifyTime=" +
			 * itsmOrderInfoBean.getModifyTime() + ",att1=" +
			 * itsmOrderInfoBean.getAtt1() + ",att2=" +
			 * itsmOrderInfoBean.getAtt2() + ",att3=" +
			 * itsmOrderInfoBean.getAtt3());
			 */
			// for(int i = 0 ; i < dateGrid.getRows().size();i++)
			// {
			// log.info("queryItsmOrder:" +" uuid"
			// + dateGrid.getRows().get(i).getUuid() + ",orId="
			// + dateGrid.getRows().get(i).getOrId() + ",orCategory="
			// + dateGrid.getRows().get(i).getOrCategory() + ",title="
			// + dateGrid.getRows().get(i).getTitle() + ",workContent="
			// + dateGrid.getRows().get(i).getWorkContent() + ",applicant="
			// + dateGrid.getRows().get(i).getApplicant() + ",applicantTime="
			// + dateGrid.getRows().get(i).getApplyTime() + ",performer="
			// + dateGrid.getRows().get(i).getPerformer() + ",performerTime="
			// + dateGrid.getRows().get(i).getPerformTime() + ",isowner="
			// + dateGrid.getRows().get(i).getIsowner() + ",businessSystem="
			// + dateGrid.getRows().get(i).getBusinessSystem() + ",performMode="
			// + dateGrid.getRows().get(i).getPerformMode() + ",annotation="
			// + dateGrid.getRows().get(i).getAnnotation() + ",dataState="
			// + dateGrid.getRows().get(i).getDataState() + ",status="
			// + dateGrid.getRows().get(i).getStatus() + ",creator="
			// + dateGrid.getRows().get(i).getCreator() + ",createTime="
			// + dateGrid.getRows().get(i).getCreateTime() + ",modifier="
			// + dateGrid.getRows().get(i).getModifier() + ",modifyTime="
			// + dateGrid.getRows().get(i).getModifyTime() + ",att1="
			// + dateGrid.getRows().get(i).getAtt1() + ",att2="
			// + dateGrid.getRows().get(i).getAtt2() + ",att3="
			// + dateGrid.getRows().get(i).getAtt3());
			// }
		} else {
			/*
			 * For add.
			 */
			request.getSession().setAttribute("uuid", "");
			if (log.isDebugEnabled()) {
				log.debug("uuid:" + request.getSession().getAttribute("uuid"));
			}
		}

		log.info("end [fowardEditItsmOrder] method.");
		return new ModelAndView(foward);
	}

	/**
	 * delete itsmorder by orid
	 * 
	 * @throws IOException
	 */
	@RequestMapping(value = "/deleteItsmOrderDoByOrId")
	public void deleteItsmOrderDoByOrId(HttpServletRequest request,
			HttpServletResponse response, String orId) throws IOException {

		log.info("start [deleteItsmOrderDoByOrId] method.");
		/*
		 * Generate uuid list.
		 */
//		System.out.println("aaaaaaaaaaaa"+orId);
		
		ItsmOrderInfoBean itsmOrderInfoBean = new ItsmOrderInfoBean();

		itsmOrderInfoBean.setOrId(orId);
		if (log.isDebugEnabled()) {
			log.debug("uuIds.value:" + orId);
		}

		/*
		 * Do batch delete.
		 */
		boolean flag = false;
		if (flag) {
			response.setHeader("Content-type", "text/html;charset=UTF-8");
			// output data directly to the client
			response.getWriter()
					.write("<script type='text/javascript' type='language'>parent.window.alert('Delete Successful!');</script>");
			// empty the cache data
			response.getWriter().flush();
			response.getWriter().close();
		} else {
			response.setHeader("Content-type", "text/html;charset=UTF-8");
			// output data directly to the client
			response.getWriter()
					.write("<script type='text/javascript' type='language'>parent.window.alert('Delete failure!!');</script>");
			// empty the cache data
			response.getWriter().flush();
			response.getWriter().close();
		}
		log.info("end [deleteItsmOrderDoByOrId] method.");
	}

	/**
	 * add itsmorder
	 **/

	@RequestMapping(value = "/addOrEditItsmOrder")
	public ModelAndView addItsmOrder(HttpServletRequest request,
			ItsmOrderInfoBean itsmOrderInfoBean) {
		log.info("start [addItsmOrder] method.");

		String foward = "admin/itsmorder/InsertItsmOrder";

		Timestamp curTime = new Timestamp(System.currentTimeMillis());

		Object uuidObj = request.getSession().getAttribute("uuid");

		if (log.isDebugEnabled()) {
			log.debug("uuidObj" + uuidObj.toString());
		}

		if (BaseUtil.getInstance().isEmpty(uuidObj)) {
			/*
			 * For add.
			 */
			// Timestamp createTime = new Timestamp(System.currentTimeMillis());
			// itsmOrderInfoBean.setCreateTime(TimeUtil.getInstance().timestampToString(curTime));
			updateService.insertItsmOrder(itsmOrderInfoBean);

			log.info("insertItsmOrder:" + "orId" + itsmOrderInfoBean.getOrId()
					+ ",orCategory=" + itsmOrderInfoBean.getOrCategory()
					+ ",title=" + itsmOrderInfoBean.getTitle()
					+ ",workContent=" + itsmOrderInfoBean.getWorkContent()
					+ ",applicant=" + itsmOrderInfoBean.getApplicant()
					+ ",applyTime=" + itsmOrderInfoBean.getApplyTime()
					+ ",performer=" + itsmOrderInfoBean.getPerformer()
					+ ",performTime=" + itsmOrderInfoBean.getPerformTime()
					+ ",isowner=" + itsmOrderInfoBean.getIsowner()
					+ ",businessSystem="
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

		} else {
			/*
			 * For edit.
			 */
			// itsmOrderInfoBean.setModifyTime(TimeUtil.getInstance().timestampToString(curTime));

			ItsmOrderSearchParameter itsmOrderSearchParameter = new ItsmOrderSearchParameter();
			itsmOrderSearchParameter.setUuid_equ((String) uuidObj);

			ItsmOrderDto itsmOrderDto = new ItsmOrderDto();
			itsmOrderDto.setItsmOrderSearchParameter(itsmOrderSearchParameter);
			itsmOrderDto.setItsmOrderInfoBean(itsmOrderInfoBean);
			updateService.batchModifyItsmOrder(itsmOrderDto);

			log.info("modifyItsmOrder:" + "orId" + itsmOrderInfoBean.getOrId()
					+ ",orCategory=" + itsmOrderInfoBean.getOrCategory()
					+ ",title=" + itsmOrderInfoBean.getTitle()
					+ ",workContent=" + itsmOrderInfoBean.getWorkContent()
					+ ",applicant=" + itsmOrderInfoBean.getApplicant()
					+ ",applyTime=" + itsmOrderInfoBean.getApplyTime()
					+ ",performer=" + itsmOrderInfoBean.getPerformer()
					+ ",isowner=" + itsmOrderInfoBean.getIsowner()
					+ ",businessSystem="
					+ itsmOrderInfoBean.getBusinessSystem() + ",performMode="
					+ itsmOrderInfoBean.getPerformMode() + ",performTime="
					+ itsmOrderInfoBean.getPerformTime() + ",annotation="
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
		}

		log.info("end [addItsmOrder] method.");
		return new ModelAndView(foward);
	}

	@RequestMapping(value = "/addAndSendItsm")
	public ModelAndView addAndSendItsm(HttpServletRequest request,
			ItsmOrderInfoBean itsmOrderInfoBean) {

		log.info("start [addAndSendItsm] method.");

		String foward = "admin/itsmorder/FowardItsmCreate";
		itsmOrderInfoBean.setAtt1(request.getParameter("dataSource"));
		itsmOrderInfoBean.setAtt2(request.getParameter("itiluserid"));
		itsmOrderInfoBean.setPerformer(request.getParameter("Performer"));
		itsmOrderInfoBean.setTitle(request.getParameter("title"));
		itsmOrderInfoBean.setPerformMode(request.getParameter("performMode"));
		String JsonStr = "{\"dataResource\":\"" + itsmOrderInfoBean.getAtt1()
				+ "\"," + "\"applicant\":\"" + itsmOrderInfoBean.getPerformer()
				+ "\",\"description\":\"" + itsmOrderInfoBean.getTitle()
				+ "\",\"resolution\":\"" + itsmOrderInfoBean.getPerformMode()
				+ "\"}";

/*
 // 这里是调用ITIL系统生成工单号的连接地址
 
 	try {
			Service s = Service
					.create(new URL(
							"http://10.1.63.52:8080/fvsd_imrc/services/DataTransportService"),
							new QName(
									"http://transport.service.server.webservice.itsm.dhcc.com/",
									"DataTransportServiceImplService"));
			DataTransportService d = s.getPort(DataTransportService.class);

			itsmOrderInfoBean.setOrId(d.createRequestForJson(JsonStr));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 	
*/		
		itsmOrderInfoBean.setOrId("fdsafdsa");//这里先指定ID
		
		updateService.insertItsmOrder(itsmOrderInfoBean);

		log.info("insertItsmOrder:" + "orId" + itsmOrderInfoBean.getOrId()
				+ ",orCategory=" + itsmOrderInfoBean.getOrCategory()
				+ ",title=" + itsmOrderInfoBean.getTitle() + ",workContent="
				+ itsmOrderInfoBean.getWorkContent() + ",applicant="
				+ itsmOrderInfoBean.getApplicant() + ",applyTime="
				+ itsmOrderInfoBean.getApplyTime() + ",performer="
				+ itsmOrderInfoBean.getPerformer() + ",performTime="
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
		log.info("end [addAndSendItsm] method.");

		return new ModelAndView(foward);
	}

	// public static void main(String[] args) {
	// try {
	// Service s = Service.create(new
	// URL("http://10.1.63.52:8080/fvsd_imrc/services/DataTransportService"),
	// new QName("http://transport.service.server.webservice.itsm.dhcc.com/",
	// "DataTransportServiceImplService"));
	// DataTransportService d = s.getPort(DataTransportService.class);
	// System.out.println(d.createRequestForJson("11"));
	// } catch (MalformedURLException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }

}
