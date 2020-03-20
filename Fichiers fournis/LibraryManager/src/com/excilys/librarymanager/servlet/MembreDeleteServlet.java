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

public class MembreDeleteServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MembreService membreService = MembreService.getInstance();
		Membre membre = new Membre();
		int idMembre = Integer.parseInt(req.getParameter("id"));
		try {
			membre = membreService.getById(idMembre);
		} catch (ServiceException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		req.setAttribute("membre", membre);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/View/membre_delete.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MembreService membreService = MembreService.getInstance();
		int id = Integer.parseInt(req.getParameter("id"));
		List<Membre> membres = new ArrayList<>();
		try {
			membreService.delete(id);
			membres = membreService.getList();
		} catch (ServiceException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		req.setAttribute("membres", membres);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/View/membre_list.jsp");
		dispatcher.forward(req, resp);
	}
}
