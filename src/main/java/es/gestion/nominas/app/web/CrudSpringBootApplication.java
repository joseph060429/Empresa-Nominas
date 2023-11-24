package es.gestion.nominas.app.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;

//import es.gestion.nominas.app.web.modelos.Empleados;
import es.gestion.nominas.app.web.repositorio.EmpleadosRepositorio;

/**
 * Clase principal para la aplicación Spring Boot CRUD.
 * Esta aplicación sirve como una plantilla básica para un proyecto Spring Boot con operaciones CRUD (Crear, Leer, Actualizar, Eliminar).
 * Incluye componentes para gestionar datos de empleados utilizando un repositorio.
 * La aplicación está configurada mediante las anotaciones de Spring Boot.
 * 
 * @see org.springframework.boot.autoconfigure.SpringBootApplication
 * @see org.springframework.context.annotation.ComponentScan
 * @see org.springframework.boot.CommandLineRunner
 * @see org.springframework.beans.factory.annotation.Autowired
 * @see es.gestion.nominas.app.web.repositorio.EmpleadosRepositorio
 */
@SpringBootApplication
@ComponentScan(basePackages = "es.gestion.nominas.app.web")
public class CrudSpringBootApplication implements CommandLineRunner {
	
	/**
     * Método principal que inicia la aplicación Spring Boot.
     * 
     * @param args Argumentos de línea de comandos pasados a la aplicación.
     */

	public static void main(String[] args) {
		SpringApplication.run(CrudSpringBootApplication.class, args);
	}
	
	/**
     * Este método no tiene ninguna implementación específica en este momento.
     * Se utiliza para cumplir con la interfaz CommandLineRunner.
     * 
     * @param args Argumentos de línea de comandos pasados a la aplicación.
     * @throws Exception Si ocurre alguna excepción durante la ejecución.
     */
	
	@Override
	public void run(String... args) throws Exception {

	}
	
//	 @Bean
//	  public PasswordEncoder passwordEncoder() {
//	     return new BCryptPasswordEncoder();
//	    }

}
