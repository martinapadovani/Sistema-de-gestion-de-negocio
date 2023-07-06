package ConexionDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	
	private static final String CONTROLADOR = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://aws.connect.psdb.cloud/java_project?sslMode=VERIFY_IDENTITY";
	private static final String USER = "acn8hu1nr8bj2e7zbdbb";
	private static final String PASSWORD = "pscale_pw_TvqxMvf3dLDM7fKJELo38uOTM4l6ofrkTvcGrue3Bca";
	
	static {
		try {
			Class.forName(CONTROLADOR);
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Connection conectar() {
		
		Connection conexion = null;
		
		try {
			conexion = DriverManager.getConnection(URL, USER, PASSWORD);
			
			System.out.println("CONECTADO");
			
		} catch (SQLException e) {
			System.out.println("Error en la conexión");
			e.printStackTrace();
		}
	
		return conexion;

	}

}
