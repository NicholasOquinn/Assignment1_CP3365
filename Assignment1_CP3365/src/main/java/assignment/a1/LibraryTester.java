package assignment.a1;

import java.util.Comparator;

public class LibraryTester {

    public static void main(String[] args) {
//        System.out.println("Fun with Library");
//        Library library = BookDatabaseManager.loadLibrary();
//
//        System.out.println("Books");
//        System.out.println("-------------------");
//        library.getBookList().forEach(book -> Book.printBook(System.out, book));
//
//        System.out.println("Authors");
//        System.out.println("-------------------");
//        library.getAuthorList().forEach(author -> System.out.printf("\n%d, %s, %s", author.getAuthorID(), author.getFirstName(), author.getLastName()));
//
//        library.getAuthorList().get(0).setFirstName("Smelly Pants");
//
//        System.out.println("Books");
//        System.out.println("-------------------");
//        library.getBookList().forEach(book -> Book.printBook(System.out, book));
//
//        System.out.println("Authors");
//        System.out.println("-------------------");
//        library.getAuthorList().forEach(author -> System.out.printf("\n%d, %s, %s", author.getAuthorID(), author.getFirstName(), author.getLastName()));

        System.out.println("Fun with Library");
        Library library = BookDatabaseManager.loadLibrary();
        printLibrary(library);

        addAuthorExample(library);
        addBookExample(library);
        printLibrary(library);



    }

    public static void addBookExample(Library library){
        Book book = new Book("09876", "Nicks Book", 101, "2020");

        book.addAuthor(library.getAuthorList().get(1));

        library.getBookList().add(book);

    }

    public static void addAuthorExample(Library library){
        // get the next id
        // you could check the size of the list
        // or you could order the authorList by ID and then use the next - preferred


        library.getAuthorList().sort((a1, a2) -> (Integer.compare(a1.getAuthorID(), a2.getAuthorID())));

        library.getAuthorList().forEach(author -> System.out.printf("\n%d %s, %s",
                author.getAuthorID(), author.getLastName(), author.getFirstName()));

        int id = library.getAuthorList().get(library.getAuthorList().size()-1).getAuthorID() + 1;

        System.out.printf("\n\nNext ID: %d", id);

    }

    public static void printLibrary(Library library){
        System.out.printf("\n\nAvailable Books:");
        library.getBookList().forEach(book -> Book.printBook(System.out, book));

        System.out.printf("\n\nAvailable Authors:");
        library.getAuthorList().forEach(author -> System.out.printf("\n%d, %s, %s",
                author.getAuthorID(), author.getFirstName(), author.getLastName()));
    }


}
