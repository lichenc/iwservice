package com.iws.dao;

import java.util.List;

import com.iws.pojo.history.ItsmOrderHistoryDto;
import com.iws.pojo.history.ItsmOrderHistoryInfoBean;



public interface ItsmOrderHistoryDao {
    
	 public List<ItsmOrderHistoryInfoBean> queryItsmOrderHistoryByParameter(ItsmOrderHistoryDto itsmOrderHistoryDto);
	    
	    public int count(ItsmOrderHistoryDto itsmOrderHistoryDto);
	    
	    public void insertItsmOrderHistory(ItsmOrderHistoryInfoBean itsmOrderHistoryInfoBean);
	    
	    public void batchModifyItsmOrderHistory(ItsmOrderHistoryDto itsmOrderHistoryDto);
	    
	    public void deleteItsmOrderHistoryByUUID(ItsmOrderHistoryInfoBean itsmOrderHistoryInfoBean);
	  
	    public void batchDeleteItsmOrderHistoryByUUID(List<String> uuId); 
}
