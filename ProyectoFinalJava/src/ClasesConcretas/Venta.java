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
	
	//ATRIBUTOS PARA LA CONEXIÓN: 
	Conexion conexion = new Conexion();
	private Connection cn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private PreparedStatement ps2 = null;
	private ResultSet rs2 = null;
	private PreparedStatement ps3 = null;
	private ResultSet rs3 = null;
	
	//VARIABLES AUXILIARES. 
	int idTransaccion = 0;
	Date fechaDeTransaccion = null;
	String medioDePago = null;
	float montoTotal = 0;
	int idVentas = 0;
	int idEmpleado = 0;
	int idPersona = 0;
	String nombrePersona = null;
	String apellidoPersona = null;


	// CONSTRUCTOR:
	public Venta(String medioDePago, int montoTotal, Empleado empleado, ArrayList<ProductoVentas>productos){
		super(medioDePago, montoTotal);
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
	public void generarFactura(Venta venta) {
		try {
			// Establecemos conexion con la base de datos.
			cn = conexion.conectar();
			
			/* Pasamos la fecha que se registro al momento de instanciar el objeto a uno de tipo Date 
			 * para que se pueda registrar en la DB. */
			LocalDate fecha = venta.getFecha();
			Date fechaSQL = Date.valueOf(fecha);

			/* Insertamos los datos que vienen con el objeto "venta" que recibe la función por parámetro. */
			String query = "INSERT INTO transaccion (fechaDeTransaccion, medioDePago, montoTotal) VALUES (?, ?, ?)";
			ps = cn.prepareStatement(query);
			
			ps.setDate(1, fechaSQL);
			ps.setString(2, venta.getMedioDePago());
			ps.setFloat(3, venta.getMontoTotal());
			ps.executeUpdate();
			
			
			/* Creo una variable "idTransaccion" para asignarle el valor de la idTransaccion al llamar a la tabla
			 * "transaccion" en la base de datos. Esto con el fin de poder asignarle el valor "transaccion_id" a la 
			 * tabla "venta" y poder generar el enlazamiento de los generales de la Transaccion con la Venta que le pertenece. 
			 * Lo mismo hago con el "idEmpleado", para poder referenciar al Empleado al que pertenece esa venta.*/
			int idTransaccion = 0;
			
			/* Llamo al idTransaccion */
			String query2 = "SELECT idTransaccion FROM transaccion";
			rs = ps.executeQuery(query2);
			while(rs.next()) {
				idTransaccion = rs.getInt(1);
			}
			
			/* Llamo al id del empleado. */ 
			int idEmpleado = 0;
			
			String query3 = "SELECT idEmpleado FROM empleado";
			rs = ps.executeQuery(query3);
			while(rs.next()) {
				idEmpleado = rs.getInt(1);
			}
			
			
			/* Inserto los datos del objeto Gasto en la tabla "ventas" con su respectivo transaccion_id que va a conectarlo con la
			 * Transaccion a la que pertenece. */
			String query4 = "INSERT INTO ventas(empleado_id, transaccion_id) VALUES (?, ?)";
			ps = cn.prepareStatement(query4);
			ps.setInt(1, idEmpleado);
			ps.setInt(2, idTransaccion);
			ps.executeUpdate();

			
			System.out.println("Se han insertado los datos correctamente");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}




	@Override
	public void eliminarFactura(Venta venta) {
		
		
	}


	@Override
	public void buscarFactura(int id) {
		// TODO Auto-generated method stub
		
	}
	
	/* Método axuliar para mostrarFacturas (se puede utilizar sólo o reutilizar en los métodos en donde se precise)*/
	public ArrayList<String> mostrarFacturas() {
		/* Creo un ArrayList en donde se registrarán todos los Gastos existentes en la DB. */
		ArrayList<String>datos = new ArrayList<>();
		
		try {
			/* MOSTRAR DATOS */
			
			cn = conexion.conectar();

			
			/* Llamo a los datos de los Gastos.*/
			String query1 = "SELECT transaccion.idTransaccion,"
					+ "transaccion.fechaDeTransaccion,"
					+ "transaccion.medioDePago,"
					+ "transaccion.montoTotal,"
					+ "ventas.idVentas,"
					+ "ventas.empleado_id "
					+ "FROM transaccion "
					+ "INNER JOIN ventas "
					+ "ON transaccion.idTransaccion = ventas.transaccion_id ";
			
			ps = cn.prepareStatement(query1);
			rs = ps.executeQuery(query1);
			
			/* Leo los datos */
			while (rs.next()) {
				idTransaccion = rs.getInt("idTransaccion");
				fechaDeTransaccion = rs.getDate("fechaDeTransaccion");
				medioDePago = rs.getString("medioDePago");
				montoTotal = rs.getFloat("montoTotal");
				idVentas = rs.getInt("idVentas");
				
				/* Llamo a los datos del empleado asociado a la venta*/
				String query2 = "SELECT empleado.idEmpleado,"
						+ "empleado.persona_id "
						+ "FROM empleado "
						+ "INNER JOIN ventas "
						+ "ON empleado.idEmpleado = ventas.empleado_id";
				
				ps2 = cn.prepareStatement(query2);
				rs2 = ps2.executeQuery(query2);
				
				/* Leo los datos */
				while(rs2.next()) {
					idEmpleado = rs2.getInt("idEmpleado");
					
				}
					
					/* Llamo a los datos de la persona para incluirlos en los datos del empleado*/
					String query3 = "SELECT persona.idPersona,"
							+ "persona.nombrePersona,"
							+ "persona.apellidoPersona "
							+ "FROM persona "
							+ "INNER JOIN empleado "
							+ "ON persona.idPersona = empleado.persona_id";
					ps3 = cn.prepareStatement(query3);
					rs3 = ps3.executeQuery(query3);
					
					/* Leo los datos */
					while(rs3.next()) {
						idPersona = rs3.getInt("idPersona");
						nombrePersona = rs3.getString("nombrePersona");
						apellidoPersona = rs3.getString("apellidoPersona");
						
					}
					
				
				/* Registro los datos en una variable de tipo String para despuyés insertarla en el ArrayList de tipo String.*/
				String cadenaDeDatos = "Sus datos: [ID transaccion=" + idTransaccion 
						+ ", ID venta= " + idVentas 
						+ ", ID empleado=" + idEmpleado
						+ ", ID persona=" + idPersona 
						+ ", nombre completo= " + nombrePersona + " " + apellidoPersona
						+ ", fecha de transaccion =" + fechaDeTransaccion 
						+ ", medioDePago=" + medioDePago 
						+ ", montoTotal=" + montoTotal;
						
				
				/* Inserto en el ArrayList los datos que se obtuvieron de la base de datos. */
					datos.add(cadenaDeDatos);
				
			}
			
			/* Muestro en pantalla los datos obtenidos desde la base de datos. */
			for (String dato : datos) {
				System.out.println(dato);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return datos;
		
	}
	
	
	
}
