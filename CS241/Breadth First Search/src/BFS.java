import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import javax.xml.soap.Node;

public class BFS {

	private void traverse(Node startingNode) {
		Node currentNode = null;
		Queue<Node> queue = new LinkedList<Node>();
		Set<Node> visited = new LinkedHashSet<Node>();
		queue.add(startingNode);
		while(!queue.isEmpty()) {
			currentNode = queue.remove();
			visited.add(currentNode);
			for(/*for each node adjacent to current*/) {
				if(/*n is not in visited and not in queue*/) {
					queue.add(/*n*/);
				}
			}
		}
	}

}
