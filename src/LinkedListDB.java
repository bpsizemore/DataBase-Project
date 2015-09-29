
public class LinkedListDB {
	/* LinkedList class to be used in my table HashTable
	 * needs to implement the methods:
	 * public int size() return the size of a linked list
	 * public void add() add an item to the linked list
	 * public void remove() remove an item from the linked list
	 * public String[] get(int index) get the data from a ll node
	*/
	
	private Node head; //points to the head of the list.
	private int size;  //keeps track of how many nodes exist in the list.
	
	public LinkedListDB(String[] data) {
		head = new Node(data);
		size = 1;
		
	}
	
	public String[] get(int index) {
		Node current = head;
		int c = 0;		
		
		while (c < index) {
			if (current.next != null) {
				current = current.next;
			}
		}
		if (current == null || current.data == null) {
			return null;
		}
		return current.data;			
	}
	
	public void add(String[] data) {		

		Node temp = new Node(data); //create the new node
		Node current = head;    
		
		while (current.next != null) {
				current = current.next;
			}
		current.next = temp; //point the last element to the new element
		size ++;
	}
	
	public void remove(int index) {
		Node temp = head;
		Node current = head;
		
		if (index == 0) {
			head = null;
			
		}
		else {
			current = current.next;
			int c = 1;
			while (c < index) {
				temp = temp.next;
				current = current.next;
			}
			temp.next = current.next;
			size --;
		}			
	}
	
	public int size() {
		return this.size;
	}
/*	public int size() {
		Node current = head;
		int size = 1;
		while (current.next != null) {
			current = current.next;
			size += 1;
		}
		
		return size;
	}
*/
	// Node class used in the linked list
	class Node {
		Node next;
		String[] data;
		
		public Node(String[] newData) {
			next = null;
			data = newData;
		}
		
		
	}
}
