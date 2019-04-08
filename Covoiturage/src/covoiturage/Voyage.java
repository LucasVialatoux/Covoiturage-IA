package covoiturage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;
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

    public Voyage(Utilisateur conducteur, int prix, int nbplaces, String villeDepart, String villeArrivee) {
        this.conducteur=conducteur;
        this.prix = prix;
        this.nbplaces = nbplaces;
        this.villeDepart = villeDepart;
        this.villeArrivee = villeArrivee;
        this.nbpassagers = 0;
        this.id = recupId();
        this.date = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
    }

    private Voyage() { 
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
        
        String ajout=this.prix+"#";
        ajout+=this.nbplaces +"#";
        ajout+=this.villeDepart+"#";
        ajout+=this.villeArrivee+"#";
        ajout+=this.nbpassagers+"#";
        ajout+=this.date+"#";
        
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
        if(this.estPlein()){
            this.nbpassagers++;
            sauvegardeVoyage();        
        }
    }
    
    private int recupId() {
        int newid=0;
        int temp;
        File folder = new File("voyages/.");
        for (final File fileEntry : folder.listFiles()) {
            String name=fileEntry.getName();
            temp = Integer.parseInt(name.substring(0, 1));
            if(newid<temp)
                newid=temp;
        }
        newid++;
        return newid;
    }

    static Voyage trouverVoyage(Utilisateur util, String part) {
        String path = "voyages/"+util.id+"-"+part+".txt";
        File f = new File(path);
        Voyage voyage = new Voyage();
        String[] elementsvoyage;
        if(f.exists() && !f.isDirectory()) { 
            try {
                    Scanner scanner = new Scanner(new FileReader(f));
                    String scan = scanner.nextLine();
                    elementsvoyage = scan.split("#");                
                    voyage.setConducteur(util);
                    voyage.setPrix(Integer.parseInt(elementsvoyage[0]));
                    voyage.setNbplaces(Integer.parseInt(elementsvoyage[1]));
                    voyage.setVilleDepart(elementsvoyage[2]);
                    voyage.setVilleArrivee(elementsvoyage[3]);
                    voyage.setNbpassagers(Integer.parseInt(elementsvoyage[4]));
                    voyage.setDate(elementsvoyage[5]);
                } 
            catch (FileNotFoundException ex) {
                Logger.getLogger(Voyage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return voyage;
    }
    
    
    static Voyage[] listeVoyagesCorrepondant(String depart, String arrivee, LocalDate date) {
        Voyage[] voyages;
        File folder = new File("voyages/.");
        for (final File fileEntry : folder.listFiles()) {
            String[] elementsvoyage;
            Scanner scanner;
            try {
                scanner = new Scanner(new FileReader(fileEntry));
                String scan = scanner.nextLine();
                elementsvoyage = scan.split("#");
                
                if(depart.equals(elementsvoyage[1]) && arrivee.equals(elementsvoyage[2]) && prix == Integer.parseInt(elementsvoyage[0])){
                }
               
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Voyage.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
        return voyages;
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
