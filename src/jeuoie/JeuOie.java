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
public class JeuOie {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       Scanner sc = new Scanner(System.in);
       //int partie=0;
      // while(partie == 0){     
       String j1, j2, p; // les deux joueurs
       // les 63 cases du jeu de 1 à 63, la case 0 est la case départ
       String[] cases = new String[64];
       int p1 = 0, p2 = 0; //Position des 2 joueurs
       int ap1 = 0, ap2 = 0; // ancienne position de chaque joueur
       int d1=0, d2=0, d=0; // valeurs des deux dés
       int max = 6, min = 1, random = 0;
       int boucle = 0;
       int etat1 = 0, etat2 = 0;
       int OieEnPlus = 0;
       int Recul;
       boolean Oie = false;
       boolean debut1 = true;
       boolean debut2 = true;
       
       // demande du pseudo du joueur1
       System.out.print(" Quel est votre pseudo ? ");
       j1 = sc.nextLine();
       //j1 = "moi";
       // le second joueur sera l'ordianteur
       j2 = "ordi";
       
       // Remplissage des case du plateau de jeu
       cases[0] = "Départ";
       System.out.print("| _0 _ "+cases[0]+" ");
     
       for(int i = 1; i<64; i++){
           cases[i] = "Normal";
           if (i % 9 == 0){
               cases[i] = "Oie";
           }
           cases[19] = "Hotel";
           cases[31] = "Puits";
           cases[42] = "Labyrinthe";
           cases[58] = "Tete de mort";
           cases[63] = "Jardin";
           
       }
       
        while (OieEnPlus != 2 ){
           random = (int)( Math.random() * (63-0 + 1) + 0 );
           if (cases[random].equals("Normal") && random != 30){
               cases[random] = "Oie";
               OieEnPlus = OieEnPlus + 1;
           }
        }
       
       for(int j = 1; j<64; j++){
        if (j % 9 == 0){
           System.out.println("| _"+j+" _"+cases[j]+" |"); 
            }else{
           System.out.print("| _"+j+" _"+cases[j]+" ");
           }
       }
       
       System.out.println("");
               
