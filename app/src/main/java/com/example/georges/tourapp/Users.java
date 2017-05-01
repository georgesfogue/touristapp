package com.example.georges.tourapp;

/**
 * Created by Georges on 01/05/2017.
 */

import java.io.Serializable;

public class Users implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String nom;
    private String email;
    private String motDePasse;

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
