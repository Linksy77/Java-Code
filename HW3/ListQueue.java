package HW3;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListQueue<E>{
	private Node<E> front;
	private int size;
	
	public static class Node<E>{
		private E data;
		private Node<E> next;
		private int priority;
		
		/**
		 * Constructor that creates a node holding dataItem.
		 * Task is assigned LOW_PRIORITY by default.
		 * @param dataItem
		 */
		public Node(E dataItem) {
			data = dataItem;
		}
		
		/**
		 * Constructor that creates a node holding dataItem.
		 * Task is assigned parameter priority as its priority.
		 * @param dataItem
		 * @param priority
		 */
		public Node(E dataItem, int priority) {
			data = dataItem;
			this.priority = priority;
		}
		
		/**
		 * Constructor that creates a node holding dataItem.
		 * Task is assigned parameter ref as its next Node.
		 * Task is assigned parameter priority as its priority.
		 * @param dataItem
		 * @param ref
		 * @param priority
		 */
		public Node(E dataItem, Node<E> ref, int priority) {
			data = dataItem;
			next = ref;
			this.priority = priority;
		}
		/**
		 * Accessor for E data.
		 * @return
		 */
		public E getData() {
			return data;
		}
		
		/**
		 * Accessor for Node next.
		 * @return
		 */
		public Node<E> getNext(){
			return next;
		}
	}
	
	private class Iter implements Iterator<E>{
		private Node<E> next = front;
		
		/**
		 * Returns true when the next Node is not equal to null. Returns false otherwise.
		 */
		public boolean hasNext() {
			if(next != null) {
				return true;
			} else {
				return false;
			}
		}
		
		/**
		 * Returns the data stored in the next node (attribute) and updates the next node
		 * attribute with the next node of next node (that is, we will traverse to the next
		 * node with this update).
		 * Throws NoSuchElementException if next node is null.
		 */
		public E next() {
			if(hasNext() == true) {
				E curr = next.getData();
				next = next.getNext();
				return curr;
			} else {
				throw new NoSuchElementException("ERROR! There is no next node (the next node is null).");
			}
		}
		
		/**
		 * Throws UnsupportedOperationException.
		 */
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
	
	/**
	 * Creates an empty single-linked list representing the priority queue.
	 */
	public ListQueue() {
		front = null;
		size = 0;
	}
	
	/**
	 * Creates a one-element single-linked list representing the priority queue.
	 * first parameter will be stored in front of the queue.
	 * @param first
	 */
	public ListQueue(Node<E> first) {
		front = first;
		size = 1;
	}
	
	/**
	 * Accessor for Node front.
	 * @return
	 */
	public Node<E> getFront(){
		return front;
	}
	
	/**
	 * Mutator for Node front.
	 * @param front
	 */
	public void setFront(Node<E> front) {
		this.front = front;
	}
	
	/**
	 * Accessor for int size.
	 * @return
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * Returns the information at the front of the queue.
	 * @return
	 */
	public E peek() {
		// Potentially add NoSuchElementException or NullPointerException if getData returns null?
		return front.getData();
	}
	
	/**
	 * Adds item to a position according to its priority.
	 * If there exists same-priority tasks in the list, items will be added after the existing
	 * same-priority level tasks.
	 * Throws NullPointerException if the item sent to the method is null.
	 * @param item
	 * @param priority
	 * @return
	 */
	public boolean offer(E item, int priority) {
		if(item == null) { // If the item is null
			throw new NullPointerException("ERROR! Item is null.");
		} else if(size == 0) { // If the ListQueue has no items
			front = new Node<E>(item, priority);
			size++;
			return true;
		} else {
			Node<E> curr = front;
			Node<E> nodeToAdd = new Node<E>(item, priority);
			
			if(priority < curr.priority) { // if item should go at the top/in the front of the queue
				front = nodeToAdd;
				front.next = curr;
				size++;
				return true;
			}
			
			for(int i = 1; i <= size; i++) { // Traverses all items in ListQueue
				if(priority >= curr.priority && curr.getNext() != null) {
					if(priority < curr.getNext().priority) { // If item you want to add fits between two items
						Node<E> temp = curr.getNext();
						curr.next = nodeToAdd;
						curr.next.next = temp;
						size++;
						return true;
					} else {
						curr = curr.getNext();
					}
				} else if(priority >= curr.priority && curr.getNext() == null){ // If item you want to add fits at the end of the ListQueue
					curr.next = nodeToAdd;
					curr.next.next = null;
					size++;
					return true;
				} else {
					curr = curr.getNext(); // Moves to next item for comparison
				}
			}
			return false;
		}
	}
	
	/**
	 * Adds item at the end of the queue.
	 * Always returns true, but throws NullPointerException if the item sent to the method is null.
	 * @param item
	 * @return
	 */
	public boolean addRear(E item) {
		if(item == null) {
			throw new NullPointerException("ERROR! Item is null.");
		} else if(size == 0) {
			front = new Node<E>(item);
			size++;
			return true;
		} else {
			Node<E> curr = front;
			Node<E> nodeToAdd = new Node<E>(item);
			for(int i = 1; i <= size; i++) {
				if(i == size) {
					curr.next = nodeToAdd;
					size++;
					return true;
				} else {
					curr = curr.getNext();
				}
			}
			return false;
		}
	}
	
	/**
	 * Returns the data at the front of the queue and removes it from the queue.
	 * Throws NullPointerException if the item at the front of the queue is null.
	 * @return
	 */
	public E poll() {
		if(front == null) {
			throw new NullPointerException("ERROR! Front of queue is null.");
		} else if(size == 1) {
			E frontData = front.getData();
			front = null;
			size--;
			return frontData;
		} else {
			E frontData = front.getData();
			front = front.getNext();
			size--;
			return frontData;
		}
	}
	
	/**
	 * Takes a node to be removed and removes it from the queue.
	 * Correct links are established after the node is removed, as well.
	 * @param toBeRemoved
	 * @return
	 */
	public boolean remove(Node<E> toBeRemoved) {
		if(size == 0) {
			return false;
		} else if(size == 1) {
			E currData = front.getData();
			if(((toBeRemoved.getData()).equals(currData)) && (toBeRemoved.priority == front.priority)){
				front = null;
				size--;
				return true;
			} else {
				return false;
			}
		} else {
			Node<E> curr = front;
			
			if(((toBeRemoved.getData()).equals(curr.getData())) && (toBeRemoved.priority == curr.priority)){
				front = curr.getNext();
				size--;
				return true;
			}
			
			for(int i = 2; i <= size; i++) {
				if(((toBeRemoved.getData()).equals(curr.next.getData())) && (toBeRemoved.priority == curr.next.priority)){
					if(i == size) {
						curr.next = null;
						size--;
						return true;
					} else {
						curr.next = curr.next.next;
						size--;
						return true;
					}
				} else {
					curr = curr.next;
				}
			}
			
			return false;
		}
	}
	
	/**
	 * Returns an instance of Iter class that was defined inside the ListQueue<E> class.
	 * @return
	 */
	public Iterator<E> iterator(){
		return new Iter();
	}
}
