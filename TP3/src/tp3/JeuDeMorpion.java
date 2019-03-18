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
public class JeuDeMorpion {
    
    public Plateau plateau;
    public Joueur[] joueur;
    public int aj;
    
    public JeuDeMorpion(Joueur joueur1,Joueur joueur2){
        this.plateau= new Plateau(3,3);
        this.joueur=new Joueur[2];
        this.joueur[0]=joueur1;
        this.joueur[1]=joueur2;
        this.aj=0;
        jouerPartie();
    }
    public boolean partieTerminee(){
        for(int i=0;i<3;i++){
            if(plateau.etatIdPlateau[i][0]==plateau.etatIdPlateau[i][1]&& plateau.etatIdPlateau[i][1]==plateau.etatIdPlateau[i][2]&&plateau.etatIdPlateau[i][0]!=0){
                return true;
            }
            if(plateau.etatIdPlateau[0][i]==plateau.etatIdPlateau[1][i]&& plateau.etatIdPlateau[1][i]==plateau.etatIdPlateau[2][i]&&plateau.etatIdPlateau[0][i]!=0){
                return true;
            }
        }
        if(plateau.etatIdPlateau[0][0]==plateau.etatIdPlateau[1][1]&& plateau.etatIdPlateau[1][1]==plateau.etatIdPlateau[2][2]&&plateau.etatIdPlateau[0][0]!=0){
                return true;
            }
        if(plateau.etatIdPlateau[0][2]==plateau.etatIdPlateau[1][1]&& plateau.etatIdPlateau[1][1]==plateau.etatIdPlateau[2][0]&&plateau.etatIdPlateau[0][2]!=0){
                return true;
            }
        return false;
    }
    
    public Joueur getJoueurSuivant(){
        if(this.aj==0){
            this.aj=1;
            return this.joueur[1];
        }else{
            this.aj=0;
            return this.joueur[0];
        }
    }
    
    public boolean coupPossible(Coup c){
        if(plateau.etatIdPlateau[c.x][c.y]==0){
            return true;
        }else
        return false;
    }
    
    public Joueur jouerPartie() {
        Joueur retour = null; // utilisé comme variable de parcours,et renvoyé comme étant
        // le joueur gagnant
        while (!partieTerminee()) {
            retour = getJoueurSuivant();
            Coup c = retour.getCoup(plateau);
            if (coupPossible(c))
                plateau.appliquerCoup(c,retour.getId());
            else
                System.err.print("erreur") ;
            System.exit(1) ;
        }
        return retour;
    }
}
