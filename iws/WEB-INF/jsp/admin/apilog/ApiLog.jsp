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
<title>ApiLog页面</title>


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
</style>
</head>

<body >
	<div class="easyui-layout" id="subWrap" fit="true" style="width:100%;height:100%;">
		<div region="north" border="true" title="查询条件" split="false" style="height:209px;" data-options="iconCls:'icon-search'">
			<form action="batchDeleteItsmOrderDo" id="deleteIdsForm">
				<input type="hidden" value="" name="deleteIds" id="deleteIdsInput" />
			</form>
			<form action="#" onsubmit="return submitQuery();"
				id="queryFormId" method="post" style="margin-left:20px;margin-top:11px;">
				<table cellpadding="1">
		
			<input type="hidden" name="sort" value="createTime"/>
			<input type="hidden" name="order" value="desc"/>
			<tr>
				<td>创建时间:从</td>
				<td><input class="easyui-datetimebox" name="createTimeAfter"
					style="width: 250px; height: 26px;" editable="false"
					editable="false" /></td>
				 <td>到:</td>
				<td><input class="easyui-datetimebox" name="createTimeBefore"
					style="width: 250px; height: 26px;" editable="false"
					editable="false" /></td>
			</tr>
			<tr >
				<td>类型:</td>
				<td><select class="easyui-combobox" name="logcategory_equ"
					style="width: 250px; height: 26px;">
						<option value=''></option>
						<option value='api'>api</option>
				</select></td>
			</tr>
			<tr>
				<td>模块:</td>
				<td><select class="easyui-combobox" name="logmodule_equ"
					style="width: 250px; height: 26px;">
						<option value=''></option>
						<option value='insertItsmOrder'>insertItsmOrder</option>
						<option value='queryItsmOrder'>queryItsmOrder</option>
						<option value='editItsmOrder'>editItsmOrder</option>
						<option value='initItsmOrderInfoBean'>initItsmOrderInfoBean</option>
				</select></td>
			</tr>
			<tr>
				<td>创建者:</td>
				<td><input class="easyui-textbox" name="creator_like"
					style="width: 250px; height: 26px;"></td>
			</tr>
			
			<tr  id="advanced_0">
				<td>日志标题:</td>
				<td><select class="easyui-combobox" name="logtitle_like"
					style="width: 250px; height: 26px;">
						<option value=''></option>
						<option value='pass'>pass</option>
						<option value='fail'>fail</option>
				</select></td>
			</tr>
			<tr  id="advanced_1">
				<td>日志级别:</td>
				<td><select class="easyui-combobox" name="loglevel_equ"
					style="width: 250px; height: 26px;">
						<option value=''></option>
						<option value='debug'>debug</option>
				</select></td>
			</tr>
			<tr  id="advanced_2">
				<td>日志编号:</td>
				<td><input class="easyui-textbox" name="logcode_like"
					style="width: 250px; height: 26px;"></td>
			</tr>
			<tr id="advanced_3">
				<td>运行程序的ip地址:</td>
				<td><input class="easyui-textbox" name="actip_equ"
					style="width: 250px; height: 26px;"></td>
			</tr>
			<tr id="advanced_4">
				<td>运行程序的mac地址:</td>
				<td><input class="easyui-textbox" name="actmac_equ"
					style="width: 250px; height: 26px;"></td>
			</tr>
			<tr>
				<td><button class="easyui-linkbutton" type="button"
						 value="查询" onclick="submitQuery()"
						data-options="iconCls:'icon-search'"
						style="width: 80px; height: 25px;">查询</button></td>
						<td><button id="advanced" class="easyui-linkbutton" type="button" onclick="displayChange();" style="width:80px;height:25px;">展开</button></td>
			</tr>
		
		</table>
		
		
