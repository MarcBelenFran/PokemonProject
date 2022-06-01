package clasesApoyo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import clasesCombate.Combate;
import clasesCombate.Movimiento;
import clasesCombate.Pokemon;
import clasesCombate.Usuario;

public class BuscadorCombates {
	
	//PARA EL COMBATE TENEMOS QUE:
		// 1.- COLOCAR LOS DATOS DE LOS DOS JUGADORES EN USR1 Y USR2 DE COMBATE INCLUYENDO EL EQUIPO POKEMON
			//CREAR POKEMON
			//CREAR EQUIPO
			//CREAR JUGADOR
		// (AQUI EMPEZARIA EL COMBATE)
		// 2.- POR CADA LINEA QUE SAQUEMOS DE TURNO TENEMOS QUE BUSCAR A QUE USUARIO PERTENECE Y AÑADIR CON QUE POKEMON ESTA ATACANDO
		// (SE IRIA ACTUALIZANDO LA ARRAY DEL EQUIPO POKEMON)
		// 3.- AL FINAL DE LA SELECT SE TIENE QUE DEVOLVER EL ARRAY EN FORMATO JSON AL JS
	
	public static void combate(int idCombate, int idUsr1, int[] equipo1, boolean cambio1, int idUsr2, int[]equipo2, boolean cambio2) {
		//CREAM COMBAT I USUARIS
		Combate combate = new Combate();
		combate.setId(idCombate);
		combate.setUsr1(crearUsuario(idUsr1, equipo1, cambio1));
		combate.setUsr2(crearUsuario(idUsr2, equipo2, cambio2));
		
		
	}
	
	public static Usuario crearUsuario(int id, int[]equipo, boolean cambioPokemon) {
		try {
			Class.forName(datosMysql.driver);
			String url = datosMysql.driverUrl;
			Connection con = DriverManager.getConnection(url, datosMysql.user, datosMysql.password);
			Statement st = con.createStatement();
			String query = "SELECT id, nombreUsuario FROM equipo WHERE id= '"+id+"'";
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()) {	
				Usuario usuario = new Usuario(rs.getInt("id"), rs.getString("nombreUsuario"), null, null, cambioPokemon, crearPokemons(equipo, id));
				return usuario;
				}			
	}catch(Exception e) {
		System.out.println(e.getMessage());
	}
	return null;
	}
	
	public static ArrayList<Pokemon> crearPokemons(int[]equipo, int idUsuario) {
		ArrayList<Pokemon> nuevoEquipo = new ArrayList<Pokemon>(); 
		try {
			for(int i=0; i<equipo.length; i++) {
				Class.forName(datosMysql.driver);
				String url = datosMysql.driverUrl;
				Connection con = DriverManager.getConnection(url, datosMysql.user, datosMysql.password);
				Statement st = con.createStatement();
				String query = "SELECT id, nombre, vida, defensa, ataque, velocidad  FROM pokemon WHERE idPokemon = '"+equipo[i]+"'";
				ResultSet rs = st.executeQuery(query);
				
				while(rs.next()) {
					int[] stats = {rs.getInt("vida"), rs.getInt("defensa"), rs.getInt("ataque"), rs.getInt("velocidad")};
					
					Pokemon pokemon = new Pokemon(rs.getInt("id"), rs.getString("nombre"), stats, movimientos(equipo[i], idUsuario), true);
					nuevoEquipo.add(pokemon);
				}
			}
			return nuevoEquipo;
	}catch(Exception e) {
		System.out.println(e.getMessage());
	}
	return null;
}
	
	
	public static Movimiento[] movimientos(int idPokemon, int idUsuario) {
		Movimiento[] aMovimientos = new Movimiento[4];
		try {
				Class.forName(datosMysql.driver);
				String url = datosMysql.driverUrl;
				Connection con = DriverManager.getConnection(url, datosMysql.user, datosMysql.password);
				Statement st = con.createStatement();
				String query = "SELECT movimiento1, movimiento2, movimiento3, movimiento4 FROM equipo WHERE idUsuario= '"+idUsuario+"' AND idPokemon = '"+idPokemon+"'";
				ResultSet rs = st.executeQuery(query);
				
				while(rs.next()) {
					int[] movimientos = {rs.getInt("movimiento1"), rs.getInt("movimiento2"), rs.getInt("movimiento3"), rs.getInt("movimiento4")};
					
					//creaMovimiento
					for(int i = 0;i<movimientos.length;i++) {
						aMovimientos[i]=crearMovimiento(i);
					}
				}			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	
	
	
	public static Movimiento crearMovimiento(int movimientos) {
		try {
			Class.forName(datosMysql.driver);
			String url = datosMysql.driverUrl;
			Connection con = DriverManager.getConnection(url, datosMysql.user, datosMysql.password);
			Statement st = con.createStatement();
			String query = "SELECT id, nombre, potencia, probCritico FROM movimiento WHERE id= '"+movimientos+"'";
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()) {
				Movimiento movimiento = new Movimiento(rs.getInt("id"), rs.getInt("potencia"), rs.getInt("probCritico"));
				return movimiento;
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	
	
	
	public static void turnos(int idCombate) {
		Combate combate = new Combate();
		try {
			Class.forName(datosMysql.driver);
			String url = datosMysql.driverUrl;
			Connection con = DriverManager.getConnection(url, datosMysql.user, datosMysql.password);
			Statement st = con.prepareStatement("Select t1.idCombate, t1.numeroTurno, t1.nombreUsuario, t1.idMovimiento, t1.idPokemon, t1.cambioPokemon from turnos t1, turnos t2\r\n"
					+ "where t1.numeroTurno = t2.numeroTurno and t1.idCombate = t2.idCombate and t1.nombreUsuario != t2.nombreUsuario and t1.idCombate='"+idCombate+"'");
			String query = "";
			st.executeQuery(query);
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()) {
				for (int i=0; i<2; i++) {
					if (rs.getString("nombreUsuario").equals(combate.getUsr1().getNombre())) {
						
					}else if(rs.getString("nombreUsuario").equals(combate.getUsr2().getNombre())) {
						
					}
				}
			}
				
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
