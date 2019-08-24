package edu.cpp.cs.cs241.prog_assgmnt_4;
/**
 * The following class represents the vertices, or locations, for positions in the graph.
 * It holds the id of the node, the name, and the data.
 * @author Michael
 *
 * @param <I>
 * @param <N>
 * @param <D>
 */
public class Node<I,N,D> implements NodeInterface<I,N,D> {
/**
 * ID of the node, solely represented as an integer.
 */
	protected I id;
/**
 * Name of the node, solely represented as a String.
 */
	protected N name;
/**
 * Data of node, in which I held an array of String.
 */
	protected D data;
/**
 * Cost of the node.
 */
	protected double cost;
/**
 * Constructor in which instantiates a new vertice for the graph.
 * @param id
 * @param name
 * @param data
 */
	public Node(I id, N name, D data) {
		this.id = id;
		this.name = name;
		this.data = data;
	}
/**
 * Returns the id of the node.
 * @return id
 */
	@Override
	public I getID() {
		return id;
	}
/**
 * Returns the name of the node.
 * @return name
 */
	@Override
	public N getName() {
		return name;
	}
/**
 * Returns the data of the node.
 * @return data
 */
	@Override
	public D getData() {
		return data;
	}
/**
 * Sets the cost of the node.
 * @param cost
 */
	public void setCost(double cost) {
		this.cost = cost;
	}
	
/**
 * Returns the cost of the node.
 * @return cost
 */
	public double getCost() {
		return cost;
	}
	
}
