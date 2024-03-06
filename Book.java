package jsjf;

/**
 * Represents a book object with title, first and last name, ISBN, and a shelfIndex
 *@author Yoann Olympio
 */

public class Book implements Comparable<Book> {
	
	private String title, first, last;
	private int isbn, shelfIndex;
	
	
	/**
	 * Creates a new book with all attributes
	 * 
	 * @param reference to the book's title
	 * @param reference to the author's first name
	 * @param reference to the author's last name
	 * @param reference to the book's isbn
	 */
	public Book(String title, String first, String last, int isbn) 
	{
		this.title = title;
		this.first = first;
		this.last = last;
		this.isbn = isbn;
		this.shelfIndex = 0;
	}
	
	
	
	/**
	 * Returns a reference to the book's title
	 * 
	 * @return a reference to the book's title
	 */
	public String getTitle() 
	{
		return title;
	}
	
	/**
	 * Returns a reference to the authors first name
	 * 
	 * @return a reference to the authors first name
	 */
	public String getFirst()
	{
		return first;
	}
	
	/**
	 * Returns a reference to the authors last name
	 * 
	 * @return a reference to the authors last name
	 */
	public String getLast() 
	{
		return last;
	}
	
	/**
	 * Returns a reference to the book's isbn
	 * 
	 * @return a reference to the book's isbn
	 */
	public int getISBN()
	{
		return isbn;
	}
	
	/**
	 * Returns a reference to the book's shelf index
	 * 
	 * @return a reference to the book's shelf index
	 */
	public int getShelfIndex()
	{
		return shelfIndex;
	}
	
	/**
	 * Sets the books title to newTitle
	 * 
	 * @param a reference to the new title
	 */
	public void setTitle(String title) 
	{
		this.title = title;
	}
	
	/**
	 * Sets the authors first and last names to the new first and last names
	 * 
	 * @param a reference to the new author's first name
	 * @param a reference to the new author's last name
	 */
	public void setAuthor(String First, String Last)
	{
		this.first = First;
		this.last = Last;
	}
	
	/**
	 * Sets the books isbn to newISBN
	 * 
	 * @param a reference to the new isbn
	 */
	public void setISBN(int isbn) 
	{
		this.isbn = isbn;
	}
	
	/**
	 * Sets the books shelfIndex to newIndex
	 * 
	 * @param the new index
	 */
	public void setShelfIndex(int shelfIndex) 
	{
		this.shelfIndex = shelfIndex;
	}
	
	/**
	 * Returns a negative integer, zero, or a positive integer 
	 * as this object is less than, equal to, or greater than 
	 * the specified object
	 * 
	 * @param the book to be compared
	 * @returns a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than the specified object
	 */
	public int compareTo(Book otherBook) 
	{
		//If author's last name is the same, compare titles
		if(this.last.equals(otherBook.getLast()))
		{
			return this.title.compareTo(otherBook.getTitle());
		}
		//Otherwise compare last names
		return this.last.compareTo(otherBook.getLast());
	}
	
	/**
	 * Returns true if this book and the book to be 
	 * compared to have the same title, name, and isbn.
	 * Returns false otherwise
	 * 
	 * @param object to be compared to
	 * @returns true if this book and the book to be compared to have the same title, name, and isbn
	 */
	@Override
	public boolean equals(Object obj) 
	{
		if(!(obj instanceof Book))
		{
			System.out.print("Other Object must be a Book");
		}
		Book otherBook = (Book) obj;
		return !(!this.title.equals(otherBook.getTitle()) || !this.first.equals(otherBook.getFirst()) || !this.last.equals(otherBook.getLast()) || !(this.isbn == otherBook.getISBN()));
	}
	
	/*
	 * Returns a String representation of the book
	 * 
	 * @return a String representation of the book
	 */
	@Override
	public String toString() {
		return "Title: " + this.title + "	Author: " + this.first + " " + this.last + "	ISBN: " + this.isbn + "		Shelf Index: " + this.shelfIndex;
	}

}
