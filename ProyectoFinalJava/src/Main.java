import ConexionDB.*;
import ClasesConcretas.*;
import ConexionDB.Conexion;
import ClasesAbstractas.*;

import java.time.LocalDate;
import java.util.ArrayList;


public class Main {

	public static void main(String[] args) {
		
		Conexion cn = new Conexion();
		
		cn.conectar();
		

	}

}
