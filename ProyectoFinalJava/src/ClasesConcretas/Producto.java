package ClasesConcretas;

import Interfaces.GestionDeDatos;

public class Producto implements GestionDeDatos<Producto>{
	
	//ATRIBUTOS
	private int ID;
	private String nombreProducto;
	private String categoríaProducto;
	private int stockDisponible;
	private Proveedor proveedorDelProducto;
	private int precio;
	

	public Producto(int iD, String nombreProducto, String categoríaProducto, int stockDisponible,
			Proveedor proveedorDelProducto, int precio) {
		super();
		ID = iD;
		this.nombreProducto = nombreProducto;
		this.categoríaProducto = categoríaProducto;
		this.stockDisponible = stockDisponible;
		this.proveedorDelProducto = proveedorDelProducto;
		this.precio = precio;
	}


	@Override
	public String toString() {
		return "Producto [ID=" + ID + ", nombreProducto=" + nombreProducto + ", categoríaProducto=" + categoríaProducto
				+ ", stockDisponible=" + stockDisponible + ", proveedorDelProducto=" + proveedorDelProducto
				+ ", precio=" + precio + "]";
	}

	
	//METODOS
	
	public void verStockDeProducto(Producto producto) {
	
	}

	public void calcularStock(Producto producto) {
	
	}

	//OVERRIDE

	@Override
	public void Agregar(Producto producto) {
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
