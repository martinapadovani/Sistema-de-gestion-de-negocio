package Interfaces;

public interface GestionDeDatos <T> {
	
	abstract void Agregar();
	abstract void Buscar(int ID);
	abstract void Actualizar();
	abstract void Eliminar();

}
