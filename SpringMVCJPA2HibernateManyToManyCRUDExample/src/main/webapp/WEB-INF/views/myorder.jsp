<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%-- <link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link> --%>
<%-- <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link> --%>
<title>Commande</title>
</head>
<body>
<u class="btn btn-primary">Commande</u>
<div class="generic-container">
	<div class="panel panel-default">
			  <!-- Default panel contents -->
		  	<div class="panel-heading"><span class="lead">${ListofItems} </span></div>
			<table class="table table-hover">
	    				<thead>
<%-- 		    				<c:forEach items="${orderheader}" var="item"> --%>
					        <p>Référence : ${idOrder}</p>
					        <p>Date : ${dateOrder}
	<%-- 				        	<jsp:useBean id="now" class="java.util.Date"/>     --%>
	<%-- 							<fmt:formatDate value="${now}" dateStyle="long"/> --%>
	<%-- 							<fmt:formatDate value="${now}" pattern="dd-MM-yyyy HH:mm:ss a z" /> --%>
					        </p>
					        <p>
	<%-- 				        	<c:set var="total" value="${0}"/> --%>
	<%-- 							<c:forEach var="item" items="${items}"> --%>
	<%-- 			   				<c:set var="total" value="${total + item.subtotal}" /> --%>
	<%-- 							</c:forEach> --%>
								 Prix total :  ${priceOrder}
					        </p>
					        <p>
						        <c:set var="quantityArticle" value="${0}"/>
								<c:forEach var="item" items="${items}">
				   				<c:set var="quantityArticle" value="${quantityArticle + item.quantity}" />
								</c:forEach>
					        	Nombre Items : ${quantityArticle}
					        </p>
					        <p width="100"></p>
					        <p width="100"></p>
<%-- 							</c:forEach> --%>
		    			</thead>
	    	</table>
</div>
	</div>
<div style="overflow-x:auto;">
		<form action="${pageContext.request.contextPath}/myDisplayOrder-${idOrder}"  method="GET">
		 	<button  class="btn btn-primary" type="submit"> Voir les items </button>
		</form> 
</div>
</body>
</html>