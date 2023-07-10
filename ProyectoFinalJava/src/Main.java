import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

import ClasesAbstractas.Personas;
import ClasesAbstractas.Transaccion;
import ClasesConcretas.Empleado;
import ClasesConcretas.Gasto;
import ClasesConcretas.Producto;
import ClasesConcretas.ProductoVentas;
import ClasesConcretas.Proveedor;
import ClasesConcretas.Venta;
import ManejoDeArchivos.ManejoDeArchivos;
public class Main {

	public static void main(String[] args) {

		Scanner scanner = new Scanner (System.in);
		
		//boolean salir = false;
	/*En Java, los parámetros se pasan por valor, si uso este parámetro booleano para el metodo método volverAlMenu
	se crea una copia del valor original y las modificaciones dentro del método solo afectan a esa copia
	Para poder actualizar el valor de salir en el bucle while, tengo que usar un objeto mutable, */
		
		AtomicBoolean salir = new AtomicBoolean(false);
	/*La clase AtomicBoolean proporciona métodos para manipular el valor booleano de manera mutable*/
		
		AtomicBoolean volverHaciaAtras = new AtomicBoolean(false);
	/*AtomicBoolean bandera para volver solamente una sección hacia atrás. */
		
		while (!salir.get()) {	
			System.out.println("Bienvenido! Ingrese el sector al que desea acceder:");
			System.out.println("1. Empleados");
			System.out.println("2. Proveedores	");
			System.out.println("3. Productos");
			System.out.println("4. Transacciones");
			System.out.println("0. Salir");
			
			int sector = scanner.nextInt(); 
			/* nextInt solo consume el número en sí y deja el carácter de nueva línea (salto de línea) 
			en el búfer de entrada. Este es capturado por la siguiente llamada a nextLine() y se interpreta como una línea vacía*/
			
			   
			switch(sector) {

			case 1 : //EMPLEADOS
				  System.out.println("Bienvenido al sector de Empleados!");	
				  System.out.println("Seleccione según corresponda:");
				  System.out.println("1. Datos laborales.");
				  System.out.println("2. Datos personales.");
				  System.out.println("3. General.");
				  System.out.println("0. Menu Principal.");
				  
				  int opcionEmpleados = scanner.nextInt();

			switch (opcionEmpleados) {
				
				case 1://DATOS LABORALES
					
					  System.out.println("Eligio la opcion datos laborales");	
					  System.out.println("Seleccione según corresponda:");
					  System.out.println("1. Ver datos laborales de empleados.");
					  System.out.println("2. Buscar empleado por ID.");
					  System.out.println("3. Actualizar datos laborales.");
					  System.out.println("0. Menu Principal.");
					  
					  scanner.nextLine(); // adicional
					  
					  Empleado ejecutarMetodosEmpleado = new Empleado();
					  int opcionDatosLaborales = scanner.nextInt(); 
					  
				 switch (opcionDatosLaborales) {
					 
					  case 1:
						  
						  System.out.println("Elegiste la opcion de ver empleados!");
						  ejecutarMetodosEmpleado.Ver();
						  
						  break;
					  case 2:
						  
						  System.out.println("Elegiste la opcion de buscar empleado!");
						  
						  System.out.println("Ingrese el ID del empleado:");
						  int id = scanner.nextInt(); 
						  ejecutarMetodosEmpleado.Buscar(id);
						  
						  break;
					  case 3:
						  
						  System.out.println("Elegiste la opcion de actualizar datos laborales!");
						  
						  System.out.println("Ingrese el ID del empleado:");
      					int idActualizar = scanner.nextInt(); 
      					ejecutarMetodosEmpleado.Actualizar(idActualizar);
						  
						  break;
					  case 0:
						  salir.set(false);
						  break;
						  
					default:
							
							System.out.println("Opcion inválida! Vuelva a intentarlo");
							salir.set(false);
					 }
					
					break;
					
				case 2://DATOS PERSONALES
					
					  System.out.println("Eligio la opcion de datos personales!");	
					  System.out.println("Seleccione según corresponda:");
					  System.out.println("1. Ver datos personales de empleados.");
					  System.out.println("2. Buscar Empleado por ID.");
					  System.out.println("3. Actualizar Datos personales.");
					  System.out.println("0. Menu Principal.");
					  
					  scanner.nextLine(); // adicional
					  
					  Personas ejecutarMetodosPersonales = new Personas();
					  int opcionDatosPersonales = scanner.nextInt(); 
					  
				 switch (opcionDatosPersonales) {	 
				 
				  	case 1:
				  		System.out.println("Elegiste la opcion de ver datos personales!");
				  		ejecutarMetodosPersonales.Ver();
				  		
				  		break;  
				  	case 2:
				  		System.out.println("Elegiste la opcion de buscar empleado!");
				  		
						System.out.println("Ingrese el ID del empleado:");
						int idBuscar = scanner.nextInt(); 
				  		ejecutarMetodosPersonales.Buscar(idBuscar);
				  		
				  		break;
				  	case 3:
				  		System.out.println("Elegiste la opcion de actualizar datos personales!");
				  		
						System.out.println("Ingrese el ID del Empleado:");
						int idActualizar = scanner.nextInt(); 
				  		ejecutarMetodosPersonales.Actualizar(idActualizar);
				  		
				  		break;
					  case 0:
						  salir.set(false);
						  break;
				  	default:
						
						System.out.println("Opcion inválida! Vuelva a intentarlo.");
						salir.set(false);			
				 }
					break;//Break switch opcion datos laborales/personales
					
				case 3:
					
					  System.out.println("Eligio la opcion para gestiones generales.");	
					  System.out.println("Seleccione según corresponda:");
					  System.out.println("1. Agregar nuevo empleado al sistema.");
					  System.out.println("2. Eliminar empleado del sistema.");
					  System.out.println("3. Calcular salario de empleado.");
					  System.out.println("4. Calcular desempeño de empleado.");
					  System.out.println("0. Menu Principal.");
					  
					  scanner.nextLine(); // adicional
					  
					  Empleado ejecutarMetodosGenerales = new Empleado();
					  int opcionMetodosGenerales = scanner.nextInt(); 
					  
					switch(opcionMetodosGenerales) {
					  case 1:
						  
						  System.out.println("Elegiste la opcion de agregar un empleado al sistema!");
						  ejecutarMetodosGenerales.Agregar();
						  
						  break;
					  case 2:
						  
						  System.out.println("Elegiste la opcion de eliminar un empleado del sistema!");
						  
						  System.out.println("Ingrese el ID del empleado:");
      					int idEliminar = scanner.nextInt(); 
      					ejecutarMetodosGenerales.Eliminar(idEliminar);
						  
						  break;
				      case 3:
						  
						  System.out.println("Elegiste la opcion de calcular salario!");
						  System.out.println("Ingrese el ID del empleado:");
	      					int idSalario = scanner.nextInt();
						  ejecutarMetodosGenerales.calcularSalario(idSalario);
						  
						  break;  
				     case 4:
					  
					      System.out.println("Elegiste la opcion de calcular desempeño!");
						  System.out.println("Ingrese el ID del empleado:");
	      					int idDesempeño = scanner.nextInt();
					      ejecutarMetodosGenerales.calcularDesempeño(idDesempeño);
					  
					  break;
					  case 0:
						  salir.set(false);
						  break;
						default:
							
							System.out.println("Opcion inválida! Vuelva a intentarlo.");
							salir.set(false);
					
					}
					break;
				  case 0:
					  salir.set(false);
					  break;
				default:
					
					System.out.println("Opcion inválida! Vuelva a intentarlo.");
					salir.set(false);
				}
			  volverAlMenu(salir);
			break;	//Brek switch opcion Empleados
				
			case 2://PROVEEDORES
				
				  System.out.println("Bienvenido al sector de proveedores!");	
				  System.out.println("Seleccione segun corresponda:");
				  System.out.println("1. Ver proveedores.");
				  System.out.println("2. Ver productos.");
				  System.out.println("3. Buscar proveedor por ID.");
				  System.out.println("4. Actualizar proveedor.");
				  System.out.println("5. Agregar nuevo proveedor.");
				  System.out.println("6. Eliminar proveedor del sistema.");
				  System.out.println("0. Menu Principal.");
				  
				  scanner.nextLine(); // adicional
				  
				  Proveedor ejecutarMetodosProveedor = new Proveedor();
				  int opcionProveedores = scanner.nextInt(); 
				  
			 switch (opcionProveedores) {
			 
			 case 1:
				 
				  System.out.println("Elegiste la opcion de ver proveedores!");
				  ejecutarMetodosProveedor.Ver();
				  
				 break;
			 case 2:
				 
				  System.out.println("Elegiste la opcion de ver productos!");
				  
				  System.out.println("Ingrese el ID del proveedor:");
				  int id = scanner.nextInt(); 
				  
				  ejecutarMetodosProveedor.verProductos(id);
				  
				 break;
				 
			 case 3:
				 
				  System.out.println("Elegiste la opcion de buscar proveedores!");
				  
				  System.out.println("Ingrese el ID del proveedor:");
				  int idBuscar = scanner.nextInt(); 
				  
				  ejecutarMetodosProveedor.Buscar(idBuscar);
				  
				 break;
			 case 4:
				 
				  System.out.println("Elegiste la opcion de actualizar proveedor!");
      			System.out.println("Ingrese el ID del proveedor:");
      			int idActualizar = scanner.nextInt(); 
      			ejecutarMetodosProveedor.Actualizar(idActualizar);
				  
				 break;
			 case 5:
				 
				  System.out.println("Elegiste la opcion para agregar un proveedor al sistema!");
				  ejecutarMetodosProveedor.Agregar();
				  
				 break;
			 case 6:
				 
	       			System.out.println("Elegiste la opcion para eliminar un proveedor del sistema!");
        			System.out.println("Ingrese el ID del proveedor:");
        			int idEliminar = scanner.nextInt(); 
        			ejecutarMetodosProveedor.Eliminar(idEliminar);
				  
				 break;
			  case 0:
				  salir.set(false);
				  break;
			default:
					
					System.out.println("Opcion invalida! Vuelva a intentarlo.");
					salir.set(false);
			 }
			  volverAlMenu(salir);
			   break;
			
			case 3://PRODUCTOS
				
				  System.out.println("Bienvenido al sector de productos!");	
				  System.out.println("Seleccione según corresponda:");
				  System.out.println("1. Ver productos.");
				  System.out.println("2. Buscar producto por ID.");
				  System.out.println("3. Actualizar producto.");
				  System.out.println("4. Agregar nuevo producto.");
				  System.out.println("5. Eliminar producto del sistema.");
				  System.out.println("6. Gestion de Stock.");
				  System.out.println("0. Menu Principal.");
				  
				  scanner.nextLine(); /* adicional */

				  Producto ejecutarMetodosProductos = new Producto();
				  int opcionProductos = scanner.nextInt(); 
				  
			 switch (opcionProductos) {
				 
				  case 1:
					  
					  System.out.println("Elegiste la opcion de ver productos!");
					  ejecutarMetodosProductos.Ver();
					  
					  break;
				  case 2:
					  
					  System.out.println("Elegiste la opcion de buscar producto!");
					  
					  System.out.println("Ingrese el ID del producto:");
					  int id = scanner.nextInt(); 
					  ejecutarMetodosProductos.Buscar(id);
					  
					  break;

				  case 3:
					  
	        			System.out.println("Elegiste la opcion de actualizar producto!");
	        			System.out.println("Ingrese el ID del producto:");
	        			int idActualizar = scanner.nextInt(); 
	        			ejecutarMetodosProductos.Actualizar(idActualizar);
					  
					  break;
				  case 4:
					  
					  System.out.println("Elegiste la opcion para agregar producto al sistema!");
					  ejecutarMetodosProductos.Agregar();
					  
					  break;
				  case 5:
					  
	        			System.out.println("Elegiste la opcion para eliminar producto del sistema!");
	        			System.out.println("Ingrese el ID del producto:");
	        			int idEliminar = scanner.nextInt(); 
	        			ejecutarMetodosProductos.Eliminar(idEliminar);
					  
					  break;
				  case 6:
					  
					  System.out.println("Bienvenido a la gestion de stocks!");
					  System.out.println("Seleccione segun corresponda:");
					  System.out.println("1. Ver stock.");
					  System.out.println("2. Calcular stock.");
					  System.out.println("3. Modificar stock.");
					  System.out.println("0. Menu Principal");
					  
					  int opcionStock = scanner.nextInt();
					  
					  switch (opcionStock) {
					  
					  case 1:
						  
						  System.out.println("Elegiste la opcion de ver stock!");
		        			System.out.println("Ingrese el ID del producto:");
		        			int idVerStock = scanner.nextInt(); 
		        			ejecutarMetodosProductos.verStock(idVerStock);
						  
						  break;
					  case 2:
						  
						  System.out.println("Elegiste la opcion de calcular stock!");  
		        			System.out.println("Ingrese el ID del producto:");
		        			int idCalcularStock = scanner.nextInt(); 
		        			ejecutarMetodosProductos.calcularStock(idCalcularStock);;
		        			
						  break;
					  case 3:
						  
						  System.out.println("Elegiste la opcion de modificar stock!");
		        			System.out.println("Ingrese el ID del producto:");
		        			int idModificarStock = scanner.nextInt(); 
		        			ejecutarMetodosProductos.modificarStock(idModificarStock);
						  
						  break;
					  case 0:
						  salir.set(false);
						  break;
						  
					default:
							
						System.out.println("Opcion inválida! Vuelva a intentarlo.");
						salir.set(false);
					  }
					  break;
					  
				 case 0:
					  salir.set(false);
					  break;//Finaliza switch gestion de stock
				default:
						
						System.out.println("Opcion inválida! Vuelva a intentarlo.");
						salir.set(false);
			 }
			  volverAlMenu(salir);
			   break;
			  
			case 4://TRANSACCIONES 
				
				/* El while(!volverHaciaAtras.get()) indica que se deben ejecutar los CASES de TRANSACCION, GASTOS Y VENTAS hasta que el valor 
				 * de "volverHaciaAtras" cambie.*/
				/* En caso de que se elija la opcion 4 (desde el menú principal), se resetea el valor de "volverHaciaAtras" ya que nunca cambiaría si es que 
				 * no se sale del ciclo principal "while(!salir.get()), como es en este caso.*/
				volverHaciaAtras.set(false);
				while(!volverHaciaAtras.get()) {

				System.out.println("Qué acción desea realizar?");
				System.out.println("1. Ver todas las transacciones.");
				System.out.println("2. Acceder a sección ventas.");
				System.out.println("3. Acceder a sección gastos.");
				System.out.println("4. Gestionar facturas.");
				System.out.println("0. Volver al menú principal. ");
				System.out.print("Su opción: ");
				int opcion = scanner.nextInt();
				
				Transaccion ejecutarMetodosTransaccion = new Transaccion();
				switch(opcion) {
				case 1://VER TODAS LAS TRANSACCIONES REGISTRADAS.
					
					ejecutarMetodosTransaccion.mostrarTransacciones();
					
					System.out.println("Que accion desea realizar?");
					System.out.println("1. Volver una sección.");
					System.out.println("0. Volver al menú principal. ");
					System.out.print("Su opción: ");
					
					int opcionTransaccion = scanner.nextInt();
	
					switch(opcionTransaccion) {
					case 1:
						volverUnaSeccionAtras(volverHaciaAtras);
						break;
					case 0: 
						volverHaciaAtras.set(true);
						break;
					default:
						System.out.println("Opcion inválida. Vuelva a intentarlo!");
						volverHaciaAtras.set(false);
						break;	
					}
					
					break;
					
				case 2://VENTAS
					  System.out.println("Bienvenido al sector de ventas!");	
					  System.out.println("Seleccione según corresponda:");
					  System.out.println("1. Ver ventas");
					  System.out.println("2. Ver productos enlazados a la venta.");
					  System.out.println("3. Buscar ventas");
					  System.out.println("4. Agregar nueva venta");
					  System.out.println("5. Eliminar venta del sistema");
					  System.out.println("6. Volver una sección atrás");
					  System.out.println("0. Volver al menú principal");
					  
					  scanner.nextLine();
					  
					  Empleado ejecutarMetodosEmpleado = new Empleado();
					  Venta ejecutarMetodosVenta = new Venta(null, 0, ejecutarMetodosEmpleado, null);
					  ProductoVentas ejecutarMetodosProductoVentas = new ProductoVentas();
					  
					  System.out.print("Su opción: ");
					  int opcionVenta = scanner.nextInt();
					  
					  switch(opcionVenta) {
					  case 1:
						  
						  ejecutarMetodosVenta.mostrarVentas();
						  
						  break;
						  
					  case 2: 
						  
						  ejecutarMetodosProductoVentas.mostrarProductosEnlazadosAUnaVenta();

						  
						  break;
					  case 3:
							  
						  ejecutarMetodosVenta.buscarFactura();  
						  
						  break;
					  case 4:

						  ejecutarMetodosVenta.generarFactura(); 

						  break;
						  
					  case 5:
						  
						  ejecutarMetodosVenta.eliminarFactura();
						  
						  break;
					  case 6:
						  
						  volverUnaSeccionAtras(volverHaciaAtras);

						  break;

					  case 0:
							volverHaciaAtras.set(true);
						  break;


					  default:
						  System.out.println("Opcion inválida. Vuelva a intentarlo!");
						  break;
					  }
			
					break;
					
				case 3://GASTOS
					
						  System.out.println("Bienvenido al sector de gastos!");	
						  System.out.println("Seleccione según corresponda:");
						  System.out.println("1. Ver gastos");
						  System.out.println("2. Buscar gastos");
						  System.out.println("3. Agregar nueva gasto");
						  System.out.println("4. Eliminar gasto del sistema");
						  System.out.println("5. Volver una sección atrás");
						  System.out.println("0. Volver al menú principal");
						  
						  
						  scanner.nextLine();
						  
						  Gasto ejecutarMetodosGastos = new Gasto(null, 0, null);
						  
						  System.out.print("Su opción: ");
						  int opcionGastos = scanner.nextInt();
						
						  switch(opcionGastos) {
						  case 1:
							  
							  ejecutarMetodosGastos.mostrarGastos();
							  	
							  break;
						  case 2:
							  
							  ejecutarMetodosGastos.buscarFactura();
							  
							  break;
						  case 3:
							  
							  ejecutarMetodosGastos.generarFactura();
							  
							  break;
						  case 4:
							  
							  ejecutarMetodosGastos.eliminarFactura();
							  
							  break;
							  
						  case 5:
							  
							  volverUnaSeccionAtras(volverHaciaAtras);
							  
							  break;
							  
						  case 0:
								volverHaciaAtras.set(true);
							  break;
						default:
							System.out.println("Opcion inválida. Vuelva a intentarlo!");
							break;
						  }	
						  
				case 4://GESTIONAR FACTURAS.
					System.out.println("Bienvenido a la sección de gestion de facturas!");
					System.out.println("Seleccione según corresponda: ");
					System.out.println("1. Generar factura.");
					System.out.println("2. Actualizar facturas.");
					System.out.println("3. Ver facturas.");
					System.out.println("4. Eliminar facturas.");
					System.out.println("5. Volver una sección atrás.");
					System.out.println("0. Volver al menú.");
					int opcionFacturas = scanner.nextInt();
					
					ManejoDeArchivos ejecutarMetodosManejoDeArchivos = new ManejoDeArchivos();
					
					switch(opcionFacturas) {
						case 1:
							ejecutarMetodosManejoDeArchivos.crearYEscribirArchivo();
							break;
						case 2:
							break;
						case 3: 
							ejecutarMetodosManejoDeArchivos.leerArchivo();
							break;
						case 4:
							break;
						case 5: 
							
							volverUnaSeccionAtras(volverHaciaAtras);
							 
							break;
						case 0: 
							
							volverHaciaAtras.set(true);
							
							break;
							
						default: 
							System.out.println("Opción inválida!");
							break;
					
					}
					
					break;
				case 0:
					/* En caso de elegir esta opción, cambiamos el valor del AtomicBoolean a true, por lo que se saldra del ciclo secundario
					 * y se volverá al menú principal.*/
					volverHaciaAtras.set(true);
					break;
					
				default: 
					System.out.println("Opcion inválida! Vuelva a intentarlo");
					break;
				}
				}
			
				break;
		
			case 0: //Salir
	
				System.out.println("Hasta Pronto!");	
				salir.set(true);;
				
				break;	
			default:
				
				System.out.println("Opcion inválida! Vuelva a intentarlo");
				salir.set(false);
			}
		}

	}
		public static void volverAlMenu(AtomicBoolean salir) {
	/*Utilizo AtomicBoolean para mantener el estado mutable de la variable salir. 
	 * La función toma el AtomicBoolean como parámetro y puede modificar su valor utilizando el método set().*/
		
		Scanner scanner = new Scanner (System.in);
		
		  System.out.println("Desea volver al menu (si/no)?");
		  String volver = scanner.next();
		  
		  if(volver.equalsIgnoreCase("si")) {
			  salir.set(false);
			  
		  }else {
			  System.out.println("¡Hasta pronto!");
			  salir.set(true);
		  }
	}
		/* Como en la funcion volverAlMenu, utilizamos el AtomicBoolean con el mismo sentido.*/
		public static void volverUnaSeccionAtras(AtomicBoolean volverHaciaAtras) {			
			volverHaciaAtras.set(false);
		}

}
