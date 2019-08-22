
public class CompositeTrapezoidRule {

	public static void main(String[] args) {
		System.out.println("The following program tests and runs the approximate value\n"
				+ " of the sample integral found on page 204. Any function can be approximated\n"
				+ " as long as the method \"function\" is changed to the appropriate function.\n");
		System.out.println("Calculating integral of e^(-x^2)dx from 0 to 1.");
		System.out.println("Function: e^(-x^2)");
		System.out.println("a = 0, b = 1");
		System.out.println("n = 60");
		
		double n = 60.0;
		double a = 0.0;
		double b = 1.0;
		
		double approximation = trapezoid_uniform(a,b,n);
		System.out.println("\nApproximation: " + approximation);
	}
	
	public static double function(double x) {
		return Math.exp(-1 * Math.pow(x, 2));
	}
	
	public static double trapezoid_uniform(double a, double b, double n) {
		double h = (b - a) / n;
		double sum = 0.5 * (function(a) + function(b));
		double x = 0.0;
		for(int i = 1; i < n; i++) {
			x = a + (i * h);
			sum = sum + function(x);
		}
		sum = sum * h;
		return sum;
	}

}
