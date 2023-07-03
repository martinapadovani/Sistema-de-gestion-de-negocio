package ClasesTransaccion;
import Abstractas.Transaccion;
import Interfaces.*;

public class Gastos extends Transaccion{

	private String destino;

	public Gastos(int id, int fecha, String medioDePago, int montoTotal, String destino) {
		super(id, fecha, medioDePago, montoTotal);
		this.destino = destino;
	}

	
	
	
}
