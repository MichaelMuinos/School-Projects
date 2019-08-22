
public class BisectionMethod {

	public static void main(String[] args) {
		// Solve f(x) = x^3 - 3x + 1 == 1st function
		// Solve g(x) = x^3 - 2*sin(x) == 2nd function
		// Solve h(x) = x + 10 - x*cosh(50/x) == 3rd function
		double solution = 0;
		
		System.out.println("f(x) = x^3 - 3x + 1");
		System.out.println("Starting interval: [0,1]");
		solution = bisect(0,1,1);
		System.out.println("Approximate solution: " + solution + "\n");
		
		System.out.println("g(x) = x^3 - 2*sin(x)");
		System.out.println("Starting interval: [0.5,2]");
		solution = bisect(0.5,2,2);
		System.out.println("Approximate solution: " + solution + "\n");
		
		System.out.println("h(x) = x + 10 - x*cosh(50/x))");
		System.out.println("Starting interval: [120,130]");
		solution = bisect(120,130,3);
		System.out.println("Approximate solution: " + solution);
	}
	
	private static double bisect(double left, double right, int function) {
		final double epsilon = 1 * Math.pow(10, -15);
		double midpoint = 0, y_midpoint = 0, y_value = 0;
		while((right - left) > epsilon) {
			midpoint = (left + right) / 2;
			switch(function) {
			case 1:
				y_midpoint = Math.pow(midpoint, 3) - 3*midpoint + 1;
				y_value = Math.pow(left, 3) - 3*left + 1;
				break;
			case 2:
				y_midpoint = Math.pow(midpoint, 3) - 2*Math.sin(midpoint);
				y_value = Math.pow(left, 3) - 2*Math.sin(left);
				break;
			case 3:
				y_midpoint = midpoint + 10 - midpoint*Math.cosh((50/midpoint));
				y_value = left + 10 - left*Math.cosh((50/left));
				break;
			default:
				break;
			}
			if((y_midpoint > 0 && y_value < 0) || y_midpoint < 0 && y_value > 0) {
				right = midpoint;
			} else {
				left = midpoint;
			}
			System.out.println("New interval: [" + left + "," + right + "]");
		}
		return (left + right) / 2;
	}

}
