/* Double-ended queue:
 * Supports adding and removing items from either end of the data structure.
 * Implemented using a two-way linked list.
 */
import java.util.Iterator

public class Deque<Item> implements Iterable<Item> {
    private int length;
    
    public Deque() { // Construct empty deque
        private Node first = new Node();
        private Node last = new Node();
        first.next = last;
        last.previous = first;
        first.previous = null;
        last.next = null;
        length = 0;
    }
    
    private class Node {
        <Item> item; // Node's value
        Node next; // Reference to next node in list
        Node previous; // Reference to previous node in list
    }
    
    public boolean isEmpty() { // Is deque empty?
        return (length == 0);
    }
    
    public int size() { // Return # items on deque
        return length;
    }
    public void addFirst(Item item) { // Add item to front
       Node next = first;
       Node first = new Node();
       next.previous = null;
       first.next = next;
       first.item = item;
       length += 1;
    }
    
    public addLast(Item item) { // Add item to end
        Node previous = last;
        Node last = new Node();
        last.previous = previous;
        last.next = null;
        last.item = item;
        length += 1;
    }
    
    public Item removeFirst() { // Remove + return front item
        item = first.item;
        first = first.next;
        first.previous = null;
        length -= 1;
        return item;
    }
    
    public Item removeLast() { // Remove + return end item
        item = last.item;
        last = last.previous;
        last.next = null;
        length -= 1;
        return item;
    }
    
    public Iterator<Item> iterator(){ // Return iterator over items from front to end
        public <Item> next(){}
        public boolean isNext(){}
        public void remove(){}
    }
    
    public static void main(String[] args) { // Unit testing
        
        
    }
}

/* > Throw java.lang.NullPointerException if client attempts to add null item.
 * > Throw java.util.NoSuchElementException if client attempts to remove item from empty deque
 * > Throw java.lang.UnsupportedOperationException if client call Iterator's remove()
 * > Throw java.util.NoSuchElementException if client calls next() in iterator with no items to return
 * 
 * Must support each deque operation in constant worst-case time
 * Deque with n items must use at most 48n + 192 bytes of memory
 * Must use space proportional to number of items currently in deque
 * Iterator must support each iteration in constant worst-case time
*/