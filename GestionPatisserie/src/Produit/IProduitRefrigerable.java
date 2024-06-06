package Produit;

public interface IProduitRefrigerable {
    double getTempRefrigerationOptimal(); // récupérer la temperature en celsius

    boolean isFresh(); // valide sil est frais
}
