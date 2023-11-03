<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Salario del Empleado</title>
    <link rel="stylesheet" type="text/css" href="views/css/tablas.css">
</head>
<body>
    <h1>Salario del Empleado</h1>
    <a href="javascript:history.back()" class="boton-atras">Volver</a>
    <table>
        <tr>
            <th>DNI</th>
            <th>Nombre</th>
            <th>Salario</th>
        </tr>
        <tr>
            <td>${dni}</td>
            <td>${nombre}</td>
            <td>${sueldo}</td>
        </tr>
    </table>
</body>
</html>
 
