<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="com.mohit.invoice.model.Invoice" %>
<%@ page import="com.mohit.invoice.dao.InvoiceDao" %>
<%@ page import="java.util.*" %>

<%
	int id = Integer.parseInt(request.getParameter("id"));
	List<Invoice> list = InvoiceDao.getInvoices(id);
%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Invoice Processing</title>
    <link
      href="https://fonts.googleapis.com/icon?family=Material+Icons"
      rel="stylesheet"
    />
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
      crossorigin="anonymous"
    />
    <link rel="stylesheet" href="style.css" />
  </head>
  <body>
    <header>
      <div class="logo">
        <img src="images/logo.png" alt="" height="35px" />
      </div>
    </header>
    <main>
      <h1 class="heading">Pending Invoices</h1>
      <div class="fetuares">
        <form action="addInvoice.jsp">
          <button type="submit" style="width: 8rem" class="addInv">ADD INVOICE</button>
        </form>
        <div class="dropdown">
          <button
            class="btn btn-secondary dropdown-toggle"
            type="button"
            data-bs-toggle="dropdown"
            aria-expanded="false"
            style="background-color: #007bff; border-radius: 5px;height:2.5rem;
  width: 6rem;font-size: 15px;padding: 7px 7px;margin-left:15px;border: 1.2px solid;"
          >
            FILTERS
          </button>
          <ul class="dropdown-menu">
            <li><a class="dropdown-item" href="filter.jsp?id=1">Sort by Amount</a></li>
            <li><a class="dropdown-item" href="filter.jsp?id=2">Sort by Date</a></li>
          </ul>
        </div>
        <form action="Download" style="margin-left: 15px">
          <input type="hidden" name="id" value=<%=id%>>
          <button type="submit" style="width: 5rem" class="addInv">EXPORT</button>
        </form>
      </div>
      <div class="tableDiv">
        <table>
          <thead>
            <tr>
              <th>Sr.</th>
              <th>Vendor</th>
              <th>Product</th>
              <th>Amount</th>
              <th>Date</th>
              <th>Status</th>
              <th>Action</th>
            </tr>
          </thead>
          <tbody>
            <% 
            	int i = 0;
            	for(i=0;i<list.size();i++) {
            %>
            <tr>
              <td><%=i+1%>.</td>
              <td><%=list.get(i).getVendor()%></td>
              <td><%=list.get(i).getProduct()%></td>
              <td><%=list.get(i).getAmount()%></td>
              <% 
              	String [] arr = list.get(i).getDate().toString().substring(0,10).split("-"); 
              	String temp = arr[0];
              	arr[0] = arr[2];
              	arr[2] = temp;
              %>
              <td><%=String.join("-",arr)%></td>
              <td><%=list.get(i).getStatus()%></td>
              <td><form action="DeleteInvoice" method="post"><button type="submit" class="action" name="invoiceId" value=<%=list.get(i).getId()%>><i class="material-icons">dones</i></i></button></form></td>
            </tr>
             <%
            	}
             %>
          </tbody>
        </table>
      </div>
    </main>
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
      crossorigin="anonymous"
    ></script>
  </body>
</html>
