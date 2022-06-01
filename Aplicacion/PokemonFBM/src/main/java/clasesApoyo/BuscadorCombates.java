package clasesApoyo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import clasesCombate.Combate;

public class BuscadorCombates {
	
	//PARA EL COMBATE TENEMOS QUE:
		// 1.- COLOCAR LOS DATOS DE LOS DOS JUGADORES EN USR1 Y USR2 DE COMBATE INCLUYENDO EL EQUIPO POKEMON
		// (AQUI EMPEZARIA EL COMBATE)
		// 2.- POR CADA LINEA QUE SAQUEMOS DE TURNO TENEMOS QUE BUSCAR A QUE USUARIO PERTENECE Y AÑADIR CON QUE POKEMON ESTA ATACANDO
		// (SE IRIA ACTUALIZANDO LA ARRAY DEL EQUIPO POKEMON)
		// 3.- AL FINAL DE LA SELECT SE TIENE QUE DEVOLVER EL ARRAY EN FORMATO JSON AL JS
	
	public static void turnos(int idCombate) {
		
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
					if (rs.getString("nombreUsuario").equals(Combate.getUsr1().getNombre())) {
						
					}else if(rs.getString("nombreUsuario").equals(Combate.getUsr2().getNombre())) {
						
					}
				}
			}
				
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
