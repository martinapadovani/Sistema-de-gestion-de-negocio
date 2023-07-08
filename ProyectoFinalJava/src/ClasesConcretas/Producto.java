package ClasesConcretas;
import java.util.Scanner;
import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import ConexionDB.Conexion;
import Interfaces.GestionDeDatos;

public class Producto implements GestionDeDatos<Producto>{
	

	
	//ATRIBUTOS PARA CONEXION
	Conexion conexion = new Conexion();
	private Connection cn = null;
	
	//SCANNER
	Scanner scanner = new Scanner (System.in);

	//CONSTRUCTOR

	public Producto() {

	}
	
	//METODOS
	
	public void verStock(Producto producto) {
		
		try{
			cn = conexion.conectar();
			
			String query = "SELECT stockDisponible FROM producto";
			
			Statement declaracion = cn.createStatement();
			ResultSet resultados = declaracion.executeQuery(query);
			
			while(resultados.next()) { //mientras haya datos por leer
				System.out.println(resultados.getInt("stockDisponible"));
			}
			
		} catch(SQLException e){
			e.printStackTrace();
		} 
	}
	
	public void calcularStock(Producto producto) {
		
	}
	

	//OVERRIDE
	
	@Override
	public void Ver(){
		
		try{
			cn = conexion.conectar();
			
			String query = "SELECT * FROM producto";
			
			Statement declaracion = cn.createStatement();
			ResultSet resultados = declaracion.executeQuery(query);
			
			while(resultados.next()) { //mientras haya datos por leer
				System.out.println(
						"ID: " + resultados.getInt("idProducto") + ". Nombre: " +resultados.getString("nombreProducto") + 
						". Categoria: " + resultados.getString("categoria") + ". Stock: " + resultados.getInt("stockDisponible") + 
						". Precio: " + resultados.getInt("precioxUnidad") + ". Id Proveedor: " +  resultados.getInt("proveedor_id"));
			}
			
		} catch(SQLException e){
			e.printStackTrace();
		} 
	}
	
	@Override
	public void Buscar(int id) {
		
		try{
			cn = conexion.conectar();
			
			String query = "SELECT * FROM producto WHERE  idProducto = ?";
			
			PreparedStatement declaracion  = cn.prepareStatement(query);
			
				declaracion.setInt(1, id);
				ResultSet resultados = declaracion.executeQuery();
				
			if(resultados.next()) { //mientras haya datos por leer
					System.out.println(
							"ID: " + resultados.getInt("idProducto") + ". Nombre: " +resultados.getString("nombreProducto") + 
							". Categoria: " + resultados.getString("categoria") + ". Stock: " + resultados.getInt("stockDisponible") + 
							". Precio: " + resultados.getInt("precioxUnidad") + ". Id Proveedor: " +  resultados.getInt("proveedor_id"));
			}else {
				System.out.println("ID inválido! Vuelva a intentarlo.");
			}
			
		} catch(SQLException e){
			e.printStackTrace();
		} 
	}

	
	@Override
	public void Actualizar(int id) {
		
		try{
			cn = conexion.conectar();
			
			String querySelect = "SELECT * FROM producto WHERE  idProducto = ?";
			
			PreparedStatement declaracionSelect  = cn.prepareStatement(querySelect);
		
			declaracionSelect.setInt(1, id);
			ResultSet resultados = declaracionSelect.executeQuery();
			
			if(resultados.next()) {
				
				System.out.println("Por favor, ingrese los datos correspondientes");
				System.out.println("Nombre: ");
				String nombreProducto = (scanner.nextLine()).trim().replace(" ", "_");
				// .trim para eliminar los espacios en blanco al principio y al final de un String
				//.replace, para reemplazar los espacios blancos por _, y que no rechace con un error el ingreso de espacios
				System.out.println("Categoria: ");
				String categoria = (scanner.nextLine()).trim().replace(" ", "_") ;
				System.out.println("Stock: ");
				int stockDisponible = scanner.nextInt();
				System.out.println("Precio (x unidad): ");
				int precioxUnidad = scanner.nextInt();
				System.out.println("ID del Proveedor: ");
				int proveedor_id = scanner.nextInt();
				
				//ACTUALIZACION
				
				String queryUpdate = "UPDATE producto SET nombreProducto = ?, categoria = ?, stockDisponible = ?, precioxUnidad = ?, proveedor_id = ? WHERE  idProducto = ?";
				
				PreparedStatement declaracionUpdate  = cn.prepareStatement(queryUpdate);
			
				declaracionUpdate.setString(1, nombreProducto);
				declaracionUpdate.setString(2, categoria);
				declaracionUpdate.setInt(3, stockDisponible);
				declaracionUpdate.setInt(4, precioxUnidad);
				declaracionUpdate.setInt(5, proveedor_id);
				
				declaracionUpdate.setInt(6, id);
					
				declaracionUpdate.executeUpdate();
				
				//VER DATOS
				
				while(resultados.next()) { //mientras haya datos por leer
					System.out.println("Proceso exitoso! Datos actualizados: ");
						System.out.println(
								"ID: " + resultados.getInt("idProducto") + ". Nombre: " +resultados.getString("nombreProducto") + 
								". Categoria: " + resultados.getString("categoria") + ". Stock: " + resultados.getInt("stockDisponible") + 
								". Precio: " + resultados.getInt("precioxUnidad") + ". Id Proveedor: " +  resultados.getInt("proveedor_id"));
				}
			}else {
				System.out.println("ID inválido! Vuelva a intentarlo.");
			}	
		} catch(SQLException e){
			e.printStackTrace();
		} 
	}
	
