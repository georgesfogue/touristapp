package com.example.georges.tourapp;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Georges on 18/04/2017.
 */

public class activite implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private String Useremail;
    private String nomPays;
    private String nomVille;
    private String addresse;
    private String Horairedebut;
    private String Horairefin;
    private String activite;
    private String description;

    /*public activite(String useremail,String nomPays, String nomVille, String addresse, String dateopen, String horairedebut, String activite, String description, String horairefin) {
        Useremail = useremail;
        this.nomPays = nomPays;
        this.nomVille = nomVille;
        this.addresse = addresse;
        this.dateopen = dateopen;
        Horairedebut = horairedebut;
        Horairefin = horairefin;
        this.activite = activite;
        this.description = description;
    }*/

    public void setHorairedebut(String horairedebut) {
        Horairedebut = horairedebut;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUseremail(String useremail) {
        Useremail = useremail;
    }

    public void setHorairefin(String horairefin) {
        Horairefin = horairefin;
    }

    public void setNomPays(String nomPays) {
        this.nomPays = nomPays;
    }

    public void setNomVille(String nomVille) {
        this.nomVille = nomVille;
    }

    public void setAddresse(String addresse) {
        this.addresse = addresse;
    }

    public void setActivite(String activite) {
        this.activite = activite;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getHorairedebut() {
        return Horairedebut;
    }

    public String getUseremail() {
        return Useremail;
    }

    public String getHorairefin() {
        return Horairefin;
    }

    public String getNomPays() {
        return nomPays;
    }

    public String getNomVille() {
        return nomVille;
    }

    public String getAddresse() {
        return addresse;
    }

    public String getActivite() {
        return activite;
    }

    public String getDescription() {
        return description;
    }

}
