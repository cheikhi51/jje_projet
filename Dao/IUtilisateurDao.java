package Dao;

import java.util.List;

import entities.Utilisateur;

public interface IUtilisateurDao {
	
	public Utilisateur saveUtilisateur(Utilisateur u);
	public List<Utilisateur> afficherUtilisateurs();
	public Utilisateur updateUtilisateur(Utilisateur u);
	public void deleteUtilisateur(int id_utilisateur);
	public Utilisateur findUtilisateurById(int id_utilisateur);

}
