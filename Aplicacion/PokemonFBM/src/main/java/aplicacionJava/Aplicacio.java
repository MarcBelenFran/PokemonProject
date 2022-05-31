package aplicacionJava;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

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
		String respuesta = "";
		Scanner teclat = new Scanner(System.in);
		boolean a = false;
		while(!a) {
			try {
				System.out.println("Selecciona una de las siguientes opciones \n 1.- Ver todos los usuarios \n 2.- Ver todos los Pokemons \n 3.- Ver el registro de Combates \n 3.- Salir \n (los unicos valores disponibles son 1, 2, 3 o 4)");
				respuesta = teclat.nextLine();
				switch(respuesta) {
				case "1":
					verUsuarios();
				case "2":
					verPokemons();
				case "3":
					verCombates();
				case "4":
					a=true;
				}
			}catch (Exception e) {
				System.out.println("Has introducido una opcion incorrecta");
			}
			
			switch(respuesta) {
			case "1":
			}
		}
		
		teclat.close();
	}

}
