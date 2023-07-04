package ClasesConcretas;
import ClasesAbstractas.Transaccion;

public class Venta extends Transaccion{

	// ATRIBUTOS:
	private Empleado empleado;
	private Producto producto;
	private int cantidad; 

	// CONSTRUCTOR:
	public Venta(int id, int fecha, String medioDePago, int montoTotal, Empleado empleado, Producto producto,
			int cantidad) {
		super(id, fecha, medioDePago, montoTotal);
		this.empleado = empleado;
		this.producto = producto;
		this.cantidad = cantidad;
	}

	
	// MÉTODOS: 
	@Override
	public String toString() {
		return "Venta [empleado=" + empleado + ", producto=" + producto + ", cantidad=" + cantidad + "]";
	}
	
	
	// GETTERS-SETTERS:
	
	public Empleado getEmpleado() {
		return empleado;
	}


	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}


	public Producto getProducto() {
		return producto;
	}


	public void setProducto(Producto producto) {
		this.producto = producto;
	}


	public int getCantidad() {
		return cantidad;
	}


	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	@Override 
	public void generarFactura(Transaccion transaccion) {
		
	}
	
	@Override 
	public void buscarFactura(int id) {
		
	}
	
	@Override
	public void verInfoFactura(Transaccion transaccion) {
		
	}
	
	@Override 
	public void eliminarFactura(Transaccion transaccion) {
		
	}
	
	
	
	
}
