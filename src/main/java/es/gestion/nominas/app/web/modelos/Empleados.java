package es.gestion.nominas.app.web.modelos;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.*;

/**
 * Clase que representa a los empleados en el sistema.
 * Cada empleado tiene un DNI, nombre, sexo, categoría, años de servicio y un indicador de eliminación.
 * 
 * @see jakarta.persistence.Entity
 * @see jakarta.persistence.Table
 * @see jakarta.validation.constraints.Pattern
 * @see jakarta.validation.constraints.NotBlank
 * @see jakarta.validation.constraints.Size
 * @see jakarta.validation.constraints.Min
 * @see jakarta.validation.constraints.Max
 */

@Entity
@Table (name = "Empleados")
public class Empleados {
	
	 /**
     * Identificación única del empleado, compuesta por 8 dígitos seguidos de una letra mayúscula.
     */
	
	@Id
	@Pattern(regexp = "\\d{8}[A-Z]", message = "El DNI debe tener 8 dígitos seguidos de una letra mayúscula")
    private String dni;
	
	 /**
     * Nombre del empleado.
     */
	@NotBlank(message = "El nombre no puede estar en blanco")
    @Size(max = 70, message = "El nombre no puede tener más de 70 caracteres")
	private String nombre;
	
	/**
     * Sexo del empleado, debe ser 'M' o 'F'.
     */
	
	@Pattern(regexp = "[MF]", message = "El sexo debe ser 'M' o 'F'")
	private String sexo;
	
	 /**
     * Categoría del empleado, debe estar en el rango de 1 a 10.
     */
	
	@Min(value = 1, message = "La categoría debe ser al menos 1")
    @Max(value = 10, message = "La categoría no puede ser mayor que 10")
	private Integer categoria;
	
	/**
     * Años de servicio del empleado, debe ser al menos 0.
     */
	
	@Min(value = 0, message = "Los años deben ser al menos 0")
	private Integer anyos;
	
	 /**
     * Indica si el empleado ha sido eliminado o no.
     */
	
	private Boolean deleted = false;
	
	  /**
     * Constructor vacío necesario para JPA.
     */
	
	public Empleados() {
	}

    /**
     * Constructor con todos los parámetros excepto el campo 'deleted'.
     * 
     * @param dni      Identificación única del empleado.
     * @param nombre   Nombre del empleado.
     * @param sexo     Sexo del empleado ('M' o 'F').
     * @param categoria Categoría del empleado (de 1 a 10).
     * @param anyos    Años de servicio del empleado.
     * @throws DatosNoCorrectosException Si la categoría proporcionada no está en el rango de 1 a 10.
     */
	
	public Empleados(String dni, String nombre, String sexo, Integer categoria, Integer anyos) throws DatosNoCorrectosException {
		
		if (categoria >= 1 && categoria <= 10) {
			this.categoria = categoria;
		} else {
			throw new DatosNoCorrectosException("Categoria incorrecta");
		}
		
		this.dni = dni;
		this.nombre = nombre;
		this.sexo = sexo;
		this.anyos = anyos;
		this.deleted = false; // Por defecto, no eliminado = 0
	}

	// Getters y setters

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Integer getCategoria() {
		return categoria;
	}

	public void setCategoria(Integer categoria) {
		this.categoria = categoria;
	}

	public Integer getAnyos() {
		return anyos;
	}

	public void setAnyos(Integer anyos) {
		this.anyos = anyos;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

}
