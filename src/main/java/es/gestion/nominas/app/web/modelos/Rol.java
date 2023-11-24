package es.gestion.nominas.app.web.modelos;

import java.util.UUID;

import jakarta.persistence.*;

@Entity
@Table(name = "rol")
public class Rol {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	private String nombre;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	// Constructor predeterminado sin argumentos
	public Rol() {
	}

	// Constructor con solo el nombre
	public Rol(String nombre) {
		this.nombre = nombre;
	}
}
