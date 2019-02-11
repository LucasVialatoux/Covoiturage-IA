/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package covoiturage;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;

/**
 *
 * @author p1606751
 */
public class Connexion implements EventHandler<ActionEvent>{
    public PageAcceuil page;
    public Connexion(PageAcceuil f){
        this.page=f;
    }
     public void boiteDeDialogueE(){ 
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Connexion");
                alert.setHeaderText("ERREUR!");
                alert.setContentText("Problème d'identifiant");
                alert.showAndWait();   
     }
      public void boiteDeDialogueI(String msg){ 
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Connexion");
                alert.setHeaderText("Attention");
                alert.setContentText("Veuillez rentrer un "+msg);
                alert.showAndWait();   
     }

    @Override
    public void handle(ActionEvent event) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
