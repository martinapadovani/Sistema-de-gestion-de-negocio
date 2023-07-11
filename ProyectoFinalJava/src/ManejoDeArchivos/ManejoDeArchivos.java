package ManejoDeArchivos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import ConexionDB.Conexion;

public class ManejoDeArchivos {
	
	Scanner sc = new Scanner(System.in);
	String nombreArchivo = null;
	
	//ATRIBUTOS DB:
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
	
	public ManejoDeArchivos() {
		
	}
	public void crearYEscribirArchivo() {
		
		cn = conexion.conectar();
		
		System.out.println("Nombre de su factura:");
		

		String carpetaDestino = "facturas";
		
		File carpeta = new File(carpetaDestino);
		if(!carpeta.exists()) {
			carpeta.mkdirs();
		}
		
		nombreArchivo = sc.nextLine().trim().replace(" ", "_").concat(".txt");
		
		String rutaCompletaDelArchivo = carpetaDestino + "/" + nombreArchivo;
		
		String rutaAbsoluta = carpeta.getAbsolutePath();
		
		File file = new File(rutaCompletaDelArchivo);	

		
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
		
		try {
			ps = cn.prepareStatement(query1);
			rs = ps.executeQuery();

		ArrayList<String>datos = new ArrayList<>();
		String datosStringParaMostrar = null;
		String datosStringParaImprimir = null;
		
		// OBTENER DATOS DE VENTAS:
		while(rs.next()) {
			idTransaccion = rs.getInt("idTransaccion");
			idEmpleado = rs.getString("empleado_id");
			fechaDeTransaccion = rs.getDate("fechaDeTransaccion");
			medioDePago = rs.getString("medioDePago");
			montoTotal = rs.getInt("montoTotal");
			
					datosStringParaMostrar = "Datos de la transacción:[" 
					+ "ID transaccion: " + idTransaccion
					+ ", monto total: " + montoTotal
					+ ", medio de pago: " + medioDePago
					+ ", fecha de transaccion: " + fechaDeTransaccion
					+ ", ID empleado: " + idEmpleado
					+ "Tipo de transacción: Venta]";
					
					datosStringParaImprimir = "Datos de la transacción: \n"
							+ "- ID transaccion: " + idTransaccion + "\n"
							+ "- Monto total: " + "$" + montoTotal  + "\n"
							+ "- Medio de pago: " + medioDePago + "\n" 
							+ "- Fecha de transaccion: " + fechaDeTransaccion + "\n"
							+ "- ID empleado: " + idEmpleado + "\n"
							+ "- Tipo de transaccion: Venta";
			
			datos.add(datosStringParaMostrar);
		}
		
		ps = cn.prepareStatement(query2);
		rs = ps.executeQuery();
		
		// OBTENER DATOS DE GASTOS:
		while(rs.next()) {
			idTransaccion = rs.getInt("idTransaccion");
			fechaDeTransaccion = rs.getDate("fechaDeTransaccion");
			destino = rs.getString("destino");
			
			datosStringParaMostrar = "Datos de la transaccion:["
					+ "ID transaccion: " + idTransaccion 
					+ ", destino: " + destino 
					+ ", fecha de transaccion: " + fechaDeTransaccion
					+ ", tipo de transaccion: Gasto]";
			
			datosStringParaImprimir = "Datos de la transacción: \n"
					+ "- ID transaccion: " + idTransaccion + "\n"
					+ "- Destino: " + destino  + "\n"
					+ "- Fecha de transaccion: " + fechaDeTransaccion + "\n"
					+ "- Tipo de transaccion: Gasto";
			
			datos.add(datosStringParaMostrar);
		}
		
		System.out.println("Seleccione la transacción para generar factura por ID: ");
		
		for(String datoTransaccion : datos) {
			
			System.out.println(datoTransaccion);
			
		}
		
		int idSeleccionado = sc.nextInt();
		
		try {
			
			String query = "SELECT * FROM transaccion WHERE idTransaccion = ?";
			ps = cn.prepareStatement(query);
			ps.setInt(1, idSeleccionado);
			
			if(!file.exists()) {
				file.createNewFile();
			}
			
			FileWriter escritor = new FileWriter(file);
			BufferedWriter buffer = new BufferedWriter(escritor);
			
			buffer.write(datosStringParaImprimir);
			
			buffer.close();
			
			System.out.println("Su factura se ha generado exitosamente en la carpeta la siguiente direccion: " + rutaAbsoluta);
			
			
		} catch(IOException e) {
			e.printStackTrace();
		}
			
			} catch(SQLException e) {
			e.printStackTrace();
			}	

	}
	
	public void leerArchivo() {
		
		System.out.println("Seleccione la factura que desea leer: ");
		String carpetaDestino = "facturas";

		File carpeta = new File(carpetaDestino);
	
		
		ArrayList<File>listaDeArchivos = new ArrayList<>();
		
		try {
			if(carpeta.exists() && carpeta.isDirectory()) {
				
				/* Creo una lista de tipo File que va a contener un listado de todos los archivos dentro de la carpeta*/
				File[]archivos = carpeta.listFiles();
				
				/* Si la lista contiene algo, mostraremos su contenido, específicando un índice para que pueda ser seleccionado. */
				if(archivos != null && archivos.length > 0) {
					System.out.println("Facturas disponibles: ");
					
					for(File archivo : archivos) {
						listaDeArchivos.add(archivo);
						System.out.println(listaDeArchivos.size() - 1 + ". " + archivo.getName());
					}
					
					
				}
				
				int facturaSeleccionada = sc.nextInt();
				
				/* Obtenemos, si es que existe una facturta con ese índice, los datos de esa factura, asignandole a un objeto de 
				 * tipo File el nombre de la factura seleccionada (la cuál se buscará según el índice insertado). */
				if(facturaSeleccionada >0 && facturaSeleccionada < listaDeArchivos.size()) {
					File archivoSeleccionado = listaDeArchivos.get(facturaSeleccionada);
					
					FileReader lector = new FileReader(archivoSeleccionado);
					
					BufferedReader buffer = new BufferedReader(lector);
					
					String linea = buffer.readLine();
					while (linea != null) {
							System.out.println(linea);
							
							linea = buffer.readLine();
					}
					buffer.close();

				} else {
					System.out.println("Índice de factura no válido.");
				}
				

			} else {
				System.out.println("La carpeta no existe.");
			}

		} catch(IOException e) {
			e.printStackTrace();
		}

		
	}
	
	public void actualizarArchivo(String nombreArchivo) {
		
	}
	
	public void eliminarArchivo(String nombreArchivo) {
		
	}
}
