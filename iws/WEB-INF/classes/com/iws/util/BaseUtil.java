package com.iws.util;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

public class BaseUtil {
	private static final Logger log = Logger.getLogger(BaseUtil.class);

	private static BaseUtil instance = null;

	private BaseUtil() {
	}

	public static BaseUtil getInstance() {
		if (instance == null) {
			instance = new BaseUtil();
		}
		return instance;
	}

	public boolean isEmpty(Object curObj) {

		boolean returnBoolean = false;
		if (curObj == null) {
			returnBoolean = true;
		} else {
			if ("".equals(curObj.toString())) {
				returnBoolean = true;
			}
		}

		return returnBoolean;
	}
}
