<%@ page language="java" import="java.util.*" pageEncoding="UtF-8" contentType="text/html; charset=UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'showEquipment.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  	<script type="text/javascript">
	  //设置Combobox的option
	  $(function() { 
		  $.ajax({
		  	   type: "POST",
		  	   url: "${pageContext.request.contextPath}/scene_findAll.action",
		  	   dataType: 'json', 
		  	   success: function(msg){
		  		   $('#sceneBox2').combobox("loadData", msg.message);
		   		  if(${sceneId}!=0){
		  		   $('#sceneBox2').combobox('setValue', '${sceneId}');
		   		  }
		  		   var temp = { "id": 0, "sceneName": "所有" ,"selected":true};
		  		   msg.message.unshift(temp);//数组最前面加一条记录
		  		   $('#sceneBox').combobox("loadData", msg.message);
		  		   $('#sceneBox').combobox('setValue', '${sceneId}');
		  	   }                                                                                         
		  	});
		 
		  $("#equipmentTable").datagrid({ 
				
	             url : '${pageContext.request.contextPath}/equipment_findByPage.action?sceneId=${sceneId}', 

	           //  title : '设备列表', 

	             pageSize : 5, 

	             pageList : [ 5, 10, 15, 20 ], 

	             fit : true,//自适应窗口大小变化 

	             fitColumns : true, 

	             border : false, 

	             idField : 'id', 
	             
	             pagination : true,//分页
	             
	             rownumbers : true,//行数
	             
	             columns : [ [ {
					field : "checkbocx",
					checkbox : true

					},{ 

	                 title : '设备编号', 

	                 field : 'id', 

	                 width : 100 ,
	                 
	                 hidden:true

	             //宽度必须，数值随便 

	             },{ 

	                 title : '场景编号', 

	                 field : 'scene.id', 

	                 width : 100 ,
	                 
	                 hidden:true

	             //宽度必须，数值随便 

	             },{ 

	                 title : '设备名称', 

	                 field : 'name', 

	                 width : 100 

	             }, { 

	                 title:'开关状态', 

	                 field:'state', 

	                 width:100 ,
	                 
	                 formatter: function(value,row,index){
	     				if (value==1){
	     					return '开';
	     				} else {
	     					return '关';
	     				}
	                 }

	             },{ 

	                 title:'是否关注', 

	                 field:'isRemind', 

	                 width:100 ,
	                 
	                 formatter: function(value,row,index){
	     				if (value==1){
	     					return '是';
	     				} else {
	     					return '否';
	     				}
	                 }

	             },{ 

	                 title:'备注', 

	                 field:'eqComment', 

	                 width:100 

	             } ] ], 

	             toolbar : [ { 

	                 text : '增加', 

	                 iconCls : 'icon-add', 

	                 handler : function() { 
	                	 $('#addWindow').window('open');
	                 } 

	             }, '-', { 

	                 text : '删除', 

	                 iconCls : 'icon-remove', 

	                 handler : function() { 
						//删除按钮操作
						 var ids = [];
	                	 var selectRow =  $('#equipmentTable').datagrid('getSelections');
	                	 for(var i=0; i<selectRow.length; i++){
	                		    ids.push(selectRow[i].id);
	                		}
	                	 if(confirm("确认删除？")){
	                	 $.ajax({
	                		   type: "POST",
	                		   url: "${pageContext.request.contextPath}/equipment_delByIds.action",
	                		   data: $.param({"ids":ids},true),
	                		   dataType: 'json', 
	                		   success: function(data){
	                		     alert( data.message );
	                		     $('#equipmentTable').datagrid('reload'); 
	                		   }
	                		});
	              	   	 } 
	                		 
	                	 }

	             }, '-', { 

	                 text : '编辑', 

	                 iconCls : 'icon-edit', 

	                 handler : function() { 
						var selectRow =  $('#equipmentTable').datagrid('getChecked');  
	                 	//alert(selectRow[0].id);
	                 	var selectId = selectRow[0].id;
	                 	$('#eqEditWindow').window('open');
	                 	$.ajax({
	                 	   type: "POST",
	                 	   url: "${pageContext.request.contextPath}/equipment_findById.action",
	                 	   data: "equipmentId="+selectId,
	                 	   dataType: 'json', 
	                 	   success: function(data){
// 	                 	     alert( "Data Saved: " + data.message.name );
	                 	     //给编辑窗口表单赋值
	                 	     $('#editId').val(data.message.id);
	                 	     $('#editName').textbox('setValue',data.message.name);
	                 	     $('#editComment').textbox('setValue',data.message.eqComment);
// 	                 	     $('#editHighValue').textbox('setValue',data.message.equipment.highValue);
// 	                 	     $('#editLowValue').textbox('setValue',data.message.equipment.lowValue);
	                 	  	 if(data.message.isRemind==1){
	                 	  		 $('#editRadio1').attr("checked",true);
	                 	  	 }else{
	                 	  		 $('#editRadio0').attr("checked",true);
	                 	  	 }
// 	                 	  	 if(!data.message.image==""){
// 	                 	  		$('#editPic').attr("src","${pageContext.request.contextPath}/upload/"+data.equipment.image);
// 	                 	  	 }
// 	                 	  	$('#editImg').val(data.equipment.image);
						
	                 	   }
	                 	});
	                 	
	                 } 

	             } ] 
	         }); 
		  
		  $('#sceneBox').combobox({
			  onSelect: function (n,o) {
				  var sceneId =  $('#sceneBox').combobox('getValue');
				  var currTab = $('#tabs_index').tabs('getSelected'); 
		          currTab.panel('refresh', '${pageContext.request.contextPath}/equipment_toShowSceneEq.action?sceneId='+sceneId);;	
				//  alert(sceneId);
			  }
		  });
		  
		  //单击一行事件
		  $('#equipmentTable').datagrid({
			  onClickRow: function (index, row) { 
				  var eqId = row["id"];  
// 				  alert(eqId);
			  }
		  });
		  
		  
		  
		  //增加设备窗口保存按钮
	         $('#eqSave').click(function() {
	        	var name = $('#name').textbox('getValue');
	        	  if(name.length>0&&name.length<10){
	        		  $.ajax({
	                      cache: true,
	                      type: "POST",
	                      url:"equipment_add.action",
	                      data:$('#eqAddForm').serialize(),// 你的formid
	                      async: false,
	                      error: function(request) {
	                          alert("Connection error");
	                      },
	                      success: function(data) {
	                    	  alert("添加成功");
	                    	  $('#equipmentTable').datagrid('reload');  
	                    	  $('#addWindow').window('close', true);
	                      }
	                  });
	        	  }else{
	        		  alert("设备名称不符合规范");
	        	  }
			});
		  
		  
	       //编辑保存按钮
	         $('#editSave').click(function() {
	        	 var name = $('#editName').textbox('getValue');
	       	  if(name.length>0&&name.length<10){
	       		  $.ajax({
	                     cache: true,
	                     type: "POST",
	                     dataType: 'json',
	                     url:"equipment_edit.action",
	                     data:$('#eqEditForm').serialize(),// 你的formid
	                     async: false,
	                     error: function(request) {
	                         alert("Connection error");
	                     },
	                     success: function(data) {
	                   	  alert(data.message);
	                   	  $('#equipmentTable').datagrid('reload');  
	                   	  $('#eqEditWindow').window('close', true);
	                     }
	                 });
	       	  }else{
	       		  alert("设备名称不符合规范");
	       	  }
			});
		  
		  
		  
	  
	  });  
  		
	  
  	</script>
  	<!-- 左侧DIV开始 -->
  	<div style="float: left; width: 60%">
	  	<div style="height: 50px; width: 100%">
			<table>
				<tr>
					<td style="font-size: 15px;">
						场景选择：
					</td>
					<td>
				  		 <select id="sceneBox" class="easyui-combobox" name="sceneId" style="width:130px;" data-options="valueField:'id', textField:'sceneName', panelHeight:'auto'">
								<!-- <option value="0" >所有</option> -->
						 </select>
					</td>
				</tr>
			</table>
	  	</div>
	    <div class="easyui-panel">
			<div id="p1" class="easyui-panel" title="设备列表" style="width:100%; height:380px;"
					data-options="iconCls:'icon-save'">
				 <div style="width:100%; height: 350px;">
			    	<table id="equipmentTable"></table>  
			    </div>
			</div>
		</div>
  	
  	</div>
  	<!-- 左侧DIV结束-->

  	<!-- 右侧DIV开始-->
  	
  	<!-- 右侧DIV结束-->
  	
  	
  	<!-- 添加设备窗口开始 -->
  		<div id="addWindow" class="easyui-window" title="增加设备" data-options="modal:true,closed:true,iconCls:'icon-save'" style="width:370px;height:400px;padding:10px;">
		<div class="easyui-panel" style="width:340px;padding:10px 20px;border: 0px;" >
		<form id="eqAddForm" method="post" action="" name="eqAddForm" enctype="multipart/form-data">
