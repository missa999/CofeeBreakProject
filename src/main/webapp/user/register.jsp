<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100 flex justify-center items-center min-h-screen">

<% 
    String errorMessage = (String) request.getAttribute("errorMessage");
    String username = (String) request.getAttribute("username");
    String email = (String) request.getAttribute("email");
%>

<div class="bg-white shadow-lg rounded-lg p-8 w-full max-w-md">
    <h1 class="text-2xl font-bold text-center text-gray-800 mb-6">Create an Account</h1>
    
    <% if (errorMessage != null) { %>
        <div class="bg-red-100 text-red-800 border border-red-400 p-4 rounded mb-4">
            <%= errorMessage %>
        </div>
    <% } %>

    <form action="/coffeebreak/RegisterUser" method="post" class="space-y-4">
        <div>
            <label for="username" class="block text-sm font-medium text-gray-700">Username</label>
            <input type="text" name="username" id="username" 
                value="<%= username != null ? username : "" %>"
                class="w-full px-3 py-2 border border-gray-300 rounded focus:outline-none focus:ring focus:ring-blue-500"
                required />
        </div>

        <div>
            <label for="email" class="block text-sm font-medium text-gray-700">Email</label>
            <input type="email" name="email" id="email" 
                value="<%= email != null ? email : "" %>"
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
            class="w-full bg-blue-500 text-white py-2 rounded hover:bg-blue-600 transition">
            Register
        </button>
    </form>

    <p class="mt-4 text-center text-sm text-gray-600">
        Already have an account? 
        <a href="login.jsp" class="text-blue-500 hover:underline">Login here</a>.
    </p>
</div>

</body>
</html>
