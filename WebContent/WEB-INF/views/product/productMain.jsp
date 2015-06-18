<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>易买网</title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
</head>
<body>
<div id="header" class="wrap">
	<%@ include file="../../../common/top.jsp"%> 
</div>

<div id="position" class="wrap">
		${navCode}
</div>
	
<div id="main" class="wrap">
	<div class="lefter">
		<%@ include file="../../../common/left.jsp"%> 
	</div>
	<jsp:include page="${mainPage}"/>
<div class="clear"></div>
</div>
<div id="footer">
	<%@ include file="../../../common/footer.jsp"%> 
</div>
</body>
</html>