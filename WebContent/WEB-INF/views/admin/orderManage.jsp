<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../../common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	$(function() {
		$("#dg")
				.datagrid(
						{
							iconCls : ' ',
							width : $(window).width() - 10,
							height : $(window).height() - 80,
							nowrap : true,
							pageList : [ 10 ],
							striped : true,
							loadMsg : "玩命加载中..",
							url : '${pageContext.request.contextPath}/order_list.action',
							idField : 'id',
							singleSelect : true,
							onLoadSuccess : function(data) {
								$(this).datagrid('doCellTip', {
									'max-width' : '300px',
									'delay' : 500
								});
							},
							columns : [ [
									{
										field : 'orderNo',
										title : '订单号',
										width : 200,
										align : 'center'
									},
									{
										field : 'user',
										title : '订单人用户名',
										width : 150,
										align : 'center',
										formatter : function(value, rowData,
												rowIndex) {
											return rowData.user.userName;
										}
									},
									{
										field : 'createTime',
										title : '创建时间',
										width : 150,
										align : 'center'
									},
									{
										field : 'cost',
										title : '总金额',
										width : 150,
										align : 'center'
									},
									{
										field : 'opt',
										title : '操作',
										width : 469,
										align : 'center',
										formatter : function(value, rec) {
											return "<a href='javascript:showDetail(\""
													+ rec.id
													+ "\")'>查看订单详情</a>&nbsp;&nbsp;"
													+ "<a href='javascript:changeStatus(\""
													+ rec.id
													+ "\",2)'>审核通过</a>"
													+ "&nbsp;&nbsp;<a href='javascript:changeStatus(\""
													+ rec.id
													+ "\",3)'>卖家已发货</a>";
										}
									} ] ],
							pagination : true,
							rownumbers : true
						});
	});

	// 根据大类名称查询
	function doSearchOrder() {
		$("#dg").datagrid("load", {
			"s_order.orderNo" : $("#s_orderNo").val(),
			"s_order.user.userName" : $("#s_userName").val()
		});
	}

	// 更改订单状态
	function changeStatus(id, status) {
		var statusStr = status == 2 ? "审核通过" : "卖家已发货";
		$.messager.confirm("系统提示", "您确定要修改成" + statusStr + "状态么?", function(
				result) {
			if (result) {
				$.ajax({
					url : "order_changeStatus.action",
					type : "post",
					dataType : "json",
					data : {
						"orderManage.id" : id,
						"orderManage.status" : status
					},
					cache : false,
					success : function(result) {
						if (result.success) {
							$.messager.alert("系统提示", "修改成功！");
							$("#dg").datagrid("reload");
						} else {
							$.messager.alert(result.errorMsg);
						}
					}
				});
			}
		});
	}

	// 查看订单详细信息
	function showDetail(id) {
		var options = {
			width : 700,
			height : 450,
			title : "订单详情"
		};
		showWin("order_toShowDetail.action?orderManage.id=" + id, options);
	}

	// 刷新表格
	function reload() {
		$("#dg").datagrid("reload");
	}
</script>
</head>
<body>
	<div>
		<div>
			<form id="query_form" method="post">
				<table width="100%" border="0" cellspacing="0" cellpadding="4"
					style="background: #e9f8ff; border: 1px solid #cbd9e4; font-size: 12px;">
					<tr>
						<td style="text-align: right; width: 80px;">订单号:</td>
						<td style="width: 150px;"><input type="text" id="s_orderNo"
							size="20" onkeydown="if(event.keyCode==13){doSearchOrder()}" />
						</td>
						<td style="text-align: right; width: 80px;">买家名称:</td>
						<td style="width: 150px;"><input type="text" id="s_userName"
							size="20" onkeydown="if(event.keyCode==13){doSearchOrder()}" />
						</td>
						<td><a href="javascript:void(0)" onclick="doSearchOrder();"
							style="margin-left: 10px;">查询</a>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div>
			<table id="dg">
			</table>
		</div>
	</div>
</body>
</html>