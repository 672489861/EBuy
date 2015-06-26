<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../../../common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>易买网后台管理</title>
<script type="text/javascript">
$(function(){
	$("#lastDiv>a").unbind("click");
});

// 安全退出
function logout(){
	$.messager.confirm('系统提示','您确定要退出系统吗？',function(r){
		if(r){
			window.location.href='user_adminLoginOut.action';
		}
	});
}

// 刷新系统缓存
function refreshSystem(){
	$.post("sys_refreshSystem.action",{},function(result){
		if(result.success){
			$.messager.alert("系统提示","已成功刷新系统缓存！");							
		}else{
			$.messager.alert('系统提示','刷新系统缓存');
		}
	},"json");
}

// 打开修改密码对话框
function openPasswordModifyDialog(){
	$("#dlg").dialog("open").dialog("setTitle","修改密码");
}

// 关闭修改密码
function closePasswordModifyDialog(){
	$("#dlg").dialog("close");
	$("#oldPassword").val("");
	$("#newPassword").val("");
	$("#newPassword2").val("");
}

// 修改密码
function modifyPassword(){
	$("#fm").form("submit",{
		url:"sys_modifyPassword.action?user.id=${currentUser.id}",
		onSubmit:function(){
			var oldPassword=$("#oldPassword").val();
			var newPassword=$("#newPassword").val();
			var newPassword2=$("#newPassword2").val();
			if(!$(this).form("validate")){
				return false;
			}
			if(oldPassword!='${currentUser.password}'){
				$.messager.alert('系统提示','用户密码输入错误！');
				return false;
			}
			if(newPassword!=newPassword2){
				$.messager.alert('系统提示','确认密码输入错误！');
				return false;
			}
			return true;
		},
		success:function(result){
			result = eval('('+result+')');
			if(result.success){
				$.messager.alert('系统提示','密码修改成功，下一次登录生效！');
				closePasswordModifyDialog();
			}else{
				$.messager.alert('系统提示','修改密码失败');
				return;
			}
		}
	});
}
</script>
</head>
<body class="easyui-layout">

<!-- 上 -->
<div data-options="region:'north'" style="height: 78px;background-color: #E0ECFF">
	<table style="padding: 5px;" width="100%">
		<tr>
			<td width="50%">
				<img src="${pageContext.request.contextPath}/images/bglogo.png">
			</td>
			<td valign="bottom" align="right" width="50%">
				<font size="3">&nbsp;&nbsp;<strong>欢迎:</strong>${currentUser.userName}</font>
			</td>
		</tr>
	</table>
</div>

<!-- 中 -->
<div data-options="region:'center'" style="height: 78px;background-color: #E0ECFF">
	<div class="easyui-tabs" fit="true" border="false" id="tabs">
		<div title="首页" data-options="iconCls:'icon-home'">
			<div align="center" style="padding-top: 100px;">
				<font color="red" size="10">欢迎使用</font>
			</div>
		</div>
	</div>
</div>

<!-- 左 -->
<div data-options="region:'west'" title="导航菜单" style="width: 200px;" split="true"> 
	<div class="easyui-accordion" id="accordionDiv" data-options="fit:true,border:false">
		<div title="用户管理" data-options="selected:true,iconCls:'icon-user'" style="padding:10px;">
			<a href="javascript:void(0)" id="userManager" url="${pageContext.request.contextPath}/user_toUserManager.action" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 150px;">管理用户</a>
		</div>
		<div title="商品管理"  data-options="iconCls:'icon-product'" style="padding:10px;">
			<a href="javascript:void(0)" url="${pageContext.request.contextPath}/product_toManager.action" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 150px;">管理商品</a>
			<a href="javascript:void(0)" url="${pageContext.request.contextPath}/bigType.action" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 150px;">管理商品大类</a>
			<a href="javascript:void(0)" url="${pageContext.request.contextPath}/smallType.action" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 150px;">管理商品小类</a>
		</div>
		<div title="订单管理"  data-options="iconCls:'icon-order'" style="padding:10px">
			<a href="javascript:void(0)" url="${pageContext.request.contextPath}/order_toOrderManager.action" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 150px;">管理订单</a>
		</div>
		<div title="留言管理" data-options="iconCls:'icon-comment'" style="padding:10px">
			<a href="javascript:void(0)" url="${pageContext.request.contextPath}/comment_toCommentManager.action"  class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 150px;">管理留言</a>
		</div>
		<div title="公告管理"  data-options="iconCls:'icon-notice'" style="padding:10px">
			<a href="javascript:void(0)" url="${pageContext.request.contextPath}/notice_toNoticeManager.action"  class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 150px;">管理公告</a>
		</div>
		<div title="新闻管理"  data-options="iconCls:'icon-news'" style="padding:10px">
			<a href="javascript:void(0)" url="${pageContext.request.contextPath}/news_toNewsManager.action"  class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 150px;">管理新闻</a>
		</div>
		<div title="标签管理"  data-options="iconCls:'icon-tag'" style="padding:10px">
			<a href="javascript:void(0)" url="${pageContext.request.contextPath}/tag.action" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 150px;">管理标签</a>
		</div>
		<div title="系统管理"  data-options="iconCls:'icon-item'" style="padding:10px" id="lastDiv">
			<a href="javascript:openPasswordModifyDialog()" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-modifyPassword'" style="width: 150px;">修改密码</a>
			<a href="javascript:logout();" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-exit'" style="width: 150px;">安全退出</a>
			<a href="javascript:refreshSystem();" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-refresh'" style="width: 150px;">刷新系统缓存</a>
		</div>
	</div>
</div>

<!-- 下 -->
<div data-options="region:'south'" style="height: 25px;padding:5px;background-color: #E0ECFF" align="center">
	Copyright &copy; 2015 热血传奇  All Rights Reserved.
		<a onclick="javascript:window.open('http://bdtg.37.com/s/1/1945/33922.html?uid=linuxidc.com&wd=0&ab_type=0&baidu_key=e0c257a18f9a7a14')" href="#">无锡人的传奇</a>
</div>

<div id="dlg" class="easyui-dialog" style="width: 400px;height: 250px;padding: 10px 20px" 
 closed="true" buttons="#dlg-buttons" >
 <form id="fm" method="post">
 	<table cellspacing="4px;">
 		<tr>
 			<td>用户名：</td>
 			<td><input type="hidden" name="currentUser.id" id="id" value="${currentUser.id }"><input type="text" name="user.userName" id="userName" readonly="readonly" value="${currentUser.userName }" style="width: 200px;" /></td>
 		</tr>
 		<tr>
 			<td>原密码：</td>
 			<td><input type="password" class="easyui-validatebox" name="oldPassword" id="oldPassword" style="width: 200px;" required="true" /></td>
 		</tr>
 		<tr>
 			<td>新密码：</td>
 			<td><input type="password" class="easyui-validatebox" name="user.password" id="newPassword" style="width: 200px;" required="true"  /></td>
 		</tr>
 		<tr>
 			<td>确认新密码：</td>
 			<td><input type="password" class="easyui-validatebox" name="newPassword2" id="newPassword2" style="width: 200px;" required="true" /></td>
 		</tr>
 	</table>
 </form>
</div>
<div id="dlg-buttons">
	<a href="javascript:modifyPassword()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
	<a href="javascript:closePasswordModifyDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>
</body>
</html>