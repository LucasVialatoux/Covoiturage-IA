/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package covoiturage;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author antoine dulhoste
 */
public class Covoiturage extends Application {
    
    @Override
    public void start(Stage primaryStage){
        PageAccueil pageAccueil = new PageAccueil();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
