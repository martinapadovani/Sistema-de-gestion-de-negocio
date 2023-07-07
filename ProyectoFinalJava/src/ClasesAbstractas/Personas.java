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

public class Personas implements GestionDeDatos<Empleado>{
	
	//ATRIBUTOS
	
	private String nombre;
	private String apellido;
	private int dni;
	private int teléfono;
	private String email;
	private int edad;
	
	//ATRIBUTOS PARA CONEXION
	Conexion conexion = new Conexion();
	private Connection cn = null;
	
	//SCANNER
	Scanner scanner = new Scanner (System.in);
	
	//CONSTRUCTOR
	
	public Personas(String nombre, String apellido, int dni, int teléfono, String email, int edad) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.teléfono = teléfono;
		this.email = email;
		this.edad = edad;
	}

	// METODOS
	
   //OVERRIDE
	
	@Override
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
	
	@Override
	public void Buscar(int id) {
		
		try{
			cn = conexion.conectar();
			
			String query = "SELECT * FROM empleado WHERE  idEmpleado = ?";
			
			PreparedStatement declaracion  = cn.prepareStatement(query);
			
				declaracion.setInt(1, id);
				ResultSet resultados = declaracion.executeQuery();
				
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
	
	/*@Override
	public void Agregar() {
		
	}*/
	
	@Override
	public void Actualizar(int id) {
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
		
		try{
			cn = conexion.conectar();
			
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
				
				String querySelect = "SELECT * FROM persona WHERE  idPersona = ?";
				
				PreparedStatement declaracionSelect  = cn.prepareStatement(querySelect);
			
				declaracionSelect.setInt(1, id);
				ResultSet resultados = declaracionSelect.executeQuery();
				
			while(resultados.next()) { //mientras haya datos por leer
				System.out.println("Proceso exitoso! Datos actualizados: ");
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
	
	@Override
	public void Eliminar(int id) {
		
	}
	
	//TO STRING
	
	@Override
	public String toString() {
		return "Personas [nombre=" + nombre + ", apellido=" + apellido + ", dni=" + dni + ", teléfono=" + teléfono
				+ ", email=" + email + ", fechaDeRegistro=" +  "]";
	}
	


	//GETTERS-SETTERS
	public String getNombre() {
		return nombre;
	}


	public int getEdad() {
		return edad;
	}


	public void setEdad(int edad) {
		this.edad = edad;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellido() {
		return apellido;
	}


	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	public int getDNI() {
		return dni;
	}


	public void setDNI(int dNI) {
		dni = dNI;
	}


	public int getTeléfono() {
		return teléfono;
	}


	public void setTeléfono(int teléfono) {
		this.teléfono = teléfono;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public void Agregar() {
		// TODO Auto-generated method stub
		
	}




}
