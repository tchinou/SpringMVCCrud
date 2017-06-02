<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html">
	<title>Users List</title>
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
	<form action="${pageContext.request.contextPath}/myCart"  method="GET">
		 	<button  class="btn btn-primary" type="submit">My Cart</button>
	</form> 
	<div class="generic-container">
		<div class="panel panel-default">
			  <!-- Default panel contents -->
		  	<div class="panel-heading"><span class="lead">${ListofItems} </span></div>
			<table class="table table-hover">
	    		<thead>
		      		<tr>
				        <th>Identifiant</th>
				        <th>${nameItem}</th>
				        <th>${descriptionItem}</th>
				        <th>
							 <a href="<c:url value='/sort-item?sortfield=${price}'/>"> ${priceItem}</a>
<%-- 				        	<a href="<c:url value='/sort-item-${priceItem}'/>"> ${priceItem}</a> --%>
<!-- 				        	<span id="identifierDescendant">▼</span> -->
<!-- 				        	<span id="identifierAscendant">▲</span> -->
				        </th>
				        <th>Quantité</th>
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
<%-- 						<td><a href="<c:url value='' />" class="btn btn-success custom-width">+</a></td> --%>
<!-- 						<td><input  type="number" name="myInput"></td> -->
							<td> 
		        			 
		                    	<form action="${pageContext.request.contextPath}/add?id=${item.id}" class="add-form" method="POST">
			 						<input type="number" name="quantity" min="1"  value=1>
		                    
			 						<button  class="btn btn-primary" type="submit">+</button>
		 							 <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		           
		 						</form> 
		 					</td>
					</tr>
				</c:forEach>
	    		</tbody>
	    	</table>
		</div>
<!-- 	 	<div class="well"> -->
<%-- 	 		<a href="<c:url value='/newitem' />">${AddNewItem}</a> --%>
<!-- 	 	</div> -->
   	</div>
</body>
</html>