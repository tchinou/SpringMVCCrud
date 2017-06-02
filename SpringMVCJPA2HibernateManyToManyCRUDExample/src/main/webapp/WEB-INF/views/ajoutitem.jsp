<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Item Registration Form</title>
	<link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
	<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>

<body>

 	<div class="generic-container">
	<div class="well lead">${ItemRegistrationForm}</div>
 	<form:form method="POST" modelAttribute="item" class="form-horizontal">
		<form:input type="hidden" path="id" id="id"/>
			<div class="row">
			<div class="form-group col-md-12">
				<label class="col-md-3 control-lable" for="name">${nameItem}</label>
				<div class="col-md-7">
					<form:input type="name" path="name" id="name" class="form-control input-sm" />
					<div class="has-error">
						<form:errors path="name" class="help-inline"/>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="form-group col-md-12">
				<label class="col-md-3 control-lable" for="description">${descriptionItem}</label>
				<div class="col-md-7">
					<form:input type="text" path="description" id="description" class="form-control input-sm"/>
					<div class="has-error">
						<form:errors path="description" class="help-inline"/>
					</div>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="form-group col-md-12">
				<label class="col-md-3 control-lable" for="price">${priceItem}</label>
				<div class="col-md-7">
					<form:input type="text" path="price" id="price" class="form-control input-sm" />
					<div class="has-error">
						<form:errors path="price" class="help-inline"/>
					</div>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="form-actions floatRight">
				<c:choose>
					<c:when test="${itemEdit}">
						<input type="submit" value="${Update}" class="btn btn-primary btn-sm"/> ${choix} <a href="<c:url value='/listitems' />">${cancel}</a>
					</c:when>
					<c:otherwise>
						<input type="submit" value="${Register}" class="btn btn-primary btn-sm"/> ${choix}  <a href="<c:url value='/listitems' />">${cancel}</a>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</form:form>
	</div>
</body>
</html>