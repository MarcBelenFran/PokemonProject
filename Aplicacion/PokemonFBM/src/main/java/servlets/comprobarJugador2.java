package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import clasesApoyo.BuscadorCombates;

/**
 * Servlet implementation class comprobarJugador2
 */
@WebServlet("/comprobarJugador2")
public class comprobarJugador2 extends HttpServlet {
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
		String respuesta = BuscadorCombates.combateJ1(Integer.parseInt(request.getParameter("idUsuario")));
		System.out.println(respuesta);
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.getWriter().append(respuesta);
		response.setContentType("text/plain"); 
	}

}
