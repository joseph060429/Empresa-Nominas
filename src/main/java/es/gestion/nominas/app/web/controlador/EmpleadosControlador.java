package es.gestion.nominas.app.web.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import es.gestion.nominas.app.web.servicio.EmpleadosServicio;
//import es.gestion.nominas.app.web.servicio.UsuariosServicio;
import es.gestion.nominas.app.web.usuarios.dto.*;
import es.gestion.nominas.app.web.modelos.*;

/**
 * Controlador para la gestión de empleados en la aplicación web.
 * Maneja las solicitudes relacionadas con las operaciones CRUD (Crear, Leer, Actualizar, Eliminar) de empleados.
 * 
 * @see org.springframework.stereotype.Controller
 * @see org.springframework.beans.factory.annotation.Autowired
 * @see org.springframework.ui.Model
 * @see org.springframework.web.bind.annotation.GetMapping
 * @see org.springframework.web.bind.annotation.PostMapping
 * @see org.springframework.web.bind.annotation.ModelAttribute
 * @see org.springframework.web.bind.annotation.PathVariable
 * @see org.springframework.web.servlet.mvc.support.RedirectAttributes
 * @see es.gestion.nominas.app.web.servicio.EmpleadosServicio
 * @see es.gestion.nominas.app.web.usuarios.dto.EmpleadosDto
 * @see es.gestion.nominas.app.web.modelos.Empleados
 */

@Controller
public class EmpleadosControlador {
	
	 /**
     * Servicio para la gestión de empleados.
     */
	
	@Autowired
	private EmpleadosServicio servicioEmpleado;
	
	@Autowired
//	private UsuariosServicio servicioUsuario;
	
	 /**
     * Muestra la página del menú inicial de la aplicación.
     *
     * @return La vista del menú inicial.
     */
	
	//Mi ruta del menu inicial
	@GetMapping({ "/", "/menuInicial" }) // Lo tengo puesto asi para que si no hay la ruta / me coja menuInicial
	public String mostrarMenuInicial() {
		
		return "/menuInicial";
	}
	
	 /**
     * Muestra la lista de empleados.
     *
     * @param modelo El modelo utilizado para pasar datos a la vista.
     * @return La vista de la lista de empleados.
     */
	
	//Mi ruta del listar
	@GetMapping({ "/empleados/listar" })
	public String listarEmpleados(Model modelo) {
		modelo.addAttribute("listarEmpleados", servicioEmpleado.listarTodosLosEmpleados());
		return "listarEmpleados";
	}
	
	 /**
     * Muestra el formulario para registrar un nuevo empleado.
     *
     * @param modelo El modelo utilizado para pasar datos a la vista.
     * @return La vista del formulario de creación de empleados.
     */
	
	//Mi ruta del crear
	@GetMapping({ "/empleados/crear" })
	public String mostrarFormularioRegistrarEmpleado(Model modelo) {
	    Empleados empleado = new Empleados();
	    modelo.addAttribute("empleado", empleado);
	    return "crearEmpleado";
	}
	
	/**
     * Guarda un nuevo empleado en la base de datos.
     *
     * @param empleado El empleado a ser guardado.
     * @param modelo   El modelo utilizado para pasar datos a la vista.
     * @return La vista del formulario de creación de empleados o la vista del menú inicial en caso de éxito.
     */
	
	@PostMapping({ "/empleados" })
	public String guardarEmpleado(@ModelAttribute("empleado") Empleados empleado, Model modelo) {
	    // Verificar si el empleado ya existe en la base de datos
	    if (servicioEmpleado.existeEmpleadoConDni(empleado.getDni())) {
	        modelo.addAttribute("error", "El empleado con DNI " + empleado.getDni() + " ya existe en la base de datos");
	        return "crearEmpleado";
	    }

	    // Si el empleado no existe, guardarlo en la base de datos
	    servicioEmpleado.guardarEmpleado(empleado);
	    // Agregar mensaje de éxito al modelo
	    modelo.addAttribute("exito", "El empleado ha sido dado de alta exitosamente");
	    //return "redirect:/menuInicial";
	    return "crearEmpleado";
	}
	
	/**
     * Muestra el formulario para editar la información de un empleado.
     *
     * @param dni     El DNI del empleado a ser editado.
     * @param modelo  El modelo utilizado para pasar datos a la vista.
     * @return La vista del formulario de edición de empleados o la vista del menú inicial si el empleado no existe.
     */
	