<!-- 			<input type="hidden" name="equipment.image" value="" id="icoPic"> -->
			<!-- 设备选择 -->
			<div style="margin-top:10px; float: left; width: 100%" >
				<div style="font-size: 15px ; float: left ; padding-top: 5px">场景选择:</div>
				<div style="float: left ;margin-left: 10px;">
				<select id="sceneBox2" class="easyui-combobox" name="equipment.sceneId" style="width:200px;height:32px" data-options="valueField:'id', textField:'sceneName', panelHeight:'auto'">
					<!-- <option value="0" >所有</option> -->
  				 </select>
				</div>
			</div>
			
			
			<div style=" float:left; margin-top: 30px;">
				<div style="font-size: 15px ; float: left ; padding-top: 5px">设备名称:</div>
				<div style="float: left ;margin-left: 10px;">
					<input type="text" id="name" name="equipment.name" class="easyui-textbox" style="width:200px;height:32px" data-options="required:true,validType:'length[1,10]'" onblur="validaName();">
				</div>
			</div>

			<div>
				<div style="font-size: 15px ; float: left; padding-top: 5px;margin-top: 30px;">设备备注:</div>
				<div style="float: left ;margin-left: 10px;;margin-top: 30px;">
					<input type="text" name="equipment.eqComment" class="easyui-textbox"  style="width:200px;height:32px" data-options="validType:'length[1,20]'">
				</div>
			</div>
			 
			
			<div>
				 <div style="display:inline; font-size: 15px ; float: left ; padding-top: 5px; margin-top: 30px;">是否提醒:</div>
				 <div style="display:inline; float: left ;margin-left: 10px; margin-top: 30px; padding-top: 5px;">
				 	<input type="radio" value="1" name="equipment.isRemind" style="width:25px;height:25px; "><font style="font-size: 15px; ">是</font>	
				 	<input type="radio" value="0" name="equipment.isRemind" style="width:25px;height:25px; " checked="checked"><font style="font-size: 15px; ">否</font>
				 </div>
				 <div style="display:inline;font-style:'宋体';font-weight:lighter;font-size:10px;color:gray; float: left; height:32px ; padding-top: 5px; margin-top: 30px;padding-left:20px;">备注:设置提醒后，该设<br>备的开关会发送提醒</div>
			</div>
			
