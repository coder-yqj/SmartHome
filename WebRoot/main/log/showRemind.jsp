<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'showLog.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta charset="UTF-8">
<!-- 	<link rel="stylesheet" type="text/css" href="../../jquery-easyui-1.4.2/themes/default/easyui.css"> -->
<!-- 	<link rel="stylesheet" type="text/css" href="../../jquery-easyui-1.4.2/themes/icon.css"> -->
<!-- 	<link rel="stylesheet" type="text/css" href="../jquery-easyui-1.4.2/demo.css"> -->
<!-- 	<script type="text/javascript" src="../../jquery-easyui-1.4.2/jquery.min.js"></script> -->
<!-- 	<script type="text/javascript" src="../../jquery-easyui-1.4.2/jquery.easyui.min.js"></script> -->
<!-- 	 <script type="text/javascript" src="../../js/jquery-1.7.2.js"></script> -->
  
  </head>
  
  <body>
  <script type="text/javascript">
 
	$(function() { 
	  
      $("#logGrid").datagrid({ 
 
     	  url : '${pageContext.request.contextPath}/operationLog_findByCondition.action', 

          title : '日志列表', 

          pageSize : 5, 

          pageList : [ 5, 10, 15, 20 ], 

          fit : true,//自适应窗口大小变化 

          fitColumns : true, 

          border : false, 

          idField : 'id', 
          
          pagination : true,//分页
          
          rownumbers : true,//行数
          
          columns : [ [ { 

              title : '日志编号', 

              field : 'id', 

              width : 100 ,
              
              hidden:true

          //宽度必须，数值随便 

          },{ 

              title : '场景名称', 

              field : 'sceneName', 

              width : 100 

          },{ 

              title : '设备名称', 

              field : 'equipmentName', 

              width : 100 

          },{ 

              title:'执行时间', 

              field:'opTime', 

              width:100 

          },{ 

              title:'执行信息', 

              field:'operation', 

              width:100 

          } ] ], 

          toolbar : [ { 

              text : '增加', 

              iconCls : 'icon-add', 

              handler : function() { 
           
              } 

          }, '-', { 

              text : '删除', 

              iconCls : 'icon-remove', 

              handler : function() { 
             		 
             	 }

          }, '-', { 

              text : '编辑', 

              iconCls : 'icon-edit', 

              handler : function() { 
              	
              } 

          } ] 
      }); 
      
      
      //设置Combobox的option
      $.ajax({
	  	   type: "POST",
	  	   url: "${pageContext.request.contextPath}/scene_findAll.action",
	  	   dataType: 'json', 
	  	   success: function(data){
	  		   var temp = { "id": 0, "sceneName": "所有" ,"selected":true};
	  		   data.message.unshift(temp);//数组最前面加一条记录
	  		   $('#sceneBox').combobox("loadData", data.message);
	  	   }                                                                                         
	  	});

      
      $.ajax({
    	   type: "POST",
    	   url: "${pageContext.request.contextPath}/equipment_findAll.action",
    	   dataType: 'json', 
    	   success: function(data){
    		   var temp = { "id": 0, "name": "所有" ,"selected":true};
    		   data.message.unshift(temp);//数组最前面加一条记录
    		   $('#eqBox').combobox("loadData", data.message);
    	   }                                                                                         
    	});
      //改变场景，设置设备的combobox选项
      $('#sceneBox').combobox({
		  onSelect: function (n,o) {
			  var sceneId =  $('#sceneBox').combobox('getValue');
			  $.ajax({
		    	   type: "POST",
		    	   url: "${pageContext.request.contextPath}/equipment_findBySceneId.action",
		    	   data:"sceneId="+sceneId,
		    	   dataType: 'json', 
		    	   success: function(data){
		    		   var temp = { "id": 0, "name": "所有" ,"selected":true};
		    		   data.message.unshift(temp);//数组最前面加一条记录
		    		   $('#eqBox').combobox("loadData", data.message);
		    	   }                                                                                         
		    	});
		  }
	  });
      
      
     //给搜索按钮绑定提交表单事件
     $('#search').click(function() {
    	 $('#logGrid').datagrid('load',{
    		 'logPage.sceneId' : $('#sceneBox').combobox('getValue'),
    		 'logPage.eqId' : $('#eqBox').combobox('getValue'),
    		 'logPage.startDate': $('#startDate').datebox('getValue'),
    		 'logPage.endDate': $('#endDate').datebox('getValue')
    		});;
	
     
     });
      
      
      
  });    
	 
	
// 	function showDate() {
// 		var start = $('#startDate').datebox('getValue');
// 		var end = $('#endDate').datebox('getValue');
// 		alert(start+"---"+end);
// 	}
  </script>
    <div style="margin:20px 0;"></div>
	<div style="margin-left: 130px;">
	<form id="conditionForm" action="equipmentLog_findByCondition.action" method="post">
	<table border="1px solid">
		<tr>
			<td style="padding-left: 30px;">场景选择:</td>
			<td>
				<select id="sceneBox" class="easyui-combobox" name="logPage.sceneId" style="width:130px;" data-options="valueField:'id', textField:'sceneName', panelHeight:'auto'">
					<!-- <option value="0" >所有</option> -->
				</select>
			</td>
			
			<td style="padding-left: 30px;">设备选择:</td>
			<td>
				<select id="eqBox" class="easyui-combobox" name="logPage.eqId" style="width:130px;" data-options="valueField:'id', textField:'name', panelHeight:'auto'">
					<!-- <option value="0" >所有</option> -->
				</select>
			</td>
			<td rowspan="2" style="padding-left: 30px;">
				<a href="#" onclick="" id="search" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px">Search</a>
			</td>
			
		</tr>
		<tr>
			
			<td style="padding-left: 30px;">开始时间:</td>
			<td>
				<input id="startDate" name="logPage.startDate" class="easyui-datebox" data-options="sharedCalendar:'#cc'">
			</td>
			<td style="padding-left: 30px;">结束时间:</td>
			<td>
				<input id="endDate" name="logPage.endDate" class="easyui-datebox" data-options="sharedCalendar:'#cc'">
			</td>
			
		</tr>
	</table>
	<div id="cc" class="easyui-calendar"></div>
	</form>
	</div>
	<div style="margin:20px 0;"></div>
	<div style="margin-left: 150px ;width:700px; height: 300px">
		 <table id="logGrid"></table>  
	</div>	
		
		
  </body>
	
</html>
