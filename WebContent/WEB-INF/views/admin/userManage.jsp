<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../../../common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	$(function(){
		$("#dg").datagrid({
			iconCls : ' ',
			width : $(window).width()-10,
			height : $(window).height() - 80,
			nowrap : true,
			striped : true,
			pageList:[10],
			loadMsg:"玩命加载中..",
			url : '${pageContext.request.contextPath}/user_list.action',
			idField : 'id',
			singleSelect : true,
			columns : [ [
					{
						field : 'trueName',
						title : '真实姓名',
						width : 100,
						align : 'center'
					},
					{
						field : 'userName',
						title : '用户名',
						width : 100,
						align : 'center'
					},
					{
						field : 'password',
						title : '用户密码',
						width : 100,
						align : 'center'
					},
					{
						field : 'sex',
						title : '性别',
						width : 50,
						align : 'center'
					},
					{
						field : 'birthday',
						title : '出生日期',
						width : 100,
						align : 'center'
					},
					{
						field : 'dentityCode',
						title : '身份证',
						width : 150,
						align : 'center'
					},
					{
						field : 'email',
						title : '邮件',
						width : 150,
						align : 'center'
					},
					{
						field : 'mobile',
						title : '联系电话',
						width : 100,
						align : 'center'
					},
					{
						field : 'address',
						title : '收货地址',
						width : 100,
						align : 'center'
					},{
						field:'opt',
						title : '操作',
						width : 165,
						align : 'center',
						formatter : function(value, rec) {
							return "<a href='javascript:updateUser(\""
									+ rec.id
									+ "\")'>修改</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href='javascript:deleteUser(\""
									+ rec.id + "\")'>删除</a>";
						}
					}
					 ] ],
			pagination : true,
			rownumbers : true
		});
	});
	
	// 根据用户名查询
	function doSearchUser(){
		$("#dg").datagrid("load",{
			"s_user.userName":$("#s_userName").val()
		});
	}
	
	// 删除用户
	function deleteUser(id){
		$.messager.confirm("系统提示","您确定要删除该记录么?",function(result){
			if(result){
				$.ajax({
					url:"user_delete.action",
					type:"post",
					dataType:"json",
					data:{
						"s_user.id":id
					},
					cache:false,
					success:function(result){
						if(result.success){
							$.messager.alert("系统提示","删除成功！");
							$("#dg").datagrid("reload");
						}else{
							$.messager.alert('系统提示',"删除失败!");
						}
					}
				});
			}
		});
	}
	
	// 新增或修改用户
	function updateUser(id){
		var options={
			width:650,height:400
		};
		if(id==0){
			options.title="新增用户";
		}else{
			options.title="修改用户";
		}
		showWin("user_toUserUpdate.action?user.id="+id,options);
	}
	
	// 刷新表格
	function reload(){
		$("#dg").datagrid("reload");
	}
	
</script>
</head>
<body>
<div>
	<div>
		<form id="query_form" method="post">
			<table width="100%" border="0" cellspacing="0" cellpadding="4"
					style="background:#e9f8ff; border:1px solid #cbd9e4; font-size:12px;">
				<tr>
					<td style="text-align:right;width:80px;">用户名:</td>
					<td style="width: 150px;">
						<input type="text" id="s_userName"  size="20" onkeydown="if(event.keyCode==13){doSearchUser()}"/>
					</td>
					<td>
						<a href="javascript:void(0)" onclick="doSearchUser();"
							style="margin-left: 10px;">查询</a> <a href="javascript:void(0)"
							onclick="updateUser(0);" style="margin-left: 10px;">新增</a>
					</td>	
				</tr>
			</table>
		</form>
	</div>
	<div>
		<table id="dg" >
		</table>
	</div>
</div>
</body>
</html>