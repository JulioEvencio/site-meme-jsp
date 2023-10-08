<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header class="header">
	<div class="header__container container">
		<a class="logo" href="<c:url value='/' />">Site Meme JSP</a>

		<nav id="navigation" class="navigation">
			<button id="button-mobile" class="button-mobile" aria-label="Abrir Menu" aria-haspopup="true" aria-controls="menu" aria-expanded="false">&#9776;</button>

			<ul class="menu" role="menu">
				<li class="menu__item">
					<a class="menu__link" href="<c:url value='/' />">Home</a>
				</li>

				<c:choose>
					<c:when test="${not empty sessionScope.user}">
						<li class="menu__item">
							<a class="menu__link" href="<c:url value='/profile' />">profile</a>
						</li>
					</c:when>
					<c:otherwise>
						<li class="menu__item">
							<a class="menu__link" href="<c:url value='/auth/register' />">Register</a>
						</li>
					</c:otherwise>
				</c:choose>
			</ul>
		</nav>
	</div>
</header>
