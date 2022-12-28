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
<title>List Order</title>
</head>
<body>
<h1>List Order</h1>
<br/>
<h3></h3>
<h3></h3>
<br/>
<table border=1 class="styled-table">
<tr>
	<th>Order Id</th>
	<th>Date</th>
	<th>Order Item</th>
</tr>
<c:forEach items="${orderList}" var="o">
	<tr>
		<td><c:out value="${o.id}"></c:out></td>
		<td><c:out value="${o.createdAt}"></c:out></td>
		<td><a href="<c:url value='/order/${o.id}' />">Order Item 
					</a></td>
	</tr>
</c:forEach>
</table>
<h3><a href="http://localhost:8080/consume_api01/">Home</a></h3>
</body>
</html>