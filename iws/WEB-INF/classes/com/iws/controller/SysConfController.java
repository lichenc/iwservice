package com.iws.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;




import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.iws.pojo.sysconf.SysConfDto;
import com.iws.pojo.sysconf.SysConfInfoBean;
import com.iws.pojo.sysconf.SysConfSearchParameter;
import com.iws.service.QueryService;
import com.iws.service.UpdateService;
import com.iws.util.BaseUtil;
import com.iws.util.DataGrid;
import com.iws.util.TimeUtil;

@Controller
@RequestMapping(value = "/sysconf")
public class SysConfController {

	@Autowired
	private QueryService queryService;

	@Autowired
	private UpdateService updateService;

	private static final Logger log = Logger.getLogger(SysConfController.class);
	
	@RequestMapping(value = "/fowardQuerySysConf")
	public ModelAndView fowardQuerySysConf(HttpServletRequest request,
			HttpServletResponse response) {
	    log.info("start [fowardQuerySysConf] method");
	    
		String foward = "admin/sysconf/SysConf";
		
		log.info("end [fowardQuerySysConf] method");
		return new ModelAndView(foward);
	}

	@RequestMapping(value = "/querySysConfDo")
	@ResponseBody
	public DataGrid<SysConfInfoBean> querySysConfDo(HttpServletRequest request,
			HttpServletResponse response,
			SysConfSearchParameter sysConfSearchParameter) {
	    
	    log.info("start [querySysConfDo] method");
	    
		/*
		 * Get easyui table page info.
		 */
		int pageint = ServletRequestUtils.getIntParameter(request, "page", 1);// ��ǰҳ
		int rowsint = ServletRequestUtils.getIntParameter(request, "rows", 10);// ����
		int start = ((pageint - 1) * rowsint) + 1;
		sysConfSearchParameter.setStart(start);
		sysConfSearchParameter.setNumber(rowsint);

		  
        if(log.isDebugEnabled())
        {
            log.debug("pageint:"+pageint);
            log.debug("rowsint:"+rowsint);
            log.debug("start:"+start);
        }
        
        if (log.isDebugEnabled()) {
            log.debug("sort:" + sysConfSearchParameter.getSort());
            log.debug("order:" + sysConfSearchParameter.getOrder());
        }
		/*
		 * Generate return bean.
		 */
		SysConfInfoBean curSysConfInfoBean = new SysConfInfoBean();
		curSysConfInfoBean.setUuid("");
		curSysConfInfoBean.setCfgCategory("");
		curSysConfInfoBean.setCfgGroup("");
		curSysConfInfoBean.setCfgKey("");
		curSysConfInfoBean.setCfgKeyType("");
		curSysConfInfoBean.setCfgValue("");
		curSysConfInfoBean.setCfgValueType("");
		curSysConfInfoBean.setCfgValueInfo("");
		curSysConfInfoBean.setStatus("");
		curSysConfInfoBean.setCreator("");
		curSysConfInfoBean.setCreateTime("");
		curSysConfInfoBean.setModifier("");
		curSysConfInfoBean.setModifyTime("");
		curSysConfInfoBean.setAtt1("");
		curSysConfInfoBean.setAtt2("");
		curSysConfInfoBean.setAtt3("");
		
		/*
		 * Collect dto.
		 */
		SysConfDto curSysConfDto = new SysConfDto();
		curSysConfDto.setSysConfSearchParameter(sysConfSearchParameter);
		curSysConfDto.setSysConfInfoBean(curSysConfInfoBean);
		
		log.info("querySysConf:" +"uuid"
		        + curSysConfInfoBean.getUuid() + "cfgCategory=" 
		        + curSysConfInfoBean.getCfgCategory() + ",cfgGroup="
                + curSysConfInfoBean.getCfgGroup() + ",cfgKey="
                + curSysConfInfoBean.getCfgKey() + ",cfgKeyType="
                + curSysConfInfoBean.getCfgKeyType() + ",cfgValue="
                + curSysConfInfoBean.getCfgValue() +  ",cfgValueType="
                + curSysConfInfoBean.getCfgValueType() + ",cfgValueInfo="
                + curSysConfInfoBean.getCfgValueInfo() +",status="
                + curSysConfInfoBean.getStatus() + ",creator="
                + curSysConfInfoBean.getCreator() + ",createTime="
                + curSysConfInfoBean.getCreateTime()  + ",modifier="
                + curSysConfInfoBean.getModifier() + ",modifyTime="
                + curSysConfInfoBean.getModifyTime()+ ",att1="
                + curSysConfInfoBean.getAtt1() + ",att2=" 
                + curSysConfInfoBean.getAtt2() + ",att3="
                + curSysConfInfoBean.getAtt3());
		
		/*
		 * Do query.
		 */
		DataGrid<SysConfInfoBean> returnDataGrid = queryService
				.querySysConfByParameter(curSysConfDto);
		
		
		if(log.isDebugEnabled())
		{
		    log.debug("returnDataGrid.size:"+returnDataGrid.getRows().size());
		}
		
		log.info("end [querySysConfDo] method");
		return returnDataGrid;

	}

