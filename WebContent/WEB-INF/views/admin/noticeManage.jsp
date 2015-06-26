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
							url : '${pageContext.request.contextPath}/notice_list.action',
							idField : 'id',
							singleSelect : true,
							onLoadSuccess:function(data){
								$(this).datagrid('doCellTip',{'max-width':'300px','delay':500});
							},
							columns : [ [
									{
										field : 'title',
										title : '公告名称',
										width : 200,
										align : 'center'
									},
									{
										field : 'createTime',
										title : '创建时间',
										width : 200,
										align : 'center'
									},
									{
										field : 'content',
										title : '内容',
										width : 250,
										align : 'center'
									},
									{
										field : 'opt',
										title : '操作',
										width : 461,
										align : 'center',
										formatter : function(value, rec) {
											return "<a href='javascript:updateNotice(\""
													+ rec.id
													+ "\")'>修改</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href='javascript:deleteNotice(\""
													+ rec.id + "\")'>删除</a>";
										}
									} ] ],
							pagination : true,
							rownumbers : true
						});
	});

	// 根据公告名称查询
	function doSearchNotice() {
		$("#dg").datagrid("load", {
			"s_notice.title" : $("#s_name").val()
		});
	}

	// 删除公告
	function deleteNotice(id) {
		$.messager.confirm("系统提示", "您确定要删除该标签么?", function(result) {
			if (result) {
				$.ajax({
					url : "notice_delete.action",
					type : "post",
					dataType : "json",
					data : {
						"noticeManage.id" : id
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

	// 新增或修改公告
	function updateNotice(id) {
		var options = {
			width : 750,
			height : 450
		};
		if (id == 0) {
			options.title = "新增标签";
		} else {
			options.title = "修改标签";
		}
		showWin("notice_toNoticeUpdate.action?noticeManage.id=" + id, options);
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
						<td style="text-align: right; width: 80px;">标签名称:</td>
						<td style="width: 150px;"><input type="text" id="s_name"
							size="20" onkeydown="if(event.keyCode==13){doSearchNotice()}" />
						</td>
						<td><a href="javascript:void(0)" onclick="doSearchNotice();"
							style="margin-left: 10px;">查询</a> <a href="javascript:void(0)"
							onclick="updateNotice(0);" style="margin-left: 10px;">新增</a></td>
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