<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="coffeebreak.models.Cafe, coffeebreak.models.Vinoiserie" %>
<%
    String productType = (String) request.getAttribute("productType");
    Object product = request.getAttribute("product");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Product Details</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100">
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
    <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-8">
        <div class="bg-white shadow-xl rounded-lg overflow-hidden">
            <% if (productType != null && product != null) { %>
                <% if ("cafe".equals(productType) && product instanceof Cafe) { %>
                    <% Cafe cafe = (Cafe) product; %>
                    <div class="flex flex-col md:flex-row items-center md:items-start">
                        <% if (cafe.getImageUrl() != null) { %>
                            <div class="w-full md:w-1/3 p-4">
                                <img src="<%= cafe.getImageUrl() %>" alt="<%= cafe.getNomCafe() %>" class="w-full h-64 object-cover rounded-lg shadow-lg hover:scale-105 transition-transform duration-300">
                            </div>
                        <% } else { %>
                            <div class="w-full md:w-1/3 p-4">
                                <p class="text-center text-gray-500">No image available</p>
                            </div>
                        <% } %>

                        <div class="w-full md:w-2/3 p-6">
                            <h1 class="text-3xl font-bold text-gray-800 mb-4"><%= cafe.getNomCafe() %></h1>
                            <p class="text-lg text-gray-700"><strong>Price:</strong> <%= cafe.getPrix() %> €</p>
                            <p class="text-lg text-gray-700 mb-6"><strong>Sales:</strong> <%= cafe.getVentes() %></p>

                            <div class="flex space-x-4">
                                <a href="ProductServlet?action=list&productType=<%= productType %>" 
                                   class="bg-gradient-to-r from-amber-700 to-amber-800 hover:from-amber-900 hover:to-amber-700 text-white font-bold py-2 px-4 rounded-md hover:bg-blue-600 focus:outline-none focus:ring-2 focus:ring-blue-400">
                                    Back to List
                                </a>
                            </div>
                        </div>
                    </div>

                <% } else if ("vinoiserie".equals(productType) && product instanceof Vinoiserie) { %>
                    <% Vinoiserie vino = (Vinoiserie) product; %>
                    <div class="flex flex-col md:flex-row items-center md:items-start">
                        <% if (vino.getImageUrl() != null) { %>
                            <div class="w-full md:w-1/3 p-4">
                                <img src="<%= vino.getImageUrl() %>" alt="<%= vino.getNom() %>" class="w-full h-64 object-cover rounded-lg shadow-lg hover:scale-105 transition-transform duration-300">
                            </div>
                        <% } else { %>
                            <div class="w-full md:w-1/3 p-4">
                                <p class="text-center text-gray-500">No image available</p>
                            </div>
                        <% } %>

                        <div class="w-full md:w-2/3 p-6">
                            <h1 class="text-3xl font-bold text-gray-800 mb-4"><%= vino.getNom() %></h1>
                            <p class="text-lg text-gray-700"><strong>Price:</strong> <%= vino.getPrix() %> $</p>
                            <p class="text-lg text-gray-700 mb-6"><strong>Sales:</strong> <%= vino.getVentes() %></p>

                            <div class="flex space-x-4">
                                <a href="ProductServlet?action=list&productType=<%= productType %>" 
                                   class="bg-gradient-to-r from-amber-700 to-amber-800 hover:from-amber-900 hover:to-amber-700 text-white font-bold py-2 px-4 rounded-md hover:bg-blue-600 focus:outline-none focus:ring-2 focus:ring-blue-400">
                                    Back to List
                                </a>
                            </div>
                        </div>
                    </div>
                <% } %>
            <% } else { %>
                <p class="text-center text-red-500">Product not found!</p>
            <% } %>
        </div>
    </div>

</body>
</html>
