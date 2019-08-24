package edu.cpp.cs.cs241.prog_assgmnt_3;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
/**
 * The following class is an implementation of the red black tree data structure. It has the abilities to add entries,
 * remove entries, lookup entries, and convert the red black tree to a string.
 * @author Michael
 *
 * @param <K>
 * @param <V>
 */
public class RedBlackTree<K extends Comparable<K>,V> implements Tree<K,V>, Serializable {
/**
 * Version ID for the serializable interface.	
 */
	private static final long serialVersionUID = 5520324445122353620L;
/**
 * Field for the head of the tree.
 */
	private Node<K,V> rootNode = null;
/**
 * Number of nodes in the red black tree.
 */
	private int size = 0;
/**
 * List in which holds a group of nodes that are added to the red black tree.
 */
	private ArrayList<Node<K,V>> arrayList = new ArrayList<Node<K,V>>();
/**
 * The method below will add a node to the red black tree if the specified key has not
 * already been inputed. If it has, it will simply update the value and NOT increase the size.
 * If it has not been placed before, it will run through the specified add cases to keep the invariants
 * satisfied and add the node.
 */
	@Override
	public void add(K key, V value) {
		//If the tree is empty, assign the new node as the root.
		if(rootNode == null) {
			rootNode = new Node<K,V>(key,value,Color.BLACK);
			rootNode.rightChild = new Node<K,V>(Color.BLACK);
			rootNode.rightChild.parent = rootNode;
			rootNode.leftChild = new Node<K,V>(Color.BLACK);
			rootNode.leftChild.parent = rootNode;
			System.out.println("Added root element.");
			arrayList.add(rootNode);
			size++;
			return;
		} else {
			Node<K,V> addNode = traverseTree(key,true);
			Node<K,V> newNode = new Node<K,V>(key,value,Color.RED);
			//Check if key has already been mapped.
			//If it has, just replace that node with a new value!
			if(arrayList.size() != 0) {
				Node<K,V> tempNode = null;
				for(int i = 0; i < arrayList.size(); i++) {
					tempNode = arrayList.get(i);
					if(tempNode != null && tempNode.key.compareTo(key) == 0) {
						System.out.println("Updating value.");
						tempNode.value = value;
						return;
					}
				}
			}
			//Key has not been mapped yet.
			//We must create a new node to copy over the key and values that were put inside
			//the parameters.
			newNode(addNode,newNode);
			//Start the case series
			System.out.println("Added element.");
			//Add the node to be searched later on...
			arrayList.add(addNode);
			addCaseOne(addNode);
			size++;
		}
	}
/**
 * Takes in the traversed node used in the add method and then a new instantiated node and copy's
 * over the contents of the key, value, children, and color to the new node so that it can run through the add cases
 * with the correct details.	
 * @param traversedNode
 * @param newNode
 */
	public void newNode(Node<K,V> traversedNode, Node<K,V> newNode) {
		//Copying over the nodes.
		traversedNode.leftChild = newNode.leftChild;
		traversedNode.rightChild = newNode.rightChild;
		traversedNode.key = newNode.key;
		traversedNode.value = newNode.value;
		traversedNode.nodeColor = newNode.nodeColor;
		traversedNode.leftChild.parent = traversedNode;
		traversedNode.rightChild.parent = traversedNode;
	}
/**
 * Runs through all of the nodes currently inside the tree in order to determine where the new node
 * shall be placed. Another traversal part is done for the removal portion to determine in which place
 * will the node be removed from the red black tree.
 * @param key
 * @param add
 * @return
 */
	private Node<K,V> traverseTree(K key, boolean add) {
		//Adding portion of traversing
		//Could use array list for this part
		Node<K,V> tempNode = rootNode;
		if(add == true) {
			while(!leafNode(tempNode)) {
				if(!leafNode(tempNode) && tempNode.key.compareTo(key) == 0) {
					return rootNode;
				} else if(tempNode.key.compareTo(key) < 0) {
					if(tempNode.leftChild != null) {
						tempNode = tempNode.leftChild;
					}
				} else if(tempNode.key.compareTo(key) >= 0) {
					if(tempNode.rightChild != null) {
						tempNode = tempNode.rightChild;
					}
				}
			}
			return tempNode;
		} else {
			//This might not be needed any longer since I used an array list for determining what elements
			//in the red black tree.
			while(!leafNode(tempNode)) {
				switch(tempNode.key.compareTo(key)) {
				case 1:
					if(tempNode.rightChild != null) 
						tempNode = tempNode.rightChild;
					break;
				case 0:
					return tempNode;
				case -1:
					if(tempNode.leftChild != null)
						tempNode = tempNode.leftChild;
					break;
				}
			}
			return null;
		}
	}
/**
 * First add case: The new node is the root of the tree, therefore making
 * its parent null.
 * @param addNode
 */
	private void addCaseOne(Node<K,V> addNode) {
		//New node is the root of the tree, thus its parent is null
		if(addNode.parent == null) {
			addNode.nodeColor = Color.BLACK;
			System.out.println("\tAdd case 1");
		} else {
			addCaseTwo(addNode);
		}
	}
/**	
 * Second add case: The new node has a black colored parent in which that does
 * NOT break any invariants.
 * @param addNode
 */
	private void addCaseTwo(Node<K,V> addNode) {
		//Class case
		//New node has a black colored parent
		if(addNode.parent.nodeColor == Color.BLACK) {
			System.out.println("\tAdd case 2");
			return;
		} else {
			addCaseThree(addNode);
		}
	}
/**
 * Third add case: The node being added to the red black tree has an uncle node in which is red.
 * This causes in imbalance for invariant 4. To solve, we must color the node's parent black and uncle black
 * as well as its grandparent red. It must iterate to the first add case again to ensure the grandparent is not the root node!
 * @param addNode
 */
	//TODO: error, when i add too many elements, it says that the uncle is null
	private void addCaseThree(Node<K,V> addNode) {
		//When adding, the parent's sibling is red causing a violation.
		if(addNode.uncle() != null && addNode.uncle().nodeColor == Color.RED) {
			//Two siblings are now black
			addNode.parent.nodeColor = Color.BLACK;
			addNode.uncle().nodeColor = Color.BLACK;
			//Grandparent must be red
			addNode.grandparent().nodeColor = Color.RED;
			//Check to see if the grandparent is the root node, if so, change it back to black
			//to satisfy property.
			System.out.println("\tAdd case 3");
			addCaseOne(addNode.grandparent());
		} else {
			addCaseFour(addNode);
		}
	}
/**
 * Add case four: The node being added has an uncle in which is black and a parent in which is red.
 * This possibly breaks invariant 5 because there could be more paths to black nodes along one path of the
 * child. To solve, we must find the internal node or external node and rotate accordingly.
 * @param addNode
 */
	private void addCaseFour(Node<K,V> addNode) {
		//Check if we must rotate about the parent: left or right
		boolean call = true;
		if(addNode.uncle() != null && addNode.uncle().nodeColor == Color.BLACK 
				&& addNode.parent.nodeColor == Color.RED) {
			System.out.println("\tAdd case 4");
			if(internalNode(addNode,false,false)) {
				//Rotate right about parent
				rotateRight(addNode.parent);
				//set up for case five
				addCaseFive(addNode.rightChild);
				call = false;
			} else if(internalNode(addNode,true,true)) {
				//Rotate left about parent
				rotateLeft(addNode.parent);
				addCaseFive(addNode.leftChild);
				call = false;
			} 
		}
		if(call) {
			addCaseFive(addNode);
		}
	}
/**
 * Add case five: The node being added has an uncle in which is black. This is the last stage of adding because
 * there is no other color the uncle node could possibly be. To solve, we must see of the external node is of the
 * left child or right child, then rotate accordingly.
 * @param addNode
 */
	private void addCaseFive(Node<K,V> addNode) {
		if(addNode.uncle() != null && addNode.uncle().nodeColor == Color.BLACK) {
			System.out.println("\tAdd case 5");
			if(externalNode(addNode,false,false)) {
				//Rotate left about grandparent
				rotateLeft(addNode.grandparent());
			}
			if(externalNode(addNode,true,true)) {
				//Rotate right about grandparent
				rotateRight(addNode.grandparent());
			}
		}
	}
/**
 * The following method iterates through the array list just to see if the key has been
 * placed before. If not, nothing happens. If it has, it will go to the remove helper method in which finds
 * the in order predecessor and goes through the stages of removal.
 */
	@Override
	public V remove(K key) {
		if(size == 0) {
			System.out.println("There are no items to remove.");
			return null;
		}
		if(arrayList.size() != 0) {
			Node<K,V> tempNode = null;
			for(int i = 0; i < arrayList.size(); i++) {
				tempNode = arrayList.get(i);
				if(tempNode != null && tempNode.key.compareTo(key) == 0) {
					arrayList.remove(tempNode);
					System.out.println("Removing element.");
					removeHelper(key);
					size--;
					return tempNode.value;
				}
			}
		}
		System.out.println("Couldn't remove the element.");
		return null;
	}
/**	
 * The following method will find the in order predecessor and determines the color of such node.
 * Depending on if the node is black or not, it will start the removal case series.
 * @param key
 */
	private void removeHelper(K key) {
		Node<K,V> node = traverseTree(key,false);
		Node<K,V> childNode = null;
		if(node.leftChild != null && node.rightChild != null) {
			Node<K,V> leftNode = node.leftChild;
			Node<K,V> predNode = null;
			while(leftNode.rightChild != null) {
				leftNode = leftNode.rightChild;
			}
			predNode = leftNode;
			copyNode(node,predNode);
		}
		if(node.rightChild == null) {
			childNode = node.leftChild;
		} else {
			childNode = node.rightChild;
		}
		if(node.nodeColor == Color.BLACK) {
			node.nodeColor = childNode.nodeColor;
			removeCaseOne(node);
		}
		fixTree(node,childNode);
	}
/**
 * Returns the array list of nodes.
 * @return
 */
	public ArrayList<Node<K, V>> getArrayList() {
		return arrayList;
	}
/**
 * Swaps the node color of the specified node's sibling and parent.
 * @param node
 */
	private void swapNodeColorSiblingAndParent(Node<K,V> node) {
		//Swapping colors of input
		Color nodeOneColor = null;
		Color nodeTwoColor = null;
		
		node.parent.nodeColor = nodeOneColor;
		node.sibling().nodeColor = nodeTwoColor;
		node.parent.nodeColor = nodeTwoColor;
		node.sibling().nodeColor = nodeOneColor;
	}
/**	
 * Swaps the node color of the specified node's sibling and sibling's left child.
 * @param node
 */
	private void swapNodeColorSiblingAndSiblingLeftChild(Node<K,V> node) {
		//Swapping colors of input.
		//Specifically, the sibling of the node and the sibling's left child.
		Color nodeOneColor = null;
		Color nodeTwoColor = null;
		
		node.sibling().nodeColor = nodeOneColor;
		node.sibling().leftChild.nodeColor = nodeTwoColor;
		node.sibling().nodeColor = nodeTwoColor;
		node.sibling().leftChild.nodeColor = nodeOneColor;
	}
/**
 * Swaps the node color of the specified node's sibling and sibling's right child.	
 * @param node
 */
	private void swapNodeColorSiblingAndSiblingRightChild(Node<K,V> node) {
		//Swapping colors of input.
		//Specifically, the sibling of the node and the sibling's right child.
		Color nodeOneColor = null;
		Color nodeTwoColor = null;
		
		node.sibling().nodeColor = nodeOneColor;
		node.sibling().rightChild.nodeColor = nodeTwoColor;
		node.sibling().nodeColor = nodeTwoColor;
		node.sibling().rightChild.nodeColor = nodeOneColor;
	}
/**
 * Copy's the key and value of the the inputed node to the second node.	
 * @param nodeOne
 * @param nodeTwo
 */
	//Done
	private void copyNode(Node<K,V> nodeOne, Node<K,V> nodeTwo) {
		nodeOne.key = nodeTwo.key;
		nodeOne.value = nodeTwo.value;
		nodeOne = nodeTwo;
	}
/**
 * Remove case one: If the node being removed has a parent in which is null, we know it is the
 * root and we can remove with ease.	
 * @param removeNode
 */
	private void removeCaseOne(Node<K,V> removeNode) {
		//Similar to the add case one method
		//If removed node became root node
		if(removeNode.parent == null) {
			return;
		} else {
			removeCaseTwo(removeNode);
		}
	}
/**
 * Remove case two: If the node being removed has a red sibling, we must ensure that the children follow
 * the invariant in which a red node must have 2 black children. We must swap the colors of the sibling and the parent
 * if so.	
 * @param removeNode
 */
	private void removeCaseTwo(Node<K,V> removeNode) {
		if(removeNode.sibling() != null && removeNode.sibling().nodeColor == Color.RED) {
			//check parent and sibling
			swapNodeColorSiblingAndParent(removeNode);
			//rotate about parent depending on if removeNode is the left or right sibling
			if(removeNode == removeNode.parent.leftChild) {
				rotateLeft(removeNode.parent);
			} else if(removeNode == removeNode.parent.rightChild) {
				rotateRight(removeNode.parent);
			} else {
				System.out.println("Neither left or right child, something went wrong.");
			}
		}
		removeCaseThree(removeNode);
	}
/**
 * Remove case three: The node being removed has a parent, sibling, and the node's own presence of being an uncle to his sibling's
 * children are all black. This causes an imbalance of black nodes to particular path (invariant 5).
 * @param removeNode
 */
	private void removeCaseThree(Node<K,V> removeNode) {
		if(removeNode.sibling() != null && removeNode.sibling().rightChild != null
				&& removeNode.sibling().leftChild != null 
				&& removeNode.parent.nodeColor == Color.BLACK 
				&& removeNode.sibling().nodeColor == Color.BLACK 
				&& removeNode.sibling().leftChild.nodeColor == Color.BLACK 
				&& removeNode.sibling().rightChild.nodeColor == Color.BLACK) {
			removeNode.sibling().nodeColor = Color.RED;
			//Ensure that we are not setting the root node to red!
			//That would cause a violation of a property.
			removeCaseOne(removeNode.parent);
		} else {
			removeCaseFour(removeNode);
		}
	}
/**
 * Remove case four: The node being removed has a sibling and the uncle presence of the sibling's children
 * are black. If the parent is red, we know that an invariant has been broken pertaining to the node's sibling
 * and parent and we must swap the colors!
 * @param removeNode
 */
	private void removeCaseFour(Node<K,V> removeNode) {
		//Node sibling and uncle presence of sibling children are black.
		//Parent is red.
		if(removeNode.sibling() != null && removeNode.sibling().rightChild != null
				&& removeNode.sibling().leftChild != null
				&& removeNode.sibling().nodeColor == Color.BLACK 
				&& removeNode.sibling().leftChild.nodeColor == Color.BLACK
				&& removeNode.sibling().rightChild.nodeColor == Color.BLACK
				&& removeNode.parent.nodeColor == Color.RED) {
			swapNodeColorSiblingAndParent(removeNode);
		} else {
			removeCaseFive(removeNode);
		}
	}
/**
 * Remove case five: The node being removed either has a sibling, sibling left child,
 * sibling right child in which is black, red, and black respectively. Additionally, the node being removed is actually
 * a left child. In this case, we know we must swap the colors of the sibling and left child being a red node must have 2
 * black children and rotate right. In the other case, the sibling, sibling left child, and sibling right child are black, black, and red
 * respectively. Also, the node is actually the right child. In this case, we swap colors of the sibling and sibling right child
 * and rotate left.
 * @param removeNode
 */
	private void removeCaseFive(Node<K,V> removeNode) {
		if(removeNode.sibling() != null && removeNode.sibling().rightChild != null
				&& removeNode.sibling().leftChild != null
				&& removeNode.sibling().nodeColor == Color.BLACK
				&& removeNode.sibling().leftChild.nodeColor == Color.RED
				&& removeNode.sibling().rightChild.nodeColor == Color.BLACK
				&& removeNode.parent.leftChild == removeNode) {
			swapNodeColorSiblingAndSiblingLeftChild(removeNode);
			//Rotate right by sibling
			rotateRight(removeNode.sibling());
		} else if(removeNode.sibling() != null && removeNode.sibling().rightChild != null
				&& removeNode.sibling().leftChild != null
				&& removeNode.sibling().nodeColor == Color.BLACK
				&& removeNode.sibling().rightChild.nodeColor == Color.RED
				&& removeNode.sibling().leftChild.nodeColor == Color.BLACK
				&& removeNode.parent.rightChild == removeNode) {
			swapNodeColorSiblingAndSiblingRightChild(removeNode);
			//Rotate left by sibling
			rotateLeft(removeNode.sibling());
		}
		removeCaseSix(removeNode);
	}
/**
 * Remove case six: The node being removed has a sibling in which is black and a sibling's right child
 * (thus, the uncle of that sibling child) is the color red and the node is a left child. To solve we must swap
 * colors of the sibling and parent and rotate left. In the other case, the node being removed must forice its sibling and parent
 * to swap colors and rotate right.	
 * @param removeNode
 */
	private void removeCaseSix(Node<K,V> removeNode) {
		if(removeNode.sibling() != null && removeNode.sibling().rightChild != null
				&& removeNode.sibling().leftChild != null
				&& removeNode.sibling().nodeColor == Color.BLACK 
				&& removeNode.sibling().rightChild.nodeColor == Color.RED
				&& removeNode == removeNode.parent.leftChild) {
			swapNodeColorSiblingAndParent(removeNode);
			removeNode.sibling().rightChild.nodeColor = Color.BLACK;
			//Rotate left by parent
			rotateLeft(removeNode.parent);
		} else if(removeNode.sibling() != null && removeNode.sibling().rightChild != null
				&& removeNode.sibling().leftChild != null
				&& removeNode.sibling().nodeColor == Color.BLACK
				&& removeNode.sibling().leftChild.nodeColor == Color.RED
				&& removeNode == removeNode.parent.rightChild) {
			swapNodeColorSiblingAndParent(removeNode);
			removeNode.sibling().leftChild.nodeColor = Color.BLACK;
			//Rotate right by parent
			rotateRight(removeNode.parent);
		}
	}
/**
 * Determines whether the specified node is the root, the left child of its parent,
 * or its right child of its parent.	
 * @param nodeParent
 * @param nodeChild
 */
	private void fixTree(Node<K,V> nodeParent, Node<K,V> nodeChild) {
		//In this case, the nodeParent becomes the root node because its parent is null.
		//From this, we know that would be the root node.
		if(nodeParent.parent == null) {
			rootNode = nodeParent;
		} else {
			if(nodeParent == nodeParent.parent.leftChild) {
				//Parent is actually the left child.
				nodeParent.parent.leftChild = nodeChild;
			} else {
				//Parent must be the right child.
				assert (nodeParent == nodeParent.parent.rightChild);
				nodeParent.parent.rightChild = nodeChild;
			}
		}
		//Each have equivalent parent node.
		if(nodeChild != null) {
			nodeChild.parent = nodeParent.parent;
		}
	}
/**
 * Nodes are moved in the left direction about a particular point in the red black tree.
 * @param rotateNode
 */
	private void rotateLeft(Node<K,V> rotateNode) {
		fixTree(rotateNode,rotateNode.rightChild);
		rotateNode.rightChild = rotateNode.rightChild.leftChild;
		if(rotateNode.rightChild.leftChild != null) {
			rotateNode.rightChild.leftChild.parent = rotateNode;
		}
		rotateNode.rightChild.leftChild = rotateNode;
		rotateNode.parent = rotateNode.rightChild;
	}
/**
 * Nodes are moved in the right direction about a particular point in the red black tree.
 * @param rotateNode
 */
	private void rotateRight(Node<K,V> rotateNode) {
		fixTree(rotateNode,rotateNode.leftChild);
		rotateNode.leftChild = rotateNode.leftChild.rightChild;
		if(rotateNode.leftChild.rightChild != null) {
			rotateNode.leftChild.rightChild.parent = rotateNode;
		}
		rotateNode.leftChild.rightChild = rotateNode;
		rotateNode.parent = rotateNode.leftChild;
	}
/**
 * Finds the specified value corresponding with the key that is inputed. The following function does
 * NOT change the state of the red black tree, it only check to see if the entry is inside the red black tree
 * or not.	
 */
	@Override
	public V lookup(K key) {
		if(size == 0) {
			System.out.println("There is nothing to look up! Duh...");
			return null;
		}
		if(arrayList.size() != 0) {
			Node<K,V> tempNode = null;
			for(int i = 0; i < arrayList.size(); i++) {
				tempNode = arrayList.get(i);
				if(tempNode != null && tempNode.key.compareTo(key) == 0) {
					System.out.println("Key found.");
					return tempNode.value;
				}
			}
		}
		System.out.println("Key not found.");
		return null;
	}
/**	
 * Determines if a node is a leaf or not. A leaf in this case is a node in which
 * is the color black and has a null key and value.
 * @param checkNode
 * @return
 */
	private boolean leafNode(Node<K,V> checkNode) {
		if(checkNode.nodeColor == Color.BLACK 
				&& checkNode.key == null
				&& checkNode.value == null) {
			return true;
		}
		return false;
	}
/**	
 * Finds the internal node with the appropriate node that is inputed.
 * It will decide if the internal node is the left or right child.
 * @param checkNode
 * @param child
 * @param exclude
 * @return
 */
	private boolean internalNode(Node<K,V> checkNode, boolean child, boolean exclude) {
		if(exclude) {
			if(child && checkNode.parent.rightChild == checkNode
					&& checkNode.grandparent().leftChild == checkNode.parent) {
				return true;
			} else {
				return false;
			}
		}
		if(!exclude) {
			if(!child && checkNode.parent.leftChild == checkNode
					&& checkNode.grandparent().rightChild == checkNode.parent) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
/**
 * Finds the external node with the appropriate node that is inputed. It will also decide if 
 * it is a right or left child.
 * @param checkNode
 * @param child
 * @param exclude
 * @return
 */
	private boolean externalNode(Node<K,V> checkNode, boolean child, boolean exclude) {
		if(exclude) {
			if(child && checkNode.grandparent().leftChild == checkNode.parent
				&& checkNode.parent.leftChild == checkNode) {
				return true;
			} else {
				return false;
			}
		}
		if(!exclude) {
			if(!child && checkNode.grandparent().rightChild == checkNode.parent
				&& checkNode.parent.rightChild == checkNode) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
/**
 * Returns the amount of node's inside the red black tree.
 * @return
 */
	public int getSize() {
		return size;
	}
/**
 * Returns the height of the tree (the deepest node in the red black tree).	
 * @param node
 * @return
 */
	private int heightOfTree(Node<K,V> node) {
		int count = 1;
		if(node == null) {
			return 0;
		} else {
			return count + Math.max(heightOfTree(node.leftChild),heightOfTree(node.rightChild));
		}
	}
/**
 * Prints the the red black tree and all of its contents.
 * @param node
 * @param level
 */
	private void printTree(Node<K,V> node, int level) {
		if(level < 0) {
			return;
		}
		Queue<Node<K,V>> queue = new LinkedList<Node<K,V>>();
		Queue<Integer> levels = new LinkedList<Integer>();
		queue.add(node);
		levels.add(0);
		while(!queue.isEmpty()) {
			Node<K,V> tempNode = queue.poll();
			int currentLevel = levels.poll();
			if(tempNode == null) {
				return;
			} else if(currentLevel == level) {
				displayNodeDetails(tempNode);
			} else {
				queue.add(tempNode.leftChild);
				levels.add(currentLevel + 1);
				queue.add(tempNode.rightChild);
				levels.add(currentLevel + 1);
			}
		}
		
	}
/**	
 * Displays the node information appropriately.
 * @param node
 */
	private void displayNodeDetails(Node<K,V> node) {
		if(node.nodeColor == Color.BLACK) {
			System.out.println("(" + node.key + "," + node.value + "," + "B)");
		} else {
			System.out.println("(" + node.key + "," + node.value + "," + "R)");
		}
	}
/**	
 * Prints the red black tree to a string representation.
 */
	@Override
	public String toPrettyString() {
		int level = heightOfTree(rootNode);
		printTree(rootNode,level);
		return null;
	}
}


