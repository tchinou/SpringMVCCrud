<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%-- <%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %> --%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Shopping</title>

	<spring:url value="/static/css/style.css" var="stylecss"/>  
	<spring:url value="/static/css/bootstrap.min.css" var="style2css"/>  
	
	<link href="${stylecss}" rel="stylesheet"/>  
	<link href="${style2css}" rel="stylesheet"/>  
	
</head>
<body>
	<h2>${Welcome} ${Login} | 
	</h2>  
<div style="overflow-x:auto;">
 	<table class="table table-striped">
        
        <thead>
            <tr>
                <th>${nameItem}</th>
                <th>${quantity}</th>
                <th>${priceItem}</th>
                <th>${totalPrice}</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${items}" var="item">
                <tr>
                     <td>${item.name}</td>
                     <td>${item.quantity}</td>
                     <td class="price">${item.price}</td>
                  	 <td class="price">${item.subtotal}</td>
                  	 <td>
                  	 	<a class="btn btn-warning" type="button" href="<c:url value='/delete-itemCart-${item.id}'/>">Delete</a>
					 </td>
				
               </tr>
            </c:forEach>
        </tbody>
        
    </table>
</div>
<div>   <p>
					 <c:set var="quantityArticle" value="${0}"/>
					 <c:forEach var="item" items="${items}">
			   		 <c:set var="quantityArticle" value="${quantityArticle + item.quantity}" />
					 </c:forEach>
				     ${itemNumber} : ${quantityArticle}
		  </p>
		  <p>NOMBRE ITEMS : ${Lyes}</p>
   		  <p>
				     <c:set var="total" value="${0}"/>
			    	 <c:forEach var="item" items="${items}">
			   		 <c:set var="total" value="${total + item.subtotal}" />
					 </c:forEach>
					 ${totalPrice} :  ${total}
		   </p>
		
</div>
<div style="overflow-x:auto;">
		<form action="${pageContext.request.contextPath}/myOrder-${login}"  method="GET">
		 	<button  class="btn btn-primary" type="submit"> ${OrderMyCart}</button>
		</form> 
</div>
    
<%--  <jsp:include page="_footer.jsp" /> --%>
</body>
</html>