	/**
	 * bacthDelete itsmorder
	 * */
	@RequestMapping(value = "/bacthDeleteSysConfDo")
	public void bacthDeleteSysConfDo(HttpServletRequest request,
			HttpServletResponse response, String uuids) {

	    log.info("start [bacthDeleteSysConfDo] method");
		/*
		 * Generate uuid list.
		 */
		List<String> uuIds = new ArrayList<String>();
		if (!BaseUtil.getInstance().isEmpty(uuids)) {

			String[] ss = uuids.split(",");
			for (int i = 0; i < ss.length; i++) {
				uuIds.add(ss[i]);
			}
			if(log.isDebugEnabled())
	        {
	            log.debug("uuIds.value:"+uuids);
	            log.debug("uuIds.size:"+uuIds.size());
	        }
		}

		/*
		 * Do batch delete.
		 */
				
		updateService.batchDeleteSysConf(uuIds);
		log.info("end [bacthDeleteSysConfDo] method");
	}

	/**
	 * foward add itsmorder
	 * */

	@RequestMapping(value = "/fowardEditSysConf")
	public ModelAndView fowardEditSysConf(HttpServletRequest request,
			SysConfSearchParameter sysConfSearchParameter) {
	    log.info("start [fowardEditSysConf] method");
	    
		String foward = "admin/sysconf/InsertSysConf";
		/*
		 * Search parameter.
		 */
		int pageint = ServletRequestUtils.getIntParameter(request, "page", 1);// ��ǰҳ
		int rowsint = ServletRequestUtils.getIntParameter(request, "rows", 1);// ����
		int start = ((pageint - 1) * rowsint) + 1;
		sysConfSearchParameter.setStart(start);
		sysConfSearchParameter.setNumber(rowsint);

		 if(log.isDebugEnabled())
	        {
	            log.debug("pageint:"+pageint);
	            log.debug("rowsint:"+rowsint);
	            log.debug("start:"+start);
	        }
		 
		/*
		 * Set return bean.
		 */
		SysConfInfoBean curSysConfInfoBean = new SysConfInfoBean();
		curSysConfInfoBean.setUuid("");
		curSysConfInfoBean.setCfgCategory("");
		curSysConfInfoBean.setCfgGroup("");
		curSysConfInfoBean.setCfgKey("");
		curSysConfInfoBean.setCfgKeyType("");
		curSysConfInfoBean.setCfgValue("");
		curSysConfInfoBean.setCfgValueType("");
		curSysConfInfoBean.setCfgValueInfo("");
		curSysConfInfoBean.setStatus("");
		curSysConfInfoBean.setCreator("");
		curSysConfInfoBean.setCreateTime("");
		curSysConfInfoBean.setModifier("");
		curSysConfInfoBean.setModifyTime("");
		curSysConfInfoBean.setAtt1("");
		curSysConfInfoBean.setAtt2("");
		curSysConfInfoBean.setAtt3("");

		/*
		 * Collect dto.
		 */
		SysConfDto sysConfDto = new SysConfDto();
		sysConfDto.setSysConfSearchParameter(sysConfSearchParameter);
		sysConfDto.setSysConfInfoBean(curSysConfInfoBean);

		log.info("querySysConf:" + "uuid" 
		        + curSysConfInfoBean.getUuid() +"cfgCategory=" 
		        + curSysConfInfoBean.getCfgCategory() + ",cfgGroup="
                + curSysConfInfoBean.getCfgGroup() + ",cfgKey="
                + curSysConfInfoBean.getCfgKey() + ",cfgKeyType="
                + curSysConfInfoBean.getCfgKeyType() + ",cfgValue="
                + curSysConfInfoBean.getCfgValue() +  ",cfgValueType="
                + curSysConfInfoBean.getCfgValueType() + ",cfgValueInfo="
                + curSysConfInfoBean.getCfgValueInfo() +",status="
                + curSysConfInfoBean.getStatus() + ",creator="
                + curSysConfInfoBean.getCreator() + ",createTime="
                + curSysConfInfoBean.getCreateTime()  + ",modifier="
                + curSysConfInfoBean.getModifier() + ",modifyTime="
                + curSysConfInfoBean.getModifyTime()+ ",att1="
                + curSysConfInfoBean.getAtt1() + ",att2="
                + curSysConfInfoBean.getAtt2() + ",att3="
                + curSysConfInfoBean.getAtt3());
		/*
		 * Do query.
		 */
		DataGrid<SysConfInfoBean> dateGrid = queryService.querySysConfByParameter(sysConfDto);

		if(log.isDebugEnabled())
		{
		   log.debug("sysConfList.size:"+dateGrid.getRows().size()); 
		}
		
		/*
		 * Check if DB have record.
		 */
		if (dateGrid.getRows().size() > 0) {
			/*
			 * For edit.
			 */
			request.getSession().setAttribute("uuid",sysConfSearchParameter.getUuid_equ());
			
			log.info("uuid:"+sysConfSearchParameter.getUuid_equ());
			
			request.setAttribute("sysConfInfoBean", dateGrid.getRows().get(0));
			
			SysConfInfoBean sysConfInfoBean = new SysConfInfoBean();
			sysConfInfoBean = dateGrid.getRows().get(0);
			log.info("querySysConf:"  + "uuid" 
	                + sysConfInfoBean.getUuid() + "cfgCategory=" 
			        + sysConfInfoBean.getCfgCategory() + ",cfgGroup=" 
	                + sysConfInfoBean.getCfgGroup() + ",cfgKey="
		            + sysConfInfoBean.getCfgKey() + ",cfgKeyType="
		            + sysConfInfoBean.getCfgKeyType() + ",cfgValue="
		            + sysConfInfoBean.getCfgValue() +  ",cfgValueType="
		            + sysConfInfoBean.getCfgValueType() + ",cfgValueInfo="
		            + sysConfInfoBean.getCfgValueInfo() +",status="
		            + sysConfInfoBean.getStatus() + ",creator="
		            + sysConfInfoBean.getCreator() + ",createTime="
		            + sysConfInfoBean.getCreateTime()  + ",modifier="
		            + sysConfInfoBean.getModifier() + ",modifyTime="
		            + sysConfInfoBean.getModifyTime()+ ",att1="
		            + sysConfInfoBean.getAtt1() + ",att2=" 
		            + sysConfInfoBean.getAtt2() + ",att3=" 
		            + sysConfInfoBean.getAtt3());
		} else {
			/*
			 * For add.
			 */
			request.getSession().setAttribute("uuid", "");
			if(log.isDebugEnabled())
	        {
                log.debug("uuid:"+request.getSession().getAttribute("uuid"));
            }
		}
		log.info("end [fowardEditSysConf] method");
		return new ModelAndView(foward);
	}

