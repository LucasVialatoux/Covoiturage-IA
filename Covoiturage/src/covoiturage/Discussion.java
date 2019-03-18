/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package covoiturage;

import java.io.File;
import java.io.FileFilter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author user
 */
public class Discussion {

    /**
     * @return the user1
     */
    public Utilisateur getUser1() {
        return user1;
    }

    /**
     * @return the user2
     */
    public Utilisateur getUser2() {
        return user2;
    }

    /**
     * @return the prix
     */
    public int getPrix() {
        return prix;
    }

    /**
     * @return the voyage
     */
    public String getVoyage() {
        return voyage;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }
    private Utilisateur user1;
    private Utilisateur user2;
    private int prix;
    private String voyage;
    private String date;
    private String voyageur;
            
    public Discussion(Utilisateur user1,Utilisateur user2, int prix,String voyage,String date,int i){
        this.user1=user1;
        this.user2=user2;
        this.prix=prix;
        this.voyage=voyage;
        this.date=date;
        if(i==1){
            this.voyageur=this.user1.nom;
        }else{
            this.voyageur=this.user2.nom;
        }
    }
   
    public void enregisterConversation(ArrayList<String> messages) throws IOException{
        String ajout=this.getPrix()+"#"+this.getVoyage()+"#"+this.getDate()+"#\n";
        for(String s : messages){
            ajout+=s+";\n";
        }
 
        String dateTime = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
        String path = "messages/"+this.getUser1().id+"-"+this.getUser2().id+"-"+dateTime+".txt";
        
        FileWriter fw = new FileWriter(path,true);
        fw.write(ajout);
        fw.close();
    }
    
    public ArrayList<String> recupererConversation() throws IOException{
        File f = new File("messages/.");              
        String pattern = this.user1.id+"-"+this.user2.id+".*.txt";
        final Pattern p = Pattern.compile(pattern);
        File[] pagesTemplates;
        pagesTemplates = f.listFiles((File f1) -> p.matcher(f1.getName()).matches());
        f=pagesTemplates[0];
        
        ArrayList<String> str=new ArrayList();
        if(f.exists() && !f.isDirectory()) { 
            Scanner sc = new Scanner(f);
            while(sc.hasNextLine()){
                String temp = sc.nextLine();
                if(!"#".equals(temp.substring(temp.length() - 1))){
                    temp=temp.substring(0,temp.length() - 1);
                    str.add(temp);
                }                    
            }
            System.out.println("temp");
        }
        return str;
    }
    
    @Override
    public String toString(){
        return this.getPrix()+" "+this.getVoyage()+" "+this.getDate();
    }
    
    public void conversation() throws IOException{
        ArrayList messages = new ArrayList<>();
        int quiParle = 0;
        int prixConducteurActuel=1;
        int prixUtilActuel=0;
        
        //Prix max utilisateur = random entre 0 et 20% du prix de base en moins
        int pourcentageRnd = (int)(Math.random() * (21));
        int prixMaxUtilisateur = (this.prix)-(this.prix*pourcentageRnd/100);
        System.out.println("prixMaxUtilisateur : "+prixMaxUtilisateur);
        //Prix minimum conducteur = random entre 0 et 25% du prix de base en moins
        pourcentageRnd = 0 + (int)(Math.random() * ((25 - 0) + 1));
        int prixMinConducteur = (this.prix)-(this.prix*pourcentageRnd/100);
        System.out.println("prixMinConducteur : "+prixMinConducteur);
        int nbreNegocie = negociationPrix(prixUtilActuel,prixMaxUtilisateur);
        
        //Début Négociation
        while (prixUtilActuel!=prixConducteurActuel){
            String newMessage;
            //Utilisateur première itération
            if (quiParle%2==0 && quiParle==0){
                prixUtilActuel=nbreNegocie;
                newMessage="prix proposé utilisateur : "+nbreNegocie+" prixUtilActuel : "+prixUtilActuel+" prixConducteurActuel : "+prixConducteurActuel+" prixMaxUtilisateur : "+prixMaxUtilisateur;
            //Utilisateur
            } else if (quiParle%2==0){
                prixUtilActuel=nbreNegocie;
                if(prixUtilActuel<prixConducteurActuel){
                    newMessage="prix proposé utilisateur : "+prixUtilActuel+" prixUtilActuel : "+prixUtilActuel+" prixConducteurActuel : "+prixConducteurActuel+" prixMaxUtilisateur : "+prixMaxUtilisateur;
                } else{
                    newMessage="utilisateur ok avec prix : "+prixUtilActuel+" prixUtilActuel : "+prixUtilActuel+" prixConducteurActuel : "+prixConducteurActuel+" prixMaxUtilisateur : "+prixMaxUtilisateur;
                    prixUtilActuel=prixConducteurActuel;
                }
            //Conducteur première réponse
            } else if (quiParle%2==1 && quiParle==1){
                prixConducteurActuel=nbreNegocie;
                if(prixUtilActuel<prixMinConducteur){
                    newMessage ="prix proposé conducteur : "+prixConducteurActuel+" prixUtilActuel : "+prixUtilActuel+" prixConducteurActuel : "+prixConducteurActuel+" prixMinConducteur : "+prixMinConducteur;
                } else {
                    newMessage="conducteur ok avec prix : "+prixConducteurActuel+" prixUtilActuel : "+prixUtilActuel+" prixConducteurActuel : "+prixConducteurActuel+" prixMinConducteur : "+prixMinConducteur;
                    prixConducteurActuel=prixUtilActuel;
                }
            //Conducteur
            } else {
                prixConducteurActuel=nbreNegocie;
                if(prixUtilActuel<prixConducteurActuel){
                    newMessage ="prix proposé conducteur : "+nbreNegocie+" prixUtilActuel : "+prixUtilActuel+" prixConducteurActuel : "+prixConducteurActuel+" prixMinConducteur : "+prixMinConducteur;
                } else {
                    newMessage="conducteur ok avec prix : "+nbreNegocie+" prixUtilActuel : "+prixUtilActuel+" prixConducteurActuel : "+prixConducteurActuel+" prixMinConducteur : "+prixMinConducteur;
                    prixConducteurActuel=prixUtilActuel;
                }
            }
                
            
            messages.add(newMessage);
            //On regénère un nouveau prix
            if (quiParle%2==0){
                //Utilisateur veut négocier
                nbreNegocie = negociationPrix(prixConducteurActuel,prixMaxUtilisateur);
            } else if(quiParle==1){
                //Conducteur veut négocier (1ère itération)
                nbreNegocie = negociationPrix(prixMinConducteur,this.prix);
            } else {
                //Conducteur veut négocier (après)
                nbreNegocie = negociationPrix(prixMinConducteur,this.prix);
            }
            quiParle++;
        }
        enregisterConversation(messages);
    }
    
    //typePersonne == 0 : Utilisateur (veut descendre le prix
    //typePersonne == 1 : Conducteur (ne veut pas trop descendre le prix)
    public int negociationPrix(int min,int max){
        int rndNumber = min + (int)(Math.random() * ((max - min) + 1));
        if(rndNumber<=0){
            return 0;
        } else {
            return rndNumber;
        }
    }

    /**
     * @return the voyageur
     */
    public String getVoyageur() {
        return voyageur;
    }
}
