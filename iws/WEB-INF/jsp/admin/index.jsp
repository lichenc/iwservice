<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8" />
<title>Complex Layout - jQuery EasyUI Demo</title>
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/css/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/css/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/demo.css" />
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/easyDialog/easydialog.css" />
<script type="text/javascript" src="<%=basePath%>/js/jquery.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>/js/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript"
	src="<%=basePath%>/easyDialog/easydialog.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/default.js"></script>
<style type="text/css">
.easyui-accordion ul {
	list-style-type: none;
	margin: 0px;
	padding: 5px;
}

.easyui-accordion ul li {
	padding: 0px;
}

.easyui-accordion ul li a {
	line-height: 24px;
}

.easyui-accordion ul li div {
	margin: 2px 0px;
	padding-left: 10px;
	padding-top: 2px;
}
</style>
</head>
<body class="easyui-layout" style="overflow-y: hidden" scroll="no">
	<div region="north" split="true"
		style="overflow: hidden; height: 45px; background: #E0ECFF repeat-x center 50%; line-height: 20px; color: #fff;">
		<h1
			style="font-size: 30px; line-height: 0px; text-indent: 0em; color: #0E2D5F;"
			id="title">IWS</h1>
	</div>
	<div region="south" style="height: 20px; background: #D2E0F2;">
		<div style="text-align: center; font-weight: bold">IWS version
			1.0</div>
	</div>

	<div region="west" style="width: 180px; overflow: hidden;">
		<div class="easyui-accordion" data-options="multiple:true"
			style="width: 180px;">
			<div title="介绍" data-options="iconCls:'icon-ok'"
				style="overflow: auto; padding: 10px;">
				<p>这是一个信息服务中心</p>
			</div>
			<div title="ITSM" style="padding: 10px;">
				<ul>
					<li>
						<div>
							<a target="mainFrame"
								ghref="<%=basePath%>itsmorder/fowardQueryItsmOrder.do" style="cursor:pointer;">ItsmOrder查询</a>
						</div>
					</li>
					<li>
						<div>
							<a target="mainFrame"
								ghref="<%=basePath%>itsmorder/fowardItsmCreate.do" style="cursor:pointer;">ItsmOrder生成</a>
						</div>
					</li>

				</ul>
			</div>
			<div title="系统配置" style="padding: 10px;">
				<ul>
					<li>
						<div>
							<a target="mainFrame"
								ghref="<%=basePath%>sysconf/fowardQuerySysConf.do" style="cursor:pointer;">系统配置</a>
						</div>
					</li>
					<li>
						<div>
							<a target="mainFrame"
								ghref="<%=basePath%>apilog/fowardQueryApiLog.do" style="cursor:pointer;">查询API日志</a>
						</div>
					</li>
					<li>
						<div>
							<a target="mainFrame" ghref="<%=basePath%>log/fowardQueryLog.do" style="cursor:pointer;">查询系统日志</a>
						</div>
					</li>
				</ul>
			</div>
			<div title="版本信息" style="padding: 10px;">
				<ul>
					<li>
						<div>IWS version 1.0</div>
					</li>

				</ul>

			</div>
		</div>
	</div>



	<div region="center" id="mainPanle"
		style="background: #eee; overflow: hidden;">
		<div id="tabs" class="easyui-tabs" fit="true" border="false">
			<div title="主页" style="padding: 0px;" id="home">
				<h1 style="margin-left: 20px; margin-top: 20px; font-size: 20px;">
					Welcome...</h1>
			</div>
		</div>
	</div>

</body>
</html>
