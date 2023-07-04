package Interfaces;

public interface GestionDeFacturas <T> {
	
	public void generarFactura(T objeto);
	public void buscarFactura(int id);
	public void verInfoFactura(T objeto);
	public void eliminarFactura(T objeto);

}
