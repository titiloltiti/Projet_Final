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
import com.excilys.librarymanager.service.impl.EmpruntService;
import com.excilys.librarymanager.service.impl.MembreService;

public class MembreAddServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/View/membre_add.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MembreService membreService = MembreService.getInstance();
		String nom = req.getParameter("nom");
		String prenom = req.getParameter("prenom");
		String adresse = req.getParameter("adresse");
		String email = req.getParameter("email");
		String telephone = req.getParameter("telephone");
		int id = -1;
		try {
			 id = membreService.create(nom, prenom, adresse, email, telephone);
			 req.setAttribute("membre",membreService.getById(id));
		} catch (ServiceException e) {
			throw new ServletException(e.getMessage(),e);
		}
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/View/membre_details.jsp");
		dispatcher.forward(req, resp);
	}
}
