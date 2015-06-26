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
							pageList:[10],
							striped : true,
							loadMsg : "玩命加载中..",
							url : '${pageContext.request.contextPath}/bigType_list.action',
							idField : 'id',
							singleSelect : true,
							onLoadSuccess:function(data){
								$(this).datagrid('doCellTip',{'max-width':'300px','delay':500});
							},
							columns : [ [
									{
										field : 'name',
										title : '商品大类名称',
										width : 300,
										align : 'center'
									},
									{
										field : 'remark',
										title : '备注',
										width : 300,
										align : 'center'
									},
									{
										field : 'opt',
										title : '操作',
										width : 521,
										align : 'center',
										formatter : function(value, rec) {
											return "<a href='javascript:updateBigType(\""
													+ rec.id
													+ "\")'>修改</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href='javascript:deleteBigType(\""
													+ rec.id + "\")'>删除</a>";
										}
									} ] ],
							pagination : true,
							rownumbers : true
						});
	});

	// 根据大类名称查询
	function doSearchBigType() {
		$("#dg").datagrid("load", {
			"s_bigType.name" : $("#s_name").val()
		});
	}

	// 删除商品大类
	function deleteBigType(id) {
		$.messager.confirm("系统提示", "您确定要删除该记录么?", function(result) {
			if (result) {
				$.ajax({
					url : "bigType_delete.action",
					type : "post",
					dataType : "json",
					data : {
						"bigType.id" : id
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

	// 新增或修改大类
	function updateBigType(id) {
		var options = {
			width : 550,
			height : 300
		};
		if (id == 0) {
			options.title = "新增商品大类";
		} else {
			options.title = "修改商品大类";
		}
		showWin("bigType_toBigTypeUpdate.action?bigType.id=" + id, options);
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
						<td><a href="javascript:void(0)" onclick="doSearchBigType();"
							style="margin-left: 10px;">查询</a> <a href="javascript:void(0)"
							onclick="updateBigType(0);" style="margin-left: 10px;">新增</a></td>
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