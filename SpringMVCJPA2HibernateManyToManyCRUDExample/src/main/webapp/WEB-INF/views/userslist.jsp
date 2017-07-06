<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Users List</title>
	<link href=" <c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
	<link href=" <c:url value='/static/css/app.css' />" rel="stylesheet"></link>
	<link href=" <c:url value="/static/css/styles.css" />" rel="stylesheet" />
	<link href=" <c:url value="/static/js/jquery-ui-1.12.1/jquery-ui.css" />" rel="stylesheet"/>  
	<link href=" <c:url value="/static/js/jquery-ui-1.12.1/jquery-ui.structure.css" />" rel="stylesheet"/>  
	<link href=" <c:url value="/static/js/jquery-ui-1.12.1/jquery-ui.theme.css" />" rel="stylesheet"/>  
	<script src="<c:url value="/static/js/jquery-3.2.0.js" />"></script>
	<script src="<c:url value="/static/js/jquery-ui-1.12.1/jquery-ui.js" />"></script>
</head>

<body>
	<div class="generic-cuntainer">
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
		<div class="div-container">

		</div>
		<div class="col-md-7777">
		<c:if test="${pageContext.request.userPrincipal.name != null}">
			<h2>${Welcome} ${pageContext.request.userPrincipal.name} | 
				<a href="<c:url value="/logout" />" > ${Logout}</a>
			</h2>  
		</c:if>	
		</div>
		 <div class="generic-container-left">
			<c:import url="/static/html/menu.html" />
	   	 </div>
	   	  	<div class="generic-container">
				<div class="panel panel-default">
					  <!-- Default panel contents -->
					  <div class="well">
				 		<a href="<c:url value='/newuser' />">${AddNewUser}</a></br>
		    		  </div>
				  	<div class="panel-heading"><span class="lead">${ListofUsers} </span></div>
					<table class="table table-hover">
			    		<thead>
				      		<tr>
				      			<th>${Id}</th>
						        <th>${FirstName}</th>
						        <th>${LastName}</th>
						        <th>${Email}</th>
						        <th>${Login}</th>
						        <th width="100"></th>
						        <th width="100"></th>
							</tr>
				    	</thead>
			    		<tbody>
						<c:forEach items="${users}" var="user">
							<tr>
								<td>
									<a href="<c:url value='/edit-user-${user.id}' />">${user.id}</a>
								</td>
								<td>${user.firstName}</td>
								<td>${user.lastName}</td>
								<td>${user.email}</td>
								<td>${user.login}</td>
								<td><a href="<c:url value='/edit-user-${user.id}' />" class="btn btn-success custom-width">${edit}</a></td>
								<td><a href="<c:url value='/delete-user-${user.id}' />" class="btn btn-danger custom-width">${delete}</a></td>
							</tr>
						</c:forEach>
			    		</tbody>
			    	</table>
				</div>
	   	</div>
	</div>

</body>
</html>