	/**
	 * add itsmorder
	 **/

	@RequestMapping(value = "/addOrEditSysConf")
	public ModelAndView addSysConf(HttpServletRequest request,
			SysConfInfoBean sysConfInfoBean) {
	    
	    log.info("start [addSysConf] method");
	    
		String foward = "admin/sysconf/InsertSysConf";

		Timestamp curTime = new Timestamp(System.currentTimeMillis());

		Object uuidObj = request.getSession().getAttribute("uuid");

		if (BaseUtil.getInstance().isEmpty(uuidObj) ) {
			/*
			 * For add.
			 */
//			Timestamp createTime = new Timestamp(System.currentTimeMillis());
//			sysConfInfoBean.setCreateTime(TimeUtil.getInstance()
//					.timestampToString(curTime));
			updateService.insertSysConf(sysConfInfoBean);
			
			log.info("insertSysConf:" +"uuid" 
			        + uuidObj.toString() + "cfgCategory="
			        + sysConfInfoBean.getCfgCategory() + ",cfgGroup=" 
			        + sysConfInfoBean.getCfgGroup() + ",cfgKey="
                    + sysConfInfoBean.getCfgKey() + ",cfgKeyType="
                    + sysConfInfoBean.getCfgKeyType() + ",cfgValue="
                    + sysConfInfoBean.getCfgValue() +  ",cfgValueType="
                    + sysConfInfoBean.getCfgValueType() + ",cfgValueInfo="
                    + sysConfInfoBean.getCfgValueInfo() +",status="
                    + sysConfInfoBean.getStatus() + ",creator="
                    + sysConfInfoBean.getCreator() + ",createTime="
                    + sysConfInfoBean.getCreateTime()  + ",modifier="
                    + sysConfInfoBean.getModifier() + ",modifyTime="
                    + sysConfInfoBean.getModifyTime()+ ",att1="
                    + sysConfInfoBean.getAtt1() + ",att2="
                    + sysConfInfoBean.getAtt2() + ",att3="
                    + sysConfInfoBean.getAtt3());

		} else {
			/*
			 * For edit.
			 */
//			sysConfInfoBean.setModifyTime(TimeUtil.getInstance().timestampToString(curTime));

			SysConfSearchParameter sysConfSearchParameter = new SysConfSearchParameter();
			sysConfSearchParameter.setUuid_equ((String) uuidObj);

			SysConfDto sysConfDto = new SysConfDto();
			sysConfDto.setSysConfSearchParameter(sysConfSearchParameter);
			sysConfDto.setSysConfInfoBean(sysConfInfoBean);
			updateService.batchModifySysConf(sysConfDto);
			
			log.info("modifySysConf:" +"uuid" 
                    + uuidObj.toString() + "cfgCategory=" 
			        + sysConfInfoBean.getCfgCategory() + ",cfgGroup=" 
                    + sysConfInfoBean.getCfgGroup() + ",cfgKey="
                    + sysConfInfoBean.getCfgKey() + ",cfgKeyType="
                    + sysConfInfoBean.getCfgKeyType() + ",cfgValue="
                    + sysConfInfoBean.getCfgValue() +  ",cfgValueType="
                    + sysConfInfoBean.getCfgValueType() + ",cfgValueInfo="
                    + sysConfInfoBean.getCfgValueInfo() +",status="
                    + sysConfInfoBean.getStatus() + ",creator="
                    + sysConfInfoBean.getCreator() + ",createTime="
                    + sysConfInfoBean.getCreateTime()  + ",modifier="
                    + sysConfInfoBean.getModifier() + ",modifyTime="
                    + sysConfInfoBean.getModifyTime()+ ",att1="
                    + sysConfInfoBean.getAtt1() + ",att2="
                    + sysConfInfoBean.getAtt2() + ",att3=" 
                    + sysConfInfoBean.getAtt3());

		}
		log.info("end [addSysConf] method");
		return new ModelAndView(foward);
	}

}
