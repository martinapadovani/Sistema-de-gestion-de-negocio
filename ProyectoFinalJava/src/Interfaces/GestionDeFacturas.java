package Interfaces;

import ClasesConcretas.Gasto;

public interface GestionDeFacturas <T> {
	
	public void generarFactura(T objeto);
	public void buscarFactura(int id);
	public void eliminarFactura(T objeto);

}
