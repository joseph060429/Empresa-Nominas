<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- Comentarle a Nacho que no entiendo porque no me pilla el CSS -->
<!-- <link rel="stylesheet" type="text/css" href="/views/css/mensajeExito.css"> -->
<link rel="stylesheet" type="text/css" href="/views/css/index.css">
<style>
.success-message {
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
}

.error-message {
    position: absolute;
    top: 0;
    right: 0;
    background-color: red;
    color: white;
    padding: 20px; /* Aumenta el espacio alrededor del mensaje */
    font-size: 18px; /* Aumenta el tamaño de la fuente */
    text-align: right;
    border-radius: 10px; /* Aumenta la curvatura de los bordes */
    opacity: 1;
    transition: opacity 0.5s;
}

/* Animación de desvanecimiento */
@keyframes fadeOut {
    0% {
        opacity: 1;
    }
    100% {
        opacity: 0;
    }
}

/* Animación de desvanecimiento */
@keyframes fadeOut {
	0% {
		opacity: 1;
	}
	100% {
		opacity: 0;
	}
} 
</style>
</head>
<body>

<%@ include file="/views/componentes/header.jsp"%>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="text-center mb-4">
                <h1 style="color: #fff; font-size: 2rem;">Menú de Opciones Empleado</h1>
            </div>
            <div class="menu-container">
                <ul class="list-group">
                    <li class="list-group-item">
                        <a href="/Gestion-Nominas/Empresa?opcion=crear" class="btn btn-danger btn-block" style="background-color: #ff5733; border-color: #ff5733;">Registrar un Empleado</a>
                    </li>
                    <li class="list-group-item">
                        <a href="/Gestion-Nominas/Empresa?opcion=listar" class="btn btn-danger btn-block" style="background-color: #ff5733; border-color: #ff5733;">Buscar Empleados</a>
                    </li>
                    <li class="list-group-item">
                        <a href="/Gestion-Nominas/Empresa?opcion=mostrarSalario" class="btn btn-danger btn-block" style="background-color: #ff5733; border-color: #ff5733;">Buscar Salarios</a>
                    </li>
                    <li class="list-group-item">
                        <a href="/Gestion-Nominas/Empresa?opcion=editarEmpleado" class="btn btn-danger btn-block" style="background-color: #ff5733; border-color: #ff5733;">Editar Empleados</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
<div id="alert" class="alert alert-success mt-4" role="alert"></div>
<script>
	function showAlert(type, message) {
		var alertElement = document.getElementById("alert");
		alertElement.innerHTML = message;
		alertElement.className = "success-message";
		alertElement.style.display = "block";
		alertElement.style.opacity = 1;
		setTimeout(function() {
			alertElement.style.opacity = 0;
			setTimeout(function() {
				alertElement.style.display = "none"; // Ocultar el mensaje
			}, 500); // Tiempo adicional para ocultar el mensaje
		}, 6000);
	}
	// Agregue esta función para que se me borre el botón login y header
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
	
	//Funcion para que me borre el mensaje de inicio de sesion cuando presiono el boton volver
	function checkShowMessageParameter() {
	    const urlParams = new URLSearchParams(window.location.search);
	    const mostrarMensaje = urlParams.get("mostrarMensaje");
	    if (mostrarMensaje === "false") {
	        // Ocultar el mensaje de éxito
	        const alertElement = document.getElementById("alert");
	        alertElement.style.display = "none";
	    }
	}
	// Llamo a la función cuando se cargue la página
	window.addEventListener("load", checkShowMessageParameter);
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
</c:if>

<%@ include file="/views/componentes/footer.jsp"%>
</body>
</html>






