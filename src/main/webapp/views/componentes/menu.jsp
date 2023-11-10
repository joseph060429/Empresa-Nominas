<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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

/* Animación de desvanecimiento */
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

	<div class="container">
		<div class="row justify-content-center">
			<div class="col-md-6">
				<div class="text-center mb-4">
					<h1 style="color: #fff; font-size: 2rem;">Menú de Opciones
						Empleado</h1>
				</div>
				<div class="menu-container">
					<ul class="list-group">
						<li class="list-group-item"><a
							href="/Gestion-Nominas/Empresa?opcion=crear"
							class="btn btn-danger btn-block"
							style="background-color: #ff5733; border-color: #ff5733;">Registrar
								un Empleado</a></li>
						<li class="list-group-item"><a
							href="/Gestion-Nominas/Empresa?opcion=listar"
							class="btn btn-danger btn-block"
							style="background-color: #ff5733; border-color: #ff5733;">Buscar
								Empleados</a></li>
						<li class="list-group-item"><a
							href="/Gestion-Nominas/Empresa?opcion=mostrarSalario"
							class="btn btn-danger btn-block"
							style="background-color: #ff5733; border-color: #ff5733;">Buscar
								Salarios</a></li>
						<li class="list-group-item"><a
							href="/Gestion-Nominas/Empresa?opcion=editarEmpleado"
							class="btn btn-danger btn-block"
							style="background-color: #ff5733; border-color: #ff5733;">Editar
								Empleados</a></li>
						<li class="list-group-item"><a
							href="/Gestion-Nominas/Empresa?opcion=eliminarEmpleado"
							class="btn btn-danger btn-block"
							style="background-color: #ff5733; border-color: #ff5733;">Eliminar Empleados</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
 <div id="alert" class="alert alert-success mt-4" role="alert"></div>
	<script type="text/javascript" src="views/javaScript/borraBotones.js"></script>
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
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
	</c:if>


 --%>




