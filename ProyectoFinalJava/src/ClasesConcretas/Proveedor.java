package ClasesConcretas;

import java.util.ArrayList;

import Interfaces.GestionDeDatos;

public class Proveedor implements GestionDeDatos<Proveedor>{

	//ATRIBUTOS
	private int id;
	private String nombreProveedor;
	private int telefono;
	private ArrayList<Producto>productosProveedor;
	private int precioProductoXUnidad;
	
	//CONSTRUCTOR
	
	public Proveedor(int id, String nombreProveedor, int telefono, int precioProductoXUnidad) {
		super();
		this.id = id;
		this.nombreProveedor = nombreProveedor;
		this.telefono = telefono;
		this.precioProductoXUnidad = precioProductoXUnidad;
	}
	
	
	@Override
	public String toString() {
		return "Proveedor [id=" + id + ", nombreProveedor=" + nombreProveedor + ", telefono=" + telefono
				+ ", productosProveedor=" + productosProveedor + ", precioProductoXUnidad=" + precioProductoXUnidad
				+ "]";
	}

	
	//OVERRIDES

	@Override
	public void Agregar(Proveedor proveedor) {
	}
	
	@Override
	public void Buscar(int ID) {
	}
	
	@Override
	public void Actualizar(Proveedor proveedor) {
	}
	
	@Override
	public void Eliminar(Proveedor proveedor) {
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

	public int getPrecioProductoXUnidad() {
		return precioProductoXUnidad;
	}

	public void setPrecioProductoXUnidad(int precioProductoXUnidad) {
		this.precioProductoXUnidad = precioProductoXUnidad;
	}
	
	
}
