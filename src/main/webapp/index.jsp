<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Menu de opciones</title>
    <link rel="stylesheet" type="text/css" href="views/css/index.css">
</head>
<body>
    <div class="container">
        <h1>Menu de Opciones Empleado</h1>
        <table class="menu">
            <tr>
                <td><a href="Empresa?opcion=crear">Crear un Empleado</a></td>
            </tr>
            <tr>
                <td><a href="Empresa?opcion=listar">Listar Empleados</a></td>
            </tr>
            <tr>
                <td><a href="Empresa?opcion=mostrarSalario">Buscar Salarios</a></td>
            </tr>
            <tr>
                <td><a href="Empresa?opcion=editarEmpleado">Editar Empleados</a></td>
            </tr>
        </table>
        <div id="alert" class="alert"></div>
    </div>
    <script>
        function showAlert(type, message) {
            var alertElement = document.getElementById("alert");
            alertElement.innerHTML = message;
            alertElement.className = "alert alert-" + type;
            alertElement.style.display = "block";
            alertElement.style.opacity = 1; 
            setTimeout(function () {
                alertElement.style.opacity = 0; 
            }, 4000);
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
</html>

