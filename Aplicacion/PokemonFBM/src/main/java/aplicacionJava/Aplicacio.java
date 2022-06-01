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
			String query = "SELECT id, nombreUsuario, fechaCreacion, numVictorias FROM usuario";
			ResultSet rs = st.executeQuery(query);
			
			String resultat = "Id     Nombre     Fecha de creacion     Numero de victorias";
			System.out.println(resultat);
	
			while (rs.next()) {
				resultat = rs.getString("id")+"     " +rs.getString("nombreUsuario")+"       "+rs.getString("fechaCreacion")+"     "+rs.getString("numVictorias");
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
			String query = "SELECT id, nombre, vida, defensa, ataque, velocidad FROM pokemon";
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
			String query = "SELECT id, jugador1, jugador2, ganador FROM combate";
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
				System.out.println("Selecciona una de las siguientes opciones \n 1.- Ver todos los usuarios \n 2.- Ver todos los Pokemons \n 3.- Ver el registro de Combates \n 4.- Salir \n (los unicos valores disponibles son 1, 2, 3 o 4)");
				respuesta = teclat.nextLine();
				switch(respuesta) {
				case "1":
					espaciado();
					System.out.println("-------------------USUARIOS-----------------");
					verUsuarios();
					espaciado();
					break;
				case "2":
					espaciado();
					System.out.println("-------------------POKEMON-----------------");
					verPokemons();
					espaciado();
					break;
				case "3":
					espaciado();
					System.out.println("-------------------COMBATES-----------------");
					verCombates();
					espaciado();
					break;
				case "4":
					a=true;
					break;
				}
			}catch (Exception e) {
				System.out.println("Has introducido una opcion incorrecta");
			}
		}
		
		teclat.close();
	}
	
	public static void espaciado() {
		int numeroEspacios = 2;
		
		for(int i = 0;i<numeroEspacios;i++) {
			System.out.println();
		}
	}

}
