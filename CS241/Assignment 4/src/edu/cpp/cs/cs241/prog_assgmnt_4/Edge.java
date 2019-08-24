package edu.cpp.cs.cs241.prog_assgmnt_4;
/**
 * The following class represents a line between two nodes, or vertices. To represent an edge, it keeps track of two nodes,
 * one being the top and one being the bottom. Additionally, it maintains the weight of the line as well as the street name.
 * @author Michael
 *
 * @param <I>
 * @param <N>
 * @param <D>
 */
public class Edge<I,N,D> {
/**
 * Top of the edge
 */
	private Node<I,N,D> frontNode;
/**
 * Cost of the edge
 */
	private double weight;
/**
 * Name of the edge
 */
	private String streetName;
/**
 * Bottom of the edge
 */
	private Node<I,N,D> endNode;
/**
 * Keeps track of the edge direction (south, west, etc.)
 */
	private String direction;
/**
 * Constructor in which initializes an edge to identify a relationship between two nodes.	
 * @param frontNode
 * @param weight
 * @param streetName
 * @param endNode
 */
	public Edge(Node<I,N,D> frontNode, double weight, String streetName, Node<I,N,D> endNode, String direction) {
		this.frontNode = frontNode;
		this.weight = weight;
		this.streetName = streetName;
		this.endNode = endNode;
		this.direction = direction;
	}
/**
 * Retrieves the top node of the edge
 * @return frontNode
 */
	public Node<I,N,D> getFrontNode() {
		return frontNode;
	}
/**
 * Returns the direction of the edge.	
 * @return direction
 */
	public String getDirection() {
		return direction;
	}
/**
 * Retrieves the weight of the edge
 * @return weight
 */
	public double getWeight() {
		return weight;
	}
/**
 * Sets a new weight cost of the edge
 * @param weight
 */
	public void setWeight(double weight) {
		this.weight = weight;
	}
/**
 * Retrieves the bottom node of the edge
 * @return
 */
	public Node<I,N,D> getEndNode() {
		return endNode;
	}
/**
 * Retrieves the street name of the edge
 * @return
 */
	public String getStreetName() {
		return streetName;
	}
	
}
