package clasesApoyo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BuscadorMovimientos {
	public static String mostrarTabla(String idUsuario, String idPokemon, String tipoTabla) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/pokedexdb";
		Connection con = DriverManager.getConnection(url, "root", "123456Fran");
		Statement st = con.createStatement();
		
		String query = "";
		
		if(tipoTabla.equals("agregar")) {
			query = "SELECT * from movimiento where id not in (select m.id from movimiento m, equipo e, usuario u where (m.id = e.movimiento1 or m.id = e.movimiento2 or m.id = e.movimiento3 or m.id=e.movimiento4) and e.idUsuario = u.id and u.id = '"+idUsuario+"' and e.idPokemon = '"+idPokemon+"') order by id";
		}
		
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
			
			if(tipoTabla.equals("agregar")) {
				resultado += "<td><button type=\"button\" onclick=\"agregarMovimiento('"+rs.getString("id")+"')\">Agregar</button></td>"
							+"</tr>";
			}else {
				resultado += "<td><button type=\"button\" onclick=\"agregarMovimiento('"+rs.getString("id")+"')\">Quitar</button></td>"
						+"</tr>";
			}
			
		}
		
		
		resultado=resultado+"</table> <button type=\"button\" id=\"salirMenu\" onclick=\"cerrarMenuTabla()\">Salir</button>";
		return resultado;
	}
	
	public static String agregarMovimiento(String idUsuario, String idMovimiento, String idPokemon) {
		String nombreMovimiento = "";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/pokedexdb";
			Connection con = DriverManager.getConnection(url, "root", "123456Fran");
			Statement st = con.createStatement();
			String query = "UPDATE TABLE equipo SET ";
			st.executeUpdate(query);
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		return nombreMovimiento;
	}
	
	public static String quitarPokemon(String idUsuario, String idMovimiento, String idPokemon) {
		String nombreMovimiento = "";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/pokedexdb";
			Connection con = DriverManager.getConnection(url, "root", "123456Fran");
			Statement st = con.createStatement();
			String query = "DELETE FROM movimientoPokemon where pokemon = '"+idUsuario+"' and idPokem)";
			st.executeUpdate(query);
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		return nombreMovimiento;
	}
	
}
