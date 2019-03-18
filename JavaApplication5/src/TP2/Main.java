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
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CreneauHoraireComparable c1 = new CreneauHoraireComparable(87, 10,00,1) ; 
        CreneauHoraireComparable c2 = new CreneauHoraireComparable(87, 10,00,0) ;
        System.out.println(c1.compareTo(c2));
    }
    
}
