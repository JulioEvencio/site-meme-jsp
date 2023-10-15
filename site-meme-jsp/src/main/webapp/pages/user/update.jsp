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
			<h1>Update profile</h1>
			<hr />

			<section class="update-profile">
				<c:if test="${updateProfileResponseDTO.messageSuccess}">
					<span class="message message__success">${updateProfileResponseDTO.message}</span>
				</c:if>

				<c:if test="${updateProfileResponseDTO.messageError}">
					<span class="message message__error">${updateProfileResponseDTO.message}</span>
				</c:if>

				<form class="update-profile__form" action="<c:url value='/user/update' />" method="post" enctype="multipart/form-data">
					<label>New photo:</label>
					<input type="file" name="photo" accept="image/*" required>

					<label>New username:</label>
					<input type="text" name="username" value="${updateProfileResponseDTO.username}" required />

					<label>New e-mail:</label>
					<input type="email" name="email" value="${updateProfileResponseDTO.email}" required />

					<label>New password:</label>
					<input type="password" name="password" required />

					<label>Confirm your password:</label>
					<input type="password" name="passwordConfirm" required />

					<button class="update-profile__button" type="submit">Update profile</button>
				</form>
			</section>

			<a class="link-button" href="<c:url value='/user/profile' />">Cancel</a>
		</div>
	</main>

	<jsp:include page="/components/footer.jsp"></jsp:include>
	<jsp:include page="/components/script.jsp"></jsp:include>
</body>
</html>
