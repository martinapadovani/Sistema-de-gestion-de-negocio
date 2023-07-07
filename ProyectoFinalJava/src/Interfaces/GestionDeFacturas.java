package Interfaces;

import ClasesConcretas.Gasto;

public interface GestionDeFacturas <T> {
	
	public void generarFactura();
	public void buscarFactura(int id);
	public void eliminarFactura();

}
