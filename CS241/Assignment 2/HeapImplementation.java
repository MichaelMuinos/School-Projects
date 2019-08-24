package edu.cpp.cs.cs241.prog_assgmnt_2;

import java.util.LinkedList;
/**
 * The following class implements the heap interface. It is structured in a 
 * tree based representation. There is the ability to add and remove from the heap,
 * turn the heap into an array and vice versa, as well as sort the contents.
 * @author Michael
 *
 */
public class HeapImplementation<V extends Comparable<V>> implements Heap<V> {
/**
 * Placeholder for the root node.
 */
	@SuppressWarnings("rawtypes")
	private Node rootNode;
/**
 * Placeholder for a temporary node to fill another's place.
 */
	@SuppressWarnings("rawtypes")
	private Node temp = null;
/**
 * Keeps track of the amount of nodes in the heap.
 */
	private int size = 0;

/**
 * Instantiates the class in which organizes the array and heap structure.
 */
	@SuppressWarnings("rawtypes")
	private HeapSort sort = new HeapSort();
	
/**
 * Adds a value to the heap data structure. If the size 0,
 * the value will automatically become the root node. It is held in the
 * placeholder of a value.
 * 
 * @param Element added to heap.
 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void add(V value) {
		
		Node tempNode = temp;
		int num = 1;
		
		if(rootNode == null) {
			//Start of tree
			rootNode = new Node(value);
			//Makes sure the root node does not have parent
			//Or else he wouldn't be the root node
			rootNode.parent = null;
		} else {
			
			//Determine path of tempNode
			//Size + 1
			tempNode = determinePath(num);
			
			//Assign to left
			if(tempNode.leftChild == null) {
				tempNode.leftChild = new Node(value);
				tempNode.leftChild.parent = tempNode;
				tempNode = tempNode.leftChild;
			//Assign to right
			} else {
				tempNode.rightChild = new Node(value);
				tempNode.rightChild.parent = tempNode;
				tempNode = tempNode.rightChild;
			}
			//Fix heap
			siftUp(tempNode);
		}
		size++;
	}
	
/**
 * The following method makes it so that the heap data structure
 * complies with the max heap property by moving elements upward throughout
 * the tree.
 * 
 * @param Compares its current value to its parent and decides which direction to move to.
 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void siftUp(Node node) {
		
		if(node.parent == null) return;
		//compare node value with parent
		if(node.value.compareTo(node.parent.value) > 0) {
			V tempNode = (V) node.parent.value;
			node.parent.value = node.value;
			node.value = tempNode;
			//Recursive call to ensure stability
			siftUp(node.parent);
		}
		
	}
	
/**
 * Returns the minimum value in the heap.
 * @return
 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public V findMin() {
		
		if(rootNode == null) return null;
		Node tempNode = rootNode;
		do {
			tempNode = tempNode.leftChild;
		} while(tempNode.leftChild != null);
		
		return (V) tempNode.value;
		
	}
	
/**
 * Does the same thing as SiftUp, only in the opposite direction. After ran,
 * the heap will comply with the max heap property. 
 * 
 * @param Its right and left children are compared to determine which
 * direction the swap will take place.
 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void siftDown(Node node) {
		
		if(size == 0) return;
		if(node.leftChild != null && node.rightChild != null) {
			if(node.value.compareTo(node.leftChild.value) == 0 && node.value.compareTo(node.rightChild.value) == 0) return;
			//do nothing if true
			else if(node.value.compareTo(node.leftChild.value) < 0 || node.value.compareTo(node.rightChild.value) < 0) {
				//else, check left child and right child to see where to sift down
				//sift down will force compliance with max heap property
				if(node.leftChild.value.compareTo(node.rightChild.value) > 0) {
					//Swaps parent with left child
					V tempNode = (V) node.value;
					node.value = node.leftChild.value;
					node.leftChild.value = tempNode;
					siftDown(node);
				} else if(node.leftChild.value.compareTo(node.rightChild.value) <= 0) {
					//Swaps parent with right child
					V tempNode = (V) node.value;
					node.value = node.rightChild.value;
					node.rightChild.value = tempNode;
					siftDown(node);
				}
			}
		} else if(node.rightChild == null && node.leftChild != null && node.leftChild.value.compareTo(node.value) > 0) {
			V tempNode = (V) node.value;
			node.value = node.leftChild.value;
			node.leftChild.value = tempNode;
			siftDown(node);
			return;
		}
		
	}
	
/**
 * Returns the maximum value in the heap.
 * @return
 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public V findMax() {
		
		if(rootNode == null) return null;
		Node tempNode = rootNode;
		do {
			tempNode = tempNode.rightChild;
		} while(tempNode.rightChild != null);
		
		return (V) tempNode.value;
		
	}
	
/**
 * Determines in which direction (left or right) a node
 * will go when in attempt to put the heap into max heap property.
 * It will take the binary value of a string to refer to its location. By taking
 * the substring of argument 1, it decides if it will go left or right, twice.
 * @param Determines if the binary string will be altered or not depending
 * on if an element was just added.
 * @return
 */
	@SuppressWarnings("rawtypes")
	private Node determinePath(int num) {
		
		String count = "";
		Node tempNode = rootNode;
		int addToSize = size + 1;
		
		//1st line turns it into byte code
		//2nd line grabs the 2nd digit to decide if it goes left or right
		// '0' = left
		// '1' = right
		
		if(num == 1) {
			count = Integer.toBinaryString(addToSize);
			count = count.substring(1);
		} else {
			count = Integer.toBinaryString(size);
			count = count.substring(1);
		}
		
		for(int i = 0; i < count.length(); i++) {
			if(count.charAt(i) == '0' && tempNode.leftChild != null) tempNode = tempNode.leftChild;
			if(count.charAt(i) == '1' && tempNode.rightChild != null) tempNode = tempNode.rightChild;
		}
		
		return tempNode;
		
	}
	
/**
 * Removes an element from the heap data structure. Once removed, it will perform SiftDown
 * as needed. Additionally, the size will be decreased by one.
 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public V remove() {

		Node temp = rootNode;
		Node pathNode = determinePath(0);
		Node node2 = pathNode.parent;
		boolean leftOrRight = true;
		
		switch(size) {
		
		case 0:
			
			return null;
			
		case 1:
			
			rootNode = null;
			size--;
			
			return (V) temp.value;
			
		}
			
		if(size == 2) {
			
			V temp2 = (V) rootNode.value;
			rootNode.value = temp.leftChild.value;
			rootNode.leftChild.parent = null;
			rootNode.leftChild = null;
			size--;
			
			return temp2;
			
		} else if(node2.rightChild != null) {
			
			pathNodeEqualsRootNode(pathNode,node2,leftOrRight);
			
		} else {
			
			leftOrRight = false;
			pathNodeEqualsRootNode(pathNode,node2,leftOrRight);
			
		}
		
		size--;
		//Recursive call to ensure stability when removing.
		//from top to bottom
		siftDown(rootNode);
		return (V) temp.value;
	
	}
	
/**
 * Swaps the node that is being determined which path to take with the root node.
 * @param Temp placeholder node for the traversed node.
 * @param Temp node for the root node.
 * @param Identifies whether the right or left child becomes null.
 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void pathNodeEqualsRootNode(Node n1, Node n2, boolean leftOrRight) {
		
		if(leftOrRight == true) {
			//Setting each nodes left child to be the same value
			n1.leftChild = rootNode.leftChild;
			//Setting each nodes right child to be the same value
			n1.rightChild = rootNode.rightChild;
			//present nodes right child becomes null
			n2.rightChild = null;
			//traversed nodes parent becomes null
			n1.parent = null;
			rootNode = n1;
		} else {
			//Setting each nodes left child to be the same value
			n1.leftChild = rootNode.leftChild;
			//Setting each nodes right child to be the same value
			n1.rightChild = rootNode.rightChild;
			//present nodes left child becomes null
			n2.leftChild = null;
			//traversed nodes parent becomes null
			n1.parent = null;
			rootNode = n1;
		}
		
	}
	
/**
 * Takes the heap elements and puts it into an array representation.
 *
 *@param Holds elements of heap structure.
 *
 */
	@SuppressWarnings({ "unchecked", "rawtypes"})
	public V[] toArray() {
		
		Object[] array = null;
		
		if(size > 0) {
			array = new Object[size];
			V[] heapToArray = (V[]) java.lang.reflect.Array.newInstance(array.getClass().getComponentType(), size);
			Node tempNode = rootNode;
		
			LinkedList<Node> linkedList = new LinkedList<Node>();
			linkedList.add(tempNode);
		
			for(int i = 0; i < size; i++) {
				tempNode = linkedList.remove(0);
				if(tempNode.leftChild != null) linkedList.add(tempNode.leftChild);
				if(tempNode.rightChild != null) linkedList.add(tempNode.rightChild);
				heapToArray[i] = (V) tempNode.value;
			}
			return heapToArray;
		} else {
			//Empty heap
			return null;
		}
		
		
		
	}
	
/**
 * Puts the values in the array into a heap representation.
 * 
 * @param Holds values of elements that need to be added to heap.
 * 
 */
	public void fromArray(V[] array) {
		V tempValue;
		//move all values to heap
		for(int i = 0; i < array.length; i++) {
			tempValue = array[i];
			add(tempValue);
		}
	}
/**
 * Returns amount of elements inside the heap.
 * @return
 */
	public int getSize() {
		return size;
	}
	
/**
 * Sorts the contents of the array in which is represented in heap form as well. You can call toArray,
 * then call this method to sort the array.
 * 
 * @param Holds elements in which are to be sorted.
 * 
 */
	@SuppressWarnings("unchecked")
	public V[] getSortedContents(V[] array) {
		
		V[] sortedArray = (V[]) java.lang.reflect.Array.newInstance(array.getClass().getComponentType(), size);
		sortedArray = (V[]) sort.fix(sortedArray);
		return sortedArray;
		
	}
	
}
