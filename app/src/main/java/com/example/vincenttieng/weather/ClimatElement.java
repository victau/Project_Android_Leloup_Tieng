package com.example.vincenttieng.weather;


/**
 * Created by victorien on 13/05/2018.
 */

class ClimatElement {

    String ville;
    String country;
    String location;
    String minTemp;
    String maxTemp;
    int    timeStamp;
    String nomDuMois;
    String nomDuJour;
    int mois;
    int jour;
    int annee;

    public ClimatElement(String ville, String country, String location, String minTemp, String maxTemp, int timeStamp, String nomDuMois, String nomDuJour, int jour, int mois , int annee){
        this.ville = ville;
        this.country = country;
        this.location = location;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.timeStamp = timeStamp;
        this.nomDuMois = nomDuMois;
        this.nomDuJour = nomDuJour;
        this.jour = jour;
        this.mois = mois;
        this.annee = annee;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(String minTemp) {
        this.minTemp = minTemp;
    }

    public String getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(String maxTemp) {
        this.maxTemp = maxTemp;
    }

    public int getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(int timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getNomDuMois() {
        return nomDuMois;
    }

    public void setNomDuMois(String nomDuMois) {
        this.nomDuMois = nomDuMois;
    }

    public String getNomDuJour() {
        return nomDuJour;
    }

    public void setNomDuJour(String nomDuJour) {
        this.nomDuJour = nomDuJour;
    }

   public int getMois() {
        return mois;
    }

    public void setMois(int mois) {
        this.mois = mois;
    }

    public int getJour() {
        return jour;
    }

    public void setJour(int jour) {
        this.jour = jour;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }
}
