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
	
	public static String buscarCombate(int idCombate) {
		int idUsuario1 = idUsuario(idCombate, 1);
		int idUsuario2 = idUsuario(idCombate, 2);
		String nombreUsuario1 = nombreUsuario(idUsuario1);
		String nombreUsuario2 = nombreUsuario(idUsuario2);
		int[] equipo1 = equipo(idUsuario1);
		int[] equipo2 = equipo(idUsuario2);
		return combate(idCombate, idUsuario1, nombreUsuario1, equipo1, idUsuario2, nombreUsuario2, equipo2);
	}
	
	public static int idUsuario(int idCombate, int nJugador) {
		
		try {
			String query;
			Class.forName(datosMysql.driver);
			String url = datosMysql.driverUrl;
			Connection con = DriverManager.getConnection(url, datosMysql.user, datosMysql.password);
			Statement st = con.createStatement();
			if(nJugador == 1) {
				query = "SELECT jugador1 FROM combate WHERE id ="+idCombate;
			}else {
				query = "SELECT jugador2 FROM combate WHERE id ="+idCombate;
			}
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				return rs.getInt("id");
			}
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}return 0;
	}
	
	public static String nombreUsuario(int idUsuario) {
		
		try {
			String query;
			Class.forName(datosMysql.driver);
			String url = datosMysql.driverUrl;
			Connection con = DriverManager.getConnection(url, datosMysql.user, datosMysql.password);
			Statement st = con.createStatement();
			query = "SELECT nombreUsuario FROM usuario WHERE id ="+idUsuario;
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				return rs.getString("nombreUsuario");
			}
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		return null;
	}
	
	public static int[] equipo(int idUsuario) {
		ArrayList<Integer> equipoProvisional = new ArrayList<>();
		try {
			String query;
			Class.forName(datosMysql.driver);
			String url = datosMysql.driverUrl;
			Connection con = DriverManager.getConnection(url, datosMysql.user, datosMysql.password);
			Statement st = con.createStatement();
			query = "SELECT idPokemon FROM equipo WHERE idUsuario ="+idUsuario+"ORDER BY idPokemon";
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				equipoProvisional.add(rs.getInt("idPokemon"));
			}
			int[] equipo = new int[equipoProvisional.size()];
			for (int i = 0; i < equipoProvisional.size(); i++)
	            equipo[i] = equipoProvisional.get(i);
			return equipo;
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public static String combate(int idCombate, int idUsr1, String nombreUsr1, int[] equipo1, int idUsr2,String nombreUsr2, int[]equipo2) {
		//CREAM COMBAT I USUARIS
		Combate combate = new Combate();
		combate.setId(idCombate);
		combate.setUsr1(crearUsuario(idUsr1, equipo1, nombreUsr1));
		combate.setUsr2(crearUsuario(idUsr2, equipo2, nombreUsr2));
		
		int contador = 0;
		String json = "";
			try {
				Class.forName(datosMysql.driver);
				String url = datosMysql.driverUrl;
				Connection con = DriverManager.getConnection(url, datosMysql.user, datosMysql.password);
				Statement st = con.prepareStatement("Select t1.idCombate, t1.numeroTurno, t1.idUsuario, t1.idMovimiento, t1.idPokemon, t1.cambioPokemon from turnos t1, turnos t2\r\n"
						+ "where t1.numeroTurno = t2.numeroTurno and t1.idCombate = t2.idCombate and t1.nombreUsuario != t2.nombreUsuario and t1.idCombate='"+idCombate+"' limit 1, '"+contador+"'");
				String query = "";
				st.executeQuery(query);
				ResultSet rs = st.executeQuery(query);
				
				while(rs.next()) {
						if (rs.getString("nombreUsuario").equals(combate.getUsr1().getNombre())) {
							combate.setPk1(seleccionarPokemon(rs.getInt("idPokemon"), combate.getUsr1()));
							combate.setMv1(seleccionarMovimiento(combate.getPk1(), rs.getInt("idMovimiento")));
							combate.getUsr1().setCambioPokemon(rs.getBoolean("cambioPokemon"));
						}else if(rs.getString("nombreUsuario").equals(combate.getUsr2().getNombre())) {
							combate.setPk2(seleccionarPokemon(rs.getInt("idPokemon"), combate.getUsr2()));
							combate.setMv2(seleccionarMovimiento(combate.getPk2(), rs.getInt("idMovimiento")));
							combate.getUsr2().setCambioPokemon(rs.getBoolean("cambioPokemon"));
						}
						
						contador++;
						if (contador%2==0) {
							combate.usaMovimiento();
						}
				}
				
				//DEVOLVEMOS EL NOMBRE Y LA VIDA DE LOS 12 POKEMON
				for(int i=0; i<combate.getUsr1().getEquipo().size(); i++) {
					json = json +","+ combate.getUsr1().getEquipo().get(i).toJSON(combate.getUsr1().getEquipo().get(i).getNombre());
				}
				for(int i=0; i<combate.getUsr2().getEquipo().size(); i++) {
					json = json +","+ combate.getUsr2().getEquipo().get(i).toJSON(combate.getUsr2().getEquipo().get(i).getNombre());
				}
				
				return json;
			}catch(Exception e) {
				System.out.println(e.getMessage());
				return "Ha fallado el combate";
			}
		
	}
	
	public static Movimiento seleccionarMovimiento(Pokemon pokemon, int idMovimiento) {
		for(int i=0; i<pokemon.getMovimiento().length; i++) {
			if(pokemon.getMovimiento()[i].getId()==idMovimiento) {
				return pokemon.getMovimiento()[i];
			}
		}
		return null;
	}
	
	public static Pokemon seleccionarPokemon(int idPokemon, Usuario usuario) {
		for(int i=0; i<usuario.getEquipo().size(); i++) {
			if(usuario.getEquipo().get(i).getId()==idPokemon) {
				return usuario.getEquipo().get(i);
			}
		}
		return null;
	}
	
	public static Usuario crearUsuario(int id, int[]equipo, String nombreUsuario) {
				return new Usuario(id, nombreUsuario, null, null, false, crearPokemons(equipo, id));
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
				return new Movimiento(rs.getInt("id"), rs.getInt("potencia"), rs.getInt("probCritico"));
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public static boolean convertirBooleano(String string) {
		if(string.equals("true")) {
			return true;
		}else {
			return false;
		}
	}
	
	public static int[] convertirInt(String[] string) {
		int[] numeros = new int[string.length];
		for(int i = 0;i < string.length;i++)
		{
		   numeros[i] = Integer.parseInt(string[i]);
		}
		return numeros;
	}
	
	public static Movimiento insertarTurno(int idCombate, int turno, int idJugador, int idPokemon, int idMovimiento, boolean cambioPokemon) {
		try {
			Class.forName(datosMysql.driver);
			String url = datosMysql.driverUrl;
			Connection con = DriverManager.getConnection(url, datosMysql.user, datosMysql.password);
			Statement st = con.createStatement();
			String query = "INSERT INTO turno (numeroTurno, idUsuario, idMovimiento, idPokemon, cambioPokemon) VALUES ("+turno+", "+idJugador+", "+idMovimiento+", "+idPokemon+", "+cambioPokemon+")";
			st.executeUpdate(query);
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public static String sacarMovimientos(int idPokemon, int idJugador) {
		String resultat = "";
		try {
			Class.forName(datosMysql.driver);
			String url = datosMysql.driverUrl;
			Connection con = DriverManager.getConnection(url, datosMysql.user, datosMysql.password);
			Statement st = con.createStatement();
			String query = "SELECT m.id, m.nombre FROM movimiento m, equipo e WHERE e.idUsuario= "+idJugador+" AND idPokemon= "+idPokemon+" AND m.id = movimiento1 OR m.id = movimiento2 OR m.id = movimiento3 OR m.id = movimiento4";
			ResultSet rs = st.executeQuery(query);
			while(rs.next()) {
				resultat = rs.getString("m.id")+"/"+rs.getString("m.nombre")+"/";
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return resultat;
	}
	
	public static String ultimoPokemon(int idCombate) {
		String resultat = "";
		try {
			Class.forName(datosMysql.driver);
			String url = datosMysql.driverUrl;
			Connection con = DriverManager.getConnection(url, datosMysql.user, datosMysql.password);
			Statement st = con.createStatement();
			String query = "Select t1.idUsuario, t1.idPokemon from turno t1, turno t2 where t1.numeroTurno = t2.numeroTurno and t1.idCombate = t2.idCombate and t1.idUsuario != t2.idUsuario and t1.idCombate= "+idCombate+" ORDER BY t1.numeroTurno desc LIMIT 2";
			ResultSet rs = st.executeQuery(query);
			while(rs.next()) {
				resultat = rs.getString("m.id")+"/"+rs.getString("m.nombre")+"/";
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return resultat;
	}
	
	public static String combateJ1 (int idJugador) {
		try {
			Class.forName(datosMysql.driver);
			String url = datosMysql.driverUrl;
			Connection con = DriverManager.getConnection(url, datosMysql.user, datosMysql.password);
			Statement st = con.createStatement();
			String query = "SELECT jugador1 FROM combate WHERE jugador1 = "+idJugador+" AND jugador2 is not null and ganador is null order by id desc limit 1";
			ResultSet rs = st.executeQuery(query);
			while(rs.next()) {
				return "1";
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return "0";
	}

}
