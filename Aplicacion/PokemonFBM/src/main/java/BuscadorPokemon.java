import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BuscadorPokemon {
	
	public static String mostrarTabla(String nombre) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/pokedexdb";
		Connection con = DriverManager.getConnection(url, "root", "123456Fran");
		Statement st = con.createStatement();
		
		String query = "";
		
		if(nombre == "") {
			query = "SELECT nombre, vida, ataque, defensa, velocidad FROM pokemon p";
		}else{
			query = "SELECT nombre, vida, ataque, defensa, velocidad FROM pokemon p WHERE nombre like '"+nombre+"%'";
		}
		
		
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
