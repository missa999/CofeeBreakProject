<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100 flex justify-center items-center min-h-screen">

<% 
    String errorMessage = (String) request.getAttribute("errorMessage");
%>

<div class="bg-white shadow-lg rounded-lg p-8 w-full max-w-md">
    <h1 class="text-2xl font-bold text-center text-gray-800 mb-6">Login to Your Account</h1>

    <% if (errorMessage != null) { %>
        <div class="bg-red-100 text-red-800 border border-red-400 p-4 rounded mb-4">
            <%= errorMessage %>
        </div>
    <% } %>

    <form action="/coffeebreak/UsersServlet" method="post" class="space-y-4">
        <div>
            <label for="email" class="block text-sm font-medium text-gray-700">Email</label>
            <input type="text" name="email" id="email"
                class="w-full px-3 py-2 border border-gray-300 rounded focus:outline-none focus:ring focus:ring-blue-500"
                required />
        </div>

        <div>
            <label for="password" class="block text-sm font-medium text-gray-700">Password</label>
            <input type="password" name="password" id="password"
                class="w-full px-3 py-2 border border-gray-300 rounded focus:outline-none focus:ring focus:ring-blue-500"
                required />
        </div>

        <button type="submit" 
            class="w-full bg-gradient-to-r from-amber-700 to-amber-800 hover:from-amber-900 hover:to-amber-700 text-white font-bold py-2 rounded hover:bg-blue-600 transition">
            Login
        </button>
    </form>

    <p class="mt-4 text-center text-sm text-gray-600">
        Don't have an account? 
        <a href="register.jsp" class="text-amber-500 hover:underline">Register here</a>.
    </p>
</div>

</body>
</html>
