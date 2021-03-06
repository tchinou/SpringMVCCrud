<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html">
	<title>Users List</title>
	<link href=" <c:url value="/static/css/styles.css" />" rel="stylesheet" />
	<link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
	<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
	<link href=" <c:url value="/static/js/jquery-ui-1.12.1/jquery-ui.css" />" rel="stylesheet"/>  
	<link href=" <c:url value="/static/js/jquery-ui-1.12.1/jquery-ui.structure.css" />" rel="stylesheet"/>  
	<link href=" <c:url value="/static/js/jquery-ui-1.12.1/jquery-ui.theme.css" />" rel="stylesheet"/>  
	<script src="<c:url value="/static/js/jquery-3.2.0.js" />"></script>
	<script src="<c:url value="/static/js/jquery-ui-1.12.1/jquery-ui.js" />"></script>
<%-- 	<script src="<c:url value="/static/js/functions.js" />"></script> --%>
<%-- 	<script src="<c:url value="/static/js/datatable.js" />"></script> --%>
	
</head>

<body>
	<div class="generic-cuntainer-userPage">
		 <form action="${pageContext.request.contextPath}/myCart-${pageContext.request.userPrincipal.name}"  method="GET">
					 
			<button  class="btn btn-primary" type="submit"> ${MyCart}: ${quan} ${articles} | ${priceCart} ${logoEuro} </button>
		 </form> 

		<jsp:include page="/WEB-INF/views/languageLocale.jsp"/>

		<div class="col-md-7777">
			<c:if test="${pageContext.request.userPrincipal.name != null}">
				<h2>${Welcome}
					${pageContext.request.userPrincipal.name} | <a
						href="<c:url value="/logout" />"> ${Logout}</a>
				</h2>
			</c:if>
		</div>

	 <div class="generic-container-left-userPage">
		<c:import url="/static/html/menuUser.html" />
 	 </div>
		<div class="generic-container">
			<c:if test="${pageContext.request.userPrincipal.name != null}">
				<h1>${Welcome} ${pageContext.request.userPrincipal.name} | 
					
				</h1>  
			</c:if>
<!-- 				<div class="panel panel-default"> -->
				
<!-- 				</div> -->
				
		</div>
   	</div>
</body>
</html>