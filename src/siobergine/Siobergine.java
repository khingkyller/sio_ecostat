package siobergine;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;

public class Siobergine {

    public static void main(String[] args) throws Exception {

        Class.forName("org.postgresql.Driver");
        boolean stop = true;
        String d;
        int num;
        Scanner s;
        int compteur, choix, comptCase;
        Question question = new Question("bla");
        Quizz quizz=new Quizz("");
        Sondage sondage = new Sondage("");
        Enquete enquete=new Enquete("");
        
        d = "lol";

        while (stop == true) {
            System.out.println("que voulez vous faire?\n"
                    + "pour ajouter ou supprimer une question ou des reponses tapez 1\n"
                    + "pour ajouter ou supprimer des reponses tapez 2\n"
                    + "pour ajouter ou supprimer un sondage tapez 3\n"
                    + "pour ajouter ou supprimer une enquete tapez 4\n"
                    + "pour stopper ce menu tapez 5\n");
            s = new Scanner(System.in);
            choix = s.nextInt();
            switch (choix) {
                case 1:question.ChooseQuestion(); ;break;
                case 2:quizz.ChooseQuizz(); ;break;
                case 3:sondage.ChooseSondage(); ;break;
                case 4:enquete.ChooseEnquete(); ;break;
                case 5:stop = false;break;
                default:comptCase = 0;System.out.println("vueillez entrer un choix valide!\n");
            }
            
        }
            
 /*           
            System.out.println("que voulez vous faire?\n"
                    + "pour ajouter des valeurs à une table tapez 3\n"
                    + "pour voir le contenu d'une table taper 4\n"
                    + "pour stoper ce menu tapez 5\n");
            s = new Scanner(System.in);
            choix = s.nextInt();
            switch (choix) {
                case 3:comptCase = 103;break;
                case 4:comptCase = 104;break;
                case 5:stop = false;comptCase = 0;break;
                default:comptCase = 0;System.out.println("vueillez entrer un choix valide!\n");
            }
            if (comptCase == 101) {
                System.out.println("entrez le nom de la table a ajouter");
            }
            if (comptCase == 103) {
                System.out.println("entrez une question");
                s = new Scanner(System.in);
                d = s.nextLine();            //d est le nom entrée au clavier
                p.insertIntoQuestion(d);
                System.out.println("a quelle question voulez vous attibuez vos réponse?");
                num = s.nextInt();
                System.out.println("combien de reponse aurez vous?");
                compteur = s.nextInt();
                for (int i = 1; i <= compteur; i = i) {
                    System.out.println("entrez votre reponse numero " + i);
                    s = new Scanner(System.in);
                    d = s.nextLine();
                    p.insertIntoReponse(num, d);
                    i++;
                }
            }
            if (comptCase == 104) {
                p.Test();
                System.out.println("que voulez vous selectionner");
                s = new Scanner(System.in);
                String Select = s.nextLine();
                System.out.println("dans quelle table?");
                s = new Scanner(System.in);
                String Select2 = s.nextLine();
                System.out.println("");
                p.Vue(Select, Select2);
            }    */
        }


}
