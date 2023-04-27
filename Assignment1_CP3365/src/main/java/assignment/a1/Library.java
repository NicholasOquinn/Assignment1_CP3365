package assignment.a1;

import java.util.List;
import java.util.LinkedList;

public class Library {

    private List<Book> bookList;
    private List<Author> authorList;

    public Library() {
        this.bookList = new LinkedList<>();
        this.authorList = new LinkedList<>();
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public List<Author> getAuthorList() {
        return authorList;
    }

    public void setAuthorList(List<Author> authorList) {
        this.authorList = authorList;
    }
}
