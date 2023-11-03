package com.nominaDAO;

import java.sql.Connection;


import com.nomina.model.Nomina;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.tagext.TryCatchFinally;

import com.conexion.*;
import com.nomina.model.DatosNoCorrectosException;
import com.nomina.model.Empleado;
import com.nomina.model.Persona;


/**
 * Esta clase se encarga de realizar operaciones relacionadas con la base de datos de la nómina de empleados.
 */



public class NominaDAO {

	private Connection connection;
	private PreparedStatement statement;
	private boolean estadoOperacion;

	
	/**
     * Guarda un nuevo empleado en la base de datos.
     * @param empleado El objeto Empleado a guardar.
     * @return `true` si el empleado se guardó con éxito, `false` en caso contrario.
     * @throws SQLException Si ocurre un error de SQL.
     */
	public boolean guardar(Empleado empleado) throws SQLException {
	    Connection connection = null;
	    PreparedStatement statement = null;
	    boolean exito = false;

	    try {
	        connection = obtenerConexion();
	        connection.setAutoCommit(false);

	        // Verificar si el empleado ya existe en la base de datos (por ejemplo, por su DNI)
	        if (existeEmpleadoConConDni(empleado.getDni())) {
	            // Si el empleado ya existe, no permitiremos guardar otro con el mismo DNI
	            exito = false;
	        } else if (validarCamposEmpleado(empleado) && validarFormatoDNI(empleado.getDni())) {
	            // Los campos son válidos y el DNI tiene el formato correcto, procedemos a la inserción
	            String sql = "INSERT INTO empleados (dni, nombre, sexo, categoria, anyos) VALUES(?,?,?,?,?)";
	            statement = connection.prepareStatement(sql);

	            statement.setString(1, empleado.getDni());
	            statement.setString(2, empleado.getNombre());
	            statement.setString(3, empleado.getSexo());
	            statement.setInt(4, empleado.getCategoria());
	            statement.setInt(5, empleado.getAnyos());

	            exito = statement.executeUpdate() > 0;

	            if (exito) {
	                // Calcular el sueldo utilizando la función sueldo de la clase Nomina
	                int sueldo = Nomina.sueldo(empleado); // Asegúrate de que este método calcule el sueldo correctamente

	                // Insertar el sueldo en la tabla nominas usando un JOIN
	                String sqlInsertSueldo = "INSERT INTO nominas (dni_empleado, sueldo) " +
	                        "SELECT empleados.dni, ? FROM empleados WHERE dni=?";
	                PreparedStatement statementInsertSueldo = connection.prepareStatement(sqlInsertSueldo);
	                statementInsertSueldo.setInt(1, sueldo);
	                statementInsertSueldo.setString(2, empleado.getDni());

	                statementInsertSueldo.executeUpdate();
	                connection.commit();
	                statementInsertSueldo.close();
	            } else {
	                connection.rollback();
	            }
	        } else {
	            // Los campos del empleado no son válidos o el DNI no tiene el formato correcto
	            exito = false;
	        }
	    } catch (SQLException e) {
	        // Manejar excepciones
	        e.printStackTrace();
	    } finally {
	        // Cerrar los recursos
	        if (statement != null) {
	            statement.close();
	        }
	        if (connection != null) {
	            connection.close();
	        }
	    }

	    return exito;
	}
	
	/**
	 * Valida los campos del objeto Empleado para asegurarse de que cumplan con ciertas restricciones.
	 * @param empleado El objeto Empleado cuyos campos se van a validar.
	 * @return `true` si todos los campos son válidos, `false` si alguno de los campos no cumple con las restricciones.
	 */
	
	private boolean validarCamposEmpleado(Empleado empleado) {
	    boolean nombreValido = empleado.getNombre() != null && empleado.getNombre().length() <= 70;
	    boolean sexoValido = empleado.getSexo() != null && (empleado.getSexo().equals("M") || empleado.getSexo().equals("F"));
	    boolean categoriaValida = empleado.getCategoria() >= 1 && empleado.getCategoria() <= 10;
	    boolean anyosValidos = empleado.getAnyos() >= 0;

	    return nombreValido && sexoValido && categoriaValida && anyosValidos;
	}


	/**
	 * Valida el formato de un número de identificación (DNI) para asegurarse de que cumple con un patrón específico.
	 * @param dni El número de identificación (DNI) a validar.
	 * @return `true` si el DNI cumple con el formato especificado, `false` en caso contrario.
	 */
	
	public boolean validarFormatoDNI(String dni) {
	    // Validar que el DNI tenga 8 dígitos seguidos de una letra mayúscula
	    return dni.matches("^\\d{8}[A-Z]$");
	}
	
