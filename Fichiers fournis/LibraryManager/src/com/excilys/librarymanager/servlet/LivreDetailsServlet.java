package com.excilys.librarymanager.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.librarymanager.exception.ServiceException;
import com.excilys.librarymanager.model.Emprunt;
import com.excilys.librarymanager.model.Livre;
import com.excilys.librarymanager.service.impl.EmpruntService;
import com.excilys.librarymanager.service.impl.LivreService;

public class LivreDetailsServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LivreService livreService = LivreService.getInstance();
		EmpruntService empruntService = EmpruntService.getInstance();
		Livre livre;
		List<Emprunt> emprunts = new ArrayList<>();
		int idLivre = Integer.parseInt(req.getParameter("id"));
		try {
			livre = livreService.getById(idLivre);
			emprunts = empruntService.getListCurrentByLivre(idLivre);
		} catch (ServiceException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			livre = new Livre();
		}
		req.setAttribute("emprunts", emprunts);
		req.setAttribute("livre", livre);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/View/livre_details.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LivreService livreService = LivreService.getInstance();
		String idLivre = req.getParameter("id");
		String inputTitre = req.getParameter("titre");
		String inputAuteur = req.getParameter("auteur");
		String inputIsbn = req.getParameter("isbn");
		Livre livre;
		try {
			livre = new Livre(Integer.parseInt(idLivre), inputTitre, inputAuteur, inputIsbn);
			livreService.update(livre);
		} catch (ServiceException e) {
			livre = new Livre();
			throw new ServletException(e.getMessage(),e);
		}
		req.setAttribute("livre", livre);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/View/livre_details.jsp");
		dispatcher.forward(req, resp);
	}
}
