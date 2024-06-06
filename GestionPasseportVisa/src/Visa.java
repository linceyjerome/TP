import java.time.LocalDate;

public class Visa {
    private String type;
    private LocalDate dateDelivrance;
    private LocalDate dateExpiration;
    private Boolean estValide;

    public Visa() {}

    /**
     * jutilise ce contructeur pour la variable boolean
     * @param type : String
     * @param dateDelivrance : LocaleDate
     * @param dateExpiration : LocaleDate
     * @param estValide : valeur boolean qui fait la validation dans les methodes (par référence)
     */
    public Visa(String type, LocalDate dateDelivrance, LocalDate dateExpiration, Boolean estValide) {
        this.type = type;
        this.dateDelivrance = dateDelivrance;
        this.dateExpiration = dateExpiration;
        this.estValide = true;
    }

    public Visa(String type, LocalDate dateDelivrance, LocalDate dateExpiration) {
        this.type = type;
        this.dateDelivrance = dateDelivrance;
        this.dateExpiration = dateExpiration;
    }

    public String getType() {
        return type;
    }

    public LocalDate getDateDelivrance() {
        return dateDelivrance;
    }

    public LocalDate getDateExpiration() {
        return dateExpiration;
    }

    /**
     * pronloge la date expiratioon
     *
     * @param nouvelleDateExpiration : la nouvelle date passé par référence dans le main
     */
    public void prolongerDateExpiration(LocalDate nouvelleDateExpiration) {

        if (nouvelleDateExpiration.isAfter(this.dateExpiration)) {

            this.dateExpiration = nouvelleDateExpiration;// si la date esty après la date d'expiration initial
            this.afficherInformations();

        } else System.out.println("Erreur ! date d'expiration est avant la date précédante ! ");

    }


    public void invalider() {
        this.estValide = false;
    }

    public void afficherInformations() {
        System.out.println("Type de visa: " + type);
        System.out.println("Date de délivrance: " + dateDelivrance);
        System.out.println("Date d'expiration: " + dateExpiration);
    }
}
