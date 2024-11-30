package CommandeDao;

import java.util.List;
import entities.Commande;

public interface ICommandeDao {
	    void saveCommande(Commande commande); // Inscription
	    Commande findCommande(long idClient); // Authentification
	    List<Commande> getAllCommande(); // Optionnel : Liste des commandes
	    List<Commande> getCommercantCommandes(Long id);
}
