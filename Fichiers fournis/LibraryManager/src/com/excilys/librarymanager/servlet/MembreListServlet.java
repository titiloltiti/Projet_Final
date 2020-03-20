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
import com.excilys.librarymanager.model.Membre;
import com.excilys.librarymanager.service.impl.MembreService;

public class MembreListServlet extends HttpServlet {
	@Override 
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MembreService membreService = MembreService.getInstance();
		List<Membre> membres = new ArrayList<>();
		try {
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
