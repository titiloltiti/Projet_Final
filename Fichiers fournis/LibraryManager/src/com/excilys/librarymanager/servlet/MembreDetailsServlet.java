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
import com.excilys.librarymanager.model.Membre;
import com.excilys.librarymanager.model.Membre.Abonnement;
import com.excilys.librarymanager.service.impl.EmpruntService;
import com.excilys.librarymanager.service.impl.MembreService;

public class MembreDetailsServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MembreService membreService = MembreService.getInstance();
		EmpruntService empruntService = EmpruntService.getInstance();
		Membre membre = new Membre();
		List<Emprunt> emprunts = new ArrayList<>();
		int idMembre = Integer.parseInt(req.getParameter("id"));
		try {
			membre = membreService.getById(idMembre);
			emprunts = empruntService.getListCurrentByMembre(idMembre);
		} catch (ServiceException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		req.setAttribute("membre", membre);
		req.setAttribute("emprunts", emprunts);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/View/membre_details.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MembreService membreService = MembreService.getInstance();
		String idMembre = req.getParameter("id");
		String inputNom = req.getParameter("nom");
		String inputPrenom = req.getParameter("prenom");
		String inputAbonnement = req.getParameter("abonnement");
		String inputAdress = req.getParameter("adresse");
		String inputEmail = req.getParameter("email");
		String inputTelephone = req.getParameter("telephone");
		Membre membre;
		try {
			membre = new Membre(Integer.parseInt(idMembre), inputNom, inputPrenom, inputAdress, inputEmail, inputTelephone,
					inputAbonnement);
			membreService.update(membre);
		} catch (ServiceException e) {
			membre = new Membre();
			throw new ServletException(e.getMessage(),e);
		}
		req.setAttribute("membre", membre);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/View/membre_details.jsp");
		dispatcher.forward(req, resp);
	}
}
