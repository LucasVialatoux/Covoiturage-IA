/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TP2;

import java.util.Scanner;

/**
 *
 * @author antoine dulhoste
 */
public class CreneauHoraire {
    private int jour;
    private int heure;
    private int minute;
    private int durée;
    
    public CreneauHoraire (int j,int h,int m,int d ){
        this.jour=j;
        this.heure=h;
        this.minute=m;
        this.durée=d;
    }
    
    public CreneauHoraire (CreneauHoraire ch ){
        this.jour=ch.jour;
        this.heure=ch.heure;
        this.minute=ch.minute;
        this.durée=ch.durée;
    }

    @Override
    public boolean equals(Object o){
        CreneauHoraire ch=(CreneauHoraire)o;
        return this.jour==ch.getJour() && this.heure==ch.getHeure() && this.minute==ch.getMinute() && this.durée==ch.getDurée();
    }
    public void saisi(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez saisir le jour :");
        String str = sc.nextLine();
        this.jour= Integer.parseInt(str);
        System.out.println("Veuillez saisir une heure :");
        str = sc.nextLine();
        this.heure= Integer.parseInt(str);
        System.out.println("Veuillez saisir les minutes :");
        str = sc.nextLine();
        this.minute= Integer.parseInt(str);
        System.out.println("Veuillez saisir une durée :");
        str = sc.nextLine();
        this.durée= Integer.parseInt(str);
        System.out.println(this);
    }
    /**
     * @return the jour
     */
    public int getJour() {
        return jour;
    }

    /**
     * @param jour the jour to set
     */
    public void setJour(int jour) {
        this.jour = jour;
    }

    /**
     * @return the heure
     */
    public int getHeure() {
        return heure;
    }

    /**
     * @param heure the heure to set
     */
    public void setHeure(int heure) {
        this.heure = heure;
    }

    /**
     * @return the minute
     */
    public int getMinute() {
        return minute;
    }

    /**
     * @param minute the minute to set
     */
    public void setMinute(int minute) {
        this.minute = minute;
    }

    /**
     * @return the durée
     */
    public int getDurée() {
        return durée;
    }

    /**
     * @param durée the durée to set
     */
    public void setDurée(int durée) {
        this.durée = durée;
    }
    
    @Override
    public String toString(){
        return "jour:"+this.jour+" "+this.heure+":"+this.minute+" durée:"+this.durée;
    }
}
