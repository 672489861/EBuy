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
	//提交表单
	function submitForm() {
		if ($("#fm").form("validate")) {
			$('#fm').ajaxSubmit({
				url : "tag_saveTag.action",
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
					<td>标签名称：</td>
					<td colspan="4"><input type="text" id="name" name="tag.name"
						class="easyui-validatebox" required="true" value="${tag.name}"/>
					</td>
				</tr>
				<tr>
					<td>标签地址：</td>
					<td colspan="4"><input type="text" id="url" name="tag.url"
						class="easyui-validatebox" required="true" validtype="url"
						style="width: 300px;" value="${tag.url}"/>
						<input type="hidden" name="tag.id" value="${tag.id}">
						<input type="button" style="display: none;" id="submit"  onclick="javascript:submitForm();">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>