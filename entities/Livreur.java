package entities;

import java.io.Serializable;

public class Livreur implements Serializable{
	//id_livreur	nom	telephone	disponibilite	
	private int id_livreur;
	private String nom;
	private int telephone;
	private Disponibilite disponibilite;
	
	public enum Disponibilite{
		disponible, occupe
	}
	
	public Livreur() {
		super();
	}

	public Livreur(int id_livreur, String nom, int telephone, Disponibilite disponibilite) {
		super();
		this.id_livreur = id_livreur;
		this.nom = nom;
		this.telephone = telephone;
		this.disponibilite = disponibilite;
	}

	public int getId_livreur() {
		return id_livreur;
	}

	public void setId_livreur(int id_livreur) {
		this.id_livreur = id_livreur;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getTelephone() {
		return telephone;
	}

	public void setTelephone(int telephone) {
		this.telephone = telephone;
	}

	public Disponibilite getDisponibilite() {
		return disponibilite;
	}

	public void setDisponibilite(Disponibilite disponibilite) {
		this.disponibilite = disponibilite;
	}
	
}
