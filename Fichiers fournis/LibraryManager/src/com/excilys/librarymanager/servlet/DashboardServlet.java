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
import com.excilys.librarymanager.model.*;
import com.excilys.librarymanager.service.*;
import com.excilys.librarymanager.service.impl.*;

public class DashboardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LivreService livreService = LivreService.getInstance();
		MembreService membreService = MembreService.getInstance();
		EmpruntService empruntService = EmpruntService.getInstance();
		List<Emprunt> emprunts = new ArrayList<>();
		int nb_livres = -1, nb_emprunts = -1, nb_membres = -1;
		try {
			nb_livres = livreService.count();
			nb_membres = membreService.count();
			nb_emprunts = empruntService.count();
			emprunts = empruntService.getListCurrent();
		} catch (ServiceException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		req.setAttribute("nb_livres", nb_livres);
		req.setAttribute("nb_membres", nb_membres);
		req.setAttribute("nb_emprunts", nb_emprunts);
		req.setAttribute("emprunts", emprunts);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/View/dashboard.jsp");
		dispatcher.forward(req, resp);
    }
}