

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Servlet implementation class tablaPokemon
 */
@WebServlet("/tablaPokemon")
public class tablaPokemon extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String resultado = "";
		
		try {
			resultado = BuscadorPokemon.mostrarTabla(request.getParameter("filtroNombre"),request.getParameter("idUsuario"),request.getParameter("rutaImagen"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.getWriter().append(resultado);
	}

}
