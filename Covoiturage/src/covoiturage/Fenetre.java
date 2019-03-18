/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package covoiturage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
        MenuItem voirDisc = new MenuItem("Voir discussions");
        voirDisc.setOnAction(new ChangeMenu(this.util,4));
        mesVoyages.getItems().setAll(accueil,PropV,cherV,voirDisc);
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
            switch (this.page) {
                case 1:
                    PageAccueil pageAccueil = new PageAccueil();
                    break;
                case 2:
                    Aide aide = new Aide(this.util);
                    break;
                case 3:
                    PageAccueilUtil pageAccueilUtil = new PageAccueilUtil(this.util);
                    break;
                case 4:
                    Utilisateur util2 = new Utilisateur(66,"d","d","d",false,"d.d");
                    Discussion disc = new Discussion(this.util,util2,50,"Paris Lyon","2010-10-10");
                    {
                        try {
                            disc.conversation();
                        } catch (IOException ex) {
                            Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    {
                        try {
                            PageMessage pagemessage = new PageMessage(this.util,disc);
                        } catch (IOException ex) {
                            Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    break;
                default:
                    break;
            }
        }  
    }
}
