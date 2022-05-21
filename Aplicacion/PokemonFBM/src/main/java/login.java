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
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
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
		String contrasena = request.getParameter("contrasena");
		boolean resultado = false;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/pokedexdb";
			Connection con = DriverManager.getConnection(url, "root", "123456Fran");
			Statement st = con.createStatement();
			String query = ("select * from usuario where nombreUsuario ='"+usuario+"' and contrasena='"+contrasena+"'");
			ResultSet rs = st.executeQuery(query);
			if(rs.next()) {
				resultado = true;
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		response.getWriter().append(String.valueOf(resultado));
		response.addHeader("Access-Control-Allow-Origin", "*");
	}

}
