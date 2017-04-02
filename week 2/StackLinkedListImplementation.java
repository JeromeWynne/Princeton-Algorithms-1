import java.util.Iterator;
/* Linked-list implementation of a stack iterator */
public class Stack<Item> implements Iterable<Item> // Has a method that returns an Iterator
{
    /* Stack code */
    public Iterator<Item> iterator() { return new ListIterator(); }
    
    private class ListIterator implements Iterator<Item>
    {
       private Node current = first;
       // ListIterator class must have the methods
       public boolean hasNext(){ return current != null; }
       public void remove()    { /* Not supported */ }
       public Item next()
       {
          Item item = current.item;
          current = current.next;
          return item;
       }
        
    }
}
    