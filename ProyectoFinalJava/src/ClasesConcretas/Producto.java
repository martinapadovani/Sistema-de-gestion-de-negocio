package ClasesConcretas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import ConexionDB.Conexion;
import Interfaces.GestionDeDatos;

public class Producto implements GestionDeDatos<Producto>{
	
	//ATRIBUTOS DE CLASE
	private String nombreProducto;
	private String categoriaProducto;
	private int stockDisponible;
	private Proveedor proveedorDelProducto;
	private int precio;
	private int id; 
	
	//ATRIBUTOS PARA CONEXION
	Conexion conexion = new Conexion();
	private Connection cn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;


	public Producto(int id, String nombreProducto, String categoriaProducto, int stockDisponible,
			Proveedor proveedorDelProducto, int precio) {
		this.id = id;
		this.nombreProducto = nombreProducto;
		this.categoriaProducto = categoriaProducto;
		this.stockDisponible = stockDisponible;
		this.proveedorDelProducto = proveedorDelProducto;
		this.precio = precio;
	}


	@Override
	public String toString() {
		return "Producto [nombreProducto=" + nombreProducto + ", categoríaProducto=" + categoriaProducto
				+ ", stockDisponible=" + stockDisponible + ", proveedorDelProducto=" + proveedorDelProducto.getNombreProveedor()
				+ ", precio=" + precio + "]";
	}

	
	//METODOS
	
	public void verStockDeProducto(Producto producto) {
		
		try{
			cn = conexion.conectar();
	
			
			String nombreProducto = producto.getNombreProducto();
			
			ps = cn.prepareStatement("SELECT stockDisponible FROM producto WHERE nombreProducto =" + nombreProducto );
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				 stockDisponible += rs.getInt("stockDisponible");	
			}
			
			System.out.println("El stock de" + nombreProducto + "es de: " + stockDisponible);

		} catch(SQLException e){
			e.printStackTrace();
		}

	}

	public void calcularStock(Producto producto) {
	
	}

	//OVERRIDE
	
	@Override
	public void Agregar(Producto producto) {
		try{
			
			cn = conexion.conectar();
			/* No se agrega el dato "id" ya que es auto_incremental directamente del workbench. 
			 * Tampoco se agrega el dato proveedorDelProducto ya que traducido a MySQL este será una llave foranea que contendrá
			 * el id de ese proveedor. */
			String query = "INSERT INTO producto (nombreProducto, categoria, stockDisponible, precioxUnidad) VALUES (?, ?, ?, ?)";
			ps = cn.prepareStatement(query);
			
			ps.setString(1, producto.getNombreProducto());
			ps.setString(2, producto.getCategoríaProducto());
			ps.setInt(3, producto.getStockDisponible());
			ps.setInt(4, producto.getPrecio());
			
			ps.executeUpdate();
			
			System.out.println("Se han insertado los datos correctamente");
			
			
		} catch(SQLException e){
			e.printStackTrace();
		}
			
	}	

	@Override
	public void Buscar(int ID) {
	}

	@Override
	public void Actualizar(Producto producto) {
	}

	@Override
	public void Eliminar(Producto producto){
	}
	
	

	//GETTERS-SETTERS
	
	public String getNombreProducto() {
		return nombreProducto;
	}
	
	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}
	
	public String getCategoríaProducto() {
		return categoriaProducto;
	}
	
	public void setCategoríaProducto(String categoriaProducto) {
		this.categoriaProducto = categoriaProducto;
	}
	
	public int getStockDisponible() {
		return stockDisponible;
	}
	
	public void setStockDisponible(int stockDisponible) {
		this.stockDisponible = stockDisponible;
	}
	
	public Proveedor getProveedorDelProducto() {
		return proveedorDelProducto;
	}
	
	public void setProveedorDelProducto(Proveedor proveedorDelProducto) {
		this.proveedorDelProducto = proveedorDelProducto;
	}
	
	public int getPrecio() {
		return precio;
	}
	
	public void setPrecio(int precio) {
		this.precio = precio;
	}





}
