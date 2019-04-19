package covoiturage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class PageAccueilUtil extends Fenetre {

    public PageAccueilUtil(Utilisateur util) throws FileNotFoundException {
        super(util);

        TableView<Discussion> tableReservations = new TableView();
        TableView<Discussion> tableAnnonces = new TableView();
        ObservableList<Discussion> dataAnnonces = dataAnnonces();
        ObservableList<Discussion> dataReservations = dataReservations();

        TableColumn dateAnnonces = new TableColumn("Date");
        dateAnnonces.setCellValueFactory(new PropertyValueFactory<>("date"));
        dateAnnonces.setSortType(TableColumn.SortType.DESCENDING);
        TableColumn dateReservations= new TableColumn("Date");
        dateReservations.setCellValueFactory(new PropertyValueFactory<>("date"));
        dateReservations.setSortType(TableColumn.SortType.DESCENDING);
        
        TableColumn voyageAnnonces = new TableColumn("Voyage");
        voyageAnnonces.setCellValueFactory(new PropertyValueFactory<>("voyage"));
        TableColumn voyageReservations = new TableColumn("Voyage");
        voyageReservations.setCellValueFactory(new PropertyValueFactory<>("voyage"));
        
        TableColumn conducteur = new TableColumn("Conducteur");
        conducteur.setCellValueFactory(new PropertyValueFactory<>("nomconducteur"));
        TableColumn voyageur = new TableColumn("Voyageur");
        voyageur.setCellValueFactory(new PropertyValueFactory<>("nomvoyageur"));

        tableAnnonces.getColumns().addAll(dateReservations, voyageReservations, voyageur);
        tableAnnonces.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableAnnonces.setCursor(Cursor.CLOSED_HAND);
        tableAnnonces.getItems().setAll(dataAnnonces);
        tableAnnonces.getSortOrder().add(dateReservations);
        tableAnnonces.setRowFactory(tv -> {
            TableRow<Discussion> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Discussion rowData = row.getItem();
                    stage.close();
                    try {
                        PageMessage pagemessage = new PageMessage(this.util, rowData);
                    } catch (IOException ex) {
                        Logger.getLogger(PageAccueilUtil.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            return row;
        });

        tableReservations.getColumns().addAll(dateAnnonces, voyageAnnonces, conducteur);
        tableReservations.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableReservations.setCursor(Cursor.CLOSED_HAND);
        tableReservations.getItems().setAll(dataReservations);
        tableReservations.getSortOrder().add(dateAnnonces);
        tableReservations.setRowFactory(tv -> {
            TableRow<Discussion> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Discussion rowData = row.getItem();
                    stage.close();
                    try {
                        PageMessage pagemessage = new PageMessage(this.util, rowData);
                    } catch (IOException ex) {
                        Logger.getLogger(PageAccueilUtil.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            return row;
        });

        Label labelreservations = new Label("Liste des voyages que vous avez réservés :");
        labelreservations.setMaxWidth(Double.MAX_VALUE);
        AnchorPane.setLeftAnchor(labelreservations, 0.0);
        AnchorPane.setRightAnchor(labelreservations, 0.0);
        labelreservations.setAlignment(Pos.CENTER);
        labelreservations.setFont(new Font(15));
        labelreservations.setPadding(new Insets(10, 0, 10, 0));
        
        Label labelannonces = new Label("Liste des voyages que vous avez proposés :");
        labelannonces.setMaxWidth(Double.MAX_VALUE);
        AnchorPane.setLeftAnchor(labelannonces, 0.0);
        AnchorPane.setRightAnchor(labelannonces, 0.0);
        labelannonces.setAlignment(Pos.CENTER);
        labelannonces.setFont(new Font(15));
        labelannonces.setPadding(new Insets(10, 0, 10, 0));
        
        VBox vb = new VBox();
        vb.getChildren().addAll(labelreservations,tableReservations,labelannonces, tableAnnonces);
        root.setCenter(vb);
    }

    private ObservableList<Discussion> dataAnnonces() throws FileNotFoundException {
        ObservableList<Discussion> data = FXCollections.observableArrayList();
        String[] listeDiscussion;
        listeDiscussion = listerRepertoire(new File("messages"));
        for (String s : listeDiscussion) {
            String[] parts = s.split("-");
            if (parts[0].equals(Integer.toString(this.util.id))) {
                data.add(getDisc(s,parts));
            }
        }
        return data;
    }
    private ObservableList<Discussion> dataReservations() throws FileNotFoundException {
        ObservableList<Discussion> data = FXCollections.observableArrayList();
        String[] listeDiscussion;
        listeDiscussion = listerRepertoire(new File("messages"));
        for (String s : listeDiscussion) {
            String[] parts = s.split("-");
            if (parts[1].equals(Integer.toString(this.util.id))) {
                data.add(getDisc(s,parts));
            }
        }
        return data;
    }

    public Discussion getDisc(String s,String[] parts) throws FileNotFoundException{
        Scanner scanner = new Scanner(new FileReader("messages\\" + s));
        String scan = scanner.nextLine();
        String[] discussion = scan.split("#");
        Utilisateur util1 = Fenetre.trouverUtil(parts[0]);
        Utilisateur util2 = Fenetre.trouverUtil(parts[1]);
        Voyage voyage = trouverVoyage(util2,parts[2]);        
        
        int prix=Integer.parseInt(discussion[0]);
        
        Discussion dis = new Discussion(util1, util2, voyage);
        return dis;
    }
    
    public String[] listerRepertoire(File repertoire) {
        String[] listefichiers;
        listefichiers = repertoire.list();
        return listefichiers;
    }
    
    public Voyage trouverVoyage(Utilisateur util, String part) {
        part=part.replace(".txt","");
        String path = "voyages/"+util.id+"-"+part+".txt";
        File f = new File(path);
        Voyage voyage = new Voyage();
        String[] elementsvoyage;
        if(f.exists() && !f.isDirectory()) { 
            try {
                    Scanner scanner = new Scanner(new FileReader(f));
                    String scan = scanner.nextLine();
                    elementsvoyage = scan.split("#");                
                    voyage.setConducteur(util);      
                    voyage.setId(Integer.parseInt(part));
                    voyage.setDate(elementsvoyage[0]);
                    voyage.setVilleDepart(elementsvoyage[1]);
                    voyage.setVilleArrivee(elementsvoyage[2]);
                    voyage.setNbplaces(Integer.parseInt(elementsvoyage[3]));
                    voyage.setNbpassagers(Integer.parseInt(elementsvoyage[4]));
                    voyage.setPrix(Integer.parseInt(elementsvoyage[5]));
                } 
            catch (FileNotFoundException ex) {
                Logger.getLogger(Voyage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return voyage;
    }

}
