<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<header class="header">
	<div class="header__container container">
		<a class="logo" href="<%= request.getContextPath() %>">Site Meme JSP</a>
		
		<nav id="navigation" class="navigation">
			<button id="button-mobile" class="button-mobile" aria-label="Abrir Menu" aria-haspopup="true" aria-controls="menu" aria-expanded="false">&#9776;</button>
			
			<ul class="menu" role="menu">
				<li class="menu__item">
					<a class="menu__link" href="<%= request.getContextPath() %>">Home</a>
				</li>
			</ul>
		</nav>
	</div>
</header>
