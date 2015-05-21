package siobergine;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;
import java.sql.ResultSet;

public class main_test {

    public static void main() throws Exception {

//        Class.forName("org.postgresql.Driver");
//        boolean stop = true;
//        String d;
//        int num;
//        Scanner s;
//        int compteur, choix, comptCase;
//        sql p = new sql("bla");
//        d = "lol";
////        p.SelectQuestion(d);
//
//        while (stop == true) {
////            String d;
////            int num;
////            Scanner s;
////            int compteur, choix, comptCase;
//            /*System.out.println("entre le nom de ta table");
//             s = new Scanner(System.in);
//             d = s.next();            //d est le nom entrée au clavier*/
////            sql p = new sql("bla");         //création d'un constructeur
//            //p.create(d);                    //appel de la methode create qui crée des tables
//            System.out.println("que voulez vous faire?\n"
//                    + "pour ajouter une table tapez 1\n"
//                    + "pour supprimer une table tapez 2\n"
//                    + "pour modifier une table tapez 3\n"
//                    + "pour voir le contenu d'une table taper 4\n"
//                    + "pour stoper ce menu tapez 5\n");
//            s = new Scanner(System.in);
//            choix = s.nextInt();
//            switch (choix) {
//                case 1:
//                    comptCase = 101;
//                    break;
//                case 2:
//                    comptCase = 102;
//                    break;
//                case 3:
//                    comptCase = 103;
//                    break;
//                case 4:
//                    stop=false;comptCase=104;
//                    break;
//                case 5:
//                    stop=false;comptCase=0;
//                    break;
//                default:
//                    comptCase = 0;
//                    System.out.println("vueillez entrer un choix valide!\n");
//            }
//            if (comptCase == 101) {
//                System.out.println("entrez le nom de la table a ajouter");
//            }
//            if (comptCase == 103) {
//                System.out.println("entrez une question");
//                s = new Scanner(System.in);
//                d = s.nextLine();            //d est le nom entrée au clavier
//                p.insertIntoQuestion(d);
//                System.out.println("a quelle question voulez vous attibuez vos réponse?");
//                num = s.nextInt();
//                System.out.println("combien de reponse aurez vous?");
//                compteur = s.nextInt();
//                for (int i = 1; i <= compteur; i = i) {
//                    System.out.println("entrez votre reponse numero " + i);
//                    s = new Scanner(System.in);
//                    d = s.nextLine();
//                    p.insertIntoReponse(num, d);
//                    i++;
//                }
//            }
//            if(comptCase==104){
//                p.SelectQuestion(d);
//            }
//        }
    }
}
