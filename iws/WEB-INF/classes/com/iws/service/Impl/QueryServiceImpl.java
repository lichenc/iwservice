package com.iws.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iws.dao.ApiLogDao;
import com.iws.dao.ItsmOrderDao;
import com.iws.pojo.apilog.ApiLogDto;
import com.iws.pojo.apilog.ApiLogInfoBean;
import com.iws.dao.SysConfDao;
import com.iws.pojo.itsmorder.ItsmOrderDto;
import com.iws.pojo.itsmorder.ItsmOrderInfoBean;
import com.iws.pojo.sysconf.SysConfDto;
import com.iws.pojo.sysconf.SysConfInfoBean;
import com.iws.pojo.sysconf.SysConfSearchParameter;
import com.iws.service.QueryService;
import com.iws.util.DataGrid;
@Service("queryServiceImpl")
public class QueryServiceImpl implements QueryService{

    @Autowired
    private ItsmOrderDao itsmOrderDao;
    

    @Autowired
    private SysConfDao sysConfDao;
    

    @Override
    public DataGrid<ItsmOrderInfoBean> queryItsmOrderByParameter(ItsmOrderDto itsmOrderDto) {
        // TODO Auto-generated method stub
        List<ItsmOrderInfoBean> itsmOrderInfoBeanList = itsmOrderDao.queryItsmOrderByParameter(itsmOrderDto);
        int count = itsmOrderDao.count(itsmOrderDto);
        return new DataGrid<ItsmOrderInfoBean>(count, itsmOrderInfoBeanList);
    }
    
    @Autowired
    private ApiLogDao apiLogDao;
	@Override
	public DataGrid<ApiLogInfoBean> queryApiLogByParameter(ApiLogDto apiLogDto) {
		// TODO Auto-generated method stub
		  List<ApiLogInfoBean> apiLogInfoBean = apiLogDao.queryApiLogByParameter(apiLogDto);
	        int count = apiLogDao.count(apiLogDto);
	        return new DataGrid<ApiLogInfoBean>(count, apiLogInfoBean);
		
	}

	@Override
	public DataGrid<SysConfInfoBean> querySysConfByParameter(
			SysConfDto sysConfDto) {
		// TODO Auto-generated method stub
		List<SysConfInfoBean> sysConfInfoBeanList = sysConfDao.querySysConfByParameter(sysConfDto);
        int count = sysConfDao.count(sysConfDto);
        return new DataGrid<SysConfInfoBean>(count, sysConfInfoBeanList);
	}

	@Override
	public String querySysConfStringByKey(String CfgCategory, String CfgGroup,
			String CfgKey) {
		// TODO Auto-generated method stub
		String returnValu=null;
		
		SysConfSearchParameter sysConfSearchParameter  = new SysConfSearchParameter();
		sysConfSearchParameter.setCfgCategory_equ(CfgCategory);
		sysConfSearchParameter.setCfgGroup_equ(CfgGroup);
		sysConfSearchParameter.setCfgKey_equ(CfgKey);
		sysConfSearchParameter.setNumber(1);
		sysConfSearchParameter.setStart(1);
		
		SysConfInfoBean sysConfInfoBean =new SysConfInfoBean();
		sysConfInfoBean.setCfgValue("");
		
		SysConfDto sysConfDto =new SysConfDto();
		sysConfDto.setSysConfSearchParameter(sysConfSearchParameter);
		sysConfDto.setSysConfInfoBean(sysConfInfoBean);
		
		List<SysConfInfoBean> sysConfInfoBeanList = sysConfDao.querySysConfByParameter(sysConfDto);
		
		//try
		
		returnValu=sysConfInfoBeanList.get(0).getCfgValue();
		
		return returnValu;
		
	}

	@Override
	public int querySysConfIntByKey(String CfgCategory, String CfgGroup,
			String CfgKey) {
		// TODO Auto-generated method stub
		return Integer.parseInt(querySysConfStringByKey(CfgCategory,CfgGroup,CfgKey));
	}

	@Override
	public boolean querySysConfBooleanByKey(String CfgCategory, String CfgGroup,
			String CfgKey) {
		// TODO Auto-generated method stub
		return Boolean.parseBoolean(querySysConfStringByKey(CfgCategory,CfgGroup,CfgKey));
	}
	

		@Override
	public List<String> queryOrId() {
		// TODO Auto-generated method stub
		return itsmOrderDao.queryOrId();
	}
	

}
