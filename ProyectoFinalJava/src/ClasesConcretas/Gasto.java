package ClasesConcretas;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import ClasesAbstractas.Transaccion;
import ConexionDB.Conexion;
import Interfaces.*;

public class Gasto extends Transaccion{

	// ATRIBUTOS
	private String destino;
	
	
	//ATRIBUTOS PARA LA CONEXIÓN: 
	Conexion conexion = new Conexion();
	private Connection cn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	
	// CONSTRUCTOR
	public Gasto(String medioDePago, int montoTotal, String destino) {
		super(medioDePago, montoTotal);
		this.destino = destino;
	}

	
	// MÉTODOS: 
	@Override
	public String toString() {
		return "Gastos [destino=" + destino + "]";
	}


	public String getDestino() {
		return destino;
	}



	public void setDestino(String destino) {
		this.destino = destino;
	}
	
	public void generarFactura(Transaccion transaccion, Gasto gasto) {
		try {
			LocalDate fecha = transaccion.getFecha();
			Date fechaSQL = Date.valueOf(fecha);
			int idTransaccion = 0;
			
			
			String query = "INSERT INTO transaccion (fechaDeTransaccion, medioDePago, montoTotal, transaccion_id) VALUES (?, ?, ?, ?)";
			cn = conexion.conectar();
			ps = cn.prepareStatement(query);
			
			ps.setDate(1, fechaSQL);
			ps.setString(2, transaccion.getMedioDePago());
			ps.setFloat(3, transaccion.getMontoTotal());
			
			/* Llamo el id "transaccion_id" de la tabla de transaccion. */
			while(rs.next()) {
				idTransaccion = rs.getInt("idTransaccion");
			}
			
			
		
			String query2 = "INSERT INTO gastos(destino, transaccion_id) VALUES (?, ?)";
			ps = cn.prepareStatement(query2);
			ps.setString(1, gasto.getDestino());
			ps.setInt(2, idTransaccion);
			
			ps.executeUpdate();

			
			System.out.println("Se han insertado los datos correctamente");
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
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
