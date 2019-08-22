
public class ChebyshevNodes {

	public static void main(String[] args) {
		double evenDistribution = 20.0 / 41.0;
		double start = 0.0;
		double end = 20.0;
		double node = start;
		
		System.out.println("Node -- " + start + " -- f(x): " + findFirstFunctionY(start));
		
		for(int i = 2; i < 41; i++) {
			node = node + evenDistribution;
			System.out.println("Node -- " + node + " -- f(x): " + findFirstFunctionY(node));
		}
		
		System.out.println("Node -- " + end + " -- f(x): " + findFirstFunctionY(end) + "\n\n");
		
		node = start;
		
		System.out.println("Node -- " + start + " -- p(x): " + findSecondFunctionY(start));

		for(int i = 2; i < 41; i++) {
			node = node + evenDistribution;
			System.out.println("Node -- " + node + " -- p(x): " + findSecondFunctionY(node));
		}
		
		System.out.println("Node -- " + end + " -- p(x): " + findSecondFunctionY(end));
	}
	
	public static double findFirstFunctionY(double node) {
		return 5 * Math.cos((node * Math.PI) / 20);
	}
	
	public static double findSecondFunctionY(double node) {
		double part = (2*node + 1) * Math.PI;
		return 5 * Math.cos(part / 42.0);
	}

}
