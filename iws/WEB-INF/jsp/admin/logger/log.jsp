<%@page import="java.util.Collections"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Log页面</title>
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/css/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/css/themes/icon.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/demo.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/easyDialog/easydialog.css">
<script type="text/javascript" src="<%=basePath%>/js/jquery.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>/js/jquery.easyui.min.js"></script>	 	
<script type="text/javascript"
	src="<%=basePath%>/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript"
	src="<%=basePath%>/easyDialog/easydialog.min.js"></script>
<style type="text/css">
	html,body {
		height: 0px;
	}
	
	textarea
   {
    width:100%;
    height:100%;
   }
</style>

</head>
<body onload="hideAdvanced()">
	<div class="easyui-layout" id="subWrap" fit="true" style="width:100%;height:100%;">
		<div region="north" border="true" title="查询条件" split="false" style="height:209px;" data-options="iconCls:'icon-search'">
			
			<form action="<%=basePath%>/log/fowardQueryLog.do" id="queryFormId"
				method="post">
				<table cellpadding="1" style="margin-left:20px;margin-top:50px;">
					<tr>
						<td>显示行数:</td>
						<td><select class="easyui-combobox" name="limit"
							style="width: 250px; height: 26px;">
								<option value="1000" ${limit == 1000 ? 'selected = "selected"' : '' }>1000</option>
								<option value="2000" ${limit == 2000 ? 'selected = "selected"' : '' }>2000</option>
								<option value="3000" ${limit == 3000 ? 'selected = "selected"' : '' }>3000</option>
		<%-- 						<option value="5000" ${limit == 5000 ? 'selected = "selected"' : '' }>5000</option> --%>
						</select></td>
					</tr>
					
					<tr>
						<td><button class="easyui-linkbutton" type="button" value="查询"
								onclick="submitQuery()" data-options="iconCls:'icon-search'"
								style="width: 80px; height: 25px;">查询</button></td>
					</tr>
				</table>
			</form>
		</div>
		<div region="center" border="true" title="查询结果" split="true"  data-options="iconCls:'icon-ok'" style="height: 200px;">
		<table cellpadding="1">
			<tr>
				<td>
					<textarea id="logtextarea" name="textarea" cols="235" rows="47" >
<%
	List<String> logList=(ArrayList)request.getAttribute("list");
	Collections.reverse(logList);
	for (String curLine: logList) {
		%><%=curLine %>
<%
	}
%>
</textarea>
				</td>
			</tr>
		</table>	
		<!-- <div>
			<hr align="left" style="border: 1 dashed #EBEBEB" width="98.5%"
				color=#EBEBEB size=1 />
		</div> -->
	</div>
	</div>

	<script type="text/javascript">
		function doing(){
			easyDialog.open({
			  container : {
			    content : '正在查询当前日志，请稍后...',
			  },
			});
		}
		
		function submitQuery(){
			doing();
			$('#queryFormId').submit();			
			return true;			
		}
		
		function hideAdvanced(){
			for(var i=0;i<4;i++){
				$("#advanced_"+i).hide();
			}
			$("#advanced").html("展开<<");
			isDisplay = false;
		}
		
		function showAdvanced(){
			for(var i=0;i<4;i++){
				$("#advanced_"+i).show();
			}
			$("#advanced").html("收起>>");
			isDisplay = true;
		}
		
		function displayChange(){
			if(isDisplay){
				hideAdvanced();
			}else{
				showAdvanced();
			}
		}
		
		 /* var logtextareaObj = document.getElementById("logtextarea");  
		logtextareaObj.scrollTop = logtextareaObj.scrollHeight;  */
		
	</script>
</body>
</html>