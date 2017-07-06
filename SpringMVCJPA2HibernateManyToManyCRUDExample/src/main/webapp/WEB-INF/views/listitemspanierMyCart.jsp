<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html">
	<title>Users List</title>
	<link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
	<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
	<link href=" <c:url value="/static/css/styles.css" />" rel="stylesheet" />
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
		 
		 	<button  class="btn btn-primary" type="submit">${MyCart}: ${quan} ${articles} | ${priceCart} ${logoEuro}</button>
	</form> 
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
	
	<div class="col-md-7777">
		<c:if test="${pageContext.request.userPrincipal.name != null}">
			<h2>${Welcome} ${pageContext.request.userPrincipal.name} | 
				<a href="<c:url value="/logout" />" > ${Logout}</a>
			</h2>  
		</c:if>	
	</div>
	<div class="generic-container-left-userPage">
		<form action="${pageContext.request.contextPath}/myHistoryOrder-${pageContext.request.userPrincipal.name}"  method="GET">
			<button  class="btn btn-primary" type="submit">Mes Commandes</button>
		</form> 
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
				        <th>
							<a href="<c:url value='/sort-item?sortfield=${price}'/>"> ${priceItem}</a>
				        </th>
				        <th>${quantity}</th>
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
   	</div>
   </div>
</body>
</html>