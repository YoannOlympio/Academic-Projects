package jsjf;

import jsjf.exceptions.NonComparableElementException;

/**
 * Represents a doubly linked implementation of a self sorting list
 * @author Yoann Olympio 
 */

public class DoubleLinkedOrderedList<T> extends DoubleLinkedList<T>
implements OrderedListADT<T>
{
	
	 // Creates an empty list using the DoubleLinked list class.  It is the parent class so we use super to refer to it.
	 
	public DoubleLinkedOrderedList(){
		super();
	}
	
	/**
	 * Adds an element to the list in its sorted position from least to greatest.
	 * Throws NonComparableElementException if element is not comparable.
	 */
	
	@Override
	public void add(T element) throws NonComparableElementException{
		
		  if (!(element instanceof Comparable))
		        throw new NonComparableElementException("DoubleLinkedOrderedList");

		    LinearNode<T> newLinNode = new LinearNode<T>(element);
		    
		    if (isEmpty()) 
		    {
		        head = newLinNode;
		        tail = newLinNode;
		    } 
		    else 
		    {
		        LinearNode<T> current = head;
		        
		        @SuppressWarnings("unchecked")
				Comparable<T> comparableElement = (Comparable<T>) element;
		        
		        while (current != null && comparableElement.compareTo(current.getElement()) > 0)
		        {
		            current = current.getNext();
		        }
		        
		        if (current == head)
		        {
		            newLinNode.setNext(head);
		            head.setPrevious(newLinNode);
		            head = newLinNode;
		        } 
		        else if (current == null) 
		        {
		            tail.setNext(newLinNode);
		            newLinNode.setPrevious(tail);
		            tail = newLinNode;
		        } 
		        else 
		        {
		            newLinNode.setNext(current);
		            newLinNode.setPrevious(current.getPrevious());
		            current.getPrevious().setNext(newLinNode);
		            current.setPrevious(newLinNode);
		        }
		    }
		    count++;
		    modCount++;
	}
}
