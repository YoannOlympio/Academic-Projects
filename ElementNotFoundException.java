package jsjf.exceptions;

/**
 * ElementNotFoundException accounts for the situation where a target element 
 * is not found in a collection
 *
 * @author Yoann Olympio
 * @version 4.0
 */

public class ElementNotFoundException extends RuntimeException
{
	 /**
     * Sets up this exception with an appropriate message.
     */
    public ElementNotFoundException (String collection)
    {
	    super ("The target element is not in this " + collection);
    }
}
