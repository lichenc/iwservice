package com.iws.dao;

import java.util.List;

import com.iws.pojo.sysconf.SysConfDto;
import com.iws.pojo.sysconf.SysConfInfoBean;


public interface SysConfDao {
    
    public List<SysConfInfoBean> querySysConfByParameter(SysConfDto sysConfDto); //
    
    public int count(SysConfDto sysConfDto);
    
    public void insertSysConf(SysConfInfoBean confInfoBean);
    
    public void batchModifySysConf(SysConfDto sysConfDto);//
    
    public void deleteSysConfByUUID(SysConfInfoBean confInfoBean);//
  
    
}
