package servlets;
import java.io.IOException;

import clasesApoyo.BuscadorCombates;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// ESTE SERVLET NO TIENE QUE EXISTIR
@WebServlet("/combate")

public class combate extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			int idCombate = Integer.parseInt(request.getParameter("idCombate"));
			int idUsr1 = Integer.parseInt(request.getParameter("idUsr1"));
			String nombreUsr1 = request.getParameter("nombreUsr1");
			int[] equipo1 = BuscadorCombates.convertirInt(request.getParameterValues("equipo1"));
			boolean cambio1 = BuscadorCombates.convertirBooleano(request.getParameter("cambio1"));
			int idUsr2 = Integer.parseInt(request.getParameter("idUsr2"));
			String nombreUsr2 = request.getParameter("nombreUsr2");
			int[] equipo2 = BuscadorCombates.convertirInt(request.getParameterValues("equipo2"));
			boolean cambio2 = BuscadorCombates.convertirBooleano(request.getParameter("cambio2"));
			
			String respuesta = BuscadorCombates.combate(idCombate, idUsr1, nombreUsr1, equipo1, cambio1, idUsr2, nombreUsr2, equipo2, cambio2);
			
		
		
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.getWriter().append(respuesta);
	}

}
