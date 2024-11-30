package web;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import CommandeDao.ICommandeDao;
import ProduitDao.IProduitDao;
import ProduitDao.ProduitDaoImpl;
import CommandeDao.CommandeDaoImpl;
import entities.Commande;
import entities.Produit;
import entities.Utilisateur;

/**
 * Servlet implementation class CommandeServlet
 */
public class CommandeServlet extends HttpServlet {
	private ICommandeDao commandeDao;
	private IProduitDao produitDao;

    public void init() throws ServletException {
    	commandeDao = new CommandeDaoImpl();
    	produitDao = new ProduitDaoImpl();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		if (path.equals("/commercant.commande")) {
	        
	    	HttpSession session = request.getSession(false); // Récupérer la session existante
            if (session != null) {
                Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
                if (utilisateur != null && "commercant".equals(utilisateur.getRole())) {
            CommandeModel model=new CommandeModel();  // Assurez-vous que la classe ProduitDao contient la méthode getAllProduits
            List<Commande> commandes = commandeDao.getCommercantCommandes(utilisateur.getIdUtilisateur());
            model.setCommandes(commandes);
            request.setAttribute("model", model);
            request.setAttribute("commandes", commandes);
            request.getRequestDispatcher("test2.jsp").forward(request, response);
                } else {
                    response.sendRedirect("login.jsp"); // Redirige si non connecté ou pas un client
                }
            } else {
                response.sendRedirect("login.jsp"); // Redirige si pas de session
            }
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();

        if (path.equals("/save.commande")) {
        	HttpSession session = request.getSession(false); // Récupérer la session existante
            if (session != null) {
                Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
                if (utilisateur != null && "client".equals(utilisateur.getRole())) {
        	Long idClient = utilisateur.getIdUtilisateur();
        	Long idProduit = Long.parseLong(request.getParameter("idProduit"));
        	Long quantite = Long.parseLong(request.getParameter("quantite"));
            String adresse = request.getParameter("adresse");
            
            Produit produit = produitDao.getProduit(idProduit);
            Long idCommercant = produit.getIdCommercant(); // Assure-toi que Produit contient `idCommercant`

            
            Commande commande = new Commande();
            commande.setIdClient(idClient);
            commande.setIdProduit(idProduit);
            commande.setIdCommercant(idCommercant);
            commande.setQuantite(quantite);
            commande.setAdresse(adresse);
            

            commandeDao.saveCommande(commande);

            response.sendRedirect("indexClient.jsp");
                } else {
                    response.sendRedirect("login.jsp"); // Redirige si non connecté ou pas un client
                }
            } else {
                response.sendRedirect("login.jsp"); // Redirige si pas de session
            }
            } else if (path.equals("/ConfirmationLivraison.commande")) {

    	    	Long idCommande = Long.parseLong(request.getParameter("idCommande"));
        	    String adresseLivraison = request.getParameter("adresseLivraison");
        	    
    	        request.setAttribute("idCommande", idCommande);
    	        request.setAttribute("adresseLivraison", adresseLivraison);
    	        request.getRequestDispatcher("ConfirmationLivraison.jsp").forward(request, response);
                   }
           }
	

}
