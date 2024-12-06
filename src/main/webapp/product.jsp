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

<!-- Tailwind CSS -->
<script src="https://cdn.tailwindcss.com"></script>
</head>

<body class="bg-gray-50">

	<!-- Navbar -->
	<header class="bg-gray-800 text-white p-4">
		<nav class="flex justify-between items-center container mx-auto">
			<a href="index.jsp" class="text-2xl font-bold">CoffeeBreak</a>
			<div class="space-x-4">
				<a href="MenuServlet" class="hover:text-gray-400">Home</a> <a
					href="CartServlet" class="hover:text-gray-400">Cart</a>
					<a
					href="user/logout.jsp" class="hover:text-gray-400">logout</a>
			</div>
		</nav>
	</header>

	<!-- Main Content -->
	<section class="container mx-auto p-8">
		<div class="text-center mb-8">
			<h2 class="text-3xl font-bold">Our Menu</h2>
			<p class="text-lg text-gray-600">Browse our selection of coffee
				and pastries.</p>
		</div>
		<!-- Café Products -->
		<div id="cafe"
			class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6 mb-12">
			<%
			if (products != null && !products.isEmpty()) {
				for (Object obj : products) {
					if (obj instanceof Cafe) {
				Cafe cafe = (Cafe) obj;
			%>
			<div class="bg-white shadow-lg rounded-lg p-4">
				<!-- Utiliser l'URL de l'image récupérée depuis la base de données -->
				<img src="<%=cafe.getImageUrl() %>" alt="Coffee"
					class="w-full h-40 object-cover rounded-lg">
				<h3 class="text-xl font-semibold mt-4"><%=cafe.getNomCafe()%></h3>
				<p class="text-lg text-gray-700 mt-2"><%=cafe.getPrix()%>
					€
				</p>
				<div class="flex justify-between items-center mt-4">
					<!-- Form to submit the coffee details to a servlet -->
					<form action="/coffeebreak/CartServlet" method="POST">
						<!-- Hidden fields to send coffee details to the servlet -->
						<input type="hidden" name="productName"
							value="<%=cafe.getNomCafe()%>"> <input type="hidden"
							name="price" value="<%=cafe.getPrix()%>"> <input
							type="hidden" name="image" value="<%=cafe.getImageUrl()%>">
						<!-- Submit button -->
						<button type="submit"
							class="bg-blue-600 text-white px-4 py-2 rounded-md hover:bg-blue-700 flex items-center">
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
			<p>No café products available.</p>
			<%
			}
			%>
		</div>

		<!-- Viennoiserie Products -->
		<div id="viennoiserie"
			class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6 mb-12">
			<%
			if (productsV != null && !productsV.isEmpty()) {
				for (Object obj : productsV) {
					if (obj instanceof Vinoiserie) {
				Vinoiserie vinoiserie = (Vinoiserie) obj;
			%>
			<div class="bg-white shadow-lg rounded-lg p-4">
				<!-- Utiliser l'URL de l'image récupérée depuis la base de données -->
				<img src="<%=vinoiserie.getImageUrl()%>" alt="Pastry"
					class="w-full h-40 object-cover rounded-lg">
				<h3 class="text-xl font-semibold mt-4"><%=vinoiserie.getNom()%></h3>
				<p class="text-lg text-gray-700 mt-2"><%=vinoiserie.getPrix()%>
					€
				</p>
				<div class="flex justify-between items-center mt-4">
					<button
						class="bg-blue-600 text-white px-4 py-2 rounded-md hover:bg-blue-700 flex items-center">
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
			<p>No viennoiserie products available.</p>
			<%
			}
			%>
		</div>

	</section>

	<!-- Footer -->
	<footer class="bg-gray-800 text-white py-8">
		<div class="container mx-auto text-center">
			<p>&copy; 2024 CoffeeBreak - All Rights Reserved</p>
		</div>
	</footer>

</body>

</html>
