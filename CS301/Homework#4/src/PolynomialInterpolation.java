
public class PolynomialInterpolation {

	public static void main(String[] args) {
		double[] xi = {0, 5, 10, 15};
		double[] yi = {1.792, 1.519, 1.308, 1.140};
		
		int terms = 4;
		double xValue = 8;
		double yValue = 0;
		
		for(int i = 0; i < terms; ++i) {
			double num = 1;
			double den = 1;
			
			for(int j = 0; j < terms; ++j) {
				if(j != i) {
					num = num * (xValue - xi[j]);
					den = den * (xi[i] - xi[j]);
				}
			}
			yValue = yValue + (num/den) * yi[i];
		}
		System.out.println("x: " + xValue + " ---> y: " + yValue);
	}

}
