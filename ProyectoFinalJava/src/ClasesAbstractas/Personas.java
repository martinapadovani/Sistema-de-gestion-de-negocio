package ClasesAbstractas;

import ClasesConcretas.Empleado;
import Interfaces.GestionDeDatos;

public class Personas implements GestionDeDatos<Empleado>{
	
	//ATRIBUTOS
	
	private String nombre;
	private String apellido;
	private int dni;
	private int teléfono;
	private String email;
	private int edad;
	
	//CONSTRUCTOR
	
	public Personas(String nombre, String apellido, int dni, int teléfono, String email, int edad) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.teléfono = teléfono;
		this.email = email;
		this.edad = edad;
	}

	// METODOS
	
   //OVERRIDE
	
	@Override
	public void Ver() {
		
	}
	
	@Override
	public void Buscar(int ID) {
		
	}
	
	@Override
	public void Actualizar(Empleado empleado) {
		
	}
	
	@Override
	public void Agregar() {
		
	}
	
	@Override
	public void Eliminar(Empleado empleado) {
		
	}
	
	//TO STRING
	
	@Override
	public String toString() {
		return "Personas [nombre=" + nombre + ", apellido=" + apellido + ", dni=" + dni + ", teléfono=" + teléfono
				+ ", email=" + email + ", fechaDeRegistro=" +  "]";
	}
	


	//GETTERS-SETTERS
	public String getNombre() {
		return nombre;
	}


	public int getEdad() {
		return edad;
	}


	public void setEdad(int edad) {
		this.edad = edad;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellido() {
		return apellido;
	}


	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	public int getDNI() {
		return dni;
	}


	public void setDNI(int dNI) {
		dni = dNI;
	}


	public int getTeléfono() {
		return teléfono;
	}


	public void setTeléfono(int teléfono) {
		this.teléfono = teléfono;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}




}
