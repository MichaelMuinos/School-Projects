
public class ComputeSin1Degree {
	
	public static void main(String[] args) {
		double tolerance = 1 * Math.pow(10,-10);
		int max_count = 20;
		double x = 1;
		for(int i = 1; (Math.abs(f(x)) > tolerance) && (i < max_count); i++)  {
	            x = x - f(x)/fPrime(x);
	            System.out.println("x: " + x + ", Value: "+ f(x));
	        } 
		if(Math.abs(f(x)) <= tolerance) {
			System.out.println("Value: " + x);
		}
	}
	
	private static double f(double x) {
		return Math.sin(x);
	}
	
	private static double fPrime(double x) {
		return Math.cos(x);
	}
	
}
