
public class StraightLineProcedure {

	public static void main(String[] args) {
		double[] xValues = {-1.0, 2.0, 3.0};
		double[] yValues = {5.0/4.0, 4.0/3.0, 5.0/12.0};
		int n = 3;
		
		System.out.println("Exercises 9.1: #1 Table");
		printPoints(xValues, yValues, n);
		
		double xBar = calculateXBar(xValues, n);
		double yBar = calculateYBar(yValues, n);
		double slope = calculateTopSummation(xBar, yBar, xValues, yValues, n) 
						/ calculateBottomSummation(xBar, xValues);
		double intercept = yBar - (slope * xBar);
		
		System.out.println("\nBest Fit Line ----> y = " + slope + "x + " + intercept);
		
		double[] xValuesTwo = {0, 10.0, 20.0, 30.0, 40.0, 80.0, 90.0, 95.0};
		double[] yValuesTwo = {68.0, 67.1, 66.4, 65.6, 64.6, 61.8, 61.0, 60.0};
		n = 8;
		
		System.out.println("\nOpening Chapter Problem: Page 426");
		printPoints(xValuesTwo, yValuesTwo, n);
		
		xBar = calculateXBar(xValuesTwo, n);
		yBar = calculateYBar(yValuesTwo, n);
		slope = calculateTopSummation(xBar, yBar, xValuesTwo, yValuesTwo, n)
				/ calculateBottomSummation(xBar, xValues);
		intercept = yBar - (slope * xBar);
		
		System.out.println("\nBest Fit Line ----> y = " + slope + "x + " + intercept);
		System.out.println("a = " + slope + "\tb = " + intercept);
		
		double[] xValuesThree = {4.0, 7.0, 11.0, 13.0, 17.0};
		double[] yValuesThree = {2.0, 0.0, 2.0, 6.0, 7.0};
		n = 5;
		
		System.out.println("\nExample 1: Page 429");
		printPoints(xValuesThree, yValuesThree, n);
		
		xBar = calculateXBar(xValuesThree, n);
		yBar = calculateYBar(yValuesThree, n);
		slope = calculateTopSummation(xBar, yBar, xValuesThree, yValuesThree, n)
				/ calculateBottomSummation(xBar, xValuesThree);
		intercept = yBar - (slope * xBar);
		
		System.out.println("\nBest Fit Line ----> y = " + slope + "x + " + intercept);
		System.out.println("a = " + slope + "\tb = " + intercept);
	}
	
	private static double calculateXBar(double[] xValues, int n) {
		double sum = 0;
		for(int i = 0; i < xValues.length; i++) {
			sum += xValues[i];
		}
		return sum / n;
	}
	
	private static double calculateYBar(double[] yValues, int n) {
		double sum = 0;
		for(int i = 0; i < yValues.length; i++) {
			sum += yValues[i];
		}
		return sum / n;
	}
	
	private static double calculateTopSummation(double xBar, double yBar, double[] xValues, double[] yValues, int n) {
		double sum = 0;
		for(int i = 0; i < n; i++) {
			sum += (xValues[i] - xBar) * (yValues[i] - yBar); 
		}
		return sum;
	}
	
	private static double calculateBottomSummation(double xBar, double[] xValues) {
		double sum = 0;
		for(int i = 0; i < xValues.length; i++) {
			sum += Math.pow(xValues[i] - xBar, 2);
		}
		return sum;
	}
	
	private static void printPoints(double[] xValues, double[] yValues, int n) {
		System.out.print("Points: ");
		for(int i = 0; i < n; i++) {
			System.out.print("[" + xValues[i] + "," + yValues[i] + "]\t");
		}
	}

}