<!-- 		<table cellpadding="1"> -->
<!-- 			<tr> -->
<!-- 				<td>日志编号:</td> -->
<!-- 				<td><input class="easyui-textbox" name="logcode_like" -->
<!-- 					style="width: 250px; height: 26px;"></td> -->
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				<td>日志级别:</td> -->
<!-- 				<td><input class="easyui-textbox" name="loglevel_equ" -->
<!-- 					style="width: 250px; height: 26px;"></td> -->
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				<td>日志标题:</td> -->
<!-- 				<td><input class="easyui-textbox" name="logtitle_like" -->
<!-- 					style="width: 250px; height: 26px;"></td> -->
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				<td>日志内容:</td> -->
<!-- 				<td><input class="easyui-textbox" name="logcontent_like" -->
<!-- 					style="width: 250px; height: 26px;"></td> -->
<!-- 			</tr> -->
<!-- 			<tr id="advanced_0"> -->
<!-- 				<td>日志退出编号:</td> -->
<!-- 				<td><input class="easyui-textbox" name="extlogcode_like" -->
<!-- 					style="width: 250px; height: 26px;"></td> -->
<!-- 			</tr> -->
<!-- 			<tr id="advanced_1"> -->
<!-- 				<td>日志退出的内容:</td> -->
<!-- 				<td><input class="easyui-textbox" name="extlogcontent_like" -->
<!-- 					style="width: 250px; height: 26px;"></td> -->
<!-- 			</tr> -->
<!-- 			<tr id="advanced_2"> -->
<!-- 				<td>类型:</td> -->
<!-- 				<td><input class="easyui-textbox" name="logcategory_equ" -->
<!-- 					style="width: 250px; height: 26px;"></td> -->
<!-- 			</tr> -->
<!-- 			<tr id="advanced_3"> -->
<!-- 				<td>模块:</td> -->
<!-- 				<td><input class="easyui-textbox" name="logmodule_equ" -->
<!-- 					style="width: 250px; height: 26px;"></td> -->
<!-- 			</tr> -->
<!-- 			<tr id="advanced_4"> -->
<!-- 				<td>运行程序的ip地址:</td> -->
<!-- 				<td><input class="easyui-textbox" name="actip_equ" -->
<!-- 					style="width: 250px; height: 26px;"></td> -->
<!-- 			</tr> -->
<!-- 			<tr id="advanced_5"> -->
<!-- 				<td>运行程序的mac地址:</td> -->
<!-- 				<td><input class="easyui-textbox" name="actmac_equ" -->
<!-- 					style="width: 250px; height: 26px;"></td> -->
<!-- 			</tr> -->
<!-- 			<tr id="advanced_6"> -->
<!-- 				<td>状态:</td> -->
<!-- 				<td><input class="easyui-textbox" name="status_equ" -->
<!-- 					style="width: 250px; height: 26px;"></td> -->
<!-- 			</tr> -->
<!-- 			<tr id="advanced_7"> -->
<!-- 				<td>创建者:</td> -->
<!-- 				<td><input class="easyui-textbox" name="creator_like" -->
<!-- 					style="width: 250px; height: 26px;"></td> -->
<!-- 			</tr> -->
<!-- 			<tr id="advanced_8"> -->
<!-- 				<td>修改者:</td> -->
<!-- 				<td><input class="easyui-textbox" name="modifier_like" -->
<!-- 					style="width: 250px; height: 26px;"></td> -->
<!-- 			</tr> -->
<!-- 			<tr id="advanced_9"> -->
<!-- 				<td>创建时间:从</td> -->
<!-- 				<td><input class="easyui-datetimebox" name="createTimeAfter" -->
<!-- 					style="width: 250px; height: 26px;" editable="false" -->
<!-- 					editable="false" /></td> -->
<!-- 				 <td>到:</td> -->
<!-- 				<td><input class="easyui-datetimebox" name="createTimeBefore" -->
<!-- 					style="width: 250px; height: 26px;" editable="false" -->
<!-- 					editable="false" /></td> -->
<!-- 			</tr> -->
<!-- 				<tr id="advanced_10"> -->
<!-- 				<td>修改时间:</td> -->
<!-- 				<td><input class="easyui-datetimebox" name="applyTimeAfter" -->
<!-- 					style="width: 250px; height: 26px;" editable="false" -->
<!-- 					editable="false" /></td> -->
<!-- 				 <td>到:</td> -->
<!-- 				<td><input class="easyui-datetimebox" name="modifyTimeBefore" -->
<!-- 					style="width: 250px; height: 26px;" editable="false" -->
<!-- 					editable="false" /></td>  -->
<!-- 			</tr> -->
<!-- 			<tr id="advanced_11"> -->
<!-- 				<td>att1:</td> -->
<!-- 				<td><input class="easyui-textbox" name="att1_like" -->
<!-- 					style="width: 250px; height: 26px;"></td> -->
<!-- 			</tr > -->
<!-- 				<tr id="advanced_12"> -->
<!-- 				<td>att2:</td> -->
<!-- 				<td><input class="easyui-textbox" name="att2_like" -->
<!-- 					style="width: 250px; height: 26px;"></td> -->
<!-- 			</tr > -->
<!-- 				<tr id="advanced_13"> -->
<!-- 				<td>att3:</td> -->
<!-- 				<td><input class="easyui-textbox" name="att3_like" -->
<!-- 					style="width: 250px; height: 26px;"></td> -->
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				<td><button class="easyui-linkbutton" type="button" -->
<!-- 						 value="查询" onclick="submitQuery()" -->
<!-- 						data-options="iconCls:'icon-search'" -->
<!-- 						style="width: 80px; height: 25px;">查询</button></td> -->
<!-- 						<td><button id="advanced" class="easyui-linkbutton" type="button" onclick="displayChange();" style="width:80px;height:25px;">展开</button></td> -->
<!-- 			</tr> -->
		
