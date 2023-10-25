<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Buscar Salario</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background: linear-gradient(45deg, #16a085, #1abc9c);
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
        form {
            text-align: center;
        }
        label {
            display: block;
            font-size: 18px;
            color: #fff;
            margin: 10px 0;
        }
        input[type="text"] {
            width: 100%;
            padding: 10px;
            border: none;
            background: rgba(255, 255, 255, 0.1);
            color: #fff;
            border-bottom: 2px solid #fff;
            font-size: 18px;
            margin-bottom: 20px;
        }
        input[type="text"]:focus {
            border-bottom: 2px solid #16a085;
        }
        input[type="submit"] {
            background: #1abc9c;
            border: none;
            color: #fff;
            padding: 15px 30px;
            cursor: pointer;
            font-size: 18px;
            transition: background 0.3s ease;
        }
        input[type="submit"]:hover {
            background: #149b80;
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
    <h1>Buscar Salario de Empleado</h1>
     <a href="index.jsp" class="boton-atras">Volver</a>
    <form action="Empresa?opcion=mostrarSalario" method="post">
        <label for="dni">DNI del Empleado:</label>
        <input type="text" id="dni" name="dni" required>
        <input type="submit" value="Buscar Salario">
    </form>
</body>
</html>
