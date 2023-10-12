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
			<h1>Post meme</h1>
			<hr />

			<section class="post-meme">
				<c:if test="${postMemeResponseDTO.messageSuccess}">
					<span class="message message__success">${postMemeResponseDTO.message}</span>
				</c:if>

				<c:if test="${postMemeResponseDTO.messageError}">
					<span class="message message__error">${postMemeResponseDTO.message}</span>
				</c:if>

				<form class="post-meme__form" action="<c:url value='/meme/post' />" method="post" enctype="multipart/form-data">
					<label>Select an image:</label>
					<input type="file" name="image" accept="image/*" required>

					<label>Tag:</label>
					<select name="tag">
						<c:forEach var="tag" items="${postMemeResponseDTO.tags}">
							<option value="${tag}">${tag}</option>
						</c:forEach>
					</select>

					<button class="post-meme__button" type="submit">Post meme</button>
				</form>
			</section>

			<a class="link-button" href="<c:url value='/user/profile' />">Back</a>
		</div>
	</main>

	<jsp:include page="/components/footer.jsp"></jsp:include>
	<jsp:include page="/components/script.jsp"></jsp:include>
</body>
</html>
