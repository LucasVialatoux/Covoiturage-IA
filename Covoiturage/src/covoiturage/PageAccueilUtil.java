/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package covoiturage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Date;
import java.util.Scanner;
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
    
    public PageAccueilUtil(Utilisateur util) throws FileNotFoundException{
        super(util);
        TableView<Discussion> table = new TableView();
        ObservableList<Discussion>data =disUtil();
        TableColumn date = new TableColumn("Date");
        date.setCellValueFactory(new PropertyValueFactory<Discussion, String>("date"));
        TableColumn voyage = new TableColumn("Voyage");
        voyage.setCellValueFactory(new PropertyValueFactory<Discussion, String>("voyage"));
        TableColumn voyageur = new TableColumn("Voyageur");
        voyageur.setCellValueFactory(new PropertyValueFactory<Discussion, String>("voyageur"));
        table.getColumns().addAll(date,voyage,voyageur);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setCursor(Cursor.CLOSED_HAND);
        table.getItems().setAll(data);
        table.setRowFactory( tv -> {
        TableRow<Discussion> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Discussion rowData = row.getItem();
                    stage.close();
                    try {
                        PageMessage pagemessage = new PageMessage(this.util,rowData);
                    } catch (IOException ex) {
                        Logger.getLogger(PageAccueilUtil.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            return row ;
        });
        
        root.setCenter(table);
        }
    
    public ObservableList<Discussion> disUtil() throws FileNotFoundException{
        ObservableList<Discussion> data =FXCollections.observableArrayList();
        String [] listeDiscussion;
        listeDiscussion=listerRepertoire(new File("messages"));
        for (String s:listeDiscussion){
            String[] parts = s.split("-");
            if(parts[0].equals(Integer.toString(this.util.id)) || parts[1].equals(Integer.toString(this.util.id)) ){
                Scanner scanner = new Scanner(new FileReader("messages\\"+s));
                String scan=scanner.nextLine();
                String[] discussion = scan.split("#");
                Utilisateur util1=this.trouverUtil(parts[0]);
                Utilisateur util2=this.trouverUtil(parts[1]);
                int i=2;
                if(parts[1].equals(Integer.toString(this.util.id))){
                    i=1;
                }
                Discussion dis=new Discussion(util1, util2, Integer.parseInt(discussion[0]), discussion[1], discussion[2],i);
                data.add(dis);
            }
        }
        return  data;
    }
    
    public String[] listerRepertoire(File repertoire){ 
        String [] listefichiers;
        listefichiers=repertoire.list(); 
        return listefichiers;
}
}
