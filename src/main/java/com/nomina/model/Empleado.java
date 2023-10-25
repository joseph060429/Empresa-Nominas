package com.nomina.model;

import com.nomina.model.DatosNoCorrectosException;
import com.nomina.model.Persona;
public class Empleado extends Persona{
	
	private int categoria = 1;
	public int anyos = 0;

	/**
	 * Crea un nuevo objeto Empleado.
	 *
	 * @param nombre    El nombre del empleado.
	 * @param dni       El DNI del empleado.
	 * @param sexo      El sexo del empleado.
	 * @param categoria La categoría laboral del empleado (de 1 a 10).
	 * @param anios     Los años de servicio del empleado.
	 * @throws DatosNoCorrectosException Si los datos proporcionados no son válidos.
	 */

	public Empleado(String dni,String nombre, String sexo, int categoria, int anyos) throws DatosNoCorrectosException {

		super(nombre, dni, sexo);
		if (categoria >= 1 && categoria <= 10) {
			this.categoria = categoria;
			this.anyos = anyos;

		} else {
			throw new DatosNoCorrectosException("DATOS No correctos");
		}
	}

	/**
	 * Crea un nuevo objeto Empleado.
	 *
	 * @param nombre    El nombre del empleado.
	 * @param dni       El DNI del empleado.
	 * @param sexo      El sexo del empleado.
	 * @param categoria La categoría laboral del empleado (de 1 a 10).
	 * @param anios     Los años de servicio del empleado.
	 * @throws DatosNoCorrectosException Si los datos proporcionados no son válidos.
	 */

	public Empleado(String dni, String nombre, String sexo) throws DatosNoCorrectosException {
		super(dni, nombre, sexo);

	}
	 public Empleado() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Establezco la categoría laboral del empleado.
	 *
	 * @param categoria La nueva categoría laboral del empleado (de 1 a 10).
	 * @throws DatosNoCorrectosException Si la categoría proporcionada no es válida.
	 */

	public void setCategoria(int categoria) throws DatosNoCorrectosException {
		if (categoria >= 1 && categoria <= 10) {
			this.categoria = categoria;
		} else {
			throw new DatosNoCorrectosException("Categoria incorrecta");
		}

	}

	public void incrAnyo() {
		anyos ++;
	}

	/**
	 * Imprime información sobre el empleado, incluyendo sus datos personales,
	 * categoría laboral y años.
	 */
	
	public void imprime(Persona p) {
		p.imprime();
		System.out.println(" Categoria: " + categoria + " Años: " + anyos

		);

	}

	public String getDni() {
		return dni;
	}

	public String getNombre() {
		
		return nombre;
	}

	public String getSexo() {
	
		return sexo;
	}

	public int getAnyos() {
		return anyos;
	}
	public int getCategoria() {
		return categoria;
	}
	
	@Override
	public String toString() {
	    return "DNI del empleado: " + dni + "\nNombre del empleado: " + nombre + "\nSexo del empleado: " + sexo + "\nCategoría del empleado: " + categoria + "\nAños en la empresa: " + anyos;
	}

	

}
