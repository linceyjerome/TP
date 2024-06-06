import Etiquetage.EntrepriseEtiquetage;
import Patisserie.*;
import Produit.Produit;
import Produit.IProduitConservable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static final Random RANDOM = new Random();
    public static final String SEPARATEUR = "=====================================================================";

    public static void main(String[] args) {

        Gateau gateauFraise = new Gateau(
                "Gateau au fraise",
                 RANDOM.nextInt(99999),
                17.5,
                10.99,
                true,
                true,
                false,
                LocalDate.of(2024, 3, 2),
                -4, true);

        PainFrais painBaguette = new PainFrais(
                "Pain baguette",
                RANDOM.nextInt(99999),
                9.0,
                5.00,
                false,
                false,
                true,
                LocalDate.of(2024, 3, 3),
                20,
                false);

        GateauGlace gateauCremeGlace = new GateauGlace(
                "Gâteau à la crème glacé",
                RANDOM.nextInt(99999),
                10.5,
                13.99,
                true,
                false,
                false,
                LocalDate.of(2024, 10, 1),
                3,
                false);

        ConfitDeCanard confitDeCanard = new ConfitDeCanard("Confit de canard",
                RANDOM.nextInt(99999),
                1.5, 5.59,
                4,
                LocalDate.of(2024, 5, 1));

        ConfitureGeleeSaison confitureGelee = new ConfitureGeleeSaison(
                "Confiture de gelée de saison",
                RANDOM.nextInt(99999),
                2.5,
                4.95,
                30,
                LocalDate.of(2024, 7, 1));

        /**
         * Question 1
         */
        System.out.println("######################## Question 1 ########################");
        ArrayList<Produit> produits = new ArrayList<>();
        produits.add(gateauFraise);
        produits.add(painBaguette);
        produits.add(gateauCremeGlace);

        for (Produit produit : produits) {
            System.out.println(produit);
            System.out.println(SEPARATEUR);
        }

        /**
         * Question 2
         * date + 1 an ou 2 ans getYear()
         */
        System.out.println("######################## Question 2 ########################");
        ArrayList<IProduitConservable> produitsConservables = new ArrayList<>();
        produitsConservables.add(confitDeCanard);
        produitsConservables.add(confitureGelee);

        for (IProduitConservable produitConservable : produitsConservables) {
            System.out.println(produitConservable);
            System.out.println(SEPARATEUR);
        }
        System.out.println();

        //Question 3
        System.out.println("######################## Question 3 ########################");
        ArrayList<Produit> produitList = new ArrayList<>();

        produitList.add(gateauFraise);
        produitList.add(painBaguette);
        produitList.add(gateauCremeGlace);
        produitList.add(confitDeCanard);
        produitList.add(confitureGelee);

        EntrepriseEtiquetage<Produit> entrepriseEtiquetage = new EntrepriseEtiquetage<Produit>(
                "Patisserie du Coin",
                "www.patisserieDuCoin.com",
                "123 Rue des Coin, Canada , Montreal");

        entrepriseEtiquetage.etiqueterProduits(produitList);

        // Affichage des produits avec leurs étiquettes
        for (Produit produit : produitList) {
            System.out.println(produit);
            System.out.println(SEPARATEUR);
        }
    }
}
