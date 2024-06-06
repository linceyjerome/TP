import java.time.LocalDate;

public class Personne {
    private final String nom;
    private final String prenom;
    private Passeport passeport;


    public Personne(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

    public Personne(String nom, String prenom, Passeport passeport) {
        this.nom = nom;
        this.prenom = prenom;
        this.passeport = passeport;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Passeport getPasseport() {
        return passeport;
    }

    public void setPasseport(Passeport passeport) {
        this.passeport = passeport;
    }


    /**
     * @param servicePasseports  : appelle la fonction delivrerPasseport des service passeport
     */
    public void demanderPasseport(ServicePasseport servicePasseports) {
        // utilise le service de passeport pour délivrer le passeport
        this.passeport = servicePasseports.delivrerPasseport();
    }

    /**
     * @param serviceVisa : ServivceVida
     * @param type : sera créer avec une switch ou valeur assigné par default.
     * @param dateDelivrance : LocalDate.now()
     * @param dateExpiration retourne un visa si cest valide en utilisant le serviceVisa
     */
    public void demanderVisa(ServiceVisa serviceVisa, String type, LocalDate dateDelivrance, LocalDate dateExpiration) {
        if (this.passeport == null) {
            System.out.println(" Vous ne posséder pas de passport ! Veuillez appliquer pour un passport avant la demande de visa !");
            return;
        }

        //je créer un Visa après la invalide
        Visa visa = serviceVisa.delivrerVisa(type, dateDelivrance, dateExpiration, this.passeport);

        if (visa != null) {
            this.passeport.setVisa(visa);
        }
    }


    /**
     * @param servicePasseports retourne estValide a false si le methode est appelé
     */
    public void signalerVolOuPerte(ServicePasseport servicePasseports) {
        if (this.passeport == null) {
            System.out.println("Vous n'avez pas de passeport. veuillez le signaler si volé !");
            return;
        }
        servicePasseports.validerPasseport(this.passeport);

        if (!this.passeport.getEstValide()) {
            System.out.println("Raison : Votre passeport n'est plus valide nous devons enlever votre visa associé ");
            this.getPasseport().setVisa(null);
            this.setPasseport(null);
        }
    }

    /**
     * Affiche les informations de personne
     */
    public void afficherInformation() {
        System.out.println("Nom: " + nom);
        System.out.println("Prénom: " + prenom);

        if (this.passeport != null) {
            System.out.println("Détails du passeport:");
            this.passeport.afficherInformation();
        } else {
            System.out.println("Aucun passeport délivré.");
        }

        if (this.getPasseport() != null && this.getPasseport().getVisa() == null)
            System.out.println("Aucun Visa délivré");
    }
}
