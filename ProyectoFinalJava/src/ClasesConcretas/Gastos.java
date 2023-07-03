package ClasesConcretas;
import ClasesAbstractas.Transaccion;
import Interfaces.*;

public class Gastos extends Transaccion{

	// ATRIBUTOS
	private String destino;
	
	// CONSTRUCTOR

	public Gastos(int id, int fecha, String medioDePago, int montoTotal, String destino) {
		super(id, fecha, medioDePago, montoTotal);
		this.destino = destino;
	}

	// MÉTODOS: 
	
	@Override
	public String toString() {
		return "Gastos [destino=" + destino + "]";
	}



	public String getDestino() {
		return destino;
	}



	public void setDestino(String destino) {
		this.destino = destino;
	}
	

	
	

	
	
}
