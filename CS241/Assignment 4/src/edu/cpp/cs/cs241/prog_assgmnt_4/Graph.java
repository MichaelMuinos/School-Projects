package edu.cpp.cs.cs241.prog_assgmnt_4;

import java.util.List;
import java.util.Map;
/**
 * Interface in which acts as a template for a graph. It has the ability to add vertices, add edges, and
 * determine the shortest path from one node to another by using an implementation of dijkstra's algorithm.
 * @author Michael
 *
 * @param <I>
 * @param <N>
 * @param <D>
 */
public interface Graph<I,N,D> {
/**
 * Pre: A node has already been entered into the graph, thus the graph is not null.
 * Post: The graph has a source node. This function is a pure function.	
 * @param source
 */
	void setSource(I source);
/**
 * Pre: Node is not a null node.
 * Post: Node has been added to the graph. The size of the graph is increased by 1.
 * @param node
 */
	void addNode(Node<I,N,D> node);
/**
 * Pre: Source, weight, street name, direction, and target node are not null.
 * Post: New relationship between the source node and target node has been created. Edge count has increased
 * by 1. The following function is a pure function.
 * @param source
 * @param weight
 * @param streetName
 * @param target
 */
	void createEdge(Node<I,N,D> source, double weight, String streetName, Node<I,N,D> target, String direction);
/**
 * Pre: Returned list is not null.
 * Post: True
 * @return
 */
	List<Node<I,N,D>> getNodesList();
/**
 * Pre: Returned source node is not null.
 * Post: True	
 * @return
 */
	Node<I,N,D> getSource();
/**
 * Pre: Node is not null. Node has at least one adjacent node to be put inside a list.
 * Post: True
 * @param node
 * @return
 */
	List<Node<I,N,D>> getAdjacentNodes(Node<I,N,D> node);
/**
 * Pre: nodeID is not null.
 * Post: Nodes in the graph have been traversed by Breadth-First Search.
 * @param nodeID
 * @return
 */
	Node<I,N,D> BFS(Node<I,N,D> nodeID);
/**
 * Pre: nodeID is not null.
 * Post: Nodes in the graph have been traversed by Depth-First Search.
 * @param nodeID
 * @return
 */
	Node<I,N,D> DFS(Node<I,N,D> nodeID);
/**
 * Pre: Graph is not null.
 * Post: Map has been returned with appropriate values between each node path.
 * @return
 */
	Map<I,Double> dijkstrasShortestPath();

}