package servlets;
import java.io.IOException;

import clasesApoyo.BuscadorCombates;
import clasesApoyo.BuscadorPokemon;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;

/**
 * Servlet implementation class insertarMovimiento
 */
@jakarta.servlet.annotation.WebServlet("/insertarMovimiento")
public class insertarMovimiento extends HttpServlet {
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
		BuscadorCombates.insertarTurno(Integer.parseInt(request.getParameter("idCombate")), Integer.parseInt(request.getParameter("turno")), Integer.parseInt(request.getParameter("idJugador")),
				Integer.parseInt(BuscadorPokemon.idImagen(request.getParameter("imagenPokemon"))), Integer.parseInt(request.getParameter("idMovimiento")), BuscadorCombates.convertirBooleano(request.getParameter("idCombate")));
		
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/plain"); 
	}

}
