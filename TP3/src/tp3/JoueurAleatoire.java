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
public class JoueurAleatoire extends Joueur{
    
    public JoueurAleatoire(int id){
        super(id);
    }

    @Override
    public Coup getCoup(Plateau etatjeu) {
        boolean b=false;
        Coup c=null;
        while(!b){
            c=new Coup(this.random(etatjeu.longueur, 0),this.random(etatjeu.largeur, 0));
            if(etatjeu.etatIdPlateau[c.x][c.y]==0){
                b=true;
            }
        }
        return c;
    }
}
