/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package covoiturage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 *
 * @author user
 */
public class PageMessages extends Fenetre{
    
    private final Pane root = new Pane();
    private Scene scene;

    private final VBox chatBox = new VBox(5);
    private final List<Label> messages = new ArrayList<>();
    private final ScrollPane container = new ScrollPane();
    private int index = 0;
    
    
    
    
    public PageMessages(Utilisateur user,Discussion disc) throws IOException{
        super(user);
        ArrayList<String> messagesString=disc.recupererConversation();
        
        
        this.container.setPrefSize(216, 400);
        this.container.setContent(this.chatBox); 
        this.chatBox.getStyleClass().add("chatbox");
        
        for(String s : messagesString){
            this.messages.add(new Label(s));
        }
        if(index%2==0){

            this.messages.get(index).setAlignment(Pos.CENTER_LEFT);
            System.out.println("1");

        }else{

            this.messages.get(index).setAlignment(Pos.CENTER_RIGHT);
            System.out.println("2");

        }


       chatBox.getChildren().add(this.messages.get(index));
        index++;
        
        root.getChildren().addAll(container); 
        stage.setScene(new Scene(root,300,450));
        stage.show();
    }
}

