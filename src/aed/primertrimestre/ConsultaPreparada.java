package aed.primertrimestre;

import java.sql.*;

public class ConsultaPreparada {
	
public static void main (String[] args) {
		
		try {
	//1. Crear Conexion
	Connection miConexion=DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_futbol","Admiche","123456789");
	//2. preparar consulta
	PreparedStatement miSentencia=miConexion.prepareStatement("SELECT codEquipo, nomEquipo, nomLiga, localidad, internacional FROM equipos"
			+ " INNER JOIN ligas ON equipos.codLiga=ligas.codliga where codEquipo=?");
	//3. Establecer parametros consulta
	miSentencia.setInt(1, 23);
	
	//4. Ejecutar y recorrer consulta
	ResultSet rs = miSentencia.executeQuery();
	
	while(rs.next()) {
		
		System.out.println(rs.getString(1) + " " + 
				rs.getString(2) + " " + rs.getString(3) + " " +
				rs.getString(4) + " " + rs.getString(5));
		
	}
	rs.close();
	
	//5 imprimir error
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}