import ClasesConcretas.*;
import ConexionDB.Conexion;
import ClasesAbstractas.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		
		Gasto gasto = new Gasto("tarjeta", 3000, "alla");
		
		gasto.generarFactura(gasto);
	}

}
