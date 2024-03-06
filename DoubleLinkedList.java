package jsjf;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import jsjf.exceptions.*;

/**
 * Represents a doubly linked list implementation 
 * @author Yoann Olympio
 */

public class DoubleLinkedList<T> implements ListADT<T>, Iterable<T> 
{
	protected int count;
	protected LinearNode<T> head, tail;
	protected int modCount;
	
	/**
	 * Creates an empty list
	 */
	public DoubleLinkedList(){
		count = modCount = 0;
		head = tail = null;
	
	}
	
	/**
	 * Removes the first element of this list and returns a reference
	 * to it. If list is empty, throws EmptyCollectionException
	 */
	
	@Override
	public T removeFirst () throws EmptyCollectionException 
	{
		if(this.isEmpty())
			throw new EmptyCollectionException("DoubleLinkedList");
		
		LinearNode<T> current = head;
		head = head.getNext();
		head.setPrevious(null);
		
		count--;
		modCount++;
		
		return current.getElement();
	}
	
	/**
	 * Removes the last element of this list and returns a reference
	 * to it. If list is empty, throws EmptyCollectionException
	 */
	
	@Override
	public T removeLast() throws EmptyCollectionException 
	{
		if(this.isEmpty())
			throw new EmptyCollectionException("DoubleLinkedList");
			
		LinearNode<T> current = tail;
		tail = tail.getPrevious();
		tail.setNext(null);
		
		count--;
		modCount++;
		
		return current.getElement();
	}
	
	/**
	 * Removes a target element of this list and returns a reference 
	 * to it. If list is empty, throws EmptyCollectionException. If 
	 * element is not in the list, throws ElementNotFoundException
	 */
	
	@Override
	public T remove(T element) 
	{
		 if (isEmpty()) 
			 throw new EmptyCollectionException("DoubleLinkedList");
		 
	        LinearNode<T> current = head;
	        
	        while (current != null && !element.equals(current.getElement())) 
	        {
	            current = current.getNext();
	        }
	        if (current == null) 
	        	throw new ElementNotFoundException("DoubleLinkedList");

	        if (current == head) 
	        {
	        	
	        	return removeFirst();
	        }
	        if (current == tail) 
	        {
	       
	        	return removeLast();
	        }

	        current.getPrevious().setNext(current.getNext());
	        current.getNext().setPrevious(current.getPrevious());
	        
	        count--;
	        modCount++;
	        
	        return current.getElement();
	}

	/**
	 * Returns a reference to the first element in the list.
	 * Throws a EmptyCollectionException if list is empty
	 */
	@Override
	public T first() 
	{
		if (isEmpty())
            throw new EmptyCollectionException("DoubleLinkedList");
		
    	return head.getElement();
	}

	/**
	 * Returns a reference to the last element in the list.
	 * Throws a EmptyCollectionException if list is empty
	 */
	
	@Override
	public T last() 
	{
		if (isEmpty())
            throw new EmptyCollectionException("DoubleLinkedList");
    	return tail.getElement();
	}

	/**
	 * Returns true if target element is in the list.
	 * Returns false if target element is not in the list
	 */
	
	@Override
	public boolean contains(T target) throws EmptyCollectionException 
	{
		if (isEmpty()) 
			throw new EmptyCollectionException("LinkedList");
		
        LinearNode<T> current = head;
        
        while (current != null) 
        {
            if (current.getElement().equals(target)) 
            {
            return true;
            }
            current = current.getNext();
    	}
    	
    	return false;
        
	}

	/**
	 * If the list is empty, returns true, otherwise returns false.
	 */
	
	@Override
	public boolean isEmpty() 
	{
		return count == 0;
	}

	/**
	 * Returns the number of elements in the list
	 */
	
	@Override
	public int size() 
	{
		return count;
	}

	/**
	 * Returns an iterator for the elements in this list
	 */
	
	@Override
	public DoubleLinkedListIterator iterator() 
	{
		return new DoubleLinkedListIterator();
	}
	
	/**
	 * DoubleLinkedListIterator represents an iterator for a double linked list of linear nodes
	 */
	
	public class DoubleLinkedListIterator implements Iterator<T>
	{
		
		private int iteratorModCount; 
		private LinearNode<T> current, previous;

		 // Sets up the iterator
		 
		public DoubleLinkedListIterator() 
		{	
				previous = null;
				current = head;
				iteratorModCount = modCount;
		}
		
		/**
		 * Returns true if the iterator has at least one more element
		 * being processed in the iteration. Throws ConcurrentModificationException
		 * if the list has been modified while the iterator was in progress
		 */
		
		@Override
		public boolean hasNext () 
		{
			
			if (iteratorModCount != modCount) 
				throw new ConcurrentModificationException();
			
			return current != null;
		}
		
		/**
		 * Returns true if the iterator has at least one more previous element
		 * to deliver in the iteration. Throws ConcurrentModificationException
		 * if the list has been modified while the iterator was in use.
		 */
		
		public boolean hasPrevious () throws ConcurrentModificationException
		{
			if(iteratorModCount != modCount)
				throw new ConcurrentModificationException();
			
			return(previous != null);
		}

		/**
		 * Returns the next element in the iteration. Also moves the pointer forward by one
		 * Throws ConccurentModificationException if the list has been modified while the 
		 * iterator was in use. Throws NoSuchElementException if the iterator is at the 
		 * end of the list.
		 */
		@Override
		public T next() throws ConcurrentModificationException, NoSuchElementException{
			
			if (!hasNext())
				throw new NoSuchElementException();
			
			T result = current.getElement();
			previous = current;
			current = current.getNext();
			
			return result;
		}
		
		/**
		 * Returns the previous element in the iteration. Also moves the pointer back by one.
		 * Throws ConccurentModificationException if the list was modified while the 
		 * iterator was in use. Throws NoSuchElementException if the iterator is at the 
		 * start of the list.
		 */
		public T previous() throws ConcurrentModificationException, NoSuchElementException
		{
			if(!hasPrevious()) 
				throw new NoSuchElementException();
			
			current = previous;
			T result = previous.getElement();
			previous = previous.getPrevious();
			
			return result;
		}
		
	}
	
	/**
	 * Returns a string representation of the list
	 * 
	 * @returns a string representation of the list
	 */
	@Override
	public String toString()
	{
		 StringBuilder result = new StringBuilder("DoubleLinkedList: {");
		 
	        LinearNode<T> current = head;
	        
	        while (current != null) 
	        {
	            result.append(current.getElement().toString());
	            current = current.getNext();
	            
	            if (current != null) 
	            {
	            	result.append(", ");
	            }
	        }
	        return result.append("}").toString();
	}
	


}
