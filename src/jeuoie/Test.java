/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeuoie;

import java.util.Scanner;

/**
 *
 * @author tnguyen15
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //String p ;
        int max = 6, min = 1;
        int Pion = 0;
        int Recul;
        int dee1 = 0;
        int dee2 = 0; 
        boolean Oie = false;
        Scanner sc=new Scanner(System.in);
        
        while(Pion != 63){
        System.out.println("Appuyez sur Entrée pour lancer le dé…") ; 
        dee1 = sc.nextInt();
        System.out.println("Appuyez sur Entrée pour lancer le dé…") ;
        dee2 = sc.nextInt();
        
        System.out.print("Premier dee : "+dee1);
        System.out.println("    Deuxieme dee : "+dee2);
        
        Pion = Pion + dee1 + dee2;
        
        if (Pion > 63){
            Recul = Pion - 63;
            Pion = 63 - Recul ;
            System.out.println("Le joueur a recule a la case "+Pion);
            System.out.println();
        }else{
            while (Pion%9 == 0){
                Pion = Pion + dee1 + dee2;
                System.out.println("Le joueur est tombe sur la case Oie ");
                System.out.println("Le joueur avance a la case "+Pion);
                System.out.println();
                Oie = true;
            }
        if (!Oie){
            System.out.println("Le joueur a avance a la case "+Pion);
            System.out.println();
            }
        }
        Oie = false;
        }
    System.out.println("Le joueur a fini la partie");
    }   
}
