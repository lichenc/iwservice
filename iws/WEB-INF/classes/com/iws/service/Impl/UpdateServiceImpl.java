package com.iws.service.Impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iws.dao.ApiLogDao;
import com.iws.dao.ItsmOrderDao;
import com.iws.dao.ItsmOrderHistoryDao;
import com.iws.dao.SysConfDao;
import com.iws.pojo.apilog.ApiLogDto;
import com.iws.pojo.apilog.ApiLogInfoBean;
import com.iws.pojo.history.ItsmOrderHistoryDto;
import com.iws.pojo.history.ItsmOrderHistoryInfoBean;
import com.iws.pojo.history.ItsmOrderHistorySearchParameter;
import com.iws.pojo.itsmorder.ItsmOrderDto;
import com.iws.pojo.itsmorder.ItsmOrderInfoBean;
import com.iws.pojo.itsmorder.ItsmOrderSearchParameter;
import com.iws.pojo.sysconf.SysConfDto;
import com.iws.pojo.sysconf.SysConfInfoBean;
import com.iws.service.UpdateService;
import com.iws.util.TimeUtil;
@Service("updateServiceImpl")
public class UpdateServiceImpl implements UpdateService {

    @Autowired
    private ItsmOrderDao itsmOrderDao;
    
    @Autowired
    private SysConfDao sysConfDao;
    
    @Autowired
    private ItsmOrderHistoryDao itsmOrderHistoryDao;
    
    @Autowired
    private ApiLogDao apiLogDao;
    
    @Override
    public void insertItsmOrder(ItsmOrderInfoBean itsmOrderInfoBean) {
        // TODO Auto-generated method stub
        itsmOrderDao.insertItsmOrder(itsmOrderInfoBean);
    }
    
    @Override
	public void batchInsertItsmOrder(List<ItsmOrderInfoBean> itsmOrderInfoBeans) {
		// TODO Auto-generated method stub
		for (ItsmOrderInfoBean itsmOrderInfoBean : itsmOrderInfoBeans) {
			 itsmOrderDao.insertItsmOrder(itsmOrderInfoBean);
		}
	}

    @Override
    public void batchDeleteItsmOrder(List<String> uuIds) {
        // TODO Auto-generated method stub
        
        //Limit???
        
        for (String uuid : uuIds) {
            ItsmOrderInfoBean itsmOrderInfoBean = new ItsmOrderInfoBean();
            itsmOrderInfoBean.setUuid(uuid);
            itsmOrderDao.deleteItsmOrderByUUID(itsmOrderInfoBean);
        }
        
    }
    @Override
   	public void deleteItsmOrderByorId(List<ItsmOrderInfoBean> itsmOrderInfoBeans) {
   		// TODO Auto-generated method stub
    	for (ItsmOrderInfoBean itsmOrderInfoBean : itsmOrderInfoBeans) {
    		itsmOrderDao.deleteItsmOrderByorId(itsmOrderInfoBean);
    	}	
   }


    @Override
    public void batchModifyItsmOrder(ItsmOrderDto itsmOrderDto) {
        // TODO Auto-generated method stub
        itsmOrderDao.batchModifyItsmOrder(itsmOrderDto);
    }
    
    @Override
	public void insertSysConf(SysConfInfoBean sysConfInfoBean) {
		sysConfDao.insertSysConf(sysConfInfoBean);
	}

	@Override
	public void batchDeleteSysConf(List<String> uuIds) {
		for (String uuid : uuIds) {
			SysConfInfoBean sysConfInfoBean = new SysConfInfoBean();
			sysConfInfoBean.setUuid(uuid);
			sysConfDao.deleteSysConfByUUID(sysConfInfoBean);
		}
	}

	@Override
	public void batchModifySysConf(SysConfDto sysConfDto) {
		sysConfDao.batchModifySysConf(sysConfDto);
	}

