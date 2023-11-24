package es.gestion.nominas.app.web.repositorio;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import es.gestion.nominas.app.web.modelos.Empleados;

/**
 * Interfaz que define las operaciones de acceso a datos para la entidad
 * Empleados. Extiende la interfaz JpaRepository de Spring Data JPA.
 * 
 * @see org.springframework.data.jpa.repository.JpaRepository
 * @see org.springframework.stereotype.Repository
 * @see java.util.Optional
 * @see es.gestion.nominas.app.web.modelos.Empleados
 */

@Repository
public interface EmpleadosRepositorio extends JpaRepository<Empleados, String> {

	/**
	 * Verifica la existencia de un empleado en la base de datos por su DNI.
	 * 
	 * @param dni El DNI del empleado a verificar.
	 * @return true si existe un empleado con el DNI proporcionado, false en caso
	 *         contrario.
	 */

	public boolean existsByDni(String dni);

	/**
	 * Elimina un empleado de la base de datos por su DNI.
	 * 
	 * @param dni El DNI del empleado a ser eliminado.
	 */

	public void deleteByDni(String dni);

	/**
	 * Busca un empleado por su DNI.
	 * 
	 * @param dni El DNI del empleado a buscar.
	 * @return Un Optional que contiene al empleado si se encuentra, o empty si no
	 *         se encuentra.
	 */

	Optional<Empleados> findByDni(String dni);

}
