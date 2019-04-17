package covoiturage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Voyage {

        private int id;
        private int nbplaces;
        private int nbpassagers;
        private int prix;
        private String date;
        private String villeDepart;
        private String villeArrivee;
        private Utilisateur conducteur;

    public Voyage(Utilisateur conducteur, int prix, int nbplaces, String villeDepart, String villeArrivee,String date) {
        this.conducteur=conducteur;
        this.prix = prix;
        this.nbplaces = nbplaces;
        this.villeDepart = villeDepart;
        this.villeArrivee = villeArrivee;
        this.nbpassagers = 0;
        this.id = recupId();
        this.date = date;
    }

    public Voyage(Utilisateur conducteur, int id, int prix, int nbplaces, String villeDepart, String villeArrivee, String date,  int nbpassagers) {
        this.conducteur=conducteur;
        this.prix = prix;
        this.nbplaces = nbplaces;
        this.villeDepart = villeDepart;
        this.villeArrivee = villeArrivee;
        this.nbpassagers = nbpassagers;
        this.id = id;
        this.date = date;
    }
    
    public Voyage() { 
        this.nbpassagers = 0;
    }

    
    public void sauvegardeVoyage() {
        
        String path = "voyages/"+this.conducteur.id+"-"+this.id+".txt";
        File file = new File(path);
            try {
                Files.deleteIfExists(file.toPath());
            } catch (IOException ex) {
                Logger.getLogger(Voyage.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        String ajout=this.date+"#";
        ajout+=this.villeDepart+"#";
        ajout+=this.villeArrivee+"#";
        ajout+=this.nbplaces +"#";
        ajout+=this.nbpassagers+"#";
        ajout+=this.prix+"#";
        
        FileWriter fw;
        try {
            fw = new FileWriter(path,true);
            fw.write(ajout);
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(Voyage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean estPlein(){
        return this.nbplaces == this.nbpassagers;
    }
    
    public void ajouterPassager(){
        if(!this.estPlein()){
            this.nbpassagers++;
            sauvegardeVoyage();        
        }
    }
    
    private int recupId() {
        int newid=0;
        int temp;
        File folder = new File("voyages/.");
        for (final File fileEntry : folder.listFiles()) {
            String[] name=fileEntry.getName().split("-");
            temp = Integer.parseInt(name[1].replace(".txt",""));
            if(newid<temp)
                newid=temp;
        }
        newid++;
        return newid;
    }   
    
    public int getId() {
        return id;
    }

    public int getNbplaces() {
        return nbplaces;
    }

    public int getNbpassagers() {
        return nbpassagers;
    }

    public String getDate() {
        return date;
    }

    public String getVilleDepart() {
        return villeDepart;
    }

    public String getVilleArrivee() {
        return villeArrivee;
    }

    public int getPrix() {
        return prix;
    }
    
    public Utilisateur getConducteur() {
        return conducteur;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNbplaces(int nbplaces) {
        this.nbplaces = nbplaces;
    }

    public void setNbpassagers(int nbpassagers) {
        this.nbpassagers = nbpassagers;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setVilleDepart(String villeDepart) {
        this.villeDepart = villeDepart;
    }

    public void setVilleArrivee(String villeArrivee) {
        this.villeArrivee = villeArrivee;
    }

    public void setConducteur(Utilisateur conducteur) {
        this.conducteur = conducteur;
    }
    
    
    
}