    @Override
    public int backupItsmOrderTable(int number,String createTimeBefore) {

        int returnInt = 0;

        /*
         * Search parameter. 
         */
        ItsmOrderSearchParameter itsmOrderSearchParameter = new ItsmOrderSearchParameter();
        itsmOrderSearchParameter.setStart(0);
        itsmOrderSearchParameter.setNumber(number);
        itsmOrderSearchParameter.setCreateTimeBefore(createTimeBefore);

        /*
         * Return bean. 
         */
        ItsmOrderInfoBean itsmOrderInfoBean = new ItsmOrderInfoBean();
        itsmOrderInfoBean.setUuid("");
        itsmOrderInfoBean.setOrId("");
        itsmOrderInfoBean.setOrCategory("");
        itsmOrderInfoBean.setCreateTime("");      
        itsmOrderInfoBean.setTitle("");
        itsmOrderInfoBean.setWorkContent("");
        itsmOrderInfoBean.setApplicant("");
        itsmOrderInfoBean.setApplyTime("");
        itsmOrderInfoBean.setPerformer("");
        itsmOrderInfoBean.setPerformTime("");
        itsmOrderInfoBean.setIsowner("");
        itsmOrderInfoBean.setBusinessSystem("");
        itsmOrderInfoBean.setPerformMode("");
        itsmOrderInfoBean.setAnnotation("");
        itsmOrderInfoBean.setDataState("");
        itsmOrderInfoBean.setStatus("");
        itsmOrderInfoBean.setCreator("");
        itsmOrderInfoBean.setCreateTime("");
        itsmOrderInfoBean.setModifier("");
        itsmOrderInfoBean.setModifyTime("");
        itsmOrderInfoBean.setAtt1("");
        itsmOrderInfoBean.setAtt2("");
        itsmOrderInfoBean.setAtt3("");

        /*
         * Collect dto. 
         */
        ItsmOrderDto curItsmOrderDto = new ItsmOrderDto();
        curItsmOrderDto.setItsmOrderSearchParameter(itsmOrderSearchParameter);
        curItsmOrderDto.setItsmOrderInfoBean(itsmOrderInfoBean);

        /*
         * Do query.  
         */
        List<ItsmOrderInfoBean> itsmOrderInfoBeanList = itsmOrderDao
                .queryItsmOrderByParameter(curItsmOrderDto);
//        System.out.println(itsmOrderInfoBeanList.size()+":**///**///**//");
        
        /*
         * For each return list. 
         */
        Timestamp curBackupTimestamp = new Timestamp(System.currentTimeMillis());
        for (ItsmOrderInfoBean curitsmOrderInfoBean : itsmOrderInfoBeanList) {
//            System.out.println("********555*****");

            
            //here need to modify. 
            
            /*
             * Generate history record. 
             */
        	ItsmOrderHistoryInfoBean curItsmOrderHistory4Insert = new ItsmOrderHistoryInfoBean();
            curItsmOrderHistory4Insert.setUuid(curitsmOrderInfoBean.getUuid());
            curItsmOrderHistory4Insert.setOrId(curitsmOrderInfoBean.getOrId());
            curItsmOrderHistory4Insert.setOrCategory(curitsmOrderInfoBean.getOrCategory());
            curItsmOrderHistory4Insert.setTitle(curitsmOrderInfoBean.getTitle());
            curItsmOrderHistory4Insert.setWorkContent(curitsmOrderInfoBean.getWorkContent());
            curItsmOrderHistory4Insert.setApplicant(curitsmOrderInfoBean.getApplicant());
            curItsmOrderHistory4Insert.setApplyTime(curitsmOrderInfoBean.getApplyTime());
            curItsmOrderHistory4Insert.setPerformer(curitsmOrderInfoBean.getPerformer());
            curItsmOrderHistory4Insert.setPerformTime(curitsmOrderInfoBean.getPerformTime());
            curItsmOrderHistory4Insert.setIsowner(curitsmOrderInfoBean.getIsowner());
            curItsmOrderHistory4Insert.setBusinessSystem(curitsmOrderInfoBean.getBusinessSystem());
            curItsmOrderHistory4Insert.setPerformMode(curitsmOrderInfoBean.getPerformMode());
            curItsmOrderHistory4Insert.setAnnotation(curitsmOrderInfoBean.getAnnotation());
            curItsmOrderHistory4Insert.setDataState(curitsmOrderInfoBean.getDataState());
            curItsmOrderHistory4Insert.setStatus(curitsmOrderInfoBean.getStatus());
            curItsmOrderHistory4Insert.setCreator(curitsmOrderInfoBean.getCreator());
            curItsmOrderHistory4Insert.setCreateTime(curitsmOrderInfoBean.getCreateTime());
            curItsmOrderHistory4Insert.setModifier(curitsmOrderInfoBean.getModifier());
            curItsmOrderHistory4Insert.setModifyTime(curitsmOrderInfoBean.getModifyTime());
            curItsmOrderHistory4Insert.setAtt1(curitsmOrderInfoBean.getAtt1());
            curItsmOrderHistory4Insert.setAtt2(curitsmOrderInfoBean.getAtt2());
            curItsmOrderHistory4Insert.setAtt3(curitsmOrderInfoBean.getAtt3());

            curItsmOrderHistory4Insert.setBackupTime(TimeUtil.getInstance()
                    .timestampToString(curBackupTimestamp));

            /*
             * Insert history record. 
             * 
             */
            itsmOrderHistoryDao.insertItsmOrderHistory(curItsmOrderHistory4Insert);
            
            /*
             * Delete old record. 
             */
            itsmOrderDao.deleteItsmOrderByUUID(curitsmOrderInfoBean);

            returnInt++;
        }
        
        return returnInt;
    }

