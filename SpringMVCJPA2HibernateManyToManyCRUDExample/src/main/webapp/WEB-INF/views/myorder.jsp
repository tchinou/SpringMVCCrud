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
<spring:url value="/static/css/style.css" var="stylecss"/>  
	<spring:url value="/static/css/bootstrap.min.css" var="style2css"/>  
	<link href="${stylecss}" rel="stylesheet"/>  
	<link href="${style2css}" rel="stylesheet"/>  
<title>${orderDesc}</title>
</head>
<body>
<u class="btn btn-primary">${orderDesc}</u>
<div class="generic-container">
	<div class="panel panel-default">
			  <!-- Default panel contents -->
		  	<div class="panel-heading"><span class="lead">${ListofItems} </span></div>
		  	<h2>${Login}</h2>
			<table class="table table-hover">
	    				<thead>
					        <p>${reference} : ${idOrder}</p>
					        <p>${dateOrder} : ${dateOrder}</p>
					        <p>
								  ${totalPrice} :  ${priceOrder}
					        </p>
					        <p>
						        <c:set var="quantityArticle" value="${0}"/>
								<c:forEach var="item" items="${items}">
				   				<c:set var="quantityArticle" value="${quantityArticle + item.quantity}" />
								</c:forEach>
					        	${itemNumber} : ${quantityArticle}
					        </p>
					        <p width="100"></p>
					        <p width="100"></p>
		    			</thead>
	    	</table>
</div>
	</div>
<div style="overflow-x:auto;">
		<form action="${pageContext.request.contextPath}/myDisplayOrder-${idOrder}"  method="GET">
		 	<button  class="btn btn-primary" type="submit"> ${displayItems} </button>
		</form> 
</div>
</body>
</html>