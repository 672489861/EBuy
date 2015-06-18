<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script> 
	$(function(){
		// 增加数量
		$(".add").on("click",function(){
			var number = $(this).prev(); //  数量对象
			number.val(parseInt(number.val())+1); // 这边必须parseInt 不然会字符串拼接
			var product_id = $(this).parent().find("input[id*=product_id]").val(); // 商品id
			$("#productItem_total_"+product_id).html($("#price_"+product_id).html()*number.val()); // 商品总金额
			refreshSession(product_id,number.val()); // 更新session中购物车的商品数量
			setTotal(); // 重新计算所有商品总金额
		});
		
		// 减少数量
		$(".min").on("click",function(){
			var number = $(this).next(); // 数量对象
			number.val(parseInt(number.val())-1);
			var product_id = $(this).parent().find("input[id*=product_id]").val(); // 商品id
			$("#productItem_total_"+product_id).html($("#price_"+product_id).html()*number.val()); // 商品总金额
			refreshSession(product_id,number.val()); // 更新session中购物车的商品数量
			setTotal(); // 重新计算所有商品总金额
		});
		
		// 用户手动修改数量
		$(".text_box").on("blur",function(){
			if(parseInt($(this).val())<0){
				$(this).val(1);
			}
			var product_id = $(this).parent().find("input[id*=product_id]").val(); // 商品id
			$("#productItem_total_"+product_id).html($("#price_"+product_id).html()*$(this).val()); // 商品总金额
			refreshSession(product_id,$(this).val()); // 更新session中购物车的商品数量
			setTotal(); // 重新计算所有商品总金额
		});
		
		// 刷新sesion中购物车的商品数量
		function refreshSession(productId,count){
			$.ajax({
				url:"shopping_updateShoppingCartItem.action",
				dataType:"json",
				type:"post",
				data:{
					productId:productId,
					count:count
				},
				success:function(result){
					if(result.success){
					}else{
						alert("刷新Session失败！");
					}
				}
			});
		}
		
		// 计算所有商品总金额
		function setTotal(){
			var total = 0;
			$(".productTr").each(function(){
				total += $(this).find('input[class=text_box]').val()*$(this).find('label[class*=price_]').html(); 
			});
			$("#product_total").html(total); 
		}
		
		setTotal(); 
	});
	
	// 删除商品
	function removeShopping(productId){
		if(confirm("确定要删除这个商品吗?")){
			$.ajax({
				url:"shopping_removeShoppingCartItem.action",
				dataType:"json",
				type:"post",
				data:{
					productId:productId
				},
				success:function(result){
					if(result.success){
						// 刷新页面
						window.location.reload();						
					}else{
						alert("删除商品失败！");
					}
				}
			});
		}
	}
</script> 
</head>
<body>
<div id="shopping">
		<form action="order_save.action" method="post">
			<table id="myTableProduct">
				<tr>
					<th>商品名称</th>
					<th>商品单价</th>
					<th>金额</th>
					<th>购买数量</th>
					<th>操作</th>
				</tr>
				<c:forEach items="${shoppingCart.shoppingCartItems}" var="shoppingCartItem" varStatus="status">
					<tr id="tr_${shoppingCartItem.product.id }" class="productTr">
						<td class="thumb">
							<img class="imgs" src="${shoppingCartItem.product.proPic}"/>
							<a href="${pageContext.request.contextPath}/product_showProduct.action?productId=${shoppingCartItem.product.id}">${shoppingCartItem.product.name}</a>
						</td>
						<td class="price" id="price_id_${shoppingCartItem.product.id}">
							<span><label class="price_" id="price_${shoppingCartItem.product.id}">${shoppingCartItem.product.price}</label></span>
						</td>
						<td class="price" id="price_id_${shoppingCartItem.product.id}">
							<span>￥<label id="productItem_total_${shoppingCartItem.product.id}">${shoppingCartItem.product.price*shoppingCartItem.count}</label></span>
						</td>
						<td class="number">
								<input type="hidden" id="product_id" value="${shoppingCartItem.product.id }"/>
								<input class="min"  type="button" value=" - "  /> 
								<input id="number_id_${shoppingCartItem.product.id}" class="text_box"  style="width: 30px;text-align: center"  type="text" value="${shoppingCartItem.count}" /> 
								<input class="add"  type="button" value=" + " /> 
						</td>
						<td class="delete">
							<a href="javascript:removeShopping(${shoppingCartItem.product.id});">删除</a>
						</td>
					</tr>
				</c:forEach>
			</table>

			<div class="button">
				<input type="submit" value="" />
			</div>
		</form>
</div>

<div class="shopping_list_end">

	<ul>
		<li class="shopping_list_end_2">￥<label id="product_total"></label>
		</li>
		<li class="shopping_list_end_3">商品金额总计：</li>
	</ul>
</div>

<div style="background-color: #cddbb8;margin-top: 10px;font-size: 20px;height: 40px;text-align: center">
	<div style="padding: 5px;">
		<b>个人信息</b>&nbsp;&nbsp;&nbsp;&nbsp;<b>收件人：</b>${currentUser.trueName }&nbsp;&nbsp;<b>收获地址：</b>${currentUser.address }&nbsp;&nbsp;<b>联系电话：</b>${currentUser.mobile }
	</div>
</div>
</body>
</html>