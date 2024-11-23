package web;

import jakarta.servlet.RequestDispatcher;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.List;

import Dao.ProduitDaoImpl;

import entities.Produit;


/**
 * Servlet implementation class produitServlet
 */
@WebServlet("/produitServlet")
public class produitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ProduitDaoImpl produitdao = new ProduitDaoImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Produit> produits = produitdao.afficherProduits();
		
		request.setAttribute("produits", produits);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/produits.jsp");
		
		dispatcher.forward(request, response);
		
		String action = request.getParameter("action");
		if (action.equals("action")) {
		int id_produit = Integer.parseInt(request.getParameter("id_produit"));
        ProduitDaoImpl produitdao = new ProduitDaoImpl();
        Produit produit = produitdao.findProduitById(id_produit);

        request.setAttribute("produit", produit);
        request.getRequestDispatcher("updateProduit.jsp").forward(request, response);
		}
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if ("delete".equals(action)) {
            // Suppression d'un produit
        	int id_produit = Integer.parseInt(request.getParameter("id_produit"));
            produitdao.deleteProduit(id_produit);
            
            response.sendRedirect("produitServlet");

        } else if ("add".equals(action)) {
            // Ajout d'un nouveau produit
        	String nom = request.getParameter("nom");
            String description = request.getParameter("description");
            Double prix = Double.parseDouble(request.getParameter("prix"));
            int stock = Integer.parseInt(request.getParameter("stock"));
            int id_commerce = Integer.parseInt(request.getParameter("id_commerce"));
        
            Produit produit = new Produit();
            produit.setNom(nom);
            produit.setDescription(description);
            produit.setPrix(prix);
            produit.setStock(stock);
            produit.setId_commerce(id_commerce);
            produitdao.saveProduit(produit);
            
            response.sendRedirect("produitServlet");
        }
		
	}

}
