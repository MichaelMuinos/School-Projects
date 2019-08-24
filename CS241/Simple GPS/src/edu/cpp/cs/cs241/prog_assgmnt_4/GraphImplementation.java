package edu.cpp.cs.cs241.prog_assgmnt_4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
/**
 * The following class represents a graph data structure in which keeps track of its vertices and edges. 
 * It implements the interface of Graph. Instead of an adjacency matrix, it is represented with an adjacency list.
 * @author Michael
 *
 * @param <I>
 * @param <N>
 * @param <D>
 */
public class GraphImplementation<I,N,D> implements Graph<I,N,D> {
/**
 * Map in which represents the adjacency list, with the node as the key, and the adjacent nodes as the value.
 */
	private Map<Node<I,N,D>,ArrayList<Node<I,N,D>>> adjacencyList;
/**
 * Map in which represents a list of edges, with an integer as the key, and the edge as the value.
 */
	private Map<Integer,Edge<I,N,D>> edgeList;
/**
 * List in which represents the group of nodes added to the adjacency list.
 */
	private List<Node<I,N,D>> nodeList;
/**
 * List in which represents all unvisited nodes in the graph.
 */
	private List<Node<I,N,D>> unvisitedNodes;
/**
 * Map of nodes for Dijkstra traversal.
 */
	private Map<I,Double> nodeCostTable;
/**
 * Source node of the graph.
 */
	private Node<I,N,D> sourceNode;
/**
 * Count of edges.
 */
	private int edgeCount = 0;
/**
 * Count of vertices.
 */
	private int nodeCount = 0;
/**	
 * Constructor in which instantiates all lists and maps.
 */
	public GraphImplementation() {
		adjacencyList = new HashMap<Node<I,N,D>,ArrayList<Node<I,N,D>>>();
		nodeCostTable = new HashMap<I,Double>();
		edgeList = new HashMap<Integer,Edge<I,N,D>>();
		nodeList = new ArrayList<Node<I,N,D>>();
		unvisitedNodes = new ArrayList<Node<I,N,D>>();
	}
/**
 * Sets the source of the graph using the nodes ID.	
 */
	@Override
	public void setSource(I source) {
		//Source will always have an ID of 0
		if(sourceNode == null && sourceNode.getID() == null) {
			source = sourceNode.id;
		}
	}
/**
 * Returns the adjacency list.
 * @return adjacencyList
 */
	public Map<Node<I,N,D>,ArrayList<Node<I,N,D>>> getAdjacencyList() {
		return adjacencyList;
	}
/**
 * Adds the inputed node as a vertice inside the graph.
 */
	@Override
	public void addNode(Node<I, N, D> node) {
		List<Node<I,N,D>> list = new ArrayList<Node<I,N,D>>();
		if(nodeCount == 0) {
			node.setCost(0);
		} else {
			node.setCost(Double.MAX_VALUE);
		}
		adjacencyList.put(node, (ArrayList<Node<I, N, D>>) list);
		nodeCostTable.put(node.getID(), node.getCost());
		nodeList.add(node);
		unvisitedNodes.add(node);
		nodeCount++;
	}
/**
 * Creates an edge, or relationship, between two inputed nodes. 
 * The source being the top of the edge and the target being the bottom edge.
 */
	@Override
	public void createEdge(Node<I,N,D> source, double weight, String streetName, Node<I,N,D> target, String direction) {
		Edge<I,N,D> edge = new Edge<I,N,D>(source,weight,streetName,target, direction);
		adjacencyList.get(source).add(target);
		edgeList.put(edgeCount, edge);
		edgeCount++;
	}
/**
 * Returns the list of nodes.
 * @return nodeList
 */
	@Override
	public List<Node<I,N,D>> getNodesList() {
		return nodeList;
	}
/**
 * Returns the source of the graph.
 * @return sourceNode
 */
	@Override
	public Node<I,N,D> getSource() {
		return sourceNode;
	}
/**
 * Returns a list of adjacent nodes of the particular node in the parameter.
 * @return adjacentNodeList
 */
	@Override
	public List<Node<I,N,D>> getAdjacentNodes(Node<I,N,D> node) {
			return adjacencyList.get(node);
	}
/**
 * Traverses through the graph from node to node by starting at the source and moving to its adjacent nodes
 * until all nodes have been visited.
 */
	@Override
	public Node<I,N,D> BFS(Node<I,N,D> source) {
		Node<I,N,D> current = null;
		Queue<Node<I,N,D>> queue = new LinkedList<Node<I,N,D>>();
		List<Node<I,N,D>> visited = new ArrayList<Node<I,N,D>>();
		queue.add(source);
		while(!visited.isEmpty()) {
			current = queue.remove();
			visited.add(current);
			List<Node<I,N,D>> adjacentNodeList = getAdjacentNodes(current);
			for(int i = 0; i < adjacentNodeList.size(); i++) {
				if(!visited.contains(adjacentNodeList.get(i)) && !queue.contains(adjacentNodeList.get(i))) {
					queue.add(adjacentNodeList.get(i));
				}
			}
		}
		return visited.get(visited.size() - 1);
	}
/**
 * Traverses through the graph from node to node by starting at the source and committing to a particular path
 * until the node has no adjacent nodes.
 */
	@Override
	public Node<I,N,D> DFS(Node<I,N,D> source) {
		Node<I,N,D> current = null;
		Stack<Node<I,N,D>> stack = new Stack<Node<I,N,D>>();
		List<Node<I,N,D>> visited = new ArrayList<Node<I,N,D>>();
		stack.push(source);
		while(!stack.isEmpty()) {
			current = stack.pop();
			visited.add(current);
			List<Node<I,N,D>> adjacentNodeList = getAdjacentNodes(current);
			for(int i = 0; i < adjacentNodeList.size(); i++) {
				if(!visited.contains(adjacentNodeList.get(i)) && !stack.contains(adjacentNodeList.get(i))) {
					stack.add(adjacentNodeList.get(i));
				}
			}
		}
		return visited.get(visited.size() - 1);
	}
/**
 * Returns the cost table.	
 * @return
 */
	public Map<I,Double> getCostTable() {
		return nodeCostTable;
	}
/**
 * Returns the edge list.
 * @return
 */
	public Map<Integer,Edge<I,N,D>> getEdgeList() {
		return edgeList;
	}	
/**
 * Returns all unvisited nodes.	
 * @return
 */
	public List<Node<I,N,D>> getUnvisitedNodes() {
		return unvisitedNodes;
	}
/**
 * Returns a map of the appropriate distances between two nodes. It will find the shortest path between any two nodes that were inputed
 * inside the graph.
 * @return nodeCostTable
 */
	@Override
	public Map<I, Double> dijkstrasShortestPath() {
		List<Node<I,N,D>> unvisited = getUnvisitedNodes();
		List<Node<I,N,D>> visited = new ArrayList<Node<I,N,D>>();
		Node<I,N,D> current = null;
		Map<I,Double> map = getCostTable();
		while(!unvisited.isEmpty()) {
			current = getMinCostNode(unvisited);
			visited.add(current);
			unvisited.remove(current);
			List<Node<I,N,D>> adjacentNodeList = getAdjacentNodes(current);
			for(int i = 0; i < adjacentNodeList.size(); i++) {
				if(!visited.contains(adjacentNodeList.get(i))) {
					if(current.getCost() + distanceBetween(current,adjacentNodeList.get(i)) <= adjacentNodeList.get(i).getCost()) {
						adjacentNodeList.get(i).setCost(current.getCost() + distanceBetween(current,adjacentNodeList.get(i)));
						map.put(adjacentNodeList.get(i).getID(), adjacentNodeList.get(i).getCost());
					}
				}
			}
		}
		return map;
	}
/**
 * Finds the cost of an edge between two nodes.	
 * @param frontNode
 * @param endNode
 * @return
 */
	private double distanceBetween(Node<I,N,D> frontNode, Node<I,N,D> endNode) {
		for(int i = 0; i < edgeList.size(); i++) {
			Edge<I,N,D> tempEdge = edgeList.get(i);
			if(tempEdge.getFrontNode() == frontNode && tempEdge.getEndNode() == endNode) {
				return tempEdge.getWeight();
			}
		}
		return 0;
	}
/**
 * Returns the node with the smallest cost out of the list that is inputed.
 * @param unvisited
 * @return minCostNode
 */
	private Node<I,N,D> getMinCostNode(List<Node<I,N,D>> unvisited) {
		double minCost = unvisited.get(0).getCost();
		Node<I,N,D> minCostNode = unvisited.get(0);
		for(int i = 0; i < unvisited.size(); i++) {
			if(unvisited.get(i).getCost() < minCost) {
				minCost = unvisited.get(i).getCost();
				minCostNode = unvisited.get(i);
			}
		}
		return minCostNode;
	}
	
}
