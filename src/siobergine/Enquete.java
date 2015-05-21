package siobergine;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;

public class Enquete {

    private String r;

    public Enquete(String r) {
        this.r = r;
    }

    public void ChooseEnquete()
            throws Exception {
        boolean stop = true;
        int compteur, choix;
        Scanner s;
        String d;
        while (stop == true) {
            System.out.println("que voulez vous faire?\n"
                    + "pour ajouter une enquete tapez 1\n"
                    + "pour supprimer une enquete tapez 2\n"
                    + "pour revenir au menu precedent tapez 3\n");
            s = new Scanner(System.in);
            choix = s.nextInt();
            switch (choix) {
                case 1:
                    this.creerEnquete();
                    break;
                case 2:
                    this.supprimE();
                    break;
                case 3:
                    stop = false;
                    break;
                default:
                    System.out.println("vueillez entrer un choix valide!\n");
            }
        }
    }

    public void AfficheE()
            throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://127.0.0.1/siobergine?user=root&password=password";
        Connection connect = DriverManager.getConnection(url);
        Statement st = connect.createStatement();
        ResultSet rs = st.executeQuery("Select * from siobergine.enquete");
        while (rs.next()) {
            System.out.print(rs.getString(1) + " ");
            System.out.print(rs.getString(2) + " ");
            System.out.print(rs.getString(3) + " ");
            System.out.println(rs.getString(4) + "\n ");
        }
        rs.close();
        st.close();

    }

    public void AfficheOrdreEnquete()
            throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://127.0.0.1/siobergine?user=root&password=password";
        Connection connect = DriverManager.getConnection(url);
        Statement st = connect.createStatement();
        ResultSet rs = st.executeQuery("SELECT ordre,codeQuestion,codeEnquete FROM siobergine.questionenquete order by ordre asc;");
        while (rs.next()) {
            System.out.print(rs.getString(1) + " ");
            System.out.print(rs.getString(2) + " ");
            System.out.println(rs.getString(3) + "\n ");
        }
        rs.close();
        st.close();
    }

    public void creerEnquete()
            throws Exception {
        Question p = new Question("bla");
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://127.0.0.1/visiteur_medicaux?user=root&password=password";
        Connection connect = DriverManager.getConnection(url);
        Statement st = connect.createStatement();
        String createur, theme, date, dateP;
        int codeQuestion, codeE, ordre, choose = 1, duree;
        Scanner s;
        String d;
        System.out.println("quelle est le nom du createur de l'enquete?");
        s = new Scanner(System.in);
        createur = s.nextLine();
        System.out.println("quelle est la date de creation? au format AAAA-MM-JJ");
        s = new Scanner(System.in);
        date = s.nextLine();
        System.out.println("quelle en sera le theme?");
        s = new Scanner(System.in);
        theme = s.nextLine();
        st.execute("INSERT INTO siobergine.enquete (createur, date, theme) VALUES ('" + createur + "', '" + date + "', '" + theme + "');");
        do {
            p.AfficheQ();
            System.out.println("quelle question?");
            s = new Scanner(System.in);
            codeQuestion = s.nextInt();
            this.AfficheOrdreEnquete();
            System.out.println("quelle sera son ordre?");
            s = new Scanner(System.in);
            ordre = s.nextInt();
            System.out.println("combien de temps restera t'il?");
            s = new Scanner(System.in);
            duree = s.nextInt();
            System.out.println("quelle est la date de creation? au format AAAA-MM-JJ");
            s = new Scanner(System.in);
            dateP = s.nextLine();
            ResultSet rs = st.executeQuery("Select codeEnquete from siobergine.enquete where createur = '" + createur + "' and theme='" + theme + "' and date='" + date + "'");
            rs.first();// positionne le curseur sur la première ligne
            codeE = rs.getInt(1);
            st.execute("INSERT INTO `siobergine`.`questionenquete` (`codeQuestion`, `codeEnquete`, `ordre`, `duree`, `dateDePublication`) VALUES ('" + codeQuestion + "', '" + codeE + "', '" + ordre + "', '" + duree + "', '" + dateP + "');");
            System.out.println("voulez vous rajouter une question?\n"
                    + "si oui tapez 1\n"
                    + "si non tapez 2\n");
            s = new Scanner(System.in);
            choose = s.nextInt();
        } while (choose == 1);
        st.close();

    }

    public void supprimE()
            throws Exception {
        int choose = 1;
        do {
            this.AfficheE();
            System.out.println("quelle enquete voulez supprimer?");
            Scanner s;
            s = new Scanner(System.in);
            int codeE = s.nextInt();
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://127.0.0.1/siobergine?user=root&password=password";
            Connection connect = DriverManager.getConnection(url);
            Statement st = connect.createStatement();
            st.execute("DELETE FROM `siobergine`.`questionenquete` WHERE `codeEnquete`='" + codeE + "';");
            st.execute("DELETE FROM `siobergine`.`enquete` WHERE `codeEnquete`='" + codeE + "';");
            st.close();
            System.out.println("voulez vous supprimer une autre enquete ?\n"
                    + "si oui tapez 1\n"
                    + "si non tapez 2\n");
            s = new Scanner(System.in);
            choose = s.nextInt();
        } while (choose == 1);
    }

    public void creerQuestionEnquete()
            throws Exception {
        Question p = new Question("bla");
        int codeQuestion, codeE, duree, choose = 1, ordre;
        String dateP;
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://127.0.0.1/visiteur_medicaux?user=root&password=password";
        Connection connect = DriverManager.getConnection(url);
        Statement st = connect.createStatement();
        Scanner s;
        do {
            this.AfficheE();
            System.out.println("pour quelle enquete?");
            s = new Scanner(System.in);
            codeE = s.nextInt();
            p.AfficheQ();
            System.out.println("quelle question?");
            s = new Scanner(System.in);
            codeQuestion = s.nextInt();
            this.AfficheOrdreEnquete();
            System.out.println("quelle sera son ordre?");
            s = new Scanner(System.in);
            ordre = s.nextInt();
            System.out.println("combien de temps restera t'il?");
            s = new Scanner(System.in);
            duree = s.nextInt();
            System.out.println("quelle est la date de creation? au format AAAA-MM-JJ");
            s = new Scanner(System.in);
            dateP = s.nextLine();
            st.execute("INSERT INTO `siobergine`.`questionenquete` (`codeQuestion`, `codeEnquete`, `ordre`, `duree`, `dateDePublication`) VALUES ('" + codeQuestion + "', '" + codeE + "', '" + ordre + "', '" + duree + "', '" + dateP + "');");
            System.out.println("voulez vous rajouter une question?\n"
                    + "si oui tapez 1\n"
                    + "si non tapez 2\n");
            s = new Scanner(System.in);
            choose = s.nextInt();
        } while (choose == 1);
        st.close();
    }

    public void supprimQuestionEnquete()
            throws Exception {
        Question p = new Question("bla");
        int codeQuestion, codeE, choose = 1;
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://127.0.0.1/visiteur_medicaux?user=root&password=password";
        Connection connect = DriverManager.getConnection(url);
        Statement st = connect.createStatement();
        Scanner s;
        do {
            this.AfficheE();
            System.out.println("pour quelle enquete?");
            s = new Scanner(System.in);
            codeE = s.nextInt();
            System.out.println("quelle question?");
            p.AfficheQ();
            s = new Scanner(System.in);
            codeQuestion = s.nextInt();
            st.execute("DELETE FROM `siobergine`.`questionenquete` WHERE `codeQuestion`='" + codeQuestion + "' and codeEnquete='" + codeE + "';");
            System.out.println("voulez vous supprimer une autre question?\n"
                    + "si oui tapez 1\n"
                    + "si non tapez 2\n");
            s = new Scanner(System.in);
            choose = s.nextInt();
        } while (choose == 1);
        st.close();
    }

}
