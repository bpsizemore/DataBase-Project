package adt;

/* This class' intention is to provide a list where each node contains the following:
 * 		A pointer to the 'Next' node in the list.
 * 		A generic object that will be used as a hashtable in my implementation.
 * 		A String object, the name of the generic object (hashtable).
 */
public class TableList<T> {
	private Node<T> head;
	private int size;
	
	public TableList() {
		head = null;
		size = 0;
	}
	
	public Node<T> getLast() {
		//get the last node in the list
		Node<T> current = head;
		while (current.next != null) {
			current = current.next;
		}
		return current;
	}
	
	public int size(){
		return size;
	}
	
	public void add(T value,String name) {
		Node<T> newNode = new Node<T>(value,name);
		
		if (head == null) { //if the list is null, fix dat shit.
			head = newNode;
			size ++;
			return;
		}
		
		Node<T> last = this.getLast();
		last.next = newNode;
		this.size++;
	}
	
	public void remove(String name) {
	
		if (head.name.equalsIgnoreCase(name)) {
			if (head.next != null) {
				head = head.next;
			} else {
				head = null;
			}	
			size--;
			return;
			
		} else if (head.next == null) {
			size --;
			return;
		
		} else {
			Node<T> current = head.next;
			Node<T> temp = head; //should always be one node behind current
			
			while(current != null) {
				if (current.name.equalsIgnoreCase(name)) {
					if (current.next != null) {
						temp.next = current.next;
					} else {
						temp.next = null;
					}
					size --;
					return;
				}
				temp = current;
				current = current.next;
			}	
		}
	}
	
	public T get(int index) {
		if (index > size) {
			return null;
		} else {
			Node<T> current = head;
			for (int i=1;i<=size;i++) {
				if (index == i) {
					return current.value;
				}
				current = current.next;
			}
		}
		return null; //	
	}
	
	public String getName(int index) {
		if (index > size) {
			return null;
		} else {
			Node<T> current = head;
			for (int i=1;i<=size;i++) {
				if (index == i) {
					return current.name;
				}
				current = current.next;
			}
		}
		return null; //	
	}
	
	
	public T get(String name) {
		// find the node with name and return the value
		if (head == null) {
			return null;
		} else if (head.name.equalsIgnoreCase(name)) {
			return head.value;
		} else {
		Node<T> current = head;
			while (current.next != null) {
				current = current.next;
				if (current.name.equalsIgnoreCase(name)) {
					return current.value;
				}
			}
		}
		return null; //if the value isn't anywhere in the list
	}

	
	class Node<T> {
		Node<T> next;
		String name;
		T value;
		
		Node(T newValue,String newName) { //construct a new node with a name and a value
			this.value = newValue;
			this.name = newName;
		}
	}
}