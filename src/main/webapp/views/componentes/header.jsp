<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%-- <%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> --%>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Tu Aplicación</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>

<%-- <header class="bg-dark text-white p-4">
    <div class="container">
        <div class="row">
            <div class="col-md-6">
                <h1 class="display-4 no-global">Mi Aplicación</h1>
            </div>
            <div class="col-md-6 text-right">
                <c:choose>
                    <c:when test="${not empty param.opcion && param.opcion eq 'registro'}"> </c:when>
                    <c:otherwise>
                        <a href="Empresa?opcion=registro" class="btn btn-primary">Registrarse</a>
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${not empty param.opcion && param.opcion eq 'login'}">
                    </c:when>
                    <c:otherwise>
                        <a href="Empresa?opcion=login" class="btn btn-primary ml-2">Login</a>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</header> --%>
<header class="bg-dark text-white p-4">
    <div class="container">
        <div class="row">
            <div class="col-md-6">
                <h1 class="display-4 no-global">Mi Aplicación</h1>
            </div>
            <div class="col-md-6 text-right">
                <c:choose>
                    <c:when test="${not empty param.opcion && param.opcion eq 'registro'}"> </c:when>
                    <c:otherwise>
                        <a href="Empresa?opcion=registro" class="btn btn-primary">Registrarse</a>
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${not empty param.opcion && param.opcion eq 'login'}">
                    </c:when>
                    <c:otherwise>
                        <a href="Empresa?opcion=login" class="btn btn-primary ml-2">Login</a>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</header>


<style>
    .btn-custom {
        background-color: #007bff; /* Color de fondo azul */
        color: #fff; /* Color de texto blanco */
        border: none; /* Sin borde */
    }
    .btn-custom:hover {
        background-color: #0056b3; /* Color de fondo azul oscuro al pasar el mouse */
    }
</style> 



































 








