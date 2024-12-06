//package coffeebreak.servlets;
//
//import coffeebreak.factory.ProductFactory;
//import coffeebreak.interfaces.Product;
//import coffeebreak.models.Cafe;
//import coffeebreak.models.Vinoiserie;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.*;
//import java.io.IOException;
//import java.sql.SQLException;
//
//@WebServlet("/ProductServlet")
//public class ProductServlet extends HttpServlet {
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String productType = request.getParameter("productType");
//        String action = request.getParameter("action");
//
//        try {
//            Product productDAO = ProductFactory.getProductDAO(productType);
//
//            if ("add".equals(action)) {
//                // Add product
//                if ("cafe".equalsIgnoreCase(productType)) {
//                    Cafe cafe = new Cafe(
//                            Integer.parseInt(request.getParameter("id")),
//                            request.getParameter("name"),
//                            Float.parseFloat(request.getParameter("price")),
//                            Integer.parseInt(request.getParameter("sales"))
//                    );
//                    productDAO.insertProduct(cafe);
//                } else if ("vinoiserie".equalsIgnoreCase(productType)) {
//                    Vinoiserie vinoiserie = new Vinoiserie(
//                            Integer.parseInt(request.getParameter("id")),
//                            request.getParameter("name"),
//                            Float.parseFloat(request.getParameter("price")),
//                            Integer.parseInt(request.getParameter("sales"))
//                    );
//                    productDAO.insertProduct(vinoiserie);
//                }
//                response.sendRedirect("list.jsp?productType=" + productType);
//            } else if ("get".equals(action)) {
//                // Get product details
//                String name = request.getParameter("name");
//                Object product = productDAO.getProductDetails(name);
//                request.setAttribute("product", product);
//                request.setAttribute("productType", productType);
//                request.getRequestDispatcher("details.jsp").forward(request, response);
//            } else if ("update".equals(action)) {
//                // Update sales
//                int id = Integer.parseInt(request.getParameter("id"));
//                int newSales = Integer.parseInt(request.getParameter("sales"));
//                productDAO.updateSales(id, newSales);
//                response.sendRedirect("list.jsp?productType=" + productType);
//            }else if ("delete".equals(action)) {
//                // Delete product
//                int id = Integer.parseInt(request.getParameter("id"));
//                productDAO.deleteProduct(id);
//                response.sendRedirect("list.jsp?productType=" + productType);
//            }
//
//        } catch (SQLException e) {
//            throw new ServletException("Error processing product operation", e);
//        }
//    }
//}


package coffeebreak.servlets;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import coffeebreak.config.CloudinaryUtil;
import coffeebreak.factory.ProductFactory;
import coffeebreak.interfaces.Product;
import coffeebreak.models.Cafe;
import coffeebreak.models.Vinoiserie;

@WebServlet("/ProductServlet")
@MultipartConfig(
	    fileSizeThreshold = 1024 * 1024 * 2, // 2 MB
	    maxFileSize = 1024 * 1024 * 10,      // 10 MB
	    maxRequestSize = 1024 * 1024 * 50    // 50 MB
	)
public class ProductServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String productType = request.getParameter("productType");
	    String action = request.getParameter("action");
	    try {
	        Product productDAO = ProductFactory.getProductDAO(productType);

	        if ("list".equals(action)) {
	            List<?> products = productDAO.getAllProducts();
	            request.setAttribute("products", products);
	            request.setAttribute("productType", productType);
	            request.getRequestDispatcher("list.jsp").forward(request, response);

	        } else if ("details".equals(action)) {
	            int id = Integer.parseInt(request.getParameter("id"));
	            Object product = productDAO.getProductDetails(id); // Add `getProductDetails` method in DAO if not present
	            request.setAttribute("product", product);
	            request.setAttribute("productType", productType);
	            request.getRequestDispatcher("detail.jsp").forward(request, response);
	        }

	    } catch (SQLException e) {
	        throw new ServletException("Error retrieving product information", e);
	    }
	}


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productType = request.getParameter("productType");
        String action = request.getParameter("action");

        try {
            Product productDAO = ProductFactory.getProductDAO(productType);

            if ("add".equals(action)) {
            	String uploadsDir = getServletContext().getRealPath("") + "uploads";
            	File uploadsFolder = new File(uploadsDir);
            	if (!uploadsFolder.exists()) {
            	    uploadsFolder.mkdirs();
            	}

            	Part filePart = request.getPart("image");
            	String fileName = filePart.getSubmittedFileName();
            	String filePath = uploadsDir + File.separator + fileName;

            	try {
            	    filePart.write(filePath); // Save the file temporarily
            	} catch (IOException e) {
            	    throw new ServletException("Error saving the uploaded file", e);
            	}

            	// Then upload to Cloudinary
            	String imageUrl = CloudinaryUtil.uploadImage(filePath);

                if ("cafe".equalsIgnoreCase(productType)) {
                    Cafe cafe = new Cafe(
                            request.getParameter("name"),
                            Float.parseFloat(request.getParameter("price")),
                            Integer.parseInt(request.getParameter("sales")),
                            imageUrl
                    );
                    productDAO.insertProduct(cafe);
                    request.setAttribute("message", "Café ajouté avec succès!");
                } else if ("vinoiserie".equalsIgnoreCase(productType)) {
                    Vinoiserie vinoiserie = new Vinoiserie(
                            request.getParameter("name"),
                            Float.parseFloat(request.getParameter("price")),
                            Integer.parseInt(request.getParameter("sales")),
                            imageUrl
                    );
                    productDAO.insertProduct(vinoiserie);
                    request.setAttribute("message", "Vinoiserie ajoutée avec succès!");
                }
            } else if ("update".equals(action)) {
                // Update product
                int id = Integer.parseInt(request.getParameter("id"));
                String name = request.getParameter("name");
                float price = Float.parseFloat(request.getParameter("price"));
                int sales = Integer.parseInt(request.getParameter("sales"));

                if ("cafe".equals(productType)) {
                    Cafe cafe = new Cafe(id, name, price, sales);
                    productDAO.updateProduct(cafe);
                    request.setAttribute("message", "Café mis à jour avec succès!");
                } else if ("vinoiserie".equals(productType)) {
                    Vinoiserie vinoiserie = new Vinoiserie(id, name, price, sales);
                    productDAO.updateProduct(vinoiserie);
                    request.setAttribute("message", "Vinoiserie mise à jour avec succès!");
                }
            } else if ("delete".equals(action)) {
                // Delete product
                int id = Integer.parseInt(request.getParameter("id"));
                boolean isDeleted = productDAO.deleteProduct(id);
                if (isDeleted) {
                	if ("cafe".equals(productType)) {
                		request.setAttribute("message", "Café supprimé avec succès!");
                    } else if ("vinoiserie".equals(productType)) {
                    	request.setAttribute("message", "Vinoiserie supprimé avec succès!");
                    }

                } else {
                    request.setAttribute("message", "Erreur lors de la suppression du produit.");
                }
            }

            // Forward the request to list.jsp instead of redirecting
            request.setAttribute("productType", productType);
            List<?> products = productDAO.getAllProducts();
            request.setAttribute("products", products);
            request.getRequestDispatcher("list.jsp").forward(request, response);

        } catch (SQLException e) {
            throw new ServletException("Erreur lors de l'opération produit", e);
        }
    }


}
