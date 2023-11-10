<h2 class="text-center mt-4">Buscar Salario del Empleado</h2>
<a href="javascript:history.back()" class="boton-atras">Volver</a>
<form action="Empresa?opcion=mostrarSalario" method="post">
	<label for="dni">DNI del Empleado:</label> <input type="text"
		name="dni" class="form-control" placeholder="Ejemplo: 12345678A"
		pattern="[0-9]{8}[A-Z]{1}" title="Formato válido: 12345678A" required
		maxlength="9"> <input type="submit" value="Buscar Salario">
</form>
<script type="text/javascript" src="views/javaScript/borraBotones.js"></script>




