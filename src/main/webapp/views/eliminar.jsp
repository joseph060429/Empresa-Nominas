<a href="javascript:history.back()" class="boton-atras">Volver</a>
<form action="Empresa?opcion=eliminarEmpleado" method="post">
	<input type="hidden" name="opcion" value="guardar">
	<table>
		<tr>
			<td>DNI:</td>
			<td><input type="text" name="dni"
				placeholder="Ejemplo: 12345678A" pattern="[0-9]{8}[A-Za-z]{1}"
				title="Formato válido: 12345678A" required maxlength="9"></td>
		</tr>
	</table>
	<input type="submit" value="Eliminar">
</form>
<script type="text/javascript" src="views/javaScript/borraBotones.js"></script>
