/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package covoiturage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author user
 */
public class PageMessage extends Fenetre{
    
    private final VBox chatBox = new VBox(5);
    private final List<Label> messages = new ArrayList<>();
    private int index = 0;
       
    public PageMessage(Utilisateur user,Discussion disc) throws IOException{
        super(user);
        
        ArrayList<String> messagesString=disc.recupererConversation();
        
        ObservableList listMsg = chatBox.getChildren(); 
        
        messagesString.forEach((s) -> {
            this.messages.add(new Label(s));
            if(index%2==0){
                this.messages.get(index).setAlignment(Pos.CENTER_LEFT);
            }else{
                this.messages.get(index).setAlignment(Pos.CENTER_RIGHT);
            }
            listMsg.add(this.messages.get(index));
            index++;
        });
        
        root.setCenter(chatBox);
    }
}

