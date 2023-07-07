package ClasesAbstractas;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import ConexionDB.Conexion;
import Interfaces.GestionDeFacturas;

public abstract class Transaccion{
	
	// ATRIBUTOS:
	private int id;
	private LocalDate fecha;
	private String medioDePago;
	private int montoTotal;


	
	// CONSTRUCTOR:
	public Transaccion(String medioDePago, int montoTotal) {
		this.id = id;
		this.fecha = LocalDate.now();
		this.medioDePago = medioDePago;
		this.montoTotal = montoTotal;
	}
	
	// MÉTODOS: 
	@Override
	public String toString() {
		return "Transaccion [id=" + id + ", fecha=" + fecha + ", medioDePago=" + medioDePago + ", montoTotal="
				+ montoTotal + "]";
	}
	
	/* 
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
	*/
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
