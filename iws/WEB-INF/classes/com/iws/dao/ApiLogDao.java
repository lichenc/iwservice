package com.iws.dao;

import java.util.List;

import com.iws.pojo.apilog.ApiLogDto;
import com.iws.pojo.apilog.ApiLogInfoBean;


public interface ApiLogDao {
	
	   public List<ApiLogInfoBean> queryApiLogByParameter(ApiLogDto apiLogDto); //��η���ֻҪ���ֶΣ�
	    
	    public int count(ApiLogDto apiLogDto);
	    
	    public void insertApiLog(ApiLogInfoBean apiLogInfoBean);
	    
	    public void batchModifyApiLog(ApiLogDto apiLogDto);//����жϱ���ҪUUID������serveic���ж�
	    
	    public void deleteapilogByUUID(ApiLogInfoBean apiLogInfoBean);//����жϱ���ҪUUID�� ����serveic���ж�//δ���
}
