package Produit;

public class Produit {
    protected String nom;
    protected int codeUnique;
    protected double poid;
    protected double prix;
    protected String etiquette;

    public Produit() {};

    public Produit(String nom, int codeUnique, double poid, double prix) {
        this.nom = nom;
        this.codeUnique = codeUnique;
        this.poid = poid;
        this.prix = prix;
    }

    public Produit(String nom, int codeUnique, double poid, double prix, String etiquette) {
        this.nom = nom;
        this.codeUnique = codeUnique;
        this.poid = poid;
        this.prix = prix;
        this.etiquette = etiquette;
    }

    public String getNom() {
        return nom;
    }

    public int getCodeUnique() {
        return codeUnique;
    }

    public double getPoid() {
        return poid;
    }

    public double getPrix() {
        return prix;
    }

    public String getEtiquette() {
        return etiquette;
    }

    public void setEtiquette(String etiquette) {
        this.etiquette = etiquette;
    }

    /**
     * La methode toString() retourne une affichage dépendament de letiquette (Question 3 )
     * @return : String une affichage selon le contexte
     */
    @Override
    public String toString() {
        String affichage = "| Information Général | \n" +
                "Nom :" + getNom() + "\n" +
                "Code unique :" + getCodeUnique() + "\n" +
                "Poid : " + getPoid() + "\n" +
                "Prix : " + getPrix() + "$";

        String affichageEtiqueter = "| Information Général | \n" +
                "== ÉtiquetteINC == \n"  + getEtiquette() ;

        if(getEtiquette() == null){
            return affichage;
        }else return affichageEtiqueter;

    }
}
