<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    
 <link rel="stylesheet" type="text/css" href="views/css/mensajeError.css">
 <link rel="stylesheet" type="text/css" href="views/css/registro.css">
 <link rel="stylesheet" type="text/css" href="/views/css/index.css">
 
<script type="text/javascript" src="/Asignaturas/Entorno-Servidor/Gestion-Nominas/src/main/webapp/views/javaScript/borraBotones.js"></script>
</head>
<body>
<%@ include file="views/componentes/header.jsp" %>

<div class="content">
    <c:choose>
        <c:when test="${not empty content}">
            <jsp:include page="${content}" />
            
        </c:when>
        <c:otherwise>
            <%@ include file="views/componentes/PaginaInicial.jsp" %>
        </c:otherwise>
    </c:choose>
</div>
<%@ include file="views/componentes/footer.jsp" %>
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
	</c:if>
</body>











