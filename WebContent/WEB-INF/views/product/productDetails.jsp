<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	// 添加到购物车
	function addShoppingCart(productId){
		// 判断用户是否登录 
		if("${currentUser.userName}"==''){
			alert("请先登录,然后进行购物!");
			window.location.href="${pageContext.request.contextPath}/user_toLogin.action";
		}else{
			$.ajax({
				url:"shopping_addShoppingCartItem.action",
				data:{
					productId:productId
				},
				type:"post",
				dataType:"json",
				cache:false,
				success:function(result){
					if(result.success){
						alert("已成功加入购物车");
						window.location.reload(); // 刷新本页
					}else{
						alert(result.error);
					}
				}
			});
		}
	}
	
	// 购买商品
	function goBuy(productId){
		if("${currentUser.userName}"==''){
			alert("请先登录,然后进行购物!");
			window.location.href="${pageContext.request.contextPath}/user_toLogin.action";
		}else{
			window.location.href="${pageContext.request.contextPath}/shopping_buy.action?productId="+productId;
		}
	}
</script>
</head>
<body>
<div id="product"  class="main">
	<h1>${product.name}</h1>
	<div class="infos">
		<div class="thumb">
			<img class="img" src="${pageContext.request.contextPath}/${product.proPic}" />
		</div>
		<div class="buy">
			<br/>
			<p>
				商城价：<span class="price">￥${product.price}</span>
			</p>
			<p>库 存：${product.stock}</p>
			<br/>
			<div class="button">
				<input type="button" name="button" onclick="goBuy(${product.id})" /><br/>
				<a href="javascript:addShoppingCart(${product.id })">放入购物车</a>
			</div>
		</div>
		<div class="clear"></div>
	</div>
	<div class="introduce">
		<h2>
			<strong>商品详情</strong>
		</h2>
		<div class="text">
			${product.description}
		</div>
	</div>
</div>
</body>
</html>