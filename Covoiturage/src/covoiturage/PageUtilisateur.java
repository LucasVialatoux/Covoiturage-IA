package covoiturage;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.geometry.HPos;
import javafx.scene.Scene;
/**
 *
 * @author lucas
 */
public class PageUtilisateur extends Fenetre{
    public PageUtilisateur(){
        super();
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        
        Label labelTitle = new Label("Veuillez entrer le nom de l'utilisateur ainsi que son mot de passe à créer");
 
        // Put on cell (0,0), span 2 column, 1 row.
        grid.add(labelTitle, 0, 0, 2, 100);
        
        Label labelUserName = new Label("Nom du nouvel utilisateur   : ");
        TextField fieldUserName = new TextField();
        
        Label labelPassword = new Label("Mot de passe du nouvel utilisateur");
        PasswordField fieldPassword = new PasswordField();
        
        Button createButton = new Button("Créer");
        GridPane.setHalignment(labelUserName, HPos.RIGHT);
 
       // Put on cell (0,1)
       grid.add(labelUserName, 0, 1);
 
       GridPane.setHalignment(labelPassword, HPos.RIGHT);
       grid.add(labelPassword, 0, 2);
 
       // Horizontal alignment for User Name field.
       GridPane.setHalignment(fieldUserName, HPos.LEFT);
       grid.add(fieldUserName, 1, 1);
 
       // Horizontal alignment for Password field.
       GridPane.setHalignment(fieldPassword, HPos.LEFT);
       grid.add(fieldPassword, 1, 2);
        
       // Horizontal alignment for Login button.
       GridPane.setHalignment(createButton, HPos.RIGHT);
       grid.add(createButton, 1, 3);
       root.getChildren().add(grid);
       
    }
}
