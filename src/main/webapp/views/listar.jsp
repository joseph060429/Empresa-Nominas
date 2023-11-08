<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Listar Empleados</title>
    <link rel="stylesheet" type="text/css" href="views/css/tablas.css">
</head>
<body>
    <h1>Listar Empleados</h1>
    <!-- <a href="/Gestion-Nominas/views/componentes/menu.jsp" class="boton-atras">Volver</a> -->
    <a href="/Gestion-Nominas/views/componentes/menu.jsp?volver=true&mostrarMensaje=false" class="boton-atras">Volver</a>
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








