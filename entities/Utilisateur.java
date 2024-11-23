package entities;

import java.io.Serializable;

public class Utilisateur implements Serializable{
	//les attributs de la classe utilisateur	
	private int id_utilisateur;
	private String nom;
	private String email;
	private String mot_de_passe;
	private int contact;
	private Role role;
	private String adresse;
	
	public Utilisateur() {
		super();
	}

	public Utilisateur(int id_utilisateur, String nom, String email, String mot_de_passe, int contact, Role role) {
		super();
		this.id_utilisateur = id_utilisateur;
		this.nom = nom;
		this.email = email;
		this.mot_de_passe = mot_de_passe;
		this.contact = contact;
		this.role = role;
	}
	
	public int getId_utilisateur() {
		return id_utilisateur;
	}
	public void setId_utilisateur(int id_utilisateur) {
		this.id_utilisateur = id_utilisateur;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMot_de_passe() {
		return mot_de_passe;
	}
	public void setMot_de_passe(String mot_de_passe) {
		this.mot_de_passe = mot_de_passe;
	}
	public int getContact() {
		return contact;
	}
	public void setContact(int contact) {
		this.contact = contact;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	
	
	
}
