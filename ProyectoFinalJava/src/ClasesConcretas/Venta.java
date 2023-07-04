package ClasesConcretas;
import java.time.LocalDate;
import java.util.ArrayList;

import ClasesAbstractas.Transaccion;

public class Venta extends Transaccion{

	// ATRIBUTOS:
	private Empleado empleado;
	private ArrayList<ProductoVentas>productos;

	// CONSTRUCTOR:
	public Venta(int id, LocalDate fecha, String medioDePago, int montoTotal, Empleado empleado, ArrayList<ProductoVentas>productos){
		super(id, fecha, medioDePago, montoTotal);
		this.empleado = empleado;
		this.productos = productos;
	}
	

	
	// MÉTODOS: 
	@Override
	public String toString() {
		return "Venta [empleado=" + empleado + ", productos=" + productos +  "]";
	}
	
	
	// GETTERS-SETTERS:
	
	public Empleado getEmpleado() {
		return empleado;
	}


	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}


	public ArrayList<ProductoVentas> getProducto() {
		return productos;
	}


	public void setProducto(ArrayList<ProductoVentas>productos) {
		this.productos = productos;
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
