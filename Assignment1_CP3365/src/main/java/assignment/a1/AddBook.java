package assignment.a1;

import java.util.Scanner;
import java.sql.*;

import static assignment.a1.BookDatabaseManager.buildRelationships;

public class AddBook {
    public static void addNewBook() throws SQLException {
        Scanner input = new Scanner(System.in);
        Connection connection = DBConfiguration.getBookDBConnection();
        Library library = BookDatabaseManager.loadLibrary();

        // Display AuthorList
        for (int i = 0; i < library.getAuthorList().size(); i++) {
            System.out.printf("\n%d: %s, %s", i+1 , library.getAuthorList().get(i).getLastName(), library.getAuthorList().get(i).getFirstName());
        }

        /** PROMPT USER TO CHOOSE AUTHOR */
        System.out.printf("\nPlease pick an author number\n");
        int authorChoice = input.nextInt();

        /** PROMPT USER FOR OTHER ATTRIBUTES */
        System.out.printf("\nPlease input isbn code\n");
        String isbn = input.next();

        System.out.printf("\nPlease input book title\n");
        input.nextLine();
        String title = input.nextLine();

        System.out.printf("\nPlease input edition number\n");
        int editionNumber = input.nextInt();

        System.out.printf("\nPlease input copyright\n");
        String copyright = input.next();

        /** PASS THE USER INPUT TO THE BOOK CLASS */
        Book book = new Book(isbn, title, editionNumber, copyright);

        /** MAKE THE RELATIONSHIP */
        book.addAuthor(library.getAuthorList().get(authorChoice - 1));

        /** GET THE BOOK LIST, ADD NEW ENTRY, AND PRINT OUT UPDATED LIBRARY */
        library.getBookList().add(book);
        library.getAuthorList().forEach(author -> System.out.printf("\n\t%s, %s", author.getLastName(), author.getFirstName()));
        BookDatabaseManager.printBooks(library);

        /** USING PREPARED STATEMENT TO UPDATE SQL DATABASE */
        String sql = "INSERT titles SET isbn = ?, title = ?, editionNumber = ?, copyright = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,isbn);
        preparedStatement.setString(2,title);
        preparedStatement.setInt(3,editionNumber);
        preparedStatement.setString(4,copyright);
        preparedStatement.executeUpdate();

        /** USING PREPARED STATEMENT TO UPDATE BRIDGE TABLE */
        String sql2 = "INSERT authorisbn SET authorID = ?, isbn = ?";
        PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
        preparedStatement2.setInt(1, authorChoice);
        preparedStatement2.setString(2, isbn);
        preparedStatement2.executeUpdate();

        /** CALL THE BUILD RELATIONSHIPS METHOD AGAIN TO UPDATE THE RELATIONSHIPS FOR NEXT MENU CHOICE */
        buildRelationships(connection, library.getBookList(), library.getAuthorList());


    }
}
