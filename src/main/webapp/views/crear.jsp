<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%> --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="views/css/registro.css">
<link rel="stylesheet" type="text/css" href="views/css/mensajeError.css">
<!-- Expediente X decirselo a Nacho -->
<!-- <link rel="stylesheet" type="text/css" href="views/css/mensajeExito.css"> -->
<style>
.exito-message {
	position: absolute;
	top: 0;
	right: 0;
	background-color: green;
	color: white;
	padding: 20px;
	font-size: 18px;
	text-align: right;
	border-radius: 10px;
	opacity: 1;
	transition: opacity 0.5s;
	animation: fadeOut 4s ease 1 forwards;
}

@
keyframes fadeOut { 0% {
	opacity: 1;
}
100
%
{
opacity
:
0;
}
}
</style>
<title>Crear Empleado</title>
</head>
<body>
	<%@ include file="/views/componentes/header.jsp"%>
	<c:if test="${not empty requestScope.mensajeError}">
		<div class="error-message">${requestScope.mensajeError}</div>
	</c:if>
	<c:if test="${not empty requestScope.mensajeExito}">
		<div class="exito-message">${requestScope.mensajeExito}</div>
	</c:if>
	<div class="container">
		<h2 class="text-center mt-4">Crear Empleado</h2>
		<a
			href="/Gestion-Nominas/views/componentes/menu.jsp?volver=true&mostrarMensaje=false"
			class="boton-atras">Volver</a>

		<form action="Empresa" method="post">
			<input type="hidden" name="opcion" value="guardar">
			<table class="table">
				<tr>
					<td>DNI:</td>
					<td><input type="text" name="dni" class="form-control"
						placeholder="Ejemplo: 12345678A" pattern="[0-9]{8}[A-Z]{1}"
						title="Formato válido: 12345678A" required maxlength="9"></td>
				</tr>
				<tr>
					<td>Nombre:</td>
					<td><input type="text" name="nombre" class="form-control"
						placeholder="Ejemplo: Juan Pérez" required maxlength="70"></td>
				</tr>
				<tr>
					<td>Sexo:</td>
					<td><input type="text" name="sexo" class="form-control"
						placeholder="Ejemplo: M o F" pattern="[MmFf]{1}"
						title="Formato válido: M o F" required maxlength="1"></td>
				</tr>
				<tr>
					<td>Categoría:</td>
					<td><input type="number" name="categoria" class="form-control"
						placeholder="Ejemplo: 2" required min="1" max="10"></td>
				</tr>
				<tr>
					<td>Años:</td>
					<td><input type="number" name="anyos" class="form-control"
						placeholder="Ejemplo: 5" required min="0" max="99"></td>
				</tr>
			</table>
			<button type="submit" class="btn btn-success btn-block">Guardar</button>
		</form>
	</div>
	<%@ include file="/views/componentes/footer.jsp"%>
</body>
<script>
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

	/* Mensaje para que se borre el empleado */
	document.addEventListener("DOMContentLoaded", function() {
		var errorMessage = document.querySelector(".error-message");

		if (errorMessage) {
			// Agrega una animación de desvanecimiento
			errorMessage.style.animation = "fadeOut 4s ease 1 forwards";

			// Elimina el mensaje después de 5 segundos
			setTimeout(function() {
				errorMessage.style.display = "none";
			}, 5000);
		}
	});
</script>


</html>






