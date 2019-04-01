package covoiturage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Voyage {
        
        private int id;
        private int nbplaces;
        private int nbpassagers;
        private String date;
        private String villeDepart;
        private String villeArrivee;

    public Voyage(int nbplaces, String VilleDepart, String VilleArrivee) {
        this.nbplaces = nbplaces;
        this.villeDepart = VilleDepart;
        this.villeArrivee = VilleArrivee;
        this.nbpassagers = 0;
        this.id = recupId();
        this.date = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        sauvegardeVoyage();
    }

    
    private void sauvegardeVoyage() {
        String ajout=this.nbplaces +"\n";
        ajout+=this.nbpassagers+"\n";
        ajout+=this.date+"\n";
        ajout+=this.villeDepart+"\n";
        ajout+=this.villeArrivee+"\n";
        
        String path = "voyages/"+this.id+".txt";
        
        FileWriter fw;
            try {
                fw = new FileWriter(path,true);
                fw.write(ajout);
                fw.close();
            } catch (IOException ex) {
                Logger.getLogger(Voyage.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }
    
    public void ajouterPassagers(){
        if(this.nbpassagers < this.nbplaces){
            this.nbpassagers++;
        }
    }
    
    private int recupId() {
        int newid=1;
        int temp;
        File folder = new File("voyages/.");
        for (final File fileEntry : folder.listFiles()) {
            temp = Integer.parseInt(fileEntry.getName());
            if(newid>temp);
                newid=temp;
        }
        System.out.println(newid);
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
}
