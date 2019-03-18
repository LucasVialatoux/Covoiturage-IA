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
public class Plateau {
    public int longueur;
    public int largeur;
    public int[][] etatIdPlateau;
    
    public Plateau(int longueur,int largeur){
        this.longueur=longueur;
        this.largeur=largeur;
        initialiser();
    }
    public void initialiser(){
        this.etatIdPlateau=new int[this.longueur][this.largeur];
    }
    
    public void appliquerCoup(Coup coup,int id){
        this.etatIdPlateau[coup.x][coup.y]=id;
    }
    
    public int random(int max,int min){
        return (int)(Math.random() * ( max - min ));
    }
    
    @Override
    public String toString(){
        String s="";
        for(int i=0;i<this.longueur;i++){
            for(int j=0;j<this.largeur;j++){
                s=s+this.etatIdPlateau[i][j]+"|";
            }
            s=s+"\n";
        }
        return s;
    }
}
