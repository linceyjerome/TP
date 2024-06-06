import java.time.LocalDate;

public class Passeport {
    private int noPassport;
    private LocalDate dateDelivrance;
    private LocalDate dateExpiration;
    private String lieudelivrance;
    private Boolean estValide;
    private Visa visa;

    //default
    public Passeport() {
    }

    public Passeport(int noPassport, LocalDate dateDelivrance, String lieuDelivrance, LocalDate dateExpiration, Boolean estValide) {
        this.noPassport = noPassport;
        this.dateDelivrance = dateDelivrance;
        this.lieudelivrance = lieuDelivrance;
        this.dateExpiration = dateExpiration;
        this.estValide = true;
    }

    public Visa getVisa() {
        return visa;
    }

    public void setVisa(Visa visa) {
        this.visa = visa;
    }

    public LocalDate getDateExpiration() {
        return dateExpiration;
    }

    public Boolean getEstValide() {
        return estValide;
    }

    public int getNoPassport() {
        return noPassport;
    }

    public void setEstValide(Boolean estValide) {
        this.estValide = estValide;
    }

    /**
     * @param nouvelleDate il fait l'opération de pronloger la date expiration
     */
    public void prolongerDateExpiration(LocalDate nouvelleDate) {
        if (nouvelleDate.isAfter(this.dateExpiration)) {
            this.dateExpiration = nouvelleDate; //prend la nouvelle date et l'assigne
        } else System.out.println("Erreur date d'expiration est déjà passé");
    }

    /**
     * Met l'attribut estValide a false pour de la validation.
     */
    public void invalider() {
        this.estValide = false;
    }


    /**
     * Fait laffichage et ajuste si un visa est présent ou pas
     */
    public void afficherInformation() {
        System.out.println("Numéro de passeport: " + noPassport);
        System.out.println("Date de délivrance: " + dateDelivrance);
        System.out.println("Lieu delivrance: " + lieudelivrance);
        System.out.println("Date d'expiration: " + dateExpiration);
        if (this.estValide) {
            System.out.println("Est valide");
        } else System.out.println("Est invalide");
        if (visa != null) {
            System.out.println("Détails du visa:");
            visa.afficherInformations();
        } else {
            System.out.println("Aucun visa associé. Vous pouvez appliquer pour l'obtenir");
        }
    }
}