    @Override
    public int cleanItsmOrderHistoryTable(int number,String createTimeBefore) {
        // TODO Auto-generated method stub
        int returnInt = 0;
        
        /*
         * Search parameter. 
         */
        ItsmOrderHistorySearchParameter itsmOrderHistorySearchParameter = new ItsmOrderHistorySearchParameter();
        itsmOrderHistorySearchParameter.setStart(0);
        itsmOrderHistorySearchParameter.setNumber(number);
        itsmOrderHistorySearchParameter.setCreateTimeBefore(createTimeBefore);

        /*
         * Return bean. 
         */
        ItsmOrderHistoryInfoBean itsmOrderHistoryInfoBean = new ItsmOrderHistoryInfoBean();
        itsmOrderHistoryInfoBean.setUuid("");

        /*
         * Collect dto. 
         */
        ItsmOrderHistoryDto curItsmOrderHistoryDto = new ItsmOrderHistoryDto();
        curItsmOrderHistoryDto
                .setItsmOrderHistorySearchParameter(itsmOrderHistorySearchParameter);
        curItsmOrderHistoryDto
                .setItsmOrderHistoryInfoBean(itsmOrderHistoryInfoBean);

        
        /*
         * Do query record. 
         */
        List<ItsmOrderHistoryInfoBean> queryItsmOrderHistoryList = itsmOrderHistoryDao
                .queryItsmOrderHistoryByParameter(curItsmOrderHistoryDto);
        /*
         * For each return list. 
         */
        for (ItsmOrderHistoryInfoBean itsmOrderHistory : queryItsmOrderHistoryList) {
            System.out.println("=================7");
            
            /*
             * Do delete. 
             */
            itsmOrderHistoryDao.deleteItsmOrderHistoryByUUID(itsmOrderHistory);

            returnInt++;
        }

        return returnInt;
    }
  
    

	@Override
	public void insertApiLog(ApiLogInfoBean apiLogfoBean) {
		// TODO Auto-generated method stub
		apiLogDao.insertApiLog(apiLogfoBean);
		
	}

	@Override
	public void deleteapilogByUUID(List<String> uuIds) {
		// TODO Auto-generated method stub
	    
        for (String uuid : uuIds) {
        	ApiLogInfoBean apiLogInfoBean = new ApiLogInfoBean();
        	apiLogInfoBean.setUuid(uuid);         
            apiLogDao.deleteapilogByUUID(apiLogInfoBean);
        }
        
		
	}

	@Override
	public void batchModifyApiLog(ApiLogDto apiLogDto) {
		// TODO Auto-generated method stub
		apiLogDao.batchModifyApiLog(apiLogDto);
	}

	

	

}
