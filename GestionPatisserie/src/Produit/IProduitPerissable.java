package Produit;

public interface IProduitPerissable {
    boolean isEncoreConsommable();

    boolean isExpirer();

    int dureeVie();

}
