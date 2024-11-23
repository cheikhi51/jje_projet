package Dao;

import java.util.List;

import entities.Produit;

public interface IProduitDao {
	
	public Produit saveProduit(Produit p);
	public List<Produit> afficherProduits();
	public Produit findProduitById(int id_produit);
	public Produit updateProduit(Produit p);
	public void deleteProduit(int id_produit);

}
