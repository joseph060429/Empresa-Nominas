package es.gestion.nominas.app.web.modelos;

import java.util.Collection;
import java.util.UUID;
import jakarta.validation.constraints.Email;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "usuarios", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@Column(name = "nombre")
	@NotBlank(message = "El nombre no puede estar en blanco")
	@Size(max = 70, message = "El nombre no puede tener más de 70 caracteres")
	private String nombre;

	@Column(name = "apellido")
	@NotBlank(message = "El apellido no puede estar en blanco")
	@Size(max = 70, message = "El apellido no puede tener más de 70 caracteres")
	private String apellido;
	
	@Email(message = "El formato del correo electrónico no es válido")
	private String email;
	
	
	private String password;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "usuario_roles", joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "id"), 
	inverseJoinColumns = @JoinColumn(name = "rol_id", referencedColumnName = "id")
	)
	private Collection<Rol> roles;

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

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Collection<Rol> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Rol> roles) {
		this.roles = roles;
	}

	public Usuario(UUID id,
			@NotBlank(message = "El nombre no puede estar en blanco") @Size(max = 70, message = "El nombre no puede tener más de 70 caracteres") String nombre,
			@NotBlank(message = "El apellido no puede estar en blanco") @Size(max = 70, message = "El apellido no puede tener más de 70 caracteres") String apellido,
			String email, String password, Collection<Rol> roles) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.password = password;
		this.roles = roles;
	}
	
	//He puesto esto:
	public Usuario(String nombre, String apellido, String email, String password, Collection<Rol> roles) {
	    this.nombre = nombre;
	    this.apellido = apellido;
	    this.email = email;
	    this.password = password;
	    this.roles = roles;
	}


	public Usuario() {
		super();
	}

}
