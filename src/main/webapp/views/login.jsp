<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="views/css/registro.css">
    <link rel="stylesheet" type="text/css" href="views/css/mensajeError.css">
    <title>Login Usuario</title>
</head>
<body>
<%@ include file="/views/componentes/header.jsp" %>
<c:if test="${not empty requestScope.mensajeError}">
    <div class="error-message">${requestScope.mensajeError}</div>
</c:if>

    <h1>Iniciar Sesión</h1>
    <a href="/Gestion-Nominas/index.jsp" class="boton-atras">Volver</a>
    <form action="Empresa?opcion=loginUsuario" method="post">
        <label for="email">Correo Electrónico:</label>
        <input type="text" name="email" id="email" required><br><br>

        <label for="password">Contraseña:</label>
        <input type="password" name="password" id="password" placeholder="Mínimo 8 caracteres" pattern=".{8,}" title="La contraseña debe tener al menos 8 caracteres" required maxlength="15" required><br><br>

        <input type="submit" value="Iniciar Sesión">
    </form>
    
<%@ include file="/views/componentes/footer.jsp" %>
</body>
<script>
document.addEventListener("DOMContentLoaded", function() {
    var errorMessage = document.querySelector(".error-message");
    
    if (errorMessage) {
        // Agrega una animación de desvanecimiento
        errorMessage.style.animation = "fadeOut 4s ease 1 forwards";
        
        // Elimina el mensaje después de 4 segundos
        setTimeout(function() {
            errorMessage.style.display = "none";
        }, 5000);
    }
});
</script>
</html>
