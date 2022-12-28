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

<c:url var="action" value="/product/add"></c:url>
	<form:form method="post" action="${action}" modelAttribute="product">
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
		<input type="submit" value="Add"/>
	</form:form>

</body>
</html>