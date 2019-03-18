/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package covoiturage;

import java.io.IOException;
import java.util.ArrayList;
import javafx.scene.control.Label;
import static javafx.geometry.HPos.LEFT;
import static javafx.geometry.HPos.RIGHT;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.control.ScrollPane;
import javafx.scene.paint.Color;

/**
 *
 * @author user
 */
public class PageMessage extends Fenetre{
    
    private int index = 0;
       
    public PageMessage(Utilisateur user,Discussion disc) throws IOException{
        super(user);
        GridPane grid = new GridPane();
        ScrollPane scroll = new ScrollPane();
        ArrayList<String> messagesString=disc.recupererConversation();
        
        messagesString.forEach((s) -> {
            Label msg = new Label(s);
            Color color= Color.WHITE;
            if(index%2==0){
                GridPane.setHalignment(msg,LEFT);
                grid.add(msg,0,index);
            }else{
                color= Color.LIGHTGREEN;
                GridPane.setHalignment(msg,RIGHT);
                grid.add(msg,1,index);
            }
            
            msg.setBackground(new Background(new BackgroundFill(color, new CornerRadii(10), Insets.EMPTY)));
            msg.setPadding(new Insets(10, 10, 10, 10));
            index++;
        });
        grid.setVgap(4);
        grid.setHgap(10);
        grid.setAlignment(Pos.CENTER);
        scroll.setContent(grid);
        scroll.setFitToWidth(true);
        root.setCenter(scroll);
    }
}

