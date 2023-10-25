<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Menu de opciones</title>
    <style>
        body {
            background-color: #f0f0f0;
            font-family: Arial, sans-serif;
        }
        .container {
            max-width: 600px;
            margin: 0 auto;
            padding: 20px;
            text-align: center;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0px 2px 6px rgba(0, 0, 0, 0.1);
        }
       .menu {
        border: 2px solid #007bff;
        border-collapse: collapse;
        width: 100%;
        margin: 20px auto;
    }
      .menu th, .menu td {
        padding: 15px;
        text-align: center;
    }
        .menu a {
        text-decoration: none;
        color: #007bff;
        font-weight: bold;
        font-size: 18px;
    }
     .menu a:hover {
        color: #0056b3;
    }
        .alert {
            position: fixed;
            top: 10px;
            right: 10px;
            padding: 15px;
            border-radius: 5px;
            font-weight: bold;
            display: none;
            opacity: 0;
            transition: opacity 0.5s;
            background-color: rgba(0, 0, 0, 0.7);
            color: white;
            z-index: 1000;
        }
        .alert-success {
            background-color: #4CAF50; /* Verde para éxito */
        }
        .alert-error {
            background-color: #f44336; /* Rojo para error */
        }
        h1 {
        color: #007bff;
        font-size: 24px;
        margin-bottom: 20px;
        text-transform: uppercase;
        text-align: center;
    }
    </style>
    <script>
        function showAlert(type, message) {
            var alertElement = document.getElementById("alert");
            alertElement.innerHTML = message;
            alertElement.className = "alert alert-" + type;
            alertElement.style.display = "block";
            alertElement.style.opacity = 1; // Asegúrate de que la opacidad esté configurada en 1 para mostrar la alerta
            setTimeout(function () {
                alertElement.style.opacity = 0; // Cambia la opacidad a 0 para ocultar la alerta suavemente
            }, 4000);
        }
    </script>
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

