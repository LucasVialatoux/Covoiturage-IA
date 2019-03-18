/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package covoiturage;


import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;
/**
 *
 * @author p1606751
 */
public abstract class Fenetre  {
    
    public Stage stage;
    public BorderPane root;
    public Utilisateur util;
    
    public Fenetre(Utilisateur util) {
        this.util=util;
        this.stage=new Stage();
        this.root = new BorderPane(); 
        Scene scene = new Scene(this.root, 1200, 675);
        this.stage.setTitle("Covoiturage");
        String imageURI = new File("icone.jpg").toURI().toString(); 
        Image image = new Image(imageURI);
        this.stage.getIcons().add(image);
        this.stage.setScene(scene);
        root.setStyle("-fx-background-color: #efefef;");
        menu();
        this.stage.show(); 
        
    }
    public void menu(){
        MenuBar mainMenu = new MenuBar();  
        Menu application = new Menu("Application");
        MenuItem quitter = new MenuItem("Quitter");
        quitter.setOnAction(new ChangeMenu(this.util,99));
        MenuItem deco = new MenuItem("Se deconnecter");
        application.getItems().setAll(deco,quitter);
        deco.setOnAction(new ChangeMenu(this.util,1));
        Menu aide = new Menu("Aide");
        aide.setOnAction(new ChangeMenu(this.util,2));
        MenuItem about = new MenuItem("A propos");
        aide.getItems().setAll(about);
        Menu mesVoyages = new Menu("Mes voyages");
        MenuItem accueil = new MenuItem("Accueil");
        accueil.setOnAction(new ChangeMenu(this.util,3));
        MenuItem PropV = new MenuItem("Proposer un voyage");
        MenuItem cherV = new MenuItem("Chercher un voyage");
        mesVoyages.getItems().setAll(accueil,PropV,cherV);
        mainMenu.getMenus().addAll(mesVoyages,application, aide);
        mainMenu.setUseSystemMenuBar(true);
        this.root.setTop(mainMenu);  
    }
    public class ChangeMenu implements EventHandler<ActionEvent>{
    public Utilisateur util;
    public int page;
    public ChangeMenu(Utilisateur u,int page){
        this.util=u;
        this.page=page;
    }
    @Override
    public void handle(ActionEvent event) {
            stage.close();
            if(this.page==1){
               new PageAccueil();
            }
            else if(this.page==2){
               new Aide(this.util);
            }
            else if(this.page==3){
                try {
                    new PageAccueilUtil(this.util);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }  
    }
    public Utilisateur trouverUtil(String s) throws FileNotFoundException{
            Utilisateur utilisateur=null;
            Scanner scanner = new Scanner(new FileReader("utilisateur.txt"));
            while (scanner.hasNextLine()) {
                String utili=scanner.nextLine();
                String[] parts = utili.split(";");
                for(String x:parts){
                    String[] parts2 = x.split(" ");
                    if(parts2[0].equals(s)){
                        utilisateur=new Utilisateur(Integer.parseInt(parts2[0]),parts2[1],parts2[2],parts2[3],Boolean.parseBoolean(parts2[4]),parts2[5]);
                    }
                    
                }
            }
            return utilisateur;
        }
}
