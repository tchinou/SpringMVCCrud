<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Users List</title>
	<link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
	<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
	<link href=" <c:url value="/static/css/styles.css" />" rel="stylesheet" />
	
</head>

<body>
	<div class="generic-cuntainer">
	<div class="col-md-777">
		
	</div>
	<div class="select-language">
		<select id="lang_choice" name="lang_choice" onChange="window.location.href=this.value">
			<option value="">${pageContext.response.locale}</option>
			<option value="?lang=en" name="en" > English</option>
			<option value="?lang=fr" name="fr" > French</option>
			
		</select>
		<c:set var ="testValue" value="${pageContext.response.locale}" ></c:set>
			 <c:choose>
				 <c:when test="${testValue == 'en'}">
				 	<option value="?lang=en" name="en" class="uk">
						English	</option>
				 </c:when>
				 <c:otherwise>
					<option value="?lang=fr" name="fr" class="fr" >
						French
					</option>
				</c:otherwise>
			</c:choose>	
	</div>
	</div>
		<div class="col-md-7777">
		<c:if test="${pageContext.request.userPrincipal.name != null}">
			<h2>${Welcome} ${pageContext.request.userPrincipal.name} | 
				<a href="<c:url value="/logout" />" > ${Logout}</a>
			</h2>  
		</c:if>	
	</div>
	
 	 <div class="generic-container-left">
			<h4><a href="<c:url value="/listitemspanier" />" >Go Back to Shopping</a> </h4>
 	 </div>
<div>
	<div class="generic-container-listOrdered"  style="overflow-y: scroll; height : 90%; max-height:500px">
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