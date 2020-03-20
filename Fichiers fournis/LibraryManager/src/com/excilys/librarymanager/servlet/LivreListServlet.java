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


public class LivreListServlet extends HttpServlet {
	@Override 
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LivreService livreService = LivreService.getInstance();
		List<Livre> livres = new ArrayList<>();
		try {
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
