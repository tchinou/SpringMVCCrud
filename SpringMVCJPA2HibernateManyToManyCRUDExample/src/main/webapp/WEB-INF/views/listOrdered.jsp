<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html  >

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Users List</title>
	<link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
	<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
	<link href="<c:url value="/static/css/styles.css" />" rel="stylesheet" />
	
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
	<div class="select-language">
		<c:import url="/static/html/language.html" />
		${pageContext.response.locale}
	</div>
	<div class="col-md-7777">
		<c:if test="${pageContext.request.userPrincipal.name != null}">
			<h2>${Welcome} ${pageContext.request.userPrincipal.name} | 
				<a href="<c:url value="/logout" />" > ${Logout}</a>
			</h2>  
		</c:if>	
	</div>
		
	 <div class="generic-container-left-listOrdered">
		<c:import url="/static/html/menu.html" />
 	 </div>
	
	<div class="generic-container-listOrdered" >
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
						<td>
							<c:set var ="locale" value="${pageContext.response.locale}"></c:set>
							<c:set var = "now" value = "${order.date}" ></c:set>
							<c:if test ="${locale == 'en'}"> ${order.date}</c:if>
							<c:if test ="${locale == 'fr'}"> 
								<fmt:formatDate  pattern = "dd-MM-yyyy" type ="date" value = "${order.date}" var="parseDate"/>
								${parseDate}
							
							</c:if>
							

						</td>
						<td>${order.price}</td>
						<td>${order.user.login}</td>
					</tr>
				</c:forEach>
	    		</tbody>
	    	</table>
		</div>
   	</div>
   </div>
</body>
</html>