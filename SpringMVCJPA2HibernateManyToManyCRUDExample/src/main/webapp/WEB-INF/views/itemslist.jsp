<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Users List</title>
	<link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
	<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
	<link href=" <c:url value="/static/js/jquery-ui-1.12.1/jquery-ui.css" />" rel="stylesheet"/>  
	<link href=" <c:url value="/static/js/jquery-ui-1.12.1/jquery-ui.structure.css" />" rel="stylesheet"/>  
	<link href=" <c:url value="/static/js/jquery-ui-1.12.1/jquery-ui.theme.css" />" rel="stylesheet"/>  
	<script src="<c:url value="/static/js/jquery-3.2.0.js" />"></script>
	<script src="<c:url value="/static/js/jquery-ui-1.12.1/jquery-ui.js" />"></script>
</head>

<body>
	<div class="generic-cuntainer">

	<div class="col-md-777">
			<div class="">

				<div onChange="window.location.href=this.value">
					
						<a href = "<c:url value="?lang=en"/>">
							<img src=" <c:url value="/static/images/en.png" />" /> 
						</a>
					
						<a href="<c:url value="?lang=fr"/>">
							<img src=" <c:url value="/static/images/fr.png" />" />  
						</a>
				</div>
			</div>
	</div>
	</div>
	<div class="col-md-7777">
		<c:if test="${pageContext.request.userPrincipal.name != null}">
			<h2>${Welcome} ${pageContext.request.userPrincipal.name} | 
				<a href="<c:url value="/logout" />" > ${Logout}</a>
			</h2>  
		</c:if>	
	</div>
	<div class="generic-container">
		<div class="panel panel-default">
			  <!-- Default panel contents -->
		  	<div class="panel-heading"><span class="lead">${ListofItems} </span></div>
			<table class="table table-hover">
	    		<thead>
		      		<tr>
		      			<th>${id}</th>
				        <th>${nameItem}</th>
				        <th>${descriptionItem}</th>
				        <th>${priceItem}</th>
				        <th width="100"></th>
				        <th width="100"></th>
					</tr>
		    	</thead>
	    		<tbody>
				<c:forEach items="${items}" var="item">
					<tr>
						<td>${item.id}</td>
						<td>${item.name}</td>
						<td>${item.description}</td>
						<td>${item.price}</td>
						<td><a href="<c:url value='/edit-item-${item.id}' />" class="btn btn-success custom-width">${edit}</a></td>
						<td><a href="<c:url value='/delete-item-${item.id}' />" class="btn btn-danger custom-width">${delete}</a></td>
					</tr>
				</c:forEach>
	    		</tbody>
	    	</table>
		</div>
	 	<div class="well">
	 		<a href="<c:url value='/newitem' />">${AddNewItem}</a> </br>
	 	 	<a href="<c:url value="/interfaceAdmin" />">${Goto} ${operation}</a>
	 	</div>
   	</div>
   <div class="generic-container-left">
			<h2>	
				<a href="<c:url value='/list' />">Users</a>
			</h2>
			<h2>
				<a href="<c:url value='/listOrders' />" >Orders</a>
			</h2>
			<h2>
				<a href="<c:url value='/listitems' />">Items</a>
			</h2>
   </div>
</body>
</html>