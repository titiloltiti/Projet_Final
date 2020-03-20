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
import com.excilys.librarymanager.model.Livre;
import com.excilys.librarymanager.service.impl.LivreService;

public class LivreDeleteServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LivreService livreService = LivreService.getInstance();
		Livre livre = new Livre();
		int idLivre = Integer.parseInt(req.getParameter("id"));
		try {
			livre = livreService.getById(idLivre);
		} catch (ServiceException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		req.setAttribute("livre", livre);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/View/livre_delete.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LivreService livreService = LivreService.getInstance();
		int id = Integer.parseInt(req.getParameter("id"));
		List<Livre> livres = new ArrayList<>();
		try {
			livreService.delete(id);
			livres = livreService.getList();
		} catch (ServiceException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		req.setAttribute("livres", livres);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/View/livre_list.jsp");
		dispatcher.forward(req, resp);
	}
}
