public class Deque<Item> implements Iterable<Item> {
    public Deque() // Construct empy deque
    public boolean isEmpty() // Is deque empty?
    public int size() // Return # items on deque
    public void addFirst(Item item) // Add item to front
    public addLast(Item item) // Add item to end
    public Item removeFirst() // Remove + return front item
    public Item removeLast() // Remove + return end item
    public Iterator<Item> iterator() // Return iterator over items from front to end
    public static void main(String[] args) // Unit testing