package jsjf;

import java.util.Iterator;
import java.util.Scanner;

/**
 * Represents a library that sorts and organized books
 * 
 * @author Yoann Olympio
 * @version 1.0
 */

public class Library 
{
	private static DoubleLinkedOrderedList<Book> shelf = new DoubleLinkedOrderedList<>();
    private Scanner scan = new Scanner(System.in);

    public Library() {
        // The shelf is initialized in the field declaration.
    }

    public void menu() {
        int choice = 0;
        do {
            displayMenu();
            while (!scan.hasNextInt()) {
                scan.next(); // Clear the invalid input
                displayMenu();
            }
            choice = scan.nextInt();
            scan.nextLine(); // Clear the newline after the number

            switch (choice) {
                case 1: 
                	add(); 
                	break;
                case 2:
                	search(); 
                	break;
                case 3:
                	retrieveInfo(); 
                	break;
                case 4: 
                	editBook(); 
                	break;
                case 5: 
                	removeBook();
                	break;
                case 6: 
                	printContents();
                	break;
                case 7: 
                	System.out.println("Exiting...");
                	break;
                default: 
                	System.out.println("Invalid input, please enter a number 1-7.");
            }
        } 
        while (choice != 7);
    }

    private void displayMenu()
    {
        String[] options = 
        	{
            "Add a book", "Find a book", "Retrieve book information",
            "Edit book information", "Remove a book", "See all contents", "Exit"
        };

        for (int i = 0; i < options.length; i++) 
        {
            System.out.println((i + 1) + ") " + options[i]);
        }
        
        System.out.print("Enter your choice (1-7): ");
    }

    private void add()
    {
        System.out.println("Enter book information in the following format: 'Title, First Name, Last Name, ISBN'");
        String[] input = scan.nextLine().split(",\\s*");
        
        if (!inputChecker(input)) 
        {
            return;
        }
        
        Book newBook = new Book(input[0], input[1], input[2], Integer.parseInt(input[3]));
        shelf.add(newBook);
        shelfIndexer(); // Update shelf indices after adding a new book
        System.out.println("Successfully added book.\n");
        
       promptReturnToMenu();
    }
	
	/**
	 * Requests the information of the book the user wants to search for
	 * and informs them whether or not it is in the library
	 */
	public void search()
	{
		System.out.println("Enter the information in the following format: 'Title, First, Last, ISBN'");
	    String[] input = scan.nextLine().split(",\\s*");
	    
	    if (!inputChecker(input))
	    {
	        return;
	    }
	    
	    Book bookToFind = new Book(input[0], input[1], input[2], Integer.parseInt(input[3]));
	    boolean found = shelf.contains(bookToFind);
	    
	    if (found) {
	        System.out.println("We have the book: " + bookToFind);
	    } 
	    else 
	    {
	        System.out.println("We do not have the book: " + bookToFind);
	    }
	    
	    promptReturnToMenu();
	}
	
	/**
	 * If shelf is empty, notify user
	 * Ask for index of book choice
	 * if not found, notify user and return
	 * else give info on book
	 */
	public void retrieveInfo() {
		if (shelf.isEmpty()) {
	        System.out.println("The library is empty.");
	        return;
	    }
		
	    System.out.println("Enter the index of the book:");
	    int index = scan.nextInt();
	    scan.nextLine(); // clear the newline
	    
	    if (index < 1 || index > shelf.size()) 
	    {
	        System.out.println("Invalid index.");
	        return;
	    }
	    
	    Book book = getBookAtIndex(index);
	    System.out.println(book);
	    
	    promptReturnToMenu();
		
	}
	
