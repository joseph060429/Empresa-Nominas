<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="views/css/global.css">
     <link rel="stylesheet" type="text/css" href="views/css/login.css">
    <title>Login Usuario</title>
</head>
<body>
    <h1>Iniciar Sesión</h1>
    <a href="index.jsp" class="boton-atras">Volver</a>
    <form action="Empresa?opcion=loginUsuario" method="post">
        <label for="email">Correo Electrónico:</label>
        <input type="text" name="email" id="email" required><br><br>

        <label for="password">Contraseña:</label>
        <input type="password" name="password" id="password" placeholder="Mínimo 8 caracteres" pattern=".{8,}" title="La contraseña debe tener al menos 8 caracteres" required maxlength="8" required><br><br>

        <input type="submit" value="Iniciar Sesión">
    </form>
    
    <div class="alert alert-error" id="errorAlert">
        Error: Email o contraseña inválidos
    </div>
    
    <c:if test="${not empty mensajeError}">
        <script>
            var errorAlert = document.getElementById("errorAlert");
            errorAlert.style.display = "block";
            errorAlert.style.opacity = 1;
            setTimeout(function () {
                errorAlert.style.opacity = 0;
            }, 4000);
        </script>
    </c:if>
</body>
</html>
