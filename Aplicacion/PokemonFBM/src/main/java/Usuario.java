import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Usuario {
	private String nombre;
	private String correo;
	private String contrase�a;
	
	// Constructor
	public Usuario(String nombre, String correo, String contrase�a) {
		this.setNombre(nombre);
		this.setCorreo(correo);
		this.setContrase�a(contrase�a);
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
			String query = "INSERT INTO usuario (nombreUsuario,correo,contrase�a) VALUES('"+this.getNombre()+"','"+this.getCorreo()+"','"+this.getContrase�a()+"')";
			System.out.println("5");
			st.executeUpdate(query);
			System.out.println("6");
		} catch (Exception e) {
			resultado = false;
		}
		
		return resultado;
	}
	
	
	

	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", correo=" + correo + ", contrase�a=" + contrase�a + "]";
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

	public String getContrase�a() {
		return contrase�a;
	}

	public void setContrase�a(String contrase�a) {
		this.contrase�a = contrase�a;
	}
	
	
}
