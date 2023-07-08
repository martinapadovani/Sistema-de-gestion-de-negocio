package ClasesConcretas;

public class ProductoVentas extends Producto{

	private int cantidad;

	public ProductoVentas(int id, String nombreProducto, String categoriaProducto, int stockDisponible,
			Proveedor proveedorDelProducto, int precio, int cantidad) {
		super();
		this.cantidad = cantidad;
	}

	
	
}
