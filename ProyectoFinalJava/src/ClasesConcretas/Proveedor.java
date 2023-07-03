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
}
