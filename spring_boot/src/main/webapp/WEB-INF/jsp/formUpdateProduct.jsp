<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<c:url var="action" value="/product/update"></c:url>
<form:form action="${action }" method="post" modelAttribute="product">
	<h3>Product Id</h3>
	<input type="text" name="productId"
		value="${productId }"/>
	<h3>Code</h3>
	<input type="text" name="code"/>
	<br/>
	<h3>Name</h3>
	<input type="text" name="name"/>
	<br/>
	<h3>Type</h3>
	<input type="text" name="type"/>
	<br/>
	<h3>Price</h3>
	<input type="number" name="price"/>
	<br/>
	<br/>
	<input type="submit" value="Update"/>	
</form:form>

</body>
</html>