<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Product</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <script>
        function previewImage(event) {
            const file = event.target.files[0];
            const reader = new FileReader();
            
            reader.onload = function() {
                const preview = document.getElementById('imagePreview');
                preview.src = reader.result;
                preview.style.display = 'block';
            };
            
            if (file) {
                reader.readAsDataURL(file);
            }
        }
    </script>
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

    <div class="min-h-screen flex items-center justify-center py-8">
        <div class="max-w-md w-full bg-white shadow-lg rounded-lg overflow-hidden">
            <div class="px-6 py-4">
                <h1 class="text-2xl font-bold text-center text-gray-700 mb-4">Add Product</h1>
                <form action="ProductServlet" method="post" enctype="multipart/form-data" class="space-y-6">
                    <input type="hidden" name="action" value="add">
                    <input type="hidden" name="productType" id="productType" value="<%= request.getParameter("productType") %>">

                    <div>
                        <label for="image" class="block text-sm font-medium text-gray-600">Image:</label>
                        <input type="file" name="image" id="image" accept="image/*" required
                               class="w-full px-3 py-2 mt-1 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-400"
                               onchange="previewImage(event)">
                    </div>

                    <div class="mt-4 text-center">
                        <img id="imagePreview" src="" alt="Image Preview" class="w-full h-auto rounded-md shadow-md hidden" />
                    </div>

                    <div>
                        <label for="name" class="block text-sm font-medium text-gray-600">Name:</label>
                        <input type="text" name="name" id="name" required
                               class="w-full px-3 py-2 mt-1 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-400">
                    </div>

                    <div>
                        <label for="price" class="block text-sm font-medium text-gray-600">Price:</label>
                        <input type="number" step="0.01" name="price" id="price" required
                               class="w-full px-3 py-2 mt-1 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-400">
                    </div>

                    <div>
                        <label for="sales" class="block text-sm font-medium text-gray-600">Sales:</label>
                        <input type="number" name="sales" id="sales" required
                               class="w-full px-3 py-2 mt-1 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-400">
                    </div>

                    <div class="text-center">
                        <button type="submit"
                                class="w-full bg-gradient-to-r from-amber-700 to-amber-800 hover:from-amber-900 hover:to-amber-700 text-white font-bold py-2 px-4 rounded-md hover:bg-blue-600 focus:outline-none focus:ring-2 focus:ring-blue-400">
                            Add Product
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
