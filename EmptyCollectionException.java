package jsjf.exceptions;

/**
 * Accounts for when a collection is empty.
 *
 * @author Yoann Olympio
 * @version 4.0
 */

public class EmptyCollectionException extends RuntimeException 
{
	/**
     * Sets up this exception with an appropriate message.
     * @param collection the name of the collection
     */
    public EmptyCollectionException (String collection)
    {
        super ("The " + collection + " is empty.");
    }
}
