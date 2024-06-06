package Patisserie;

import Produit.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class GateauGlace extends Produit implements IProduitRefrigerable, IProduitPerissable, IProduitEmballable {

    private final boolean enveloppe;
    private final boolean emballageBiodegradable;
    private final boolean emballageRecyclable;
    private final LocalDate dateLimiteConsommation;
    private final double tempRefrigeration;
    private final boolean isDamage;

    public GateauGlace(String nom, int codeUnique, double poid, double prix, boolean enveloppe, boolean emballageBiodegradable, boolean emballageRecyclable, LocalDate dateLimiteConsommation, double tempRefrigeration, boolean isDamage) {
        super(nom, codeUnique, poid, prix);
        this.enveloppe = enveloppe;
        this.emballageBiodegradable = emballageBiodegradable;
        this.emballageRecyclable = emballageRecyclable;
        this.dateLimiteConsommation = dateLimiteConsommation;
        this.tempRefrigeration = tempRefrigeration;
        this.isDamage = isDamage;
    }

    //Getter
    public LocalDate getDateLimiteConsommation() {
        return dateLimiteConsommation;
    }

    @Override
    public boolean isPretAVendre() {
        return this.enveloppe;
    }

    @Override
    public boolean isEmballageBiodegradable() {
        return  this.emballageBiodegradable;
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
     * @Math.max(duree,0) retourne le nombre le plus grand entre duree et 0
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
        return this.dureeVie() < 0;
    }

    @Override
    public double getTempRefrigerationOptimal() {
        return this.tempRefrigeration;
    }


    /**
     * Cette methode retourne une boolean si le gateau glacé a été réfrigéré.
     * si oui true sinon false
     * @return : Boolean
     */
    @Override
    public boolean isFresh() {
        return getTempRefrigerationOptimal() > 0 && getTempRefrigerationOptimal() < 4;
    }

    //To String methods
    public String dureeVieString() {
        int duree = (int) ChronoUnit.DAYS.between(LocalDate.now(), this.dateLimiteConsommation);
        return isExpirer() ? "Il est expiré depuis " + Math.abs(duree) + " jours" : "Il n'est pas expiré";
    }

    public String typeEmballage() {
        return isEmballageBiodegradable() ? " biodégradable " + isEmballageBiodegradable() :
                isEmballageRecyclable() ? " recyclable " + isEmballageRecyclable() :
                        " Il n'y a pas d'emballage pour ce produit ";
    }

    public String isDamageString() {
        return this.isEmballageDamage()? "L'emballage est en bonne état !" :
                "L'emballage est endommagée !" ;
    }

    @Override
    public String toString() {
        return "| Informations du gateau | \n" +
                super.toString() + "\n" +
                "Pret à vendre : " + (isPretAVendre() ? " oui" : " non") + "\n" +
                "Emballage : " + typeEmballage() + "\n" +
                "Durée de vie de : " + dureeVie() + " jours " + dureeVieString() + "\n" +
                "Date limite consommation : " + getDateLimiteConsommation() + "\n" +
                "Temp de refrigeration (Celsius) : " + getTempRefrigerationOptimal() +"C"+ "\n" +
                "État de l'emballage : " + (isEmballageDamage() ? "Bonne " : "Mauvaise ")
                .concat(isDamageString()) + "\n" +
                "Produit frais ? : "+(isFresh() ? " le produit est frais\n" : " le produit n'est pas frais\n") +
                "Consommable ? : " + (isEncoreConsommable() ? "Il est encore consommable" : "Il n'est plus consommable");
    }
}
