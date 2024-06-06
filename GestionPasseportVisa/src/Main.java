import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Main {
    // throws InterruptedException c'est pour catch les erreurs de secondes de délai (Thread.sleep() )
    public static void main(String[] args) throws InterruptedException {
        Random rand = new Random();
        Scanner scanner = new Scanner(System.in);
        HashMap<Integer, Passeport> passePortList = new HashMap<>();

        Passeport passeportValide = new Passeport(rand.nextInt(99999),
                LocalDate.now(),
                "Montreal", LocalDate.of(2028, 1, 11),
                true);

        Passeport passeportInvalide = new Passeport(rand.nextInt(99999),
                LocalDate.now(),
                "Montreal", LocalDate.of(2024, 1, 1),
                true);

        Personne personne = new Personne("Marley", "Bob");
        Personne pValid = new Personne("Valide", "James", passeportValide); // passport valide
        Personne pNotValid = new Personne("Invalide", "Julie", passeportInvalide); // passport pas valide

        ServicePasseport servicePasseports = new ServicePasseport();
        ServiceVisa serviceConsulaire = new ServiceVisa();

        String separateur = "--------------------------------------------"; // pour la console
        String reponse, reponseVisa, choix;
        int year = 2020; // année passé pour pronlonger
        int month = 1; // mois passé pour pronlonger
        int day = 1; // jour passé pour pronlonger
        boolean continuer = true; // trigger pour le type de visa

        System.out.println("Bonjour ! bienvenue chez Service Gestion Passeport ! " +
                personne.getPrenom() +
                personne.getNom());

        System.out.println("Aviez vous un passport ? (oui/non)");
        reponse = scanner.nextLine();

        //va boucler jusqua temps la réponse est valide (String oui , non)
        while (!reponse.toLowerCase().trim().equals("oui") &&
                !reponse.toLowerCase().trim().equals("non") || reponse.isEmpty()) {

            System.out.println("entrez une valeur valide sil vous plait (oui OU non) !");
            reponse = scanner.nextLine();
        }

        // va automatiquement créer un passport, puisqu'il y en a pas au début
        if (reponse.toLowerCase().trim().equals("non") && personne.getPasseport() == null) {

            System.out.println("---------------------Simulation Demande passport---------------------");
            System.out.println("veuillez patienter nous faisons votre passport");
            Thread.sleep(3000); // delai de 3 secondes avant les autres opérateurs suivantes

            // Demande et obtention d'un passeport
            personne.demanderPasseport(servicePasseports);

            System.out.println(separateur);
            System.out.println("passeport fait ! \n");
            personne.afficherInformation();

        } else {
            System.out.println("voici ce qu'on nous avions dans notre dossier");
            personne.afficherInformation();

            if (personne.getPasseport() == null) {
                System.out.println("Vous aviez pas de passeport ! nous allons vous en faire un veuillez patienter...");
                Thread.sleep(3000);

                personne.demanderPasseport(servicePasseports);

                System.out.println(separateur);
                System.out.println("passeport fait ! \n");
                personne.afficherInformation();
            }
        }

        System.out.println("---------------------Simulation Demande Visa---------------------");

        System.out.println("Voulez vous un visa ? (oui/non)");
        reponseVisa = scanner.nextLine();

        //va boucler jusqua temps la réponse est valide
        while (!reponseVisa.toLowerCase().trim().equals("oui") &&
                !reponseVisa.toLowerCase().trim().equals("non") && !reponseVisa.isEmpty()) {

            System.out.println("entrez une valeur valide sil vous plait (oui OU non) !");
            reponseVisa = scanner.nextLine();
        }

        // création d'un visa , switch pour le type de passeport
        if (reponseVisa.toLowerCase().trim().equals("oui")) {

            while (reponseVisa.toLowerCase().trim().equals("oui") && continuer) {
                System.out.println("Bienvenue au Service de Visa, Pour le visa etes vous : 1 touriste , 2 etudiant , 3 travailleur Entrer le chiffre associé (1 , 2 ou 3)");
                choix = scanner.nextLine();
                switch (Integer.parseInt(choix)) {
                    case 1:
                        personne.demanderVisa(serviceConsulaire, "touriste", LocalDate.now(), LocalDate.of(2026, 1, 1));
                        continuer = false; // trigger qui force a sortir de la boucle
                        break;
                    case 2:
                        personne.demanderVisa(serviceConsulaire, "etudiant", LocalDate.now(), LocalDate.of(2026, 1, 1));
                        continuer = false;
                        break;

                    case 3:
                        personne.demanderVisa(serviceConsulaire, "travailleur", LocalDate.now(), LocalDate.of(2026, 1, 1));
                        continuer = false;
                        break;

                    default:
                        //va répéter la question jusqua temps une réponse valide est entrée
                        System.out.println("Entrez une valeur valide sil vous plait");
                        break;
                }
            }

            personne.afficherInformation();

        } else {
            // sinon , va créer un visa automatiquement avec le type touriste
            System.out.println("je vous fait quand meme un visa pour du tourisme ! bonne vacance");
            Thread.sleep(3000);
            personne.demanderVisa(serviceConsulaire, "touriste", LocalDate.now(), LocalDate.of(2026, 1, 1));
            personne.afficherInformation();
        }

        System.out.println(separateur);
        System.out.println(separateur);
        System.out.println("---------------------Simulation d’invalidité de passeport (vol ou perte)---------------------");

        // Simuler une situation d’invalidité de passeport (vol ou perte)
        System.out.println(personne.getPrenom() + " perd sont passeport");
        Thread.sleep(3000); // 3 secondes

        System.out.println("Bonjour j'ai perdu mon passport , le passport et visa sera plus valide .");
        personne.signalerVolOuPerte(servicePasseports); // appelle a la method pour invalidé
        System.out.println("Mise a jour....");
        Thread.sleep(4000); // 4 secondes
        personne.afficherInformation();


        System.out.println(separateur);
        System.out.println(separateur);
        Thread.sleep(3000);


        System.out.println("---------------------Simulation prolongation de la date d'expiration du Visa---------------------");

        //  prolongation de la date d'expiration du visa
        System.out.println(personne.getPrenom() + " demande de prolonger sa date de visa... puisque la date d'échéance est proche sa vous coutera 150$");
        System.out.println("il veut changer pour cette date " + year + "-" + month + "-" + day);
        personne.demanderPasseport(servicePasseports);
        personne.demanderVisa(serviceConsulaire, "touriste", LocalDate.now(), LocalDate.of(2024, 5, 26));
        serviceConsulaire.prolongerVisa(LocalDate.of(year, month, day), personne);

        System.out.println(separateur);
        System.out.println(separateur);

        // la date n'est pas bonne , donc change pour une date valide (celle du passport).
        System.out.println("une nouvelle date est entrée automatiquement pour une valide... prend celle du passeport");
        serviceConsulaire.prolongerVisa(LocalDate.of(personne.getPasseport().getDateExpiration().getYear(),
                personne.getPasseport().getDateExpiration().getMonth(),
                personne.getPasseport().getDateExpiration().getDayOfMonth()), personne); // prend annee-mois-jour du passport personne Bob

        System.out.println(separateur);
        System.out.println(separateur);
        Thread.sleep(3000);

        System.out.println("---------------------Simulation pronlonger passeport---------------------");
        // prolongation de la date d'expiration du passeport
        servicePasseports.prolongerPasseport(LocalDate.of(2026, 9, 9), personne);
        personne.afficherInformation();
        System.out.println(separateur);
        System.out.println(separateur);

        System.out.println("---------------------Simulation vérifier les passeports---------------------");

        passePortList.put(personne.getPasseport().getNoPassport(), personne.getPasseport());
        passePortList.put(pValid.getPasseport().getNoPassport(), pValid.getPasseport());
        passePortList.put(pNotValid.getPasseport().getNoPassport(), pNotValid.getPasseport());

        System.out.println("Liste de passeport a valider :");

        for (Map.Entry<Integer, Passeport> entry : passePortList.entrySet()) {
            int numeroPasseport = entry.getKey();
            Passeport passeport = entry.getValue();
            System.out.println("Numéro de passeport: " + numeroPasseport +
                    ", Date expiration : " + passeport.getDateExpiration());
        }
        servicePasseports.verifierValiditePasseport(passePortList);
    }
}