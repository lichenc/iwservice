package com.iws.dao;

import java.util.List;

import com.iws.pojo.itsmorder.ItsmOrderDto;
import com.iws.pojo.itsmorder.ItsmOrderInfoBean;


public interface ItsmOrderDao {
    
    public List<ItsmOrderInfoBean> queryItsmOrderByParameter(ItsmOrderDto itsmOrderDto); //��η���ֻҪ���ֶΣ�
    
    public int count(ItsmOrderDto itsmOrderDto);
    
    public void insertItsmOrder(ItsmOrderInfoBean itsmOrderInfoBean);
    
    public void batchModifyItsmOrder(ItsmOrderDto itsmOrderDto);//����жϱ���ҪUUID������serveic���ж�
    
    public void deleteItsmOrderByUUID(ItsmOrderInfoBean itsmOrderInfoBean);//����жϱ���ҪUUID�� ����serveic���ж�//δ���
  
    public boolean deleteItsmOrderByorId(ItsmOrderInfoBean itsmOrderInfoBean); //
	
	public List<String> queryOrId();
}
