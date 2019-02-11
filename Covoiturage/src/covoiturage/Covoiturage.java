/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package covoiturage;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author antoine dulhoste
 */
public class Covoiturage extends Application {
    public ArrayList<Utilisateur> utils;

    public void start(Stage primaryStage) throws FileNotFoundException, IOException {
        initUtil();
        new PageAcceuil();
    }
    
    public void initUtil() throws FileNotFoundException{
            this.utils=new ArrayList<Utilisateur>();
            Scanner scanner = new Scanner(new FileReader("utilisateur.txt"));
            while (scanner.hasNextLine()) {
                String util=scanner.nextLine();
                String[] parts = util.split(" ");
                utils.add(new Utilisateur(parts[0],parts[1],parts[2],Boolean.parseBoolean(parts[3]),parts[4]));
            }
        }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
