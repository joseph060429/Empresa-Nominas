package com.nomina.model;

public class DatosNoCorrectosException extends Exception {
	/**
	 * Crea una nueva instancia de DatosNoCorrectosException con un mensaje de
	 * error.
	 *
	 * @param mensaje El mensaje de error que describe la excepción.
	 */
	public DatosNoCorrectosException(String mensaje) {
		super(mensaje);

	}
}