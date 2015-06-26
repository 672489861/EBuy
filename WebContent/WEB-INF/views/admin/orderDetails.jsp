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
		$("#status").val(formatStatus("${orderManage.status}"));

		$("#dg2")
				.datagrid(
						{
							iconCls : ' ',
							width : $(window).width() - 10,
							height : $(window).height() - 80,
							nowrap : true,
							pageList : [ 10 ],
							title : "订单商品列表",
							striped : true,
							loadMsg : "玩命加载中..",
							url : 'order_findProductListByOrderId.action?orderId='
									+ "${orderManage.id}",
							idField : 'id',
							singleSelect : true,
							columns : [ [
									{
										field : 'name',
										title : '商品名称',
										width : 200,
										align : 'center'
									},
									{
										field : 'proPic',
										title : '商品图片',
										width : 120,
										align : 'center',
										formatter : function(value, rowData,
												rowIndex) {
											return "<img width=100 height=100 src='${pageContext.request.contextPath}/"+value+"'/>";
										}
									}, {
										field : 'price',
										title : '商品价格',
										width : 100,
										align : 'center'
									}, {
										field : 'num',
										title : '商品数量',
										width : 100,
										align : 'center'
									}, {
										field : 'subtotal',
										title : '小计',
										width : 100,
										align : 'center'
									} ] ],
							pagination : true,
							rownumbers : true
						});
	});

	function formatStatus(val) {
		if (val == 1) {
			return "待审核";
		} else if (val == 2) {
			return "审核通过";
		} else if (val == 3) {
			return "卖家已发货";
		} else if (val == 4) {
			return "交易已完成";
		}
		return "";
	}
</script>
</head>
<body>
	<table cellspacing="8px;">
		<tr>
			<td>订单号：</td>
			<td><input type="text" id="orderNo" readonly="readonly"
				value="${orderManage.orderNo}" /></td>
			<td>&nbsp;</td>
			<td>订单人：</td>
			<td><input type="text" id="user" readonly="readonly"
				value="${orderManage.user.userName}" /></td>
		</tr>
		<tr>
			<td>总金额：</td>
			<td><input type="text" id="cost" readonly="readonly"
				value="${orderManage.cost}" /></td>
			<td>&nbsp;</td>
			<td>订单状态：</td>
			<td><input type="text" id="status" readonly="readonly" />
			</td>
		</tr>
	</table>
	<br />
	<table id="dg2">
	</table>
</body>
</html>