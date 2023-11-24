//package es.gestion.nominas.app.web.servicio;
//
//import java.util.Arrays;
//
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import java.util.UUID;
//
//import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import es.gestion.nominas.app.web.modelos.Rol;
//import es.gestion.nominas.app.web.modelos.Usuario;
//import es.gestion.nominas.app.web.repositorio.UsuariosRepositorio;
//import es.gestion.nominas.app.web.usuarios.dto.UsuarioRegistroDTO;
//
//@Service
//public class UsuariosServicioImpl implements UsuariosServicio {
//
//	@Autowired
//	private UsuariosRepositorio repostorioUsuario;
//
////	@Autowired
////	private PasswordEncoder passwordEncoder;
//
//	@Override
//	public Usuario guardarUsuario(UsuarioRegistroDTO registroDTO) {
//		Usuario usuario = new Usuario(registroDTO.getNombre(),
//				registroDTO.getApellido(), registroDTO.getEmail(),
//				registroDTO.getPassword(), Arrays.asList(new Rol("ROLE_USER")));
//				return repostorioUsuario.save(usuario);
//		
//		
////		String passwordHasheada = passwordEncoder.encode(registroDTO.getPassword());
////
////	    Usuario usuario = new Usuario(
////	        registroDTO.getNombre(),
////	        registroDTO.getApellido(),
////	        registroDTO.getEmail(),
////	        passwordHasheada,  // Guarda la contraseña hasheada
////	        Arrays.asList(new Rol("ROLE_USER"))
////	    );
////
////	    return repostorioUsuario.save(usuario);
//	}
//
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		// TODO Auto-generated method stub
//		return null;
//	}
//}
