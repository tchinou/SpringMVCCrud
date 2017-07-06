<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html >

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Users List</title>
		<link href=" <c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
	<link href=" <c:url value='/static/css/app.css' />" rel="stylesheet"></link>
	<link href=" <c:url value="/static/css/styles.css" />" rel="stylesheet" />
	<link href=" <c:url value="/static/js/jquery-ui-1.12.1/jquery-ui.css" />" rel="stylesheet"/>  
	<link href=" <c:url value="/static/js/jquery-ui-1.12.1/jquery-ui.structure.css" />" rel="stylesheet"/>  
	<link href=" <c:url value="/static/js/jquery-ui-1.12.1/jquery-ui.theme.css" />" rel="stylesheet"/>  
	<script src="<c:url value="/static/js/jquery-3.2.0.js" />"></script>
	<script src="<c:url value="/static/js/jquery-3.2.1.min.js" />"></script>
	<script src="<c:url value="/static/js/jquery-ui-1.12.1/jquery-ui.js" />"></script>
	<script src="<c:url value="/static/js/jquery-latest.min.js" />"></script>
	
</head>

<body>
  <div id="generic-cuntainer">
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
 	 
		<div class="panel panel-default"  id="generic-container-listOrdered">
			  <!-- Default panel contents -->
				<div class="panel-heading"><span class="lead">${ordersList} </span></div>
			<table class="table table-hover">
	    		<thead>
		      		<tr>
		      			<th>${idOrder}</th>
				        <th>${dateOrder}</th>
				        <th>${PriceOrder}</th>
				        <th>${NameUser}</th>
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

								<fmt:setLocale value="${pageContext.response.locale}"/>
								<fmt:formatDate value="${order.date}" dateStyle="SHORT" var ="fromDateOrder"/>${fromDateOrder}
							
						</td>
						<td>${order.price}</td>
						<td>${order.user.login}</td>
					</tr>
				</c:forEach>
	    		</tbody>
	    	</table>
		</div>
   </div>
 
</body>
</html>