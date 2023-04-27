package assignment.a1;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * This class contains all the Database queries and all the methods to retrieve what you need
 *
 * THIS IS THE MOST IMPORTANT PART OF THE ASSIGNMENT
 *
 * @author Josh
 */
public class BookDatabaseManager {


    public static Connection connection = DBConfiguration.getBookDBConnection();

    /**LOAD LIBRARY*/
    public static Library loadLibrary(){

        Library library = new Library();

        /**LOAD THE BOOK LIST*/
        library.setBookList(loadBookList(connection));

        /**LOAD THE AUTHOR LIST*/
        library.setAuthorList(loadAuthorList(connection));
        //

        /**BUILD RELATIONSHIPS BETWEEN TWO LISTS*/
        buildRelationships(connection, library.getBookList(), library.getAuthorList());

        return library;
    }

    // Load up the book list without relationships

    /**
     * Load a book list from the database
     * @param connection connects you to the books database
     * @return list of book objects
     */
    private static List<Book> loadBookList(Connection connection) {
        /**CREATE A NEW LINKED LIST*/
        LinkedList<Book> bookLinkedList = new LinkedList<>();
        try{

            /**RETRIEVING RESULT SET USING SQL*/
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM " + DBConfiguration.DB_BOOKS_TITLE_TABLE_NAME + ";";
            ResultSet resultSet = statement.executeQuery(sql);

            //TODO we need to abstract the table id labels into DB Config
            while(resultSet.next()){
                bookLinkedList.add(new Book(
                        resultSet.getString(DBConfiguration.DB_BOOKS_TITLES_ISBN),
                        resultSet.getString(DBConfiguration.DB_BOOKS_TITLES_TITLE),
                        resultSet.getInt(DBConfiguration.DB_BOOKS_TITLES_EDITION_NUMBER),
                        resultSet.getString(DBConfiguration.DB_BOOKS_TITLES_COPYRIGHT)

                ));


            }
            statement.close();
        }
        catch (SQLException sqlException){
            sqlException.printStackTrace();;
        }

        return bookLinkedList;
    }

    /**LOAD THE AUTHOR LIST*/
    private static List<Author> loadAuthorList(Connection connection) {
        /**CREATE A NEW LINKED LIST*/
        LinkedList<Author> authorLinkedList = new LinkedList<>();
        try{
            /**RETRIEVING RESULT SET USING SQL*/
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM " + DBConfiguration.DB_BOOKS_AUTHORS_TABLE_NAME + ";";
            ResultSet resultSet = statement.executeQuery(sql);

            //TODO we need to abstract the table id labels into DB Config
            while(resultSet.next()){
                authorLinkedList.add(new Author(
                        resultSet.getInt(DBConfiguration.DB_BOOKS_AUTHORS_AUTHOR_ID),
                        resultSet.getString(DBConfiguration.DB_BOOKS_AUTHORS_FIRST_NAME),
                        resultSet.getString(DBConfiguration.DB_BOOKS_AUTHORS_LAST_NAME)

                ));


            }
            statement.close();
        }
        catch (SQLException sqlException){
            sqlException.printStackTrace();;
        }

        return authorLinkedList;
    }

    /** Build the relationships between these objects
     * by going through the bridge table (AuthorsISBN)
     * */
    static void buildRelationships(Connection connection, List<Book> bookList, List<Author> authorList) {

        try{
            /**RETRIEVING RESULT SET USING SQL*/
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM " + DBConfiguration.DB_BOOKS_AUTHORS_ISBN_TABLE_NAME + ";";
            ResultSet resultSet = statement.executeQuery(sql);

            //TODO we need to abstract the table id labels into DB Config
            while(resultSet.next()){
                int authorID = resultSet.getInt(DBConfiguration.DB_BOOKS_AUTHORS_AUTHOR_ID);
                String isbn = resultSet.getString(DBConfiguration.DB_BOOKS_TITLES_ISBN);
                Author author = null;
                Book book = null;

                // CLEAN UP
                for (Author a : authorList) {
                    if (a.getAuthorID() == authorID){
                        author = a;
                    }
                }

                // CLEAN UP
                for (Book b : bookList) {
                    if (b.getIsbn().equals(isbn)){
                        book = b;
                    }
                }

                author.addBook(book);
                book.addAuthor(author);

            }
            statement.close();
        }
        catch (SQLException sqlException){
            sqlException.printStackTrace();;
        }

    }
    /**PRINT LIBRARY FUNCTION*/
    public static void printLibrary(Library library){
        System.out.printf("\n\nAvailable Books:");
        library.getBookList().forEach(book -> Book.printBook(System.out, book));

        System.out.printf("\n\nAvailable Authors:");
        library.getAuthorList().forEach(author -> System.out.printf("\n%d, %s, %s",
                author.getAuthorID(), author.getFirstName(), author.getLastName()));
    }

    public static void printBooks(Library library) {
        System.out.printf("\n\nAvailable Books:");
        library.getBookList().forEach(book -> Book.printBook(System.out, book));
    }


    public static void printAuthors(Library library) {
        System.out.printf("\n\nAvailable Authors:");
        for (int i = 0; i < library.getAuthorList().size(); i++){
            System.out.printf("\n%d: %s, %s", i, library.getAuthorList().get(i).getLastName(), library.getAuthorList().get(i).getFirstName());
        }
    }


}

