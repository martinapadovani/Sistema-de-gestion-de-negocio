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
						". Telefono: " + resultados.getLong("telefonoPersona") + ". Nombre: " + resultados.getString("nombrePersona") + 
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
			
			String query = "SELECT * FROM persona WHERE  idPersona = ?";
			
			PreparedStatement declaracion  = cn.prepareStatement(query);
			
				declaracion.setInt(1, id);
				ResultSet resultados = declaracion.executeQuery();
				
			if(resultados.next()) { //mientras haya datos por leer
					System.out.println(
							"ID: " + resultados.getInt("idPersona") + ". DNI: " +resultados.getInt("dni") + 
							". Telefono: " + resultados.getLong("telefonoPersona") + ". Nombre: " + resultados.getString("nombrePersona") + 
							". Apellido: " + resultados.getString("apellidoPersona") + ". Edad: " +  resultados.getInt("edad") + 
							". Email: " +  resultados.getString("email"));
			}else {
				System.out.println("ID inválido! Vuelva a intentarlo.");
			}
			
		} catch(SQLException e){
			e.printStackTrace();
		} 
		
	}

	public void Actualizar(int id)  {
		
		try{
			cn = conexion.conectar();
			
			String querySelect = "SELECT * FROM persona WHERE  idPersona = ?";
			
			PreparedStatement declaracionSelect  = cn.prepareStatement(querySelect);
		
			declaracionSelect.setInt(1, id);
			ResultSet resultados = declaracionSelect.executeQuery();
			
			if(resultados.next()) {
				
			boolean salir = false;
				
			  while (!salir) {
				  
				System.out.println("Ingrese el atributo que quisiera actualizar");
				System.out.println("1. DNI.");
				System.out.println("2. Telefono.");
				System.out.println("3. Nombre.");
				System.out.println("4. Apellido.");
				System.out.println("5. Edad.");
				System.out.println("6. Email.");

				int opcion = scanner.nextInt();
				
					switch(opcion) {
					
					case 1:
						scanner.nextLine();
						System.out.println("DNI:");
						int dni = scanner.nextInt();
						
						String queryUpdate1 = "UPDATE persona SET dni = ? WHERE idPersona = ?";
						
						PreparedStatement declaracionUpdate1  = cn.prepareStatement(queryUpdate1);
					
						declaracionUpdate1.setInt(1, dni);
						declaracionUpdate1.setInt(2, id);
							
						declaracionUpdate1.executeUpdate();
						
					break;
						
					case 2:
						scanner.nextLine();
						System.out.println("Telefono:");
						long  telefono = scanner.nextLong();
						
						String queryUpdate = "UPDATE persona SET telefonoPersona = ? WHERE  idPersona = ?";
						
						PreparedStatement declaracionUpdate  = cn.prepareStatement(queryUpdate);
					
						declaracionUpdate.setLong(1, telefono);
						declaracionUpdate.setInt(2, id);
							
						declaracionUpdate.executeUpdate();
					break;
					
					case 3:
						scanner.nextLine();
						System.out.println("Nombre:");
						String nombre = scanner.next().trim().replace(" ", "_");
						
						String queryUpdate3 = "UPDATE persona SET nombrePersona = ? WHERE  idPersona = ?";
						PreparedStatement declaracionUpdate3  = cn.prepareStatement(queryUpdate3);
					
						declaracionUpdate3.setString(1, nombre);
						declaracionUpdate3.setInt(2, id);
							
						declaracionUpdate3.executeUpdate();
						break;
					
					case 4:
						scanner.nextLine();
						System.out.println("Apellido:");
						String apellido = scanner.next().trim().replace(" ", "_");
						
						String queryUpdate4 = "UPDATE persona SET apellidoPersona = ? WHERE  idPersona = ?";
						PreparedStatement declaracionUpdate4  = cn.prepareStatement(queryUpdate4);
					
						declaracionUpdate4.setString(1, apellido);
						declaracionUpdate4.setInt(2, id);
							
						declaracionUpdate4.executeUpdate();
						break;
					
					case 5:
						scanner.nextLine();
						System.out.println("Edad:");
						int edad = scanner.nextInt();
						
						String queryUpdate5 = "UPDATE persona SET edad = ? WHERE  idPersona = ?";
						PreparedStatement declaracionUpdate5 = cn.prepareStatement(queryUpdate5);
					
						declaracionUpdate5.setInt(1, edad);
						declaracionUpdate5.setInt(2, id);
							
						declaracionUpdate5.executeUpdate();
						break;
					
					case 6:
						scanner.nextLine();
						System.out.println("Email: ");
						String email = scanner.next().trim().replace(" ", "_");
						
						String queryUpdate6 = "UPDATE persona SET email = ? WHERE  idPersona = ?";
						PreparedStatement declaracionUpdate6 = cn.prepareStatement(queryUpdate6);
					
						declaracionUpdate6.setString(1, email);
						declaracionUpdate6.setInt(2, id);
							
						declaracionUpdate6.executeUpdate();
						break;
					
					default:
						System.out.println("Opcion inválida! Vuelva a intentarlo.");
						break;
					}
					
					System.out.println("Desea seguir actualizando? (si/no)");
					String seguir = scanner.next();
					  
					  if(seguir.equalsIgnoreCase("si")) {
						  
						  salir = false;
						  
					  }else {
						  salir = true;
					  }
				}
				
				//VER DATOS ACTUALIZADOS
				ResultSet resultados2 = declaracionSelect.executeQuery();
					
				while(resultados2.next()) { //mientras haya datos por leer
						System.out.println("Proceso exitoso! Datos actualizados: ");
						System.out.println(
								"ID: " + resultados2.getInt("idPersona") + ". DNI: " +resultados2.getInt("dni") + 
								". Telefono: " + resultados2.getLong("telefonoPersona") + ". Nombre: " + resultados2.getString("nombrePersona") + 
								". Apellido: " + resultados2.getString("apellidoPersona") + ". Edad: " +  resultados2.getInt("edad") + 
								". Email: " +  resultados2.getString("email"));
					}
			}else {
				System.out.println("ID inválido! Vuelva a intentarlo.");
			}
		} catch(SQLException e){
			e.printStackTrace();
		} 
		
	}
	
	

}
