package com.nomina.model;

public class Persona {
	public String nombre;
	public String dni;
	public String sexo;

	/**
	 * Crea un nuevo objeto Persona con nombre, DNI y sexo.
	 *
	 * @param nombre El nombre de la persona.
	 * @param dni    El número de identificación (DNI) de la persona.
	 * @param sexo   El sexo de la persona.
	 */
	public Persona(String nombre, String dni, String sexo) {
		super();
		this.nombre = nombre;
		this.dni = dni;
		this.sexo = sexo;

	}
	public Persona() {
		
	}

	/**
	 * Creo un nuevo objeto Persona con nombre y sexo.
	 *
	 * @param nombre El nombre de la persona.
	 * @param sexo   El sexo de la persona.
	 */

	public Persona(String nombre, String sexo) {
		super();
		this.nombre = nombre;
		this.sexo = sexo;
	}

	public void setDni(String dni) {
		this.dni = dni;

	}

	public void imprime() {
		System.out.println("El nombre es " + nombre);
		System.out.println("El Dni es  " + dni);
		System.out.println("El sexo es " + sexo);
		;
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
	public String getDni() {
		return dni;
	}
	public int getAnyos() {
		return getAnyos();
	}
	
	public int getCategoria() {
		return getCategoria();
	}
	
	
	
	
}