<!-- 		</table> -->
			</form>
		</div>
		<div region="center" border="true" title="查询结果" split="true"  data-options="iconCls:'icon-ok'" style="height: 200px;">
			<table id="list_data" cellspacing="0" cellpadding="0" />
			<div id="edit" class="easyui-window"
				data-options="modal:true,closed:true,iconCls:'icon-save',shadow: true,minimizable: false,maximizable:false,collapsible: false">
				<iframe id="editIframe" src="" width="100%" height="100%"
					frameborder="no" border="0" marginwidth="0" marginheight="0"
					scrolling="yes" allowtransparency="true"></iframe>
			</div>
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
	$('#list_data').datagrid({
		url : '<%=basePath%>/apilog/queryApiLogDo.do?'+$('form').serialize()
	});
	
	//查询后的分页控件
    var p = $('#list_data').datagrid('getPager'); 
   	$(p).pagination({
       	showRefresh : false
   	});
	return false;	 	
}

function hideAdvanced(){
	for(var i=0;i<5;i++){
		$("#advanced_"+i).hide();
	}
	$("#advanced").html("展开<<");
	isDisplay = false;
}

function showAdvanced(){
	for(var i=0;i<5;i++){
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
//datagrid初始化 
$(function(){
	$('#list_data').datagrid({
		fit: true,
		pagination : true,//分页控件
		border: true,
		nowrap : true,
		striped : true,
		collapsible : false,//是否可折叠的
		remoteSort : true,
		idField : 'uuid',
		rownumbers:true,//行号
		method:'post',
		onDblClickRow:function(rowIndex,rowData){
			openEdit(rowData.uuid);
		},
		onLoadSuccess:function(data){  
    	  	easyDialog.close();
		},
		columns:[[
					{field:'uuid',title:'<span>uuid</span>',align:'center',width:100,checkbox:true},
					{field:'createTime',title:'<span>创建时间</span>',align:'center',sortable:'true',width:180},
					{field:'logtitle',title:'<span>标题</span>',align:'center',sortable:'true',width:50},
					{field:'logcontent',title:'<span>内容</span>',align:'',sortable:'true',width:600},
					{field:'logcode',title:'<span>日志代码</span>',align:'center',sortable:'true',width:50},
					{field:'extlogcode',title:'<span>扩展代码</span>',align:'center',sortable:'true',width:50},
					{field:'extlogcontent',title:'<span>扩展内容</span>',align:'',sortable:'true',width:300},
					{field:'logcategory',title:'<span>类别</span>',align:'center',sortable:'true',width:50},
					{field:'logmodule',title:'<span>模块</span>',align:'center',sortable:'true',width:130},
					{field:'creator',title:'<span>创建者</span>',align:'center',sortable:'true',width:100},
					{field:'loglevel',title:'<span>级别</span>',align:'center',sortable:'true',width:50},
					
// 				  	{field:'uuid',title:'<span>uuid</span>',align:'center',width:100,checkbox:true}, 
// 	              	{field:'logcode',title:'<span>日志编号</span>',align:'center',sortable:'true',width:100},
// 		          	{field:'loglevel',title:'<span>日志级别</span>',align:'center',sortable:'true',width:100},
// 		          	{field:'logtitle',title:'<span>日志标题</span>',align:'center',sortable:'true',width:100},
// 		          	{field:'logcontent',title:'<span>日志内容</span>',align:'center',sortable:'true',width:100},
// 		          	{field:'extlogcode',title:'<span>日志退出编码</span>',align:'center',sortable:'true',width:100},
// 		          	{field:'extlogcontent',title:'<span>日志退出的内容</span>',align:'center',sortable:'true',width:100},
// 		          	{field:'logcategory',title:'<span>日志类型</span>',align:'center',sortable:'true',width:100},
// 		          	{field:'logmodule',title:'<span>登录模块</span>',align:'center',sortable:'true',width:100},
// 		          	{field:'actip',title:'<span>运行程序的ip地址</span>',align:'center',sortable:'true',width:100},
// 		          	{field:'actmac',title:'<span>运行程序的mac地址</span>',align:'center',sortable:'true',width:100},
// 		          	{field:'status',title:'<span>状态</span>',align:'center',sortable:'true',width:100},
// 		          	{field:'creator',title:'<span>创建者</span>',align:'center',sortable:'true',width:100},
// 		          	{field:'createTime',title:'<span>创建时间</span>',align:'center',sortable:'true',width:100},
// 		          	{field:'modifier',title:'<span>修改者</span>',align:'center',sortable:'true',width:100},
// 		          	{field:'modifyTime',title:'<span>修改时间</span>',align:'center',sortable:'true',width:100},
// 		          	{field:'att1',title:'<span>att1</span>',align:'center',sortable:'true',width:100},
// 		          	{field:'att2',title:'<span>att2</span>',align:'center',sortable:'true',width:100},
// 		          	{field:'att3',title:'<span>att3</span>',align:'center',sortable:'true',width:100},
		          	
// 	              {field:'status',title:'<span>状态</span>',align:'center',sortable:'true',width:40},
// 	              {field:'creator',title:'<span>创建者</span>',align:'center',sortable:'true',width:80},
// 	              {field:'createTimeStr',title:'<span>创建时间</span>',align:'center',sortable:'true',width:100},
// 	              {field:'modifier',title:'<span>修改者</span>',align:'center',sortable:'true',width:80},
// 	              {field:'modifyTimeStr',title:'<span>修改时间</span>',align:'center',sortable:'true',width:100},
// 	              {field:'att1',title:'<span>扩展属性1</span>',align:'center',width:100},
// 	              {field:'att2',title:'<span>扩展属性2</span>',align:'center',width:100},
// 	              {field:'att3',title:'<span>扩展属性3</span>',align:'center',width:100},
	              
		          	
		]],toolbar: [

// 		{
// 			text: '添加',
// 			iconCls: 'icon-add',
// 			handler: function() {
// 				openEdit('-1');
// 		 	}
// 		}, 
// 		'-',
// 		{
// 			text: '删除',
// 			iconCls: 'icon-remove',
// 			handler: function(){
// 				var rowObj=$('#list_data').datagrid("getSelections");
// 				if(rowObj.length == 0){
// 					$.messager.alert('提示框','请选择数据!');
// 				}else{
// 					var uuids = "";
// 					for(var i=0;i<rowObj.length;i++){
// 						uuids= uuids + rowObj[i].uuid + ",";
// 					}
// 					uuids = uuids.substr(0, uuids.length - 1);
// 					$.messager.confirm('提示框', '你确定要删除吗?',function(r){
// 						if (r) {
// 							$.ajax({
//								'url':'<%=basePath%>/apilog/bacthDeleteApiLogDo.do',
// 								'type':'post',
// 								'data':{'uuids':uuids},
// 								'dateType':'xml',
// 								'success':function(data,status){
// 									doing();
// 									$.messager.alert('提示框',data);
// 									$('#list_data').datagrid({
//										url : '<%=basePath%>/apilog/queryApiLogDo.do?'+$('form').serialize()
// 									});
// 									$('#list_data').datagrid("clearSelections");
// 								},error : function(data) {
// 					     			$.messager.alert('友情提示','请稍后再试');
// 					     		}
// 							});
// 						}
// 					})

					
// 				}
// 			}
// 		}
		]
	});

    //默认分页控件数目为0
     var p = $('#list_data').datagrid('getPager'); 
     if (p){ 
    	 $(p).pagination({
        	displayMsg: '当前显示 0 条记录, 共 0 条记录',
        	showRefresh : false
    			});
			}
	});

	function openEdit(uuid){
		$('#editIframe').attr("src","<%=basePath%>/apilog/fowardEditApiLog.do?uuid_equ="+uuid);
		
		$('#edit').window({
		    title: 'ItsmOrder',
		    width:  $(window).width()-100,
		    height: $(window).height()-100,
		    top:$(document).scrollTop()+50,
			left:$(document).scrollLeft()+50
		});
		
		$('#edit').window('open');
	}
	
	
	$(document).keypress(function(event){
		if(event.keyCode == 27 && $('#edit').is(":visible")){
// 			$.messager.confirm('提示框', '取消保存?',function(r){
// 				if (r) {
					openEdit('-1');
					$('#edit').window('close', true);
// 				}
// 			})
			
		}
	}); 
	
	$('#edit').window({
	    onBeforeClose: function () { //当面板关闭之前触发的事件
// 	         if (confirm('窗口正在关闭，请确认您当前的操作已保存。\n 是否继续关闭窗口？')) {
	        	openEdit('-1');
	            $('#edit').window('close', true); //这里调用close 方法，true 表示面板被关闭的时候忽略onBeforeClose 回调函数。
// 	         } else{
// 	         	return false;
// 	         }    
	    }
	});

window.onload=function(){
		hideAdvanced();
	}




	

</script>
</body>
</html>