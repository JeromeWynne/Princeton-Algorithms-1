public class LinkedStackOfStrings
{
    private Node first = null; // Allocate memory for a Node object to the variable 'first'
    
    private class Node
    {
        String item; // Node's value
        Node next; // Reference to next node in list
    }
    
    public boolean isEmpty()
    {   return first == null;   }
    
    public void push(String item)
    {
        Node oldfirst = first; // Squish previous first node to second position
        first = new Node(); // Create new first node
        first.item = item; // Assign it a value
        first.next = oldfirst; // Assign it a reference to the next node ^
    }
    
    public String pop()
    {
        String item = first.item; // Retrieve first item's value
        first = first.next; // Have the node refer to the second position ('remove' the item)
        return item; // Return the popped item's value
    }
}