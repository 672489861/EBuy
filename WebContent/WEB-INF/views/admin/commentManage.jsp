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
							url : '${pageContext.request.contextPath}/comment_list.action',
							idField : 'id',
							singleSelect : true,
							onLoadSuccess:function(data){
								$(this).datagrid('doCellTip',{'max-width':'300px','delay':500});
							},
							columns : [ [
									{
										field : 'content',
										title : '留言内容',
										width : 250,
										align : 'center'
									},
									{
										field : 'nickName',
										title : '留言人昵称',
										width : 150,
										align : 'center'
									},
									{
										field : 'createTime',
										title : '创建时间',
										width : 150,
										align : 'center'
									},
									{
										field : 'replyContent',
										title : '回复内容',
										width : 250,
										align : 'center'
									},
									{
										field : 'replyTime',
										title : '回复时间',
										width : 150,
										align : 'center'
									},
									{
										field : 'opt',
										title : '操作',
										width : 170,
										align : 'center',
										formatter : function(value, rec) {
											return "<a href='javascript:deleteComment(\""
													+ rec.id + "\")'>删除</a>";
										}
									} ] ],
							pagination : true,
							rownumbers : true
						});
	});

	// 根据大类名称查询
	function doSearchComment() {
		$("#dg").datagrid("load", {
			"s_comment.content" : $("#s_name").val()
		});
	}

	// 删除商品大类
	function deleteComment(id) {
		$.messager.confirm("系统提示", "您确定要删除该记录么?", function(result) {
			if (result) {
				$.ajax({
					url : "comment_delete.action",
					type : "post",
					dataType : "json",
					data : {
						"commentManage.id" : id
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
						<td style="text-align: right; width: 80px;">留言内容:</td>
						<td style="width: 150px;"><input type="text" id="s_name"
							size="20" onkeydown="if(event.keyCode==13){doSearchComment()}" />
						</td>
						<td><a href="javascript:void(0)" onclick="doSearchComment();"
							style="margin-left: 10px;">查询</a> </td>
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