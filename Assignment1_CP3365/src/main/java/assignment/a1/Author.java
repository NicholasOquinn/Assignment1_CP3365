package assignment.a1;

import java.util.LinkedList;
import java.util.List;

public class Author {

    //TODO write this class - see lecture Jan 25th if you are stuck
    private int authorID;
    private String firstName;
    private String lastName;
    private List<Book> bookList;

    /**CONSTRUCTOR*/
    public Author(int authorID, String firstName, String lastName) {
        this.authorID = authorID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bookList = new LinkedList<>();
    }

    /**GETTERS AND SETTERS*/
    public int getAuthorID() {
        return authorID;
    }

    public void setAuthorID(int authorID) {
        this.authorID = authorID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void addBook(Book book){
        this.bookList.add(book);
    }


}