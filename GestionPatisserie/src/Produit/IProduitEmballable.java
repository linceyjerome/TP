package Produit;

public interface IProduitEmballable {
    boolean isPretAVendre(); //sil est envelopé

    boolean isEmballageBiodegradable(); // sil est bio

    boolean isEmballageRecyclable(); // sil est recyclable

    boolean isEmballageDamage(); // sil est endomagé

}
