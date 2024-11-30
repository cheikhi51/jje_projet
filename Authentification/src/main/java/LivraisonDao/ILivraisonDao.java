package LivraisonDao;

import java.util.List;
import entities.Livraison;

public interface ILivraisonDao {
	    void saveLivraison(Livraison livraison); // Inscription
	    Livraison findLivraison(long idLivreur); // Authentification
	    List<Livraison> getAllLivraison(); // Optionnel : Liste des livraisons
	    List<Livraison> getLivreurLivraisons(Long id);
}



