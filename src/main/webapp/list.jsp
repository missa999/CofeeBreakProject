<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="coffeebreak.models.Cafe, coffeebreak.models.Vinoiserie" %>
<%
    String sessionEmail = (String) session.getAttribute("email");
    if (sessionEmail == null) {
        response.sendRedirect("user/login.jsp");
        return;
    }

    String productType = request.getParameter("productType");
    List<?> products = (List<?>) request.getAttribute("products");
    String message = (String) request.getAttribute("message");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Product List</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100">

<%
    String email = (String) session.getAttribute("email");
%>

    <nav class="bg-white shadow-lg">
        <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
            <div class="flex justify-between items-center h-16">
                <div class="flex-shrink-0">
                    <a href="user/home.jsp" class="text-2xl font-bold text-amber-700">CoffeeBreak</a>
                </div>
    
                <div class="hidden md:flex space-x-6">
                    <a href="/coffeebreak/ProductServlet?action=list&productType=cafe" 
                       class="text-gray-700 hover:text-blue-500 transition">
                        Coffees
                    </a>
                    <a href="/coffeebreak/ProductServlet?action=list&productType=vinoiserie" 
                       class="text-gray-700 hover:text-green-500 transition">
                        Pastries
                    </a>
                </div>
    
                <div class="hidden md:flex items-center space-x-4">
                    <a href="user/logout.jsp" 
                       class="bg-red-500 text-white px-4 py-2 rounded hover:bg-red-600 transition">
                        Logout
                    </a>
                </div>
    
            </div>
        </div>
    </nav>

    <div class="max-w-4xl mx-auto bg-white shadow-md rounded px-8 py-6 mt-6">
        <% if ("cafe".equals(productType)) { %>
            <h1 class="text-2xl font-bold mb-6 text-center">Coffees List</h1>
        <% } else { %>
            <h1 class="text-2xl font-bold mb-6 text-center">Pastries List</h1>                
        <% } %>

        <% if (message != null) { %>
            <div class="text-center text-lg font-semibold text-green-500 mb-4">
                <%= message %>
            </div>
        <% } %>

        <div class="mb-4 text-center float-right">
            <% if ("cafe".equals(productType)) { %>
                <a href="add.jsp?productType=<%= productType %>"   class="bg-gradient-to-r from-amber-700 to-amber-800 hover:from-amber-900 hover:to-amber-700 text-white font-bold py-2 px-4 rounded-lg shadow-md transform hover:scale-105 transition-transform duration-300" >Add New Coffee</a>
            <% } else { %>
                <a href="add.jsp?productType=<%= productType %>" class="bg-gradient-to-r from-amber-700 to-amber-800 hover:from-amber-900 hover:to-amber-700 text-white font-bold py-2 px-4 rounded-lg shadow-md transform hover:scale-105 transition-transform duration-300" >Add New Pastries</a>
            <% } %>
        </div>

        <table class="min-w-full border-collapse border border-gray-200">
            <thead>
                <tr class="bg-gray-100">
                    <th class="border px-4 py-2">Image</th>
                    <th class="border px-4 py-2">Name</th>
                    <th class="border px-4 py-2">Price</th>
                    <th class="border px-4 py-2">Sales</th>
                    <th class="border px-4 py-2">Actions</th>
                </tr>
            </thead>
            <tbody>
                <% if (products != null) {
                    for (Object obj : products) {
                        if ("cafe".equals(productType) && obj instanceof Cafe) {
                            Cafe cafe = (Cafe) obj;
                %>
                <tr>
                    <td class="border px-4 py-2">
		                <% if (cafe.getImageUrl() != null) { %>
		                    <img src="<%= cafe.getImageUrl() %>" alt="<%= cafe.getNomCafe() %>" class="w-16 h-16 object-cover">
		                <% } else { %>
		                    <span>No image</span>
		                <% } %>
		            </td>
                    <td class="border px-4 py-2"><%= cafe.getNomCafe() %></td>
                    <td class="border px-4 py-2"><%= cafe.getPrix() %></td>
                    <td class="border px-4 py-2"><%= cafe.getVentes() %></td>
                    <td class="border px-4 py-2 text-center">
                    	<a href="ProductServlet?action=details&id=<%= cafe.getId() %>&productType=cafe" class="text-blue-500 hover:text-blue-700">Details</a>
                        |<a href="edit.jsp?id=<%= cafe.getId() %>&productType=cafe" class="ml-2 text-green-500 hover:text-green-700">Edit</a>
                        |<form action="ProductServlet" method="post" class="inline-block ml-2">
                            <input type="hidden" name="id" value="<%= cafe.getId() %>">
                            <input type="hidden" name="productType" value="cafe">
                            <input type="hidden" name="action" value="delete">
                            <button type="submit" class="text-red-500 hover:text-red-700">Delete</button>
                        </form>
                    </td>
                </tr>
                <% } else if ("vinoiserie".equals(productType) && obj instanceof Vinoiserie) {
                        Vinoiserie vino = (Vinoiserie) obj;
                %>
                <tr>
                    <td class="border px-4 py-2">
		                <% if (vino.getImageUrl() != null) { %>
		                    <img src="<%= vino.getImageUrl() %>" alt="hhh" class="w-16 h-16 object-cover">
		                <% } else { %>
		                    <span>No image</span>
		                <% } %>
		            </td>
                    <td class="border px-4 py-2"><%= vino.getNom() %></td>
                    <td class="border px-4 py-2"><%= vino.getPrix() %></td>
                    <td class="border px-4 py-2"><%= vino.getVentes() %></td>
                    <td class="border px-4 py-2 text-center">
                    	<a href="ProductServlet?action=details&id=<%= vino.getId() %>&productType=vinoiserie" class="text-blue-500 hover:text-blue-700">Details</a>
                        |<a href="edit.jsp?id=<%= vino.getId() %>&productType=vinoiserie" class="ml-2 text-green-500 hover:text-green-700">Edit</a>
                        |<form action="ProductServlet" method="post" class="inline-block ml-2">
                            <input type="hidden" name="id" value="<%= vino.getId() %>">
                            <input type="hidden" name="productType" value="vinoiserie">
                            <input type="hidden" name="action" value="delete">
                            <button type="submit" class="text-red-500 hover:text-red-700">Delete</button>
                        </form>
                    </td>
                </tr>
                <% }}}  else { %>
                <tr>
                    <td colspan="5" class="text-center text-gray-500 py-4">No products found.</td>
                </tr>
                <% } %>
            </tbody>
        </table>
    </div>
</body>
</html>
