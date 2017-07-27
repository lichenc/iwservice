package com.iws.service;

import com.iws.pojo.apilog.ApiLogDto;
import com.iws.pojo.apilog.ApiLogInfoBean;
import com.iws.pojo.itsmorder.ItsmOrderDto;
import com.iws.pojo.itsmorder.ItsmOrderInfoBean;
import com.iws.pojo.sysconf.SysConfDto;
import com.iws.pojo.sysconf.SysConfInfoBean;
import com.iws.util.DataGrid;

public interface QueryService {

    public DataGrid<ItsmOrderInfoBean> queryItsmOrderByParameter(ItsmOrderDto itsmOrderDto);

    public DataGrid<ApiLogInfoBean> queryApiLogByParameter(ApiLogDto apiLogDto);

    public DataGrid<SysConfInfoBean> querySysConfByParameter(SysConfDto sysConfDto);

    public String querySysConfStringByKey(String CfgCategory,String CfgGroup,String CfgKey);
    
    public int querySysConfIntByKey(String CfgCategory,String CfgGroup,String CfgKey);
    
    public boolean querySysConfBooleanByKey(String CfgCategory,String CfgGroup,String CfgKey);
	
	 public List<String> queryOrId();
}
