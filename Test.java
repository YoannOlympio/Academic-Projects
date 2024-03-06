package jsjf;

public class Test {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Library hoover = new Library();
		hoover.menu();
		
		DoubleLinkedOrderedList<Book> list = new DoubleLinkedOrderedList<Book>();	
		Book book1 = new Book("The Hobbit", "Olympio", "Yoann", 134);
		list.add(book1);
		Book book2 = new Book("The Hobbit", "Gale", "Thorn", 137);
		list.add(book2);
		
		System.out.println(list.contains(book2));
		System.out.println(book1.equals(book2));
		
		System.out.println(book1);
		System.out.println(book2);
		
		
		DoubleLinkedOrderedList<String> list2 = new DoubleLinkedOrderedList<String>();	
		String string1 = "hello";
		list2.add(string1);
		String string2 = "hello";
		System.out.println(list2.contains(string2));
		System.out.println(string1.equals(string2));
		
		list2.add("b");
		list2.add("a");
		list2.add("d");
		list2.add("c");
		
		list2.contains("a");
		
	
		System.out.println(list2);
		
		
		
	}
}
