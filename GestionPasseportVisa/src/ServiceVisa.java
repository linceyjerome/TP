import java.time.LocalDate;

public class ServiceVisa {

    public ServiceVisa() {}

    /**
     * Permet de livrer un visa valid lors de la demande
     * @param type : String
     * @param dateDelivrance : LocaleDate
     * @param dateExpiration : Localdate
     * @param passeport : Passport
     * @return : retourne soit un message d'erreur ou un visa si la date expiration est correcte
     */
    public Visa delivrerVisa(String type, LocalDate dateDelivrance, LocalDate dateExpiration, Passeport passeport) {
        if (passeport == null) {
            System.out.println("Impossible de délivrer un visa sans passeport. Veuillesz appliquer pour un passport !");
            return null;
        }

        //regarde si la date d'expiration du visa est aprés celle du passport
        if (dateExpiration.isAfter(passeport.getDateExpiration())) {
            System.out.println("La date d'expiration du visa ne peut pas dépasser celle du passeport. La date d'expiration du visa sera celle du passeport correspondant.");
            dateExpiration = passeport.getDateExpiration();
        }

        return new Visa(type, dateDelivrance, dateExpiration,true);
    }

    /**
     * Change la daate expiration pour une nouvelle passé par référence
     * @param nouvelleDateExpiration : LocaleDate
     * @param personne : Personne , par référence
     */
    public void prolongerVisa(LocalDate nouvelleDateExpiration,Personne personne) {
        if (personne.getPasseport() == null || personne.getPasseport().getVisa() == null) {
            System.out.println("Vous n'avez pas de visa. Veuillez appliquer pour un visa");
            return;
        }
        // retourne un visa avec une nouvelle date
        personne.getPasseport().getVisa().prolongerDateExpiration(nouvelleDateExpiration);
    }
}
