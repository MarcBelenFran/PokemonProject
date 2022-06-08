package servlets;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import clasesApoyo.BuscadorMovimientos;
import clasesApoyo.BuscadorPokemon;
/**
 * Servlet implementation class agregarMovimiento
 */
@WebServlet("/agregarMovimiento")
public class agregarMovimiento extends HttpServlet {
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
		String nombreMovimiento = "";
		
		try {
			nombreMovimiento = BuscadorMovimientos.agregarMovimiento(request.getParameter("idUsuario"), request.getParameter("idMovimiento"), 
									BuscadorPokemon.idImagen(request.getParameter("imagenPokemon")), request.getParameter("numeroMovimiento") );
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.getWriter().append(nombreMovimiento);
		response.setContentType("text/plain"); 
	}

}
