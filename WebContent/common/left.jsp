<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="box">
	<h2>商品分类</h2>
	<dl>
		<c:forEach var="bigType" items="${bigTypeList}">
			<dt>${bigType.name}</dt>
			<c:forEach var="smallType" items="${bigType.smallTypes}">
				<dd><a href="${pageContext.request.contextPath}/product.action?s_product.smallType.id=${smallType.id}">${smallType.name}</a></dd>
			</c:forEach>
		</c:forEach>
	</dl>
</div>

<div class="spacer"></div>
<div class="last-view">
	<h2>最近浏览</h2>
	<dl class="clearfix">
		<c:forEach var="currentPro" items="${currentBrowse}">
			<dt>
				<img src="${pageContext.request.contextPath}/${currentPro.proPic}" class="imgs" style="height:50px;width:50px;">
			</dt>
			<dd>
				<a href="${pageContext.request.contextPath}/product_showProduct.action?productId=${currentPro.id}">${currentPro.name}</a>
			</dd>
		</c:forEach>
	</dl>
</div>
</body>
</html>