package clasesApoyo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BuscadorPartidas {
	public static String mostrarTablaPartidas(String nombre) throws SQLException, ClassNotFoundException {
		Class.forName(datosMysql.driver);
		String url = datosMysql.driverUrl;
		Connection con = DriverManager.getConnection(url, datosMysql.user, datosMysql.password);
		Statement st = con.createStatement();
		
		String query = "";
		
		query = ""
				+ "SET @i = 0; SELECT @i:=@i+1 as contador, jugador1 FROM combate WHERE jugador1 != '"+nombre+"'";

		
		ResultSet rs = st.executeQuery(query);
		
		String resultado = "<table id=\"tablaPartidas\">"
						+ "<tr>"
						+ "<th>Id</th>"
						+ "<th>Nombre jugador</th>"
						+ "<th>Unirse</th>"
						+ "</tr>";
		
		while (rs.next()) {
			resultado = resultado 
						+"<tr>"
						+ "<td>"+rs.getString("nombre")+"</td>"
						+ "<td>" + rs.getInt("contador")+"</td>";						
		}
		
		resultado=resultado+"</table>";
		return resultado;
	}
}
