package ClasesConcretas;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import ClasesAbstractas.Transaccion;
import ConexionDB.Conexion;
import Interfaces.GestionDeFacturas;

public class Venta extends Transaccion implements GestionDeFacturas<Venta>{

	// ATRIBUTOS:
	private Empleado empleado;
	private ArrayList<ProductoVentas>productos;

	// CONSTRUCTOR:
	public Venta(String medioDePago, int montoTotal, Empleado empleado, ArrayList<ProductoVentas>productos){
		super(medioDePago, montoTotal);
		this.empleado = empleado;
		this.productos = productos;
	}
	
	//ATRIBUTOS PARA LA CONEXIÓN: 
	Conexion conexion = new Conexion();
	private Connection cn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	

	
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
	public void generarFactura(Venta venta) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void verInfoFactura(Venta venta) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void eliminarFactura(Venta venta) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void buscarFactura(int id) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
}
