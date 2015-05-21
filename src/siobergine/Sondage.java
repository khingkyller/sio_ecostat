package siobergine;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;

public class Sondage {

    private String r;

    public Sondage(String r) {
        this.r = r;
    }

    public void ChooseSondage()
            throws Exception {
        boolean stop = true;
        int compteur, choix;
        Scanner s;
        String d;
        while (stop == true) {
            System.out.println("que voulez vous faire?\n"
                    + "pour ajouter un sondage tapez 1\n"
                    + "pour supprimer un sondage tapez 2\n"
                    + "pour revenir au menu precedent tapez 5\n");
            s = new Scanner(System.in);
            choix = s.nextInt();
            switch (choix) {
                case 1:
                    this.creerSondage();
                    break;
                case 2:
                    this.supprimS();
                    break;
                case 3:
                    stop = false;
                    break;
                default:
                    System.out.println("vueillez entrer un choix valide!\n");
            }
        }
    }

    public void AfficheS()
            throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://127.0.0.1/siobergine?user=root&password=password";
        Connection connect = DriverManager.getConnection(url);
        Statement st = connect.createStatement();
        ResultSet rs = st.executeQuery("Select * from siobergine.sondage");
        while (rs.next()) {
            System.out.print(rs.getString(1) + " ");
            System.out.print(rs.getString(2) + " ");
            System.out.print(rs.getString(3) + " ");
            System.out.print(rs.getString(4) + " ");
            System.out.println(rs.getString(5) + "\n ");
        }
        rs.close();
        st.close();

    }

    public void creerSondage()
            throws Exception {
        Question p = new Question("bla");
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://127.0.0.1/visiteur_medicaux?user=root&password=password";
        Connection connect = DriverManager.getConnection(url);
        Statement st = connect.createStatement();
        String createur, theme, date;
        int codeQuestion, codeQ, duree, choose = 1;
        Scanner s;
        String d;
        System.out.println("quelle est le nom du createur du sondage?");
        s = new Scanner(System.in);
        createur = s.nextLine();
        System.out.println("quelle est la date de creation? au format AAAA-MM-JJ");
        s = new Scanner(System.in);
        date = s.nextLine();
        System.out.println("pour combien de temps est il créé?");
        s = new Scanner(System.in);
        duree = s.nextInt();
        System.out.println("quelle en sera le theme?");
        s = new Scanner(System.in);
        theme = s.nextLine();
        st.execute("INSERT INTO siobergine.sondage (createur, duree, date, theme) VALUES ('" + createur + "', '" + duree + "', '" + date + "', '" + theme + "');");
        do {
            System.out.println("quelle question?");
            p.AfficheQ();
            s = new Scanner(System.in);
            codeQuestion = s.nextInt();
            ResultSet rs = st.executeQuery("Select codeSondage from siobergine.sondage where createur = '" + createur + "' and theme='" + theme + "' and duree='" + duree + "'");
            rs.first();// positionne le curseur sur la première ligne
            codeQ = rs.getInt(1);
            st.execute("INSERT INTO siobergine.questionsondage (codeQuestion,codeSondage) VALUES ('" + codeQuestion + "', '" + codeQ + "');");
            System.out.println("voulez vous rajouter une question?\n"
                    + "si oui tapez 1\n"
                    + "si non tapez 2\n");
            s = new Scanner(System.in);
            choose = s.nextInt();
        } while (choose == 1);
        st.close();

    }

    public void supprimS()
            throws Exception {
        int choose = 1;
        do {
            this.AfficheS();
            System.out.println("quelle sondage voulez supprimer?");
            Scanner s;
            s = new Scanner(System.in);
            int codeS = s.nextInt();
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://127.0.0.1/siobergine?user=root&password=password";
            Connection connect = DriverManager.getConnection(url);
            Statement st = connect.createStatement();
            st.execute("DELETE FROM `siobergine`.`questionsondage` WHERE `codeSondage`='" + codeS + "';");
            st.execute("DELETE FROM `siobergine`.`sondage` WHERE `codeSondage`='" + codeS + "';");
            st.close();
            System.out.println("voulez vous supprimer un autre sondage ?\n"
                    + "si oui tapez 1\n"
                    + "si non tapez 2\n");
            s = new Scanner(System.in);
            choose = s.nextInt();
        } while (choose == 1);
    }

    public void creerQuestionSondage()
            throws Exception {
        Question p = new Question("bla");
        int codeQuestion, codeQ, duree, choose = 1;
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://127.0.0.1/visiteur_medicaux?user=root&password=password";
        Connection connect = DriverManager.getConnection(url);
        Statement st = connect.createStatement();
        Scanner s;
        do {
            this.AfficheS();
            System.out.println("pour quelle sondage?");
            s = new Scanner(System.in);
            codeQ = s.nextInt();
            System.out.println("quelle question?");
            p.AfficheQ();
            s = new Scanner(System.in);
            codeQuestion = s.nextInt();
            st.execute("INSERT INTO siobergine.questionsondage (codeQuestion,codeSondage) VALUES ('" + codeQuestion + "', '" + codeQ + "');");
            System.out.println("voulez vous rajouter une question?\n"
                    + "si oui tapez 1\n"
                    + "si non tapez 2\n");
            s = new Scanner(System.in);
            choose = s.nextInt();
        } while (choose == 1);
        st.close();
    }

    public void supprimQuestionSondage()
            throws Exception {
                Question p = new Question("bla");
        int codeQuestion, codeQ, choose = 1;
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://127.0.0.1/visiteur_medicaux?user=root&password=password";
        Connection connect = DriverManager.getConnection(url);
        Statement st = connect.createStatement();
        Scanner s;
        do {
            this.AfficheS();
            System.out.println("pour quelle sondage?");
            s = new Scanner(System.in);
            codeQ = s.nextInt();
            System.out.println("quelle question?");
            p.AfficheQ();
            s = new Scanner(System.in);
            codeQuestion = s.nextInt();
            st.execute("DELETE FROM `siobergine`.`questionsondage` WHERE `codeQuestion`='" + codeQuestion + "' and codeSondage='" + codeQ + "';");
            System.out.println("voulez vous supprimer une autre question?\n"
                    + "si oui tapez 1\n"
                    + "si non tapez 2\n");
            s = new Scanner(System.in);
            choose = s.nextInt();
        } while (choose == 1);
        st.close();
    }

    }
