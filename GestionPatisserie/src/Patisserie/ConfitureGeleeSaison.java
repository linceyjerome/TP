package Patisserie;

import Produit.*;

import java.time.LocalDate;

public class ConfitureGeleeSaison extends Produit implements IProduitConservable {
    private final int dureeConservation;
    private final LocalDate dateMiseEnConserve;

    public ConfitureGeleeSaison(String nom, int codeUnique, double poid, double prix, int dureeConservation, LocalDate dateMiseEnConserve) {
        super(nom, codeUnique, poid, prix);
        this.dureeConservation = dureeConservation;
        this.dateMiseEnConserve = dateMiseEnConserve;
    }

    @Override
    public int dureeConservation() {
        return dureeConservation;
    }

    @Override
    public LocalDate getDateMiseEnConserve() {
        return dateMiseEnConserve;
    }

    @Override
    public LocalDate getDateFinValidite() {
        return dateMiseEnConserve.plusDays(dureeConservation);
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +
                "Date de mise en conserve: " + this.getDateMiseEnConserve() + "\n" +
                "Date de fin de validité: " + this.getDateFinValidite() + "\n" +
                "Durée de conservation (en jours): " + this.dureeConservation() + " jours";
    }
}
