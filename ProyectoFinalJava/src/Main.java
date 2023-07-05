import ClasesConcretas.*;
import ConexionDB.Conexion;
import ClasesAbstractas.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		
		Gasto gasto = new Gasto("efectivo", 3000, "otro");
		
		gasto.generarFactura(gasto);
	}

}
