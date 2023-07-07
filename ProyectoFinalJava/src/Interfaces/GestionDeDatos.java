package Interfaces;

import ClasesConcretas.Producto;

public interface GestionDeDatos <T> {
	
	abstract void Ver();
	abstract void Buscar(int ID);
	abstract void Actualizar(T objeto);
	abstract void Agregar();
	abstract void Eliminar(T objeto);
}