	@Override
	public void Agregar() {
		
		System.out.println("Por favor, ingrese los datos correspondientes");
		System.out.println("Nombre: ");
		String nombreProducto = (scanner.nextLine()).trim().replace(" ", "_");
		// .trim para eliminar los espacios en blanco al principio y al final de un String
		//.replace, para reemplazar los espacios blancos por _, y que no rechace con un error el ingreso de espacios
		System.out.println("Categoria: ");
		String categoria = (scanner.nextLine()).trim().replace(" ", "_") ;
		System.out.println("Stock: ");
		int stockDisponible = scanner.nextInt();
		System.out.println("Precio (x unidad): ");
		int precioxUnidad = scanner.nextInt();
		System.out.println("ID del Proveedor: ");
		int proveedor_id = scanner.nextInt();
		
		try{
			cn = conexion.conectar();
			
			String query = "INSERT INTO producto (nombreProducto, categoria, stockDisponible, precioxUnidad, proveedor_id) VALUES (?, ?, ?, ?, ?)";
			//excluyo el id ya que es autoincremental
			
			PreparedStatement declaracion  = cn.prepareStatement(query);
			
				declaracion.setString(1, nombreProducto);
				declaracion.setString(2, categoria);
				declaracion.setInt(3, stockDisponible);
				declaracion.setInt(4, precioxUnidad);
				declaracion.setInt(5, proveedor_id);
				
				declaracion.executeUpdate();
				
				System.out.println("Datos cargados exitosamente!");
			
		} catch(SQLException e){
			e.printStackTrace();
		} 
	}
	

	@Override
	public void Eliminar(int id){
		
		try{
			cn = conexion.conectar();
			
			String querySelect = "SELECT * FROM producto WHERE  idProducto = ?";
			
			PreparedStatement declaracionSelect  = cn.prepareStatement(querySelect);
		
			declaracionSelect.setInt(1, id);
			ResultSet resultados = declaracionSelect.executeQuery();
				
			if(resultados.next()) { //mientras haya datos por leer
				System.out.println("Seguro que desea eliminar el producto: ");
					System.out.println(
							"ID: " + resultados.getInt("idProducto") + ". Nombre: " +resultados.getString("nombreProducto") + 
							". Categoria: " + resultados.getString("categoria") + ". Stock: " + resultados.getInt("stockDisponible") + 
							". Precio: " + resultados.getInt("precioxUnidad") + ". Id Proveedor: " +  resultados.getInt("proveedor_id"));
					System.out.println("Ingrese si/no segun desee.");
					
					String confirmacion = scanner.nextLine();
					
					 if(confirmacion.equalsIgnoreCase("si")) {
						 
						 try{
							 cn = conexion.conectar();
						
							 String queryDelete = "DELETE FROM producto WHERE idProducto = ?";
							 //excluyo el id ya que es autoincremental
						
								PreparedStatement declaracionDelete  = cn.prepareStatement(queryDelete);
								
								declaracionDelete.setInt(1, id);
								declaracionDelete.executeUpdate();
							
							 	System.out.println("Producto eliminado del sistema!");
						
						 } catch(SQLException e){
							 e.printStackTrace();
						 }
						 
					  }else {
						  System.out.println("Perfecto! El producto no será eliminado");
					  }
			}else {
				System.out.println("ID inválido! Vuelva a intentarlo.");
			}
		}catch(SQLException e){
				e.printStackTrace();
			} 
	}
}
