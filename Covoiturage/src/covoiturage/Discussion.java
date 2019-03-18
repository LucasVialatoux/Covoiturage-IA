/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package covoiturage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

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
            
    public Discussion(Utilisateur user1,Utilisateur user2, int prix,String voyage,String date){
        this.user1=user1;
        this.user2=user2;
        this.prix=prix;
        this.voyage=voyage;
        this.date=date;
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
        String path = "messages/"+this.getUser1().id+"-"+this.getUser2().id+".txt";
        File f = new File(path);
        if(f.exists() && !f.isDirectory()) { 
            ArrayList<String> str=new ArrayList();
            Scanner sc = new Scanner(f);
            while(sc.hasNextLine()){
                String temp = sc.nextLine();
                if(!"|".equals(temp.substring(temp.length() - 1))){
                    temp=temp.substring(0,temp.length() - 1);
                    str.add(temp);
                }                    
            }
            return str;
        }
        return null;
    }
    
    @Override
    public String toString(){
        return this.getPrix()+" "+this.getVoyage()+" "+this.getDate();
    }
}
