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
<title>FowardItsmCreate页面</title>


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
		height: 100%;
	}
</style>
<script type="text/javascript">

	function submitAddAndSend() {
	if(document.forms[0].Performer.value==""||document.forms[0].title.value==""||
	document.forms[0].performMode.value==""||document.forms[0].itiluserid.value==""){
	alert("请完整填写表单信息！");
	return false;
	}
	
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

<body class="easyui-layout" onload="hideAdvanced()">

	<!--< div region="center" border="true" title="center" style="border-left:0px;border-right:0px;overflow:hidden;"> -->		
		<div class="easyui-layout" id="subWrap" fit="true" style="width:100%;height:100%;">
			<div region="north" border="true" title="检修生成" split="false" style="height:300px;" data-options="iconCls:'icon-search'">
			<!-- <h2>ItsmOrder查询</h2> -->
			<form  action="<%=basePath%>/itsmorder/addAndSendItsm.do"  id="addandsedFrom"
				method="post" style="margin-left:20px;margin-top:11px;" onsubmit="return submitAddAndSend()">
				<input type="hidden" id="uuid" name="uuid" value='${itsmOrderInfoBean.uuid}' />
				<table cellpadding="1">
					<tr>
						<td>数据来源:</td>
						<td><input class="easyui-textbox" name="dataSource"
							style="width: 250px; height: 26px;" value="AS400_urgency_order" readOnly="true"></td>
					</tr>
					<tr>
						<td>实施人:</td>
						<td><input class="easyui-textbox" name="Performer" 
							style="width: 250px; height: 26px;" ></td>
					</tr>
					<tr>
						<td>ITIL员工号:</td>
						<td><input class="easyui-textbox" name="itiluserid" 
							style="width: 250px; height: 26px;" ></td>
					</tr>
					<tr>
						<td>所提请求的描述:</td>
						<td><input class="easyui-textbox" name="title"
							style="width: 250px; height: 26px;" ></td>
					</tr>
					<tr>
						<td>此问题的处理方案:</td>
						<td><input class="easyui-textbox" name="performMode"
							style="width: 250px; height: 26px;" ></td>
					</tr>

					 <tr>
						<td><button class="easyui-linkbutton" type="submit" value="生成"
								 data-options="iconCls:'icon-search'"
								style="width: 80px; height: 25px;">生成</button></td>
					</tr>
				</table>
			</form>
          </div>
     </div>
			
</body>
</html>