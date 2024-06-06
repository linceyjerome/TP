package Produit;

import java.time.LocalDate;

public interface IProduitConservable {
    int dureeConservation();
    LocalDate getDateMiseEnConserve();
    LocalDate getDateFinValidite();
}
