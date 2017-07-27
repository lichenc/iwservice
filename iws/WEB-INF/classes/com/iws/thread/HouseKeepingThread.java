package com.iws.thread;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.Resource;

import com.iws.pojo.sysconf.SysConfInfoBean;
import com.iws.service.QueryService;
import com.iws.service.UpdateService;
import com.iws.util.TimeUtil;


public class HouseKeepingThread {

	@Resource(name = "updateServiceImpl")
	private UpdateService updateService;
	@Resource(name = "queryServiceImpl")
	private QueryService queryService;

	public void backupItsmOrderTable() {
		
		
		int batchCounts = queryService.querySysConfIntByKey("thread","backup","batchcounts");
		
		int looptime = queryService.querySysConfIntByKey("thread","backup","looptime");
		String createTimeBefore=getTimestampFromLooptime(looptime);
		
		int backupItsmOrderTableCount = -1;
		do {
			backupItsmOrderTableCount = updateService
					.backupItsmOrderTable(batchCounts,createTimeBefore);

		} while (backupItsmOrderTableCount > 0);
	}

	public void cleanItsmOrderHistoryTable() {
		
		int batchCounts = queryService.querySysConfIntByKey("thread","clean","batchcounts");
		
		int looptime = queryService.querySysConfIntByKey("thread","clean","looptime");
		String createTimeBefore=getTimestampFromLooptime(looptime);
		
		int cleanItsmOrderTableCount = -1;
		
		do {
			cleanItsmOrderTableCount = updateService
					.cleanItsmOrderHistoryTable(batchCounts,createTimeBefore);

		} while (cleanItsmOrderTableCount > 0);
	}

	
	public String getTimestampFromLooptime(int looptime){
		 GregorianCalendar gc = new GregorianCalendar();
	        gc.setTime(new Date());
	        gc.add(Calendar.MONTH, -looptime);    
		return TimeUtil.getInstance()
                .timestampToString(new Timestamp(gc.getTimeInMillis()));
	}
	


}
