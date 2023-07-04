package ConexionDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	
	private final static String CONTROLADOR = "com.mysql.cj.jdbc.Driver";
	private final static String URL = "jdbc:mysql://localhost:3306/java_proyecto";
	private final static String USER = "root";
	private final static String PASSWORD = "roukoperro2013";
	
	static {
		try {
			Class.forName(CONTROLADOR);
			
			System.out.println("CONNECTED");
			
		} catch (ClassNotFoundException e) {
			System.out.println("Error al cargar el controlador");
			e.printStackTrace();
		}
	}
	
	public Connection conectar() {
		Connection conexion = null;
		
		try {
			
			conexion = DriverManager.getConnection(URL, USER, PASSWORD);
			
		} catch (SQLException e) {
			System.out.println("Error en la conexión");
			e.printStackTrace();
		}
		
		return conexion;
		
		
	}
	
	

}
