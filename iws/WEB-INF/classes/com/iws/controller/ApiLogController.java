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

import com.iws.pojo.apilog.ApiLogDto;
import com.iws.pojo.apilog.ApiLogInfoBean;
import com.iws.pojo.apilog.ApiLogSearchParameter;
import com.iws.service.QueryService;
import com.iws.service.UpdateService;
import com.iws.util.BaseUtil;
import com.iws.util.DataGrid;
import com.iws.util.TimeUtil;


@Controller
@RequestMapping(value="/apilog")
public class ApiLogController {
    
    
    @Autowired
    private QueryService queryService;

    @Autowired
    private UpdateService updateService;
    
    private static final Logger log = Logger.getLogger(ApiLogController.class);
    
    @RequestMapping(value="/fowardQueryApiLog")
    public ModelAndView fowardQueryApiLog(HttpServletRequest request,HttpServletResponse response)
    {
        log.info("start [fowardQueryApiLog] method.");
        
        String foward = "admin/apilog/ApiLog";
        
        log.info("end [fowardQueryApiLog] method.");
        return new ModelAndView(foward);
    }
    
    
    @RequestMapping(value="/queryApiLogDo")
    @ResponseBody
    public DataGrid<ApiLogInfoBean> queryApiLogDo(HttpServletRequest request, HttpServletResponse response,
            ApiLogSearchParameter apiLogSearchParameter) {  
        log.info("start [queryApiLogDo] method.");
        
        /*
         * Get easyui table page info. 
         */
        int pageint = ServletRequestUtils.getIntParameter(request, "page", 1);// ��ǰҳ
        int rowsint = ServletRequestUtils.getIntParameter(request, "rows", 10);// ����
        int start = ((pageint - 1) * rowsint) + 1;
        apiLogSearchParameter.setStart(start);
        apiLogSearchParameter.setNumber(rowsint);
        
        if(log.isDebugEnabled())
        {
            log.debug("pageint:"+pageint);
            log.debug("rowsint:"+rowsint);
            log.debug("start:"+start);
        }
        
        if (log.isDebugEnabled()) {
            log.debug("sort:" + apiLogSearchParameter.getSort());
            log.debug("order:" + apiLogSearchParameter.getOrder());
        }
        
        System.out.println("getOrId_equ:"+apiLogSearchParameter.getStatus_like());  
        System.out.println("getApplicant_equ:"+apiLogSearchParameter.getActip_like());  
        
        
        /*
         * Generate return bean. 
         */
        ApiLogInfoBean apiLogInfoBean=new ApiLogInfoBean();
        apiLogInfoBean.setUuid(""); 
        apiLogInfoBean.setLogcode("");
        apiLogInfoBean.setLoglevel("");      
        apiLogInfoBean.setLogtitle("");
        apiLogInfoBean.setLogcontent("");
        apiLogInfoBean.setExtlogcode("");
        apiLogInfoBean.setExtlogcontent("");
        apiLogInfoBean.setLogcategory("");      
        apiLogInfoBean.setLogmodule("");
        apiLogInfoBean.setActip("");
        apiLogInfoBean.setActmac("");
        apiLogInfoBean.setStatus("");
        apiLogInfoBean.setCreator("");
        apiLogInfoBean.setCreateTime("");
        apiLogInfoBean.setModifier("");
        apiLogInfoBean.setModifyTime("");
        apiLogInfoBean.setAtt1("");
        apiLogInfoBean.setAtt2("");
        apiLogInfoBean.setAtt3("");
        
        /*
         * Collect dto. 
         */
        ApiLogDto apiLogDto=new ApiLogDto();
        apiLogDto.setApiLogSearchParameter(apiLogSearchParameter);
        apiLogDto.setApiLogInfoBean(apiLogInfoBean);
        
        log.info("generateApiLog:" +"uuid" 
                + apiLogInfoBean.getUuid() + "logcode=" 
                + apiLogInfoBean.getLogcode() + ",loglevel=" 
                + apiLogInfoBean.getLoglevel() + ",logtitle="
                + apiLogInfoBean.getLogtitle() + ",logcontent="
                + apiLogInfoBean.getLogcontent() + ",extlogcode="
                + apiLogInfoBean.getExtlogcode() + ",extlogcontent="
                + apiLogInfoBean.getExtlogcontent() +  ",logcategory="
                + apiLogInfoBean.getLogcategory() + ",logmodule="
                + apiLogInfoBean.getLogmodule() + ",actip="
                + apiLogInfoBean.getActip() + ",actmac="
                + apiLogInfoBean.getStatus()+ ",status="
                + apiLogInfoBean.getStatus() + ",creator="
                + apiLogInfoBean.getCreator() + ",createTime="
                + apiLogInfoBean.getCreateTime() + ",modifier="
                + apiLogInfoBean.getModifier() + ",modifyTime="
                + apiLogInfoBean.getModifyTime() + ",att1="
                + apiLogInfoBean.getAtt1() + ",att2=" 
                + apiLogInfoBean.getAtt2() + ",att3=" 
                + apiLogInfoBean.getAtt3());
        
        /*
         * Do query. 
         */
        DataGrid<ApiLogInfoBean> returnDataGrid = queryService.queryApiLogByParameter(apiLogDto);
        
        if (log.isDebugEnabled()) {
            log.debug("ApiLogList.size:" + returnDataGrid.getRows().size());
        }
        
        log.info("end [queryApiLogDo] method.");
        return returnDataGrid;
       
    }
   
