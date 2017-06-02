<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="generic-container">
	<div class="panel panel-default">
			  <!-- Default panel contents -->
		  	<div class="panel-heading"><span class="lead">${ListofItems} </span></div>
			<table class="table table-hover">
	    		<thead>
				        <p>Commande :</p>
				        <p>Référence :</p>
				        <p>Date :</p>
				        <p>
							<a href="<c:url value='/sort-item'/>"> Prix total : </a>
				        ${price}
				        </p>
				        <p>Nombre Items : </p>
				        <p width="100"></p>
				        <p width="100"></p>
					
		    	</thead>
	    	</table>
</div>
	</div>
<div style="overflow-x:auto;">
		<form action="${pageContext.request.contextPath}/myOrder"  method="GET">
		 	<button  class="btn btn-primary" type="submit"> Display Items Ordered</button>
		</form> 
</div>
</body>
</html>