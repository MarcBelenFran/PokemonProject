package servlets;
import java.io.IOException;

import clasesApoyo.BuscadorPartidas;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class actualizarTablaPartida
 */
@WebServlet("/actualizarTablaPartidas")
public class actualizarTablaPartidas extends HttpServlet {
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
		String tablaPartidas = "";
		try {
			tablaPartidas = BuscadorPartidas.mostrarTablaPartidas(request.getParameter("idUsuario"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		

		response.addHeader("Access-Control-Allow-Origin", "*");
		response.getWriter().append(tablaPartidas);
		response.setContentType("text/plain"); 
	}

}
