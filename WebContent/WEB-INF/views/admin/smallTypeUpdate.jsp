<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
<title>Insert title here</title>
<script type="text/javascript">
	$(function() {
		if ("${smallType.id}" != 0) {
			$("#bName").combobox("setValue", "${smallType.bigType.id}");
		} else {
			$("#bName").combobox("select", "");
		}
	});

	//提交表单
	function submitForm() {
		if ($("#fm").form("validate")) {
			if($('#bName').combobox("getValue")==""){
				$.messager.alert("系统提示","请选择商品大类");
				return false;
			}
			$('#fm').ajaxSubmit({
				url : "smallType_saveSmallType.action",
				type : "post",
				dataType : "json",
				success : function(result) {
					if (result.success) {
						$.messager.alert("系统提示", "保存成功");
						setTimeout(function() {
							parent.closeWin();
						}, 1000);
						parent.reload();
					} else {
						$.messager.alert("系统提示", "保存失败");
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
		<form id="fm" method="post">
			<table cellspacing="8px;">
				<tr>
					<td>所属大类：</td>
					<td colspan="4"><select class="easyui-combobox" id="bName"
						style="width: 152px;" name="smallType.bigType.id"
						data-options="panelHeight:'auto',editable:false,valueField:'id',textField:'name',url:'bigType_comboList.action'">

					</select></td>
				</tr>
				<tr>
					<td>小类名称：</td>
					<td colspan="4"><input type="text" id="name"
						name="smallType.name" class="easyui-validatebox" required="true"
						value="${smallType.name}" /> <input type="hidden"
						name="smallType.id" value="${smallType.id}"></td>
				</tr>
				<tr>
					<td valign="top">备注：</td>
					<td colspan="4"><textarea rows="7" cols="50"
							name="smallType.remarks" id="remarks"></textarea> <input
						type="button" style="display: none;" id="submit"
						onclick="javascript:submitForm();"></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>