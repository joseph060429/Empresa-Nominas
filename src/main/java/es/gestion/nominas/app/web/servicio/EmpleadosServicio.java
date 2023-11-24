package es.gestion.nominas.app.web.servicio;

import java.util.List;
import es.gestion.nominas.app.web.modelos.*;

/**
 * Interfaz que define los servicios relacionados con la gestión de empleados en
 * la aplicación. Proporciona métodos para listar, guardar, verificar la
 * existencia, obtener, actualizar y eliminar empleados, así como para obtener
 * las nóminas asociadas a un empleado.
 * 
 * @see es.gestion.nominas.app.web.modelos.Empleados
 * @see es.gestion.nominas.app.web.modelos.Nomina
 */

public interface EmpleadosServicio {

	/**
	 * Obtiene una lista de todos los empleados en el sistema.
	 * 
	 * @return Lista de empleados.
	 */

	public List<Empleados> listarTodosLosEmpleados();

	/**
	 * Guarda un nuevo empleado en el sistema.
	 * 
	 * @param empleado El empleado a ser guardado.
	 * @return El empleado guardado.
	 */

	public Empleados guardarEmpleado(Empleados empleado);

	/**
	 * Verifica la existencia de un empleado por su DNI.
	 * 
	 * @param dni El DNI del empleado a verificar.
	 * @return true si existe un empleado con el DNI proporcionado, false en caso
	 *         contrario.
	 */

	boolean existeEmpleadoConDni(String dni);

	/**
	 * Obtiene un empleado por su DNI.
	 * 
	 * @param dni El DNI del empleado a obtener.
	 * @return El empleado encontrado o null si no se encuentra.
	 */

	public Empleados obtenerEmpleadoConDni(String dni);

	/**
	 * Actualiza la información de un empleado en el sistema.
	 * 
	 * @param empleado El empleado con la información actualizada.
	 * @return El empleado actualizado.
	 */

	public Empleados actualizarEmpleado(Empleados empleado);

	/**
	 * Elimina un empleado del sistema por su DNI.
	 * 
	 * @param dni El DNI del empleado a ser eliminado.
	 */

	public void eliminarEmpleado(String dni);

	/**
	 * Obtiene las nóminas asociadas a un empleado.
	 * 
	 * @param empleado El empleado del cual se desean obtener las nóminas.
	 * @return Lista de nóminas asociadas al empleado.
	 */

	List<Nomina> obtenerNominasDeEmpleado(Empleados empleado);

}
