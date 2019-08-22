
public class DefiniteIntegralEstimation {

	public static void main(String[] args) {
		System.out.println("Function: x^-1 * sin(x) dx");
		System.out.println("a = 0, b = 1");
		System.out.println("n = 800");
		
		double n = 800;
		System.out.println("Approximation: " + computeTaylorSeries(n));
	}
	
	public static double computeFactorial(int x) {
		int fact = 1;
		if(x >= 16) {
			x = 16;
		}
		for (int i = 1; i <= x; i++) {
            fact *= i;
        }
        return fact;
	}
	
	public static double computeTaylorSeries(double n) {
		double sum = 0.0;
		for(int i = 0; i < n + 1; i++) {
			double firstTerm = Math.pow(-1, i);
			double top = Math.pow(1, 2*i + 1);
			double bottom = computeFactorial(2*i + 1) * (2*i + 1);
			sum += firstTerm * (top / bottom);
		}
		return sum;
	}

}
