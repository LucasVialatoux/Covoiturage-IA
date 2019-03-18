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
        //utilisateur commence à négocier
        int quiParle = 0;
        int minConducteur=0;
        int minUtilisateur=0;
        int nbreNegocie = negociationPrix(minUtilisateur,this.prix);
        while (nbreNegocie!= 0 && nbreNegocie!=this.prix && quiParle<10){
            String newMessage;
            //On teste le nouveau prix
            if (nbreNegocie==0){
                newMessage="Abandon";
            }else if(nbreNegocie==this.prix){
                newMessage="ok";
            }
            
            //Utilisateur premier passage
            if (quiParle%2==0 && quiParle==0){
                minUtilisateur=nbreNegocie;
                newMessage="prix proposé utilisateur : "+nbreNegocie+" minUtilisateur : "+minUtilisateur+" minConducteur : "+minConducteur;
            //Utilisateur ensuite
            }else if(quiParle%2==0){
                minUtilisateur=nbreNegocie;
                if(nbreNegocie>minUtilisateur){
                    newMessage="prix proposé utilisateur : "+nbreNegocie+" minUtilisateur : "+minUtilisateur+" minConducteur : "+minConducteur;
                } else{
                    newMessage="utilisateur ok avec prix : "+nbreNegocie+" minUtilisateur : "+minUtilisateur+" minConducteur : "+minConducteur;
                    quiParle=10;
                }
            //Conducteur
            } else {
                minConducteur=nbreNegocie;
                if(minUtilisateur<minConducteur){
                    newMessage ="prix proposé conducteur : "+nbreNegocie+" minUtilisateur : "+minUtilisateur+" minConducteur : "+minConducteur;
                } else {
                    newMessage="conducteur ok avec prix : "+nbreNegocie+" minUtilisateur : "+minUtilisateur+" minConducteur : "+minConducteur;
                    quiParle=10;
                }
            }
                
            
            messages.add(newMessage);
            //On regénère un nouveau prix
            if (quiParle%2==0){
                //Utilisateur veut négocier
                nbreNegocie = negociationPrix(minUtilisateur,this.prix);
            } else{
                //Conducteur veut négocier
                nbreNegocie = negociationPrix(minConducteur,this.prix);
            }
            quiParle++;
        }
        enregisterConversation(messages);
    }
    
    //typePersonne == 0 : Utilisateur (veut descendre le prix
    //typePersonne == 1 : Conducteur (ne veut pas trop descendre le prix)
    public int negociationPrix(int min,int prix){
        int rndNumber = min + (int)(Math.random() * ((prix - min) + 1));
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
