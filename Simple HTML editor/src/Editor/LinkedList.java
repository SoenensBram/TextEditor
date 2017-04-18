package Editor;

import java.util.Iterator;

/**
 * @param <T> type of paramater
 * @author Bram Soenens
 *         <p>
 *         Singly linked list
 */
public class LinkedList<T> implements Iterable<T> {

    private Node head;
    private int size;

    /**
     * Constructor of an emty list
     */
    public LinkedList() {
        head = null;
        size = 0;
    }

    /**
     * Constructor for a likned list with one element
     *
     * @param element
     */
    public LinkedList(T element) {
        head = new Node(element);
        size = 1;
    }

    private LinkedList(Node node) {
        head = node;
        size = this.count(head, 0);
    }

    /**
     * Prepends the element to the linked list
     *
     * @param element
     */
    public void prepend(T element) {
        Node newNode = new Node(element, head);
        head = newNode;
        size++;
    }

    /**
     * @return return the head of the list
     */
    public T first() {
        if (head == null) {
            return null;
        }
        return head.get();
    }

    public int size() {
        return size;
    }

    /**
     * @return true if emty, false if the linked list contains elements
     */
    public boolean isEmty() {
        return size == 0;
    }


    public boolean find(T element) {
        Node cursor = head;
        do {
            if (cursor.get().equals(element)) return true;
        }
        while (cursor.next != null);
        return false;
    }

    /**
     * @return the linked list whithout the head element
     */
    public LinkedList<T> tail() {
        return new LinkedList<T>(head.next());
    }

    private int count(Node current, int totaal) {
        if (current.next() == null) return totaal;
        return count(current.next(), ++totaal);
    }
    
    public void removeLast(){
    	Node Secondlast = this.Secondlast(head);
    	Secondlast = new Node(Secondlast.get());
    }
    
    private Node Secondlast(Node current) {
            if (current.next().next() == null) return current;
            return Secondlast(current.next());
        	
    }

    public Iterator<T> iterator() {
        return null;
    }

    private class LinkedListIterator implements Iterator<T> {
        private Node index = head;

        @Override
        public boolean hasNext() {
            return index.next != null;
        }

        @Override
        public T next() {
            T element = index.get();
            index = index.next();
            return element;
        }

    }


    /**
     * @return the last element of the list
     */
    public T last() {
        return lastRecursive(head).get();
    }

    private Node lastRecursive(Node current) {
        if (current.next() == null) return current;
        return lastRecursive(current.next());
    }

    private class Node {
        private T element;
        private Node next;

        public Node(T element) {
            this(element, null);
        }

        public Node(T element, Node next) {
            this.element = element;
            this.next = next;
        }

        public T get() {
            return element;
        }

        public Node next() {
            return next;
        }

    }

}
