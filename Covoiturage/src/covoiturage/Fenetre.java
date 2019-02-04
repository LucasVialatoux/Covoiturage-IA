/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package covoiturage;


import java.awt.Color;
import java.io.File;
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
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author p1606751
 */
public abstract class Fenetre  {
    
    public Stage stage;
    public BorderPane root;
    
    public Fenetre() {
        this.stage=new Stage();
        this.root = new BorderPane(); 
        Scene scene = new Scene(this.root, 1200, 675);
        this.stage.setTitle("Covoiturage");
        String imageURI = new File("icone.png").toURI().toString(); 
        Image image = new Image(imageURI);
        this.stage.getIcons().add(image);
        this.stage.setScene(scene);
        //menu();
        this.stage.show(); 

    }
    /*public void menu(){
        MenuBar mainMenu = new MenuBar();  
        Menu planning = new Menu("Planning");
        planning.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent t) {
                                stage.close();
                                new Planning();
                                }
                            });
        MenuItem consulter = new MenuItem("Consulter");
        planning.getItems().setAll(consulter); ;
        Menu application = new Menu("Application");
        MenuItem quitter = new MenuItem("Quitter");
        quitter.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent t) {
                                stage.close();
                                }
                            });
        MenuItem deco = new MenuItem("Se deconnecter");
        application.getItems().setAll(deco,quitter);
        deco.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent t) {
                                stage.close();
                                new PageAcceuil();
                                }
                            });
        Menu aide = new Menu("Aide");
        aide.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent t) {
                                stage.close();
                                new Aide();
                                }
                            });
        MenuItem about = new MenuItem("A propos");
        aide.getItems().setAll(about);
        mainMenu.getMenus().addAll(planning, application, aide);
        mainMenu.setUseSystemMenuBar(true);
        this.root.setTop(mainMenu);  
    }*/
}
