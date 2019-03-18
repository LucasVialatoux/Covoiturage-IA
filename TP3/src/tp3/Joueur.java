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
public abstract class Joueur {
    private int id;
    
    public Joueur(int id){
        this.id=id;
    }

    public int getId() {
        return id;
    }
    
    public int random(int max,int min){
        return (int)(Math.random() * ( max - min ));
    }
    public abstract Coup getCoup(Plateau etatjeu);
}
