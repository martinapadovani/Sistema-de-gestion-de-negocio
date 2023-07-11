package ClasesConcretas;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

import ClasesAbstractas.Transaccion;
import ConexionDB.Conexion;
import Interfaces.GestionDeFacturas;

public class Venta extends Transaccion implements GestionDeFacturas<Venta>{

	// ATRIBUTOS:
	private Empleado empleado;
	ArrayList<String>productos = new ArrayList<>();
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
	Scanner sc = new Scanner(System.in);
	int idTransaccion = 0;
	Date fechaDeTransaccion = null;
	String medioDePago = null;
	float montoTotal = 0;
	int idVentas = 0;
	int idEmpleado = 0;
	int idPersona = 0;
	String nombrePersona = null;
	String apellidoPersona = null;
	int producto = 0;

	// Variables auxiliares para productosVentas:
	int idProducto = 0;
	String nombreProducto = null;
	int stockDisponible = 0;
	int precioxUnidad = 0;
	AtomicBoolean flag = new AtomicBoolean(true);
	int opcion = 0;
	int idProductoAEnlazar = 0;
	int ventaId = 0;
	int productoIdEnlazado = 0;


	// CONSTRUCTOR:
	public Venta(String medioDePago, int montoTotal, Empleado empleado, ArrayList<String>productos){
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


	public ArrayList<String> getProducto() {
		return productos;
	}


	public void setProducto(ArrayList<String>productos) {
		this.productos = productos;
	}



	@Override
	public void generarFactura() {
		try {
			// Establecemos conexion con la base de datos.
			cn = conexion.conectar();
			
			/* Pasamos la fecha que se registro al momento de instanciar el objeto a uno de tipo Date 
			 * para que se pueda registrar en la DB. */
			LocalDate fecha = LocalDate.now();
			Date fechaSQL = Date.valueOf(fecha);
			Empleado ejecutarMetodosEmpleado = new Empleado();
			
			System.out.println("Ingrese los datos de la venta:");
			
			System.out.println("Seleccione el ID del empleado que realizó la venta: ");
			System.out.println("Empleados registrados: ");
			ejecutarMetodosEmpleado.Ver();
			idEmpleado = sc.nextInt();
			
			System.out.println("Medio de pago: ");
			medioDePago = sc.nextLine();
			medioDePago = sc.nextLine();
			
			System.out.println("Monto total: ");
			montoTotal = sc.nextFloat();

			/* Insertamos los datos que vienen con el objeto "venta" que recibe la función por parámetro. */
			String query = "INSERT INTO transaccion (fechaDeTransaccion, medioDePago, montoTotal) VALUES (?, ?, ?)";
			ps = cn.prepareStatement(query);
			
			ps.setDate(1, fechaSQL);
			ps.setString(2, medioDePago);
			ps.setFloat(3, montoTotal);
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

			/* Inserto los datos del objeto Gasto en la tabla "ventas" con su respectivo transaccion_id que va a conectarlo con la
			 * Transaccion a la que pertenece. */
			String query4 = "INSERT INTO ventas(empleado_id, transaccion_id) VALUES (?, ?)";
			ps = cn.prepareStatement(query4);
			ps.setInt(1, idEmpleado);
			ps.setInt(2, idTransaccion);
			ps.executeUpdate();
			
			
			String query5 = "SELECT idVentas from ventas";
			
			int idVentas = 0;
			rs = ps.executeQuery(query5);
			while(rs.next()) {
				idVentas = rs.getInt(1);
			}
			
			/* Enlazar los productos */ 
			
			/* Primero, creamos llamado a la tabla de "producto" para obtener los datos que contiene.*/
			System.out.println("Productos de la venta: ");
			String queryProductos = "SELECT * FROM producto"; 
			rs = ps.executeQuery(queryProductos);
			
			/* Creamos un nuevo array en donde se ingresaran los datos provenientes de la DB de forma local para 
			 * poder mostrarlos más adelante. */
			ArrayList<String>productos = new ArrayList<>();
			
			flag.set(true);
			
			while(flag.get()) {
				
			while(rs.next()) {
				idProducto = rs.getInt("idProducto");
				nombreProducto = rs.getString("nombreProducto");
				stockDisponible = rs.getInt("stockDisponible");
				precioxUnidad = rs.getInt("precioxUnidad");
				
				String agregarProductoALista = "[ID PRODUCTO= " + idProducto 
						+ ", NOMBRE PRODUCTO= " + nombreProducto 
						+ ", STOCK DISPONIBLE= " + stockDisponible 
						+ ", PRECIO X UNIDAD= " + precioxUnidad;
				
				/* Insertamos los productos dentro de un ArrayList, para luego poder mostrarlos al usuario. */
				productos.add(agregarProductoALista);
			}
			
			/* Mostramos los productos disponibles con sus atributos, con la finalidad de que el usuario pueda elegir por id
			 * los productos que el empleado desea agregar a la venta. */
			for(String producto : productos) {
				System.out.println(producto);
			}
			
			/* Reseteamos el valor del AtomicBoolean para que el ciclo, en el próximo llamado que se haga, comience
			 * correctamente. */
		
				
				System.out.println("Seleccione el ID del producto que desea enlazar a la venta: ");
				idProductoAEnlazar = sc.nextInt();
				
				String query6 = "INSERT INTO productoVenta(venta_id, producto_id) VALUES (?, ?)";
				ps = cn.prepareStatement(query6);
				ps.setInt(1, idVentas);
				ps.setInt(2, idProductoAEnlazar);
				ps.executeUpdate();
				
				String query7 = "SELECT stockDisponible FROM producto WHERE idProducto= ?";
				ps = cn.prepareStatement(query7);
				ps.setInt(1, idProductoAEnlazar);
				rs = ps.executeQuery();
				
				int stockActualizado = 0;
				if(rs.next()) {
					stockActualizado = rs.getInt("stockDisponible") - 1;
				}
				
				String query8 = "UPDATE producto SET stockDisponible = ? WHERE idProducto = ?";
				ps = cn.prepareStatement(query8);
				ps.setInt(1, stockActualizado);
				ps.setInt(2, idProductoAEnlazar);
				ps.executeUpdate();
				
				System.out.println("Desea agregar otro producto a la venta?");
				System.out.println("1. SI");
				System.out.println("0. NO");
				opcion = sc.nextInt();
				
				if(opcion == 1) {
					flag.set(true);
				} else if(opcion == 0) {
					flag.set(false);
				}
				
			}
			
			System.out.println("Se han insertado los datos correctamente");
			
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}




	@Override
	public void eliminarFactura() {
		
		try {
			
			cn = conexion.conectar();
			
			ArrayList<String> datos = mostrarVentas();
			int idSeleccionadorTransaccion = 0;
			int idSeleccionadorVentas = 0;
			
			if(datos.isEmpty() == false) {
				System.out.println("Ingrese el ID de la transaccion a eliminar:");
				System.out.print("Su opcion: ");
				idSeleccionadorTransaccion = sc.nextInt();
				
				System.out.println("Ingrese el ID de la venta a eliminar: ");
				idSeleccionadorVentas = sc.nextInt();
				
				String query1 = "DELETE from ventas WHERE transaccion_id = "+idSeleccionadorTransaccion;
				String query2 = "DELETE from transaccion WHERE idTransaccion ="+idSeleccionadorTransaccion;
				String query3 = "DELETE from productoVenta WHERE venta_id ="+idSeleccionadorVentas ;
				
				ps = cn.prepareStatement(query3);
				ps.executeUpdate();
				
				ps = cn.prepareStatement(query1);
				ps.executeUpdate();
				
				ps = cn.prepareStatement(query2);
				ps.executeUpdate();
				
				
				System.out.println("La venta seleccionada ha sido eliminada con éxito!");
				
				/* Verificamos si no hay registros en la tabla antes de restablecer sus valores de ID a 0*/
				
				/* Sumamos las ventas en un atributo propio de SQL al que llamamos ventas, para verificar si existen ventas o no. */
				String verificarRegistrosQueryVentas = "SELECT COUNT(*) as total FROM ventas";
				ps = cn.prepareStatement(verificarRegistrosQueryVentas);
				rs = ps.executeQuery();
				
				/* Ejecutamos un condicional para verificar si hay resultados de la consulta. */
				if(rs.next()) {
					
					/* Registramos el total de ventas que existen en la DB */
					int totalRegistrosVentas = rs.getInt("total");
					
					/* Por lo tanto, si ese total nos da igual a 0, significa que no hay registros de ventas por lo que actualizará el valor 
					 * del auto incremento del ID a 0. */
					if(totalRegistrosVentas == 0) {
						String actualizarIDVentas = "ALTER TABLE ventas AUTO_INCREMENT = 1";
						
						ps = cn.prepareStatement(actualizarIDVentas);
						ps.executeUpdate();
					}
				}
				
				String verificarRegistrosQueryTransaccion = "SELECT COUNT(*) as total FROM transaccion";
				ps = cn.prepareStatement(verificarRegistrosQueryTransaccion);
				rs = ps.executeQuery();
				
				if(rs.next()) {
					int totalRegistrosTransaccion = rs.getInt("total");
					
					if(totalRegistrosTransaccion == 0) {
						String actualizarIDTransaccion = "ALTER TABLE transaccion AUTO_INCREMENT = 1";
						
						ps = cn.prepareStatement(actualizarIDTransaccion);
						ps.executeUpdate();
					}
				}
			}	
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}


	@Override
	public void buscarFactura() {
		
		ArrayList<String> datos = mostrarVentas();
		
		try {
			cn = conexion.conectar();
			
			if(datos.isEmpty() == false) {
				System.out.println("Ingrese el id de la venta que desea buscar: ");
				int id = sc.nextInt();
				
				String query = "SELECT transaccion.idTransaccion,"
						+ "transaccion.fechaDeTransaccion,"
						+ "transaccion.medioDePago,"
						+ "transaccion.montoTotal,"
						+ "ventas.idVentas,"
						+ "ventas.empleado_id,"
						+ "ventas.transaccion_id "
						+ "FROM transaccion "
						+ "INNER JOIN ventas "
						+ "ON transaccion.idTransaccion = ventas.transaccion_id "
						+ "INNER JOIN empleado "
						+ "ON ventas.empleado_id = empleado.idEmpleado "
						+ "WHERE transaccion.idTransaccion = " + id;

				ps = cn.prepareStatement(query);
				ps.executeQuery();
				
				rs = ps.executeQuery(query);
				/* Obtenemos los datos desde la DB y los almacenamos en sus correspondientes variables.*/
				while(rs.next()) {
					idTransaccion = rs.getInt("idTransaccion");
					fechaDeTransaccion = rs.getDate("fechaDeTransaccion");
					idVentas = rs.getInt("idVentas");
					idEmpleado = rs.getInt("empleado_id");
					montoTotal = rs.getFloat("montoTotal");
					medioDePago = rs.getString("medioDePago");
				}
				
				System.out.println("Datos de la venta:\n" 
				+ "ID TRANSACCION: " + idTransaccion + "\n" 
				+ "ID VENTA: " + idVentas + "\n" 
				+ "ID ID EMPLEADO: " + idEmpleado + "\n" 
				+ "MONTO TOTAL: " + montoTotal + "\n" 
				+ "MEDIO DE PAGO: " + medioDePago + "\n" 
				+ "FECHA TRANSACCION: " + fechaDeTransaccion);
				
			} 
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	
		
	}
	
	/* Método para mostrar las ventas en el sistema. */
	public ArrayList<String> mostrarVentas() {
		/* Creo un ArrayList en donde se registrarán todos las Ventas existentes en la DB, para luego reutilizarlos. */
		ArrayList<String>datos = new ArrayList<>();
		
		try {
			/* MOSTRAR DATOS */
			cn = conexion.conectar();
			
			/* Llamo a los datos de las Ventas.*/
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
				String cadenaDeDatos = "[ID TRANSACCION=" + idTransaccion 
						+ ", ID VENTA= " + idVentas 
						+ ", ID EMPLEADO=" + idEmpleado
						+ ", ID PERSONA=" + idPersona 
						+ ", NOMBRE COMPLETO= " + nombrePersona + " " + apellidoPersona
						+ ", FECHA TRANSACCION=" + fechaDeTransaccion 
						+ ", MEDIO DE PAGO=" + medioDePago 
						+ ", MONTO TOTAL=" + montoTotal;
						
				
				/* Inserto en el ArrayList los datos que se obtuvieron de la base de datos. */
					datos.add(cadenaDeDatos);
					
					}
			
				if(datos.isEmpty() == false) {
					/* Si hay datos disponibles en la DB, muestro en pantalla los datos obtenidos.*/
					System.out.println("Ventas registradas en el sistema: ");
					
					for (String dato : datos) {
						System.out.println(dato);
					}
				} else {
					System.out.println("No hay ventas registradas en el sistema!");

				}

				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return datos;	
	}
}
	

