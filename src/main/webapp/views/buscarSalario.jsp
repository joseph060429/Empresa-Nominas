<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Buscar Salario de Empleado</title>
<link rel="stylesheet" type="text/css" href="views/css/global.css">
</head>
<body>
	<h1>Buscar Salario de Empleado</h1>
	<a href="index.jsp" class="boton-atras">Volver</a>
	<form action="Empresa?opcion=mostrarSalario" method="post">
		<label for="dni">DNI del Empleado:</label> <input type="text" id="dni"
			name="dni" required> <input type="submit"
			value="Buscar Salario">
	</form>
</body>
</html>




