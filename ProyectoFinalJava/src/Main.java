import ConexionDB.*;
import ClasesConcretas.*;
import ConexionDB.Conexion;
import ClasesAbstractas.*;

import java.time.LocalDate;
import java.util.ArrayList;
import ClasesConcretas.*;
import ClasesAbstractas.*;
public class Main {

	public static void main(String[] args) {
		
		ArrayList<ProductoVentas> productos = null;
		LocalDate fecha = LocalDate.now();
		
		Empleado empleado = null;
		Venta venta = new Venta("tarjeta", 4000, empleado, productos);

		venta.mostrarFacturas();

	}

}
