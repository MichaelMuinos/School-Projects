
public class EquallySpacedNodes {

	public static void main(String[] args) {
		double evenDistribution = 10.0 / 41.0;
		double start = -5.0;
		double end = 5.0;
		double node = start;
		
		System.out.println("Node -- " + start + " -- f(x): " + findY(start));
		
		for(int i = 2; i < 41; i++) {
			node = node + evenDistribution;
			System.out.println("Node -- " + node + " -- f(x): " + findY(node));
		}
		
		System.out.println("Node -- " + end + " -- f(x): " + findY(end) + "\n\n");
		
		node = start;
		
		System.out.println("Node -- " + start + " -- p(x): " + findPxY(start));
		
		for(int i = 2; i < 41; i++) {
			node = node + evenDistribution;
			System.out.println("Node -- " + node + " -- p(x): " + findPxY(node));
		}
		
		System.out.println("Node -- " + end + " -- p(x): " + findPxY(end) + "\n\n");
	}
	
	public static double findY(double start) {
		return Math.pow((start * start) + 1, -1);
	}
	
	public static double findPxY(double start) {
		return Math.pow((Math.pow(start, 20) + 1), -1);
	}

}
