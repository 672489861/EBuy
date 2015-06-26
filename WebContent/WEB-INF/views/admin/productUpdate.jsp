<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		$("#bName").combobox({
			"onChange":function(newValue, oldValue){
				 $("#sName").combobox({  
	                    url:'${pageContext.request.contextPath}/smallType_comboList.action?bigTypeId='+newValue
	             }).combobox('clear').combobox("setValue","");  
			}
		});
		
		if ("${productManager.bigType.id}" != 0) {
			$("#bName").combobox("setValue", "${productManager.bigType.id}");
		} else {
			$("#bName").combobox("select", "");
		}
		if ("${productManager.smallType.id}" != 0) {
			$("#sName").combobox("setValue", "${productManager.smallType.id}");
		} else {
			$("#sName").combobox("select", "");
		}
	});

	//提交表单
	function submitForm() {
		if ($("#fm").form("validate")) {
			if ($('#bName').combobox("getValue") == "") {
				$.messager.alert("系统提示", "请选择商品大类");
				return false;
			}
			if ($('#sName').combobox("getValue") == "") {
				$.messager.alert("系统提示", "请选择商品小类");
				return false;
			}
			$('#fm').ajaxSubmit({
				url : "product_saveProduct.action",
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
					<td>商品名称：</td>
					<td colspan="4"><input class="easyui-validatebox" id="name"
						name="productManager.name" required="true" style="width: 300px;"
						value="${productManager.name}" /></td>
				</tr>
				<tr>
					<td>价格：</td>
					<td colspan="4"><input class="easyui-validatebox" id="price"
						name="productManager.price" required="true"
						value="${productManager.price}" />
					</td>
				</tr>
				<tr>
					<td>库存：</td>
					<td colspan="4"><input class="easyui-validatebox" id="stock"
						name="productManager.stock" required="true"
						value="${productManager.stock}" />
					</td>
				</tr>
				<tr>
					<td>商品图片：</td>
					<td colspan="4">
						<input id="pP" name="proPic" type="file" value="${productManager.proPic}"/>
					</td>
				</tr>
				<tr>
					<td>所属大类：</td>
					<td colspan="4"><select class="easyui-combobox" id="bName"
						style="width: 152px;" name="productManager.bigType.id"
						data-options="editable:'false',panelHeight:'auto',editable:false,valueField:'id',textField:'name',url:'bigType_comboList.action'">
					</select>
					</td>
				</tr>
				<tr>
					<td>所属小类：</td>
					<td colspan="4"><select class="easyui-combobox" id="sName"
						name="productManager.smallType.id"
						data-options="editable:'false',panelHeight:'auto',editable:false,valueField:'id',textField:'name'">
							<option value="">请先选择大类</option>
					</select></td>
				</tr>
				<tr>
					<td valign="top">备注：</td>
					<td colspan="4"><textarea rows="7" cols="50"
							name="productManager.description" id="description"></textarea> 
						<input type="hidden" name="productManager.id" value="${productManager.id}">
						<input type="hidden" id="proPic" name="productManager.proPic" value="${productManager.proPic}"/> 
						<input type="hidden" id="hot" name="productManager.hot" value="${productManager.hot}"/> 
						<input type="hidden" id="specialPrice" name="productManager.specialPrice" value="${productManager.specialPrice}"/>
						<input type="hidden" id="hotTime" name="productManager.hotTime" value="<fmt:formatDate value="${productManager.hotTime}" pattern="yyyy-MM-dd" type="date"/>"/> 
						<input type="hidden" id="specialPriceTime" name="productManager.specialPriceTime" value="<fmt:formatDate value="${productManager.specialTime}" pattern="yyyy-MM-dd" type="date"/>"/></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>