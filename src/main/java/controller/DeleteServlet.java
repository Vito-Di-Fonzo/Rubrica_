package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao;
import model.User;

/**
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int idCont = Integer.parseInt(request.getParameter("id"));
		Dao.eliminaDatiContatto(idCont);
		Dao.eliminaDatiUtente_Contatto(idCont);
		String id_utente = (String) request.getParameter("id_utente"); // andiamo a riprendere id_utente per far
																		// aggiornare i dati e non la pagina
		User utenteTrovato = Dao.cercaUtente(Integer.valueOf(id_utente));
		request.setAttribute("utenteLoggato", utenteTrovato);
		request.setAttribute("listaContatti", Dao.getListaContattiByUser(utenteTrovato.getId()));
		RequestDispatcher rd = request.getRequestDispatcher("/Home.jsp");
		rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
