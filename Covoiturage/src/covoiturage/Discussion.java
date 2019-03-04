/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package covoiturage;

import java.io.File;
import java.io.FileReader;
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
    public Utilisateur user1;
    public Utilisateur user2;
    public boolean estFini;
    public int prix;
    public String voyage;
    public String date;
            
    public Discussion(Utilisateur user1,Utilisateur user2, int prix,String voyage,String date){
        this.user1=user1;
        this.user2=user2;
        this.estFini=false;
        this.prix=prix;
        this.voyage=voyage;
        this.date=date;
    }
   
    public void enregisterConversation(ArrayList<String> messages) throws IOException{
        String ajout=this.prix+"|"+this.voyage+"|"+this.date+"|";
        for(String s : messages){
            ajout+=s+";";
        }
        
        String path= this.user1.id+"-"+this.user2.id+".txt";
        File file = new File(path);
        file.delete();
        
        if(this.estFini){ 
            String dateTime = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
            path = this.user1.id+"-"+this.user2.id+"_"+dateTime+".txt";
        }
        
        FileWriter fw = new FileWriter(path,true);
        fw.write(ajout);
        fw.close();
    }
    
    public String recupererConversation() throws IOException{
        String path = this.user1.id+"-"+this.user2.id+".txt";
        File f = new File(path);
        if(f.exists() && !f.isDirectory()) { 
            String str="";
            Scanner sc = new Scanner(f);
            while(sc.hasNextLine()){
                str += sc.nextLine();                     
            }
            return str;
        }
        return null;
    }   
    
    public void finirDiscussion(){
        this.estFini=true;
    }
}