	/**
	 * Comprueba si existe un empleado con un número de identificación (DNI) específico en la base de datos.
	 * @param dniEmpleado El número de identificación (DNI) a buscar en la base de datos.
	 * @return `true` si existe un empleado con el DNI especificado, `false` si no existe.
	 */
	
	public boolean existeEmpleadoConConDni(String dniEmpleado) {
		
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = obtenerConexion();
			String sql = "SELECT COUNT(*) FROM empleados WHERE dni = ?";
			statement = connection.prepareStatement(sql);
			statement.setString(1, dniEmpleado);
			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				int count = resultSet.getInt(1);
				return count > 0;
			}
		} catch (SQLException e) {
			// Manejar excepciones
			e.printStackTrace();
		}

		return false;
	}
	
	/**
	 * Edita los datos de un empleado en la base de datos, si el empleado existe.
	 * @param empleado El objeto Empleado con los nuevos datos a editar en la base de datos.
	 * @return `true` si los datos del empleado se editaron con éxito, `false` en caso contrario.
	 * @throws SQLException Si ocurre un error de SQL durante la edición.
	 */
	
	public boolean editar(Empleado empleado) throws SQLException {
		
		Connection connection = null;
	    PreparedStatement statement = null;
	    boolean exito = false;
	  
	    try {
	        connection = obtenerConexion();
	        connection.setAutoCommit(false);

	        // Verifica si el empleado existe en la base de datos
	        if (existeEmpleadoConConDni(empleado.getDni()) && validarCamposEmpleado(empleado)) {
	            // Realiza la actualización de los datos del empleado
	            String sql = "UPDATE empleados SET nombre=?, sexo=?, categoria=?, anyos=? WHERE dni=?";
	            statement = connection.prepareStatement(sql);

	            statement.setString(1, empleado.getNombre());
	            statement.setString(2, empleado.getSexo());
	            statement.setInt(3, empleado.getCategoria());
	            statement.setInt(4, empleado.getAnyos());
	            statement.setString(5, empleado.getDni());

	            exito = statement.executeUpdate() > 0;

	            if (exito) {
	                connection.commit();
	                // Luego, actualiza el sueldo del empleado si es necesario
	                int sueldo = Nomina.sueldo(empleado); // Asegúrate de que este método calcule el sueldo correctamente
	                String actualizarSueldo = "UPDATE nominas SET sueldo = ? WHERE dni_empleado = ?";
	                statement = connection.prepareStatement(actualizarSueldo);
	                statement.setInt(1, sueldo);
	                statement.setString(2, empleado.getDni());
	                statement.executeUpdate();
	            } else {
	                connection.rollback();
	            }
	        } else {
	            // El empleado no existe, por lo que no se puede editar
	            exito = false;
	        }
	    } catch (SQLException e) {
	        // Manejar excepciones
	        e.printStackTrace();
	    } finally {
	        // Cerrar los recursos
	        if (statement != null) {
	            statement.close();
	        }
	        if (connection != null) {
	            connection.close();
	        }
	    }

	    return exito;
		
	}
	
	//Metodo para que me actualice el sueldo en la base de datos
	public boolean actualizarSueldo(String dniEmpleado, int nuevoSueldo) throws SQLException {
	    Connection connection = null;
	    PreparedStatement statement = null;
	    boolean exito = false;

	    try {
	        connection = obtenerConexion();
	        connection.setAutoCommit(false);

	        // Preparar la consulta SQL para actualizar el sueldo
	        String sql = "UPDATE nominas SET sueldo = ? WHERE dni_empleado = ?";
	        statement = connection.prepareStatement(sql);
	        statement.setInt(1, nuevoSueldo);
	        statement.setString(2, dniEmpleado);

	        // Ejecutar la actualización del sueldo
	        exito = statement.executeUpdate() > 0;

	        if (exito) {
	            connection.commit();
	        } else {
	            connection.rollback();
	        }
	    } catch (SQLException e) {
	        // Manejar las excepciones apropiadamente
	        e.printStackTrace();
	    } finally {
	        // Cerrar los recursos
	        if (statement != null) {
	            statement.close();
	        }
	        if (connection != null) {
	            connection.close();
	        }
	    }

	    return exito;
	}
	
	/**
	 * Actualiza el sueldo de un empleado en la base de datos.
	 * @param dniEmpleado El número de identificación (DNI) del empleado cuyo sueldo se va a actualizar.
	 * @param nuevoSueldo El nuevo valor del sueldo del empleado.
	 * @return `true` si el sueldo se actualizó con éxito, `false` en caso contrario.
	 * @throws SQLException Si ocurre un error de SQL durante la actualización del sueldo.
	 */
	
	public List<Empleado> obtenerEmpleados() throws SQLException, DatosNoCorrectosException {
	    
		ResultSet resultSet = null;
	    List<Empleado> listaEmpleados = new ArrayList<>();

	    String sql = null;
	    estadoOperacion = false;
	    connection = obtenerConexion();

	    try {
	        String consultaSQL = "SELECT * FROM empleados";
	        statement = connection.prepareStatement(consultaSQL); 

	        ResultSet rs = statement.executeQuery();

	        while (rs.next()) {
	            String dni = rs.getString("dni");
	            String nombre = rs.getString("nombre");
	            String sexo = rs.getString("sexo");
	            int categoria = rs.getInt("categoria");
	            int anyos = rs.getInt("anyos");

	            // Crea un objeto Empleado con los datos obtenidos
	            Empleado empleado = new Empleado(dni, nombre, sexo, categoria, anyos);
	            listaEmpleados.add(empleado);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        // Cierra el PreparedStatement y la conexión en el bloque finally
	        if (statement != null) {
	            statement.close();
	        }
	        if (connection != null) {
	            connection.close();
	        }
	    }

	    return listaEmpleados;
	}

	/**
	 * Obtiene el salario de un empleado a través de su número de identificación (DNI).
	 * @param dniEmpleado El número de identificación (DNI) del empleado para el que se desea obtener el salario.
	 * @return El salario del empleado si existe y es mayor que cero, de lo contrario retorna 0.
	 * @throws SQLException Si ocurre un error de SQL durante la consulta del salario.
	 * @throws DatosNoCorrectosException Si los datos obtenidos no son correctos o no se encuentra el empleado.
	 */
	
	public int obtenerSalarioPorDNI(String dniEmpleado) throws SQLException, DatosNoCorrectosException {
	    ResultSet rs = null;
	    int sueldo = 0; 

	    String sql = null;
	    connection = obtenerConexion();

	    try {
	        // Verificar si el DNI existe antes de realizar la consulta
	        if (existeEmpleadoConConDni(dniEmpleado)) {
	            sql = "SELECT n.sueldo " +
	                 "FROM nominas n " +
	                 "JOIN empleados e ON n.dni_empleado = e.dni " +
	                 "WHERE e.dni = ?";
	            
	            statement = connection.prepareStatement(sql);
	            statement.setString(1, dniEmpleado);

	            rs = statement.executeQuery();

	            if (rs.next()) {
	                // Obtén el salario del empleado
	                sueldo = rs.getInt("sueldo");
	            }
	        }
	    } catch (SQLException e) {
	        // Manejo las excepciones
	        e.printStackTrace();
	    } finally {
	        // Cierro los recursos abiertos
	        if (rs != null) {
	            rs.close();
	        }
	        if (statement != null) {
	            statement.close();
	        }
	        if (connection != null) {
	            connection.close();
	        }
	    }

	    return sueldo;
	}

	/**
	 * Obtiene el nombre de un empleado a través de su número de identificación (DNI).
	 * @param dniEmpleado El número de identificación (DNI) del empleado para el cual se desea obtener el nombre.
	 * @return El nombre del empleado si existe en la base de datos, de lo contrario, retorna null.
	 * @throws SQLException Si ocurre un error de SQL durante la consulta del nombre.
	 * @throws DatosNoCorrectosException Si los datos obtenidos no son correctos o no se encuentra el empleado.
	 */
	
	public String obtenerNombrePorDNI(String dniEmpleado) throws SQLException, DatosNoCorrectosException {
	    ResultSet rs = null;
	    String nombre = null;

	    String sql = null;
	    connection = obtenerConexion();

	    try {
	        sql = "SELECT nombre FROM empleados WHERE dni = ?";
	        statement = connection.prepareStatement(sql);
	        statement.setString(1, dniEmpleado);

	        rs = statement.executeQuery();

	        if (rs.next()) {
	            // Obtén el nombre del empleado
	            nombre = rs.getString("nombre");
	        }
	    } catch (SQLException e) {
	        // Manejo de excepciones
	        e.printStackTrace();
	    } finally {
	        // Cierro los recursos abiertos
	        if (rs != null) {
	            rs.close();
	        }
	        if (statement != null) {
	            statement.close();
	        }
	        if (connection != null) {
	            connection.close();
	        }
	    }

	    return nombre;
	}
	/**
	 * Obtiene una conexión a la base de datos a través del pool de conexiones.
	 * @return Una conexión a la base de datos.
	 * @throws SQLException Si ocurre un error al intentar obtener una conexión.
	 */

	private Connection obtenerConexion() throws SQLException {
		return Conexion.getConnection();
	}
}
