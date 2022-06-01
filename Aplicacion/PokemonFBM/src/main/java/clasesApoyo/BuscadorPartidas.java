package clasesApoyo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BuscadorPartidas {
	
	/**
	 * Metodo mostrarTablaPartidas. Muestra la lista de todos los combates que hay disponibles para unirse.
	 * @param idUsuario
	 * @return tabla completa de los combates disponibles.
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static String mostrarTablaPartidas(String idUsuario) throws SQLException, ClassNotFoundException {
		Class.forName(datosMysql.driver);
		String url = datosMysql.driverUrl;
		Connection con = DriverManager.getConnection(url, datosMysql.user, datosMysql.password);
		Statement st = con.createStatement();
		
		int contador = 0;
		String query = "";
		query = "SELECT jugador1, id FROM combate WHERE jugador1 != "+idUsuario+" and jugador2 is null";

		
		ResultSet rs = st.executeQuery(query);
		
		String resultado = "<table id=\"tablaPartidas\">"
						+ "<tr>"
						+ "<th>Id</th>"
						+ "<th>Nombre jugador</th>"
						+ "</tr>";
		
		while (rs.next()) {
			contador++;
			resultado = resultado 
						+"<tr>"
						+ "<td>"+contador+"</td>"
						+ "<td>"+BuscadorUsuario.nombreID(rs.getString("jugador1"))+"</td>"
						+ "<td><button onclick=\"unirseCombate('"+rs.getString("id")+"')\" class=\"boton\">Unirse</button></td>"
						+"<tr>";						
		}
		
		resultado=resultado+"</table>";
		
		return resultado;
	}
	
	/**
	 * Metodo crearPartida. Crea una partida en la base de datos con jugador1 el usuario que ha hecho la peticion.
	 * @param idUsuario. Usuario que hace la peticion.
	 */
	public static void crearPartida(String idUsuario){
		
		try {
			Class.forName(datosMysql.driver);
			String url = datosMysql.driverUrl;
			Connection con = DriverManager.getConnection(url, datosMysql.user, datosMysql.password);
			Statement st = con.createStatement();
			String query = "INSERT INTO combate(jugador1) VALUES ('"+idUsuario+"')";
			st.executeUpdate(query);
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public static void unirsePartida(String idUsuario, String idCombate) {
		System.out.println(idUsuario);
		System.out.println(idCombate);
		try {
			Class.forName(datosMysql.driver);
			String url = datosMysql.driverUrl;
			Connection con = DriverManager.getConnection(url, datosMysql.user, datosMysql.password);
			Statement st = con.createStatement();
			String query = "UPDATE combate SET jugador2 = "+idUsuario+" WHERE id = "+idCombate+" order by id desc limit 1";
			st.executeUpdate(query);

		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	/**
	 * Metodo eliminarPartida. Elimina la partida la cual el jugador1 ha hecho la peticion.
	 * @param idUsuario. Usuario que hace la peticion.
	 */
	public static void eliminarPartida(String idUsuario) {

		try {
			Class.forName(datosMysql.driver);
			String url = datosMysql.driverUrl;
			Connection con = DriverManager.getConnection(url, datosMysql.user, datosMysql.password);
			Statement st = con.createStatement();
			String query = "DELETE FROM pokedexdb.combate WHERE jugador1 = "+ idUsuario +" ORDER BY id DESC LIMIT 1; ";
			st.executeUpdate(query);

		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
}
