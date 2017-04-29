package com.example.georges.tourapp;

import java.io.Serializable;

/**
 * Created by Georges on 08/04/2017.
 */

public class user implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private int      id;
    private String    nom;
    private String    prenom;
    private String    email;
    private String    motDePasse;

    public int getId() {
        return id;
    }
    public void setId(int id) {
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
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }
    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

}
