package jsjf;

/**
 * Represents a node in a linked list.
 * 
 * @author Yoann Olympio
 * @version 4.0
 */ 

public class LinearNode<T> 
{
	 private LinearNode<T> previous, next;
	    private T element;
	 
	   
	     // Creates an empty node.
	     
	    public LinearNode()
	    {
	    	previous = null;
	        next = null;
	        element = null;
	    }
	 
	    
	     //Creates a node to store the specified element.
	    
	    public LinearNode(T elem)
	    {
	        previous = next = null;
	        element = elem;
	    }
	 
	    
	     // Returns the node next node.
	    
	    public LinearNode<T> getNext()
	    {
	        return next;
	    }
	    
	    //Return the previous node 
	    
	    public LinearNode<T> getPrevious(){
	    	return previous;
	    }
	   
	    //Sets the next node 
	    public void setNext(LinearNode<T> node)
	    {
	        next = node;
	    }
	 
	   //Sets the previous node
	    public void setPrevious(LinearNode<T> node)
	    {
	    	previous = node;
	    }
	  
	    //Return element stores in node 
	    public T getElement()
	    {
	        return element;
	    }
	 
	   
	    //Sets element for node 
	    public void setElement(T elem)
	    {
	        element = elem;
	    }
}
