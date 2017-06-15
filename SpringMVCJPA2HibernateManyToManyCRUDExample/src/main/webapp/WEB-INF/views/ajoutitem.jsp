<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Item Registration Form</title>
	<link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
	<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
	<script src="<c:url value="https://cdn.jsdelivr.net/jquery/1.12.4/jquery.min.js" />"></script> 
	<script src="<c:url value="https://cdn.jsdelivr.net/jquery.validation/1.15.1/jquery.validate.min.js" />"></script> 
	<script>
	$(function() {
		  // Initialize form validation on the registration form.
		  // It has the name attribute "registration"
		  $("form[name='registration']").validate({
		    // Specify validation rules
		    rules: {
		      // The key name on the left side is the name attribute
		      // of an input field. Validation rules are defined
		      // on the right side
		      name : "required",
		      price: "required",
		      description: "required",
		     
		    // Specify validation error messages
		    messages: {
			  name : "Please enter Name Item",	    
		      price: "Please enter Price Item",
		      description: "Please enter description Item"
		    },
		    // Make sure the form is submitted to the destination defined
		    // in the "action" attribute of the form when valid
		    submitHandler: function(form) {
		      form.submit();
		    }
		  }
		  });
		  $("#myform").on('click', function () {
		        $("#reg_form").validate().resetForm();	//clear out the validation errors
		        var form = $("#reg_form");
		        form[0].reset(); //clear out the form data
		    });
	   	    
		});
	</script>
</head>

<body>

 	<div class="generic-container">
	<div class="well lead">${ItemRegistrationForm}</div>
 	<form:form method="POST" modelAttribute="item" class="form-horizontal" id="reg_form" name="registration">
		<form:input type="hidden" path="id" />
			<div class="row">
			<div class="form-group col-md-12">
				<label class="col-md-3 control-lable" for="name">${nameItem}</label>
				<div class="col-md-7">
					<form:input type="name" path="name" id="name" class="form-control input-sm" name="name"/>
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
					<form:input type="text" path="description" id="description" class="form-control input-sm" name="description"/>
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
					<form:input type="text" path="price" id="price" class="form-control input-sm" name="price"/>
					<div class="has-error">
						<form:errors path="price" class="help-inline"/>
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