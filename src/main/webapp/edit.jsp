<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="coffeebreak.models.Cafe, coffeebreak.models.Vinoiserie,coffeebreak.factory.*,coffeebreak.interfaces.*" %>
<%
    String productType = request.getParameter("productType");
    int id = Integer.parseInt(request.getParameter("id"));
    Product productDAO = ProductFactory.getProductDAO(productType);
    Object product = productDAO.getProductDetails(id);
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Product</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body>
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
<div class="max-w-md mx-auto mt-8 bg-white shadow-lg rounded-lg p-6">
    <h1 class="text-3xl font-bold text-center text-gray-800 mb-6">Edit <%= productType.equals("vinoiserie") ? "Pastries" : "Coffee" %></h1>
    <form action="ProductServlet" method="post" class="space-y-6">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="productType" value="<%= productType %>">
        <input type="hidden" name="id" value="<%= id %>">

        <% if (product instanceof Cafe) {
            Cafe cafe = (Cafe) product;
        %>
        <div>
            <label for="name" class="block text-sm font-medium text-gray-700">Name:</label>
            <input type="text" name="name" id="name" value="<%= cafe.getNomCafe() %>" required
                   class="w-full px-4 py-2 mt-1 border border-gray-300 rounded-lg shadow-sm focus:ring-blue-500 focus:border-blue-500">
        </div>

        <div>
            <label for="price" class="block text-sm font-medium text-gray-700">Price:</label>
            <input type="number" step="0.01" name="price" id="price" value="<%= cafe.getPrix() %>" required
                   class="w-full px-4 py-2 mt-1 border border-gray-300 rounded-lg shadow-sm focus:ring-blue-500 focus:border-blue-500">
        </div>

        <div>
            <label for="sales" class="block text-sm font-medium text-gray-700">Sales:</label>
            <input type="number" name="sales" id="sales" value="<%= cafe.getVentes() %>" required
                   class="w-full px-4 py-2 mt-1 border border-gray-300 rounded-lg shadow-sm focus:ring-blue-500 focus:border-blue-500">
        </div>
        <% } else if (product instanceof Vinoiserie) {
            Vinoiserie vino = (Vinoiserie) product;
        %>
        <div>
            <label for="name" class="block text-sm font-medium text-gray-700">Name:</label>
            <input type="text" name="name" id="name" value="<%= vino.getNom() %>" required
                   class="w-full px-4 py-2 mt-1 border border-gray-300 rounded-lg shadow-sm focus:ring-blue-500 focus:border-blue-500">
        </div>

        <div>
            <label for="price" class="block text-sm font-medium text-gray-700">Price:</label>
            <input type="number" step="0.01" name="price" id="price" value="<%= vino.getPrix() %>" required
                   class="w-full px-4 py-2 mt-1 border border-gray-300 rounded-lg shadow-sm focus:ring-blue-500 focus:border-blue-500">
        </div>

        <div>
            <label for="sales" class="block text-sm font-medium text-gray-700">Sales:</label>
            <input type="number" name="sales" id="sales" value="<%= vino.getVentes() %>" required
                   class="w-full px-4 py-2 mt-1 border border-gray-300 rounded-lg shadow-sm focus:ring-blue-500 focus:border-blue-500">
        </div>
        <% } %>

        <div class="text-center">
            <button type="submit"
                    class="w-full bg-gradient-to-r from-amber-700 to-amber-800 hover:from-amber-900 hover:to-amber-700 text-white font-bold py-3 px-6 rounded-lg shadow-md hover:bg-blue-600 focus:ring-2 focus:ring-blue-400 focus:ring-opacity-75">
                Save Changes
            </button>
        </div>
    </form>
</div>
</body>
</html>
