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
							striped : true,
							loadMsg : "玩命加载中..",
							url : '${pageContext.request.contextPath}/product_list.action',
							idField : 'id',
							singleSelect : true,
							onLoadSuccess:function(data){
								$(this).datagrid('doCellTip',{'max-width':'300px','delay':500});
							},
							columns : [ [
									{
										field : 'proPic',
										title : '商品图片',
										width : 150,
										align : 'center',
										formatter : function(value, rowData,
												rowIndex) {
											return "<img width=100 height=100 src='${pageContext.request.contextPath}/"+value+"'/>";
										}
									},
									{
										field : 'name',
										title : '商品名称',
										width : 200,
										align : 'center'
									},
									{
										field : 'price',
										title : '价格',
										width : 50,
										align : 'center'
									},
									{
										field : 'stock',
										title : '库存',
										width : 50,
										align : 'center'
									},
									{
										field : 'smallType',
										title : '所属商品小类',
										width : 100,
										align : 'center',
										formatter : function(value, rowData,
												rowIndex) {
											return rowData.smallType.name;
										}
									},
									{
										field : 'bigType',
										title : '所属商品大类',
										width : 50,
										align : 'center',
										formatter : function(value, rowData,
												rowIndex) {
											return rowData.bigType.name;
										}
									},
									{
										field : 'hot',
										title : '是否热卖',
										width : 50,
										align : 'center',
										formatter : function(value, rowData,
												rowIndex) {
											return value == 1 ? "是" : "否";
										}
									},
									{
										field : 'specialPrice',
										title : '是否特价',
										width : 50,
										align : 'center',
										formatter : function(value, rowData,
												rowIndex) {
											return value == 1 ? "是" : "否";
										}
									},
									{
										field : 'opt',
										title : '操作',
										width : 400,
										align : 'center',
										formatter : function(value, rec) {
											return "<a href='javascript:updateProduct(\""
													+ rec.id
													+ "\")'>修改</a>&nbsp;&nbsp;<a href='javascript:deleteProduct(\""
													+ rec.id
													+ "\")'>删除</a>&nbsp;&nbsp;<a href='javascript:setHotProduct(\""
													+ rec.id
													+ "\")'>设置为热卖</a>&nbsp;&nbsp;<a href='javascript:setSpeicalProduct(\""
													+ rec.id + "\")'>设置为特价</a>";
										}
									} ] ],
							pagination : true,
							rownumbers : true,
							pageList : [ 10 ]
						});
	});

	// 根据用户名查询
	function doSearchProduct() {
		$("#dg").datagrid("load", {
			"s_productManager.name" : $("#s_name").val()
		});
	}

	// 删除用户
	function deleteProduct(id) {
		$.messager.confirm("系统提示", "您确定要删除该记录么?", function(result) {
			if (result) {
				$.ajax({
					url : "product_delete.action",
					type : "post",
					dataType : "json",
					data : {
						"productManager.id" : id
					},
					cache : false,
					success : function(result) {
						if (result.success) {
							$.messager.alert("系统提示", "删除成功！");
							$("#dg").datagrid("reload");
						} else {
							$.messager.alert(result.errorMsg);
						}
					}
				});
			}
		});
	}
	
	// 设置为热卖
	function setHotProduct(id) {
		$.messager.confirm("系统提示", "您确定要设置该商品为热卖么?", function(result) {
			if (result) {
				$.ajax({
					url : "product_setHot.action",
					type : "post",
					dataType : "json",
					data : {
						"productManager.id" : id
					},
					cache : false,
					success : function(result) {
						if (result.success) {
							$.messager.alert("系统提示", "设置成功！");
							$("#dg").datagrid("reload");
						} else {
							$.messager.alert(result.errorMsg);
						}
					}
				});
			}
		});
	}
	
	// 设置为特价
	function setSpeicalProduct(id) {
		$.messager.confirm("系统提示", "您确定要设置该商品为特价么?", function(result) {
			if (result) {
				$.ajax({
					url : "product_setSpecial.action",
					type : "post",
					dataType : "json",
					data : {
						"productManager.id" : id
					},
					cache : false,
					success : function(result) {
						if (result.success) {
							$.messager.alert("系统提示", "设置成功！");
							$("#dg").datagrid("reload");
						} else {
							$.messager.alert(result.errorMsg);
						}
					}
				});
			}
		});
	}
	
	// 新增或修改用户
	function updateProduct(id) {
		var options = {
			width : 650,
			height : 400
		};
		if (id == 0) {
			options.title = "新增商品";
		} else {
			options.title = "修改商品";
		}
		showWin("product_toProductUpdate.action?productManager.id=" + id, options);
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
						<td style="text-align: right; width: 80px;">商品名称:</td>
						<td style="width: 150px;"><input type="text" id="s_name"
							size="20" onkeydown="if(event.keyCode==13){doSearchProduct()}" />
						</td>
						<td><a href="javascript:void(0)" onclick="doSearchProduct();"
							style="margin-left: 10px;">查询</a> <a href="javascript:void(0)"
							onclick="updateProduct(0);" style="margin-left: 10px;">新增</a>
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