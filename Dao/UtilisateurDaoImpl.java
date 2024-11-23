package Dao;

import java.util.ArrayList;
import java.util.List;
import entities.Role;
import entities.Utilisateur;
import java.sql.*;
public class UtilisateurDaoImpl implements IUtilisateurDao {

	@Override
	public Utilisateur saveUtilisateur(Utilisateur u) {
		Connection connection = SingletonConnection.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO utilisateur(nom,email,mot_de_passe,contact,adresse,role) VALUES(?,?,?,?,?,?)");
			ps.setString(1, u.getNom());
			ps.setString(2, u.getEmail());
			ps.setString(3, u.getMot_de_passe());
			ps.setInt(4, u.getContact());
			ps.setString(5, u.getAdresse());
			ps.setString(6, u.getRole().name());
			ps.executeUpdate();
			
			ps.close();
			return u;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Utilisateur> afficherUtilisateurs() {
		Connection connection = SingletonConnection.getConnection();
		List<Utilisateur> utilisateurs = new ArrayList<>();
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM utilisateur");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Utilisateur utilisateur = new Utilisateur();
				utilisateur.setId_utilisateur(rs.getInt("id_utilisateur"));
				utilisateur.setNom(rs.getString("nom"));
				utilisateur.setEmail(rs.getString("email"));
				utilisateur.setMot_de_passe(rs.getString("mot_de_passe"));
				utilisateur.setContact(rs.getInt("contact"));
				utilisateur.setAdresse(rs.getString("adresse"));
				 // Convert role String to enum and set it
			    String roleStr = rs.getString("role");
			    Role role = Role.valueOf(roleStr);
			    utilisateur.setRole(role);
			    utilisateurs.add(utilisateur);
			}
			rs.close();
			ps.close();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return utilisateurs;
	}

	@Override
	public Utilisateur updateUtilisateur(Utilisateur u) {
		
		Connection connection = SingletonConnection.getConnection();
	    try {
	        PreparedStatement ps = connection.prepareStatement("UPDATE utilisateur SET nom = ?, email = ?, mot_de_passe = ?, contact = ?, adresse = ?, role = ? WHERE id_utilisateur = ?");
	        ps.setString(1, u.getNom());
			ps.setString(2, u.getEmail());
			ps.setString(3, u.getMot_de_passe());
			ps.setInt(4, u.getContact());
			ps.setString(5, u.getAdresse());
			ps.setString(6, u.getRole().name());
			ps.setInt(7, u.getId_utilisateur());
	        ps.executeUpdate();
	        ps.close();
	        return u;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}

	@Override
	public void deleteUtilisateur(int id_utilisateur) {
		Connection connection = SingletonConnection.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("DELETE FROM utilisateur WHERE id_utilisateur=?");
			ps.setInt(1, id_utilisateur);
			ps.executeUpdate();
			ps.close();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Utilisateur findUtilisateurById(int id_utilisateur) {
	    String query = "SELECT * FROM utilisateur WHERE id_utilisateur=?";
	    try (Connection connection = SingletonConnection.getConnection();
	         PreparedStatement ps = connection.prepareStatement(query)) {
	        
	        ps.setInt(1, id_utilisateur);
	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                Utilisateur utilisateur = new Utilisateur();
	                utilisateur.setId_utilisateur(rs.getInt("id_utilisateur"));
	                utilisateur.setNom(rs.getString("nom"));
	                utilisateur.setEmail(rs.getString("email"));
	                utilisateur.setMot_de_passe(rs.getString("mot_de_passe"));
	                utilisateur.setContact(rs.getInt("contact"));
	                utilisateur.setAdresse(rs.getString("adresse"));

	                // Convert role String to enum safely
	                String roleStr = rs.getString("role");
	                try {
	                    Role role = Role.valueOf(roleStr);
	                    utilisateur.setRole(role);
	                } catch (IllegalArgumentException | NullPointerException e) {
	                    System.err.println("Invalid role value: " + roleStr);
	                    utilisateur.setRole(null); // Or set a default value
	                }

	                return utilisateur;
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        // Log the error or rethrow as a custom exception
	    }
	    return null; // Return null if no record is found or an error occurs
	}

	
	

}
