package ClasesAbstractas;

public abstract class Personas {
	
	//ATRIBUTOS
	
	private String nombre;
	private String apellido;
	private int DNI;
	private int teléfono;
	private String email;
	private int fechaDeRegistro;
	
	//CONSTRUCTOR
	
	public Personas(String nombre, String apellido, int dNI, int teléfono, String email, int fechaDeRegistro) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		DNI = dNI;
		this.teléfono = teléfono;
		this.email = email;
		this.fechaDeRegistro = fechaDeRegistro;
	}
	
	//GETTERS-SETTERS
	
	public String getNombre() {
		return nombre;
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
		return DNI;
	}


	public void setDNI(int dNI) {
		DNI = dNI;
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


	public int getFechaDeRegistro() {
		return fechaDeRegistro;
	}


	public void setFechaDeRegistro(int fechaDeRegistro) {
		this.fechaDeRegistro = fechaDeRegistro;
	}




}
