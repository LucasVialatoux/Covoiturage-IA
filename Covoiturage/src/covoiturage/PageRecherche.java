package covoiturage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class PageRecherche extends Fenetre{
    public DatePicker Date;
    public TextField Depart;
    public TextField Arrivee;
    
    public PageRecherche(Utilisateur util) {
        super(util);
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        Label labelTitle = new Label("Veuillez entrer les informations ci-dessous pour effectuer une recherche");
        grid.add(labelTitle, 0, 0, 2, 1);
        
        Label labelDepart = new Label("Ville de départ : ");
        Depart = new TextField();
        
        Label labelArrivee = new Label("Ville d'arrivée : ");
        Arrivee = new TextField();
        
        Label labelDate = new Label("Date : ");
        Date = new DatePicker();
        
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

        searchButton.setOnAction((ActionEvent t) -> {
            try {
                recherche();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(PageRecherche.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(PageRecherche.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        grid.setVgap(4);
        grid.setHgap(10);
        grid.setAlignment(Pos.CENTER);
        root.setCenter(grid);
    }

    public void recherche() throws FileNotFoundException, IOException{
        String depart = Depart.getText();
        String arrivee = Arrivee.getText();
        LocalDate date = Date.getValue();
        
        String pattern = "yyyy-MM-dd";
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);
        String dateStr =dateFormatter.format(date);
        
        if (depart.isEmpty() && arrivee.isEmpty() && date == null){
            boiteDialogueError(1);
        } else if (depart.isEmpty() || arrivee.isEmpty()){
            boiteDialogueError(2);
        } else if (date == null){
            boiteDialogueError(3);
        } else{
            ArrayList<Voyage> voyages = listeVoyagesCorrepondant(depart,arrivee,dateStr);
            PageResultat pr = new PageResultat(voyages,util);
            stage.close();
        }        
    }
    
    //info=1 : Tout vide
    //info=2 : Pas de départ ni d'arrivée
    //info=3 : pas de date
    //info autre : Format date incorrecte
    public void boiteDialogueError(int info){
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur de saisie");
        alert.setHeaderText("Informations manquantes !");
        switch (info) {
            case 1:
                alert.setContentText("Veuillez entrer soit une ville de départ, soit une ville d'arrivée ainsi qu'une date");
                break;
            case 2:
                alert.setContentText("Veuillez entrer une ville de départ et une ville d'arrivée");
                break;
            case 3:
                alert.setContentText("Veuillez entrer une date");
                break;
            default:
                alert.setContentText("Format de la date incorrecte (Rappel du format : JJ/MM/AAAA");
                break;
        }
        alert.showAndWait();
    }
    
    public ArrayList<Voyage> listeVoyagesCorrepondant(String depart, String arrivee, String date) {
        ArrayList<Voyage> voyages = new ArrayList<>();
        File folder = new File("voyages/.");
        for (final File fileEntry : folder.listFiles()) {
            String[] elementsvoyage;
            Scanner scanner;
            try {
                scanner = new Scanner(new FileReader(fileEntry));
                String scan = scanner.nextLine();
                elementsvoyage = scan.split("#");
                String file = fileEntry.getName().substring(0, fileEntry.getName().length() - 4);
                String[] filename = file.split("-");
                if(date.equals(elementsvoyage[0]) &&  depart.equals(elementsvoyage[1]) && arrivee.equals(elementsvoyage[2])){
                    System.out.print(Fenetre.trouverUtil(filename[0])); 
                    Voyage v = new Voyage(Fenetre.trouverUtil(filename[0]),Integer.parseInt(filename[1]),Integer.parseInt(elementsvoyage[5]),Integer.parseInt(elementsvoyage[3]),elementsvoyage[1],elementsvoyage[2],elementsvoyage[0],Integer.parseInt(elementsvoyage[4]));
                    if(!v.estPlein())
                        voyages.add(v);
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Voyage.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
        return voyages;
    }
    
}
