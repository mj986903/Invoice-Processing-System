package com.mohit.invoice.controller;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

import com.mohit.invoice.dao.InvoiceDao;
import com.mohit.invoice.model.Invoice;

public class AddInvoice extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String vendor = request.getParameter("vendor");
		String product = request.getParameter("product");
		String amountString = request.getParameter("amount");
		String dateString = request.getParameter("date");
		
		if(vendor == "" || product == "" || amountString == "" || dateString == "") {
			response.sendRedirect("addInvoice.jsp");
			return;
		}

		long amount = Long.parseLong(amountString);
		Date date=Date.valueOf(dateString);  
		
		Invoice invoice = new Invoice();
		invoice.setVendor(vendor);
		invoice.setProduct(product);
		invoice.setAmount(amount);
		invoice.setDate(date);
		
		InvoiceDao.addInvoice(invoice);
		
		response.sendRedirect("index.jsp");
	}

}
