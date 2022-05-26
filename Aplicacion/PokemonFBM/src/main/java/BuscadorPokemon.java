import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BuscadorPokemon {
	
	public static String mostrarTabla(String nombre, String idUsuario, String rutaImagen) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/pokedexdb";
		Connection con = DriverManager.getConnection(url, "root", "123456Fran");
		Statement st = con.createStatement();
		
		String query = "";
		boolean eliminar = false;
		
		if(nombre.equals("") && rutaImagen.equals("../Imagenes/pokemonNoElegido.png")) {
			query = "SELECT nombre, vida, ataque, defensa, velocidad FROM pokemon p";
			
		}else if (!(nombre.equals("")) && rutaImagen.equals("../Imagenes/pokemonNoElegido.png")){
			query = "SELECT nombre, vida, ataque, defensa, velocidad FROM pokemon p WHERE nombre like '"+nombre+"%'";
			
		} else if (nombre.equals("") && !(rutaImagen.equals("../Imagenes/pokemonNoElegido.png"))){
			query = "SELECT p.nombre, p.vida, p.ataque, p.defensa, p.velocidad FROM pokemon p, equipo e, usuario u where p.id=e.idPokemon and u.id = e.idUsuario and p.rutaImagen = '"+rutaImagen+"'";
			eliminar = true;
			
		} else {
			query = "SELECT p.nombre, p.vida, p.ataque, p.defensa, p.velocidad FROM pokemon p, equipo e, usuario u where p.id=e.idPokemon and u.id = e.idUsuario and p.rutaImagen = '"+rutaImagen+"' and p.nombre like '"+nombre+"%'";
			eliminar = true;
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
						+ "<td>"+rs.getString("velocidad")+"</td>";
			
			if(!eliminar) {
				String nombrePokemon = rs.getString("nombre");
				resultado += "<td><button type=\"button\" onclick=\"agregarPokemon('"+nombrePokemon+"')\">Agregar</button></td>"
							+"</tr>";
			}else {
				resultado += "<td><button type=\"button\" onclick='quitarPokemon()'>Quitar</button></td>"
						+"</tr>";
			}
						
		}
		
		resultado=resultado+"</table>";
		return resultado;
	}
	

	public static String agregarPokemon(String idUsuario, String nombrePokemon) {
		String rutaImagen = "";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/pokedexdb";
			Connection con = DriverManager.getConnection(url, "root", "123456Fran");
			Statement st = con.createStatement();
			String query = "INSERT INTO equipo(idUsuario, idPokemon) VALUES ('"+idUsuario+"','"+BuscadorPokemon.buscadorID(nombrePokemon)+"')";
			st.executeUpdate(query);
			rutaImagen = BuscadorPokemon.buscadorImagen(nombrePokemon);
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		return rutaImagen;
	}
	
	
	// METODOS DE APOYO
	public static String buscadorID(String nombrePokemon) {
		String id = "";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/pokedexdb";
			Connection con = DriverManager.getConnection(url, "root", "123456Fran");
			Statement st = con.createStatement();
			String query = "SELECT id from pokemon where nombre = '"+nombrePokemon+"'";
			ResultSet rs = st.executeQuery(query);
			while(rs.next()) {
				id = rs.getString("id");
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return id;
	}
	
	public static String buscadorImagen(String nombrePokemon) {
		String rutaImagen = "";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/pokedexdb";
			Connection con = DriverManager.getConnection(url, "root", "123456Fran");
			Statement st = con.createStatement();
			String query = "SELECT rutaImagen from pokemon where nombre = '"+nombrePokemon+"'";
			ResultSet rs = st.executeQuery(query);
			while(rs.next()) {
				rutaImagen = rs.getString("rutaImagen");
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return rutaImagen;
	}
}
