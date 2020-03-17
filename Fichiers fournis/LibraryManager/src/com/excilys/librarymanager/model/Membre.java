package com.excilys.librarymanager.model;

public class Membre {

    public enum Abonnement {
        BASIC(0), PREMIUM(1), VIP(2);

        final private Integer value;

        Abonnement(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }
    }

    private Integer id;
    private String nom;
    private String prenom;
    private String adress;
    private String email;
    private String telephone;
    private Abonnement abonnement;

    public Membre() {
        super();
    }

    public Membre(Integer id, String nom, String prenom, String adress, String email, String telephone,
            Abonnement abonnement) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adress = adress;
        this.email = email;
        this.telephone = telephone;
        this.abonnement = abonnement;
    }

    public Membre(Integer id, String nom, String prenom, String adress, String email, String telephone,
            String abonnement) {
        this(id, nom, prenom, adress, email, telephone,Abonnement.valueOf(abonnement));

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Abonnement getAbonnement() {
        return abonnement;
    }

    public void setAbonnement(Abonnement abonnement) {
        this.abonnement = abonnement;
    }

    public void setAbonnement(String abonnement) {
        this.abonnement = Abonnement.valueOf(abonnement);
    }
    

    @Override
    public String toString() {
        return "Membre [abonnement=" + abonnement + ", adress=" + adress + ", email=" + email + ", id=" + id + ", nom="
                + nom + ", prenom=" + prenom + ", telephone=" + telephone + "]";
    }

}