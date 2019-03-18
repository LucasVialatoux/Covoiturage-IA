/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TP2;

/**
 *
 * @author antoine dulhoste
 */
public class CreneauHoraireComparable extends CreneauHoraire implements Comparable<CreneauHoraireComparable> {
    
    public CreneauHoraireComparable(int j, int h, int m, int d) {
        super(j, h, m, d);   
    }
    
    @Override
    public int compareTo(CreneauHoraireComparable o){
        CreneauHoraireComparable ch=(CreneauHoraireComparable) o;
            if(this.getDurée()==ch.getDurée()){
                return 0;
            }else if(this.getDurée()<ch.getDurée()){
                return 1;
            }else{
                return -1;
            }
    }
}
