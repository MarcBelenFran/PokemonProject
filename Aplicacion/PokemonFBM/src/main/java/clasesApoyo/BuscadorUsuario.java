package clasesApoyo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class BuscadorUsuario {
	
	/**
	 *  Metodo nombreID. Busca el nombre de un usuario a partir de su id.
	 *  
	 * @param idUsuario. ID del usuario que se quiere buscar.
	 * @return nombreUsuario. Nombre del usuario
	 */
	public static String nombreID(String idUsuario) {
		String nombreUsuario = "";
		
		try {
			Class.forName(datosMysql.driver);
			String url = datosMysql.driverUrl;
			Connection con = DriverManager.getConnection(url, datosMysql.user, datosMysql.password);
			Statement st = con.createStatement();
			String query = "SELECT nombreUsuario from usuario where id = '"+idUsuario+"'";
			ResultSet rs = st.executeQuery(query);
			while(rs.next()) {
				nombreUsuario = rs.getString("nombreUsuario");
			}
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return nombreUsuario;
	}
}
