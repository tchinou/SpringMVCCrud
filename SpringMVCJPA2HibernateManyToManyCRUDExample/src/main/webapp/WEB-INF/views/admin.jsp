<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>
		<a href="<c:url value='/list' />">${mUser}</a>
	</h2>
	<h2>
		<a href="<c:url value='/listOrders' />" >${mOrder}</a>
	</h2>
	<h2>
	<a href="<c:url value='/listitems' />">${mItem}</a>
	</h2>
    Current Locale : ${pageContext.response.locale} / ${locale} : ${startMeeting}
    	<div class="row">
			<div class="form-group col-md-12">
				<label class="col-md-3 control-lable" for="language"></label>
				<div class="col-md-7">
					<select onChange="window.location.href=this.value">
				        <option value="">Select language</option>
							 <option value="?lang=en">English</option>
							 <option value="?lang=fr">Français</option>
					
				    </select>
				</div>
			</div>
	</div>
</body>
</html>