	//Mi ruta del editar
	@GetMapping({ "/empleados/editar/{dni}" })
	public String mostrarFormularioDeEditar(@PathVariable String dni, Model modelo) {
	    boolean empleadoExistente = servicioEmpleado.existeEmpleadoConDni(dni);
	    if (empleadoExistente) {
	        Empleados empleado = servicioEmpleado.obtenerEmpleadoConDni(dni);
	        modelo.addAttribute("empleado", empleado);
	        return "editarEmpleado";
	    } else {
	        modelo.addAttribute("error", "El empleado con DNI " + dni + " no existe.");
	        return "redirect:/menuInicial";
	    }
	}
	
	   /**
     * Actualiza la información de un empleado en la base de datos.
     *
     * @param dni                 El DNI del empleado a ser actualizado.
     * @param empleado           El empleado con la información actualizada.
     * @param modelo             El modelo utilizado para pasar datos a la vista.
     * @param redirectAttributes Atributos de redirección para mensajes flash.
     * @return La redirección a la lista de empleados.
     */
	
	@PostMapping("/empleados/{dni}")
	public String actualizarEmpleado(@PathVariable String dni, @ModelAttribute("empleado") Empleados empleado,
	        Model modelo, RedirectAttributes redirectAttributes) {
	    boolean empleadoExistente = servicioEmpleado.existeEmpleadoConDni(dni);

	    if (empleadoExistente) {
	        // Actualizo el empleado con el nuevo contenido
	        servicioEmpleado.actualizarEmpleado(empleado);
	        redirectAttributes.addFlashAttribute("exito", "El empleado con DNI " + dni + " ha sido editado exitosamente.");
	    } else {
	        // Mensaje de error
	        redirectAttributes.addFlashAttribute("error", "El empleado con DNI " + dni + " no existe.");
	        return "redirect:/empleados/listar"; // Si hay un error que no lo creo, redirijo directamente a la lista sin pasar por la vista de editar
	    }

	    return "redirect:/empleados/listar";
	}
	
	 /**
     * Elimina un empleado de la base de datos.
     *
     * @param dni                 El DNI del empleado a ser eliminado.
     * @param redirectAttributes Atributos de redirección para mensajes flash.
     * @return La redirección a la lista de empleados.
     */
	
	//Mi ruta para eliminar
	@GetMapping("/empleados/{dni}")
	public String eliminarEmpleado(@PathVariable String dni, RedirectAttributes redirectAttributes) {
		servicioEmpleado.eliminarEmpleado(dni);
		redirectAttributes.addFlashAttribute("exito", "El empleado con DNI " + dni + " ha sido eliminado exitosamente.");
		return "redirect:/empleados/listar";

	}
	
	/**
     * Muestra el formulario para buscar los salarios de un empleado.
     *
     * @return La vista del formulario de búsqueda de salarios.
     */
	
	//Ruta para mostrar salarios
	@GetMapping("/buscar/salarios")
	public String mostrarFormularioBuscarSalarios() {
		return "buscarSalarios";
	}
	
	/**
     * Busca y muestra el salario de un empleado.
     *
     * @param dni    El DNI del empleado para buscar el salario.
     * @param modelo El modelo utilizado para pasar datos a la vista.
     * @return La vista del formulario de búsqueda de salarios con los resultados.
     */
	
	//Puse esto para que me coja el sueldo de la tabla nominas relacionado por el dni del empleado
	@PostMapping("/buscar/salarios")
	public String buscarSalario(@RequestParam String dni, Model modelo) {
	    Empleados empleado = servicioEmpleado.obtenerEmpleadoConDni(dni);

	    if (empleado != null) {
	        List<Nomina> nominas = servicioEmpleado.obtenerNominasDeEmpleado(empleado);
	        if (!nominas.isEmpty()) {
	            modelo.addAttribute("empleado", empleado);
	            modelo.addAttribute("sueldo", nominas.get(0).getSueldo());
	        } else {
	            modelo.addAttribute("error", "No hay nóminas para el empleado con DNI " + dni);
	        }
	    } else {
	        modelo.addAttribute("error", "No se encontró ningún empleado con DNI " + dni);
	    }

	    return "buscarSalarios";
	}


	
	
//	Puse esto para los usuarios
//	@ModelAttribute("usuario")
//	public UsuarioRegistroDTO retornarNuevoUsuarioRegistroDTO() {
//		return new UsuarioRegistroDTO();
//	}
//	
//	
//	@GetMapping({ "/" , "/registro" })
////	@GetMapping( "/registro" )
//	public String mostrarFormularioRegistro() {
//		return "registroUsuario";
//	}
//	
//	@PostMapping("/registro")
//	public String registrarCuentaUsuario(@ModelAttribute("usuario") UsuarioRegistroDTO registroDTO) {
//		servicioUsuario.guardarUsuario(registroDTO);
//		return "redirect:/menuInicial";
//	}
	
	
	
	
}
