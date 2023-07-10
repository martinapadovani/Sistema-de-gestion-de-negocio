package ClasesConcretas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import ConexionDB.Conexion;

public class ProductoVentas extends Producto{

	private int cantidad;
	
	//ATRIBUTOS PARA LA CONEXIÓN: 
	Conexion conexion = new Conexion();
	private Connection cn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	//ATRIBUTOS para métodos.
	int ventaId = 0;
	int productoIdEnlazado = 0;
	
	// CONSTRUCTORES 
	
	public ProductoVentas() {
		
	}

	public ProductoVentas(int id, String nombreProducto, String categoriaProducto, int stockDisponible,
			Proveedor proveedorDelProducto, int precio, int cantidad) {
		super();
		this.cantidad = cantidad;
	}

	/* METODOS */
	
	/* El siguiente método recopila los datos de la tabla 'productoVenta', agrupa los productos enlazados por venta y
	 * muestra la cantidad de veces que aparece cada producto en una venta.*/
	public void mostrarProductosEnlazadosAUnaVenta() {
		try {
			cn = conexion.conectar();
			
			/* Creamos una lista 'datosProductoVenta' que almacenará los datos de los ventas(ventas_id) y productos enlazados. */
			ArrayList<String>datosProductoVenta = new ArrayList<>();
			
			/* Declaramos un HashMap, que es una estructura de datos que permite almacenar pares clave valor, donde cada
			 * clave es única y se utiliza para acceder a su valor correspondiente de manera más eficiente. 
			 * En este caso, se utiliza para asociar cada "ventaId" con una lista de productos enlazados a esa venta, permitiendonos agrupar los productos enlazados por venta.
			 * En este caso, Integer hace referencia a que la clave "ventaId" es de tipo Integer y el valor asociado será una lista
			 * de enteros que contiene los "productoIdEnlazado".*/
			HashMap<Integer, ArrayList<Integer>> productosPorVenta = new HashMap<>();
			
			/* Ejecutamos una consulta en la DB para obtener los resultados de la tabla 'productoVenta'. Luego, con un bucle, obtenemos los resultados.*/
			String query = "SELECT * FROM productoVenta";
			
			ps = cn.prepareStatement(query);
			rs = ps.executeQuery(query);
			
			while(rs.next()) {
				ventaId = rs.getInt("venta_id");
				productoIdEnlazado = rs.getInt("producto_id");
				
				/* Primero verificamos si el "ventaId" actual ya está presente como una clave en el HashMap. Si no está presente
				 * agregamos una nueva entrada al 'HashMap' con el 'ventaId' como clave y una nueva lista vacía como valor.
				 * Luego, agregamos el 'productoIdEnlazado' a la lista correspondiente utilizando el método 'get()' y 'add()' */
				if(!productosPorVenta.containsKey(ventaId)) {
					productosPorVenta.put(ventaId, new ArrayList<>());
				}
				
				productosPorVenta.get(ventaId).add(productoIdEnlazado);
			}			
			
			/*  Cuando se utiliza el método entrySet() en un HashMap nos devuelve un conjunto de entradas(claves y valores) en el HashMap. Cada entrada
			 * es representada por la interfaz 'Map.Entry<K, V>', donde 'K' es el tipo de dato de la clave y 'V' es el tipo de dato del valor asociado. 
			 * Cada entrada puede ser accedida utilizando métodos como 'getKey()' para obtener la clave y 'getValue()' para obtener el valor asociado. 
			 * 
			 * En este caso, se utiliza el método 'entrySet()' para recorrer todas las entradas del 'HashMap' 'productosPorVenta' dentro del bucle 'for'. 
			 * Al utilizar el bucle, en combinación con entrySet() se puede iterar sobre todas las entradas del 'HashMap' y acceder a la clave ('ventaId') 
			 * y al valor ('ArrayList<Integer>' de productos enlazados) de cada entrada.*/
			for(Map.Entry<Integer, ArrayList<Integer>> entry : productosPorVenta.entrySet()) {
				int ventaId = entry.getKey();
				ArrayList<Integer> productosEnlazados = entry.getValue();
				
				/* Por cada venta, se crea un nuevo HashMap que se utilizará para controlar la cantidad de veces que aparece cada producto en la venta actual 
				 * ya que aún nos encontramos en el for del HashMap anterior. */
				HashMap<Integer, Integer> productosConCantidad = new HashMap<>();
				
				/* Se recorrren los 'productosEnlazados' de la venta y se verifica si el 'productoId' ya está presente en 'productosConCantidad'. Si es así, se 
				 * incrementa la cantidad en 1. Si no está presente, se agrega una nueva entrada con el 'productoId' y la cantidad '1'.*/
				for(int productoId : productosEnlazados) {
					if(productosConCantidad.containsKey(productoId)) {
						int cantidad = productosConCantidad.get(productoId);
						productosConCantidad.put(productoId, cantidad + 1);
					} else {
						productosConCantidad.put(productoId, 1);
					}
				}
				
				/* Se construye un StringBuilder que almacenará los datos de la venta y los productos enlazados. */
				StringBuilder datos = new StringBuilder();
				
				/* Construimos el String dato con los datos. */
				datos.append("ID VENTA= ").append(ventaId).append(", PRODUCTOS VENDIDOS= [");
				
				/* Creamos un booleano bandera para que controle si es la primera iteracion del bucle. Al principio, establecemos 'primeraIteracion' como true. Dentro del bucle
				 * antes de agregar cada paréntesis, verificamos si 'primeraIteración' es true. Si es 'true', no agregamos una coma. Si no lo es, agregamos una coma luego de cada 
				 * paréntesis. Esto serviría para que no se agregue una coma en el último elemento del arreglo, sino sólo se agregaría luego de cada elemento al que le siga un elemento nuevo. */
				boolean primeraIteracion = true;
				for(Map.Entry<Integer, Integer> productoEntry : productosConCantidad.entrySet()) {
					int productoId = productoEntry.getKey();
					int cantidad = productoEntry.getValue();
					
					if(primeraIteracion) {
						primeraIteracion = false;
					} else {
						datos.append(", ");
					}
					
					/* Construimos una cadena para representar cada paréntesis que contiene el 'productoId' y la 'cantidad', esto se agrega al
					 * StringBuilder 'datos'.*/
					datos.append("(PRODUCTO_ID= ").append(productoId).append(", CANTIDAD= ").append(cantidad).append(")");
					
				}
				
				/* Agregamos un corchete de cierre al 'StringBuilder' 'datos'.*/
				datos.append("]");
				
				/* Agregamos la cadena a la lista 'datosProductoVenta' que creamos al inicio del método. */
				datosProductoVenta.add(datos.toString());
				
			}
			
			/* Finalmente, iteramos sobre la lista 'datosProductoVenta', si tiene contenido, mostramos los elementos en consola. 
			 * Si no, damos el msj de que no contiene datos.*/
			if(!datosProductoVenta.isEmpty()) {
				for(String dato : datosProductoVenta) {
					System.out.println(dato);
				}
			} else {
				System.out.println("No hay registros de productos vendidos en el sistema!");
			}


			
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

}
	

