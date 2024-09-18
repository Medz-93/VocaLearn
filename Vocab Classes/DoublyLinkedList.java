
/**
 * Represents a doubly linked list data structure.
 * @author Mehdi Hanini
 * @version 4/15/2024
 * @param <E> The type of elements stored in the list.
 */
public class DoublyLinkedList<E> {
    
	// Inner class DNode represents a node in the doubly linked list
    private static class DNode<E> {
        E element;       // The element stored at this node
        DNode<E> next;   // Reference to the next node in the list
        DNode<E> prev;   // Reference to the previous node in the list

        /**
         * Constructs a new node with the specified element, next node, and previous node.
         * @param element The element to store in the node.
         * @param next The next node in the list.
         * @param prev The previous node in the list.
         */       
        public DNode(E element, DNode<E> next, DNode<E> prev) {
            this.element = element;
            this.next = next;
            this.prev = prev;
        }
    }
	
	private DNode<E> head; // Head node of the list
    private DNode<E> tail; // Tail node of the list
    private int size;      // Number of nodes in the list

    /**
     * Constructs an empty doubly linked list.
     */
    public DoublyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Checks if the list is empty.
     * @return true if the list is empty, false otherwise.
     */
    public boolean isEmpty() {
        return size == 0;
        //return head == null;
    }

    /**
     * Returns the number of elements in the list.
     * @return The size of the list.
     */
    public int size() {
        return size;
    }  

    /**
     * Adds an element to the beginning of the list.
     * @param element The element to add.
     */
    public void addFirst(E element) {
        DNode<E> newNode = new DNode<>(element, head, null);
        if (isEmpty()) {
            tail = newNode;
        } else {
            head.prev = newNode;
        }
        head = newNode;
        size++;
    }

    /**
     * Adds an element to the end of the list.
     * @param element The element to add.
     */
    public void addLast(E element) {
        DNode<E> newNode = new DNode<>(element, null, tail);
        if (isEmpty()) {
            head = newNode;
        } else {
            tail.next = newNode;
        }
        tail = newNode;
        size++;
    }
    

    /**
     * Removes and returns the first element in the list.
     * @return The first element in the list, or null if the list is empty.
     */
    public E removeFirst() {
            if (isEmpty()) {
                return null; // No elements to remove
            }

            E element = head.element;
            if (size == 1) {
                head = null;
                tail = null;
            } else {
                head = head.next;
                head.prev = null;
            }
            size--;
            return element;
        }

    /**
     * Removes and returns the last element in the list.
     * @return The last element in the list, or null if the list is empty.
     */
    public E removeLast() {
            if (isEmpty()) {
                return null; // No elements to remove
            }

            E element = tail.element;
            if (size == 1) {
                head = null;
                tail = null;
            } else {
                tail = tail.prev;
                tail.next = null;
            }
            size--;
            return element;
        }
    
    /**
     * Displays the topics stored in the list of type Vocab.
     */
    public void displayTopics() {
        DNode current = head;
        int index = 1;
        while (current != null) {
            System.out.println(index + ": " + ((Vocab)current.element).getTopic());
            current = current.next;
            index++;
        }
    }

    /**
     * Removes all elements from the list.
     */
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }
    
    /**
     * Retrieves the Vocab object stored at the specified index in the list
     * @param index The index of the element to retrieve.
     * @return The Vocab object at the specified index, or null if the index is out of bounds
     */
    public Vocab getVocabByIndex(int index) {
        if (index < 0 || index >= size) {
            return null; // Index out of bounds
        }
        DNode<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return (Vocab) current.element; 
    }

    /**
     * Inserts an element before the specified position in the list.
     * @param position The index at which to insert the element.
     * @param element The element to insert.
     */
    public void addBefore(int position, E element) {
            if (position == 0) { // Insert at the beginning
                addFirst(element);
                return;
            }
            if (position == size) { // Insert at the end
                addLast(element);
                return;
            }

            DNode<E> current = head;
            for (int i = 0; i < position; i++) {
                current = current.next;
            }

            DNode<E> newNode = new DNode<>(element, current, current.prev);
            current.prev.next = newNode;
            current.prev = newNode;
            size++;
        
    }
        
    /**
     * Inserts an element after the specified position in the list.
     * @param position The index after which to insert the element.
     * @param element The element to insert.
     */
    public void addAfter(int position, E element) {
            if (position == size - 1) { // Insert at the end
                addLast(element);
                return;
            }

            DNode<E> current = head;
            for (int i = 0; i < position; i++) {
                current = current.next;
            }

            DNode<E> newNode = new DNode<>(element, current.next, current);
            current.next.prev = newNode;
            current.next = newNode;
            size++;
        }
  
    /**
     * Removes the element at the specified position in the list.
     * @param position The index of the element to remove.
     * @return The removed element, or null if the index is out of bounds.
     */
    public E removeAt(int position) {
            if (position < 0 || position >= size) {
                return null; // Position out of bounds
            }

            DNode<E> current = head;
            if (position == 0) { // Remove from the beginning
                return removeFirst();
            } else if (position == size - 1) { // Remove from the end
                return removeLast();
            } else { // Remove from the middle
                for (int i = 0; i < position; i++) {
                    current = current.next;
                }
                current.prev.next = current.next;
                current.next.prev = current.prev;
                size--;
                return current.element;
            }

        }
}
