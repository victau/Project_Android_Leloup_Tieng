package com.example.vincenttieng.weather;

public class Formattage{

    public static String getMois(int mois){
        String nomDuMois = null;
        switch (mois){
            case 0:
                nomDuMois = "Janvier";
                break;
            case 1:
                nomDuMois = "Février";
                break;
            case 2:
                nomDuMois = "Mars";
                break;
            case 3:
                nomDuMois = "Avril";
                break;
            case 4:
                nomDuMois = "Mai";
                break;
            case 5:
                nomDuMois = "Juin";
                break;
            case 6:
                nomDuMois = "Juillet";
                break;
            case 7:
                nomDuMois = "Août";
                break;
            case 8:
                nomDuMois = "Septembre";
                break;
            case 9:
                nomDuMois = "Octobre";
                break;
            case 10:
                nomDuMois = "Novembre";
                break;
            case 11:
                nomDuMois = "Décembre";
                break;

        }
        return nomDuMois;
    }

    public static String getJour(int jour){
        String nomDuJour = null;
        switch (jour){
            case 1:
            nomDuJour = "Dimanche";
            break;
            case 2:
                nomDuJour = "Lundi";
                break;
            case 3:
                nomDuJour = "Mardi";
                break;
            case 4:
                nomDuJour = "Mercredi";
                break;
            case 5:
                nomDuJour = "Jeudi";
                break;
            case 6:
                nomDuJour = "Vendredi";
                break;
            case 7:
                nomDuJour = "Samedi";
                break;
        }
        return nomDuJour;
    }
}