       while(p1 < 63 && p2 < 63){
        
        if (p1 == 0){
            debut1 = true;
        }
           
        if (etat1 == 0){
        System.out.print(j1+" : Appuyez sur Entree pour lancer les des…") ; 
        p = sc.nextLine();
        
        d1 = (int)( Math.random() * (max-min + 1) ) + min; 
        d2 = (int)( Math.random() * (max-min + 1) ) + min;
        d = d1+d2;
        ap1 = p1;
        p1 = p1 + d;
        System.out.println(j1+" : de1 --> "+d1+" de2 --> "+d2+" = "+d);
        
        if (debut1){
            if ((d1 == 6 && d2 == 3) || (d1 == 3 && d2 == 6)){
                p1 = 26;
            }
            if ((d1 == 4 && d2 == 5) || (d1 == 5 && d2 == 4)){
                p1 = 53;
            }
        }
        debut1 = false;
        
       if (p1 > 63){
            Recul = p1 - 63;
            p1 = 63 - Recul ;
            System.out.println(j1+" recule a la case "+p1+" "+cases[p1]);
            Oie = true;
        }
            while (cases[p1].equals("Oie") && boucle < 2){
                System.out.println(j1+" est a la case : "+p1+" "+cases[p1]);
                p1 = p1 + d;
                if (p1 > 63){
                    Recul = p1 - 63;
                    p1 = 63 - Recul;
                    boucle = boucle + 1;
                    System.out.println(j1+" recule a la case "+p1+" "+cases[p1]);
                    Oie = true;
                }else{
                System.out.println(j1+" avance a la case "+p1+" "+cases[p1]);
                Oie = true;
                }
            }
            boucle = 0;
            
        if (!Oie){
            System.out.println(j1+" est a la case : "+p1+" "+cases[p1]);
            }
        
        Oie = false;
        }
        
        if (p1 == p2){
           System.out.println("Les joueurs échangent leur place");
           p2 = ap1;
           etat2 = 0;
           System.out.println(j1+" est a la case : "+p1);
           System.out.println(j2+" est a la case : "+p2);
       }
        
       if (cases[p1].equals("Puits")){
           if (etat1 == 1){
           System.out.println(j1+" doit faire un 6 avec un de ses des pour sortir");
           System.out.print(j1+" : Appuyez sur Entree pour lancer les des…") ; 
           p = sc.nextLine();
           
            d1 = (int)( Math.random() * (max-min + 1) ) + min; 
            d2 = (int)( Math.random() * (max-min + 1) ) + min;
            System.out.println(j1+" : de1 --> "+d1+" de2 --> "+d2);
            
            if (d1 == 6 || d2 == 6){
                etat1 = 0;
                System.out.println(j1+" est sorti du puits");
                p1 = p1 + d1 + d2;
                System.out.println(j1+" avance a la case "+p1+" "+cases[p1]);
            }
            
           }else{
            System.out.println(j1+" est tombe dans un puits");
            etat1 = 1;
            }
       }
       
       if (cases[p1].equals("Hotel")){
            System.out.println(j1+" se repose a l hotel");
            etat1 = etat1 + 1;
            if (etat1==2){
                System.out.println(j1+" se repose a l hotel et jouera au prochain tour");
                etat1 = 0;
            }
        }
        
       if (cases[p1].equals("Labyrinthe")){
           ap1 = p1;
           System.out.println(j1+" s est perdu et retourne a la case 30");
           p1 = 30;
       }
       
       if (p1 == p2){
           System.out.println("Les joueurs échangent leur place");
           p2 = ap1;
           etat2 = 0;
           System.out.println(j1+" est a la case : "+p1);
           System.out.println(j2+" est a la case : "+p2);
       }
        
       if (cases[p1].equals("Tete de mort")){
           System.out.println(j1+" retourne a la case depart");
           p1 = 0;
       }
       

       /////////////////////////////////////////////////////////////////////////
       
       if (p1 < 63){
       
       if (p2 == 0){
            debut2 = true;
       }
           
       if (etat2 == 0){
        //System.out.print(j2+" : Appuyez sur Entree pour lancer les des…") ; 
        //p = sc.nextLine();
        
        d1 = (int)( Math.random() * (max-min + 1) ) + min; 
        d2 = (int)( Math.random() * (max-min + 1) ) + min;
        d = d1+d2;
        ap2 = p2;
        p2 = p2 + d;
        System.out.println(j2+" : de1 --> "+d1+" de2 --> "+d2+" = "+d);
        
        if (debut2){
            if ((d1 == 6 && d2 == 3) || (d1 == 3 && d2 == 6)){
                p2 = 26;
            }
            if ((d1 == 4 && d2 == 5) || (d1 == 5 && d2 == 4)){
                p2 = 53;
            }
        }
        debut2 = false;
        
       if (p2 > 63){
            Recul = p2 - 63;
            p2 = 63 - Recul ;
            System.out.println(j2+" recule a la case "+p2+" "+cases[p2]);
            Oie = true;
        }
            while (cases[p2].equals("Oie") && boucle < 2){
                System.out.println(j2+" est a la case : "+p2+" "+cases[p2]);
                p2 = p2 + d;
                if (p2 > 63){
                    Recul = p2 - 63;
                    p2 = 63 - Recul;
                    boucle = boucle + 1;
                    System.out.println(j2+" recule a la case "+p2+" "+cases[p2]);
                    Oie = true;
                }else{
                System.out.println(j2+" avance a la case "+p2+" "+cases[p2]);
                Oie = true;
            }
            }
            boucle = 0;
            
        if (!Oie){
            System.out.println(j2+" est a la case : "+p2+" "+cases[p2]);
            }
        
        Oie = false;
        }
        
        if (p2 == p1){
           System.out.println("Les joueurs échangent leur place");
           p1 = ap2;
           etat1 = 0;
           System.out.println(j2+" est a la case : "+p2);
           System.out.println(j1+" est a la case : "+p1);
       }
       
       if (cases[p2].equals("Puits")){
           if (etat2 == 1){
           System.out.println(j2+" doit faire un 6 avec un de ses des pour sortir");
           //System.out.println(j2+" : Appuyez sur Entree pour lancer les des…") ; 
           //p = sc.nextLine();
           
            d1 = (int)( Math.random() * (max-min + 1) ) + min; 
            d2 = (int)( Math.random() * (max-min + 1) ) + min;
            System.out.println(j2+" : de1 --> "+d1+" de2 --> "+d2);
            
            if (d1 == 6 || d2 == 6){
                etat2 = 0;
                System.out.println(j2+" est sorti du puits");
                p2 = p2 + d1 + d2;
                System.out.println(j2+" avance a la case "+p2+" "+cases[p2]);
            }    
           }else{
            System.out.println(j2+" est tombe dans un puits");
            etat2 = 1;
            }
       }
       
       if (cases[p2].equals("Hotel")){
            System.out.println(j2+" se repose a l hotel");
            etat2 = etat2 + 1;
            if (etat2==2){
                System.out.println(j2+" se repose a l hotel et jouera au prochain tour");
                etat2 = 0;
            }
        }
        
       if (cases[p2].equals("Labyrinthe")){
           System.out.println(j2+" s est perdu et retourne a la case 30");
           ap2 = p2;
           p2 = 30;
       }
       
       if (p2 == p1){
           System.out.println("Les joueurs échangent leur place");
           p1 = ap2;
           etat1 = 0;
           System.out.println(j2+" est a la case : "+p2);
           System.out.println(j1+" est a la case : "+p1);
       }
        
       if (cases[p2].equals("Tete de mort")){
           System.out.println(j2+" retourne a la case depart");
           p2 = 0;
       }
       
       
       
       }
       
    }
    }
    //}
}