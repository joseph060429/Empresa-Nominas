<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

    <h2 class="text-center mt-4">Listar Empleados</h2>
    <a href="javascript:history.back()" class="boton-atras">Volver</a>
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
    <script type="text/javascript" src="views/javaScript/borraBotones.js"></script>









