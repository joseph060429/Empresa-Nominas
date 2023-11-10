package com.controller.nomina;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.text.AbstractDocument.Content;

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
		String content = "";

		if (opcion.equals("crear")) {
			System.out.println("Usted a presionado la opción crear");

			request.setAttribute("content", "views/crear.jsp");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
			requestDispatcher.forward(request, response);
			return;

		}

		else if (opcion.equals("listar")) {

			NominaDAO nominaDAO = new NominaDAO();
			List<Empleado> lista = new ArrayList<>();
			try {
				lista = nominaDAO.obtenerEmpleados();
				request.setAttribute("lista", lista);

				// Establece el atributo "content" con la ruta de listar.jsp
				request.setAttribute("content", "views/listar.jsp");

				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
				requestDispatcher.forward(request, response);
			} catch (SQLException | DatosNoCorrectosException e) {
				// Maneja la excepción
				e.printStackTrace();
			}

			System.out.println("Usted a presionado la opción listar");
			return;

		}

		else if (opcion.equals("mostrarSalario")) {
			System.out.println("Usted a presionado la opcion mostrarSalario");
			request.setAttribute("content", "views/buscarSalario.jsp");

			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
			requestDispatcher.forward(request, response);
			return;

		} else if (opcion.equals("editarEmpleado")) {
			System.out.println("Usted a presionado la opcion editar");
			request.setAttribute("content", "views/editarEmpleado.jsp");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
			requestDispatcher.forward(request, response);
			return;

		} else if (opcion.equals("eliminarEmpleado")) {
			System.out.println("Usted ha presionado la opción eliminar");
			request.setAttribute("content", "views/eliminar.jsp");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
			requestDispatcher.forward(request, response);
			return;
		}

		else if (opcion.equals("registro")) {
			System.out.println("Usted a presionado la opcion registrar");
			request.setAttribute("content", "views/registro.jsp");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
			requestDispatcher.forward(request, response);
			return;
		} else if (opcion.equals("login")) {
			request.setAttribute("mensajeError", "Email o contraseña inválidos");

			request.setAttribute("content", "views/login.jsp");

			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
			requestDispatcher.forward(request, response);
			System.out.println("Usted ha presionado la opción loguearse");
			return;

		}
		request.setAttribute("content", "views/componentes/menu.jsp");
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
		requestDispatcher.forward(request, response);
		return;

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
							"No se pudo guardar el empleado porque el DNI ya existe o has puesto campos no válidos");
				}
				// Redirigir de vuelta a la página de menu
				request.setAttribute("content", "views/componentes/menu.jsp");
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

						// Configura el atributo "content" con la ruta de mostrarSalario.jsp
						request.setAttribute("content", "views/mostrarSalario.jsp");
						RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
						requestDispatcher.forward(request, response);
					} else {
						// El DNI no existe en la base de datos, muestra un mensaje de error
						request.setAttribute("mensajeError", "El DNI del empleado no existe en la base de datos.");
						request.setAttribute("content", "views/componentes/menu.jsp");
						RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
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

					// Actualizar el sueldo en la base de dato
					boolean exitoActualizacionSueldo = nominaDAO.actualizarSueldo(dni, sueldoNuevo);

					if (exitoActualizacionSueldo) {
						// Éxito: el sueldo se actualizó correctamente
						request.setAttribute("mensajeExito", "El empleado se actualizó exitosamente.");
						request.setAttribute("content", "views/componentes/menu.jsp");

					} else {
						request.setAttribute("mensajeError", "No se pudo actualizar el sueldo.");
					}
				} else {
					// Error: no se pudo actualizar el empleado
					request.setAttribute("mensajeError",
							"No se pudo actualizar el empleado porque el DNI no existe o has insertado un campo mal");
				}

				// Redirigir de vuelta a la página de inicio
				request.setAttribute("content", "views/componentes/menu.jsp");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
				requestDispatcher.forward(request, response);
			} catch (DatosNoCorrectosException | SQLException e) {
				// Manejar las excepciones apropiadamente
				e.printStackTrace();
			}

		} else if (opcion.equals("eliminarEmpleado")) {

//			NominaDAO nominaDAO = new NominaDAO();
//
//			String dni = request.getParameter("dni");
//
//			try {
//				// Llamar al método eliminar de NominaDAO
//				boolean exito = nominaDAO.eliminar(dni);
//
//				if (exito) {
//					// Éxito: el empleado se marcó como eliminado correctamente
//					request.setAttribute("mensajeExito", "El empleado eliminó exitosamente.");
//					request.setAttribute("content", "views/componentes/menu.jsp");
//				} else {
//					// Error: no se pudo marcar como eliminado el empleado
//					request.setAttribute("mensajeError",
//							"No se pudo eliminar porque el DNI no existe o has insertado un campo mal.");
//					request.setAttribute("content", "views/componentes/menu.jsp");
//					RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
//					requestDispatcher.forward(request, response);
//				}
//
//				// Configurar la vista
////			    request.setAttribute("content", "views/componentes/menu.jsp");
//
//				// Redirigir de vuelta a la página de inicio
//				request.setAttribute("content", "views/componentes/menu.jsp");
//				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
//				requestDispatcher.forward(request, response);
//
//			} catch (SQLException e) {
//				// Manejar las excepciones apropiadamente
//				e.printStackTrace();
//			}

			NominaDAO nominaDAO = new NominaDAO();
			String dni = request.getParameter("dni");

			try {
				// Llamar al método eliminar de NominaDAO
				boolean exito = nominaDAO.eliminar(dni);

				if (exito) {
					// Éxito: el empleado se marcó como eliminado correctamente
					request.setAttribute("mensajeExito", "El empleado se eliminó exitosamente.");
				} else {
					// Error: no se pudo marcar como eliminado el empleado
					request.setAttribute("mensajeError",
							"No se pudo eliminar porque el DNI no existe o has insertado un campo mal.");
				}

			} catch (SQLException e) {
				// Manejar las excepciones apropiadamente
				e.printStackTrace();
			}

			// Configurar la vista
			request.setAttribute("content", "views/componentes/menu.jsp");

			// Redirigir de vuelta a la página de inicio
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
			requestDispatcher.forward(request, response);

		}

		else if (opcion.equals("registrarUsuario")) {

			String name = request.getParameter("name");
			String surnames = request.getParameter("surnames");
			String email = request.getParameter("email");
			String password = request.getParameter("password");

			// Realizo la lógica de registro aquí y llamo al controlador
			authController authController = new authController();
			User user = new User(name, surnames, email, password);

			try {
				boolean exito = authController.registrarUsuario(user);

				if (exito) {
					// Me redirige al usuario a "menu.jsp" en la carpeta "componentes" después del
					// registro exitoso
					System.out.println("Redirigiendo al usuario a menu.jsp");
//					request.getSession().setAttribute("mensajeExito", "Registro exitoso.");
//					response.sendRedirect(request.getContextPath() + "/views/componentes/menu.jsp");
					request.getSession().setAttribute("mensajeExito", "Registro exitoso.");
					request.setAttribute("content", "views/componentes/menu.jsp");
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
					requestDispatcher.forward(request, response);
				} else {
					// El registro no fue exitoso, muestra un mensaje de error
					request.setAttribute("mensajeError",
							"No se pudo completar el registro, porque el email ya existe, o has puesto un campo malo.");
					request.setAttribute("content", "views/registro.jsp");
					System.out.println("Errorrrr registro");

					RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
					requestDispatcher.forward(request, response);
				}
			} catch (SQLException e) {
				// Manejar las excepciones apropiadamente
				e.printStackTrace();
				// Puedes configurar un mensaje de error personalizado si lo necesitas
				request.setAttribute("mensajeError", "Ocurrió un error durante el registro.");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
				requestDispatcher.forward(request, response);
			}

		}

		else if (opcion.equals("loginUsuario")) {
			// Obtener los parámetros del formulario

			String email = request.getParameter("email");
			String password = request.getParameter("password");

			// Realizo la lógica de inicio de sesión aquí
			authController authController = new authController();

			try {
				String token = authController.login(email, password);

				if (token != null) {
					request.getSession().setAttribute("mensajeExito", "Inicio de sesión exitoso.");
					// El inicio de sesión fue exitoso, almacena el token en la sesión o como sea
					// necesario
					request.getSession().setAttribute("token", token);
					// Redirigir a la página de menú en caso de éxito
					response.sendRedirect(request.getContextPath() + "/Empresa?opcion=loginUsuario");
				} else {
					// Las credenciales son inválidas, muestro un mensaje de error
					request.setAttribute("mensajeError", "Usuario o contraseña incorrectas.");
					System.out.println("Errorrrr login");
//					// No redirigir, me quedare en la misma página (login.jsp)
					request.setAttribute("content", "views/login.jsp");
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
					requestDispatcher.forward(request, response);

				}

			} catch (SQLException e) {
				// Manejar las excepciones apropiadamente
				e.printStackTrace();
				// En caso de error en SQL, también puedes establecer un mensaje de error
				request.setAttribute("mensajeError", "Ocurrió un error durante el inicio de sesión.");

				// No redirigir, se quedará en la misma página (login.jsp)
				request.setAttribute("content", "views/login.jsp");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
				requestDispatcher.forward(request, response);
			}

		}
	}
}
