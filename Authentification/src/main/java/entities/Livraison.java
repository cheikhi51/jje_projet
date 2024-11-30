package entities;

import java.util.Date;

public class Livraison {
    private long idLivraison;
    private long idCommande;
    private long idCommercant;
    private Long idLivreur; // Nullable
    private String adresseCommercant;
    private String adresseLivraison;
    private Date dateLivraison; // Date au lieu de LocalDateTime
    private String statutLivraison; // Possible values: 'en_attente', 'en_cours', 'livree'

    // Constructeurs
    public Livraison() {
    	super();
    }

    public Livraison(long idCommande,long idCommercant, Long idLivreur,String adresseCommercant, String adresseLivraison, Date dateLivraison, String statutLivraison) {
        this.idCommande = idCommande;
        this.idCommercant = idCommercant;
        this.idLivreur = idLivreur;
        this.adresseCommercant = adresseCommercant;
        this.adresseLivraison = adresseLivraison;
        this.dateLivraison = dateLivraison;
        this.statutLivraison = statutLivraison;
    }

    public Livraison(long idLivraison, long idCommande,long idCommercant, Long idLivreur,String adresseCommercant, String adresseLivraison, Date dateLivraison, String statutLivraison) {
        this.idLivraison = idLivraison;
        this.idCommande = idCommande;
        this.idCommercant = idCommercant;
        this.idLivreur = idLivreur;
        this.adresseCommercant = adresseCommercant;
        this.adresseLivraison = adresseLivraison;
        this.dateLivraison = dateLivraison;
        this.statutLivraison = statutLivraison;
    }

    // Getters et Setters
    public long getIdLivraison() {
        return idLivraison;
    }

    public void setIdLivraison(long idLivraison) {
        this.idLivraison = idLivraison;
    }

    public long getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(long idCommande) {
        this.idCommande = idCommande;
    }

    public Long getIdLivreur() {
        return idLivreur;
    }

    public void setIdLivreur(Long idLivreur) {
        this.idLivreur = idLivreur;
    }

    public String getAdresseLivraison() {
        return adresseLivraison;
    }

    public void setAdresseLivraison(String adresseLivraison) {
        this.adresseLivraison = adresseLivraison;
    }

    public Date getDateLivraison() {
        return dateLivraison;
    }

    public void setDateLivraison(Date dateLivraison) {
        this.dateLivraison = dateLivraison;
    }

    public String getStatutLivraison() {
        return statutLivraison;
    }

    public void setStatutLivraison(String statutLivraison) {
        this.statutLivraison = statutLivraison;
    }

    // Méthode toString
    @Override
    public String toString() {
        return "Livraison{" +
                "idLivraison=" + idLivraison +
                ", idCommande=" + idCommande +
                ", idCommercant=" + idCommercant +
                ", idLivreur=" + idLivreur +
                ", adresseCommercant='" + adresseCommercant + '\'' +
                ", adresseLivraison='" + adresseLivraison + '\'' +
                ", dateLivraison=" + dateLivraison +
                ", statutLivraison='" + statutLivraison + '\'' +
                '}';
    }

	public long getIdCommercant() {
		return idCommercant;
	}

	public void setIdCommercant(long idCommercant) {
		this.idCommercant = idCommercant;
	}

	public String getAdresseCommercant() {
		return adresseCommercant;
	}

	public void setAdresseCommercant(String adresseCommercant) {
		this.adresseCommercant = adresseCommercant;
	}
}
