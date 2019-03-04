/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package covoiturage;

import java.io.File;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author antoine dulhoste
 */
public class PageAccueilUtil extends Fenetre {
    public PageAccueilUtil(Utilisateur util){
        super(util);
        TableView<Discussion> table = new TableView();
        ObservableList<Discussion>data =disUtil();
        //data.add(new Discussion(rs.getInt(1),rs.getString(11),rs.getDate(7),n1,n2,rs.getString(14),rs.getInt(8),rs.getTime(9)));
        table.setEditable(true);
        TableColumn date = new TableColumn("Date");
        date.setCellValueFactory(new PropertyValueFactory<Discussion, String>("Date"));
        TableColumn voyage = new TableColumn("Voyage");
        voyage.setCellValueFactory(new PropertyValueFactory<Discussion, String>("voyage"));
        TableColumn voyageur = new TableColumn("Voyageur");
        voyageur.setCellValueFactory(new PropertyValueFactory<Discussion, String>("voyageur"));
        TableColumn fini = new TableColumn("Terminé");
        fini.setCellValueFactory(new PropertyValueFactory<Discussion, String>("Termine"));
        table.getColumns().addAll(date,voyage,voyageur,fini);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setCursor(Cursor.CLOSED_HAND);
        root.setCenter(table);
        }
    public ObservableList<Discussion> disUtil(){
        ObservableList<Discussion> data =FXCollections.observableArrayList();
        
        return  data;
    }
    public void listerRepertoire(File repertoire){ 
        String [] listefichiers; 
        int i; 
        listefichiers=repertoire.list(); 
        for(i=0;i<listefichiers.length;i++){ 
        if(listefichiers[i].endsWith(".txt")==true){ 
            System.out.println(listefichiers[i].replaceFirst(".txt","")); 
        } 
    } 
}
}
