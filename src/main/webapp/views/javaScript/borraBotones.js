document.addEventListener("DOMContentLoaded", function() {
		var registroButton = document.querySelector("a[href*='registro']");
		var loginButton = document.querySelector("a[href*='login']");

		if (registroButton) {
			registroButton.style.display = "none";
		}

		if (loginButton) {
			loginButton.style.display = "none";
		}
	});