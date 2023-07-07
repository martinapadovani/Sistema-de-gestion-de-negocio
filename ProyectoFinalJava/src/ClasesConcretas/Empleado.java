package ClasesConcretas;
import java.sql.*;
import java.time.LocalDate;
import ConexionDB.Conexion;
import java.util.Scanner;
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
	private LocalDate fechaDeInicio;
	
	//ATRIBUTOS PARA CONEXION
	Conexion conexion = new Conexion();
	private Connection cn = null;
	
	//SCANNER
	Scanner scanner = new Scanner (System.in);
	
	
	//CONSTRUCTOR
	public Empleado(String nombre, String apellido, int dni, int teléfono, String email, int edad, int fechaDeRegistro, int id,
			int horasMensuales, int sueldo, int ventasMensuales, String turno, String puesto, boolean activo) {
		super(nombre, apellido, dni, teléfono, email, edad);
		this.id = id;
		this.horasMensuales = horasMensuales;
		this.sueldo = sueldo;
		this.ventasMensuales = ventasMensuales;
		this.turno = turno;
		this.puesto = puesto;
		this.activo = activo;
	}
	
	
	//METODOS
	
	public void calcularSalario(){	
	}
	
	public void calcularDesempeño(){	
	}
	
	
	//OVERRIDES
	
	@Override
	public void Ver() {
		
		try{
			cn = conexion.conectar();
			
			String query = "SELECT * FROM persona";
			
			Statement declaracion = cn.createStatement();
			ResultSet resultados = declaracion.executeQuery(query);
			
			while(resultados.next()) { //mientras haya datos por leer
				System.out.println(
						"ID: " + resultados.getInt("idEmpleado") + ". Horas mensuales: " +resultados.getInt("horasMensuales") + 
						". Sueldo: " + resultados.getBigDecimal("sueldo") + ". Ventas mensuales: " + resultados.getBigDecimal("ventasMensuales") + 
						". Turno: " + resultados.getString("turno") + ". Puesto: " +  resultados.getString("puesto") + 
						". Activo: " +  resultados.getBoolean("activo") + ". Fecha de Inicio: " +  resultados.getDate("fechaDeInicio") +
						". Id Persona: " +  resultados.getInt("persona_id"));
			}
			
		} catch(SQLException e){
			e.printStackTrace();
		} 
	}
	
	@Override
	public void Agregar() {
		
	
	    // Obtener los datos personales
	    System.out.println("DNI: ");
	    int dni = scanner.nextInt();
	    System.out.println("Telefono: ");
	    int telefono = scanner.nextInt();
	    System.out.println("Nombre: ");
	    String nombre = scanner.next().trim().replace(" ", "_");
	    System.out.println("Apellido:");
	    String apellido = scanner.next().trim().replace(" ", "_");
	    System.out.println("Edad: ");
	    int edad = scanner.nextInt();
	    System.out.println("Email: ");
	    String email = scanner.next().trim().replace(" ", "_");

	    // Obtener los datos laborales
	    System.out.println("Horas mensuales: ");
	    int horasMensuales = scanner.nextInt();
	    System.out.println("Sueldo: ");
	    double sueldo = scanner.nextDouble();
	    System.out.println("Ventas Mensuales: ");
	    int ventasMensuales = scanner.nextInt();
	    System.out.println("Turno: ");
	    String turno = scanner.next().trim().replace(" ", "_");
	    System.out.println("Puesto: ");
	    String puesto = scanner.next().trim().replace(" ", "_");
	    System.out.println("Activo (si/no): ");
	    boolean activo = scanner.nextBoolean();
	    
	    //Obtener fecha de inicio
        LocalDate fechaPredeterminada = LocalDate.now();
        // .now() de la clase LocalDate, para obtener la fecha actual.
        Date fechaDeInicio = Date.valueOf(fechaPredeterminada);
        /* .valueOf(fechaPredeterminada) para convertir fechaPredeterminada de tipo LocalDate 
            a java.sql.Date, para luego establecer su valor  en la consulta de inserción */
	    
		try{
			cn = conexion.conectar();
			
	        // Insertar datos personales en la tabla persona
	        String queryPersona = "INSERT INTO persona (dni, telefonoPersona, nombrePersona, apellidoPersona, edad, email) VALUES (?, ?, ?, ?, ?, ?)";
	        PreparedStatement declaracionPersona = cn.prepareStatement(queryPersona, Statement.RETURN_GENERATED_KEYS);
	        //Utilizo .RETURN_GENERATED_KEYS al crear el PreparedStatement para que los ID generados sean devueltos por la consulta.

	        declaracionPersona.setInt(1, dni);
	        declaracionPersona.setInt(2, telefono);
	        declaracionPersona.setString(3, nombre);
	        declaracionPersona.setString(4, apellido);
	        declaracionPersona.setInt(5, edad);
	        declaracionPersona.setString(6, email);

	        declaracionPersona.executeUpdate();

	        // Obtener el ID generado para persona
	        ResultSet rs = declaracionPersona.getGeneratedKeys();
	        //obtenemos los ID generados utilizando getGeneratedKeys() en el PreparedStatement, luego de haberlo ejecutado 
	        // Devolvera un ResultSet que contiene las claves generadas automáticamente
	        int personaId = 0; 
	        if (rs.next()) {
	            personaId = rs.getInt(1);//Llamo a rs.getInt(1), para obtener el valor del ID generado para la persona recién insertada,
	            //y lo almacenamos en la variable personaId.
	        }
	        //Para proximamente utilizar la variable personaId como el valor de persona_id en la inserción de la tabla empleado.
	        //De esta manera, el valor de persona_id en la tabla empleado coincidirá con el ID autoincremental generado en la tabla persona.
	        
	        // Insertar datos laborales en la tabla empleado
	        String queryEmpleado = "INSERT INTO empleado (horasMensuales, sueldo, ventasMensuales, turno, puesto, activo, fechaDeInicio, persona_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	        PreparedStatement declaracionEmpleado = cn.prepareStatement(queryEmpleado);

	        declaracionEmpleado.setInt(1, horasMensuales);
	        declaracionEmpleado.setDouble(2, sueldo);
	        declaracionEmpleado.setInt(3, ventasMensuales);
	        declaracionEmpleado.setString(4, turno);
	        declaracionEmpleado.setString(5, puesto);
	        declaracionEmpleado.setBoolean(6, activo);
	        declaracionEmpleado.setDate(7, fechaDeInicio);
	        declaracionEmpleado.setInt(8, personaId);

	        declaracionEmpleado.executeUpdate();

	        System.out.println("Datos cargadosexitosamente!");
			
		} catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	@Override
	public void Buscar(int id) {
		
		try{
			cn = conexion.conectar();
			
			String query = "SELECT * FROM empleado WHERE  idEmpleado = ?";
			
			PreparedStatement declaracion  = cn.prepareStatement(query);
			
				declaracion.setInt(1, id);
				ResultSet resultados = declaracion.executeQuery();
				
			while(resultados.next()) { //mientras haya datos por leer
					System.out.println(
							"ID: " + resultados.getInt("idEmpleado") + ". Horas mensuales: " +resultados.getInt("horasMensuales") + 
							". Sueldo: " + resultados.getBigDecimal("sueldo") + ". Ventas mensuales: " + resultados.getBigDecimal("ventasMensuales") + 
							". Turno: " + resultados.getString("turno") + ". Puesto: " +  resultados.getString("puesto") + 
							". Activo: " +  resultados.getBoolean("activo") + ". Fecha de Inicio: " +  resultados.getDate("fechaDeInicio") +
							". Id Persona: " +  resultados.getInt("persona_id"));
			}
			
		} catch(SQLException e){
			e.printStackTrace();
		} 
	}
	
	@Override
	public void Actualizar(Empleado empleado) {
	}
	
	@Override
	public void Eliminar(Empleado empleado) {
	}
	
	
	//TO STRING
	@Override
	public String toString() {
		return "Empleado: id=" + id + ", horasMensuales=" + horasMensuales + ", sueldo=" + sueldo + ", ventasMensuales="
				+ ventasMensuales + ", turno=" + turno + ", puesto=" + puesto + ", activo=" + activo;
	}

	
	//GETTERS-SETTERS

	public int getID() {
		return id;
	}

	public void setID(int id) {
		this.id = id;
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
	
	public LocalDate getFechaDeInicio() {
		return fechaDeInicio;
	}

	public void setFechaDeInicio(LocalDate fechaDeInicio) {
		this.fechaDeInicio = fechaDeInicio;
	}
}
