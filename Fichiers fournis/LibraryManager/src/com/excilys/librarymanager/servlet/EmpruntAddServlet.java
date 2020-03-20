package com.excilys.librarymanager.servlet;

import java.io.IOException;
import java.time.LocalDate;
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
import com.excilys.librarymanager.model.Membre;
import com.excilys.librarymanager.service.impl.EmpruntService;
import com.excilys.librarymanager.service.impl.LivreService;
import com.excilys.librarymanager.service.impl.MembreService;

public class EmpruntAddServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MembreService membreService = MembreService.getInstance();
		LivreService livreService = LivreService.getInstance();
		List<Membre> membres = new ArrayList<>();
		List<Livre> livres = new ArrayList<>();
		try {
			membres = membreService.getListMembreEmpruntPossible();
			livres = livreService.getListDispo();	
		} catch (ServiceException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		req.setAttribute("membres", membres);
		req.setAttribute("livres", livres);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/View/emprunt_add.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		EmpruntService empruntService = EmpruntService.getInstance();
		int idLivre = Integer.parseInt(req.getParameter("idLivre"));
		int idMembre = Integer.parseInt(req.getParameter("idMembre"));
		List<Emprunt> emprunts = new ArrayList<>();
		try {
			empruntService.create(idMembre, idLivre, LocalDate.now());
			emprunts = empruntService.getListCurrent();
		} catch (ServiceException e) {
			throw new ServletException(e.getMessage(),e);
		}
		req.setAttribute("emprunts", emprunts);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/View/emprunt_list.jsp");
		dispatcher.forward(req, resp);
	}
}
