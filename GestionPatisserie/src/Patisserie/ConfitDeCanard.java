package Patisserie;

import Produit.Produit;
import Produit.IProduitConservable;

import java.time.LocalDate;

public class ConfitDeCanard extends Produit implements IProduitConservable {
    private final int dureeConservation;
    private final LocalDate dateMiseEnConserve;

    public ConfitDeCanard(String nom, int codeUnique, double poid, double prix, int dureeConservation, LocalDate dateMiseEnConserve) {
        super(nom, codeUnique, poid, prix);
        this.dureeConservation = dureeConservation;
        this.dateMiseEnConserve = dateMiseEnConserve;
    }

    public int getDureeConservation() {
        return this.dureeConservation;
    }

    @Override
    public int dureeConservation() {
        return getDureeConservation();
    }

    @Override
    public LocalDate getDateMiseEnConserve() {
        return this.dateMiseEnConserve;
    }

    @Override
    public LocalDate getDateFinValidite() {
        return this.dateMiseEnConserve.plusDays(getDureeConservation());
    }

    @Override
    public String toString() {
        return  super.toString() + "\n" +
                "Date de mise en conserve : " + this.getDateMiseEnConserve() + "\n" +
                "Date de fin de validité : " + this.getDateFinValidite() + "\n" +
                "Durée de conservation (en jours) : " + this.dureeConservation() + " jours";
    }
}
