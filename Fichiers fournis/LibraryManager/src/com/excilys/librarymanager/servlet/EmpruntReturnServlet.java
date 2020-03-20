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

public class EmpruntReturnServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		EmpruntService empruntService = EmpruntService.getInstance();
		List<Emprunt> emprunts = new ArrayList<>();
		String id = req.getParameter("id");
		try {
			emprunts = empruntService.getListCurrent();
		} catch (ServiceException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		if (id != null)
			req.setAttribute("id", id);
		req.setAttribute("emprunts", emprunts);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/View/emprunt_return.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		EmpruntService empruntService = EmpruntService.getInstance();
		int id = Integer.parseInt(req.getParameter("id"));
		List<Emprunt> emprunts = new ArrayList<>();
		try {
			empruntService.returnBook(id);
			emprunts = empruntService.getListCurrent();
		} catch (ServiceException e) {
			throw new ServletException(e.getMessage(), e);
		}
		req.setAttribute("emprunts", emprunts);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/View/emprunt_list.jsp");
		dispatcher.forward(req, resp);
	}

}
