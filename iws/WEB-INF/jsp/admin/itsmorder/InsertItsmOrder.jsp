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
<title>编辑ItsmOrder页面</title>

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
<style type="text/css">
	html,body {
		height: 0px;
	}
</style>
</head>
<body>
	<form id="clearFrom" action="<%=basePath%>/itsmorder/addOrEditItsmOrder.do" method="post"
		name="addForm" onsubmit="return canSave();">
		<table cellpadding="1">
			

			<input type="hidden" id="uuid" name="uuid" value='${itsmOrderInfoBean.uuid}' />

			<tr>
				<td><font color="red">*</font>工单号:</td>
				<td><input id="orId" class="easyui-textbox" name="orId"
					required="required" style="width: 250px; height: 26px;"
					value="${itsmOrderInfoBean.orId}" />(必填)</td>
			</tr>
			<tr>
				<td><font color="red">&nbsp;&nbsp;</font>工单类别:</td>
				<td><input id="orCategory" class="easyui-textbox"
					name="orCategory" 
					style="width: 250px; height: 26px;"
					value="${itsmOrderInfoBean.orCategory}" /></td>
			</tr>
			<tr>
				<td><font color="red">&nbsp;&nbsp;</font>标题:</td>
				<td><input id="title" class="easyui-textbox" name="title"
					 style="width: 250px; height: 26px;"
					value="${itsmOrderInfoBean.title}" /></td>
			</tr>
			<tr>
				<td><font color="red">&nbsp;&nbsp;</font>作业内容:</td>
				<td><input id="workContent" class="easyui-textbox"
					name="workContent" 
					style="width: 250px; height: 26px;"
					value="${itsmOrderInfoBean.workContent}" /></td>
			</tr>
			<tr>
				<td><font color="red">&nbsp;&nbsp;</font>申请人:</td>
				<td><input id="applicant" class="easyui-textbox"
					name="applicant" 
					style="width: 250px; height: 26px;"
					value="${itsmOrderInfoBean.applicant}" /></td>
			</tr>
			<tr>
				<td><font color="red">&nbsp;&nbsp;</font>申请时间:</td>
					<td><input id="applyTime" class="easyui-datetimebox" name="applyTime" style="width:250px;height:26px;" editable="false" value="${itsmOrderInfoBean.applyTime}" /></td>
			</tr>
			<tr>
				<td><font color="red">&nbsp;&nbsp;</font>实施人:</td>
				<td><input id="performer" class="easyui-textbox"
					name="performer" 
					style="width: 250px; height: 26px;"
					value="${itsmOrderInfoBean.performer}" /></td>
			</tr>
			<tr>
				<td><font color="red">&nbsp;&nbsp;</font>实施时间:</td>
					<td><input id="performTime" class="easyui-datetimebox" name="performTime" style="width:250px;height:26px;" editable="false" value="${itsmOrderInfoBean.performTime}" /></td>
			</tr>
			<tr>
				<td><font color="red">&nbsp;&nbsp;</font>IS担当:</td>
				<td><input id="isowner" class="easyui-textbox" name="isowner"
					 style="width: 250px; height: 26px;"
					value="${itsmOrderInfoBean.isowner}" /></td>
			</tr>
			<tr>
				<td><font color="red">&nbsp;&nbsp;</font>业务系统:</td>
				<td><input id="businessSystem" class="easyui-textbox"
					name="businessSystem" 
					style="width: 250px; height: 26px;"
					value="${itsmOrderInfoBean.businessSystem}" /></td>
			</tr>
			<tr>
				<td><font color="red">&nbsp;&nbsp;</font>实施方式:</td>
				<td><input id="performMode" class="easyui-textbox"
					name="performMode" 
					style="width: 250px; height: 26px;"
					value="${itsmOrderInfoBean.performMode}" /></td>
			</tr>
			<tr>
				<td><font color="red">&nbsp;&nbsp;</font>注解:</td>
				<td><input id="annotation" class="easyui-textbox"
					name="annotation" 
					style="width: 250px; height: 26px;"
					value="${itsmOrderInfoBean.annotation}" /></td>
			</tr>
			<tr>
				<td><font color="red">&nbsp;&nbsp;</font>数据状态:</td>
				<td><input id="dataState" class="easyui-textbox"
					name="dataState" 
					style="width: 250px; height: 26px;"
					value="${itsmOrderInfoBean.dataState}" /></td>
			</tr>
			
			<!-- 				<tr> -->
			<!-- 					<td><font color="red">&nbsp;&nbsp;</font>状态:</td> -->
			<%-- 					<td><select class="easyui-combobox" name="status" style="width:250px;height:26px;"> --%>
			<%-- 					<s:iterator value="statusSelectionMap" id="entry"> --%>
			<%-- 						<s:if test="#entry.key==#request.appInfoList[0].status"> --%>
			<%-- 							<option value='<s:property value="key" />' selected='selected'><s:property value="value" /></option> --%>
			<%-- 						</s:if> --%>
			<%-- 						<s:else> --%>
			<%-- 							<option value='<s:property value="key" />'><s:property value="value" /></option> --%>
			<%-- 						</s:else> --%>
			<%-- 					</s:iterator></select></td> --%>
			<!-- 				</tr> -->
			<!-- 				<tr> -->
			<!-- 					<td>扩展属性1:</td> -->
			<!-- 					<td><input id="att1" class="easyui-textbox" name="att1" style="width:250px;height:26px;" -->
			<%-- 					value="<s:property value="#request.appInfoList[0].att1" />" /></td> --%>
			<!-- 				</tr> -->
			<!-- 				<tr> -->
			<!-- 					<td>扩展属性2:</td> -->
			<!-- 					<td><input id="att2" class="easyui-textbox" name="att2" style="width:250px;height:26px;" -->
			<%-- 					value="<s:property value="#request.appInfoList[0].att2" />" /></td> --%>
			<!-- 				</tr> -->
			<!-- 				<tr> -->
			<!-- 					<td>扩展属性3:</td> -->
			<!-- 					<td><input id="att3" class="easyui-textbox" name="att3" style="width:250px;height:26px;" -->
			<%-- 					value="<s:property value="#request.appInfoList[0].att3" />" /></td> --%>
			<!-- 				</tr> -->
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