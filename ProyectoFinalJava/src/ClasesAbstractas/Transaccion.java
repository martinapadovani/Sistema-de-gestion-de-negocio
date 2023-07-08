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

public class Transaccion{
	
	// ATRIBUTOS:
	private int id;
	private LocalDate fecha;
	private String medioDePago;
	private int montoTotal;
	private String destino;
	
	
	private Date fechaDeTransaccion = null;	
	private int idTransaccion = 0;
	private String idEmpleado = null;
	//ATRIBUTOS PARA LA CONEXIÓN: 
	Conexion conexion = new Conexion();
	private Connection cn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	
	// CONSTRUCTOR:
	public Transaccion() {
		
	}
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

	
	public void mostrarTransacciones() {
		try {
			cn = conexion.conectar();
			String query1 = "SELECT transaccion.idTransaccion,"
					+ "transaccion.fechaDeTransaccion,"
					+ "transaccion.medioDePago,"
					+ "transaccion.montoTotal,"
					+ "ventas.idVentas,"
					+ "ventas.empleado_id "
					+ "FROM transaccion "
					+ "INNER JOIN ventas "
					+ "ON transaccion.idTransaccion = ventas.transaccion_id";
			String query2 = "SELECT transaccion.idTransaccion,"
					+ "transaccion.fechaDeTransaccion,"
					+ "transaccion.medioDePago,"
					+ "transaccion.montoTotal,"
					+ "gastos.destino,"
					+ "gastos.transaccion_id "
					+ "FROM transaccion "
					+ "INNER JOIN gastos "
					+ "ON transaccion.idTransaccion = gastos.transaccion_id";
			
			ps = cn.prepareStatement(query1);
			rs = ps.executeQuery();
	
			ArrayList<String>datos = new ArrayList<>();
			String datosString = null;
			
			while(rs.next()) {
				idTransaccion = rs.getInt("idTransaccion");
				idEmpleado = rs.getString("empleado_id");
				fechaDeTransaccion = rs.getDate("fechaDeTransaccion");
				medioDePago = rs.getString("medioDePago");
				montoTotal = rs.getInt("montoTotal");
				
						datosString = "Datos de la venta:\n" 
						+ "ID transaccion: " + idTransaccion + "\n" 
						+ "Monto total: " + montoTotal + "\n" 
						+ "Medio de pago: " + medioDePago + "\n" 
						+ "Fecha de transaccion: " + fechaDeTransaccion +"\n"
						+ "ID empleado: " + idEmpleado + "\n"
						+ "Tipo de transacción: Venta" + "\n";
				
				datos.add(datosString);
			}
			
			ps = cn.prepareStatement(query2);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				idTransaccion = rs.getInt("idTransaccion");
				fechaDeTransaccion = rs.getDate("fechaDeTransaccion");
				destino = rs.getString("destino");
				
				datosString = "Datos de la venta:\n"
						+ "ID transaccion: " + idTransaccion + "\n"
						+ "Destino: " + destino + "\n" 
						+ "Fecha de transaccion: " + fechaDeTransaccion + "\n"
						+ "Tipo de transaccion: Gasto" + "\n";
				
				datos.add(datosString);
			}
			
			
			if(datos.isEmpty() == false)  {
				for(String dato: datos) {
					System.out.println(dato);
				}
			} else {
				System.out.println("No hay transacciones registradas en el sistema!");
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	
}
