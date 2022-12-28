<%@page import="java.util.List"%>
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
<title>List Product</title>
</head>
<body>
<h1>List Product</h1>
<br/>
<h3><a href="http://localhost:8080/consume_api01/addProduct">Add Product</a>	|	<a href="http://localhost:8080/consume_api01/">Home</a></h3>
<h3></h3>
<h3></h3>
<br/>
<table border=1 class="styled-table">
<tr>
	<th>Code</th>
	<th>Name</th>
	<th>Type</th>
	<th>Price</th>
	<th>Edit</th>
</tr>
<c:forEach items="${productList}" var="p">
	<tr>
		<td><c:out value="${p.getCode()}"></c:out></td>
		<td><c:out value="${p.getName()}"></c:out></td>
		<td><c:out value="${p.getType()}"></c:out></td>
		<td><c:out value="${p.getPrice()}"></c:out></td>
		<td><span><a href="<c:url value='/delete/${p.productId}' />"> <spring:message
								code="label.Delete" /> 
					</a>  | 
		<a href="<c:url value='/update/${p.productId}' />"><spring:message
								code="label.Update" /></a>  </span></td>
	</tr>
</c:forEach>
</table>
</body>
</html>