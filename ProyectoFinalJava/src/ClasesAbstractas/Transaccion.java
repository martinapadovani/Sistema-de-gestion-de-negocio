package ClasesAbstractas;
import java.time.LocalDate;

import Interfaces.*;

public abstract class Transaccion implements GestionDeFacturas<Transaccion>{
	
	// ATRIBUTOS:
	private int id;
	private LocalDate fecha;
	private String medioDePago;
	private int montoTotal;
	
	// CONSTRUCTOR:
	public Transaccion(int id, LocalDate fecha2, String medioDePago, int montoTotal) {
		this.id = id;
		this.fecha = fecha2;
		this.medioDePago = medioDePago;
		this.montoTotal = montoTotal;
	}
	
	// MÉTODOS: 
	@Override
	public String toString() {
		return "Transaccion [id=" + id + ", fecha=" + fecha + ", medioDePago=" + medioDePago + ", montoTotal="
				+ montoTotal + "]";
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
	
	// Getters y setters:
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public String getMedioDePago() {
		return medioDePago;
	}

	public void setMedioDePago(String medioDePago) {
		this.medioDePago = medioDePago;
	}

	public int getMontoTotal() {
		return montoTotal;
	}

	public void setMontoTotal(int montoTotal) {
		this.montoTotal = montoTotal;
	}

	
	
	
	
	
}
