package com.iws.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class TimeUtil {
	


	private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private static TimeUtil instance = null;

	private TimeUtil() {
	}

	public static TimeUtil getInstance() {
		if (instance == null) {
			instance = new TimeUtil();
		}
		return instance;
	}

	public Timestamp getCurrentTimestamp() {

		return new Timestamp(System.currentTimeMillis());
	}

	public String timestampToString(Timestamp timestamp) {
		if (timestamp != null) {
			return df.format(timestamp);
		} else {
			return "";
		}

	}

	public Timestamp stringToTimestamp(String curStr) {
		
		/*if (!BaseUtil.getInstance().isEmpty(curStr)) {

//			String yyyy = curStr.split(" ")[0].split("/")[2];
//			String MM = curStr.split(" ")[0].split("/")[0];
//			String dd = curStr.split(" ")[0].split("/")[1];
//			String HHmmss = curStr.split(" ")[1];

			return Timestamp.valueOf(curStr);

		} else {
			return null;
		}
		*/
		return null;
	}
}
