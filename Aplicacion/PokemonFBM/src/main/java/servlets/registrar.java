package servlets;
import java.io.IOException;

import clasesCombate.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;

/**
 * Servlet implementation class registrar
 */
@jakarta.servlet.annotation.WebServlet("/registrar")
public class registrar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponseWrapper response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Usuario u = new Usuario(request.getParameter("nombre"),request.getParameter("correo"),request.getParameter("contrasena"));
		System.out.println(u);
		if(u.registrarUsuario()) {
			response.getWriter().append("Usuario registrado correctamente");
		}else {
			response.getWriter().append("Error al registrar el usuario");
		}
		
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/html");
	}

}
