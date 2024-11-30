package CommandeDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import SingletonConnection.SingletonConnection;
import entities.Commande;
import entities.Produit;

public class CommandeDaoImpl implements ICommandeDao{
    private Connection connection = SingletonConnection.getConnection();
    

	@Override
	public void saveCommande(Commande commande) {
		try {
            PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO commande (id_client,id_produit,id_commercant,quantite,adresse) VALUES (?,?, ?, ?, ?)");
            ps.setLong(1, commande.getIdClient());
            ps.setLong(2, commande.getIdProduit());
            ps.setLong(3, commande.getIdCommercant());
            ps.setLong(4, commande.getQuantite());
            ps.setString(5, commande.getAdresse());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
	}

	@Override
	public Commande findCommande(long idClient) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Commande> getAllCommande() {
		// TODO Auto-generated method stub
		return null;
	}
	/**	public List<Commande> getCommercantCommandes(Long id) {
		List<Commande> commandes = new ArrayList<>();
	    Connection connection = SingletonConnection.getConnection();
	    try {
	        // Préparation de la requête SELECT
	        PreparedStatement ps = connection.prepareStatement("SELECT * FROM commande WHERE id_commercant = ?");
	        ps.setLong(1, id);
	        ResultSet rs = ps.executeQuery();
	        
	        // Parcours des résultats
	        while (rs.next()) {
	            Commande commande = new Commande();
	            commande.setIdCommande(rs.getLong("id_commande"));
	            commande.setIdClient(rs.getLong("id_client"));
	            commande.setIdProduit(rs.getLong("id_produit"));
	            commande.setIdCommercant(rs.getLong("id_commercant"));
	            commande.setQuantite(rs.getLong("quantite"));
	            commande.setAdresse(rs.getString("adresse"));
	            commande.setDateCommande(rs.getTimestamp("date_commande").toLocalDateTime());
	            commande.setStatut(rs.getString("statut"));
	            
	            
	            // Ajout du commande à la liste
	            commandes.add(commande);
	        }

	        ps.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return commandes;
	}
	*/
	public List<Commande> getCommercantCommandes(Long id) {
	    List<Commande> commandes = new ArrayList<>();
	    Connection connection = SingletonConnection.getConnection();
	    try {
	        // Requête avec jointure pour récupérer les détails du produit
	        String sql = "SELECT c.*, p.nom, p.prix, p.image " +
	                     "FROM commande c " +
	                     "JOIN produit p ON c.id_produit = p.id_produit " +
	                     "WHERE c.id_commercant = ?";
	        PreparedStatement ps = connection.prepareStatement(sql);
	        ps.setLong(1, id);
	        ResultSet rs = ps.executeQuery();
	        
	        while (rs.next()) {
	            Commande commande = new Commande();
	            commande.setIdCommande(rs.getLong("id_commande"));
	            commande.setIdClient(rs.getLong("id_client"));
	            commande.setIdProduit(rs.getLong("id_produit"));
	            commande.setQuantite(rs.getLong("quantite"));
	            commande.setAdresse(rs.getString("adresse"));
	            commande.setDateCommande(rs.getTimestamp("date_commande").toLocalDateTime());
	            commande.setStatut(rs.getString("statut"));
	            
	            // Détails du produit
	            Produit produit = new Produit();
	            produit.setNom(rs.getString("nom"));
	            produit.setPrix(rs.getDouble("prix"));
	            produit.setImage(rs.getString("image"));
	            commande.setProduit(produit); // Ajoutez un champ Produit dans votre classe Commande
	            
	            commandes.add(commande);
	        }
	        ps.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return commandes;
	}


}
