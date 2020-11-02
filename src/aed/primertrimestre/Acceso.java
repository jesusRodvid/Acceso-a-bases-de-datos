package aed.primertrimestre;
import java.sql.*;


public class Acceso {
	
	public static void main (String[] args) {
		
		try {
			
			//1. Crear Conexion
			Connection miConexion=DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_futbol","Admiche","123456789");
			//2. Crear OBJETO STATEMENT
			Statement miStatement = miConexion.createStatement();
			//3. ejecutar Sql
			ResultSet miResulset=miStatement.executeQuery("SELECT codEquipo, nomEquipo, nomLiga, localidad, internacional FROM equipos INNER JOIN ligas ON equipos.codLiga=ligas.codliga order by codEquipo");
			//4. Recorrer miResulset. Mientras haya un registro en equipos se va imprimir lo del bucle con .next
			
			while(miResulset.next()) {
				/* System.out.println(miResulset.getString("codEquipo") + " " + 
			miResulset.getString("nomEquipo") + " " + miResulset.getString("nomLiga") + " " +
						miResulset.getString("localidad") + " " + miResulset.getString("internacional"));*/
				
				//4.1 hecho como en el ejemplo del profesor
				String codEquipo = miResulset.getString(1);
				String nomEquipo = miResulset.getString(2);
				String codLiga = miResulset.getString(3);
				String localidad = miResulset.getString(4);
				String internacional = miResulset.getString(5); 

				System.out.println(codEquipo+","+nomEquipo+","+codLiga+","+localidad+","+internacional); 
			}
		
		}catch(SQLException e) {
			e.printStackTrace();
		
		}
		
	}

}
