package es.gestion.nominas.app.web.repositorio;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import es.gestion.nominas.app.web.modelos.Nomina;

/**
 * Interfaz que define las operaciones de acceso a datos para la entidad Nomina.
 * Extiende la interfaz JpaRepository de Spring Data JPA.
 * 
 * @see org.springframework.data.jpa.repository.JpaRepository
 * @see java.util.UUID
 * @see es.gestion.nominas.app.web.modelos.Nomina
 */

public interface NominasRepositorio extends JpaRepository<Nomina, UUID> {

	 /**
     * Busca y devuelve una nómina por el DNI de su empleado asociado.
     * 
     * @param dni El DNI del empleado asociado a la nómina.
     * @return La nómina encontrada, o null si no se encuentra ninguna.
     */
	
	Nomina findByEmpleado_Dni(String dni);
}
