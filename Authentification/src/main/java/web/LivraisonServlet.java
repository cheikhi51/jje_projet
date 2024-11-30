package web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import LivraisonDao.LivraisonDaoImpl;
import entities.Commande;
import entities.Livraison;
import entities.Produit;
import entities.Utilisateur;
import LivraisonDao.ILivraisonDao;

/**
 * Servlet implementation class LivraisonServlet
 */
public class LivraisonServlet extends HttpServlet {
	private ILivraisonDao livraisonDao;

    public void init() throws ServletException {
    	livraisonDao = new LivraisonDaoImpl();
    }
    public LivraisonServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();

        if (path.equals("/save.livraison")) {
        	HttpSession session = request.getSession(false); // Récupérer la session existante
            if (session != null) {
                Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
                if (utilisateur != null && "commercant".equals(utilisateur.getRole())) {
        	Long idCommercant = utilisateur.getIdUtilisateur();
        	String AdresseCommercant = utilisateur.getAdresse();

        	Long idProduit = Long.parseLong(request.getParameter("idProduit"));
        	Long quantite = Long.parseLong(request.getParameter("quantite"));
            String adresse = request.getParameter("adresse");
            
            Produit produit = produitDao.getProduit(idProduit);
            Long idCommercant = produit.getIdCommercant(); // Assure-toi que Produit contient `idCommercant`

            
            Livraison livraison = new Livraison();
            livraison.setIdClient(idClient);
            livraison.setIdProduit(idProduit);
            livraison.setIdCommercant(idCommercant);
            livraison.setQuantite(quantite);
            livraison.setAdresse(adresse);
            

            livraisonDao.saveLivraison(livraison);

            response.sendRedirect("indexClient.jsp");
                } else {
                    response.sendRedirect("login.jsp"); // Redirige si non connecté ou pas un client
                }
            } else {
                response.sendRedirect("login.jsp"); // Redirige si pas de session
            }
            }
	} 
	}

}
