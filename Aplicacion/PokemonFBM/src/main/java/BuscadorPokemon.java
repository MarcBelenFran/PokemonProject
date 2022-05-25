import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BuscadorPokemon {
	
	public static String mostrarTabla(String nombre, String tipo) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/pokedexdb";
		Connection con = DriverManager.getConnection(url, "root", "123456Fran");
		Statement st = con.createStatement();
		
		String query = "";
		
		if(nombre == "" && tipo == "") {
			query = "SELECT p.nombre, vida, ataque, defensa, velocidad, t.nombre as tipo FROM pokemon p, tipo t WHERE p.tipoID = t.id";
		}else if(nombre != "" && tipo == ""){
			query = "SELECT p.nombre, vida, ataque, defensa, velocidad, t.nombre as tipo FROM pokemon p, tipo t WHERE p.tipoID = t.id and p.nombre like '"+nombre+"%'";
		}else if(nombre != "" && tipo == ""){
			query = "SELECT p.nombre, vida, ataque, defensa, velocidad, t.nombre as tipo FROM pokemon p, tipo t WHERE p.tipoID = t.id and t.nombre = '"+tipo+"'";
		}else {
			query = "SELECT p.nombre, vida, ataque, defensa, velocidad, t.nombre as tipo FROM pokemon p, tipo t WHERE p.tipoID = t.id and p.nombre like '"+nombre+"%' and t.nombre = '"+tipo+"'";
		}
		
		
		ResultSet rs = st.executeQuery(query);
		
		String resultado = "<table id=\"tablaPokemon\">"
						+ "<tr>"
						+ "<th>Nombre</th>"
						+ "<th>Vida</th>"
						+ "<th>Ataque</th>"
						+ "<th>Defensa</th>"
						+ "<th>Velocidad</th>"
						+ "<th>Tipo</th>"
						+ "<th>Opcion</th>"
						+ "</tr>";
		
		while (rs.next()) {
			resultado = resultado 
						+"<tr>"
						+ "<td>"+rs.getString("nombre")+"</td>"
						+ "<td>"+rs.getString("vida")+"</td>"
						+ "<td>"+rs.getString("ataque")+"</td>"
						+ "<td>"+rs.getString("defensa")+"</td>"
						+ "<td>"+rs.getString("velocidad")+"</td>"
						+ "<td>"+rs.getString("tipo")+"</td>"
						+ "<td><button type=\"button\">Agregar</button></td>"
						+"</tr>";
		}
		resultado=resultado+"</table>";
		return resultado;
	}
	
	public static String filtroNombre(String texto) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/pokedexdb";
		Connection con = DriverManager.getConnection(url, "root", "123456Fran");
		Statement st = con.createStatement();
		
		String query = "SELECT nombre, vida, ataque, defensa, velocidad FROM pokemon WHERE nombre like "+texto+"%";
		ResultSet rs = st.executeQuery(query);
		
		String resultado = "<table id=\"tablaPokemon\">"
						+ "<tr>"
						+ "<th>Nombre</th>"
						+ "<th>Vida</th>"
						+ "<th>Ataque</th>"
						+ "<th>Defensa</th>"
						+ "<th>Velocidad</th>"
						+ "<th>Opcion</th>"
						+ "</tr>";
		
		while (rs.next()) {
			resultado = resultado 
						+"<tr>"
						+ "<td>"+rs.getString("nombre")+"</td>"
						+ "<td>"+rs.getString("vida")+"</td>"
						+ "<td>"+rs.getString("ataque")+"</td>"
						+ "<td>"+rs.getString("defensa")+"</td>"
						+ "<td>"+rs.getString("velocidad")+"</td>"
						+ "<td><button type=\"button\">Agregar</button></td>"
						+"</tr>";
		}
		resultado=resultado+"</table>";
		return resultado;
	}
}
