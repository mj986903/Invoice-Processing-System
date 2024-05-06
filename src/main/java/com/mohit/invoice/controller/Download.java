package com.mohit.invoice.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

import javax.imageio.ImageIO;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.mohit.invoice.dao.InvoiceDao;
import com.mohit.invoice.model.Invoice;

public class Download extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Document document = new Document();
		try {
			PdfWriter.getInstance(document, response.getOutputStream());
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		document.open();
		try {
			Image img = Image.getInstance("C:\\Users\\mj986\\OneDrive\\Desktop\\logo.png");
			img.scaleAbsolute(200, 50);
			document.add(img);
		} catch (DocumentException | IOException e) {
			e.printStackTrace();
		}
		PdfPTable table = new PdfPTable(6);
		addTableHeader(table);
		try {
			String id =request.getParameter("id");
			System.out.println(id);
			addRows(table,Integer.parseInt(id));
		}catch(Exception e){
			addRows(table,0);
		}finally {
			try {
				response.setContentType("application/pdf");
				response.addHeader("Content-Disposition", "attachment; filename=" + "Invoices.pdf");
				Font fontSize =  FontFactory.getFont(FontFactory.TIMES, 30f);
				document.add(new Paragraph("\n"));
				document.add(new Paragraph("  Pending Invoices",fontSize));
				document.add(new Paragraph("\n"));
				document.add(new Paragraph("\n"));
				document.add(table);
			} catch (DocumentException e) {
				e.printStackTrace();
			}
			document.close();
		}		
	}

	private void addRows(PdfPTable table,int id) {
		List<Invoice> list;
		if(id == 0) {
			list = InvoiceDao.getInvoices();
		}else {
			list = InvoiceDao.getInvoices(id);
		}
		int m = 1;
		
		for(Invoice i : list) {
			table.addCell(String.valueOf(m+"."));
			table.addCell(i.getVendor());
		    table.addCell(i.getProduct());
		    table.addCell(String.valueOf(i.getAmount()));
		    String [] arr = i.getDate().toString().substring(0,10).split("-"); 
          	String temp = arr[0];
          	arr[0] = arr[2];
          	arr[2] = temp;
		    table.addCell(String.join("-", arr));
		    table.addCell(i.getStatus());
		    m++;
		}
	}

	private void addTableHeader(PdfPTable table) {
		Stream.of("Sr.", "Vendor", "Product", "Amount", "Date", "Status")
	      .forEach(columnTitle -> {
	        PdfPCell header = new PdfPCell();
	        header.setBackgroundColor(BaseColor.LIGHT_GRAY);
	        header.setBorderWidth(1);
	        header.setPhrase(new Phrase(columnTitle));
	        table.addCell(header);
	    });
	}

}
