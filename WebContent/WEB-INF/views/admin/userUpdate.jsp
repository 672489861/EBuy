<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
<title>Insert title here</title>
<script type="text/javascript">
	$(function() {
		$("#sex").combobox("setValue","${user.sex}");
	});

	//提交表单
	function submitForm(){
		if ($("#fm").form("validate")) {
			$('#fm').ajaxSubmit({
				url:"user_saveUser.action",
				type:"post",
				dataType : "json",
				success:function(result){
					if(result.success){
						$.messager.alert("系统提示","保存成功");
						setTimeout(function(){
							parent.closeWin();
						}, 1000);
						parent.reload();
					}else{
						$.messager.alert("系统提示","保存失败");
						return;
					}
				}
			});
		}
	}
</script>
</head>
<body>
	<div>
		<form id="fm" method="post" >
			<table cellspacing="8px;">
				<tr>
					<td>真实姓名：</td>
					<td><input type="text" id="trueName" name="user.trueName"
						class="easyui-validatebox" required="true"
						value="${user.trueName}" />
					</td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>用户名：</td>
					<td><input type="text" id="userName" name="user.userName"
						class="easyui-validatebox" required="true"
						value="${user.userName}" />
					</td>
				</tr>
				<tr>
					<td>密码：</td>
					<td><input type="text" id="password" name="user.password"
						class="easyui-validatebox" required="true"
						value="${user.password}" />
					</td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>性别：</td>
					<td><select class="easyui-combobox" id="sex" name="user.sex"
						style="width: 154px;" editable="false" panelHeight="auto">
							<option value="">请选择性别</option>
							<option value="男">男</option>
							<option value="女">女</option>
					</select></td>
				</tr>
				<tr>
					<td>出生日期：</td>
					<td><input type="text" id="birthday" name="user.birthday"
						class="easyui-datebox" editable="false" required="true"
						value="<fmt:formatDate value="${user.birthday}" pattern="yyyy-MM-dd" type="date"/>" />
						
					</td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>身份证：</td>
					<td><input type="text" id="dentityCode"
						name="user.dentityCode" class="easyui-validatebox" required="true"
						value="${user.dentityCode}" />
					</td>
				</tr>
				<tr>
					<td>邮件：</td>
					<td><input type="text" id="email" name="user.email"
						class="easyui-validatebox" validType="email" required="true"
						value="${user.email}" />
					</td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>联系电话：</td>
					<td><input type="text" id="mobile" name="user.mobile"
						class="easyui-validatebox" required="true" value="${user.mobile}" />
					</td>
				</tr>
				<tr>
					<td>收货地址：</td>
					<td colspan="4"><input type="text" id="address"
						name="user.address" class="easyui-validatebox" required="true"
						style="width: 350px;" value="${user.address}" /> <input
						type="hidden" name="user.id" value="${user.id}">
						<input type="button" id="submit" style="display: none;" onclick="javascript:submitForm();" value="提交">
						</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>