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
	
	/* Al intentar overridear con el tipo de dato "Factura" únicamente, hay ciertos parámetros pertenecientes a Gasto
	 * como "getDestino" que no se podrían llamar. Por lo tanto, el tipo de dato que recibe generarFactura en este caso sería
	 * Gasto, para poder traer consigo todo lo de "Factura" y a su vez incorporar los atributos de "Gasto".
	 * Si yo quisiera implementar la interfaz directamente en esta clase "Gasto" se podría hacer únicamente si saco la implementación
	 * del objeto "Transaccion". 
	 * La implementación del método "generarFactura" en el objeto "Transaccion" tal vez no sería necesaria ya que este se va a aplicar 
	 * desde las clases hijas (Gasto y Venta), trayendo consigo todo lo que pertenezca a Transaccion. 
	 * Otra opción podría ser crear métodos abstractos desde la clase "Transaccion" e incorporarlos en estas clases hijas. */
	
	public void generarFactura(Gasto gasto) {
		try {
			// Establecemos conexion con la base de datos.
			cn = conexion.conectar();
			
			/* Pasamos la fecha que se registro al momento de instanciar el objeto a uno de tipo Date 
			 * para que se pueda registrar en la DB. */
			LocalDate fecha = gasto.getFecha();
			Date fechaSQL = Date.valueOf(fecha);

			/* Insertamos los datos que vienen con el objeto "gasto" que recibe la función por parámetro. */
			String query = "INSERT INTO transaccion (fechaDeTransaccion, medioDePago, montoTotal) VALUES (?, ?, ?)";
			ps = cn.prepareStatement(query);
			
			ps.setDate(1, fechaSQL);
			ps.setString(2, gasto.getMedioDePago());
			ps.setFloat(3, gasto.getMontoTotal());
			
			ps.executeUpdate();
			
			
			/* Creo una variable "idTransaccion" para asignarle el valor de la idTransaccion al llamar a la tabla
			 * "transaccion" en la base de datos. Esto con el fin de poder asignarle el valor "transaccion_id" a la 
			 * tabla "gastos" y poder generar el enlazamiento de los generales de la Transaccion con el Gasto que le pertenece. */
			int idTransaccion = 0;
			
			/* Llamo al idTransaccion */

		
			/* Inserto los datos del objeto Gasto en la tabla "gastos" con su respectivo transaccion_id que va a conectarlo con la
			 * Transaccion a la que pertenece. */
			String query3 = "INSERT INTO gastos(destino, transaccion_id) VALUES (?, ?)";
			ps = cn.prepareStatement(query3);
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
