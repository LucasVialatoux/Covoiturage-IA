/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package covoiturage;

/**
 *
 * @author user
 */
public class Trajet {
    public String villeDepart;
    public String villeArrivee;
    public String dateDepart;
    public int nombrePlaces;
    public int prixInitial;
    public boolean accepteFumeur;
    public boolean accepteAnimaux;
    
    public Trajet(String villeDepart,String villeArrivee,String dateDepart,int nombrePlaces,int prixInitial,boolean accepteFumeur,boolean accepteAnimaux){
        this.accepteAnimaux=accepteAnimaux;
        this.accepteFumeur=accepteFumeur;
        this.dateDepart=dateDepart;
        this.nombrePlaces=nombrePlaces;
        this.prixInitial=prixInitial;
        this.villeArrivee=villeArrivee;
        this.villeDepart=villeDepart;
    }
    
    public void ajouterTrajet(){
        
    }
}
