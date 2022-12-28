<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<c:url var="action" value="/formOrder"></c:url>
		<form:form action="${action}" method="get" >
			<h1 align="center">Product Item</h1>
			<hr/>
			Product
			<br/>
			<select name="new_order">
				<c:forEach items="${productList}" var="p">
				<option ><c:out value="${p.getProductId()} - ${p.getCode()} - ${p.getName()} - ${p.getType()} - ${p.getPrice()}"></c:out></option>
				</c:forEach>
			</select>
			<br/>
			<br/>
			Quantity <br/>
			<input type="number" name="quantity" value="1"/>
			<br/>
			<input type="submit" value="ADD"/>
		</form:form>
</body>
</html>