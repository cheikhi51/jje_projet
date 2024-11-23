package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Produit;

public class ProduitDaoImpl implements IProduitDao{

	@Override
	public Produit saveProduit(Produit p) {
		Connection connection = SingletonConnection.getConnection();
	    try {
	        // Updated SQL query to include id_commerce
	        PreparedStatement ps = connection.prepareStatement(
	            "INSERT INTO produit(nom, description, prix, stock, id_commerce) VALUES(?, ?, ?, ?, ?)", 
	            PreparedStatement.RETURN_GENERATED_KEYS); // To retrieve the auto-generated id_produit
	        
	        // Setting values for the query
	        ps.setString(1, p.getNom());
	        ps.setString(2, p.getDescription());
	        ps.setDouble(3, p.getPrix());
	        ps.setInt(4, p.getStock());
	        ps.setInt(5, p.getId_commerce()); // Setting the id_commerce
	        
	        // Execute the update
	        ps.executeUpdate();
	        
	        // Retrieve the generated id_produit
	        ResultSet rs = ps.getGeneratedKeys();
	        if (rs.next()) {
	            p.setId_produit(rs.getInt(1)); // Assign the generated ID to the Produit object
	        }
	        rs.close();
	        ps.close();
	        
	        return p;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}

	@Override
	public List<Produit> afficherProduits() {
		Connection connection = SingletonConnection.getConnection();
		List<Produit> produits = new ArrayList<>();
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM produit");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Produit produit = new Produit();
				produit.setId_produit(rs.getInt("id_produit"));
				produit.setId_commerce(rs.getInt("id_commerce"));
				produit.setNom(rs.getString("nom"));
				produit.setDescription(rs.getString("description"));
				produit.setPrix(rs.getDouble("prix"));
				produit.setStock(rs.getInt("stock"));
				produits.add(produit);
			}
			rs.close();
			ps.close();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return produits;
	}

	@Override
	public Produit findProduitById(int id_produit) {
		String query = "SELECT * FROM produit WHERE id_produit=?";
	    try (Connection connection = SingletonConnection.getConnection();
	         PreparedStatement ps = connection.prepareStatement(query)) {
	        
	        ps.setInt(1, id_produit);
	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                Produit produit = new Produit();
	                produit.setId_produit(rs.getInt("id_produit"));
					produit.setId_commerce(rs.getInt("id_commerce"));
					produit.setNom(rs.getString("nom"));
					produit.setDescription(rs.getString("description"));
					produit.setPrix(rs.getDouble("prix"));
					produit.setStock(rs.getInt("stock"));
	                return produit;
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        // Log the error or rethrow as a custom exception
	    }
	    return null;
	}

	@Override
	public Produit updateProduit(Produit p) {
		Connection connection = SingletonConnection.getConnection();
	    try {
	        PreparedStatement ps = connection.prepareStatement("UPDATE produit SET nom = ?, description = ?, prix = ?, stock = ? WHERE id_produit = ?");
	        ps.setString(1, p.getNom());
			ps.setString(2, p.getDescription());
			ps.setDouble(3, p.getPrix());
			ps.setInt(4, p.getStock());
			ps.setInt(5, p.getId_produit());
	        ps.executeUpdate();
	        ps.close();
	        return p;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}

	@Override
	public void deleteProduit(int id_produit) {
		Connection connection = SingletonConnection.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("DELETE FROM produit WHERE id_produit=?");
			ps.setInt(1, id_produit);
			ps.executeUpdate();
			ps.close();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

}
