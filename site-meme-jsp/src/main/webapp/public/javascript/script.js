document.querySelector('#button-mobile').addEventListener('click', showMenu)
document.querySelector('#button-mobile').addEventListener('touchstart', showMenu)

function showMenu(event) {
	if (event.type === 'touchstart') {
		event.preventDefault()
	}

	let navigation = document.querySelector('#navigation')
	navigation.classList.toggle('activated')

	let activated = navigation.classList.contains('activated')
	event.currentTarget.setAttribute('aria-expanded', activated)

	if (activated) {
		event.currentTarget.setAttribute('aria-label', 'Fechar Menu')
	} else {
		event.currentTarget.setAttribute('aria-label', 'Abrir Menu')
	}
}
