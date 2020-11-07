package org.itstep;

import java.sql.*;

/**
 * Hello world!
 *
 */
public class App1
{
    public static final String URL = "jdbc:mysql://localhost/movies";
    public static final String USER = "root";
    public static final String PASSWORD = "";

    public static void main( String[] args )
    {
        try(Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("select m.Title, g.GenereName, a.FirstName , a.LastName, d.FirstName, d.LastName\n" +
                    "from Movies m\n" +
                    "join MovieGenres mg\n" +
                    "using (MovieID)\n" +
                    "join Genres g\n" +
                    "using (GenreID)\n" +
                    "join MovieActor ma\n" +
                    "using (MovieID)\n" +
                    "join Actors a\n" +
                    "using (ActorID)\n" +
                    "join Directors d\n" +
                    "using (DirectorID);");
            System.out.printf("%30s|%61s|%61s|\n", "Movie", "Actors", "Directors");
            for (int i = 1; i < 186; i++) {
                System.out.print("_");
            }
            System.out.println();
            ResultSetMetaData md = rs.getMetaData();
            for (int i = 0; i < md.getColumnCount(); i++) {
                System.out.printf("%30s", md.getColumnName(i + 1));
                if (i != md.getColumnCount() - 1) System.out.print("|");
            }
            System.out.println();
            for (int i = 1; i < 186; i++) {
                System.out.print("_");
            }
            System.out.println();
            while (rs.next()){
                System.out.printf("%30s|%30s|%30s|%30s|%30s|%30s\n",
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