    /**
     *  bacthDelete itsmorder
     * */
    @RequestMapping(value="/bacthDeleteApiLogDo")
    public void bacthDeleteApiLogDo(HttpServletRequest request,HttpServletResponse response,String uuids)
    {

        log.info("start [bacthDeleteApiLogDo] method.");
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
        updateService.deleteapilogByUUID(uuIds);
        log.info("end [bacthDeleteApiLogDo] method.");
    }
    
    /**
     * foward add itsmorder
     * */
    
    @RequestMapping(value="/fowardEditApiLog")
    public ModelAndView fowardEditApiLog(HttpServletRequest request, ApiLogSearchParameter apiLogSearchParameter)
    {
        log.info("start [fowardEditApiLog] method.");
        
        String foward = "admin/apilog/InsertApiLog";
        /*
         * Search parameter. 
         */
        int pageint = ServletRequestUtils.getIntParameter(request, "page", 1);// ��ǰҳ
        int rowsint = ServletRequestUtils.getIntParameter(request, "rows", 1);// ����
        int start = ((pageint - 1) * rowsint) + 1;
        apiLogSearchParameter.setStart(start);
        apiLogSearchParameter.setNumber(rowsint);
        
        if(log.isDebugEnabled())
        {
            log.debug("pageint:"+pageint);
            log.debug("rowsint:"+rowsint);
            log.debug("start:"+start);
        }
        
        /*
         * Set return bean. 
         */
        ApiLogInfoBean apiLogInfoBean=new ApiLogInfoBean();
        apiLogInfoBean.setUuid("");
        apiLogInfoBean.setLogcode("");
        apiLogInfoBean.setLoglevel("");        
        apiLogInfoBean.setLogtitle("");
        apiLogInfoBean.setLogcontent("");
        apiLogInfoBean.setExtlogcode("");
        apiLogInfoBean.setExtlogcontent("");
        apiLogInfoBean.setLogcategory("");
        apiLogInfoBean.setLogmodule("");        
        apiLogInfoBean.setActip("");
        apiLogInfoBean.setActmac("");       
        apiLogInfoBean.setStatus("");
        apiLogInfoBean.setCreator("");
        apiLogInfoBean.setCreateTime("");
        apiLogInfoBean.setModifier("");
        apiLogInfoBean.setModifyTime("");
        apiLogInfoBean.setAtt1("");
        apiLogInfoBean.setAtt2("");
        apiLogInfoBean.setAtt3("");
        
        /*
         * Collect dto. 
         */
        ApiLogDto apiLogDto = new ApiLogDto(); 
        apiLogDto.setApiLogSearchParameter(apiLogSearchParameter);
        apiLogDto.setApiLogInfoBean(apiLogInfoBean);
        
        log.info("queryApiLog:" +"uuid" 
                + apiLogInfoBean.getUuid()+ "logcode=" 
                + apiLogInfoBean.getLogcode() + ",loglevel=" 
                + apiLogInfoBean.getLoglevel() + ",logtitle="
                + apiLogInfoBean.getLogtitle() + ",logcontent="
                + apiLogInfoBean.getLogcontent() + ",extlogcode="
                + apiLogInfoBean.getExtlogcode() + ",extlogcontent="
                + apiLogInfoBean.getExtlogcontent() +  ",logcategory="
                + apiLogInfoBean.getLogcategory() + ",logmodule="
                + apiLogInfoBean.getLogmodule() + ",actip="
                + apiLogInfoBean.getActip() + ",actmac="
                + apiLogInfoBean.getActmac() + ",status="
                + apiLogInfoBean.getStatus() + ",creator="
                + apiLogInfoBean.getCreator() + ",createTime="
                + apiLogInfoBean.getCreateTime() + ",modifier="
                + apiLogInfoBean.getModifier() + ",modifyTime="
                + apiLogInfoBean.getModifyTime() + ",att1="
                + apiLogInfoBean.getAtt1() + ",att2=" 
                + apiLogInfoBean.getAtt2() + ",att3=" 
                + apiLogInfoBean.getAtt3());
        /*
         * Do query. 
         */
        DataGrid<ApiLogInfoBean> dateGrid= queryService.queryApiLogByParameter(apiLogDto);
        
        if(log.isDebugEnabled())
        {
            log.debug("ApiLogList.size"+dateGrid.getRows().size());
        }
        
        /*
         * Check if DB have record. 
         */
        if( dateGrid.getRows().size()>0 ){
            /*
             * For edit. 
             */
            request.getSession().setAttribute("uuid", apiLogSearchParameter.getUuid_equ());
            request.setAttribute("apiLogInfoBean", dateGrid.getRows().get(0));
            
            ApiLogInfoBean apiLogInfoBean2 = dateGrid.getRows().get(0);
            log.info("queryApiLog:" +"uuid" 
                    + apiLogInfoBean2.getUuid()+ "logcode=" 
                    + apiLogInfoBean2.getLogcode() + ",loglevel=" 
                    + apiLogInfoBean2.getLoglevel() + ",logtitle="
                    + apiLogInfoBean2.getLogtitle() + ",logcontent="
                    + apiLogInfoBean2.getLogcontent() + ",extlogcode="
                    + apiLogInfoBean2.getExtlogcode() + ",extlogcontent="
                    + apiLogInfoBean2.getExtlogcontent() +  ",logcategory="
                    + apiLogInfoBean2.getLogcategory() + ",logmodule="
                    + apiLogInfoBean2.getLogmodule() + ",actip="
                    + apiLogInfoBean2.getActip() + ",actmac="
                    + apiLogInfoBean2.getActmac() + ",status="
                    + apiLogInfoBean2.getStatus() + ",creator="
                    + apiLogInfoBean2.getCreator() + ",createTime="
                    + apiLogInfoBean2.getCreateTime() + ",modifier="
                    + apiLogInfoBean2.getModifier() + ",modifyTime="
                    + apiLogInfoBean2.getModifyTime() + ",att1="
                    + apiLogInfoBean2.getAtt1() + ",att2=" 
                    + apiLogInfoBean2.getAtt2() + ",att3=" 
                    + apiLogInfoBean2.getAtt3());
        }else{
            /*
             * For add. 
             */
            request.getSession().setAttribute("uuid", "");
        }
        
        log.info("end [fowardEditApiLog] method");
        return new ModelAndView(foward);
    }
    
