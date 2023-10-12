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
		</div>
	</main>

	<jsp:include page="/components/footer.jsp"></jsp:include>
	<jsp:include page="/components/script.jsp"></jsp:include>
</body>
</html>
