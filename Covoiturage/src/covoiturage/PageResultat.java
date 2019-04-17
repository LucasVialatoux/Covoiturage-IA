/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package covoiturage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import static javafx.geometry.HPos.RIGHT;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 *
 * @author user
 */
public class PageResultat extends Fenetre{
    
    public PageResultat(ArrayList<Voyage> voyages, Utilisateur util){
        super(util);
        GridPane grid = new GridPane();
        int i=0;
        for(Voyage v : voyages){
            Label titre = new Label(v.getVilleDepart()+"-"+v.getVilleArrivee()+" | "+v.getDate());
            Button button = new Button("Reserver");
            button.setOnAction((event) -> {
                Discussion disc = new Discussion(util,v.getConducteur(),v);
                try {
                    disc.conversation();
                    new PageMessage(util,disc);
                    stage.close();
                } catch (IOException ex) {
                    Logger.getLogger(PageResultat.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                System.out.println("Button clicked");
              });
            grid.add(titre,0,i);
            grid.add(button,1,i);
            i++;
        }
        Button retour = new Button("Retour");
        retour.setOnAction((ActionEvent t) -> {
            stage.close();
            try {
                new PageAccueilUtil(util);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(PageMessage.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        GridPane.setHalignment(retour,RIGHT);
        GridPane.setMargin(retour, new Insets(20, 0, 0, 0));
        grid.add(retour,1,voyages.size()+1);
        root.setCenter(grid);
        
    }  
}
