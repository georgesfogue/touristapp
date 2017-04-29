package com.example.georges.tourapp;

/**
 * Created by Georges on 28/04/2017.
 */

public class activite {

    private String nomVille;
    private String nomPays;
    private int nbreHabitants;
    private String activite;
    private String description;
    private String jour;


    //Constructeur avec paramètres
    public activite(String pays,String ville, int Nbrhabit,  String Activite, String Description, String Jour)
    {
        nomVille = ville;
        nomPays = pays;
        nbreHabitants = Nbrhabit;
        activite = Activite;
        description = Description;
        jour = Jour;
    }

    //*************   ACCESSEURS *************

    //Retourne le nom de la ville
    public String getNomVille(){
        return nomVille;
    }
    //Retourne la description
    public String getdescrip(){
        return description;
    }

    //Retourne le nom du pays
    public String getNomPays(){
        return nomPays;
    }

    // Retourne le nombre d'habitants
    public int getNombreHabitants(){
        return nbreHabitants;
    }
    // Retourne l'activité
    public String getActivite(){
        return activite;
    }

    //Retourne le jour de l'activite
    public String getjour(){
        return jour;
    }

    //*************   MUTATEURS   *************

    //Définit le nom de la ville
    public void setNomVille(String pNom){
        nomVille = pNom;
    }

    //Définit le nom de la ville
    public void setdescip(String pdescrip){
        description = pdescrip;
    }

    //Définit le nom du pays
    public void setNomPays(String pPays){
        nomPays = pPays;
    }
    //Définit le nom du pays
    public void setActivite(String pactivite){
        activite = pactivite;
    }

    //Définit le nombre d'habitants
    public void setNombreHabitants(int nbre){
        nbreHabitants = nbre;
    }

    //Définit le nombre d'habitants
    public void setjour(String jourac){
        jour = jourac;
    }

}
