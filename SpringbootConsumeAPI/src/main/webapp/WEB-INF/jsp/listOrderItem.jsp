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
<title>Order Item</title>
</head>
<body>
	<h1>List Order Item</h1>
	<br />
	<h2>
		Order Id:
		<c:out value="${order.id}"></c:out>
	</h2>
	<h3>
		Order Created At:
		<c:out value="${order.createdAt}"></c:out>
	</h3>
	<table border=1 class="styled-table">
		<tr>
			<th>Id Order Item</th>
			<th>Price</th>
			<th>Quantity</th>
			<th>Id Product</th>
		</tr>
		<c:forEach items="${order.orderItems}" var="oi">
			<tr>
				<td><c:out value="${oi.id}"></c:out></td>
				<td><c:out value="${oi.price}"></c:out></td>
				<td><c:out value="${oi.quantity}"></c:out></td>
				<td><c:out value="${oi.productIdFk}"></c:out></td>
			</tr>
		</c:forEach>
	</table>
	<h3>
		<a href="http://localhost:9090/">Home</a>
	</h3>
</body>
</html>