<!-- 			<div> -->
<!-- 				<div style="font-size: 15px ; float: left ; padding-top: 5px;margin-top: 30px;">设备图标:</div> -->
<!-- 					<div style="float: left ;margin-left: 10px; margin-top: 30px;"> -->
<!-- 					 	<a href="#" class="easyui-linkbutton" style="width:60px;height:32px" onclick=" $('#addPicWindow').window('open');">添加图标</a> -->
<!-- 						<img id="pic" alt="" src="" style="width: 40px;height: 40px; float: right;margin-left: 20px; display:none;" > -->
<!-- 					</div> -->
<!-- 			</div> -->
			
			<div >
				<a href="#" id="eqSave" style="margin-top: 30px; width: 80px; height: 40px; margin-left: 30px; margin-right: 60px;" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存</a>
				<a href="#" id="eqReturn" style="margin-top: 30px; width: 80px; height: 40px" class="easyui-linkbutton" data-options="iconCls:'icon-undo'" onclick="$('#addWindow').window('close', true);">返回</a>
			</div>
			
		</form>
		
		</div>		
	</div>
  	<!-- 添加设备窗口结束 -->
  	
  	 <!-- 编辑窗口开始 -->
	 <div id="eqEditWindow" class="easyui-window" title="编辑设备" data-options="modal:true,closed:true,iconCls:'icon-save'" style="width:400px;height:500px;padding:10px;">
		<div class="easyui-panel" style="width:370px;padding:10px 20px;border: 0px;" >
			<form action="" method="post" id="eqEditForm">
			<input type="hidden" id="editId" name="equipment.id" value="">
			<input type="hidden" id="editImg" name="equipment.image" value="">
			<div style="width: 300px; height: 50px; float: left; margin-top: 20px;">
				<div style="font-size: 15px ; float: left ; padding-top: 5px">设备名称:</div>
				<div style="float: left ;margin-left: 10px;">
					<input type="text" id="editName" name="equipment.name" class="easyui-textbox" style="width:200px;height:32px" data-options="required:true,validType:'length[1,10]'" onblur="validaName();">
				</div>
			</div>
			
			<div style="width: 300px; height: 50px; float: left; margin-top: 20px;">
				<div style="font-size: 15px ; float: left ; padding-top: 5px">设备备注:</div>
				<div style="float: left ;margin-left: 10px;">
					<input type="text" id="editComment"  name="equipment.eqComment" class="easyui-textbox"  style="width:200px;height:32px" data-options="validType:'length[1,20]'">
				</div>
			</div>
			 
