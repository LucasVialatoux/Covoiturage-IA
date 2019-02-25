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
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author user
 */
public class Discussion {
    public Utilisateur user1;
    public Utilisateur user2;
    
    public Discussion(Utilisateur user1,Utilisateur user2){
       this.user1=user1;
       this.user2=user2;
    }
   
    public void enregisterConversation(ArrayList<String> messages) throws IOException{
        String ajout="";
        String path = user1.nom+"-"+user2.nom+".txt";
        
        for(String s : messages){
            ajout+=s+";";
        }
        File f = new File(path);
        if(!f.exists()) { 
            File file = new File(path);
        }
        FileWriter fw = new FileWriter(path,true);
        fw.write(ajout);
        fw.close();
    }
    
    public String recupererConversation() throws IOException{
        String path = user1.nom+"-"+user2.nom+".txt";
        File f = new File(path);
        if(f.exists() && !f.isDirectory()) { 
            Scanner scanner = new Scanner(new FileReader(path));
            return scanner.toString();
        }
        return null;
    }
   
    
   
}
