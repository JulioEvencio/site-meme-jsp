<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
	<jsp:include page="/components/config.jsp"></jsp:include>
	<link rel="stylesheet" href="<c:url value='/public/css/user/profile.css' />">
</head>
<body>
	<jsp:include page="/components/header.jsp"></jsp:include>

	<main class="profile">
		<div class="profile__container container">
			<section class="user">
				<figure class="user__figure">
					<img class="user__img" src="${profileResponseDTO.photo}" alt="User photo" />
				</figure>

				<h1>${profileResponseDTO.username}</h1>

				<a class="link-button" href="<c:url value='/user/update' />">Update profile</a>
				<a class="link-button" href="<c:url value='/meme/post' />">Post meme</a>
				<a class="link-button" href="<c:url value='/auth/logout' />">logout</a>
			</section>

			<section class="memes">
				<h1>My memes</h1>
				<hr />

				<div class="memes__list">
					<c:forEach var="meme" items="${profileResponseDTO.memes}">
						<div class="memes__item">
							<a class="link-img" href="<c:url value='/meme?uuid=${meme.uuid}' />">
								<img class="memes__img" src="${meme.image}" alt="Meme image" />
							</a>
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