<!-- 			<div style="width: 300px; height: 50px; float: left; margin-top: 20px;"> -->
<!-- 				<div style="font-size: 15px ; float: left ; padding-top: 5px;">阀值上限:</div> -->
<!-- 				<div style="float: left ;margin-left: 10px;"> -->
<!-- 					<input type="text" id="editHighValue" name="equipment.highValue" class="easyui-numberspinner" data-options="prompt:'如果是开关设备，可不填'" style="width:200px;height:32px"> -->
<!-- 				</div> -->
<!-- 			</div> -->
			
<!-- 			<div style="width: 300px; height: 50px; float: left; margin-top: 20px;"> -->
<!-- 				<div style="font-size: 15px ; float: left ; padding-top: 5px;">阀值下限:</div> -->
<!-- 				<div style="float: left ;margin-left: 10px; "> -->
<!-- 					<input type="text" id="editLowValue" name="equipment.lowValue" class="easyui-numberspinner" data-options="prompt:'如果是开关设备，可不填'" style="width:200px;height:32px"> -->
<!-- 				</div> -->
<!-- 			</div> -->
			
			
			<div style="width: 300px; height: 50px; float: left; margin-top: 20px;">
				 <div style="display:inline; font-size: 15px ; float: left ; padding-top: 5px; ">是否提醒:</div>
				 <div style="display:inline; float: left ;margin-left: 10px;  padding-top: 5px;">
				 	<input id="editRadio1" type="radio" value="1" name="equipment.isRemind" style="width:25px;height:25px; "><font style="font-size: 15px; ">是</font>	
				 	<input id="editRadio0" type="radio" value="0" name="equipment.isRemind" style="width:25px;height:25px; "><font style="font-size: 15px; ">否</font>
				 </div>
				 <div style="display:inline;font-style:'宋体';font-weight:lighter;font-size:10px;color:gray; float: left; height:32px ; padding-top: 5px;padding-left:20px;">备注:设置提醒后，该设<br>备的开关会发送提醒</div>
			</div>
			
<!-- 			<div style="width: 300px; height: 50px; float: left; margin-top: 20px;"> -->
<!-- 				<div style="font-size: 15px ; float: left ; padding-top: 5px;">设备图标:</div> -->
<!-- 					<div style="float: left ;margin-left: 10px; "> -->
<!-- 					 	<a href="#" class="easyui-linkbutton" style="width:60px;height:32px" onclick=" $('#addPicWindow').window('open');">添加图标</a> -->
<!-- 						<img id="editPic" alt="" src="" style="width: 40px;height: 40px; float: right;margin-left: 20px;" > -->
<!-- 					</div> -->
<!-- 			</div> -->
			
			<div style="width: 300px; height: 50px; float: left; margin-top: 20px;">
				<a href="#" id="editSave" style="width: 80px; height: 40px; margin-left: 30px; margin-right: 60px;" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存</a>
				<a href="#" id="editReturn" style="width: 80px; height: 40px" class="easyui-linkbutton" data-options="iconCls:'icon-undo'" onclick=" $('#eqEditWindow').window('close', true);">返回</a>
			</div>
			
			</form>
		</div>
	 </div>
	 <!-- 编辑窗口结束 -->
  	
  	
  	
  </body>
</html>
