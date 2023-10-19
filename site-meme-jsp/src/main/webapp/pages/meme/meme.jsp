<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
	<jsp:include page="/components/config.jsp"></jsp:include>
	<link rel="stylesheet" href="<c:url value='/public/css/meme/meme.css' />">
</head>
<body>
	<jsp:include page="/components/header.jsp"></jsp:include>

	<main class="meme">
		<div class="container">
			<h1>${memeResponseDTO.tag}</h1>
			<hr />

			<h2>Meme by ${memeResponseDTO.username}</h2>
			<br />

			<section class="image">
				<figure class="image__figure">
					<img class="image__img" src="${memeResponseDTO.image}" alt="Meme image" />
				</figure>
			</section>

			<section class="comments">
				<div class="comments__post">
					<form class="comments__form" action="<c:url value='/meme' />" method="post">
						<c:if test="${memeResponseDTO.messageSuccess}">
							<span class="message message__success">${memeResponseDTO.message}</span>
						</c:if>

						<c:if test="${memeResponseDTO.messageError}">
							<span class="message message__error">${memeResponseDTO.message}</span>
						</c:if>

						<input type="text" name="uuid" value="${memeResponseDTO.uuid}" hidden />

						<label>New comment:</label>
						<input type="text" name="comment" value="${memeResponseDTO.comment}" required />

						<button type="submit">Post comment</button>
					</form>
				</div>

				<div class="comments__items">
					<c:forEach var="comment" items="${memeResponseDTO.comments}">
						<div class="comments__item">
							<h2>${comment.username}</h2>
							<hr />
							<p>${comment.comment}</p>
						</div>
					</c:forEach>
				</div>
			</section>
		</div>
	</main>

	<jsp:include page="/components/footer.jsp"></jsp:include>
	<jsp:include page="/components/script.jsp"></jsp:include>
</body>
</html>
