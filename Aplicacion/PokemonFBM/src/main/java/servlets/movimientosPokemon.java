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
 * Servlet implementation class movimientosPokemon
 */
@WebServlet("/movimientosPokemon")
public class movimientosPokemon extends HttpServlet {
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
		String movimientos = "";
		
		try {
			movimientos = BuscadorMovimientos.actualizarMovimientos(request.getParameter("idUsuario"),BuscadorPokemon.idImagen(request.getParameter("imagenPokemon")));
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.getWriter().append(movimientos);
	}

}
