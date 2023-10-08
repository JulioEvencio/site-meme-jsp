<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
	<jsp:include page="/components/config.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="/components/header.jsp"></jsp:include>

	<main>
		<div class="container">
			<h1>Register</h1>
			<p>Create an account, it's free!</p>
			<hr />

			<section class="register">
				<c:if test="${messageSuccess}">
					<span class="message message__success">${message}</span>
				</c:if>

				<c:if test="${messageError}">
					<span class="message message__error">${message}</span>
				</c:if>

				<form class="register__form" action="<c:url value='/register' />" method="post">
					<label>Username:</label>
					<input type="text" name="username" value="${username}" required />

					<label>E-mail:</label>
					<input type="email" name="email" value="${email}" required />

					<label>Password:</label>
					<input type="password" name="password" required />

					<label>Confirm your password:</label>
					<input type="password" name="passwordConfirm" required />

					<button class="register__button" type="submit">Create account</button>
				</form>
			</section>
		</div>
	</main>

	<jsp:include page="/components/footer.jsp"></jsp:include>
	<jsp:include page="/components/script.jsp"></jsp:include>
</body>
</html>
