
public class CompositeTrapezoidRuleTests {

	public static void main(String[] args) {
		System.out.println("Function: sin(x) dx");
		System.out.println("a = 0, b = pi");
		System.out.println("n = 100");
		
		double a = 0.0;
		double b = Math.PI;
		double n = 100;
		System.out.println("Approximation: " + trapezoid_uniform(0,a,b,n) + "\n");
		
		System.out.println("Function: e^x dx");
		System.out.println("a = 0, b = 1");
		System.out.println("n = 100");
		
		a = 0.0;
		b = 1.0;
		n = 100;
		System.out.println("Approximation: " + trapezoid_uniform(1,a,b,n) + "\n");
		
		System.out.println("Function: arctan(x) dx");
		System.out.println("a = 0, b = 1");
		System.out.println("n = 100");
		
		a = 0.0;
		b = 1.0;
		n = 100;
		System.out.println("Approximation: " + trapezoid_uniform(2,a,b,n));
	}
	
	public static double function(int func, double x) {
		switch(func) {
		case 0:
			return Math.sin(x);
		case 1:
			return Math.exp(x);
		case 2:
			return Math.atan(x);
		}
		return 0;
	}
	
	public static double trapezoid_uniform(int func, double a, double b, double n) {
		double h = (b - a) / n;
		double sum = 0.5 * (function(func,a) + function(func,b));
		double x = 0.0;
		for(int i = 1; i < n; i++) {
			x = a + (i * h);
			sum = sum + function(func,x);
		}
		sum = sum * h;
		return sum;
	}

}
