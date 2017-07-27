package com.iws.service;

import java.util.List;

import com.iws.pojo.apilog.ApiLogDto;
import com.iws.pojo.apilog.ApiLogInfoBean;
import com.iws.pojo.itsmorder.ItsmOrderDto;
import com.iws.pojo.itsmorder.ItsmOrderInfoBean;
import com.iws.pojo.sysconf.SysConfDto;
import com.iws.pojo.sysconf.SysConfInfoBean;

public interface UpdateService {

    public void insertItsmOrder(ItsmOrderInfoBean itsmOrderInfoBean);
    
    public void batchInsertItsmOrder(List<ItsmOrderInfoBean> itsmOrderInfoBeans);

    public void batchDeleteItsmOrder(List<String> uuIds);
    
//    public boolean deleteItsmOrderByorId(ItsmOrderInfoBean itsmOrderInfoBean);
    public void deleteItsmOrderByorId(List<ItsmOrderInfoBean> itsmOrderInfoBeans);

    public void batchModifyItsmOrder(ItsmOrderDto itsmOrderDto);
    
    public void insertSysConf(SysConfInfoBean sysConfInfoBean);

    public void batchDeleteSysConf(List<String> uuIds);
    

    public void batchModifySysConf(SysConfDto sysConfDto);
    
    //ApiLog的接口
    public void insertApiLog(ApiLogInfoBean apiLogfoBean);

    public void deleteapilogByUUID(List<String> uuIds);

    public void batchModifyApiLog(ApiLogDto apiLogDto);
    
    public int backupItsmOrderTable(int number,String createTimeBefore);

    public int cleanItsmOrderHistoryTable(int number,String createTimeBefore);
    
}
