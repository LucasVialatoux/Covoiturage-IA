/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package covoiturage;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;

/**
 *
 * @author p1606751
 */
public class Connexion implements EventHandler<ActionEvent>{
    public ArrayList<Utilisateur> utils;
    public PageAccueil page;
    public Connexion(PageAccueil f){
        this.page=f;
    }
     public void boiteDeDialogueE(){ 
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Connexion");
                alert.setHeaderText("ERREUR!");
                alert.setContentText("Problème d'identifiant");
                alert.showAndWait();   
     }
     public void boiteDeDialogueF(){ 
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("ERREUR!");
                alert.setContentText("Problème inconnu");
                alert.showAndWait();   
     }

    @Override
    public void handle(ActionEvent event) {
        try {
            initUtil();
        } catch (FileNotFoundException ex) {
            boiteDeDialogueF();
        }
        Utilisateur util=null;
        for(Utilisateur x:utils){
            if(x.email.equals(page.user.getText())){
                util=x;
            }
        }
        if(util.mdp.equals(page.mdp.getText())){
            new AccueilUtil(util);
            page.stage.close();
        }else{
            boiteDeDialogueE();
        }
    }
    public void initUtil() throws FileNotFoundException{
            this.utils=new ArrayList<Utilisateur>();
            Scanner scanner = new Scanner(new FileReader("utilisateur.txt"));
            while (scanner.hasNextLine()) {
                String util=scanner.nextLine();
                String[] parts = util.split(" ");
                utils.add(new Utilisateur(parts[0],parts[1],parts[2],Boolean.parseBoolean(parts[3]),parts[4]));
            }
        }
}
