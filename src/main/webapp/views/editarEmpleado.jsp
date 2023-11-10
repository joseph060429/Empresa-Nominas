<a href="javascript:history.back()" class="boton-atras">Volver</a>
<form action="Empresa?opcion=editarEmpleado" method="post">
	<input type="hidden" name="opcion" value="guardar">
	<table>
		<tr>
			<td>DNI:</td>
			<td><input type="text" name="dni"
				placeholder="Ejemplo: 12345678A" pattern="[0-9]{8}[A-Za-z]{1}"
				title="Formato v�lido: 12345678A" required maxlength="9"></td>
		</tr>
		<tr>
			<td>Nombre:</td>
			<td><input type="text" name="nombre"
				placeholder="Ejemplo: Juan P�rez" required maxlength="70"></td>
		</tr>
		<tr>
			<td>Sexo:</td>
			<td><input type="text" name="sexo" placeholder="Ejemplo: M o F"
				pattern="[MF]{1}" title="Formato v�lido: M o F" required
				maxlength="1"></td>
		</tr>
		<tr>
			<td>Categor�a:</td>
			<td><input type="number" name="categoria"
				placeholder="Ejemplo: 2" required min="1" max="10"></td>
		</tr>
		<tr>
			<td>A�os:</td>
			<td><input type="number" name="anyos" placeholder="Ejemplo: 5"
				required min="0" max="99"></td>
		</tr>
	</table>
	<input type="submit" value="Editar">
</form>
<script type="text/javascript" src="views/javaScript/borraBotones.js"></script>



