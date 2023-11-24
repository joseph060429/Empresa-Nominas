package es.gestion.nominas.app.web.servicio;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import es.gestion.nominas.app.web.modelos.Empleados;
import es.gestion.nominas.app.web.modelos.Nomina;
import es.gestion.nominas.app.web.repositorio.EmpleadosRepositorio;
import es.gestion.nominas.app.web.repositorio.NominasRepositorio;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

/**
 * Implementación de la interfaz EmpleadosServicio que proporciona servicios
 * relacionados con la gestión de empleados en la aplicación. Utiliza
 * repositorios de empleados y nóminas para acceder y manipular datos en la base
 * de datos. Además, utiliza la validación de Hibernate Validator para
 * garantizar la integridad de los datos antes de realizar operaciones en la
 * base de datos.
 * 
 * @see es.gestion.nominas.app.web.modelos.Empleados
 * @see es.gestion.nominas.app.web.modelos.Nomina
 * @see es.gestion.nominas.app.web.repositorio.EmpleadosRepositorio
 * @see es.gestion.nominas.app.web.repositorio.NominasRepositorio
 * @see jakarta.validation.Validator
 * @see org.springframework.stereotype.Service
 * @see org.springframework.transaction.annotation.Transactional
 */

@Service
public class EmpleadosServicioImpl implements EmpleadosServicio {

	@Autowired
	private EmpleadosRepositorio repositorioEmpleado;

	@Autowired
	private NominasRepositorio repositorioNominas;

	@Autowired
	private Validator validator;

	/**
	 * {@inheritDoc} Obtiene una lista de todos los empleados en el sistema.
	 *
	 * @return Lista de empleados.
	 */

	@Override
	public List<Empleados> listarTodosLosEmpleados() {
		return repositorioEmpleado.findAll();
	}

	/**
	 * {@inheritDoc} Guarda un nuevo empleado en el sistema.
	 *
	 * @param empleado El empleado a ser guardado.
	 * @return El empleado guardado.
	 * @throws RuntimeException Si el empleado ya está registrado o si hay
	 *                          violaciones de validación.
	 */

	@Override
	public Empleados guardarEmpleado(Empleados empleado) {

		if (existeEmpleadoConDni(empleado.getDni())) {
			throw new RuntimeException("¡Error! El empleado con DNI " + empleado.getDni() + " ya está registrado.");
		}

		// Valido usando Hibernate Validator
		Set<ConstraintViolation<Empleados>> violations = validator.validate(empleado);

		if (violations.isEmpty()) {
			// No hay violaciones, guardo el empleado y calculo el sueldo
			Empleados empleadoGuardado = repositorioEmpleado.save(empleado);

			// Creo una instancia de Nomina y guardo el sueldo en la tabla de Nominas
			Nomina nomina = new Nomina(empleadoGuardado);
			repositorioNominas.save(nomina);
			return empleadoGuardado;
		} else {
			// Hay violaciones, suelto una exception
			throw new RuntimeException("Error de validación: " + violations.toString());
		}
	}

	/**
	 * {@inheritDoc} Verifica la existencia de un empleado por su DNI.
	 *
	 * @param dni El DNI del empleado a verificar.
	 * @return true si existe un empleado con el DNI proporcionado, false en caso
	 *         contrario.
	 */

	@Override
	// Compurebo que exista el empleado por ese DNI
	public boolean existeEmpleadoConDni(String dni) {
		// Utilizo el método existsByDni proporcionado por el repositorioEmpleado para
		// verificar
		// la existencia del DNI.
		return repositorioEmpleado.existsByDni(dni);
	}

	/**
	 * {@inheritDoc} Obtiene un empleado por su DNI.
	 *
	 * @param dni El DNI del empleado a obtener.
	 * @return El empleado encontrado o null si no se encuentra.
	 */

