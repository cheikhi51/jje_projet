package UtilisateurDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import SingletonConnection.SingletonConnection;
import entities.Utilisateur;
public class UtilisateurDaoImpl implements IUtilisateurDao {
    private Connection connection = SingletonConnection.getConnection();
    

    @Override
    public void saveUtilisateur(Utilisateur utilisateur) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO utilisateur (nom, email, mot_de_passe, contact, adresse, role) VALUES (?, ?, ?, ?, ?, ?)");
            ps.setString(1, utilisateur.getNom());
            ps.setString(2, utilisateur.getEmail());
            ps.setString(3, utilisateur.getMotDePasse());
            ps.setLong(4, utilisateur.getContact());
            ps.setString(5, utilisateur.getAdresse());
            ps.setString(6, utilisateur.getRole());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Utilisateur findByEmailAndPassword(String email, String password) {
        Utilisateur utilisateur = null;
        try {
            PreparedStatement ps = connection.prepareStatement(
                "SELECT * FROM utilisateur WHERE email = ? AND mot_de_passe = ?");
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                utilisateur = new Utilisateur();
                utilisateur.setIdUtilisateur(rs.getLong("id_utilisateur"));
                utilisateur.setNom(rs.getString("nom"));
                utilisateur.setEmail(rs.getString("email"));
                utilisateur.setContact(rs.getLong("contact"));
                utilisateur.setAdresse(rs.getString("adresse"));
                utilisateur.setRole(rs.getString("role"));
                utilisateur.setStatut(rs.getString("statut"));
                
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return utilisateur;
    }

	@Override
	public List<Utilisateur> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}

