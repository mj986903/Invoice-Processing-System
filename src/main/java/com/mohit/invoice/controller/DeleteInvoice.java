package com.mohit.invoice.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.mohit.invoice.dao.InvoiceDao;

public class DeleteInvoice extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idString = request.getParameter("invoiceId");
		int id = Integer.parseInt(idString);
		
		InvoiceDao.deleteInvoice(id);
		
		response.sendRedirect("index.jsp");
	}
}
