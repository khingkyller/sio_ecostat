package siobergine;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;

public class Quizz {

    private String r;
    private int num;

    public Quizz(String r) {
        this.r = r;
    }

    public void ChooseQuizz()
            throws Exception {
        boolean stop = true;
        int compteur, choix;
        Scanner s;
        String d;
        while (stop == true) {
            System.out.println("que voulez vous faire?\n"
                    + "pour ajouter un quizz tapez 1\n"
                    + "pour supprimer un quizz tapez 2\n"
                    + "pour ajouter des questions a un quizz tapez 3\n"
                    + "pour enlever des questions a un quizz tapez 4\n"
                    + "pour revenir au menu precedent tapez 5\n");
            s = new Scanner(System.in);
            choix = s.nextInt();
            switch (choix) {
                case 1:
                    this.creerQuizz();
                    break;
                case 2:
                    this.supprimQ();
                    break;
                case 3:
                    this.ajoutQuestionQuizz();
                    break;
                case 4:
                    this.supprimQuestionQuizz();
                    break;
                case 5:
                    stop = false;
                    break;
                default:
                    System.out.println("vueillez entrer un choix valide!\n");
            }
        }
    }

    public void AfficheQ()
            throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://127.0.0.1/siobergine?user=root&password=password";
        Connection connect = DriverManager.getConnection(url);
        Statement st = connect.createStatement();
        ResultSet rs = st.executeQuery("Select * from siobergine.quizz");
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

    public void ajoutQuestionQuizz()
            throws Exception {

        Question p = new Question("bla");
        int codeQuestion, codeQ, duree, choose = 1;
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://127.0.0.1/visiteur_medicaux?user=root&password=password";
        Connection connect = DriverManager.getConnection(url);
        Statement st = connect.createStatement();
        Scanner s;
        do {
            this.AfficheQ();
            System.out.println("pour quelle quizz?");
            s = new Scanner(System.in);
            codeQ = s.nextInt();
            System.out.println("quelle question?");
            p.AfficheQ();
            s = new Scanner(System.in);
            codeQuestion = s.nextInt();
            System.out.println("reecrivez la bonne reponse");
            p.AfficheR(codeQuestion);
            s = new Scanner(System.in);
            String reponse = s.nextLine();
            st.execute("INSERT INTO siobergine.questionquizz (codeQuestion,codeQuizz, reponse) VALUES ('" + codeQuestion + "', '" + codeQ + "', '" + reponse + "');");
            System.out.println("voulez vous rajouter une question?\n"
                    + "si oui tapez 1\n"
                    + "si non tapez 2\n");
            s = new Scanner(System.in);
            choose = s.nextInt();
        } while (choose == 1);
        st.close();
    }

    public void supprimQuestionQuizz()
            throws Exception {

        Question p = new Question("bla");
        int codeQuestion, codeQ,choose = 1;
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://127.0.0.1/visiteur_medicaux?user=root&password=password";
        Connection connect = DriverManager.getConnection(url);
        Statement st = connect.createStatement();
        Scanner s;
        do {
            this.AfficheQ();
            System.out.println("pour quelle quizz?");
            s = new Scanner(System.in);
            codeQ = s.nextInt();
            System.out.println("quelle question?");
            p.AfficheQ();
            s = new Scanner(System.in);
            codeQuestion = s.nextInt();
            st.execute("DELETE FROM `siobergine`.`questionquizz` WHERE `codeQuestion`='" + codeQuestion + "' and codeQuizz='" + codeQ + "';");
            System.out.println("voulez vous supprimer une autre question?\n"
                    + "si oui tapez 1\n"
                    + "si non tapez 2\n");
            s = new Scanner(System.in);
            choose = s.nextInt();
        } while (choose == 1);
        st.close();
    }

    public void creerQuizz()
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
        System.out.println("quelle est le nom du createur du quizz?");
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
        st.execute("INSERT INTO siobergine.quizz (createur, duree, date, theme) VALUES ('" + createur + "', '" + duree + "', '" + date + "', '" + theme + "');");
        st.close();
    }

    public void supprimQ()
            throws Exception {
        int choose = 1;
        do {
            this.AfficheQ();
            System.out.println("quelle quizz voulez supprimer?");
            Scanner s;
            s = new Scanner(System.in);
            int codeQ = s.nextInt();
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://127.0.0.1/siobergine?user=root&password=password";
            Connection connect = DriverManager.getConnection(url);
            Statement st = connect.createStatement();
            st.execute("DELETE FROM `siobergine`.`questionquizz` WHERE `codeQuizz`='" + codeQ + "';");
            st.execute("DELETE FROM `siobergine`.`quizz` WHERE `codeQuizz`='" + codeQ + "';");
            st.close();
            System.out.println("voulez vous supprimer un autre quizz ?\n"
                    + "si oui tapez 1\n"
                    + "si non tapez 2\n");
            s = new Scanner(System.in);
            choose = s.nextInt();
        } while (choose == 1);
    }

}
