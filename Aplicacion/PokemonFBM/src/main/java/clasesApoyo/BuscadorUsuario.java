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
	
	/**
	 * Metodo validarSesion. Validar que todos los datos que se guardan en el localSotorage concuerdan con un usuario en la base de datos
	 * @param idUsuario
	 * @param nombre
	 * @param contrasena
	 * @return resultado de la validacion (error o correcto).
	 */
	public static String validarSesion(String idUsuario, String nombre, String contrasena){
		String resultado = "error";
		
		try {
			Class.forName(datosMysql.driver);
			String url = datosMysql.driverUrl;
			Connection con = DriverManager.getConnection(url, datosMysql.user, datosMysql.password);
			Statement st = con.createStatement();
			String query = "SELECT * from usuario where id = '"+idUsuario+"' and nombreUsuario = '"+nombre+"' and contrasena = '"+contrasena+"'";
			ResultSet rs = st.executeQuery(query);
			while(rs.next()) {
				resultado = "correcto";
			}
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return resultado;
	}
}
