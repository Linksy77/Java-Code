package HW3;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TestTaskList<E> {
	private TaskList<E> toDoList;
	Scanner scan;
	
	/**
	 * Main method.
	 * Creates a TestTaskList object and calls the printMenu method from it.
	 * @param args
	 */
	public static void main(String[] args) {
		TestTaskList list1 = new TestTaskList();
		list1.printMenu();
	}
	
	/**
	 * Prints 8 menu items.
	 * Takes user input indicating which operation the user chooses.
	 * Repeatedly does so until user enters 8 to quit the program.
	 * Prints an error message and asks for user input again if user input is not
	 * an integer value between 1 and 8 (inclusive).
	 */
	public void printMenu() {
		
		toDoList = new TaskList<E>();
		
		boolean keepRunning = true;
		
		System.out.println("~~~ TO-DO List Program, created by Cecilia Esteban ~~~");
		
		while(keepRunning == true) {
			
			if(toDoList.getActive().getSize() == 0) {
				System.out.println("==> Currently there are NO items in the To-Do List");
			} else {
				System.out.println("Current TO-DO List:");
				System.out.println("-------------------");
				toDoList.showActiveTasks();
			}
			
			System.out.println("To add a new task without priority information, press 1.");
			System.out.println("To add a new task with a priority information, press 2.");
			System.out.println("To cross off the task at the top of the list, press 3.");
			System.out.println("To cross off a certain task in the list, press 4.");
			System.out.println("To see the top 3 highest priority tasks, press 5.");
			System.out.println("To see the completed tasks, press 6.");
			System.out.println("To see all the tasks that have been completed or are still active, press 7.");
			System.out.println("To quit the program, press 8.");
			
			Scanner scan = new Scanner(System.in);
			
			try {
				int menuItem = scan.nextInt();
				keepRunning = processMenuItem(menuItem);
			} catch(InputMismatchException ime) {
				System.out.println("ERROR! Please enter a number between 1 and 8 (included).");
			}
		}
		
	}
	
	/**
	 * Helper method for printMenu().
	 * Processes user input (parameter menuItem) and calls the appropriate function
	 * for each operation from the toDoList object.
	 * @param menuItem
	 * @return
	 */
	private boolean processMenuItem(int menuItem) {
		if(menuItem < 1 || menuItem > 8) {
			System.out.println("ERROR! Please enter a number between 1 and 8 (inlcuded).");
			return true;
		} else if(menuItem == 1){
			System.out.println("Please enter the task description:");
			scan = new Scanner(System.in);
			E task = (E) scan.nextLine();
			boolean success = toDoList.createTask(task);
			if(success == true) {
				System.out.println("Successfully entered the task to the to-do list!");
			}
			return true;
			
		} else if(menuItem == 2) {
			System.out.println("Please enter the task description:");
			scan = new Scanner(System.in);
			E task = (E) scan.nextLine();
			System.out.println("Please enter a priority number (1 indicates highest priority, increasing numbers show lower priority) :");
			scan = new Scanner(System.in);
			try {
				int prior = scan.nextInt();
				boolean success = toDoList.createTask(task, prior);
				if(success == true) {
					System.out.println("Successfully entered the task to the to-do list!");
				}
			} catch(InputMismatchException ime) {
				System.out.println("ERROR! Input was not an integer; please enter an integer next time.");
			}
			return true;
			
		} else if(menuItem == 3) {
			try {
				String completedTask = (String) toDoList.getActive().getFront().getData();
				boolean success = toDoList.crossOffMostUrgent();
				if(success == true) {
					System.out.println("Task is completed and removed from the list: " + completedTask);
					System.out.println("Successfully removed the most urgent task/top of the list task!");
				}
				return true;
			} catch(NullPointerException npe) {
				System.out.println("ERROR! Highest priority task does not exist.");
			}
			return true;
			
		} else if(menuItem == 4) {
			System.out.println("Please enter the task number you would like to cross off the list :");
			scan = new Scanner(System.in);
			try {
				int taskNum = scan.nextInt();
				boolean success = toDoList.crossOffTask(taskNum);
				if(success == true) {
					ListQueue.Node<E> curr = toDoList.getCompleted().getFront();
					
					String completedTask = "";
					for(int i = 1; i <= toDoList.getCompleted().getSize(); i++) {
						if(i == toDoList.getCompleted().getSize()) {
							completedTask = (String) curr.getData();
							break;
						} else {
							if(curr.getNext() == null) {
								completedTask = (String) curr.getData();
							} else {
								curr = curr.getNext();
							}
						}
					}
					System.out.println("Task number " + taskNum + " is removed: " + completedTask);
					System.out.println("Successfully removed the task number: " + taskNum);
				}
			} catch(InputMismatchException ime) {
				System.out.println("ERROR! Input was not an integer; please enter an integer next time.");
			}
			return true;
			
		} else if(menuItem == 5) {
			System.out.println("Top 3 highest priority tasks:");
			System.out.println("------------------------------");
			System.out.println("Printing Top Three Tasks...");
			toDoList.printTopThreeTasks();
			return true;
			
		} else if(menuItem == 6) {
			System.out.println("Completed Tasks:");
			System.out.println("----------------");
			toDoList.showCompletedTasks();
			return true;
		
		} else if(menuItem == 7) {
			System.out.println("All of the Tasks - Both completed and active:");
			System.out.println("---------------------------------------------");
			toDoList.showAllTasks();
			return true;
		} else {
			return false;
		}
	}
}
