<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Order Here!</title>
</head>
<body>
<h1>Form Order</h1>
<hr />
	<c:url var="action" value="/order/save"></c:url>
	<form:form action="${action }" method="post" id="formAllOrder" modelAttribute="order">
		<table border=1>
			<tr>
				<th>Name</th>
				<th>Price</th>
				<th>Quantity</th>
				<th>Total</th>
				<c:choose>
					<c:when test="${productsDto != null}">
						<c:forEach items="${productsDto}" var="pl">
							<tr>
								<td><c:out value="${pl.getName()}"></c:out> <input
									type="hidden" name="product_name" value="${pl.getName()}" /></td>
								<td><c:out value="${pl.getPrice()}"></c:out> <input
									type="hidden" name="product_price" value="${pl.getPrice()}" /></td>
								<td><c:out value="${pl.getQuantity()}"></c:out> <input
									type="hidden" name="product_quantity"
									value="${pl.getQuantity()}" /></td>
								<td><c:out value="${pl.getTotalPrice()}"></c:out> <input
									type="hidden" name="product_total_price"
									value="${pl.getTotalPrice()}" /></td>
							</tr>
						</c:forEach>
						<tr>
							<td colspan="4"></td>
						</tr>
						<tr>
							<td colspan="3">Total :</td>
							<td><c:out value="${totalOrder}"></c:out><input
								type="hidden" name="product_total_order" value="${totalOrder}" /></td>
						</tr>
					</c:when>
					<c:otherwise>
						<tr>
							<td>empty</td>
							<td>empty</td>
							<td>empty</td>
							<td>empty</td>
						</tr>
						<tr>
							<td colspan="4"></td>

						</tr>
						<tr>
							<td colspan="3">Total :</td>
							<td>0</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</tr>
			<tr>
				<td colspan="3"></td>
				<td><a
					href="http://localhost:9090/formAddOrder"
					type="text">ADD ORDER</a></td>
			</tr>
			<tr>
				<td colspan="3"></td>
				<td><input type="submit" value="Checkout"></td>
			</tr>
		</table>
	</form:form>
	
<h3><a href="http://localhost:9090/">Home</a></h3>
</body>
</html>