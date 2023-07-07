package ClasesConcretas;
import java.sql.*;
import java.time.LocalDate;
import ConexionDB.Conexion;
import java.util.Scanner;
import ClasesAbstractas.Personas;
import Interfaces.GestionDeDatos;


public class Empleado extends Personas implements GestionDeDatos<Empleado>{

	
	//ATRIBUTOS PARA CONEXION
	Conexion conexion = new Conexion();
	private Connection cn = null;
	
	//SCANNER
	Scanner scanner = new Scanner (System.in);
	
	//CONSTRUCTOR
	public Empleado() {
	}
	
	//METODOS
	
	public void calcularSalario(int id){	
	}
	
	public void calcularDesempeño(int id){	
	}
	
	//OVERRIDES
	
	@Override
	public void Ver() {
		
		try{
			cn = conexion.conectar();
			
			String query = "SELECT * FROM empleado";
			
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
	    System.out.println("Activo (true/false): ");
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

	        System.out.println("Datos cargados exitosamente!");
			
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
				
			if(resultados.next()) { //mientras haya datos por leer
					System.out.println(
							"ID: " + resultados.getInt("idEmpleado") + ". Horas mensuales: " +resultados.getInt("horasMensuales") + 
							". Sueldo: " + resultados.getBigDecimal("sueldo") + ". Ventas mensuales: " + resultados.getBigDecimal("ventasMensuales") + 
							". Turno: " + resultados.getString("turno") + ". Puesto: " +  resultados.getString("puesto") + 
							". Activo: " +  resultados.getBoolean("activo") + ". Fecha de Inicio: " +  resultados.getDate("fechaDeInicio") +
							". Id Persona: " +  resultados.getInt("persona_id"));
			}else {
				System.out.println("ID inválido! Vuelva a intentarlo.");
			}
			
		} catch(SQLException e){
			e.printStackTrace();
		} 
	}
	
	@Override
	public void Actualizar(int id) {
	
		try{
			cn = conexion.conectar();
			
			String querySelectEmpleado = "SELECT * FROM empleado WHERE  idEmpleado = ?";
			
			PreparedStatement declaracionSelectEmpleado  = cn.prepareStatement(querySelectEmpleado);
		
			declaracionSelectEmpleado.setInt(1, id);
			ResultSet resultadosEmpleado = declaracionSelectEmpleado.executeQuery();
			
			if(resultadosEmpleado.next()) {
				
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
			    System.out.println("Activo (true/false): ");
			    boolean activo = scanner.nextBoolean();
			    
				
				//ACTUALIZACION
				
				String queryUpdate = "UPDATE empleado SET horasMensuales = ?, sueldo = ?, ventasMensuales = ?, turno = ?, puesto = ?, activo = ? WHERE  idEmpleado = ?";
				
				PreparedStatement declaracionUpdate  = cn.prepareStatement(queryUpdate);
			
				declaracionUpdate.setInt(1, horasMensuales);
				declaracionUpdate.setDouble(2, sueldo);
				declaracionUpdate.setInt(3, ventasMensuales);
				declaracionUpdate.setString(4, turno);
				declaracionUpdate.setString(5, puesto);
				declaracionUpdate.setBoolean(6, activo);
				
				declaracionUpdate.setInt(7, id);
					
				declaracionUpdate.executeUpdate();
				
				//VER DATOS
					
				while(resultadosEmpleado.next()) { //mientras haya datos por leer
					System.out.println("Proceso exitoso! Datos actualizados: ");
						System.out.println(
								"ID: " + resultadosEmpleado.getInt("idEmpleado") + ". Horas mensuales: " +resultadosEmpleado.getInt("horasMensuales") + 
								". Sueldo: " + resultadosEmpleado.getBigDecimal("sueldo") + ". Ventas mensuales: " + resultadosEmpleado.getBigDecimal("ventasMensuales") + 
								". Turno: " + resultadosEmpleado.getString("turno") + ". Puesto: " +  resultadosEmpleado.getString("puesto") + 
								". Activo: " +  resultadosEmpleado.getBoolean("activo") + ". Fecha de Inicio: " +  resultadosEmpleado.getDate("fechaDeInicio") +
								". Id Persona: " +  resultadosEmpleado.getInt("persona_id") );
				}
			}else {
				System.out.println("ID inválido! Vuelva a intentarlo.");
			}
		} catch(SQLException e){
			e.printStackTrace();
		} 
	}
	
	@Override
	public void Eliminar(int id) {
		
		try{
			
			cn = conexion.conectar();
			
			String querySelectEmpleado = "SELECT * FROM empleado WHERE  idEmpleado = ?";
			
			PreparedStatement declaracionSelectEmpleado  = cn.prepareStatement(querySelectEmpleado);
		
			declaracionSelectEmpleado.setInt(1, id);
			ResultSet resultadosEmpleado = declaracionSelectEmpleado.executeQuery();
			
			String querySelectPersona = "SELECT * FROM persona WHERE  idPersona = ?";
			
			PreparedStatement declaracionSelectPersona  = cn.prepareStatement(querySelectPersona);
		
			declaracionSelectPersona.setInt(1, id);
			ResultSet resultadosPersona = declaracionSelectPersona.executeQuery();
			
			
			
			if(resultadosEmpleado.next() && resultadosPersona.next()) { //mientras haya datos por leer
				
				System.out.println("Seguro que desea eliminar el empleado: ");
				System.out.println(
						"ID: " + resultadosPersona.getInt("idPersona") + ". DNI: " +resultadosPersona.getInt("dni") + 
						". Telefono: " + resultadosPersona.getInt("telefonoPersona") + ". Nombre: " + resultadosPersona.getString("nombrePersona") + 
						". Apellido: " + resultadosPersona.getString("apellidoPersona") + ". Edad: " +  resultadosPersona.getInt("edad") + 
						". Email: " +  resultadosPersona.getString("email") + "ID: " + resultadosEmpleado.getInt("idEmpleado") + ". Horas mensuales: " +resultadosEmpleado.getInt("horasMensuales") + 
						". Sueldo: " + resultadosEmpleado.getBigDecimal("sueldo") + ". Ventas mensuales: " + resultadosEmpleado.getBigDecimal("ventasMensuales") + 
						". Turno: " + resultadosEmpleado.getString("turno") + ". Puesto: " +  resultadosEmpleado.getString("puesto") + 
						". Activo: " +  resultadosEmpleado.getBoolean("activo") + ". Fecha de Inicio: " +  resultadosEmpleado.getDate("fechaDeInicio") +
						". Id Persona: " +  resultadosEmpleado.getInt("persona_id") );
				System.out.println("Ingrese si/no segun desee.");
				String confirmacion = scanner.nextLine();
				
				 if(confirmacion.equalsIgnoreCase("si")) {
					 
					 try{
						 cn = conexion.conectar();
					
						 String queryDelete = "DELETE FROM empleado WHERE idEmpleado = ?";
						 //excluyo el id ya que es autoincremental
					
							PreparedStatement declaracionDelete  = cn.prepareStatement(queryDelete);
							
							declaracionDelete.setInt(1, id);
							declaracionDelete.executeUpdate();
						
						 	System.out.println("Empleado eliminado del sistema!");
					
					 } catch(SQLException e){
						 e.printStackTrace();
					 }
					 
				  }else {
					  System.out.println("Perfecto! El empleado no será eliminado");
				  }
			}else {
				System.out.println("ID inválido! Vuelva a intentarlo.");
			}
		
		}catch(SQLException e){
				e.printStackTrace();
			} 
	}
}
