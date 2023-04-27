package assignment.a1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class AddAuthor {

    /**ADD A NEW AUTHOR TO THE DATABASE*/
    public static void addNewAuthor() throws SQLException {

        /**CREATE A NEW SCANNER, CONNECTION, AND LOAD LIBRARY*/
        Scanner input = new Scanner(System.in);
        Connection connection = DBConfiguration.getBookDBConnection();
        Library library = BookDatabaseManager.loadLibrary();

        /**Prompt the user to get Author firstName & lastName*/
        System.out.printf("\nPlease input Authors first name:\n");
        String firstName = input.next();

        System.out.printf("\nPlease input Authors last name:\n");
        String lastName = input.next();

        /**GETTING THE MOST RECENTLY AVAILABLE AUTHOR ID*/
        int id = library.getAuthorList().get(library.getAuthorList().size()-1).getAuthorID() + 1;

        /**PASS THE NEW AUTHOR ATTRIBUTES TO THE CONSTRUCTOR*/
        Author author = new Author(id,firstName,lastName);
        library.getAuthorList().sort((a1, a2) -> (Integer.compare(a1.getAuthorID(), a2.getAuthorID())));

        library.getAuthorList().add(author);
        for (int i = 0; i < library.getAuthorList().size(); i++) {
            System.out.printf("\n%d: %s, %s", i, library.getAuthorList().get(i).getLastName(), library.getAuthorList().get(i).getFirstName());
        }

        /**USE A PREPARED STATEMENT TO UPDATE THE DATABASE*/
        String sql = "INSERT authors SET authorID = ?, firstName = ?, lastName = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        preparedStatement.setString(2, firstName);
        preparedStatement.setString(3, lastName);
        preparedStatement.executeUpdate();

        preparedStatement.close();
    }
}
