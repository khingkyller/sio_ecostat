package siobergine;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;

public class Question {

    private String r;
    private int num;

    public Question(String r) {
        this.r = r;
    }

    public void ChooseQuestion()
            throws Exception {
        boolean stop = true;
        Scanner s;
        int choix;
        String d;
        while (stop == true) {
            System.out.println("que voulez vous faire?\n"
                    + "pour creer une question tapez 1\n"
                    + "pour supprimer une question tapez 2\n"
                    + "pour ajouter des reponses a une question tapez 3\n"
                    + "pour enlever des reponses a une question tapez 4\n"
                    + "pour revenir au menu precedent tapez 5\n");
            s = new Scanner(System.in);
            choix = s.nextInt();
            switch (choix) {
                case 1:
                    this.insertIntoQuestion();
                    break;
                case 2:
                    this.supprimerQuestion();
                    break;
                case 3:
                    this.insertIntoReponse();
                    break;
                case 4:
                    this.suprimReponse();
                    break;
                case 5:
                    stop = false;
                    break;
                default:
                    System.out.println("vueillez entrer un choix valide!\n");
            }
        }
    }

    public void insertIntoQuestion()
            throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://127.0.0.1/siobergine?user=root&password=password";
        Connection connect = DriverManager.getConnection(url);
        Statement st = connect.createStatement();
        Scanner s;
        String d;
        int choose = 1;
        do {
            System.out.println("entrez une question");
            s = new Scanner(System.in);
            d = s.nextLine();            //d est le nom entrée au clavier
            st.execute("INSERT INTO `siobergine`.`question` (`question`) VALUES ('" + d + "');");
            st.close();
            System.out.println("voulez vous rajouter une question?\n"
                    + "si oui tapez 1\n"
                    + "si non tapez 2\n");
            s = new Scanner(System.in);
            choose = s.nextInt();
        } while (choose == 1);
    }

    public void insertIntoReponse()
            throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://127.0.0.1/siobergine?user=root&password=password";
        Connection connect = DriverManager.getConnection(url);
        Statement st = connect.createStatement();
        Scanner s;
        String rep;
        int choose = 1, compteur, codeQ;
        do {
            this.AfficheQ();
            System.out.println("a quelle question voulez vous attibuez vos réponse?");
            s = new Scanner(System.in);
            codeQ = s.nextInt();
            System.out.println("combien de reponse aurez vous?");
            compteur = s.nextInt();
            for (int i = 1; i <= compteur; i = i) {
                System.out.println("entrez votre reponse numero " + i);
                s = new Scanner(System.in);
                rep = s.nextLine();
                st.execute("INSERT INTO `siobergine`.`Reponse` (`codeQuestion`,`reponse`) VALUES ('" + codeQ + "','" + rep + "');");
                i++;
            }
            System.out.println("voulez vous rajouter des reponses?\n"
                    + "si oui tapez 1\n"
                    + "si non tapez 2\n");
            s = new Scanner(System.in);
            choose = s.nextInt();
        } while (choose == 1);
        st.close();
    }

    public void Vue(String select, String select2)
            throws Exception {
        String Select = select;
        String Select2 = select2;
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://127.0.0.1/siobergine?user=root&password=password";
        Connection connect = DriverManager.getConnection(url);
        Statement st = connect.createStatement();
        ResultSet rs = st.executeQuery("Select " + Select + " from siobergine." + Select2);
        while (rs.next()) {
            System.out.print(rs.getString(1) + " ");
        }
        rs.close();
        st.close();
    }

    public void AfficheQ()
            throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://127.0.0.1/siobergine?user=root&password=password";
        Connection connect = DriverManager.getConnection(url);
        Statement st = connect.createStatement();
        ResultSet rs = st.executeQuery("Select * from siobergine.question");
        while (rs.next()) {
            System.out.print(rs.getString(1) + " ");
            System.out.println(rs.getString(2) + "\n");
        }
        rs.close();
        st.close();
    }

    public void AfficheR(int q)
            throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://127.0.0.1/siobergine?user=root&password=password";
        Connection connect = DriverManager.getConnection(url);
        Statement st = connect.createStatement();
        ResultSet rs = st.executeQuery("Select codeReponse,reponse from siobergine.reponse where codeQuestion=" + q);
        while (rs.next()) {
            System.out.print(rs.getString(1) + " ");
            System.out.print(rs.getString(2) + "\n ");
        }
        rs.close();
        st.close();

    }

    public void supprimerQuestion()
            throws Exception {
        int choose = 1;
        do {
            this.AfficheQ();
            System.out.println("quelle question voulez supprimer?");
            Scanner s;
            s = new Scanner(System.in);
            int codeQ = s.nextInt();
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://127.0.0.1/siobergine?user=root&password=password";
            Connection connect = DriverManager.getConnection(url);
            Statement st = connect.createStatement();
            st.execute("DELETE FROM `siobergine`.`reponse` WHERE `codeQuestion`='" + codeQ + "';");
            st.execute("DELETE FROM `siobergine`.`questionquizz` WHERE `codeQuestion`='" + codeQ + "';");
            st.execute("DELETE FROM `siobergine`.`question` WHERE `codeQuestion`='" + codeQ + "';");
            st.close();
            System.out.println("voulez vous supprimer une autre question ?\n"
                    + "si oui tapez 1\n"
                    + "si non tapez 2\n");
            s = new Scanner(System.in);
            choose = s.nextInt();
        } while (choose == 1);
    }

    public void suprimReponse()
            throws Exception {
        int choose1 = 1, choose2 = 1;
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://127.0.0.1/siobergine?user=root&password=password";
        Connection connect = DriverManager.getConnection(url);
        Statement st = connect.createStatement();
        Scanner s;
        do {
            do {

                this.AfficheQ();
                System.out.println("de quelle question voulez supprimer les reponses?");
                s = new Scanner(System.in);
                int codeQ = s.nextInt();
                this.AfficheR(codeQ);
                System.out.println("quelles reponses voulez vous supprimer?");
                s = new Scanner(System.in);
                int codeRep = s.nextInt();
                st.execute("DELETE FROM `siobergine`.`reponse` WHERE `codeQuestion`='" + codeQ + "' and codeReponse='" + codeRep + "';");
                System.out.println("voulez vous supprimer une autre reponse de cette question?\n"
                        + "si oui tapez 1\n"
                        + "si non tapez 2\n");
                s = new Scanner(System.in);
                choose1 = s.nextInt();
            } while (choose1 == 1);
            System.out.println("voulez vous supprimer une reponse d'une autre question?\n"
                    + "si oui tapez 1\n"
                    + "si non tapez 2\n");
            s = new Scanner(System.in);
            choose2 = s.nextInt();
        } while (choose2 == 1);
        st.close();
    }
}
