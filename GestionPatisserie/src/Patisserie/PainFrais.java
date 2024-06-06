package Patisserie;
import Produit.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class PainFrais extends Produit implements IProduitPerissable, IProduitEmballable {
    private final boolean enveloppe;
    private final boolean emballageBiodegradable;
    private final boolean emballageRecyclable;
    private final LocalDate dateLimiteConsommation;
    private final boolean isDamage;

    public PainFrais(String nom, int codeUnique, double poid, double prix, boolean enveloppe, boolean emballageBiodegradable, boolean emballageRecyclable, LocalDate dateLimiteConsommation, double tempRefrigeration, boolean isDamage) {
        super(nom, codeUnique, poid, prix);
        this.enveloppe = enveloppe;
        this.emballageBiodegradable = emballageBiodegradable;
        this.emballageRecyclable = emballageRecyclable;
        this.dateLimiteConsommation = dateLimiteConsommation;
        this.isDamage = isDamage;
    }

    public LocalDate getDateLimiteConsommation() {
        return dateLimiteConsommation;
    }

    @Override
    public boolean isPretAVendre() {
        return this.enveloppe;
    }

    @Override
    public boolean isEmballageBiodegradable() {
        return this.emballageBiodegradable;
    }

    @Override
    public boolean isEmballageRecyclable() {
        return this.emballageRecyclable;
    }

    @Override
    public boolean isEmballageDamage() {
        return this.isDamage;
    }

    @Override
    public boolean isEncoreConsommable() {
        return !this.dateLimiteConsommation.isBefore(LocalDate.now());
    }

    /**
     * Calcule le nombre de jours restant avant qu'il soit expiré
     * @int duree prend le nombre de jour entre aujourdui et la date de consommation limite (expiration)
     * @MAth.max(duree,0) retourne le nombre le plus grand entre duree et 0
     * s'il est inférier a 0 , il retourne 0
     * @return le résultat en entier avec Math.max()
     */
    @Override
    public int dureeVie() {
        int duree = (int) ChronoUnit.DAYS.between(LocalDate.now(), this.dateLimiteConsommation);
        return Math.max(duree, 0);
    }

    @Override
    public boolean isExpirer() {
        return getDateLimiteConsommation().isBefore(LocalDate.now());
    }

    //To String methods

    /**
     * Retourne un message expiré et depuis combien de jours
     * @return message String
     */
    public String dureeVieString() {
        int duree = (int) ChronoUnit.DAYS.between(LocalDate.now(), this.dateLimiteConsommation);
        return isExpirer() ? "Il est expiré depuis " + Math.abs(duree) + " jours" : "Il n'est pas expiré";
    }

    /**
     * Retourne un message avec le résultat
     * permet de construire la method toString
     * @return : String
     */
    public String typeEmballage() {
        return isEmballageBiodegradable() ? "biodégradable "  :
                isEmballageRecyclable() ? "recyclable "  :
                        "Erreur type emballage";
    }

    /**
     * c'est pour la methode toString
     * @return : String
     */
    public String isDamageString() {
        return this.isEmballageDamage()? "L'emballage est en bonne état !" :
                "L'emballage est endommagée !";
    }

    /**
     * Réutilise les method implémentées pour afficher les informations du gateau
     * @return : String
     */
    @Override
    public String toString() {
        return "| Informations du Pain frais | \n" +
                super.toString() + "\n" +
                "Pret à vendre : " + (isPretAVendre() ? " oui" : " non") + "\n" +
                "Emballage : " + typeEmballage() + "\n" +
                "Durée de vie de : " + dureeVie() + " jours " + dureeVieString() + "\n" +
                "Date limite consommation : " + getDateLimiteConsommation() + "\n" +
                "État de l'emballage : " + (isEmballageDamage() ? "Bonne " : "Mauvaise ")
                .concat(isDamageString()) + "\n" +
                "Consommable ? : " + (isEncoreConsommable() ? "Il est encore consommable" : "Il n'est plus consommable");
    }
}
