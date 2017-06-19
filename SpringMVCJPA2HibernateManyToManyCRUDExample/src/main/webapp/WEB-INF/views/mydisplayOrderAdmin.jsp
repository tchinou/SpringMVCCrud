<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<spring:url value="/static/css/style.css" var="stylecss"/>  
	<spring:url value="/static/css/bootstrap.min.css" var="style2css"/>  
	<link href="${stylecss}" rel="stylesheet"/>  
	<link href="${style2css}" rel="stylesheet"/>  

<title>${orderDesc}</title>
</head>
<body>
<u class="btn btn-primary">${orderDesc}</u>
<div class="generic-container">
	<div class="panel panel-default">
			  <!-- Default panel contents -->
		  	<div class="panel-heading"><span class="lead">${ListofItems} </span></div>
		  	<h2>${Login}</h2>
			<table class="table table-hover">
	    				<thead>
<%-- 		    				<c:forEach items="${orderheader}" var="item"> --%>
					        <p>${reference} : ${idOrder}</p>
					        <p>${dateOrder} : ${dateOrderFromOrder}</p>
							<p>${totalPrice} :  ${priceOrder}</p>
					        <p>
					        	${itemNumber} : ${quantityArticle}
					        </p>
					        <p width="100"></p>
					        <p width="100"></p>
<%-- 							</c:forEach> --%>
		    			</thead>
	    	</table>
</div>
	</div>
<div style="overflow-x:auto;">
 	<table class="table table-striped">
        
        <thead>
            <tr>
                <th>Item Name</th>
                <th>Quantity</th>
                <th>Price</th>
                <th>Total Price</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${items}" var="item">
                <tr>
                     <td>${item.name}</td>
                     <td>${item.quantity}</td>
                     <td class="price">${item.price}</td>
                  	 <td class="price">${item.subtotal}</td>
               </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

<div style="overflow-x:auto;">
		<form action="${pageContext.request.contextPath}"  method="GET">
		 	<button  class="btn btn-primary" type="submit"> Go back to menu</button>
		</form> 
</div>
</body>
</html>