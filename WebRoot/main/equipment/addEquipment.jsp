<%@ page language="java" import="java.util.*" pageEncoding="UtF-8" contentType="text/html; charset=UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'addequipment.jsp' starting page</title>
    
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
     
    <script type="text/javascript" src="<%=path%>/js/jquery.form.js"></script>
     <script type="text/javascript"> 
     $(function() { 
       
    	 
    	 $("#equipmentGrid").datagrid({ 

             url : '${pageContext.request.contextPath}/equipment_findByPage.action', 

             title : '设备列表', 

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

             }, { 

                 title : '设备名称', 

                 field : 'name', 

                 width : 100 

             }, { 

                 title : '当前值', 

                 field : 'value', 

                 width : 100 

             },{ 

                 title:'阀值上限', 

                 field:'highValue', 

                 width:100 

             },{ 

                 title:'阀值下限', 

                 field:'lowValue', 

                 width:100 

             },{ 

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
                	 var selectRow =  $('#equipmentGrid').datagrid('getSelections');
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
                		     $('#equipmentGrid').datagrid('reload'); 
                		     alert( data.msg );
                		   }
                		});
              	   	 } 
                		 
                	 }

             }, '-', { 

                 text : '编辑', 

                 iconCls : 'icon-edit', 

                 handler : function() { 
					var selectRow =  $('#equipmentGrid').datagrid('getChecked');  
                 	//alert(selectRow[0].id);
                 	var selectId = selectRow[0].id;
                 	$('#editWindow').window('open');
                 	$.ajax({
                 	   type: "POST",
                 	   url: "${pageContext.request.contextPath}/equipment_findById.action",
                 	   data: "equipmentId="+selectId,
                 	   dataType: 'json', 
                 	   success: function(data){
                 	     //alert( "Data Saved: " + data.equipment.name );
                 	     //给编辑窗口表单赋值
                 	     $('#editId').val(data.equipment.id);
                 	     $('#editName').textbox('setValue',data.equipment.name);
                 	     $('#editComment').textbox('setValue',data.equipment.eqComment);
                 	     $('#editHighValue').textbox('setValue',data.equipment.highValue);
                 	     $('#editLowValue').textbox('setValue',data.equipment.lowValue);
                 	  	 if(data.equipment.isRemind==1){
                 	  		 $('#editRadio1').attr("checked",true);
                 	  	 }else{
                 	  		 $('#editRadio0').attr("checked",true);
                 	  	 }
                 	  	 if(!data.equipment.image==""){
                 	  		$('#editPic').attr("src","${pageContext.request.contextPath}/upload/"+data.equipment.image);
                 	  	 }
                 	  	$('#editImg').val(data.equipment.image);
					
                 	   }
                 	});
                 	
                 } 

             } ] 
         }); 
			
         
         
         //增加设备窗口保存按钮
         $('#save').click(function() {
        	var name = $('#name').textbox('getValue');
        	  if(name.length>1&&name.length<10){
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
                    	  $('#equipmentGrid').datagrid('reload');  
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
       	  if(name.length>1&&name.length<10){
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
                   	  alert(data.msg);
                   	  $('#equipmentGrid').datagrid('reload');  
                   	  $('#editWindow').window('close', true);
                     }
                 });
       	  }else{
       		  alert("设备名称不符合规范");
       	  }
		});
         
         
         //增加设备窗口返回按钮
		 $('#return').click(function() {
			 $('#addWindow').window('close', true);
		});
         
	
		 	// 为表单绑定异步上传的事件
		 $("#picForm").ajaxForm({
		 	url : "${pageContext.request.contextPath}/equipment_uploadFile.action", // 请求的url
		 	type : "post", // 请求方式
		 	dataType : "json", // 响应的数据类型
		 	async :true, // 异步
		 	success : function(data){
		 		alert("上传成功");
				$('#selectPic').attr("src","${pageContext.request.contextPath}/upload/"+data.path);
				$('#picRadio').attr("value",data.path);
		 	
		 	},
		 	error : function(){
		 		alert("数据加载失败！");
		 	}
		 });
		//图标选择窗口保存按钮
		$('#picSave').click(function() {
			var val = $('input:radio[name=pic]:checked').val();
			if(val == null){
				alert("请选择一个图标");
				return false;
			}else{
				//alert(val);
				$('#icoPic').attr("value",val);
				$('#pic').css("display","block");
				$('#pic').attr("src","${pageContext.request.contextPath}/upload/"+val);
				//编辑选择的图标
				$('#editPic').attr("src","${pageContext.request.contextPath}/upload/"+val);
				$('#editImg').val(val);
				$('#addPicWindow').window('close', true);
			}
		});
         

     });
     
     function checkurl() {
    	 var file = document.getElementById("picFile").value;
    	 //alert(file);
    	 if(file==""){
    		 alert("请先选择图片");
    		 return false;
    	 }else if(!/\.(gif|jpg|jpeg|png|gif|jpg|png|ico)$/i.test(file)){
             alert("图片类型必须是.gif,jpeg,jpg,png,ico中的一种");
             return false;
         }
	}  
	</script>
    <div style="width:700px; height: 300px; margin: 20px 100px">
    	<table id="equipmentGrid"></table>  
    </div>
    <!-- 添加窗口 -->
    <div id="addWindow" class="easyui-window" title="增加设备" data-options="modal:true,closed:true,iconCls:'icon-save'" style="width:370px;height:500px;padding:10px;">
		<div class="easyui-panel" style="width:340px;padding:10px 20px;border: 0px;" >
		<form id="eqAddForm" method="post" action="" name="eqAddForm" enctype="multipart/form-data">
			<input type="hidden" name="equipment.image" value="" id="icoPic">
			<div style="margin-top:10px;">
				<div style="font-size: 15px ; float: left ; padding-top: 5px">设备名称:</div>
				<div style="float: left ;margin-left: 10px;">
					<input type="text" id="name" name="equipment.name" class="easyui-textbox" style="width:200px;height:32px" data-options="required:true,validType:'length[1,10]'" onblur="validaName();">
				</div>
			</div>

			<div>
				<div style="font-size: 15px ; float: left ; padding-top: 5px;margin-top: 30px;">设备备注:</div>
				<div style="float: left ;margin-left: 10px;;margin-top: 30px;">
					<input type="text" name="equipment.eqComment" class="easyui-textbox"  style="width:200px;height:32px" data-options="validType:'length[1,20]'">
				</div>
			</div>
			 
			<div>
				<div style="font-size: 15px ; float: left ; padding-top: 5px;margin-top: 30px;">阀值上限:</div>
				<div style="float: left ;margin-left: 10px; margin-top: 30px;">
					<input type="text" name="equipment.highValue" class="easyui-numberspinner" data-options="prompt:'如果是开关设备，可不填'" style="width:200px;height:32px">
				</div>
			</div>
			
			<div>
				<div style="font-size: 15px ; float: left ; padding-top: 5px; margin-top: 30px;">阀值下限:</div>
				<div style="float: left ;margin-left: 10px; margin-top: 30px;">
					<input type="text" name="equipment.lowValue" class="easyui-numberspinner" data-options="prompt:'如果是开关设备，可不填'" style="width:200px;height:32px">
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
			
			<div>
				<div style="font-size: 15px ; float: left ; padding-top: 5px;margin-top: 30px;">设备图标:</div>
					<div style="float: left ;margin-left: 10px; margin-top: 30px;">
					 	<a href="#" class="easyui-linkbutton" style="width:60px;height:32px" onclick=" $('#addPicWindow').window('open');">添加图标</a>
						<img id="pic" alt="" src="" style="width: 40px;height: 40px; float: right;margin-left: 20px; display:none;" >
					</div>
			</div>
			
			<div >
				<a href="#" id="save" style="margin-top: 30px; width: 80px; height: 40px; margin-left: 30px; margin-right: 60px;" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存</a>
				<a href="#" id="return" style="margin-top: 30px; width: 80px; height: 40px" class="easyui-linkbutton" data-options="iconCls:'icon-undo'">返回</a>
			</div>
			
		</form>
		
		</div>		
	</div>
	 <!-- 添加窗口 结束-->
	 
	 <!-- 添加图片窗口 -->
	 	<div id="addPicWindow" class="easyui-window" title="添加图标" data-options="modal:true,closed:true,iconCls:'icon-save'" style="width:400px;height:500px;padding:10px;">
	 		<div class="easyui-panel" style="width:370px;padding:10px 20px;border: 0px;" >
		 		<div style="display:inline; font-size: 20px ; padding-top: 5px; margin-top: 30px;  font-family:'微软雅黑'; ">系统图标列表:</div><br>
		 		<div style="margin:20px 0"></div>
		 		<div style="float: left; margin-right: 20px;" >
		 			<img alt="" src="${pageContext.request.contextPath}/upload/a1.ico" style="width: 40px;height: 40px;">
		 			<input type="radio" name="pic" value="a1.ico">
	 			</div>
		 		<div style="float: left; margin-right: 20px;">
		 			<img alt="" src="${pageContext.request.contextPath}/upload/a2.ico" style="width: 40px;height: 40px;">
		 			<input type="radio" name="pic" value="a2.ico">
	 			</div>
		 		<div style="float: left; margin-right: 20px;">
		 			<img alt="" src="${pageContext.request.contextPath}/upload/a3.ico" style="width: 40px;height: 40px;">
		 			<input type="radio" name="pic" value="a3.ico">
	 			</div>
		 		<div style="float: left; margin-right: 20px;">
		 			<img alt="" src="${pageContext.request.contextPath}/upload/a4.ico" style="width: 40px;height: 40px;">
		 			<input type="radio" name="pic" value="a4.ico">
	 			</div>
	 			
	 		 	<!-- 第二行 -->
	 		 	
	 		 	<div style="float: left; margin-right: 20px; margin-top: 20px;" >
		 			<img alt="" src="${pageContext.request.contextPath}/upload/a5.ico" style="width: 40px;height: 40px;">
		 			<input type="radio" name="pic" value="a5.ico">
	 			</div>
	 		 	<div style="float: left; margin-right: 20px; margin-top: 20px; " >
		 			<img alt="" src="${pageContext.request.contextPath}/upload/a6.ico" style="width: 40px;height: 40px;">
		 			<input type="radio" name="pic" value="a6.ico">
	 			</div>
	 		 	<div style="float: left; margin-right: 20px; margin-top: 20px; " >
		 			<img alt="" src="${pageContext.request.contextPath}/upload/a7.ico" style="width: 40px;height: 40px;">
		 			<input type="radio" name="pic" value="a7.ico">
	 			</div>
	 		 	<div style="float: left; margin-right: 20px; margin-top: 20px; " >
		 			<img alt="" src="${pageContext.request.contextPath}/upload/a8.ico" style="width: 40px;height: 40px;">
		 			<input type="radio" name="pic" value="a8.ico">
	 			</div>
	 			
	 			 <div style="width: 350px;float: left; margin-top: 20px;" >
				    <form id="picForm" name="picForm" action="equipment_uploadFile.action" method="post" enctype="multipart/form-data" onsubmit="return checkurl();">   
				        <div style=" font-size: 15px;"> 自定义图标上传：</div>
				         <div style="margin-top: 20px;">
				         <input type="file" id="picFile" name="pic" />
				         <input type="submit" id="btnupload" value="开始上传">  
				     	 </div>	
				     </form>
			     </div>
			     <div style="float: left; margin-top: 20px;">
			     	<div style="font-size: 15px ; float: left ; ">预览:</div>
			     	<img id="selectPic" src="" style="width: 40px;height: 40px; float: left; margin-left: 20px;">
			    	<input id="picRadio" type="radio" name="pic" value="" style="margin-top: 12px;">
			     </div>  
	 			
	 			<div style="float: left; margin-right: 50px; margin-top: 60px;">
					 <a href="#" id="picSave" class="easyui-linkbutton" style="margin-left:40px; width: 80px; height: 40px">确定</a>
					<a href="#" id="return2" style=" margin-left:70px; width: 80px; height: 40px" class="easyui-linkbutton" data-options="iconCls:'icon-undo'" onclick=" $('#addPicWindow').window('close', true);">返回</a>
				</div>
	 			
	 		</div>
	 	</div>
	 <!-- 添加图片窗口 结束 -->
	 
	 
	 <!-- 编辑窗口开始 -->
	 <div id="editWindow" class="easyui-window" title="编辑设备" data-options="modal:true,closed:true,iconCls:'icon-save'" style="width:400px;height:500px;padding:10px;">
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
			 
			<div style="width: 300px; height: 50px; float: left; margin-top: 20px;">
				<div style="font-size: 15px ; float: left ; padding-top: 5px;">阀值上限:</div>
				<div style="float: left ;margin-left: 10px;">
					<input type="text" id="editHighValue" name="equipment.highValue" class="easyui-numberspinner" data-options="prompt:'如果是开关设备，可不填'" style="width:200px;height:32px">
				</div>
			</div>
			
			<div style="width: 300px; height: 50px; float: left; margin-top: 20px;">
				<div style="font-size: 15px ; float: left ; padding-top: 5px;">阀值下限:</div>
				<div style="float: left ;margin-left: 10px; ">
					<input type="text" id="editLowValue" name="equipment.lowValue" class="easyui-numberspinner" data-options="prompt:'如果是开关设备，可不填'" style="width:200px;height:32px">
				</div>
			</div>
			
			
			<div style="width: 300px; height: 50px; float: left; margin-top: 20px;">
				 <div style="display:inline; font-size: 15px ; float: left ; padding-top: 5px; ">是否提醒:</div>
				 <div style="display:inline; float: left ;margin-left: 10px;  padding-top: 5px;">
				 	<input id="editRadio1" type="radio" value="1" name="equipment.isRemind" style="width:25px;height:25px; "><font style="font-size: 15px; ">是</font>	
				 	<input id="editRadio0" type="radio" value="0" name="equipment.isRemind" style="width:25px;height:25px; "><font style="font-size: 15px; ">否</font>
				 </div>
				 <div style="display:inline;font-style:'宋体';font-weight:lighter;font-size:10px;color:gray; float: left; height:32px ; padding-top: 5px;padding-left:20px;">备注:设置提醒后，该设<br>备的开关会发送提醒</div>
			</div>
			
			<div style="width: 300px; height: 50px; float: left; margin-top: 20px;">
				<div style="font-size: 15px ; float: left ; padding-top: 5px;">设备图标:</div>
					<div style="float: left ;margin-left: 10px; ">
					 	<a href="#" class="easyui-linkbutton" style="width:60px;height:32px" onclick=" $('#addPicWindow').window('open');">添加图标</a>
						<img id="editPic" alt="" src="" style="width: 40px;height: 40px; float: right;margin-left: 20px;" >
					</div>
			</div>
			
			<div style="width: 300px; height: 50px; float: left; margin-top: 20px;">
				<a href="#" id="editSave" style="width: 80px; height: 40px; margin-left: 30px; margin-right: 60px;" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存</a>
				<a href="#" id="editReturn" style="width: 80px; height: 40px" class="easyui-linkbutton" data-options="iconCls:'icon-undo'" onclick=" $('#editWindow').window('close', true);">返回</a>
			</div>
			
			</form>
		</div>
	 </div>
	 <!-- 编辑窗口结束 -->
	 
  </body>
</html>
