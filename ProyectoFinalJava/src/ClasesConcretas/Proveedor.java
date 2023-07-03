package ClasesConcretas;

import Interfaces.GestionDeDatos;

public class Proveedor implements GestionDeDatos{

	//ATRIBUTOS
	private int id;
	private String nombreProveedor;
	private int telefono;
	//private ArrayList<Producto>productosProveedor;
	private int precioProductoXUnidad;
	
	//CONSTRUCTOR
	
	public Proveedor(int id, String nombreProveedor, int telefono, int precioProductoXUnidad) {
		super();
		this.id = id;
		this.nombreProveedor = nombreProveedor;
		this.telefono = telefono;
		this.precioProductoXUnidad = precioProductoXUnidad;
	}

	//OVERRIDES
	
	@Override
	public void Agregar() {
	}
	
	@Override
	public void Buscar(int ID) {
	}
	
	@Override
	public void Actualizar() {
	}
	
	@Override
	public void Eliminar() {
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
