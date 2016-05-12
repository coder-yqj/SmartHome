<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'showScene.jsp' starting page</title>
    
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
	<style type="text/css">
		img {	
			width: 80px;
			height: 80px;
			transition: width 2s;
			-moz-transition: width 2s;	/* Firefox 4 */
			-webkit-transition: width 2s;	/* Safari 和 Chrome */
			-o-transition: width 2s;
			}
		img:hover
			{
			width: 90px;
			height: 90px;
			}
		.imgbd{
			width: 80px;
			height: 80px;
		}	
		.fontbd{
			width: 80px;
			text-align: center;
			font-size: 20px;
		}
		.imgdiv{
			float: left; 
			margin-right: 30px; 
			margin-top: 10px; 
			width: 90px;
			height: 90px;
/* 			border:1px solid #F00; */
		}
		.imgtd{
			width: 90px;
			height: 90px;
		}
	</style>
	<script type="text/javascript">
// 	$.ajax({
// 		   type: "POST",
// 		   url: "${pageContext.request.contextPath}/scene_findAll.action",
// 		   dataType: 'json', 
// 		   success: function(data){
// 			   jQuery.each(data.result.rows, function(index,item) {
// 				$('#imgs').append("<div style='margin:10px 10px;'><div </div>");
// 			});
// 		   }
// 		});
	
	$(function() {
// 		 var currTab = $('#tabs_index').tabs('getSelected'); //获得当前tab
//          currTab.panel('refresh', 'get_content.php');

		//提交按钮绑定事件
		$('#sceneSave').click(function() {
			var name = $('#sceneName').val();
			
			if(name.length>1&&name.length<10){
				 $.ajax({
                     cache: true,
                     type: "POST",
                     dataType: 'json',
                     url:"scene_add.action",
                     data:$('#sceneForm').serialize(),// 你的formid
                     async: false,
                     error: function(request) {
                         alert("Connection error");
                     },
                     success: function(data) {
                   	  	alert(data.message);
                   	  	$('#addSceneWindow').window('close', true);
                   	  	//刷新当前的tab
	                   	 var currTab = $('#tabs_index').tabs('getSelected'); //获得当前tab
	                   	 currTab.panel('refresh', '${pageContext.request.contextPath}/scene_toShowScene.action');
                   	
                     }
                 });
				
			}else{
				alert("场景名称长度不规范");
			}
		});
		
		
		
		
	});
	
	//选中效果
	function choice(img,name,id) {
		$('.imgdiv>img').css({ width: "80px", height: "80px" });
		$('.imgdiv').css("border","");
		$('#sceneImg').val(img);
		$('#sceneName').val(name);
		$('#'+id).css("border","2px dotted gray");
		$('#'+id+'>img').css({ width: "90px", height: "90px" });
		
	}
	
	function showEq(){
		var sceneId = $('#updateSceneId').val();
	   	var url="${pageContext.request.contextPath}/equipment_toShowSceneEq.action?sceneId="+sceneId;
		
		//var index = $('#tabs_index').tabs('exists','设备列表');
		 if ($('#tabs_index').tabs('exists', '设备列表')){    
		        //重新加载内容
                var tab = $('#tabs_index').tabs('getTab','设备列表');
                $('#tabs_index').tabs('update', {
                        tab: tab,
                        options: {
                                content:'<div id="tabDiv">加载数据中,请稍候....</div>',
                                href:url
                        }
                });
		        $('#tabs_index').tabs('select', '设备列表');   
//                 $('#tabDiv').load(${pageContext.request.contextPath}/main/equipment/showEquipment.jsp); 
		    } else {    
		    	// var content = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';    
		         $('#tabs_index').tabs('add',{    
		             title:'设备列表', 
		             href:url,
		             //content:content,    
		             closable:true    
		         });  
		    }    
	}
	
	function showDetail(id,img,name) {
		$('#detailTable').css("visibility","visible");
		$('#imgDetail').attr("src","${pageContext.request.contextPath}/upload/"+img);
		$('#updateSceneId').val(id);
		$('#updateSceneImg').val(img);
		$('#updateSceneName').val(name);
		
	}
	
	function updateScene() {
		var name = $('#updateSceneName').val();
		if(name.length>1&&name.length<10){
			 $.ajax({
                 cache: true,
                 type: "POST",
                 dataType: 'json',
                 url:"scene_update.action",
                 data:$('#detailForm').serialize(),
                 async: false,
                 error: function(request) {
                     alert("Connection error");
                 },
                 success: function(data) {
               	  	alert(data.message); 
               	//刷新当前tab
               	var currTab = $('#tabs_index').tabs('getSelected'); 
               	currTab.panel('refresh', '${pageContext.request.contextPath}/scene_toShowScene.action');

                 }
             });
		}else{
			alert("场景名称不符合规范");
			return;
		}
	}
	
	function delScene() {
		 $.ajax({
             cache: true,
             type: "POST",
             dataType: 'json',
             url:"scene_delete.action",
             data:$('#detailForm').serialize(),
             async: false,
             error: function(request) {
                 alert("Connection error");
             },
             success: function(data) {
           	  	alert(data.message); 
	           	//刷新当前tab
	           	var currTab = $('#tabs_index').tabs('getSelected'); 
	           	currTab.panel('refresh', '${pageContext.request.contextPath}/scene_toShowScene.action');

             }
         });
	}
	
	</script>
	
	<!-- 场景列表panel开始 -->
	<div style="float: left; width: 70%">
	<div id="sceneList" class="easyui-panel" title="场景列表" style="height:500px;"
				data-options="iconCls:'icon-save',collapsible:true,minimizable:true,maximizable:true,closable:true">
    <div id="imgs" style="margin-left: 20px; border-color: red;border: 2px;">
    	<c:forEach items="${queryResult.rows }" var="scene">
    		<div style="margin:10px 10px; width: 80px; float: left;">
	   			<div class="imgbd"><a href="javascript:;" onclick="showDetail('${scene.id}','${scene.sceneImg}','${scene.sceneName }');"><img alt="" src="${pageContext.request.contextPath}/upload/${scene.sceneImg}"></a></div>
	    		<div class="fontbd">${scene.sceneName }</div>
    		</div>
    	</c:forEach>
    	<div style="margin:10px 10px; width: 80px; float: left;" >
    		<div class="imgbd"><a href="javascript:;" onclick="$('#addSceneWindow').window('open');"><img alt="" src="${pageContext.request.contextPath}/upload/jiahao.png" ></a></div>
    		<div class="fontbd">增加场景</div>
    	</div>
    </div>
	</div>
	</div>
	<!-- 场景列表panel结束 -->
	
	
	<!-- 场景详情panel开始 -->
	<div style="float: right; width: 30%">
	<div id="sceneDetails" class="easyui-panel" title="场景详情" style="height:500px;"
				data-options="iconCls:'icon-save',collapsible:true,minimizable:true,maximizable:true,closable:true">
		<table id="detailTable" style="margin:30px 10px; width:320px;visibility:hidden " >
			<tr height="150px;">
				<td colspan="3">
					<div style="float: left; margin-left:125px; margin-top: 10px; width: 90px;height: 90px;" >
			 			<img id="imgDetail" src="">
			 		</div>
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<div style="text-align: center; font-size: 20px;">场景名称:</div>
				</td>
			</tr>
			<tr>
				<td colspan="3" style="text-align: center;">
				<form id="detailForm" action="" method="post">
					<input id="updateSceneId" type="hidden" name="scene.id">
					<input id="updateSceneImg" type="hidden" name="scene.sceneImg">
					<input id="updateSceneName" name="scene.sceneName" style="width: 130px;height: 30px; text-align: center; font-size: 20px;" class="easyui-validatebox textbox"  data-options="prompt:'场景名称.',required:true,validType:'length[1,10]'">
				</form>
				</td>
			</tr>
			<tr style="text-align:center; height: 80px;" >
				<td><a href="#" onclick="showEq();"  class="easyui-linkbutton" data-options="iconCls:'icon-search'" >查看详情</a></td>
				<td><a href="#" onclick="updateScene();" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存修改</a></td>
				<td><a href="#" onclick="delScene();" class="easyui-linkbutton" iconCls="icon-cancel">删除场景</a></td>
			</tr>
			
		</table>
	</div>			
	</div>
    <!-- 场景详情panel结束 -->
    
    
    
    
    <!--添加场景开始 -->
    <div id="addSceneWindow" class="easyui-window" title="添加场景" data-options="modal:true,closed:true,iconCls:'icon-save'" style="width:420px;height:500px;padding:10px;">
    	<div class="easyui-panel" style="width:390px;padding:10px 20px;border: 0px;" >
		<form id="sceneForm" action="scene_add.action" method="post">
			<input id="sceneImg" type="hidden" name="scene.sceneImg" value="" >
			<input id="sceneName" name="scene.sceneName" style="margin-left: 100px; width: 130px;height: 30px; text-align: center; font-size: 20px;" class="easyui-validatebox textbox"  data-options="prompt:'场景名称.',required:true,validType:'length[1,10]'">
		</form>
    	<div>
    	<table>
    		<tr>
    			<td>
    				<div class="imgdiv" id="d1">
			 			<img onclick="choice('zhuwo.png','主卧','d1');"  src="${pageContext.request.contextPath}/upload/zhuwo.png">
			 		</div>
    			</td>
    			<td class="imgtd" >
    				<div class="imgdiv" id="d2">
				 		<img onclick="choice('cheku.png','车库','d2');" src="${pageContext.request.contextPath}/upload/cheku.png">
			 		</div>
    			</td>
    			<td class="imgtd" >
    				<div class="imgdiv" id="d3">
				 		<img onclick="choice('keting.png','客厅','d3');" src="${pageContext.request.contextPath}/upload/keting.png" >
			 		</div>
    			</td>
    		</tr>
    		<tr>
    			<td class="imgtd" >
    				<div class="imgdiv" id="d4">
				 		<img onclick="choice('ciwo.png','次卧','d4');" src="${pageContext.request.contextPath}/upload/ciwo.png" >
			 		</div>
    			</td>
    			<td class="imgtd" >
    				<div class="imgdiv" id="d5">
						<img onclick="choice('yushi.png','浴室','d5');" src="${pageContext.request.contextPath}/upload/yushi.png" >
					</div>
    			</td>
    			<td class="imgtd" >
    				<div class="imgdiv" id="d6">
				 		<img onclick="choice('canting.png','厨房','d6');" src="${pageContext.request.contextPath}/upload/canting.png" >
			 		</div>
    			</td>
    		</tr>
    		<tr>
    			<td class="imgtd">
    				<div class="imgdiv" id="d7">
				 		<img onclick="choice('qita.png','其他','d7');" src="${pageContext.request.contextPath}/upload/qita.png">
			 		</div>
    			</td>
    		</tr>
    	</table>
    		<div style="padding-top: 20px;">
    			<a href="#" id="sceneSave" style="width: 80px; height: 40px; margin-left: 60px;" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存</a>
				<a href="#" id="return" style="width: 80px; height: 40px;margin-left: 40px;" class="easyui-linkbutton" data-options="iconCls:'icon-undo'" onclick=" $('#addSceneWindow').window('close', true);">返回</a>
    		</div>
    	</div>	
    		
    		
    	</div>
    </div>
    <!-- 添加场景结束 -->
    
  </body>
</html>
		