	/**
	 * If shelf is empty notify user and return
	 * Asks user for index of book they want to edit
	 * Check if input is valid
	 * iterate to book they want to edit
	 * Ask what they want the information to be replaced with
	 * Check if given information is valid, then add new book and remove old book
	 */
	public void editBook()
	{
	    if (shelf.isEmpty()) {
	        System.out.println("The library is empty.");
	        return;
	    }
	    
	    System.out.println("Enter the index of the book you want to edit:");
	    int index = scan.nextInt();
	    scan.nextLine(); // clear the newline
	    
	    if (index < 1 || index > shelf.size())
	    {
	        System.out.println("Invalid index.");
	        return;
	    }
	    
	    Book bookToEdit = getBookAtIndex(index);
	    System.out.println("Enter the new information in the following format: 'Title, First, Last, ISBN'");
	    String[] input = scan.nextLine().split(",\\s*");
	    
	    if (!inputChecker(input))
	    {
	        return;
	    }
	    
	    shelf.remove(bookToEdit);
	    Book updatedBook = new Book(input[0], input[1], input[2], Integer.parseInt(input[3]));
	    shelf.add(updatedBook);
	    shelfIndexer(); // Update shelf indices after adding a new book
	    System.out.println("Successfully edited the book.");
	    
	    promptReturnToMenu();
	}
	
	/**
	 * If shelf is empty, give prompt and return
	 * Request contents of book and check if input is viable with input checker
	 * Try to remove newBook from shelf
	 * Catch the element not found exception and give user a prompt
	 */
	public void removeBook() 
	{
		if (shelf.isEmpty())
		{
	        System.out.println("The library is empty.");
	        return;
	    }
		
	    System.out.println("Enter the index of the book to remove:");
	    int index = scan.nextInt();
	    scan.nextLine(); // clear the newline
	    
	    if (index < 1 || index > shelf.size())
	    {
	        System.out.println("Invalid index.");
	        return;
	    }
	    
	    Book bookToRemove = getBookAtIndex(index);
	    shelf.remove(bookToRemove);
	    shelfIndexer(); // Update shelf indices after adding a new book
	    System.out.println("Successfully removed the book: " + bookToRemove);
	    promptReturnToMenu();
	}
	
	/**
	 * If shelf is empty, Give prompt stating so and return
	 * Iterates through the shelf printing each book in a row
	 */
	public void printContents() 
	{
		if (shelf.isEmpty())
		{
	        System.out.println("The library is empty.");
	        return;
	    }
		
	    Iterator<Book> iterator = shelf.iterator();
	    
	    while (iterator.hasNext()) 
	    {
	        System.out.println(iterator.next());
	    }
	    
	    promptReturnToMenu();
	}
	
	
	
	/**
	 * Checks if input is correct length
	 * Checks if name includes numbers or ISBN contains alphabetical characters
	 */
	
	public static boolean inputChecker(String[] input) 
	{
		  if (input.length != 4) 
		  {
	            System.out.println("Invalid input, expected 4 pieces of information separated by commas.");
	            return false;
	        }
		  
	        if (containsInt(input[1]) || containsInt(input[2]) || !isInteger(input[3]))
	        {
	            System.out.println("Invalid input: Names cannot include numbers and ISBN must be numeric.");
	            return false;
	        }
	        
	        return true;
	}
	
	private Book getBookAtIndex(int index) 
	{
	    Iterator<Book> iterator = shelf.iterator();
	    Book book = null;
	    for (int i = 0; i < index; i++)
	    {
	        book = iterator.next();
	    }
	    
	    return book;
	}
	/**
	 * Checks if the string contains digits 1-9
	 */
	public static boolean containsInt(String line) 
	{
		 return line.matches(".*\\d.*");
	}
	
	/**
	 * Try to parse input as int, if possible return true
	 * else, return false.
	 */
	public static boolean isInteger(String input)
	{
		  try 
		  {
	            Integer.parseInt(input);
	            return true;
	        } 
		  catch (NumberFormatException e)
		  {
	            return false;
	        }
	}
	
	/**
	 * Iterates through the shelf, setting the books indexes starting from 1
	 */
	public static void shelfIndexer() 
	{
		Iterator<Book> iterator = shelf.iterator();
		int index = 1;
		
		while(iterator.hasNext()) 
		{
			iterator.next().setShelfIndex(index);
			index++;
		}
	}
	
	   private void promptReturnToMenu() 
	   {
	        System.out.println("Press Enter to return to the menu...");
	        scan.nextLine();
	   }
}
