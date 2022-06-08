package servlets;
import java.io.IOException;

import clasesApoyo.BuscadorCombates;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/combate")

public class combate extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			int idCombate = Integer.parseInt(request.getParameter("idCombate"));
			
			String respuesta = BuscadorCombates.buscarCombate(idCombate);
		
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.getWriter().append(respuesta);
		response.setContentType("text/plain"); 
	}

}
