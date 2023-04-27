package assignment.a1;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class BookDBTester {

    public static void main(String[] args) {
        System.out.println("fun with Books DB Testing!");

        try{
            //Check DBConfiguration Class if you have any issues with this connection
            Connection connection = DriverManager.getConnection(DBConfiguration.DB_URL + DBConfiguration.DB_BOOKS, DBConfiguration.DB_USER, DBConfiguration.DB_PASSWORD);
            List<Book> bookList = loadBookList(connection);
            bookList.forEach(book -> System.out.printf("\n%s, %s, %d, %s", book.getIsbn(), book.getTitle(), book.getEditionNumber(), book.getCopyright()));
            //printEmployeesTable(connection);
        }
        catch (SQLException sqlException){
            sqlException.printStackTrace();;
        }

    }

    public static List<Book> loadBookList(Connection connection) {
        LinkedList<Book> bookLinkedList = new LinkedList<>();
        try{
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM titles;";
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

}
