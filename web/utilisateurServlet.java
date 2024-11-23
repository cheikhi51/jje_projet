package web;

import jakarta.servlet.RequestDispatcher;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import Dao.UtilisateurDaoImpl;
import entities.Role;
import entities.Utilisateur;

/**
 * Servlet implementation class utilisateurServlet
 */
@WebServlet("/utilisateurServlet")
public class utilisateurServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private UtilisateurDaoImpl utilisateurdao = new UtilisateurDaoImpl();
	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Utilisateur> utilisateurs = utilisateurdao.afficherUtilisateurs();
		
		request.setAttribute("utilisateurs", utilisateurs);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/utilisateurs.jsp");
		
		dispatcher.forward(request, response);
		String action = request.getParameter("action");
		if (action.equals("action")) {
		int id = Integer.parseInt(request.getParameter("id_utilisateur"));
        UtilisateurDaoImpl utilisateurDao = new UtilisateurDaoImpl();
        Utilisateur utilisateur = utilisateurDao.findUtilisateurById(id);

        request.setAttribute("utilisateur", utilisateur);
        request.getRequestDispatcher("updateUtilisateur.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");
		/**
		 * if ("update".equals(action)) {
            
            int id_utilisateur = Integer.parseInt(request.getParameter("id_utilisateur"));
            String nom = request.getParameter("nom");
            String email = request.getParameter("email");
            String mot_de_passe = request.getParameter("mot_de_passe");
            int contact = Integer.parseInt(request.getParameter("contact"));
            String adresse = request.getParameter("adresse");
            Role role = Role.valueOf(request.getParameter("role"));
            Utilisateur utilisateur = new Utilisateur();
            utilisateur.setId_utilisateur(id_utilisateur);
            utilisateur.setNom(nom);
            utilisateur.setEmail(email);
            utilisateur.setMot_de_passe(mot_de_passe);
            utilisateur.setContact(contact);
            utilisateur.setAdresse(adresse);
            utilisateur.setRole(role);
            utilisateurdao.updateUtilisateur(utilisateur);
            
            response.sendRedirect("utilisateurServlet");

        }
		 */
		if ("delete".equals(action)) {
            // Suppression d'un utilisateur
        	int id_utilisateur = Integer.parseInt(request.getParameter("id_utilisateur"));
            utilisateurdao.deleteUtilisateur(id_utilisateur);
            
            response.sendRedirect("utilisateurServlet");

        } else if ("add".equals(action)) {
            // Ajout d'un nouveau utilisateur
        	String nom = request.getParameter("nom");
            String email = request.getParameter("email");
            String mot_de_passe = request.getParameter("mot_de_passe");
            int contact = Integer.parseInt(request.getParameter("contact"));
            String adresse = request.getParameter("adresse");
            Role role = Role.valueOf(request.getParameter("role"));
            Utilisateur utilisateur = new Utilisateur();
            utilisateur.setNom(nom);
            utilisateur.setEmail(email);
            utilisateur.setMot_de_passe(mot_de_passe);
            utilisateur.setContact(contact);
            utilisateur.setAdresse(adresse);
            utilisateur.setRole(role);
            utilisateurdao.saveUtilisateur(utilisateur);
            
            response.sendRedirect("utilisateurServlet");
        }
	}

}
