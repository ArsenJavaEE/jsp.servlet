<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>

	<form action="<c:url value="/user" />" method="post">
		First name: <input type="text" name="firstName" value="" /><br>
		Last name: <input type="text" name="lastName" value="" /><br>
		Age: <input type="text" name="age" value="" /><br> Login: <input
			type="text" name="login" value="" /><br> Pass: <input
			type="password" name="pass" value="" /><br> <input type="submit"
			value="Save" /> <br>
	</form>
	
	<form action="<c:url value="/user" />" method="get">
		<input type="submit" value="Get all users" />
	</form>

	<c:choose>
		<c:when test="${empty usersList}">
			
		</c:when>
		<c:otherwise>
		
		<table border="3">
				<c:forEach items="${usersList}" var="userb" >
					<tr>
						<td><c:out value="${userb.firstName}" /></td>
						<td><c:out value="${userb.lastName}" /></td>
						<td><c:out value="${userb.age}" /></td>
						<td><c:out value="${userb.login}" /></td>
						<td><c:out value="${userb.password}" /></td>
					</tr>
				</c:forEach>
			</table>
			
		</c:otherwise>
	</c:choose>


</body>
</html>
