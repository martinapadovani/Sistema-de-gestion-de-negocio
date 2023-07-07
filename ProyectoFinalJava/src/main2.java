import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

import ClasesAbstractas.Personas;
import ClasesConcretas.Empleado;
import ClasesConcretas.Producto;
import ClasesConcretas.Proveedor;

public class main2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stu
		
		Scanner scanner = new Scanner (System.in);
		
         //boolean salir = false;
         /*En Java, los parámetros se pasan por valor, si uso este parámetro booleano para el metodo método volverAlMenu
           se crea una copia del valor original y las modificaciones dentro del método solo afectan a esa copia
           Para poder actualizar el valor de salir en el bucle while, tengo que usar un objeto mutable, */

        AtomicBoolean salir = new AtomicBoolean(false);
          /*La clase AtomicBoolean proporciona métodos para manipular el valor booleano de manera mutable*/

        while (!salir.get()) {	
        	
        	System.out.println("Bienvenido! Ingrese el sector al que desea acceder:");
        	System.out.println("1. Empleados");
        	System.out.println("2. Proveedores	");
        	System.out.println("3. Productos");
        	System.out.println("4. Ventas");
        	System.out.println("5. Gastos");
        	System.out.println("0. Salir");
	
        	int sector = scanner.nextInt(); 
        	/* nextInt solo consume el número en sí y deja el carácter de nueva línea (salto de línea) 
			en el búfer de entrada. Este es capturado por la siguiente llamada a nextLine() y se interpreta como una línea vacía*/
	
	   
        	switch(sector) {

        	case 1 : //EMPLEADOS
        		System.out.println("Bienvenido al sector de Empleados!");	
        		System.out.println("Seleccione según corresponda:");
        		System.out.println("1. Datos laborales");
        		System.out.println("2. Datos personales");
		  
        		int opcionTipoDeDatos = scanner.nextInt();

        		switch (opcionTipoDeDatos) {
		
        		case 1://DATOS LABORALES
			
        			System.out.println("Eligio la opcion datos laborales");	
        			System.out.println("Seleccione según corresponda:");
        			System.out.println("1. Ver empleados");
        			System.out.println("2. Buscar empleado");
        			System.out.println("3. Actualizar datos laborales");
        			System.out.println("4. Agregar nuevo empleado");
        			System.out.println("5. Eliminar empleado del sistema");
        			System.out.println("6. Calcular salario");
        			System.out.println("7. Calcular desempeño");
			  
        			scanner.nextLine(); // adicional

        			Empleado ejecutarMetodosEmpleado = new Empleado(null, null, 0, 0, null, 0, 0, 0, 0, 0, 0, null, null, false);
        			int opcionDatosLaborales = scanner.nextInt(); 
			  
        			switch (opcionDatosLaborales) {
			 
        				case 1:
				  
        					System.out.println("Elegiste la opcion de ver empleados!");
        					ejecutarMetodosEmpleado.Ver();
				  
        					break;
        				case 2:
				  
        					System.out.println("Elegiste la opcion de buscar empleados!");
				  
        					System.out.println("Ingrese el id del Empleado:");
        					int idBuscar = scanner.nextInt(); 
        					ejecutarMetodosEmpleado.Buscar(idBuscar);
				  
        					break;
        				case 3:
				  
        					System.out.println("Elegiste la opcion de actualizar empleados!");
        					
  						    System.out.println("Ingrese el id del Empleado:");
        					int idActualizar = scanner.nextInt(); 
        					ejecutarMetodosEmpleado.Actualizar(idActualizar);
				  
        					break;
        				case 4:
				  
        					System.out.println("Elegiste la opcion de agregar empleados!");
        					ejecutarMetodosEmpleado.Agregar();
				  
        					break;
        				case 5:
				  
        					System.out.println("Elegiste la opcion de eliminar empleados!");
        					
  						    System.out.println("Ingrese el id del Empleado:");
        					int idEliminar = scanner.nextInt(); 
        					ejecutarMetodosEmpleado.Eliminar(idEliminar);
				  
        					break;
        				case 6:
				  
        					System.out.println("Elegiste la opcion de calcular salario!");
        					ejecutarMetodosEmpleado.calcularSalario();
				  
        					break;  
        				case 7:
			  
        					System.out.println("Elegiste la opcion de calcular desempeño!");
        					ejecutarMetodosEmpleado.calcularDesempeño();
			  
        					break;
        				default:
					
        					System.out.println("Opcion inválida! Vuelva a intentarlo");
        					salir.set(false);
        			}	
			
        			break;
			
        		case 2://DATOS PERSONALES
			
        			System.out.println("Eligio la opcion de datos personales!");	
        			System.out.println("Seleccione según corresponda:");
        			System.out.println("1. Ver Datos personales");
        			System.out.println("2. Buscar Empleado");
        			System.out.println("3. Actualizar Datos personales");
        			System.out.println("5. Eliminar Datos personales");
			  
        			scanner.nextLine(); // adicional
        			Personas ejecutarMetodosPersonales = new Personas(null, null, 0, 0, null, 0);

			  int opcionDatosPersonales = scanner.nextInt(); 
			  
			  switch (opcionDatosPersonales) {
			 
			  	case 1:
			  		System.out.println("Elegiste la opcion de ver datos personales!");
			  		ejecutarMetodosPersonales.Ver();
			  		
			  		break;  
			  	case 2:
			  		System.out.println("Elegiste la opcion de Buscar empleado");
			  		
					System.out.println("Ingrese el id del Empleado:");
					int idBuscar = scanner.nextInt(); 
			  		ejecutarMetodosPersonales.Buscar(idBuscar);
			  		
			  		break;
			  	case 3:
			  		System.out.println("Elegiste la opcion de actualizar datos personales");
			  		
					System.out.println("Ingrese el id del Empleado:");
					int idActualizar = scanner.nextInt(); 
			  		ejecutarMetodosPersonales.Actualizar(idActualizar);
			  		
				  
			  		default:
					
					System.out.println("Opcion inválida! Vuelva a intentarlo");
					salir.set(false);
					
			  }
			  break;//Brek switch opcion datos laborales/personales
        	default:
			
        		System.out.println("Opcion inválida! Vuelva a intentarlo");
        		salir.set(false);
        		}
        		volverAlMenu(salir);
        		break;	//Brek switch opcion Empleados
		
        	case 2://PROVEEDORES
		
        		System.out.println("Bienvenido al sector de proveedores!");	
        		System.out.println("Seleccione según corresponda:");
        		System.out.println("1. Ver proveedores");
        		System.out.println("2. Buscar proveedor");
        		System.out.println("3. Actualizar proveedores");
        		System.out.println("4. Agregar nuevo proveedor");
        		System.out.println("5. Eliminar proveedor del sistema");
		  
        		scanner.nextLine(); /* adicional, para consumir el salto de línea pendiente, que queda en el búfer de entrada.
		   		Prepara correctamente el búfer de entrada para que la siguiente llamado, evitando que el nextLine() del titulo, 
		   		lea ese salto de línea (y devuelva una cadena vacía) en lugar de la línea ingresada por el usuario (el título) */
		  
        		Proveedor ejecutarMetodosProveedor = new Proveedor(0, null, 0, null);
        		int opcionProveedores = scanner.nextInt(); 
		  
        		switch (opcionProveedores) {
	 
        		case 1:
		 
        				System.out.println("Elegiste la opcion de ver proveedores!");
        				ejecutarMetodosProveedor.Ver();
		  
        				break;
        		case 2:
		 
        			System.out.println("Elegiste la opcion de buscar proveedores!");
		  
        			System.out.println("Ingrese el id del Proveedor:");
        			int idBuscar = scanner.nextInt(); 
		  
        			ejecutarMetodosProveedor.Buscar(idBuscar);
		  
        			break;
        		case 3:
		 
        			System.out.println("Elegiste la opcion de actualizar empleados!");
        			System.out.println("Ingrese el id del Proveedor:");
        			int idActualizar = scanner.nextInt(); 
        			ejecutarMetodosProveedor.Actualizar(idActualizar);
		  
        			break;
        		case 4:
		 
        			System.out.println("Elegiste la opcion de agregar proveedores!");
        			ejecutarMetodosProveedor.Agregar();
		  
        			break;
        		case 5:
		 
        			System.out.println("Elegiste la opcion de eliminar proveedores!");
        			System.out.println("Ingrese el id del Proveedor:");
        			int idEliminar = scanner.nextInt(); 
        			ejecutarMetodosProveedor.Eliminar(idEliminar);
		  
        			break;
        		default:
			
        			System.out.println("Opcion inválida! Vuelva a intentarlo");
        			salir.set(false);
        			}
        		volverAlMenu(salir);
        		break;
	
        	case 3://PRODUCTOS
		
        		System.out.println("Bienvenido al sector de productos!");	
        		System.out.println("Seleccione según corresponda:");
        		System.out.println("1. Ver productos");
        		System.out.println("2. Buscar producto");
        		System.out.println("3. Actualizar producto");
        		System.out.println("4. Agregar nuevo producto");
        		System.out.println("5. Eliminar producto del sistema");
        		System.out.println("6. Ver stock de producto");
        		System.out.println("7. Calcular stock de producto");
		  
        		scanner.nextLine(); /* adicional, para consumir el salto de línea pendiente, que queda en el búfer de entrada.
		   		Prepara correctamente el búfer de entrada para que la siguiente llamado, evitando que el nextLine() del titulo, 
		   		lea ese salto de línea (y devuelva una cadena vacía) en lugar de la línea ingresada por el usuario (el título) */

        		Proveedor completarConstructor = new Proveedor(0, null, 0, null);
        		Producto ejecutarMetodosProductos = new Producto(0, null, null, 0, completarConstructor , 0);
        		int opcionProductos = scanner.nextInt(); 
		  
        		switch (opcionProductos) {
		 
        		case 1:
			  
        			System.out.println("Elegiste la opcion de ver productos!");
        			ejecutarMetodosProductos.Ver();
			  
        			break;
        		case 2:
			  
        			System.out.println("Elegiste la opcion de buscar producto!");
			  
        			System.out.println("Ingrese el id del Producto:");
        			int id = scanner.nextInt(); 
        			ejecutarMetodosProductos.Buscar(id);
			  
        			break;
        		case 3:
			  
        			System.out.println("Elegiste la opcion de actualizar producto!");
        			System.out.println("Ingrese el id del Producto:");
        			int idActualizar = scanner.nextInt(); 
        			ejecutarMetodosProductos.Actualizar(idActualizar);
			  
        			break;
        		case 4:
			  
        			System.out.println("Elegiste la opcion de agregar producto!");
        			ejecutarMetodosProductos.Agregar();
			  
        			break;
        		case 5:
			  
        			System.out.println("Elegiste la opcion de eliminar producto!");
        			System.out.println("Ingrese el id del Producto:");
        			int idEliminar = scanner.nextInt(); 
        			ejecutarMetodosProductos.Eliminar(idEliminar);
			  
        			break;
        		case 6:
			  
        			System.out.println("Elegiste la opcion de ver stock!");
        			ejecutarMetodosProductos.verStock(ejecutarMetodosProductos);
			  
        			break;
        		case 7:
			  
        			System.out.println("Elegiste la opcion de calcular stock!");
        			ejecutarMetodosProductos.calcularStock(ejecutarMetodosProductos);
			  
        			break;
			  
        		default:
        			
					System.out.println("Opcion inválida! Vuelva a intentarlo");
					salir.set(false);
        		}
        		volverAlMenu(salir);
        		break;
	
        	case 4://VENTAS
        		break;
		
        	case 5://GASTOS
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
		  System.out.println("Hasta pronto!");
		  salir.set(true);
	  }
}

}
