package aed.primertrimestre;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Modifica {
	
public static void main (String[] args) {
		
		try {
			
			//1. Crear Conexion
			
			Connection miConexion=DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_futbol","Admiche","123456789");
			
			//2. Crear OBJETO STATEMENT
			
			Statement miStatement = miConexion.createStatement();
			
			//Sentencia de insert
			
			String instruccionSql="INSERT INTO EQUIPOS (codEquipo, nomEquipo, codLiga, localidad, internacional) VALUES (null,'JAVA', 11111, 'Los Sauces', 0)";
			
			//ejecutando la sentencia anterior
			miStatement.execute(instruccionSql);
		
		}catch(Exception e) {
			System.out.println("No conecta");
		}
		
	}

}
