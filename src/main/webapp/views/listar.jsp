<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Listar Empleados</title>
    <a href="index.jsp" class="boton-atras">Volver</a>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background: linear-gradient(45deg, #2980b9, #6ab0f3);
            background-size: 400% 400%;
            animation: gradient 15s ease infinite;
        }
        @keyframes gradient {
            0% {
                background-position: 0% 50%;
            }
            50% {
                background-position: 100% 50%;
            }
            100% {
                background-position: 0% 50%;
            }
        }
        h1 {
            text-align: center;
            margin: 20px 0;
            color: #fff;
            text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.7);
        }
        table {
            width: 80%;
            margin: 0 auto;
            border-collapse: collapse;
            background-color: #fff;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
        }
        table, th, td {
            border: 1px solid #ddd;
        }
        th, td {
            padding: 12px;
            text-align: left;
        }
        th {
            background-color: #2980b9;
            color: #fff;
        }
        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
        .boton-atras {
           display: block;
    padding: 10px 20px;
    background-color: #ff5733; 
    color: #fff;
    text-decoration: none;
    border: none;
    border-radius: 3px;
    cursor: pointer;
    text-align: center;
    position: fixed;
    bottom: 10px;
    left: 10px;
    z-index: 1;
    }

    .boton-atras:hover {
       background-color: #4CAF50;
    }
    </style>
</head>
<body>
    <h1>Listar Empleados</h1>
    <a href="index.jsp" class="boton-atras">Volver</a>
    <table>
        <tr>
            <th>DNI</th>
            <th>Nombre</th>
            <th>Sexo</th>
            <th>Categoría</th>
            <th>Años</th>
        </tr>
        <c:forEach var="empleado" items="${lista}">
            <tr>
                <td><c:out value="${empleado.dni}" /></td>
                <td><c:out value="${empleado.nombre}" /></td>
                <td><c:out value="${empleado.sexo}" /></td>
                <td><c:out value="${empleado.categoria}" /></td>
                <td><c:out value="${empleado.anyos}" /></td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>

