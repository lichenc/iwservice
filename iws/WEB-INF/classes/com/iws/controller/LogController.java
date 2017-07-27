package com.iws.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Appender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.iws.dao.SysConfDao;
import com.iws.pojo.sysconf.SysConfDto;
import com.iws.pojo.sysconf.SysConfInfoBean;
import com.iws.pojo.sysconf.SysConfSearchParameter;
import com.iws.service.QueryService;
import com.iws.service.Impl.QueryServiceImpl;
import com.iws.thread.HouseKeepingThread;
import com.iws.util.BaseUtil;

@Controller
@RequestMapping(value = "/log")
public class LogController {

	
	@Autowired
    private SysConfDao sysConfDao;
	@Autowired
    private QueryService queryService;
	
	@RequestMapping(value = "/fowardQueryLog")
	public ModelAndView fowardLog(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ParseException {

		String foward = "admin/logger/log";
		String limit = request.getParameter("limit");

		List<String> returnList = new ArrayList<String>();

		if (!BaseUtil.getInstance().isEmpty(limit)) {
			int readlines = Integer.parseInt(limit);
			if (readlines > 0) {
				
				String appenderName=queryService.querySysConfStringByKey("log", "log4j", "appender");								
				Appender appender = Logger.getRootLogger().getAppender(appenderName);					
				if (appender instanceof FileAppender) {
		            FileAppender curFileAppender = (FileAppender) appender;
		            String fileName=curFileAppender.getFile();
		            returnList = getLinesFromLast(fileName,readlines);
		            
			}
			}
		
		}

//		System.out.println( "===============>:returnList"+returnList );
		
		request.setAttribute("limit", limit);
		return new ModelAndView(foward, "list", returnList);

	}

	public static List<String> getLinesFromLast(String fileName, int readlines)
			throws FileNotFoundException, IOException, ParseException {

		List<String> returnlist = new ArrayList<String>();

		RandomAccessFile rf = new RandomAccessFile(fileName, "r");

		long fileLen = rf.length();
		long startPoint = rf.getFilePointer();
		long endPoint = startPoint + fileLen - 1;

		// To end point
		rf.seek(endPoint);

		int endFlag = -1;
		while (endPoint > startPoint) {
			endFlag = rf.read();
			if (endFlag == '\n' || endFlag == '\r') {
				String line = rf.readLine();
				if (readlines > 0) {
					// 向前读lines行
//					System.out.println( "line>>:"+line );
					returnlist.add(line);
					readlines--;
				}
				endPoint--;
			}

			// ???
			endPoint--;

			if (readlines == 0) {
				break;
			}

			// Move
			rf.seek(endPoint);

			// 当文件指针退至文件开始处，输出第一行
			if (endPoint == 0) {
				String line = rf.readLine();
				if (readlines == 1) {
					returnlist.add(line);
				}
			}
		}

		rf.close();
		return returnlist;
	}

}
