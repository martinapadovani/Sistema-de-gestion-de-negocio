package ClasesAbstractas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import ClasesConcretas.Empleado;
import ConexionDB.Conexion;
import Interfaces.GestionDeDatos;

public class Personas{
	
	
	//ATRIBUTOS PARA CONEXION
	Conexion conexion = new Conexion();
	private Connection cn = null;
	
	//SCANNER
	Scanner scanner = new Scanner (System.in);
	
	//CONSTRUCTOR
	
	public Personas() {
	}

	// METODOS
	
	public void Ver() {
		
		try{
			cn = conexion.conectar();
			
			String query = "SELECT * FROM persona";
			
			Statement declaracion = cn.createStatement();
			ResultSet resultados = declaracion.executeQuery(query);
			
			while(resultados.next()) { //mientras haya datos por leer
				System.out.println(
						"ID: " + resultados.getInt("idPersona") + ". DNI: " +resultados.getInt("dni") + 
						". Telefono: " + resultados.getInt("telefonoPersona") + ". Nombre: " + resultados.getString("nombrePersona") + 
						". Apellido: " + resultados.getString("apellidoPersona") + ". Edad: " +  resultados.getInt("edad") + 
						". Email: " +  resultados.getString("email"));
			}
			
		} catch(SQLException e){
			e.printStackTrace();
		} 
		
	}
	

	public void Buscar(int id) {
		
		try{
			cn = conexion.conectar();
			
			String query = "SELECT * FROM empleado WHERE  idEmpleado = ?";
			
			PreparedStatement declaracion  = cn.prepareStatement(query);
			
				declaracion.setInt(1, id);
				ResultSet resultados = declaracion.executeQuery();
				
			if(resultados.next()) { //mientras haya datos por leer
					System.out.println(
							"ID: " + resultados.getInt("idPersona") + ". DNI: " +resultados.getInt("dni") + 
							". Telefono: " + resultados.getInt("telefonoPersona") + ". Nombre: " + resultados.getString("nombrePersona") + 
							". Apellido: " + resultados.getString("apellidoPersona") + ". Edad: " +  resultados.getInt("edad") + 
							". Email: " +  resultados.getString("email"));
			}else {
				System.out.println("ID inválido! Vuelva a intentarlo.");
			}
			
		} catch(SQLException e){
			e.printStackTrace();
		} 
		
	}

	public void Actualizar(int id) {
		
	  try{
			cn = conexion.conectar();
		
			String querySelect = "SELECT * FROM persona WHERE  idPersona = ?";
		
			PreparedStatement declaracionSelect  = cn.prepareStatement(querySelect);
	
			declaracionSelect.setInt(1, id);
			ResultSet resultados = declaracionSelect.executeQuery();
			
			if(resultados.next()) { //mientras haya datos por leer
				
				System.out.println("Por favor, ingrese los datos correspondientes");
				System.out.println("DNI: ");
				int dni = scanner.nextInt();
				System.out.println("Telefono: ");
				int telefono = scanner.nextInt();
				System.out.println("Nombre: ");
				String nombre = scanner.next().trim().replace(" ", "_");
				System.out.println("Apellido:");
				String apellido = scanner.next().trim().replace(" ", "_");
				System.out.println("Edad: ");
				int edad = scanner.nextInt();
				System.out.println("Email: ");
				String email = scanner.next().trim().replace(" ", "_");
			
				//ACTUALIZACION
				
				String queryUpdate = "UPDATE persona SET dni = ?, telefonoPersona = ?, nombrePersona = ?, apellidoPersona = ?, edad = ?, email  = ?  WHERE idPersona = ?";
				
				PreparedStatement declaracionUpdate  = cn.prepareStatement(queryUpdate);
			
				declaracionUpdate.setInt(1, dni);
				declaracionUpdate.setInt(2, telefono);
				declaracionUpdate.setString(3, nombre);
				declaracionUpdate.setString(4, apellido);
				declaracionUpdate.setInt(5, edad);
				declaracionUpdate.setString(6, email);
				
				declaracionUpdate.setInt(7, id);
					
				declaracionUpdate.executeUpdate();
				
				//VER DATOS
					
				while(resultados.next()) { //mientras haya datos por leer
					System.out.println("Proceso exitoso! Datos actualizados: ");
						System.out.println(
								"ID: " + resultados.getInt("idPersona") + ". DNI: " +resultados.getInt("dni") + 
								". Telefono: " + resultados.getInt("telefonoPersona") + ". Nombre: " + resultados.getString("nombrePersona") + 
								". Apellido: " + resultados.getString("apellidoPersona") + ". Edad: " +  resultados.getInt("edad") + 
								". Email: " +  resultados.getString("email"));
								}
			}
		} catch(SQLException e){
			e.printStackTrace();
		} 
	}
}
