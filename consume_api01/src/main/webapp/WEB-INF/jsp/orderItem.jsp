<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<title>Spring MVC Hibernate - CRUD</title>
<style type="text/css">
body {
	font-family: Arial;
	font-size: 10px;
	margin: 30px;
}

.userTable, .userTable td {
	border-collapse: collapse;
	border: 1px solid #0000FF;
	margin: 2px;
	padding: 2px 2px 2px 10px;
	font-size: 14px;
}

.userTable th {
	font-weight: bold;
	font-size: 14px;
	background-color: #5C82FF;
	color: white;
}

.userLabel {
	font-family: Arial;
	font-size: 14px;
	font-weight: bold;
}

a, a:AFTER {
	color: blue;
}
</style>
<script type="text/javascript">
    function deleteOrderItem(orderItemId){
        if(confirm('Do you want to delete this OrderItem ?')){
            var url = 'delete/'+orderItemId;
            window.location.href = url;
        }
    }
    </script>
</head>
<body>

	<h2>SpringMVC-Hibernate CRUD Application</h2>
	<p style="color: green; font-weight: bold;">
		<a href="<c:url value='/home' />"> Add New OrderItem</a>
	</p>
	<c:url var="action" value="/orderItem/add"></c:url>
	<form:form method="post" action="${action}" modelAttribute="orderItem">
		<table>

			<c:if test="${not empty orderItem.code}">
				<tr>
					<td><form:label path="id" cssClass="userLabel">
							<spring:message code="label.OrderItemId" />
						</form:label></td>

					<td><form:input path="id" readonly="true" size="10"
							disabled="true" /> <form:hidden path="id" /></td>
				</tr>
			</c:if>

			<tr>
				<td><form:label path="code" cssClass="userLabel">
						<spring:message code="label.Code" />
					</form:label></td>

				<td><form:input path="code" /></td>
			</tr>

			<tr>
				<td><form:label path="name" cssClass="userLabel">
						<spring:message code="label.Name" />
					</form:label></td>

				<td><form:input path="name" /></td>
			</tr>

			<tr>
				<td><form:label path="price" cssClass="userLabel">
						<spring:message code="label.Price" />
					</form:label></td>

				<td><form:input path="price" /></td>
			</tr>

			<tr>
				<td><form:label path="quantity" cssClass="userLabel">
						<spring:message code="label.Quantity" />
					</form:label></td>

				<td><form:input path="quantity" /></td>
			</tr>
			
			<tr>
				<td><form:label path="type" cssClass="userLabel">
						<spring:message code="label.Type" />
					</form:label></td>

				<td><form:input path="type" /></td>
			</tr>

			<tr>
				<td colspan="2"><input type="submit"
					value="<spring:message code="label.AddOrderItem"/>" /></td>
			</tr>

		</table>
	</form:form>

	<h3>List of OrderItem</h3>

	<c:if test="${not empty orderItemList}">
		<table class="userTable">
			<tr>
				<th width="160">Code</th>
				<th width="80">Name</th>
				<th width="60">Price</th>
				<th width="60">Quantity</th>
				<th width="80">Type</th>
				<th width="100">Action</th>
			</tr>
			<c:forEach items="${orderItemList}" var="orderItem">
				<tr>
					<td><a href="<c:url value='/update/${orderItem.id}' />">${orderItem.code}</a>
					</td>
					<td>${orderItem.name}</td>
					<td>${orderItem.price}</td>
					<td>${orderItem.quantity}</td>
					<td>${orderItem.type}</td>
					<td><a href="<c:url value='/update/${orderItem.id}' />"> <spring:message
								code="label.UpdateOrderItem" /> >
					</a> <a href="#" onclick="javascript:deleteOrderItem(${orderItem.id})"> <spring:message
								code="label.Delete" /> >
					</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>

</body>
</html>