package com.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

/**
 * Esta clase proporciona una conexión a una base de datos MariaDB utilizando un DataSource.
 */

public class Conexion {
	
	
	private static BasicDataSource dataSource = null;
	
	/**
     * Obtiene el DataSource para la conexión a la base de datos.
     * Si el DataSource aún no se ha inicializado, lo crea con la configuración predefinida.
     * @return El DataSource configurado para la conexión a la base de datos.
     */
	 
	 private static DataSource getDataSource() {
	  if (dataSource == null) {
	   dataSource = new BasicDataSource();
	   dataSource.setDriverClassName("org.mariadb.jdbc.Driver");
	   dataSource.setUsername("root");
	   dataSource.setPassword("123456");
	   dataSource.setUrl("jdbc:mariadb://localhost:3306/laboral");
	  
	  }
	  return dataSource;
	 }
	 /**
	     * Obtiene una conexión a la base de datos utilizando el DataSource configurado.
	     * @return Una conexión a la base de datos MariaDB.
	     * @throws SQLException Si ocurre un error al establecer la conexión.
	 */
	 
	 public static Connection getConnection() throws SQLException {
	  return getDataSource().getConnection();
	 }
	
}
