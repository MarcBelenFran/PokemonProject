package aplicacionJava;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import clasesApoyo.datosMysql;

public class Aplicacio {
	public static void verUsuarios() {
		
		try {
			Class.forName(datosMysql.driver);
			String url = datosMysql.driverUrl;
			Connection con = DriverManager.getConnection(url, datosMysql.user, datosMysql.password);
			Statement st = con.createStatement();
			String query = "SELECT id, nombreUsuarios, fechaCreacion, numVictorias  FROM usuario";
			st.executeUpdate(query);
			ResultSet rs = st.executeQuery(query);
			
			String resultat = "Id     Nombre     Fecha de creacion     Numero de victorias";
			System.out.println(resultat);
	
			while (rs.next()) {
				resultat = rs.getString("id")+"   "+rs.getString("nom")+"   "+rs.getString("fechaCreacion")+"   "+rs.getString("numVictorias");
				System.out.println(resultat);
			}
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void verPokemons() {
		
		try {
			Class.forName(datosMysql.driver);
			String url = datosMysql.driverUrl;
			Connection con = DriverManager.getConnection(url, datosMysql.user, datosMysql.password);
			Statement st = con.createStatement();
			String query = "SELECT id, nombre, vida, defensa, ataque, velocidad FROM usuario";
			st.executeUpdate(query);
			ResultSet rs = st.executeQuery(query);
			
			String resultat = "Id     Nombre     Vida     Defensa     Ataque     Velocidad";
			System.out.println(resultat);
			
			while (rs.next()) {
				resultat = rs.getString("id")+"   "+rs.getString("nombre")+"   "+rs.getString("vida")+"   "+rs.getString("defensa")+"   "+rs.getString("ataque")+"   "+rs.getString("velocidad");
				System.out.println(resultat);
			}
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void verCombates() {
		
		try {
			Class.forName(datosMysql.driver);
			String url = datosMysql.driverUrl;
			Connection con = DriverManager.getConnection(url, datosMysql.user, datosMysql.password);
			Statement st = con.createStatement();
			String query = "SELECT id, jugador1, jugador2, ganador FROM usuario";
			st.executeUpdate(query);
			ResultSet rs = st.executeQuery(query);
			
			String resultat = "Id     Nombre J1     Nombre J2     Ganador del Combate";
			System.out.println(resultat);
			
			while (rs.next()) {
				resultat = rs.getString("id")+"   "+rs.getString("jugador1")+"   "+rs.getString("jugador2")+"   "+rs.getString("ganador");
				System.out.println(resultat);
			}
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void main(String[] args) {
		

	}

}
