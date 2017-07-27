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
<title>编辑SysConf页面</title>



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
	<form id="clearFrom" action="<%=basePath%>/sysconf/addOrEditSysConf.do" method="post"
		name="addForm" onsubmit="return canSave();">
		<table cellpadding="1">
			

			<input type="hidden" id="uuid" name="uuid" value='${sysConfInfoBean.uuid}' />

			<tr>
				<td><font color="red">*</font>类别:</td>
				<td><input id="CFGCategory" class="easyui-textbox" name="cfgCategory"
					required="required" style="width: 250px; height: 26px;"
					value="${sysConfInfoBean.cfgCategory}" />(必填)</td>
			</tr>
			<tr>
				<td><font color="red">*</font>组:</td>
				<td><input id="CFGGroup" class="easyui-textbox"
					name="cfgGroup" 
					style="width: 250px; height: 26px;"
					value="${sysConfInfoBean.cfgGroup}" />(必填)</td>
			</tr>
			<tr>
				<td><font color="red">*</font>key:</td>
				<td><input id="CFGKey" class="easyui-textbox" name="cfgKey"
					 style="width: 250px; height: 26px;"
					value="${sysConfInfoBean.cfgKey}" />(必填)</td>
			</tr>
			<tr>
				<td><font color="red">*</font>key类型:</td>
				<td><input id="CFGKeyType" class="easyui-textbox"
					name="cfgKeyType" 
					style="width: 250px; height: 26px;"
					value="${sysConfInfoBean.cfgKeyType}" />(必填)</td>
			</tr>
			<tr>
				<td><font color="red">*</font>值:</td>
				<td><input id="CFGValue" class="easyui-textbox"
					name="cfgValue" 
					style="width: 250px; height: 26px;"
					value="${sysConfInfoBean.cfgValue}" />(必填)</td>
			</tr>
		
			<tr>
				<td><font color="red">*</font>值类型:</td>
				<td><input id="CFGValueType" class="easyui-textbox"
					name="cfgValueType" 
					style="width: 250px; height: 26px;"
					value="${sysConfInfoBean.cfgValueType}" />(必填)</td>
			</tr>
			
			<tr>
				<td><font color="red">*</font>说明:</td>
				<td><input id="CFGValueInfo" class="easyui-textbox" name="cfgValueInfo"
					 style="width: 250px; height: 26px;"
					value="${sysConfInfoBean.cfgValueInfo}" />(必填)</td>
			</tr>
<!-- 			<tr> -->
<!-- 				<td><font color="red">*</font>状态:</td> -->
<!-- 				<td><input id="status" class="easyui-textbox" -->
<!-- 					name="status"  -->
<!-- 					style="width: 250px; height: 26px;" -->
<%-- 					value="${sysConfInfoBean.status}" />(必填)</td> --%>
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				<td><font color="red">*</font>创建人:</td> -->
<!-- 				<td><input id="creator" class="easyui-textbox" -->
<!-- 					name="creator"  -->
<!-- 					style="width: 250px; height: 26px;" -->
<%-- 					value="${sysConfInfoBean.creator}" />(必填)</td> --%>
<!-- 			</tr> -->
<!-- 				<tr> -->
<!-- 				<td><font color="red">*</font>创建时间:</td> -->
<%-- 					<td><input id="createTime" class="easyui-datetimebox" name="createTime" style="width:250px;height:26px;" editable="false" value="${sysConfInfoBean.createTime}" /></td> --%>
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				<td><font color="red">*</font>修改人:</td> -->
<!-- 				<td><input id="modifier" class="easyui-textbox" -->
<!-- 					name="modifier"  -->
<!-- 					style="width: 250px; height: 26px;" -->
<%-- 					value="${sysConfInfoBean.modifier}" />(必填)</td> --%>
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				<td><font color="red">*</font>修改时间:</td> -->
<%-- 					<td><input id="modifyTime" class="easyui-datetimebox" name="modifyTime" style="width:250px;height:26px;" editable="false" value="${sysConfInfoBean.modifyTime}" /></td> --%>
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				<td><font color="red">*</font>扩展属性1:</td> -->
<!-- 				<td><input id="att1" class="easyui-textbox" -->
<!-- 					name="att1"  -->
<!-- 					style="width: 250px; height: 26px;" -->
<%-- 					value="${sysConfInfoBean.att1}" />(必填)</td> --%>
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				<td><font color="red">*</font>扩展属性2:</td> -->
<!-- 				<td><input id="att2" class="easyui-textbox" -->
<!-- 					name="att2"  -->
<!-- 					style="width: 250px; height: 26px;" -->
<%-- 					value="${sysConfInfoBean.att2}" />(必填)</td> --%>
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				<td><font color="red">*</font>扩展属性3:</td> -->
<!-- 				<td><input id="att3" class="easyui-textbox" -->
<!-- 					name="att3"  -->
<!-- 					style="width: 250px; height: 26px;" -->
<%-- 					value="${sysConfInfoBean.att3}" />(必填)</td> --%>
<!-- 			</tr> -->
			
			<table>
				<tr>
					<td><button class="easyui-linkbutton" type="submit" 
							data-options="iconCls:'icon-save'" 
							style="width: 80px; height: 25px;" id="edit">保存</button></td><!--  -->
				</tr>
			</table>
		</table>
	</form>
</body>
</html>