	@Override
	// Este metodo solo busca los empleados, lo tuve que poner para que me deje
	// buscar por dni
	public Empleados obtenerEmpleadoConDni(String dni) {
		Optional<Empleados> optionalEmpleado = repositorioEmpleado.findByDni(dni);
		// Si el empleado existe, devuelvo el objeto Empleados, de lo contrario,
		// devuelvo null
		return optionalEmpleado.orElse(null);
	}

	/**
	 * {@inheritDoc} Actualiza la información de un empleado en el sistema.
	 *
	 * @param empleado El empleado con la información actualizada.
	 * @return El empleado actualizado.
	 * @throws RuntimeException Si el empleado no está registrado, si hay
	 *                          violaciones de validación o si no se encuentra la
	 *                          nómina asociada al empleado.
	 */

	@Override
	public Empleados actualizarEmpleado(Empleados empleado) {
		// Verifico si el empleado existe en la base de datos
		if (!repositorioEmpleado.existsByDni(empleado.getDni())) {
			throw new RuntimeException("¡Error! El empleado con DNI " + empleado.getDni() + " no está registrado.");
		}
		// Valido usando Hibernate Validator
		Set<ConstraintViolation<Empleados>> violations = validator.validate(empleado);

		if (violations.isEmpty()) {
			// No hay violaciones, actualizo el sueldo y luego actualizar el empleado
			Nomina nomina = repositorioNominas.findByEmpleado_Dni(empleado.getDni());

			if (nomina != null) {
				// Si la Nomina existe, actualizo el sueldo
				nomina.setSueldo(nomina.calcularSueldo(empleado));
				repositorioNominas.save(nomina);
			} else {
				// Si la Nomina no existe, hago una exception, preguntar si esto sobra porque mi
				// programa solo deja actualizar dnis que existan ya
				// que lo tengo en la vista asi
				throw new RuntimeException(
						"¡Error! La nómina para el empleado con DNI " + empleado.getDni() + " no está registrada.");
			}
			// Actualizo el empleado en el repositorio
			return repositorioEmpleado.save(empleado);
		} else {
			// Hay violaciones, lanzo una exeption
			throw new RuntimeException("Error de validación: " + violations.toString());
		}
	}

	/**
	 * {@inheritDoc} Elimina un empleado del sistema por su DNI.
	 *
	 * @param dni El DNI del empleado a ser eliminado.
	 * @throws RuntimeException Si el empleado no está registrado o si no se
	 *                          encuentra la nómina asociada al empleado.
	 */

	@Override
	@Transactional // Puse transactional para que me deje eliminar
	public void eliminarEmpleado(String dni) {
		// Verifico que el empleado exista en la base de datos
		Optional<Empleados> empleado = repositorioEmpleado.findByDni(dni);
		if (empleado == null) {
			throw new RuntimeException("¡Error! El empleado con DNI " + dni + " no está registrado.");
		}
		// Elimino la nómina asociada si existe
		Nomina nomina = repositorioNominas.findByEmpleado_Dni(dni);
		if (nomina != null) {
			repositorioNominas.delete(nomina);
		}
		// Elimino el empleado
		repositorioEmpleado.deleteByDni(dni);
	}

	/**
	 * {@inheritDoc} Obtiene las nóminas asociadas a un empleado.
	 *
	 * @param empleado El empleado del cual se desean obtener las nóminas.
	 * @return Lista de nóminas asociadas al empleado.
	 */

	@Override
	public List<Nomina> obtenerNominasDeEmpleado(Empleados empleado) {
		// Verifico si el empleado es nulo
		if (empleado != null) {
			// Obtengo la nómina asociada al empleado desde el repositorio de nóminas
			Nomina nomina = repositorioNominas.findByEmpleado_Dni(empleado.getDni());

			// Retorna la lista de nóminas asociadas al empleado
			return Collections.singletonList(nomina);
		} else {
			// Si no hay empleado , retorno una lista vacía
			return Collections.emptyList();
		}
	}
}
