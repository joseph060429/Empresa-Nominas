package es.gestion.nominas.app.web.modelos;

/**
 * Excepción personalizada para indicar que los datos proporcionados no son correctos.
 * Puede ser lanzada en situaciones en las que se esperan ciertos datos válidos, pero se reciben datos incorrectos.
 * 
 * @see java.lang.Exception
 */

public class DatosNoCorrectosException extends Exception{
	
	 /**
     * Constructor que acepta un mensaje descriptivo para la excepción.
     *
     * @param mensaje Mensaje que describe la naturaleza de la excepción.
     */
	
	private static final long serialVersionUID = 1L;

	public DatosNoCorrectosException(String mensaje) {
		super(mensaje);

	}
}
