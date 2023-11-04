package com.controller.nomina;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;

import com.nomina.model.DatosNoCorrectosException;
import com.nomina.model.Empleado;
import com.nomina.model.Nomina;
import com.nomina.model.User;
import com.nominaDAO.NominaDAO;
import com.nominaDAO.authController;

import middleware.validateToken;

/**
 * Servlet implementation class Empresa
 */

/**
 * Servlet que maneja las operaciones relacionadas con la gestión de la nómina
 * de una empresa.
 */

//@WebServlet("/NominaController")
@WebServlet("/Empresa")
public class Empresa extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	/**
	 * Constructor por defecto del servlet.
	 */
	public Empresa() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	/**
	 * Maneja las solicitudes GET realizadas al servlet.
	 * 
	 * @param request  El objeto HttpServletRequest.
	 * @param response El objeto HttpServletResponse.
	 * @throws ServletException Excepción de servlet.
	 * @throws IOException      Excepción de E/S.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String opcion = request.getParameter("opcion");

		if (opcion.equals("crear")) {
			System.out.println("Usted a presionado la opcion crear");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/crear.jsp");
			requestDispatcher.forward(request, response);
		} else if (opcion.equals("listar")) {

			NominaDAO nominaDAO = new NominaDAO();
			List<Empleado> lista = new ArrayList<>();
			try {
				lista = nominaDAO.obtenerEmpleados();
				for (Empleado empleado : lista) {
//					System.out.println(empleado);
				}
				request.setAttribute("lista", lista);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/listar.jsp");
				requestDispatcher.forward(request, response);

			} catch (SQLException | DatosNoCorrectosException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println("Usted a presionado la opcion listar");
		}

		else if (opcion.equals("mostrarSalario")) {

			// Redirige a la página "Mostrar Salario"
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/buscarSalario.jsp");
			requestDispatcher.forward(request, response);
		} else if (opcion.equals("editarEmpleado")) {
			System.out.println("Usted a presionado la opcion editar Empleado");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/editarEmpleado.jsp");
			requestDispatcher.forward(request, response);
		} else if (opcion.equals("registro")) {
			System.out.println("Usted a presionado la opcion registrarse");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/registro.jsp");
			requestDispatcher.forward(request, response);
		}else if (opcion.equals("login")) {
			System.out.println("Usted a presionado la opcion loguearse");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/login.jsp");
			requestDispatcher.forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	/**
	 * Maneja las solicitudes POST realizadas al servlet.
	 * 
	 * @param request  El objeto HttpServletRequest.
	 * @param response El objeto HttpServletResponse.
	 * @throws ServletException Excepción de servlet.
	 * @throws IOException      Excepción de E/S.
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// TODO Auto-generated method stub
		String opcion = request.getParameter("opcion");

		if (opcion.equals("guardar")) {
			NominaDAO nominaDAO = new NominaDAO();

			// Obtener datos del formulario
			String dni = request.getParameter("dni");
			String nombre = request.getParameter("nombre");
			String sexo = request.getParameter("sexo");
			int categoria = Integer.parseInt(request.getParameter("categoria"));
			int anyos = Integer.parseInt(request.getParameter("anyos"));

			try {
				// Crear una instancia de Empleado con los datos del formulario
				Empleado empleado = new Empleado(dni, nombre, sexo, categoria, anyos);

				// Llamar al método guardar de NominaDAO
				boolean exito = nominaDAO.guardar(empleado);

				if (exito) {
					// Éxito: el empleado se guardó correctamente
					// Calcular el sueldo del empleado
					int sueldoNuevo = Nomina.sueldo(empleado);

					// Actualizar el sueldo en la base de datos
					boolean exitoActualizacionSueldo = nominaDAO.actualizarSueldo(dni, sueldoNuevo);

					if (exitoActualizacionSueldo) {
						// Éxito: el sueldo se actualizó correctamente
						request.setAttribute("mensajeExito", "El empleado se guardó exitosamente.");

					} else {
						request.setAttribute("mensajeError", "No se pudo guardar el empleado");
					}
				} else {
					// Error: no se pudo guardar el empleado
					request.setAttribute("mensajeError",
							"No se pudo guardar el empleado empleado porque el DNI ya existe o has puestos campos no validos");
				}

				// Redirigir de vuelta a la página de inicio
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
				requestDispatcher.forward(request, response);
			} catch (DatosNoCorrectosException | SQLException e) {
				// Manejar las excepciones apropiadamente
				e.printStackTrace();
			}
		} else if (opcion.equals("mostrarSalario")) {
			String dni = request.getParameter("dni");

			// Verificar si el DNI proporcionado no está vacío
			if (dni != null && !dni.isEmpty()) {
				try {
					NominaDAO nominaDAO = new NominaDAO();

					// Obtener el salario, nombre y DNI del empleado
					int sueldo = nominaDAO.obtenerSalarioPorDNI(dni);

					// Verificar si se obtuvo un salario válido (mayor que cero)
					if (sueldo > 0) {
						String nombre = nominaDAO.obtenerNombrePorDNI(dni);
						String dniEmpleado = dni;
						// Establecer los atributos en la solicitud para pasarlo a la JSP
						request.setAttribute("sueldo", sueldo);
						request.setAttribute("nombre", nombre);
						request.setAttribute("dni", dniEmpleado);

						// Redirige a la página "Mostrar Salario"
						RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/mostrarSalario.jsp");
						requestDispatcher.forward(request, response);
					} else {
						// El DNI no existe en la base de datos, muestra un mensaje de error
						request.setAttribute("mensajeError", "El DNI del empleado no existe en la base de datos.");
						RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp"); // Reemplaza
																											// "paginaDeError.jsp"
																											// con la
																											// página de
																											// error que
																											// desees
						requestDispatcher.forward(request, response);
					}
				} catch (SQLException | DatosNoCorrectosException e) {
					// Maneja las excepciones apropiadamente
					e.printStackTrace();
				}
				System.out.println("Usted ha presionado la opción mostrar salario");
			}

		} else if (opcion.equals("editarEmpleado")) {
			NominaDAO nominaDAO = new NominaDAO();

			// Obtener datos del formulario
			String dni = request.getParameter("dni");
			String nuevoNombre = request.getParameter("nombre");
			String nuevoSexo = request.getParameter("sexo");
			int nuevaCategoria = Integer.parseInt(request.getParameter("categoria"));
			int nuevosAnyos = Integer.parseInt(request.getParameter("anyos"));

			try {
				// Crear una instancia de Empleado con los datos del formulario
				Empleado empleado = new Empleado(dni, nuevoNombre, nuevoSexo, nuevaCategoria, nuevosAnyos);

				// Llamar al método editar de NominaDAO
				boolean exito = nominaDAO.editar(empleado);

				if (exito) {
					// Éxito: el empleado se actualizó correctamente
					// Actualizar el sueldo del empleado
					int sueldoNuevo = Nomina.sueldo(empleado);

					// Actualizar el sueldo en la base de datos
					boolean exitoActualizacionSueldo = nominaDAO.actualizarSueldo(dni, sueldoNuevo);

					if (exitoActualizacionSueldo) {
						// Éxito: el sueldo se actualizó correctamente
						request.setAttribute("mensajeExito", "El empleado se actualizó exitosamente.");
					} else {
						request.setAttribute("mensajeError", "No se pudo actualizar el sueldo.");
					}
				} else {
					// Error: no se pudo actualizar el empleado
					request.setAttribute("mensajeError",
							"No se pudo actualizar el empleado porque el DNI no existe o has insertado un campo mal");
				}

				// Redirigir de vuelta a la página de inicio
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
				requestDispatcher.forward(request, response);
			} catch (DatosNoCorrectosException | SQLException e) {
				// Manejar las excepciones apropiadamente
				e.printStackTrace();
			}

		} else if (opcion.equals("registrarUsuario")) {

//			response.setContentType("text/html");


			// Obtener los parámetros del formulario
			String name = request.getParameter("name");
			String surnames = request.getParameter("surnames");
			String email = request.getParameter("email");
			String password = request.getParameter("password");

			// Realizar la lógica de registro aquí (puedes llamar a tu controlador o
			// servicio)
			authController authController = new authController();
			User user = new User(name, surnames, email, password);

			// Invoca el método de registro y almacena el resultado en una variable
			boolean exito = false;

			try {
				exito = authController.registrarUsuario(user);

				if (exito) {
					request.setAttribute("mensajeExito", "El usuario se registró exitosamente.");
				} else {
					request.setAttribute("mensajeError", "Error el empleado no se registró porque el email ya existe o pusiste un campo mal.");
				}
				// Redirigir de vuelta a la página de inicio
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
				requestDispatcher.forward(request, response);
			} catch (SQLException e) {
				// Manejar las excepciones apropiadamente
				e.printStackTrace();
			}
	}else if (opcion.equals("loginUsuario")) {
		
//		response.setContentType("text/html");

        // Obtener los parámetros del formulario
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Realizar la lógica de inicio de sesión aquí
        authController authController = new authController();

        try {
            String token = authController.login(email, password);

            if (token != null) {
            	request.getSession().setAttribute("mensajeExito", "Inicio de sesión exitoso.");
                // El inicio de sesión fue exitoso, almacena el token en la sesión o como sea necesario
                request.getSession().setAttribute("token", token);
                response.sendRedirect("index.jsp");
            } else {
                // Las credenciales son inválidas, muestra un mensaje de error
            	request.getSession().setAttribute("mensajeError" , "Email o contraseña inválidos");
                // Redirigir de vuelta al formulario de inicio de sesión
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/login.jsp");
                requestDispatcher.forward(request, response);
            }
        } catch (SQLException e) {
            // Manejar las excepciones apropiadamente
            e.printStackTrace();
            // Puedes configurar un mensaje de error personalizado si lo necesitas
            System.out.println("Estoy en inicio de sesion malisimo");
            request.setAttribute("mensajeError", "Ocurrió un error durante el inicio de sesión.");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/login.jsp");
            requestDispatcher.forward(request, response);
        }
		
	}

}
}
