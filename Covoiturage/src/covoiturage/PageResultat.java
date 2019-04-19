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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 *
 * @author user
 */
public class PageResultat extends Fenetre{
    
    public PageResultat(ArrayList<Voyage> voyages, Utilisateur util){
        super(util);
               
        TableView<Voyage> tableResultat = new TableView();
        ObservableList<Voyage> data = FXCollections.observableArrayList();
        voyages.forEach((v) -> {
            data.add(v);
        });
        ObservableList<Voyage> dataResultat = data;
        
        TableColumn date = new TableColumn("Date");
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        TableColumn voyage = new TableColumn("Voyage");
        voyage.setCellValueFactory(new PropertyValueFactory<>("voyage"));
        TableColumn placeLibre = new TableColumn("Place libre restante");
        placeLibre.setCellValueFactory(new PropertyValueFactory<>("PlaceLibre"));
        TableColumn conducteur = new TableColumn("Conducteur");
        conducteur.setCellValueFactory(new PropertyValueFactory<>("NomConducteur"));
        
        tableResultat.getColumns().addAll(date, voyage, placeLibre,conducteur);
        tableResultat.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableResultat.setCursor(Cursor.CLOSED_HAND);
        tableResultat.getItems().setAll(dataResultat);
        tableResultat.setRowFactory(tv -> {
            TableRow<Voyage> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Voyage rowData = row.getItem();
                    stage.close();
                    try {
                        Discussion disc = new Discussion(util,rowData.getConducteur(),rowData);
                        disc.conversation();
                        new PageMessage(util,disc);
                        stage.close();
                    } catch (IOException ex) {
                        Logger.getLogger(PageAccueilUtil.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            return row;
        });
        Button retour = new Button("Retour");
        retour.setOnAction((ActionEvent t) -> {
            stage.close();
            try {
                new PageAccueilUtil(util);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(PageMessage.class.getName()).log(Level.SEVERE, null, ex);
            }
        });    
        
        Label labelresulats = new Label("Voyages correspondants (cliquez pour réserver):");
        labelresulats.setFont(new Font(15));
        labelresulats.setPadding(new Insets(25, 0, 25, 0));
        
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(35);
        
        GridPane grid = new GridPane();
        GridPane.setMargin(retour, new Insets(0, 0, 0, 10));
        grid.add(retour,0,0);
        grid.add(labelresulats, 1, 0);
        grid.getColumnConstraints().addAll(col1);
        
        VBox vb = new VBox();
        vb.getChildren().addAll(grid,tableResultat);
        root.setCenter(vb);        
    }  
}
