
public class Number13 {
	
	private static double algA = 0;
	private static double algB = 0;

	public static void main(String[] args) {
		for(int i = 2; i < Integer.MAX_VALUE; ++i) {
			calculatePerformance(i);
			if(compareAlgorithms()) {
				System.out.println(i);
				break;
			}
		}
	}
	
	private static void calculatePerformance(int n) {
		algA = 10 * n * n;
		algB = 300 * Math.log(n);
	}
	
	private static boolean compareAlgorithms() {
		if(algB < algA) {
			return true;
		}
		return false;
	}

}
