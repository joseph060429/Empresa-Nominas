<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<a href="/Gestion-Nominas/index.jsp" class="boton-atras">Volver</a>
<form action="Empresa?opcion=registrarUsuario" method="post">
	<input type="hidden" name="opcion" value="registrarUsuario">
		<table>
		<tr>
			<td>Nombre:</td>
			<td><input type="text" name="name" placeholder="Nombre" required
				maxlength="30"></td>
		</tr>
		<tr>
			<td>Apellidos:</td>
			<td><input type="text" name="surnames" placeholder="Apellidos"
				required maxlength="50"></td>
		</tr>
		<tr>
			<td>Email:</td>
			<td><input type="email" name="email"
				placeholder="Ejemplo: usuario@dominio.com" required maxlength="255"></td>
		</tr>
		<tr>
			<td>Contraseña:</td>
			<td><input type="password" name="password"
				placeholder="Mínimo 8 caracteres, maximo 15" pattern=".{8,}"
				title="La contraseña debe tener al menos 8 caracteres" required
				maxlength="15"></td>
		</tr>
	</table>
	<input type="submit" value="Registrar">
</form>





































<%-- <div id="alert" class="alert alert-success mt-4" role="alert"></div>
 <c:if test="${not empty requestScope.mensajeError}">
	<div class="error-message">${requestScope.mensajeError}</div>
</c:if>
<script>
	document.addEventListener("DOMContentLoaded", function() {
		var errorMessage = document.querySelector(".error-message");

		if (errorMessage) {
			// Agrega una animación de desvanecimiento
			errorMessage.style.animation = "fadeOut 4s ease 1 forwards";

			// Elimina el mensaje después de 5 segundos
			setTimeout(function() {
				errorMessage.style.display = "none";
			}, 7000);
		}
	});
</script>  --%>


<%-- <div id="alert" class="alert alert-success mt-4" role="alert"></div>
<script>
		//Mensajes de error o de exito	
		function showAlert(type, message) {
			var alertElement = document.getElementById("alert");
			alertElement.innerHTML = message;

			if (type === "error") {
				alertElement.className = "error-message"; // Usar la clase error-message para los mensajes de error
			} else {
				alertElement.className = "success-message";
			}

			alertElement.style.display = "block";
			alertElement.style.opacity = 1;
			setTimeout(function() {
				alertElement.style.opacity = 0;
				setTimeout(function() {
					alertElement.style.display = "none"; // Ocultar el mensaje
				}, 500); // Tiempo adicional para ocultar el mensaje
			}, 6000);
		}
</script>

<c:if test="${not empty mensajeExito}">
		<script>
			showAlert("success", "<c:out value='${mensajeExito}' />");
		</script>
	</c:if>

	<c:if test="${not empty mensajeError}">
		<script>
			showAlert("error", "<c:out value='${mensajeError}' />");
		</script>
	</c:if> --%>
	
<%-- <script>
		//Mensajes de error o de exito	
		function showAlert(type, message) {
			var alertElement = document.getElementById("alert");
			alertElement.innerHTML = message;

			if (type === "error") {
				alertElement.className = "error-message"; // Usar la clase error-message para los mensajes de error
			} else {
				alertElement.className = "success-message";
			}

			alertElement.style.display = "block";
			alertElement.style.opacity = 1;
			setTimeout(function() {
				alertElement.style.opacity = 0;
				setTimeout(function() {
					alertElement.style.display = "none"; // Ocultar el mensaje
				}, 500); // Tiempo adicional para ocultar el mensaje
			}, 6000);
		}
</script>

	<c:if test="${not empty mensajeExito}">
		<script>
			showAlert("success", "<c:out value='${mensajeExito}' />");
		</script>
	</c:if>

	<c:if test="${not empty mensajeError}">
		<script>
			showAlert("error", "<c:out value='${mensajeError}' />");
		</script>
	</c:if> --%>


