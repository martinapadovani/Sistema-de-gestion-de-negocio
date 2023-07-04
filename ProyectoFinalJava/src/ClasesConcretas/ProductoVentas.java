package ClasesConcretas;

public class ProductoVentas extends Producto{

	private int cantidad;

	public ProductoVentas(int iD, String nombreProducto, String categoríaProducto, int stockDisponible,
			Proveedor proveedorDelProducto, int precio, int cantidad) {
		super(iD, nombreProducto, categoríaProducto, stockDisponible, proveedorDelProducto, precio);
		this.cantidad = cantidad;
	}
	
	
}
