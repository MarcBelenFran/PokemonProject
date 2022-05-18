import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Usuario {
	private String nombre;
	private String correo;
	private String contraseña;
	
	// Constructor
	public Usuario(String nombre, String correo, String contraseña) {
		this.setNombre(nombre);
		this.setCorreo(correo);
		this.setContraseña(contraseña);
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
			String query = "INSERT INTO usuario (nombreUsuario,correo,contraseña) VALUES('"+this.getNombre()+"','"+this.getCorreo()+"','"+this.getContraseña()+"')";
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
		return "Usuario [nombre=" + nombre + ", correo=" + correo + ", contraseña=" + contraseña + "]";
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

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	
	
}
