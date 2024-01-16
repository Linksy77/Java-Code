package HW3;

import java.util.Iterator;

public class TaskList<E>{
	private ListQueue<E> all;
	private ListQueue<E> active;
	private ListQueue<E> completed;
	private int LOW_PRIORITY = Integer.MAX_VALUE;
	private int HIGH_PRIORITY = 1;
	
	/**
	 * Initializes all the ListQueues in the attributes.
	 */
	public TaskList() {
		all = new ListQueue<E>();
		active = new ListQueue<E>();
		completed = new ListQueue<E>();
	}
	
	/**
	 * Adds the item into active and all queues with default priority as LOW_PRIORITY.
	 * If item is null, it will return false. Otherwise, returns true.
	 * @param item
	 * @return false if item is null; otherwise, true.
	 */
	public boolean createTask(E item) {
		if(item == null) {
			return false;
		} else {
			active.offer(item, LOW_PRIORITY);
			all.offer(item, LOW_PRIORITY);
			return true;
		}
	}
	
	/**
	 * Adds the item into active and all queues.
	 * If item is null, it will return false. Otherwise returns true.
	 * @param item
	 * @param priority
	 * @return false if item is null; otherwise, true.
	 */
	public boolean createTask(E item, int priority) {
		if(item == null) {
			return false;
		} else {
			active.offer(item, priority);
			all.offer(item, priority);
			return true;
		}
	}
	
	/**
	 * Prints the top 3 highest priority tasks.
	 */
	public void printTopThreeTasks() {
		if(active.getSize() <= 3) {
			ListQueue.Node<E> curr = active.getFront();
			for(int i = 1; i <= active.getSize(); i++) {
				System.out.println(i + ".  " + curr.getData());
				curr = curr.getNext();
			}
		} else {
			ListQueue.Node<E> curr = active.getFront();
			for(int i = 1; i <= 3; i++) {
				System.out.println(i + ".  " + curr.getData());
				curr = curr.getNext();
			}
		}
	}
	
	/**
	 * Prints all active tasks with use of helper function printTasks(ListQueue<E> queue).
	 */
	public void showActiveTasks() {
		printTasks(active);
	}
	
	/**
	 * Prints all completed tasks with use of helper function printTasks(ListQueue<E> queue).
	 */
	public void showCompletedTasks() {
		printTasks(completed);
	}
	
	/**
	 * Prints all tasks with use of helper function printTasks(ListQueue<E> queue).
	 */
	public void showAllTasks() {
		printTasks(all);
	}
	
	/**
	 * Helper method that utilizes the iterator() to iterate through the queue elements
	 * and print them with numbers.
	 * Front of the queue has task number 1 and each subsequent node has an increasing
	 * task number.
	 * @param queue
	 */
	private void printTasks(ListQueue<E> queue) {
		Iterator<E> iter = queue.iterator();
		E curr;
		
		for(int i = 1; i <= queue.getSize(); i++) {
			if(iter.hasNext() == true) {
				curr = iter.next();
				System.out.println(i + ". " + curr);
			}
		}
	}
	
	/**
	 * Removes the highest priority task from the front of the queue and return true if it
	 * successfully removes.
	 * Prints an error message and returns false if it does not exist.
	 * @return
	 */
	public boolean crossOffMostUrgent() {
		E frontQueue = active.poll();
		completed.addRear(frontQueue);
		if(frontQueue != null) {
			return true;
		} else {
			System.out.println("ERROR! Highest priority task does not exist.");
			return false;
		}
	}
	
	/**
	 * Removes the task at the location identified by taskNumber.
	 * Front of the queue will have 1 as the taskNumber and subsequent numbers will be
	 * incremented by one for each next task.
	 * Returns true for successful run.
	 * Returns false if task number is greater than the size of the active list or if
	 * task could not be removed successfully.
	 * @param taskNumber
	 * @return
	 */
	public boolean crossOffTask(int taskNumber) {
		if(taskNumber < 1 || taskNumber > active.getSize()) {
			System.out.println("Unsuccessful operation! Please try again!");
			return false;
		} else {
			ListQueue.Node<E> curr = active.getFront();
			for(int i = 1; i <= active.getSize(); i++) {
				if(i == taskNumber) {
					completed.addRear(curr.getData());
					active.remove(curr);
					return true;
				} else {
					if(curr.getNext() != null) {
						curr = curr.getNext();
					} else {
						return false;
					}
					
				}
			}
		}
		return true;
	}
	
	/**
	 * Accessor for ListQueue<E> all (containing all items).
	 * @return
	 */
	public ListQueue<E> getAll(){
		return all;
	}
	
	/**
	 * Accessor for ListQueue<E> completed (containing all completed items).
	 * @return
	 */
	public ListQueue<E> getCompleted(){
		return completed;
	}
	
	/**
	 * Accessor for ListQueue<E> active (containing all active items).
	 * @return
	 */
	public ListQueue<E> getActive(){
		return active;
	}
}
