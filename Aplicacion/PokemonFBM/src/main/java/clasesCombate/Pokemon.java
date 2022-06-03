package clasesCombate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import clasesApoyo.datosMysql;

public class Pokemon {
	
	private int id;
	private String nombre;
	// HP, DEFENSA, DMG, VELOCIDAD
	private int[] stats;
	private Movimiento[] movimiento;
	private static boolean cambio = true;
	private boolean vivo = true;

	public Pokemon(int id, String nombre, int[] stats, Movimiento[] movimiento, boolean vivo) {
		this.setId(id);
		this.stats = new int[4];
		this.setNombre(nombre);
		this.setStats(stats);
		this.setMovimiento(movimiento);
		this.vivo = vivo;
	}
	
	public void crearPokemon(int[] pokemons, Usuario usr, int jugador, boolean cambio, int idCombate) {
		if(jugador == 1) {
			
		}
	}

	public String getNombre() {return nombre;}
	public void setNombre(String nombre) {this.nombre = nombre;}
	
	public void setStats(int[] stats) {this.stats = stats;}	
	public int[] getStats() {return stats;}
	
	public Movimiento[] getMovimiento() {return movimiento;}
	public void setMovimiento(Movimiento[] movimiento) {this.movimiento = movimiento;}
	
	public static boolean isCambio() {return cambio;}
	public static void setCambio(boolean cambio) {Pokemon.cambio = cambio;}
	
	public boolean isVivo() {return vivo;}
	public void setVivo(boolean vivo) {this.vivo = vivo;}
	
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	
	
	
	public void takeDmg(int damage) {
		stats[0] -= damage; 
		
		if(stats[0]<0) {
			stats[0]=0;
			this.setVivo(false);
		}
	}

	public String toJSON(String nombre) {
		try {
			Class.forName(datosMysql.driver);
			String url = datosMysql.driverUrl;
			Connection con = DriverManager.getConnection(url, datosMysql.user, datosMysql.password);
			Statement st = con.createStatement();
			String query = "SELECT rutaImagen FROM pokemon WHERE nombre = '"+nombre+"'";
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()) {
				return "{\"nombre\":\""+getNombre()+"\", \"vida\":\""+getStats()[0]+"\", \"imagen\":"+rs.getString("rutaImagen")+"\"}";
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return "Ha fallado la conversión a JSON";
	}

	
}
