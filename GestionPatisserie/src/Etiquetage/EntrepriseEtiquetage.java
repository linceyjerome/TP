package Etiquetage;

import Produit.Produit;
import java.util.List;

public class EntrepriseEtiquetage<T extends Produit> {
    private String nomEntreprise;
    private String siteWeb;
    private String coordonnees;

    public EntrepriseEtiquetage(String nomEntreprise, String siteWeb, String coordonnees) {
        this.nomEntreprise = nomEntreprise;
        this.siteWeb = siteWeb;
        this.coordonnees = coordonnees;
    }

    public String getNomEntreprise() {
        return nomEntreprise;
    }

    public String getSiteWeb() {
        return siteWeb;
    }

    public String getCoordonnees() {
        return coordonnees;
    }

    public void etiqueterProduits(List<? extends Produit> produits) {
        for (Produit produit : produits) {
            String etiquette = "| \u001B[4mProduit :\u001B[0m " + produit.getNom() + " |\n" +
                    "| \u001B[4mCode :\u001B[0m " +"#"+ produit.getCodeUnique() + " |\n" +
                    "| \u001B[4mPrix :\u001B[0m " + produit.getPrix() +"$" +" |\n" +
                    "| \u001B[4mPoids :\u001B[0m " + produit.getPoid() +" g" + " |\n" +
                    "| \u001B[4mEntreprise : \u001B[0m " + getNomEntreprise() + " |\n" +
                    "| \u001B[4mSite web :\u001B[0m " + getSiteWeb() + " |\n" +
                    "| \u001B[4mCoordonnées : \u001B[0m " + getCoordonnees().concat(" |");
            produit.setEtiquette(etiquette);
        }
    }
}
