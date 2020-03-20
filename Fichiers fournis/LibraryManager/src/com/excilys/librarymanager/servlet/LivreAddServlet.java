package com.excilys.librarymanager.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.librarymanager.exception.ServiceException;
import com.excilys.librarymanager.service.impl.LivreService;

public class LivreAddServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/View/livre_add.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LivreService livreService = LivreService.getInstance();
		String titre = req.getParameter("titre");
		String auteur = req.getParameter("auteur");
		String isbn = req.getParameter("isbn");
		int id = -1;
		try {
			 id = livreService.create(titre, auteur, isbn);
			 req.setAttribute("livre",livreService.getById(id));
		} catch (ServiceException e) {
			throw new ServletException(e.getMessage(),e);
		}
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/View/livre_details.jsp");
		dispatcher.forward(req, resp);
	}
}
