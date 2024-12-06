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

    <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-8">
        <div class="bg-white shadow-xl rounded-lg overflow-hidden">
            <% if (productType != null && product != null) { %>
                <% if ("cafe".equals(productType) && product instanceof Cafe) { %>
                    <% Cafe cafe = (Cafe) product; %>
                    <div class="flex flex-col md:flex-row items-center md:items-start">
                        <%-- Display Image --%>
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
                            <h1 class="text-3xl font-bold text-gray-800 mb-4">Details of Café: <%= cafe.getNomCafe() %></h1>
                            <p class="text-lg text-gray-700"><strong>ID:</strong> <%= cafe.getId() %></p>
                            <p class="text-lg text-gray-700"><strong>Price:</strong> <%= cafe.getPrix() %> €</p>
                            <p class="text-lg text-gray-700 mb-6"><strong>Sales:</strong> <%= cafe.getVentes() %></p>

                            <div class="flex space-x-4">
                                <a href="ProductServlet?action=list&productType=<%= productType %>" 
                                   class="inline-block bg-blue-500 hover:bg-blue-600 text-white px-6 py-3 rounded-lg text-lg transition">
                                    Back to List
                                </a>
                            </div>
                        </div>
                    </div>

                <% } else if ("vinoiserie".equals(productType) && product instanceof Vinoiserie) { %>
                    <% Vinoiserie vino = (Vinoiserie) product; %>
                    <div class="flex flex-col md:flex-row items-center md:items-start">
                        <%-- Display Image --%>
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
                            <h1 class="text-3xl font-bold text-gray-800 mb-4">Details of Vinoiserie: <%= vino.getNom() %></h1>
                            <p class="text-lg text-gray-700"><strong>ID:</strong> <%= vino.getId() %></p>
                            <p class="text-lg text-gray-700"><strong>Price:</strong> <%= vino.getPrix() %> €</p>
                            <p class="text-lg text-gray-700 mb-6"><strong>Sales:</strong> <%= vino.getVentes() %></p>

                            <div class="flex space-x-4">
                                <a href="ProductServlet?action=list&productType=<%= productType %>" 
                                   class="inline-block bg-blue-500 hover:bg-blue-600 text-white px-6 py-3 rounded-lg text-lg transition">
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
