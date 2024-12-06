<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100 flex justify-center items-center min-h-screen">

<%
    String email = (String) session.getAttribute("email");
%>

<div class="bg-white shadow-lg rounded-lg p-8 w-full max-w-md text-center">
    <h1 class="text-2xl font-bold text-gray-800 mb-6">Welcome, <%= email != null ? email : "User" %>!</h1>
    <p class="text-gray-700">You have successfully logged in.</p>
    
	<div class="flex justify-between w-full mt-6">
	    <a href="/coffeebreak/ProductServlet?action=list&productType=cafe" class="bg-blue-500 text-white py-2 px-4 rounded hover:bg-blue-600 transition">
	        Go to cafes Page
	    </a>
	    
	    <a href="/coffeebreak/ProductServlet?action=list&productType=vinoiserie" class="bg-green-500 text-white py-2 px-4 rounded hover:bg-green-600 transition">
	        Go to vinoiserie Page
	    </a>    
	</div>


    <!-- Logout Button -->
    <a href="logout.jsp" class="block mt-4 bg-red-500 text-white py-2 rounded hover:bg-red-600 transition">
        Logout
    </a>
</div>

</body>
</html>
