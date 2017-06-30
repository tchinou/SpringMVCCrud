<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>User Registration Form</title>
	<link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
	<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
	<link href=" <c:url value="/static/css/styles.css" />" rel="stylesheet" />
	<script src="<c:url value="https://cdn.jsdelivr.net/jquery/1.12.4/jquery.min.js" />"></script> 
	<script src="<c:url value="https://cdn.jsdelivr.net/jquery.validation/1.15.1/jquery.validate.min.js" />"></script> 
<%-- 	<script src="<c:url value="/static/js/firstScript.js" />"></script> --%>
<%-- 	<script src="<c:url value="//code.jquery.com/jquery-1.9.1.js"/>"></script> --%>
<%--  	<script src="<c:url value="//ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.min.js"/>"></script> --%>
	<script>
	// Wait for the DOM to be ready
	$(function() {
	  // Initialize form validation on the registration form.
	  // It has the name attribute "registration"
	  $("form[name='registration']").validate({
	    // Specify validation rules
	    rules: {
	      // The key name on the left side is the name attribute
	      // of an input field. Validation rules are defined
	      // on the right side
	      login : "required",
	      firstName: "required",
	      lastName: "required",
	      email: {
	        required: true,
	        // Specify that email should be validated
	        // by the built-in "email" rule
	        email: true
	      },
	      password: {
	        required: true,
	        minlength: 5
	      }
	    },
	    // Specify validation error messages
	    messages: {
		  login : "Please enter your login",	    
	      firstName: "Please enter your firstname",
	      lastName: "Please enter your lastname",
	      password: {
	        required: "Please provide a password",
	        minlength: "Your password must be at least 5 characters long"
	      },
	      email: "Please enter a valid email address"
	    },
	    // Make sure the form is submitted to the destination defined
	    // in the "action" attribute of the form when valid
	    submitHandler: function(form) {
	      form.submit();
	    }
	  });
	  $('#myform').on('click', function () {
	        $("#reg_form").validate().resetForm();	//clear out the validation errors
	        var form = $("#reg_form");
	        form[0].reset(); //clear out the form data
	    });
   	    
	});

	</script>
</head>

<body>
	<div class="generic-cuntainer">

	<div class="col-md-777">
				<div onChange="window.location.href=this.value">
					
						<a href = "<c:url value="?lang=en"/>">
							<img src=" <c:url value="/static/images/en.png" />" /> 
						</a>
					
						<a href="<c:url value="?lang=fr"/>">
							<img src=" <c:url value="/static/images/fr.png" />" />  
						</a>
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
	<div class="generic-container-left">
		<c:import url="/static/html/menu.html" />
 	</div>
 	<div class="generic-container">
 	<div class="panel panel-default">
 	
	<div class="well lead">${UserRegistrationForm}</div>
 	<form:form method="POST" modelAttribute="user" class="form-horizontal" id="reg_form" name="registration">
		<form:input type="hidden" path="id" id="id"/>
		
		<div class="row">
			<div class="form-group col-md-12">
				<label class="col-md-3 control-lable" for="firstName">${FirstName}</label>
				<div class="col-md-7">
					<form:input type="text" path="firstName" id="firstName" 
					class="form-control input-sm" name="firstName" placeholder="firstName"/>
					
					<div class="has-error">
						<form:errors path="firstName" class="help-inline"/>
					</div>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="form-group col-md-12">
				<label class="col-md-3 control-lable" for="lastName">${LastName}</label>
				<div class="col-md-7">
					<form:input  type="text" path="lastName" id="lastName" 
					class="form-control input-sm" name="lastName"  placeholder="lastName"/>
					<div class="has-error">
						<form:errors path="lastName" class="help-inline"/>
					</div>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="form-group col-md-12">
				<label class="col-md-3 control-lable" for="ssoId">SSO ID</label>
				<div class="col-md-7">
					<c:choose>
						<c:when test="${edit}">
							<form:input type="text" path="ssoId" id="ssoId" class="form-control input-sm" disabled="true"/>
						</c:when>
						<c:otherwise>
							<form:input type="text" path="ssoId" id="ssoId" class="form-control input-sm" />
							<div class="has-error">
								<form:errors path="ssoId" class="help-inline"/>
							</div>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="form-group col-md-12">
				<label class="col-md-3 control-lable" for="login">${Login}</label>
				<div class="col-md-7">
					<form:input  type="login" path="login" id="login" name ="login" 
					class="form-control input-sm"  placeholder="Login"/>
					<div class="has-error">
						<form:errors path="login" class="help-inline"/>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="form-group col-md-12">
				<label class="col-md-3 control-lable" for="password">${Password}</label>
				<div class="col-md-7">
					<form:input type="password" path="password" id="password" 
					class="form-control input-sm" name="password" placeholder="&#9679;&#9679;&#9679;&#9679;&#9679;"/>
					<div class="has-error">
						<form:errors path="password" class="help-inline"/>
					</div>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="form-group col-md-12">
				<label class="col-md-3 control-lable" for="email">${Email}</label>
				<div class="col-md-7">
					<form:input type="text" path="email" id="email" class="form-control input-sm" name="monemail"/>
					<div class="has-error">
						<form:errors path="email" class="help-inline"/>
					</div>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="form-group col-md-12">
				<label class="col-md-3 control-lable" for="userProfiles">Roles</label>
				<div class="col-md-7">
					<form:select path="userProfiles" items="${roles}" multiple="true" itemValue="id" itemLabel="type" class="form-control input-sm" />
					<div class="has-error">
						<form:errors path="userProfiles" class="help-inline"/>
					</div>
				</div>
			</div>
		</div>
		<div>
			<input type="button" value="reinitialiser" id="myform"/> 
		</div>
		
		<div class="row">
			<div class="form-actions floatRight">
				<c:choose>
					<c:when test="${edit}">
						<input type="submit" value="${Update}" class="btn btn-primary btn-sm"/> ${choix} <a href="<c:url value='/list' />">${cancel}</a>
					</c:when>
					<c:otherwise>
						<input type="submit" value="${Register}" class="btn btn-primary btn-sm"/> ${choix}  <a href="<c:url value='/list' />">${cancel}</a>
					</c:otherwise>
				</c:choose>
				
			</div>
		</div>
	</form:form>
	</div>
	</div>
</body>
</html>