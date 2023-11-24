package es.gestion.nominas.app.web.repositorio;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.gestion.nominas.app.web.modelos.Usuario;

@Repository
public interface UsuariosRepositorio extends JpaRepository <Usuario, UUID>{
	
	
}
