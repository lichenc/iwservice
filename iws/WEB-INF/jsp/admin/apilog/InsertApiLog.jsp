<%@page import="java.util.Iterator"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.HashMap"%>
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
<title>编辑ApiLog页面</title>



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

<script type="text/javascript">
	
	function canSave() {
		if (confirm('是否确认保存信息?')) {
			easyDialog.open({
				container : {
					content : '正在执行操作，请稍后...',
				},
			});		
			return true;
			
		} else {
			return false;
		}
	}
</script>
</head>
<body>
	<form id="clearFrom" action="<%=basePath%>/apilog/addOrEditApiLog.do" method="post"
		name="addForm" onsubmit="return canSave();">
		<table cellpadding="1">
			

			<input type="hidden" id="uuid" name="uuid" value='${apiLogInfoBean.uuid}' />

			<tr>
				<td><font color="red">*</font>日志编号:</td>
				<td><input id="logcode" class="easyui-textbox" name="logcode"
					required="required" style="width: 250px; height: 26px;"
					value="${apiLogInfoBean.logcode}" />(必填)</td>
			</tr>
			<tr>
				<td><font color="red">*</font>日志级别:</td>
				<td><input id="loglevel" class="easyui-textbox"
					name="loglevel" 
					style="width: 250px; height: 26px;"
					value="${apiLogInfoBean.loglevel}" />(必填)</td>
			</tr>
			<tr>
				<td><font color="red">*</font>日志标题:</td>
				<td><input id="logtitle" class="easyui-textbox" name="logtitle"
					 style="width: 250px; height: 26px;"
					value="${apiLogInfoBean.logtitle}" />(必填)</td>
			</tr>
			<tr>
				<td><font color="red">*</font>日志内容:</td>
				<td><textarea id="logcontent" name="logcontent" cols="30" rows="10">
${apiLogInfoBean.logcontent}</textarea></td>
			</tr>
			<tr>
				<td><font color="red">*</font>扩展日志代码:</td>
				<td><input id="extlogcode" class="easyui-textbox"
					name="extlogcode" 
					style="width: 250px; height: 26px;"
					value="${apiLogInfoBean.extlogcode}" />(必填)</td>
			</tr>
			<tr>
				<td><font color="red">*</font>扩展日志内容:</td>
				<td><textarea id="extlogcontent" name="extlogcontent" cols="30" rows="10">
${apiLogInfoBean.extlogcontent}</textarea></td>
			</tr>
			<tr>
				<td><font color="red">*</font>日志类型:</td>
				<td><input id="logcategory" class="easyui-textbox"
					name="logcategory" 
					style="width: 250px; height: 26px;"
					value="${apiLogInfoBean.logcategory}" />(必填)</td>
			</tr>
			<tr>
				<td><font color="red">*</font>模块:</td>
				<td><input id="logmodule" class="easyui-textbox" name="logmodule"
					 style="width: 250px; height: 26px;"
					value="${apiLogInfoBean.logmodule}" />(必填)</td>
			</tr>
			<tr>
				<td><font color="red">*</font>运行程序的ip地址:</td>
				<td><input id="actip" class="easyui-textbox"
					name="actip" 
					style="width: 250px; height: 26px;"
					value="${apiLogInfoBean.actip}" />(必填)</td>
			</tr>
			<tr>
				<td><font color="red">*</font>运行程序的mac地址:</td>
				<td><input id="actmac" class="easyui-textbox"
					name="actmac" 
					style="width: 250px; height: 26px;"
					value="${apiLogInfoBean.actmac}" />(必填)</td>
			</tr>
<!-- 			<tr> -->
<!-- 				<td><font color="red">*</font>状态:</td> -->
<!-- 				<td><input id="status" class="easyui-textbox" -->
<!-- 					name="status"  -->
<!-- 					style="width: 250px; height: 26px;" -->
<%-- 					value="${apiLogInfoBean.status}" />(必填)</td> --%>
<!-- 			</tr> -->
			<tr>
				<td><font color="red">*</font>创建者:</td>
				<td><input id="creator" class="easyui-textbox"
					name="creator" 
					style="width: 250px; height: 26px;"
					value="${apiLogInfoBean.creator}" />(必填)</td>
			</tr>
			<tr>
			<td><font color="red">*</font>创建时间:</td>
					<td><input id="createTime" class="easyui-datetimebox" name="createTime" style="width:250px;height:26px;" editable="false" value="${apiLogInfoBean.createTime}" /></td>
			</tr>
			
<!-- 			<tr> -->
<!-- 				<td><font color="red">*</font>修改者:</td> -->
<!-- 				<td><input id="modifier" class="easyui-textbox" -->
<!-- 					name="modifier"  -->
<!-- 					style="width: 250px; height: 26px;" -->
<%-- 					value="${apiLogInfoBean.modifier}" />(必填)</td> --%>
<!-- 			</tr> -->
			
<!-- 			<tr> -->
<!-- 			<td><font color="red">*</font>修改时间:</td> -->
<%-- 					<td><input id="modifyTime" class="easyui-datetimebox" name="modifyTime" style="width:250px;height:26px;" editable="false" value="${apiLogInfoBean.modifyTime}" /></td> --%>
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				<td><font color="red">*</font>att1:</td> -->
<!-- 				<td><input id="att1" class="easyui-textbox" -->
<!-- 					name="att1"  -->
<!-- 					style="width: 250px; height: 26px;" -->
<%-- 					value="${apiLogInfoBean.att1}" />(必填)</td> --%>
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				<td><font color="red">*</font>att2:</td> -->
<!-- 				<td><input id="att2" class="easyui-textbox" -->
<!-- 					name="att2"  -->
<!-- 					style="width: 250px; height: 26px;" -->
<%-- 					value="${apiLogInfoBean.att2}" />(必填)</td> --%>
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				<td><font color="red">*</font>att3:</td> -->
<!-- 				<td><input id="att3" class="easyui-textbox" -->
<!-- 					name="att3"  -->
<!-- 					style="width: 250px; height: 26px;" -->
<%-- 					value="${apiLogInfoBean.att3}" />(必填)</td> --%>
<!-- 			</tr> -->
			
			
<!-- 			<table> -->
<!-- 				<tr> -->
<!-- 					<td><button class="easyui-linkbutton" type="submit"  -->
<!-- 							data-options="iconCls:'icon-save'"  -->
<!-- 							style="width: 80px; height: 25px;" id="edit">保存</button></td> -->
<!-- 				</tr> -->
<!-- 			</table> -->
		</table>
	</form>
</body>
</html>