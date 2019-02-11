package covoiturage;
import javafx.geometry.Insets;
import java.io.File;
import javafx.scene.image.Image;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
/**
 *
 * @author p1606751
 */
public class PageAcceuil {
    
    public TextField user;
    public PasswordField mdp;
    public Stage stage;
    public Pane root;
    

    public PageAcceuil(){
        this.stage=new Stage();
        this.root = new Pane();
        String imageURI = new File("icone.jpg").toURI().toString(); 
        Image image = new Image(imageURI);
        this.stage.getIcons().add(image);
        Text t = new Text();
        t.setText("Identifiants   : ");
        t.setFont(Font.font ("Comic Sans MS",FontWeight.BOLD,20));
        t.setFill(Color.RED);
        Text t1 = new Text();
        t1.setText("Mot de passe :");
        t1.setFont(Font.font ("Comic Sans MS",FontWeight.BOLD,20));
        t1.setFill(Color.RED);
        user = new TextField ();
        mdp = new PasswordField();
        Button btn = new Button();
        btn.setText("Connexion");
        Button btn2 = new Button();
        btn2.setText("Pas encore inscrit ?");
        String imageURI2 = new File("fond.png").toURI().toString(); 
        Image image2 = new Image(imageURI2);
        ImageView imageView = new ImageView(image2); 
        imageView.setFitWidth(1200); 
        imageView.setFitHeight(675); 
        root.getChildren().setAll(imageView);
        GridPane grid = new GridPane();
        grid.setVgap(4);
        grid.setHgap(10);
        grid.setPadding(new Insets(5, 5, 5, 5));
        grid.add(t,45 , 60);
        grid.add(user, 46, 60);
        grid.add(t1, 45, 65);
        grid.add(mdp, 46, 65);
        grid.add(btn, 46, 70);
        grid.add(btn2, 46, 75);
        root.getChildren().add(grid);
        Scene scene = new Scene(root, 1200, 675);
        stage.setTitle("covoiturage"); 
        stage.setScene(scene);
        stage.show();
    }
}
