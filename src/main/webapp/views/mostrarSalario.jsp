<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Mostrar Salario</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background: #3498db;
        }
        h1 {
            text-align: center;
            color: #fff;
            text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.7);
            margin: 20px 0;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            color: #fff;
        }
        th, td {
            border: 1px solid #fff;
            padding: 8px;
            text-align: center;
        }
        th {
            background-color: #3498db;
        }
        tr:nth-child(even) {
            background-color: #2980b9;
        }
        tr:nth-child(odd) {
            background-color: #3498db;
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
    <h1>Salario del Empleado</h1>
    <!-- Tuve que poner esto para que me funcionara el ir para atras -->
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

