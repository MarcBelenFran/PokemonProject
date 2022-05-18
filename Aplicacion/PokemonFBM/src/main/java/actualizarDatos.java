import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class actualizarDatos
 */
@WebServlet("/actualizarDatos")
public class actualizarDatos extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String usuario = request.getParameter("nombre");
		String contraseña = request.getParameter("contraseña");
		String rutaImagen = "../Imagenes/EntrenadorIncognito.png";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/pokedexdb";
			Connection con = DriverManager.getConnection(url, "root", "123456Fran");
			Statement st = con.createStatement();
			String query = ("select rutaImagen from usuario, avatarUsuario where usuario.avatar_ID = avatarUsuario.id and nombreUsuario ='"+usuario+"' and contraseña='"+contraseña+"'");
			ResultSet rs = st.executeQuery(query);
			if(rs.next()) {
				rutaImagen = rs.getString("rutaImagen");
			}
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.getWriter().append(rutaImagen);
		
	}

}
