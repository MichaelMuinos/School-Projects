import java.util.Random;

public class MonteCarloMethod {

	public static void main(String[] args) {
		System.out.println("The following program approximates PI using \n"
				+ "the Monte Carlo Method.");
		
		Random rand = new Random(System.currentTimeMillis());
		
		double approximations = 10000000.0, insideCircle = 0.0;
		
		for(int i = 0; i < approximations; ++i) {
			double xValue = (rand.nextDouble()) * 2 - 1.0;
			double yValue = (rand.nextDouble()) * 2 - 1.0;
			
			if(isInsideBoundaries(xValue, yValue)) {
				++insideCircle;
			}
		}
		
		double pi = (4.0 * (insideCircle / approximations));
		System.out.println("\nPI: " + pi);
	}
	
	private static boolean isInsideBoundaries(double x, double y) {
		double distance = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
		if(distance < 1.0) {
			return true;
		}
		return false;
	}

}