    /**
     * add itsmorder
     **/
    
    @RequestMapping(value="/addOrEditApiLog")
    public ModelAndView addOrEditApiLog(HttpServletRequest request,ApiLogInfoBean apiLogInfoBean) 
    {
        log.info("start [addOrEditApiLog] method");
        
        String foward = "admin/apilog/InsertApiLog";
       
        Timestamp curTime = new Timestamp(System.currentTimeMillis());
        
        Object uuidObj=request.getSession().getAttribute("uuid");
        
        if( BaseUtil.getInstance().isEmpty(uuidObj) ){
            /*
             * For add. 
             */
//            Timestamp createTime = new Timestamp(System.currentTimeMillis());
//            apiLogInfoBean.setCreateTime(TimeUtil.getInstance().timestampToString(curTime));
            updateService.insertApiLog(apiLogInfoBean);
            
                log.info("insertApiLog:" + "uuid"
                        +uuidObj.toString() +"logcode=" 
                        + apiLogInfoBean.getLogcode() + ",loglevel=" 
                        + apiLogInfoBean.getLoglevel() + ",logtitle="
                        + apiLogInfoBean.getLogtitle() + ",logcontent="
                        + apiLogInfoBean.getLogcontent() + ",extlogcode="
                        + apiLogInfoBean.getExtlogcode() + ",extlogcontent="
                        + apiLogInfoBean.getExtlogcontent() +  ",logcategory="
                        + apiLogInfoBean.getLogcategory() + ",logmodule="
                        + apiLogInfoBean.getLogmodule() + ",actip="
                        + apiLogInfoBean.getActip() + ",actmac="
                        + apiLogInfoBean.getActmac() + ",status="
                        + apiLogInfoBean.getStatus() + ",creator="
                        + apiLogInfoBean.getCreator() + ",createTime="
                        + apiLogInfoBean.getCreateTime() + ",modifier="
                        + apiLogInfoBean.getModifier() + ",modifyTime="
                        + apiLogInfoBean.getModifyTime() + ",att1="
                        + apiLogInfoBean.getAtt1() + ",att2=" 
                        + apiLogInfoBean.getAtt2() + ",att3=" 
                        + apiLogInfoBean.getAtt3());
            
        }else{
            /*
             * For edit. 
             */
//        	apiLogInfoBean.setModifyTime(TimeUtil.getInstance().timestampToString(curTime));
            
        	ApiLogSearchParameter apiLogSearchParameter=new ApiLogSearchParameter();
        	apiLogSearchParameter.setUuid_equ((String)uuidObj);
            if(log.isDebugEnabled())
            {
                log.debug("uuid:"+uuidObj.toString());
            }
        	ApiLogDto apiLogDto = new ApiLogDto();
        	apiLogDto.setApiLogSearchParameter(apiLogSearchParameter);
        	apiLogDto.setApiLogInfoBean(apiLogInfoBean);
            updateService.batchModifyApiLog(apiLogDto);

                log.info("ModifyApiLog:" + "uuid"
                        +uuidObj.toString() + "logcode=" 
                        + apiLogInfoBean.getLogcode() + ",loglevel=" 
                        + apiLogInfoBean.getLoglevel() + ",logtitle="
                        + apiLogInfoBean.getLogtitle() + ",logcontent="
                        + apiLogInfoBean.getLogcontent() + ",extlogcode="
                        + apiLogInfoBean.getExtlogcode() + ",extlogcontent="
                        + apiLogInfoBean.getExtlogcontent() +  ",logcategory="
                        + apiLogInfoBean.getLogcategory() + ",logmodule="
                        + apiLogInfoBean.getLogmodule() + ",actip="
                        + apiLogInfoBean.getActip() + ",actmac="
                        + apiLogInfoBean.getActmac() + ",status="
                        + apiLogInfoBean.getStatus() + ",creator="
                        + apiLogInfoBean.getCreator() + ",createTime="
                        + apiLogInfoBean.getCreateTime() + ",modifier="
                        + apiLogInfoBean.getModifier() + ",modifyTime="
                        + apiLogInfoBean.getModifyTime() + ",att1="
                        + apiLogInfoBean.getAtt1() + ",att2=" + apiLogInfoBean.getAtt2()
                        + ",att3=" + apiLogInfoBean.getAtt3());            
        }
        
        log.info("end [addOrEditApiLog] method");
        return new ModelAndView(foward);
    }

}
