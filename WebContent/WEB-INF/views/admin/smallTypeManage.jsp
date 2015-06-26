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
							url : '${pageContext.request.contextPath}/smallType_list.action',
							idField : 'id',
							singleSelect : true,
							pageList:[10],
							columns : [ [
									{
										field : 'name',
										title : '商品小类名称',
										width : 200,
										align : 'center'
									},
									{
										field : 'bigType',
										title : '商品大类名称',
										width : 200,
										align : 'center',
										formatter: function(value,rowData,rowIndex){
											return rowData.bigType.name;
										}
									},
									{
										field : 'remarks',
										title : '备注',
										width : 300,
										align : 'center'
									},
									{
										field : 'opt',
										title : '操作',
										width : 400,
										align : 'center',
										formatter : function(value, rec) {
											return "<a href='javascript:updateSmallType(\""
													+ rec.id
													+ "\")'>修改</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href='javascript:deleteSmallType(\""
													+ rec.id + "\")'>删除</a>";
										}
									} ] ],
							pagination : true,
							rownumbers : true
						});
	});

	// 根据名称查询
	function doSearchSmallType() {
		$("#dg").datagrid("load", {
			"s_smallType.name" : $("#s_name").val()
		});
	}

	// 删除商品小类
	function deleteSmallType(id) {
		$.messager.confirm("系统提示", "您确定要删除该记录么?", function(result) {
			if (result) {
				$.ajax({
					url : "smallType_delete.action",
					type : "post",
					dataType : "json",
					data : {
						"smallType.id" : id
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

	// 新增或修改商品小类
	function updateSmallType(id) {
		var options = {
			width : 550,
			height : 300
		};
		if (id == 0) {
			options.title = "新增商品小类";
		} else {
			options.title = "修改商品小类";
		}
		showWin("smallType_toSmallTypeUpdate.action?smallType.id=" + id, options);
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
						<td style="text-align: right; width: 80px;">大类名称:</td>
						<td style="width: 150px;"><input type="text" id="s_name"
							size="20" onkeydown="if(event.keyCode==13){doSearchBigType()}" />
						</td>
						<td><a href="javascript:void(0)" onclick="doSearchSmallType();"
							style="margin-left: 10px;">查询</a> <a href="javascript:void(0)"
							onclick="updateSmallType(0);" style="margin-left: 10px;">新增</a></td>
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