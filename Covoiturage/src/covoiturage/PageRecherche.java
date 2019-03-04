/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package covoiturage;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 *
 * @author lucas
 */
public class PageRecherche extends Fenetre{
    public TextField Date;
    public TextField Depart;
    public TextField Arrivee;
    
    public PageRecherche(Utilisateur util) {
        super(util);
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        Label labelTitle = new Label("Veuillez entrer les informations ci-dessous pour effectuer une recherche");
        grid.add(labelTitle, 0, 0, 2, 1);
        
        Label labelDepart = new Label("Départ : ");
        Depart = new TextField();
        
        Label labelArrivee = new Label("Arrivée : ");
        Arrivee = new TextField();
        
        Label labelDate = new Label("Date : ");
        Date = new TextField();
        
        Button searchButton = new Button("Chercher un trajet");
        
        GridPane.setHalignment(labelDepart, HPos.RIGHT);
        grid.add(labelDepart, 0, 1);
       
        GridPane.setHalignment(labelArrivee, HPos.RIGHT);
        grid.add(labelArrivee, 0, 2);
       
        GridPane.setHalignment(labelDate, HPos.RIGHT);
        grid.add(labelDate, 0, 3);
       
        // Horizontal alignment for last Name field.
        GridPane.setHalignment(Depart, HPos.LEFT);
        grid.add(Depart, 1, 1);

        // Horizontal alignment for first Name field.
        GridPane.setHalignment(Arrivee, HPos.LEFT);
        grid.add(Arrivee, 1, 2);

        // Horizontal alignment for e-mail field.
        GridPane.setHalignment(Date, HPos.LEFT);
        grid.add(Date, 1, 3);
        
        // Horizontal alignment for search button.
        GridPane.setHalignment(searchButton, HPos.RIGHT);
        grid.add(searchButton, 1, 4);

        
        grid.setVgap(4);
        grid.setHgap(10);
        grid.setAlignment(Pos.CENTER);
        root.setCenter(grid);
    }

    
    
}
