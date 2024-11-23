package entities;

import java.io.Serializable;

public class Produit implements Serializable{
	
	private int id_produit;
	private int id_commerce;
	private String nom;
	private String description;
	private Double prix;
	private int stock;
	
	public Produit() {
		super();
	}
	
	public Produit(int id_produit, int id_commerce, String nom, String description, Double prix, int stock) {
		super();
		this.id_produit = id_produit;
		this.id_commerce = id_commerce;
		this.nom = nom;
		this.description = description;
		this.prix = prix;
		this.stock = stock;
	}
	
	public int getId_produit() {
		return id_produit;
	}
	public void setId_produit(int id_produit) {
		this.id_produit = id_produit;
	}
	public int getId_commerce() {
		return id_commerce;
	}
	public void setId_commerce(int id_commerce) {
		this.id_commerce = id_commerce;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getPrix() {
		return prix;
	}
	public void setPrix(Double prix) {
		this.prix = prix;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}

}
