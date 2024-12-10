<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.List, coffeebreak.models.Cafe, coffeebreak.models.Vinoiserie"%>
<!DOCTYPE html>
<html lang="en">

<%
List<?> products = (List<?>) request.getAttribute("products");
List<?> productsV = (List<?>) request.getAttribute("productsV");
%>

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>CoffeeBreak</title>

<script src="https://cdn.tailwindcss.com"></script>
</head>

<body class="bg-gray-50">

    <nav class="bg-white shadow-lg">
        <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
            <div class="flex justify-between items-center h-16">
                <div class="flex-shrink-0">
                    <a href="MenuServlet" class="text-2xl font-bold text-amber-700">CoffeeBreak</a>
                </div>
    
                <div class="hidden md:flex space-x-6">
                    <a href="MenuServlet" 
                       class="text-gray-700 hover:text-blue-500 transition">
                        Menu
                    </a>
                    <a href="CartServlet" 
                       class="text-gray-700 hover:text-green-500 transition">
                        Cart
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

	<section class="container mx-auto p-8">
		<div class="text-center mb-8">
			<h2 class="text-3xl font-bold">Our Menu</h2>
			<p class="text-lg text-gray-600">Browse our selection of coffee
				and pastries.</p>
		</div>
		<div id="cafe"
			class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6 mb-12">
			<%
			if (products != null && !products.isEmpty()) {
				for (Object obj : products) {
					if (obj instanceof Cafe) {
				Cafe cafe = (Cafe) obj;
			%>
			<div class="bg-white shadow-lg rounded-lg p-4">
				<img src="<%=cafe.getImageUrl() %>" alt="Coffee"
					class="w-full h-40 object-cover rounded-lg">
				<h3 class="text-xl font-semibold mt-4"><%=cafe.getNomCafe()%></h3>
				<p class="text-lg text-gray-700 mt-2"><%=cafe.getPrix()%>
					$
				</p>
				<div class="flex justify-between items-center mt-4">
					<form action="/coffeebreak/CartServlet" method="POST">
						<input type="hidden" name="productName"
							value="<%=cafe.getNomCafe()%>"> <input type="hidden"
							name="price" value="<%=cafe.getPrix()%>"> <input
							type="hidden" name="image" value="<%=cafe.getImageUrl()%>">
						<button type="submit"
							class="bg-gradient-to-r from-amber-700 to-amber-800 hover:from-amber-900 hover:to-amber-700 text-white font-bold px-4 py-2 rounded-md flex items-center">
							<svg xmlns="http://www.w3.org/2000/svg" class="w-5 h-5 mr-2"
								fill="none" viewBox="0 0 24 24" stroke="currentColor">
                    <path stroke-linecap="round" stroke-linejoin="round"
									stroke-width="2" d="M3 3h18M3 3l6 18h6l6-18" />
                </svg>
							Add to Cart
						</button>
					</form>
				</div>
			</div>
			<%
			}
			}
			} else {
			%>
			<p>No coffees available.</p>
			<%
			}
			%>
		</div>

		<div id="viennoiserie"
			class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6 mb-12">
			<%
			if (productsV != null && !productsV.isEmpty()) {
				for (Object obj : productsV) {
					if (obj instanceof Vinoiserie) {
				Vinoiserie vinoiserie = (Vinoiserie) obj;
			%>
			<div class="bg-white shadow-lg rounded-lg p-4">
				<img src="<%=vinoiserie.getImageUrl()%>" alt="Pastry"
					class="w-full h-40 object-cover rounded-lg">
				<h3 class="text-xl font-semibold mt-4"><%=vinoiserie.getNom()%></h3>
				<p class="text-lg text-gray-700 mt-2"><%=vinoiserie.getPrix()%>
					$
				</p>
				<div class="flex justify-between items-center mt-4">
					<button
						class="bg-gradient-to-r from-amber-700 to-amber-800 hover:from-amber-900 hover:to-amber-700 text-white font-bold px-4 py-2 rounded-md flex items-center">
						<svg xmlns="http://www.w3.org/2000/svg" class="w-5 h-5 mr-2"
							fill="none" viewBox="0 0 24 24" stroke="currentColor">
                            <path stroke-linecap="round"
								stroke-linejoin="round" stroke-width="2"
								d="M3 3h18M3 3l6 18h6l6-18" />
                        </svg>
						Add to Cart
					</button>
				</div>
			</div>
			<%
			}
			}
			} else {
			%>
			<p>No pastries available.</p>
			<%
			}
			%>
		</div>

	</section>
</body>

</html>
