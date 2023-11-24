package es.gestion.nominas.app.web.modelos;

import jakarta.persistence.*;
import java.util.UUID;

/**
 * Clase que representa la nómina de un empleado en el sistema. Cada nómina
 * tiene un identificador único, una referencia al empleado asociado y el sueldo
 * correspondiente.
 * 
 * @see jakarta.persistence.Entity
 * @see jakarta.persistence.Table
 * @see jakarta.persistence.Id
 * @see jakarta.persistence.GeneratedValue
 * @see jakarta.persistence.GenerationType
 * @see jakarta.persistence.ManyToOne
 * @see jakarta.persistence.JoinColumn
 * @see es.gestion.nominas.app.web.modelos.Empleados
 */

@Entity
@Table(name = "Nominas")
public class Nomina {

	/**
	 * Identificador único de la nómina.
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	/**
	 * Empleado asociado a la nómina.
	 */

	@ManyToOne
	@JoinColumn(name = "dni_empleado", referencedColumnName = "dni")
	private Empleados empleado;

	/**
	 * Sueldo correspondiente a la nómina.
	 */

	private int sueldo;

	/**
	 * Constructor vacío necesario para JPA.
	 */

	public Nomina() {
	}

	/**
	 * Constructor que asigna el empleado y calcula el sueldo automáticamente.
	 * 
	 * @param empleado El empleado asociado a la nómina.
	 */
	public Nomina(Empleados empleado) {
		this.empleado = empleado;
		this.sueldo = calcularSueldo(empleado);
	}

	/**
	 * Obtiene el identificador único de la nómina.
	 * 
	 * @return El identificador único de la nómina.
	 */

	public UUID getId() {
		return id;
	}

	/**
	 * Establece el identificador único de la nómina.
	 * 
	 * @param id El identificador único a ser establecido.
	 */

	public void setId(UUID id) {
		this.id = id;
	}

	/**
	 * Obtiene el empleado asociado a la nómina.
	 * 
	 * @return El empleado asociado a la nómina.
	 */

	public Empleados getEmpleado() {
		return empleado;
	}

	/**
	 * Establece el empleado asociado a la nómina y recalcula el sueldo
	 * automáticamente.
	 * 
	 * @param empleado El empleado a ser establecido.
	 */
	public void setEmpleado(Empleados empleado) {
		this.empleado = empleado;
		this.sueldo = calcularSueldo(empleado);
	}

	/**
	 * Obtiene el sueldo correspondiente a la nómina.
	 * 
	 * @return El sueldo correspondiente a la nómina.
	 */

	public int getSueldo() {
		return sueldo;
	}

	/**
	 * Establece el sueldo correspondiente a la nómina.
	 * 
	 * @param sueldo El sueldo a ser establecido.
	 */

	public void setSueldo(int sueldo) {
		this.sueldo = sueldo;
	}

	/**
	 * Método para calcular el sueldo basado en la categoría y años del empleado.
	 * 
	 * @param e El empleado del cual se calculará el sueldo.
	 * @return El sueldo calculado.
	 */

	public int calcularSueldo(Empleados e) {
		// Hice esto para calcular el sueldo basado en la categoría y años del empleado
		int[] SUELDO_BASE = { 50000, 70000, 90000, 110000, 130000, 150000, 170000, 190000, 210000, 230000 };
		return SUELDO_BASE[e.getCategoria() - 1] + 5000 * e.getAnyos();
	}

}
