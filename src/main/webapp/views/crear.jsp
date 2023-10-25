<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Crear Empleado</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f0f0f0;
    }

    h1 {
        text-align: center;
        color: #333;
    }

    form {
        max-width: 400px;
        margin: 0 auto;
        padding: 20px;
        background-color: #fff;
        border-radius: 5px;
        box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
    }

    table {
        width: 100%;
    }

    table td {
        padding: 10px;
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

    input[type="text"], input[type="number"] {
        width: 100%;
        padding: 10px;
        margin: 5px 0;
        border: 1px solid #ccc;
        border-radius: 3px;
    }

    input[type="submit"] {
        background-color: #007bff;
        color: #fff;
        border: none;
        border-radius: 3px;
        padding: 10px 20px;
        cursor: pointer;
    }

    input[type="submit"]:hover {
        background-color: #0056b3;
    }
</style>
</head>
<body>
    <h1>Crear Empleado</h1>
    <a href="index.jsp" class="boton-atras">Volver</a>
    <form action="Empresa?opcion=guardar" method="post">
        <input type="hidden" name="opcion" value="guardar">
        <table>
            <tr>
                <td>DNI:</td>
               <td><input type="text" name="dni" placeholder="Ejemplo: 12345678A" pattern="[0-9]{8}[A-Z]{1}" title="Formato válido: 12345678A" required maxlength="9"></td>

            </tr>
            <tr>
                <td>Nombre:</td>
                <td><input type="text" name="nombre" placeholder="Ejemplo: Juan Pérez" required maxlength="70"></td>
            </tr>
            <tr>
                <td>Sexo:</td>
                <td><input type="text" name="sexo" placeholder="Ejemplo: M o F" pattern="[MmFf]{1}" title="Formato válido: M o F" required maxlength="1"></td>
            </tr>
            <tr>
                <td>Categoría:</td>
                <td><input type="number" name="categoria" placeholder="Ejemplo: 2" required min="1" max="10"></td>
            </tr>
            <tr>
                <td>Años:</td>
                <td><input type="number" name="anyos" placeholder="Ejemplo: 5" required min="0" max="99"></td>
            </tr>
        </table>
        <input type="submit" value="Guardar">
    </form>
</body>
</html>



