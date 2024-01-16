package HW4;

import java.util.Random;
import java.util.Stack;

public class Treap<E extends Comparable<E>> {
	
	private static class Node<E> {
		public E data; // key for the search
		public int priority; // random heap priority
		public Node<E> left;
		public Node<E> right;
		
		/**
		 * Creates a new node with the given data and priority.
		 * Pointers to child nodes are null.
		 * If data is null: exception is thrown.
		 * @param data
		 * @param priority
		 */
		public Node(E data, int priority) {
			if(data == null) {
				throw new IllegalArgumentException("ERROR: data value is null. Please input a valid data value.");
			} else {
				this.data = data;
			}
			this.priority = priority;
			left = null;
			right = null;
		}
		
		/**
		 * Performs a right rotation and returns a reference to the root of the result.
		 * @return reference to the root of the result
		 */
		Node<E> rotateRight(){
			Node<E> leftN = this.left;
			Node<E> leftRightN = this.left.right;
			leftN.right = this;
			this.left = leftRightN;
			return leftN;
		}
		
		/**
		 * Performs a left rotation and returns a reference to the root of the result.
		 * @return reference to the root of the result
		 */
		Node<E> rotateLeft(){
			Node<E> rightN = this.right;
			Node<E> rightLeftN = this.right.left;
			rightN.left = this;
			this.right = rightLeftN;
			return rightN;
		}
		
		/**
		 * Returns a representation of the node as a String.
		 */
		@Override
		public String toString() {
			return "(key = " + data + " , priority = " + priority + ")";
		}
	}
	
	
	private Random priorityGenerator;
	private Node<E> root;
	
	/**
	 * Creates an empty treap. priorityGenerator is initialized using Random().
	 */
	public Treap() {
		this.root = null;
		this.priorityGenerator = new Random();
	}
	
	/**
	 * Creates an empty treap. priorityGenerator is initialized using Random(seed).
	 * @param seed
	 */
	public Treap(long seed) {
		this.root = null;
		this.priorityGenerator = new Random(seed);
	}
	
	/**
	 * Inserts the given element into the tree.
	 * Returns true if a node with the key was successfully added to the treap.
	 * Returns false and does not modify the treap if a node containing the given key
	 * already exists.
	 * @param key
	 * @return
	 */
	boolean add(E key) {
		if(key == null) {
			return false;
		} else if(find(key) == true) {
			return false;
		} else {
			add(key, priorityGenerator.nextInt(100));
			return true;
		}
	}
	
	/**
	 * Inserts the given element into the tree.
	 * Returns true if a node with the key was successfully added to the treap.
	 * Returns false and does not modify the treap if a node containing the given key
	 * already exists.
	 * @param key
	 * @param prioity
	 * @return
	 */
	boolean add(E key, int priority) {
		Stack<Node<E>> treeStack = new Stack<Node<E>>();
		Node<E> nodeToIns = new Node<E>(key, priority);
		
		if(key == null) {
			return false;
		} else if(find(key) == true) {
			return false;
		} else {
			if(this.root == null) {
				this.root = new Node<E>(key, priority);
				return true;
			} else {
				Node<E> curr = this.root;
				while(curr != null) {
					if(key.compareTo(curr.data) > 0) {
						treeStack.push(curr);
						
						if(curr.right != null) {
							curr = curr.right;
						} else {
							treeStack.push(nodeToIns);
							curr.right = nodeToIns;
							reheap(treeStack);
							curr = null;
						}	
					} else if(key.compareTo(curr.data) < 0) {
						treeStack.push(curr);
						
						if(curr.left != null) {
							curr = curr.left;
						} else {
							treeStack.push(nodeToIns);
							curr.left = nodeToIns;
							reheap(treeStack);
							curr = null;
						}
					}
				}
			}
		}
		return true;
	}
	
	/**
	 * Helper function for add(E key, int priority) that restores the heap invariant.
	 * @param addedNode
	 * @param treeStack
	 */
	private void reheap(Stack<Node<E>> treeStack) {
		
		Node<E> curr = treeStack.pop();
		
		while(treeStack.size() > 0) {
			Node<E> par = treeStack.pop();
			if(curr.priority > par.priority) {
				if((curr.data).compareTo(par.data) > 0) {
					curr = par.rotateLeft();
				} else if((curr.data).compareTo(par.data) < 0) {
					curr = par.rotateRight();
				}
			} else if(curr.priority < par.priority) {
				break;
			}
			
			if(treeStack.size() > 0) {
				Node<E> grandpar = treeStack.peek();
				if(grandpar.left == par) {
					grandpar.left = curr;
				} else if(grandpar.right == par) {
					grandpar.right = curr;
				}
			} else {
				root = curr;
			}
		}
	}
	
	/**
	 * Deletes the node with the given key from the treap and return true.
	 * Returns false and does not modify the treap if the key was not found.
	 * @param key
	 * @return
	 */
	boolean delete(E key) {
		if(key == null) {
			return false;
		} else if(find(key) == false) {
			return false;
		} else {
			root = deleteHelper(key, root);
			return true;
		}
	}
	
	/**
	 * Helper method for delete(E key) that locates the node the user wishes
	 * to delete from the treap.
	 * @param key
	 * @param curr
	 * @return
	 */
	private Node<E> deleteHelper(E key, Node<E> curr) {
		if(curr != null) {
			if(key.compareTo(curr.data) > 0) {
				curr.right = deleteHelper(key, curr.right);
			} else if(key.compareTo(curr.data) < 0) {
				curr.left = deleteHelper(key, curr.left);
			} else {
				if(curr.left == null) {
					return curr.right;
				} else if(curr.right == null) {
					return curr.left;
				} else {
					Node<E> rtLeftNode = curr.right;
					while(rtLeftNode.left != null) {
						rtLeftNode = rtLeftNode.left;
					}
					curr.data = rtLeftNode.data;
					curr.right = deleteHelper(curr.data, curr.right);
				}
			}
		}
		return curr;
	}
	
	/**
	 * Finds a node with the given key in the treap rooted at root.
	 * Returns true if it finds it, and false otherwise.
	 * @param root
	 * @param key
	 * @return
	 */
	private boolean find(Node<E> root, E key) {
		while(root != null) {
			if((key.compareTo(root.data)) > 0) {
				root = root.right;
			} else if((key.compareTo(root.data)) < 0) {
				root = root.left;
			} else {
				// key.compareTo(root.data) == 0, meaning key was found
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Finds a node with the given key in the treap.
	 * Returns true if it finds it, and false otherwise.
	 * @param key
	 * @return
	 */
	public boolean find(E key) {
		if(key == null) {
			return false;
		} else {
			return find(root, key);
		}
	}
	
	/**
	 * Carries out a preorder traversal of the tree and retruns a representation
	 * of the nodes as a string.
	 */
	public String toString() {
		return toString(root, 0);
	}
	
	/**
	 * Helper method for toString() method with parameters for the current node
	 * and the level to begin at.
	 * @param curr
	 * @param lvl
	 * @return
	 */
	public String toString(Node<E> curr, int lvl) {
		StringBuilder str = new StringBuilder();
		
		for(int i = 0; i < lvl; i++) {
			str.append(" ");
		}
		
		if(curr == null) {
			str.append("null\n");
		} else {
			str.append(curr.toString() + "\n");
			str.append(toString(curr.left, lvl + 1));
			str.append(toString(curr.right, lvl + 1));
		}
		
		return str.toString();
	}
	
}