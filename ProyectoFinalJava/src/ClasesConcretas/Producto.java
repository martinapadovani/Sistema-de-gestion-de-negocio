package ClasesConcretas;

import Interfaces.GestionDeDatos;

public class Producto implements GestionDeDatos{
	
	//ATRIBUTOS
	private int ID;
	private String nombreProducto;
	private String categoríaProducto;
	private int stockDisponible;
	private Proveedor proveedorDelProducto;
	private int precio;


	//METODOS

	public void verStockDeProducto(Producto producto) {
	
	}

	public void calcularStock(Producto producto) {
	
	}

	//OVERRIDE

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
	
	

	//GETTERS-SETTERS

	public int getID() {
		return ID;
	}
	
	public void setID(int iD) {
		ID = iD;
	}
	
	public String getNombreProducto() {
		return nombreProducto;
	}
	
	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}
	
	public String getCategoríaProducto() {
		return categoríaProducto;
	}
	
	public void setCategoríaProducto(String categoríaProducto) {
		this.categoríaProducto = categoríaProducto;
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
