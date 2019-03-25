/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package covoiturage;

import java.util.ArrayList;

/**
 *
 * @author lucas
 */
public class Preference {
    private ArrayList<String> preferenceExistante = new ArrayList<String>(10);
    
    //Initialisation des préférences
    public void initPref(ArrayList<String> preferenceExistante){
        preferenceExistante.add("Fumeur");
        preferenceExistante.add("Animaux");
        preferenceExistante.add("Enfants");
        
    }
}
