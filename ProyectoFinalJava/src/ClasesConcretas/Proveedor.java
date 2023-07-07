package ClasesConcretas;
import java.sql.*;

import java.util.ArrayList;

import Interfaces.GestionDeDatos;
import ConexionDB.Conexion;
import java.util.Scanner;
public class Proveedor implements GestionDeDatos<Proveedor>{

	//ATRIBUTOS
	private int id;
	private String nombreProveedor;
	private int telefono;
	//private ArrayList<Producto>productosProveedor;
	//El dato de a que proveedor corresponde cada producto va a ser indicado y subido a al DB desde la clase de productos, con la llave foranea
	
	//ATRIBUTOS PARA CONEXION
	Conexion conexion = new Conexion();
	private Connection cn = null;
	
	//SCANNER
	Scanner scanner = new Scanner (System.in);
	
	//CONSTRUCTOR
	public Proveedor(int id, String nombreProveedor, int telefono, ArrayList<Producto>productosProveedor) {
		super();
		this.id = id;
		this.nombreProveedor = nombreProveedor;
		this.telefono = telefono;
	//	this.productosProveedor = productosProveedor;
	}
	
	

	//OVERRIDES
	
	@Override
	public void Ver() {
		
		try{
			cn = conexion.conectar();
			
			String query = "SELECT * FROM proveedor";
			
			Statement declaracion = cn.createStatement();
			ResultSet resultados = declaracion.executeQuery(query);
			
			while(resultados.next()) { //mientras haya datos por leer
			System.out.println(
						"ID: " + resultados.getInt("idProveedor") + ". Nombre: " +resultados.getString("nombreProveedor") + 
						 ". Telefono: " + resultados.getInt("telefonoProveedor"));
			}
		} catch(SQLException e){
			e.printStackTrace();
		} 
	}

	@Override
	public void Buscar(int ID) {
		
		try{
			cn = conexion.conectar();
			
			String query = "SELECT * FROM proveedor WHERE  idProveedor = ?";
			
			PreparedStatement declaracion  = cn.prepareStatement(query);
		
				declaracion.setInt(1, ID);
				ResultSet resultados = declaracion.executeQuery();
				
			while(resultados.next()) { //mientras haya datos por leer
					System.out.println(
								"ID: " + resultados.getInt("idProveedor") + ". Nombre: " +resultados.getString("nombreProveedor") + 
								 ". Telefono: " + resultados.getInt("telefonoProveedor"));
			}
			
		} catch(SQLException e){
			e.printStackTrace();
		} 
	}
	
	@Override
	public void Actualizar(int id) {
	}
	
	@Override
	public void Agregar() {
		
		System.out.println("Por favor, ingrese los datos correspondientes");
		System.out.println("Nombre: ");
		String nombreProveedor = (scanner.nextLine()).trim().replace(" ", "_");
		System.out.println("Telefono: ");
		int telefono = scanner.nextInt();

		try{
			cn = conexion.conectar();
			
			String query = "INSERT INTO proveedor (nombreProveedor, telefonoProveedor) VALUES (?, ?)";
			//excluyo el id ya que es autoincremental
			
			PreparedStatement declaracion  = cn.prepareStatement(query);
			
				declaracion.setString(1, nombreProveedor);
				declaracion.setInt(2, telefono);
				
				declaracion.executeUpdate();
				
				System.out.println("Datos cargados exitosamente!");
			
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
		return "Proveedor: id=" + id + ", nombreProveedor=" + nombreProveedor + ", telefono=" + telefono
				+ ", productosProveedor=";
	}

	// GETTERS-SETTERS: 
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombreProveedor() {
		return nombreProveedor;
	}

	public void setNombreProveedor(String nombreProveedor) {
		this.nombreProveedor = nombreProveedor;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	
	
}
