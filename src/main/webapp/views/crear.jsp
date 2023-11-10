<!-- <div class="container">
		<h2 class="text-center mt-4">Crear Empleado</h2>
		<a href="javascript:history.back()" class="boton-atras">Volver</a>

		<form action="Empresa" method="post">
			<input type="hidden" name="opcion" value="guardar">
			<table class="table">
				<tr>
					<td>DNI:</td>
					<td><input type="text" name="dni" class="form-control"
						placeholder="Ejemplo: 12345678A" pattern="[0-9]{8}[A-Z]{1}"
						title="Formato válido: 12345678A" required maxlength="9">
					</td>
				</tr>
				<tr>
					<td>Nombre:</td>
					<td><input type="text" name="nombre" class="form-control"
						placeholder="Ejemplo: Juan Pérez" required maxlength="70"></td>
				</tr>
				<tr>
					<td>Sexo:</td>
					<td><input type="text" name="sexo" class="form-control"
						placeholder="Ejemplo: M o F" pattern="[MmFf]{1}"
						title="Formato válido: M o F" required maxlength="1"></td>
				</tr>
				<tr>
					<td>Categoría:</td>
					<td><input type="number" name="categoria" class="form-control"
						placeholder="Ejemplo: 2" required min="1" max="10"></td>
				</tr>
				<tr>
					<td>Años:</td>
					<td><input type="number" name="anyos" class="form-control"
						placeholder="Ejemplo: 5" required min="0" max="99"></td>
				</tr>
			</table>
			<button type="submit" class="btn btn-success btn-block">Guardar</button>
		</form>
</div>

	<div id="alert" class="alert alert-success mt-4" role="alert"></div>
	<script type="text/javascript" src="views/javaScript/borraBotones.js"></script> -->
	
<div class="container">
    
    <a href="javascript:history.back()" class="boton-atras">Volver</a>

    <form action="Empresa" method="post">
        <input type="hidden" name="opcion" value="guardar">
        <table class="table">
            <tr><td>DNI:</td><td><input type="text" name="dni" class="form-control" placeholder="Ej: 12345678A" pattern="[0-9]{8}[A-Z]{1}" title="Formato válido: 12345678A" required maxlength="9"></td></tr>
            <tr><td>Nombre:</td><td><input type="text" name="nombre" class="form-control" placeholder="Ej: Juan Pérez" required maxlength="70"></td></tr>
            <tr><td>Sexo:</td><td><input type="text" name="sexo" class="form-control" placeholder="Ej: M o F" pattern="[MmFf]{1}" title="Formato válido: M o F" required maxlength="1"></td></tr>
            <tr><td>Categoría:</td><td><input type="number" name="categoria" class="form-control" placeholder="Ej: 2" required min="1" max="10"></td></tr>
            <tr><td>Años:</td><td><input type="number" name="anyos" class="form-control" placeholder="Ej: 5" required min="0" max="99"></td></tr>
        </table>
        <button type="submit" class="btn btn-success btn-block">Guardar</button>
    </form>
</div>


<div id="alert" class="alert alert-success mt-4" role="alert"></div>
<script type="text/javascript" src="views/javaScript/borraBotones.js"></script>








