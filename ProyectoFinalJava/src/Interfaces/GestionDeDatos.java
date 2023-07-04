package Interfaces;

public interface GestionDeDatos <T> {
	
	abstract void Agregar(T objeto);
	abstract void Buscar(int ID);
	abstract void Actualizar(T objeto);
	abstract void Eliminar(T objeto);

}
