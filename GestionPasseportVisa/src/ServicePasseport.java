import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ServicePasseport {

    /**
     * retourne un passport avec une date de délivrance d'aujourdui
     * @return : un passeport valide
     */
    public Passeport delivrerPasseport() {
        Random random = new Random(); // pour le numero de passport
        int dernierNumeroPasseport = random.nextInt(99999);

        return new Passeport(dernierNumeroPasseport, LocalDate.now(),
                "Service des passeports MTL", LocalDate.of(2026, 5, 5), true);
    }

    /**
     * permet de valider un visa associé au passport
     * @param passeport : Passport
     */
    public void validerPasseport(Passeport passeport) {
        passeport.invalider();

        if (passeport.getVisa() != null) {
            passeport.getVisa().invalider();
        }
    }

    /**
     * pronlonge la date expiration d'un passport d'une personne
     * @param nouvelleDateExpiration : LocaleDate
     * @param personne : Personne , par référence
     */
    public void prolongerPasseport(LocalDate nouvelleDateExpiration, Personne personne) {
        if (personne.getPasseport() == null) {
            System.out.println(" Vous ne posséder pas de passport ! \n Veuillez appliquer pour un passport.");
            return;
        }
        // retourne un passport avec une nouvelle date valide.
        personne.getPasseport().prolongerDateExpiration(nouvelleDateExpiration);
    }

    /**
     * il vérifie une liste de passport par rapport a sa date et validité.
     * @param passePorts : HashMap<Interger, Passport> Liste de passport avec numéro.
     */
    public void verifierValiditePasseport(HashMap<Integer, Passeport> passePorts) {
        for (Map.Entry<Integer, Passeport> entry : passePorts.entrySet()) {
            int numeroPasseport = entry.getKey();
            Passeport passeport = entry.getValue();
            if (!passeport.getEstValide() || passeport.getDateExpiration().isBefore(LocalDate.now())) {
                System.out.println("Le passeport avec le numéro " + numeroPasseport + " est invalide.");
                passeport.setEstValide(false);
            } else {
                System.out.println("Le passeport avec le numéro " + numeroPasseport + " est valide.");
            }
        }
    }
}
