package assignment.a1;

import java.sql.SQLException;
import java.util.Scanner;

/**
 * Simple console Menu Application to use in CP2280
 *
 * @author Nicholas O'Quinn
 */
public class ApplicationMenu {

    /**MAIN FUNCTION*/
    public static void main(String[] args) throws SQLException {

        System.out.println("LIBRARY APPLICATION");

        Library library = BookDatabaseManager.loadLibrary();

        /**CREATE A SCANNER OBJECT*/
        Scanner input = new Scanner(System.in);
        char c; //Char to drive menu choice

        do{
            /** CALL THE PRINT MENU */
            printMenu();

            /**Grab the user input */
            c = Character.toUpperCase(input.next().charAt(0));

            //System.out.printf("\nEcho: %c", c);
            /**IF USER CHOOSES A*/
            if(c=='A'){

                //System.out.printf("\nIn Option %c", c);
                /** LOAD THE LIBRARY AND PRINT IT*/
                library = BookDatabaseManager.loadLibrary();
                BookDatabaseManager.printLibrary(library);

            }/**IF USER CHOOSES B*/
            else  if(c=='B'){

                /**CALL ADD BOOK CLASS*/
                AddBook.addNewBook();

            }/**IF USER CHOOSES C*/
            else if (c=='C'){
                System.out.printf("\nIn Option %c", c);
                //TODO do something
                AddAuthor.addNewAuthor();
            }

            /**IF USER CHOOSES Q*/
        }while(c!='Q');

        System.out.println("\nGOODBYE!");
    }

    /**PRINTING OUT THE APPLICATION MENU */
    public static void printMenu(){
        System.out.println("\n\nMake a choice");
        System.out.println("(A) View the library");
        System.out.println("(B) Add a book for an existing author");
        System.out.println("(C) Add a new author");
        System.out.println("(Q) Quit");
    }



}

