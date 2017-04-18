package Snake;
/**
 * 
 * @author Bram Soenens
 *
 *Singly linked list
 *
 * @param <T> type of paramater
 */
public class LinkedList<T> {
	
	private Node head;
	private int size;
	private int refFirst;
	
	/**
	 * Constructor of an emty list
	 */
	public LinkedList(){
		head = null;
		size = 0;
		refFirst=0;
	}
	
	/**
	 * Constructor for a likned list with one element
	 * 
	 * @param element
	 */
	public LinkedList(T element){
		head = new Node(element);
		size = 1;
	}
	
	private LinkedList(Node node){
		head = node;
		size = this.count(head,0);
	}
	
	/**
	 * Prepends the element to the linked list
	 * 
	 * @param element
	 */
	public void prepend(T element){
		Node newNode = new Node(element,head, head.getNum()-1);
		refFirst = head.getNum();
		head = newNode;
		size ++;
	}
	
	public void add(T element){
		this.lastRecursive(head).setNext(new Node(element, head.getNum()+1));
		size ++;
	}
	
	/**
	 * 
	 * @return return the head of the list
	 */
	public T first(){
		if(head == null){return null;}
		return head.get();
	}
	
	public int size(){
		return size;
	}

	/**
	 * 
	 * @return true if emty, false if the linked list contains elements
	 */
	public boolean isEmty(){
		return size == 0;
	}

	
	public boolean find(T element){
		Node cursor = head;
		do{
			if(cursor.get().equals(element))return true;
		}
		while(cursor.next !=null);
		return false;
	}
	
	/**
	 * 
	 * @return the linked list whithout the head element
	 */
	public LinkedList<T> tail(){
		return new LinkedList<T>(head.next());
	}
	
	private int count(Node current,int totaal){
		if(current.next()==null)return totaal;
		return count(current.next(),++totaal);
	}

	public T get(int num){
		return this.got(num).get();
	}
	
	private Node got(int num){
		Node cursor = head;
		do{
			if((cursor.getNum()+size-refFirst)==num)return cursor;
		}
		while(cursor.next !=null);
		return null;
	}

	public void set(int num, T element){
		this.got(num).set(element);
	}
	
	/**
	 * 
	 * @return the last element of the list
	 */
	public T last(){
		return lastRecursive(head).get();
	}
	
	public void removeLast(){
		Node current = head;
		Node vorige = new Node();
		while(current.next() != null){
			vorige = current;
			current = current.next();
		}
		vorige.setNext(null);
	}
	
	private Node lastRecursive(Node current){
		if(current.next()==null) return current;
		return lastRecursive(current.next());
	}
	
	public void keep2Leads(){
		head.next().setNext(null);
		size =2;
	}
	
	private class Node{
		private T element;
		private Node next;
		private int num;
		
		public Node(){
			this(null);
		}
		
		public Node(T element){
			this(element, null);
		}
		
		public Node(T element, int num){
			this(element, null, num);
		}
		
		public Node(T element, Node next){
			this(element,next,0);
		}
		
		public Node(T element, Node next, int num){
			this.element = element;
			this.next = next;
			this.num = num;
		}
		
		public int getNum(){
			return this.num;
		}
		
		public T get(){
			return element;
		}
		
		public Node next(){
			return next;
		}
		
		public void setNext(Node next){
			this.next = next;
		}
		
		public void set(T element){
			this.element = element;
		}
		
	}

}
