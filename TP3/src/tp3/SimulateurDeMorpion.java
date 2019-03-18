/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp3;

/**
 *
 * @author antoine dulhoste
 */
public class SimulateurDeMorpion{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JoueurAleatoire j1=new JoueurAleatoire(1);
        JoueurAleatoire j2=new JoueurAleatoire(2);
        JeuDeMorpion j=new JeuDeMorpion(j1, j2);
        System.out.println(j.plateau);
        
    }
    
}
