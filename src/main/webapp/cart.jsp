<%@page import="coffeebreak.config.StandardCalculation"%>
<%@page import="coffeebreak.interfaces.CartCalculationStrategy"%>
<%@page import="coffeebreak.dao.CartDAO"%>
<%@page import="coffeebreak.models.Order"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Arrays"%>
<%@ page import="java.util.List"%>
<%@ page import="coffeebreak.*"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Shopping Cart</title>
<script src="https://cdn.tailwindcss.com"></script>
</head>

<body class="bg-gray-50">
	<header class="bg-gray-800 text-white p-4 w-full">
		<nav class="flex justify-between items-center container mx-auto">
			<a href="index.jsp" class="text-2xl font-bold">CoffeeBreak</a>
			<div class="space-x-6">
				<!-- Home link -->
				<a href="MenuServlet" class="hover:text-gray-400">Home</a>
				<!-- Cart link -->
				<a href="CartServlet" class="hover:text-gray-400">Cart</a>
				<!-- Logout link -->
				<a href="user/logout.jsp" class="hover:text-gray-400">Logout</a>
			</div>
		</nav>
	</header>
	
	<!-- Main content -->
	<div class="container mx-auto mt-8">
		<h1 class="text-4xl font-bold mb-6 text-gray-800 text-center">Shopping Cart</h1>

		<!-- Cart Table -->
		<div class="bg-white rounded-lg shadow-md p-6 w-full max-w-2xl mx-auto">
			<table class="w-full">
				<thead>
					<tr class="border-b-2">
						<th class="text-left pb-2">Product</th>
						<th class="text-center pb-2">Quantity</th>
						<th class="text-right pb-2">Price</th>
						<th class="text-right pb-2">Subtotal</th>
					</tr>
				</thead>
				<tbody>
					<%
					List<Order> orders = (List<Order>) request.getAttribute("product");
					CartDAO cart = new CartDAO(orders);
					CartCalculationStrategy strategy = new StandardCalculation();

					for (Order order : orders) {
					%>
					<tr class="border-b">
						<td class="py-4"><%=order.getProductName()%></td>
						<td class="text-center"><input type="number"
							value="<%=order.getQuantity()%>" min="1"
							class="w-16 border-gray-300 rounded text-center"
							onchange="updateSubtotal(this, <%=order.getPrice()%>)"></td>
						<td class="text-right">$<%=order.getPrice()%></td>
						<td class="text-right font-bold" id="subtotal">$<%=order.getSubtotal()%></td>
					</tr>
					<%
					}
					%>
				</tbody>
			</table>

			<!-- Total Section -->
			<div class="mt-6 text-right">
				<strong class="text-lg">Total:</strong> <span id="total" class="text-xl font-bold">
					$<%=cart.calculateTotal(strategy)%>
				</span>
			</div>
		</div>

		<!-- Checkout Button -->
		<div class="flex justify-center mt-8">
			<form action="/coffeebreak/CartServlet" method="POST">
				<input type="hidden" name="checkout" value="hhhh">
				<input type="hidden" name="id_user" value="<%=(int) session.getAttribute("id_user")%>">
				<button type="submit" class="px-6 py-2 bg-blue-500 text-white rounded hover:bg-blue-600">
					Check out
				</button>
			</form>
		</div>
	</div>
</body>

</html>
