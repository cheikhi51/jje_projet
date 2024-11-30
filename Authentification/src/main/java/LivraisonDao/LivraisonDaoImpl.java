package LivraisonDao;
import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import SingletonConnection.SingletonConnection;
import entities.Livraison;

public class LivraisonDaoImpl implements ILivraisonDao {
    private Connection connection = SingletonConnection.getConnection();

    public void saveLivraison(Livraison livraison){
    try {
        PreparedStatement ps = connection.prepareStatement(
        		"INSERT INTO livraison (id_commande,id_commercant, id_livreur,adresse_commercant, adresse_livraison, date_livraison, statut_livraison) VALUES (?,?,?, ?, ?, ?, ?)");
            
        ps.setLong(1, livraison.getIdCommande());
        ps.setLong(2, livraison.getIdCommercant());

        ps.setLong(2, livraison.getIdLivreur());
        ps.setString(3, livraison.getAdresseCommercant());
        ps.setString(3, livraison.getAdresseLivraison());
        ps.setDate(4, new java.sql.Date(livraison.getAdresseLivraison().getTime()));
        ps.setString(5, livraison.getStatutLivraison());
        ps.executeUpdate();
        ps.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    }

	@Override
	public Livraison findLivraison(long idLivreur) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Livraison> getAllLivraison() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Livraison> getLivreurLivraisons(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
