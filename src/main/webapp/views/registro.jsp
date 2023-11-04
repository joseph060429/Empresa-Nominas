<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="views/css/global.css">
<title>Registro Usuario</title>
</head>
<body>
    <h1>Registrar Usuario</h1>
    <a href="index.jsp" class="boton-atras">Volver</a>
    <form action="Empresa?opcion=registrarUsuario" method="post">
        <input type="hidden" name="opcion" value="registro">
        <table>
            <tr>
                <td>Nombre:</td>
                <td><input type="text" name="name" placeholder="Nombre" required maxlength= "30"></td>
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
                <td><input type="password" name="password" placeholder="Mínimo 8 caracteres" pattern=".{8,}" title="La contraseña debe tener al menos 8 caracteres" required maxlength="8"></td>
            </tr>
        </table>
        <input type="submit" value="Guardar" >
    </form>
</body>
</html>