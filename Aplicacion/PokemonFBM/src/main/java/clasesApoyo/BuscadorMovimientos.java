package clasesApoyo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BuscadorMovimientos {
	
	public static String mostrarTabla(String idUsuario, String idPokemon) throws SQLException, ClassNotFoundException {
		Class.forName(datosMysql.driver);
		String url = datosMysql.driverUrl;
		Connection con = DriverManager.getConnection(url, datosMysql.user, datosMysql.password);
		Statement st = con.createStatement();
		
		String query = "";
		
		query = "SELECT * from movimiento where id not in (select m.id from movimiento m, equipo e, usuario u where (m.id = e.movimiento1 or m.id = e.movimiento2 or m.id = e.movimiento3 or m.id=e.movimiento4) and e.idUsuario = u.id and u.id = '"+idUsuario+"' and e.idPokemon = '"+idPokemon+"') order by id";
	
		
		ResultSet rs = st.executeQuery(query);
		
		String resultado = "<table id=\"tablaMovimientos\">"
						+ "<tr>"
						+ "<th>Nombre</th>"
						+ "<th>Potencia</th>"
						+ "<th>Prob.Critico</th>"
						+ "</tr>";
		

		while (rs.next()) {
			resultado = resultado 
						+"<tr>"
						+ "<td>"+rs.getString("nombre")+"</td>"
						+ "<td>"+rs.getString("potencia")+"</td>"
						+ "<td>"+rs.getString("probCritico")+"</td>";
			
				resultado += "<td><button type=\"button\" onclick=\"agregarMovimiento('"+rs.getString("id")+"')\">Agregar</button></td>"
							+"</tr>";
			
		}
		
		
		resultado=resultado+"</table> <button type=\"button\" id=\"salirMenu\" onclick=\"cerrarMenuTabla()\">Salir</button>";
		return resultado;
	}
	
	public static String agregarMovimiento(String idUsuario, String idMovimiento, String idPokemon, String numeroMovimiento) {
		String nombreMovimiento = "";
		
		try {
			Class.forName(datosMysql.driver);
			String url = datosMysql.driverUrl;
			Connection con = DriverManager.getConnection(url, datosMysql.user, datosMysql.password);
			Statement st = con.createStatement();
			String query = "UPDATE equipo SET "+numeroMovimiento+" = "+idMovimiento+" where idUsuario = "+idUsuario+" and idPokemon = "+idPokemon+"";
			st.executeUpdate(query);
			
			nombreMovimiento = BuscadorMovimientos.nombreID(idMovimiento);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		return nombreMovimiento;
	}
	
	public static void quitarMovimiento(String idUsuario, String idPokemon, String numeroMovimiento) {
		
		try {
			Class.forName(datosMysql.driver);
			String url = datosMysql.driverUrl;
			Connection con = DriverManager.getConnection(url, datosMysql.user, datosMysql.password);
			Statement st = con.createStatement();
			String query = "UPDATE equipo SET "+numeroMovimiento+" = null where idUsuario = "+idUsuario+" and idPokemon = "+idPokemon+"";
			st.executeUpdate(query);
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static String actualizarMovimientos(String idUsuario, String idPokemon) {
		String resultado = "{ \"array\": [";
		int contador;
		
		try {
			Class.forName(datosMysql.driver);
			String url = datosMysql.driverUrl;
			Connection con = DriverManager.getConnection(url, datosMysql.user, datosMysql.password);
			Statement st = con.createStatement();
			
		for(contador = 1; contador < 5; contador++){
			String query = "SELECT nombre \r\n"
					+ "	from movimiento m\r\n"
					+ "    where m.id in \r\n"
					+ "		(select m.id from movimiento m, equipo e\r\n"
					+ "			where m.id = movimiento"+contador+"\r\n"
					+ "            and e.idUsuario = '"+idUsuario+"' \r\n"
					+ "			and e.idPokemon = '"+idPokemon+"') \r\n"
					+ "";
			ResultSet rs = st.executeQuery(query);
			if(rs.next()) {
				resultado+= "\""+rs.getString("nombre")+"\", ";
			}else {
				resultado+= "null, ";
			}
			
		}
			
			resultado = resultado.substring(0,resultado.length()-2)+"]}";
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return resultado;
	}
	
	public static String nombreID(String idMovimiento) {
		String resultado = "";
		
		try {
			Class.forName(datosMysql.driver);
			String url = datosMysql.driverUrl;
			Connection con = DriverManager.getConnection(url, datosMysql.user, datosMysql.password);
			Statement st = con.createStatement();
			String query = "SELECT nombre from movimiento where id = "+idMovimiento+"";
			ResultSet rs = st.executeQuery(query);
			while(rs.next()) {
				resultado = rs.getString("nombre");
			}
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return resultado;
	}
}
