package clasesCombate;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.regex.*;

public class Usuario {
	private String nombre;
	private String correo;
	private String contrasena;
	public boolean cambioPokemon = false;
	private ArrayList<Pokemon> equipo;
	
	// Constructor
	public Usuario(String nombre, String contrasena) {
		this(nombre, null, contrasena, null);
	}
	
	public Usuario(String nombre, String correo, String contrasena) {
		this(nombre, correo, contrasena, null);
	}

	public Usuario(String nombre, String correo, String contrasena, ArrayList<Pokemon> equipo) {
		this.setNombre(nombre);
		this.setCorreo(correo);
		this.setContrasena(contrasena);
		this.equipo = new ArrayList<Pokemon>();
		this.nombre = nombre;
		this.equipo = equipo;
	}
	
	
	public boolean registrarUsuario() {
		boolean resultado = true; 
		try {
			System.out.println("0");
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("1");
			String url = "jdbc:mysql://localhost:3306/pokedexdb";
			System.out.println("2");
			Connection con = DriverManager.getConnection(url, "root", "123456Fran");
			System.out.println("3");
			Statement st = con.createStatement();
			System.out.println("4");
			String query = "INSERT INTO usuario (nombreUsuario,correo,contrasena) VALUES('"+this.getNombre()+"','"+this.getCorreo()+"','"+this.getContrasena()+"')";
			System.out.println("5");
			st.executeUpdate(query);
			System.out.println("6");
		} catch (Exception e) {
			resultado = false;
		}
		
		return resultado;
	}
	
	public String logearUsuario() {
		String resultado = "";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/pokedexdb";
			Connection con = DriverManager.getConnection(url, "root", "123456Fran");
			Statement st = con.createStatement();
			String query = ("select id from usuario where nombreUsuario ='"+this.getNombre()+"' and contrasena='"+this.getContrasena()+"'");
			ResultSet rs = st.executeQuery(query);
			if(rs.next()) {
					resultado = rs.getString("id");
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return resultado;
	}
	
	public boolean validarDatosRegistro(){
		boolean validacion = false; 
		String patternNombre = "^[0-9a-zA-Z]{1,10}$";
		String patternCorreo = "^\\w{1,70}\\@[a-zA-Z0-9]{2,64}\\.[a-zA-Z]{2,64}$";
		String patternContrasena = "^[0-9a-zA-Z]{6,20}$";
			

		
		if(!Pattern.matches(patternNombre, this.getNombre())) {
			
			
		}else if(!Pattern.matches(patternCorreo, this.getCorreo())){
			
			
		}else if(!Pattern.matches(patternContrasena, this.getContrasena())){
			
		}else {
			validacion = true;
		}
		
		return validacion;
	}
	
	public String  actualizarDatos() {
		String resultado = "../Imagenes/EntrenadorIncognito.png";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/pokedexdb";
			Connection con = DriverManager.getConnection(url, "root", "123456Fran");
			Statement st = con.createStatement();
			String query = ("select rutaImagen, numVictorias from usuario, avatarUsuario where usuario.avatar_ID = avatarUsuario.id and nombreUsuario ='"+this.getNombre()+"' and contrasena='"+this.getContrasena()+"'");
			ResultSet rs = st.executeQuery(query);
			if(rs.next()) {
				resultado = rs.getString("rutaImagen")+",Victorias: "+rs.getString("numVictorias");
			}
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return resultado;
	}
	
	public boolean validarDatosLogin(){
		boolean validacion = false; 
		String patternNombre = "^[0-9a-zA-Z]{1,10}$";
		String patternContrasena = "^[0-9a-zA-Z]{6,20}$";
			

		
		if(!Pattern.matches(patternNombre, this.getNombre())) {
			
		}else if(!Pattern.matches(patternContrasena, this.getContrasena())){
			
		}else {
			validacion = true;
		}
		
		return validacion;
	}
	
	
	

	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", correo=" + correo + ", contrasena=" + contrasena + "]";
	}

	
	// Getters y Setters
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	
	public ArrayList<Pokemon> getEquipo() {
		return equipo;
	}
	
	public void setEquipo(ArrayList<Pokemon> equipo) {
		this.equipo = equipo;
	}

	public boolean isCambioPokemon() {
		return cambioPokemon;
	}

	public void setCambioPokemon(boolean cambioPokemon) {
		this.cambioPokemon = cambioPokemon;
	}
	
}