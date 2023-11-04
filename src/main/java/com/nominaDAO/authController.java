package com.nominaDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.mindrot.jbcrypt.BCrypt;
import org.apache.catalina.connector.Response;
import com.conexion.Conexion;
import com.nomina.model.Empleado;
import com.nomina.model.Nomina;
import com.nomina.model.User;

import middleware.validateToken;

public class authController {

	Connection connection = null;
	PreparedStatement statement = null;
	boolean exito = false;
	
	public boolean registrarUsuario(User user) throws SQLException {
//	    Connection connection = null;
//	    PreparedStatement statement = null;
//	    boolean exito = false;

	    try {
	        connection = obtenerConexion();
	        connection.setAutoCommit(false);
	        
	        ;

	        // Verificar si el usuario ya existe en la base de datos por correo electrónico
	        if (existeUserconEmail(user.getEmail().toLowerCase())) {
	            System.out.println("El usuario ya existe en la base de datos.");
	            exito = false;
	            
	       } else if (validarCamposUser(user) && isValidEmail(user.getEmail())) {
	    	   
	            System.out.println("Campos válidos, procediendo a la inserción.");
	            
	            // Los campos son válidos, procedemos a la inserción
	            String contraseñaEncriptada = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12));

	            String sql = "INSERT INTO users (name, surnames, email, password) VALUES(?,?,?,?)";
	            statement = connection.prepareStatement(sql);

	            statement.setString(1, user.getName());
	            statement.setString(2, user.getSurnames());
	            statement.setString(3, user.getEmail());
	            statement.setString(4, contraseñaEncriptada);

	            exito = statement.executeUpdate() > 0;

	            if (exito) {
	                // Crear las reclamaciones del token JWT
	                Map<String, Object> claims = new HashMap<>();
	                claims.put("email", user.getEmail()); // Agrega las reclamaciones necesarias, en este caso, el
	                // correo electrónico del usuario

	                // Generar el token JWT
	                String token = validateToken.generateToken(claims);
	                System.out.println("Token JWT generado: " + token);
	            }
	        } else {
	        	
	            System.out.println("Los campos del usuario no son válidos.");
	            // Los campos del usuario no son válidos
	            exito = false;
	        }

	        if (exito) {
	            connection.commit();
	        } else {
	            connection.rollback();
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

	// Método para validar el formato del correo electrónico
	private boolean isValidEmail(String email) {
		String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
	    Pattern pattern = Pattern.compile(emailRegex);
	    Matcher matcher = pattern.matcher(email);
	    return matcher.matches();
	}
	
	private boolean validarCamposUser(User user) {
		boolean nombreValido = user.getName() != null && user.getName().length() <= 30;
		boolean apellidosValidos = user.getSurnames() != null && user.getSurnames().length() <= 50;
		boolean passwordValida = user.getPassword() != null && user.getPassword().length() == 8;
		
		

		return nombreValido && apellidosValidos && passwordValida;
	}

	public boolean existeUserconEmail(String email) {
	    Connection connection = null;
	    PreparedStatement statement = null;
	    ResultSet resultSet = null;

	    try {
	        connection = obtenerConexion();
	        String sql = "SELECT COUNT(*) FROM users WHERE email = ?";
	        statement = connection.prepareStatement(sql);
	        statement.setString(1, email); // Corregir el índice del parámetro a 1
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
	
	
	//Login
	public String login(String email, String password) throws SQLException {
	    Connection connection = null;
	    PreparedStatement statement = null;
	    ResultSet resultSet = null;

	    try {
	        connection = obtenerConexion();
	        String sql = "SELECT email, password FROM users WHERE email = ?";
	        statement = connection.prepareStatement(sql);
	        statement.setString(1, email);
	        resultSet = statement.executeQuery();

	        if (resultSet.next()) {
	            String storedEmail = resultSet.getString("email");
	            String storedPassword = resultSet.getString("password");

	            if (BCrypt.checkpw(password, storedPassword)) {
	                // Las credenciales son válidas
	                Map<String, Object> claims = new HashMap<>();
	                claims.put("email", email); // Agrega las reclamaciones necesarias, en este caso, el correo electrónico del usuario

	                // Generar el token JWT
	                String token = validateToken.generateToken(claims);
	                System.out.println("Token JWT generado: " + token);
	                return token;
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        if (resultSet != null) {
	            resultSet.close();
	        }
	        if (statement != null) {
	            statement.close();
	        }
	        if (connection != null) {
	            connection.close();
	        }
	    }

	    return null; 
	}

	
	
	
	

	private Connection obtenerConexion() throws SQLException {
		return Conexion.getConnection();
	}

}



