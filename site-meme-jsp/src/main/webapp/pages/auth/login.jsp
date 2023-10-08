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
			<h1>Login</h1>
			<hr />

			<section class="login">
				<c:if test="${loginResponseDTO.messageError}">
					<span class="message message__error">${loginResponseDTO.message}</span>
				</c:if>

				<form class="login__form" action="<c:url value='/auth/login' />" method="post">
					<label>E-mail:</label>
					<input type="email" name="email" value="${loginResponseDTO.email}" required />

					<label>Password:</label>
					<input type="password" name="password" required />

					<button class="login__button" type="submit">Login</button>

					<p>
						Not have an account yet?
						<a href="<c:url value='/auth/register' />" >Click here</a>
						to create an account!
					</p>
				</form>
			</section>
		</div>
	</main>

	<jsp:include page="/components/footer.jsp"></jsp:include>
	<jsp:include page="/components/script.jsp"></jsp:include>
</body>
</html>
