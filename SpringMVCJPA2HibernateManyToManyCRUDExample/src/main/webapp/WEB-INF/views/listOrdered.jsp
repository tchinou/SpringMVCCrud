<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Users List</title>
	<link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
	<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>

<body>
<div>
	<div class="generic-container"  style="overflow: scroll; height : 90%; max-height:500px">
		<div class="panel panel-default">
			  <!-- Default panel contents -->
		  	<div class="panel-heading"><span class="lead">${ordersList} </span></div>
			<table class="table table-hover">
	    		<thead>
		      		<tr>
		      			<th>${idOrder}</th>
				        <th>${dateOrder}</th>
				        <th>${PriceOrder}</th>
				        <th>${NameUser}</th>
				        <th width="100"></th>
				        <th width="100"></th>
					</tr>
		    	</thead>
	    		<tbody>
				<c:forEach items="${Orders}" var="order">
					<tr>
						<td>
						<a href="<c:url value='/display-order-${order.id}'/>"> ${order.id}</a>
						</td>
						<td>${order.date}</td>
						<td>${order.price}</td>
						<td>${order.user.login}</td>
<%-- 						<td><a href="<c:url value='/edit-order-${order.id}' />" class="btn btn-success custom-width">${edit}</a></td> --%>
<%-- 						<td><a href="<c:url value='/delete-order-${order.id}' />" class="btn btn-danger custom-width">${delete}</a></td> --%>
					</tr>
				</c:forEach>
	    		</tbody>
	    	</table>
		</div>
	 	<div class="well">
<%-- 	 		<a href="<c:url value='/newitem' />">${AddNewItem}</a> </br> --%>
	 	 	<a href="<c:url value="/interfaceAdmin" />">${Goto} ${operation}</a>
	 	</div>
   	</div>
</div>
</body>
</html>