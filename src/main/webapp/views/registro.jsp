<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="views/css/registro.css">
<link rel="stylesheet" type="text/css" href="views/css/mensajeError.css">
</head>
<body>
<%@ include file="/views/componentes/header.jsp" %>
<a href="/Gestion-Nominas/index.jsp" class="boton-atras">Volver</a>
<form action="Empresa?opcion=registrarUsuario" method="post">
    <input type="hidden" name="opcion" value="registrarUsuario">
    
    <table>
        <tr>
            <td>Nombre:</td>
            <td><input type="text" name="name" placeholder="Nombre" required maxlength="30"></td>
        </tr>
        <tr>
            <td>Apellidos:</td>
            <td><input type="text" name="surnames" placeholder="Apellidos" required maxlength="50"></td>
        </tr>
        <tr>
            <td>Email:</td>
            <td><input type="email" name="email" placeholder="Ejemplo: usuario@dominio.com" required maxlength="255"></td>
        </tr>
        <tr>
            <td>Contraseña:</td>
            <td><input type="password" name="password" placeholder="Mínimo 8 caracteres, maximo 15" pattern=".{8,}" title="La contraseña debe tener al menos 8 caracteres" required maxlength="15"></td>
        </tr>
    </table>
    <input type="submit" value="Registrar">
</form>
<c:if test="${not empty requestScope.mensajeError}">
    <div class="error-message">
        ${requestScope.mensajeError}
    </div>
</c:if>
<%@ include file="/views/componentes/footer.jsp" %>
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
</script>
</body>
</html>

