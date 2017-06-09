<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%-- <%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %> --%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Commande</title>

	<spring:url value="/static/css/style.css" var="stylecss"/>  
	<spring:url value="/static/css/bootstrap.min.css" var="style2css"/>  
	
	<link href="${stylecss}" rel="stylesheet"/>  
	<link href="${style2css}" rel="stylesheet"/>  
	
</head>
<body>
		 <u>Commande </u>
         <p>Référence : ${idOrder}</p>
         <p>
         Date : ${dateOrder}
		 </p>
		  <p>
			Prix total :  ${priceOrder}
		 </p>
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
    
<%--  <jsp:include page="_footer.jsp" /> --%>
</body>
</html>
