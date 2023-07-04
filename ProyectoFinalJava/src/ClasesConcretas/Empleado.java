package ClasesConcretas;
import ClasesAbstractas.Personas;
import Interfaces.GestionDeDatos;


public class Empleado extends Personas implements GestionDeDatos<Empleado>{

	//ATRIBUTOS
	
	private int id;
	private int horasMensuales;
	private int sueldo;
	private int ventasMensuales;
	private String turno;
	private String puesto;
	private boolean activo;
	
	
	//CONSTRUCTOR
	
	public Empleado(String nombre, String apellido, int dni, int teléfono, String email, int fechaDeRegistro, int id,
			int horasMensuales, int sueldo, int ventasMensuales, String turno, String puesto, boolean activo) {
		super(nombre, apellido, dni, teléfono, email, fechaDeRegistro);
		this.id = id;
		this.horasMensuales = horasMensuales;
		this.sueldo = sueldo;
		this.ventasMensuales = ventasMensuales;
		this.turno = turno;
		this.puesto = puesto;
		this.activo = activo;
	}
	
	@Override
	public String toString() {
		return "Empleado [id=" + id + ", horasMensuales=" + horasMensuales + ", sueldo=" + sueldo + ", ventasMensuales="
				+ ventasMensuales + ", turno=" + turno + ", puesto=" + puesto + ", activo=" + activo + "]";
	}

	
	//GETTERS-SETTERS

	public int getID() {
		return id;
	}

	public void setID(int id) {
		id = id;
	}

	public int getHorasMensuales() {
		return horasMensuales;
	}

	public void setHorasMensuales(int horasMensuales) {
		this.horasMensuales = horasMensuales;
	}

	public int getSueldo() {
		return sueldo;
	}

	public void setSueldo(int sueldo) {
		this.sueldo = sueldo;
	}

	public int getVentasMensuales() {
		return ventasMensuales;
	}

	public void setVentasMensuales(int ventasMensuales) {
		this.ventasMensuales = ventasMensuales;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	public String getPuesto() {
		return puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	
	//OVERRIDES
	
	@Override
	public void Agregar(Empleado empleado) {
	}
	
	@Override
	public void Buscar(int id) {
	}
	
	@Override
	public void Actualizar(Empleado empleado) {
	}
	
	@Override
	public void Eliminar(Empleado empleado) {
	}
}
