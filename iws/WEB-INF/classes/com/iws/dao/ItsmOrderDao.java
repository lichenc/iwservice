package com.iws.dao;

import java.util.List;

import com.iws.pojo.itsmorder.ItsmOrderDto;
import com.iws.pojo.itsmorder.ItsmOrderInfoBean;


public interface ItsmOrderDao {
    
    public List<ItsmOrderInfoBean> queryItsmOrderByParameter(ItsmOrderDto itsmOrderDto); //如何返回只要的字段？
    
    public int count(ItsmOrderDto itsmOrderDto);
    
    public void insertItsmOrder(ItsmOrderInfoBean itsmOrderInfoBean);
    
    public void batchModifyItsmOrder(ItsmOrderDto itsmOrderDto);//如何判断必须要UUID？或者serveic层判断
    
    public void deleteItsmOrderByUUID(ItsmOrderInfoBean itsmOrderInfoBean);//如何判断必须要UUID？ 或者serveic层判断//未完成
  
    public boolean deleteItsmOrderByorId(ItsmOrderInfoBean itsmOrderInfoBean); //
	
	public List<String> queryOrId();
}
