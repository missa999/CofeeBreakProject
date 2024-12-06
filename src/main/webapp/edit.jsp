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
    <div class="max-w-md mx-auto mt-8">
        <h1 class="text-2xl font-bold text-center mb-6">Edit <%= productType %></h1>
        <form action="ProductServlet" method="post" class="space-y-6">
            <input type="hidden" name="action" value="update">
            <input type="hidden" name="productType" value="<%= productType %>">
            <input type="hidden" name="id" value="<%= id %>">
            
            <% if (product instanceof Cafe) {
                Cafe cafe = (Cafe) product;
            %>
            <div>
                <label for="name" class="block text-sm font-medium text-gray-600">Name:</label>
                <input type="text" name="name" id="name" value="<%= cafe.getNomCafe() %>" required
                       class="w-full px-3 py-2 mt-1 border border-gray-300 rounded-md">
            </div>

            <div>
                <label for="price" class="block text-sm font-medium text-gray-600">Price:</label>
                <input type="number" step="0.01" name="price" id="price" value="<%= cafe.getPrix() %>" required
                       class="w-full px-3 py-2 mt-1 border border-gray-300 rounded-md">
            </div>

            <div>
                <label for="sales" class="block text-sm font-medium text-gray-600">Sales:</label>
                <input type="number" name="sales" id="sales" value="<%= cafe.getVentes() %>" required
                       class="w-full px-3 py-2 mt-1 border border-gray-300 rounded-md">
            </div>
            <% } else if (product instanceof Vinoiserie) {
                Vinoiserie vino = (Vinoiserie) product;
            %>
            <div>
                <label for="name" class="block text-sm font-medium text-gray-600">Name:</label>
                <input type="text" name="name" id="name" value="<%= vino.getNom() %>" required
                       class="w-full px-3 py-2 mt-1 border border-gray-300 rounded-md">
            </div>

            <div>
                <label for="price" class="block text-sm font-medium text-gray-600">Price:</label>
                <input type="number" step="0.01" name="price" id="price" value="<%= vino.getPrix() %>" required
                       class="w-full px-3 py-2 mt-1 border border-gray-300 rounded-md">
            </div>

            <div>
                <label for="sales" class="block text-sm font-medium text-gray-600">Sales:</label>
                <input type="number" name="sales" id="sales" value="<%= vino.getVentes() %>" required
                       class="w-full px-3 py-2 mt-1 border border-gray-300 rounded-md">
            </div>
            <% } %>

            <div class="text-center">
                <button type="submit"
                        class="w-full bg-blue-500 text-white font-semibold py-2 px-4 rounded-md hover:bg-blue-600">
                    Save Changes
                </button>
            </div>
        </form>
    </div>
</body>
</html>
