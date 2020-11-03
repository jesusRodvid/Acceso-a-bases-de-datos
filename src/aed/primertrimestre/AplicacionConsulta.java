package aed.primertrimestre;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import javax.swing.*;
public class AplicacionConsulta {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		JFrame mimarco=new Marco_Aplicacion();
		
		mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mimarco.setVisible(true);

	}

}

	class Marco_Aplicacion extends JFrame{
		
		public Marco_Aplicacion(){
			
			setTitle ("Consulta BBDD");
			
			setBounds(500,300,400,400);
			
			setLayout(new BorderLayout());
			
			JPanel menus=new JPanel();
			
			menus.setLayout(new FlowLayout());
			
			nomligas=new JComboBox();
			
			nomligas.setEditable(false);
			
			nomligas.addItem("Todos");
			
			
			resultado= new JTextArea(4,50);
			
			resultado.setEditable(false);
			
			add(resultado);
			
			menus.add(nomligas);
				
			
			add(menus, BorderLayout.NORTH);
			
			add(resultado, BorderLayout.CENTER);
			
			JButton botonConsulta=new JButton("Consulta");
			
			botonConsulta.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					ejecutaConsulta();
				}
				
			});
			
			add(botonConsulta, BorderLayout.SOUTH);
			
		
			// Conexion con BBDD
			try {
				
				
				miConexion=DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_futbol","Admiche","123456789");
				
				Statement sentencia =miConexion.createStatement();
				
				String consulta ="SELECT DISTINCTROW nomLiga FROM equipos INNER JOIN ligas ON equipos.codLiga=ligas.codliga order by codEquipo";
				
				ResultSet rs=sentencia.executeQuery(consulta);
				
				while (rs.next()) {
					
					nomligas.addItem(rs.getString(1));
					
				}
				
				rs.close();
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
			
			
		}	
		

		private void ejecutaConsulta() {
			
			ResultSet rs=null;
			
			try {
				
				String nomliga= (String)nomligas.getSelectedItem();
				
				 enviarConsulta=miConexion.prepareStatement(CONSULTASELECCION);
				
				 enviarConsulta.setString(1, nomliga);
				 
				 rs=enviarConsulta.executeQuery();
				 
				 while(rs.next()) {
						
					 	resultado.append(rs.getString(1));
					 
						resultado.append(" ");
						
						resultado.append(rs.getString(2));
						 
						resultado.append(" ");
						
						resultado.append(rs.getString(3));
						 
						resultado.append(" ");
						
						resultado.append(rs.getString(4));
						 
						resultado.append(" ");
						
						resultado.append(rs.getString(5));
						
						 
						resultado.append("\n");
						
						
					}
					rs.close();
				 
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			
		}
		
		private Connection miConexion;
		
		private PreparedStatement enviarConsulta;
		
		private final  String CONSULTASELECCION="SELECT codEquipo, nomEquipo, nomLiga, localidad, internacional FROM equipos "
				+ "INNER JOIN ligas ON equipos.codLiga=ligas.codliga where nomLiga=?";
		
		private JComboBox nomligas;
		
		
		private JTextArea resultado;	
	
}