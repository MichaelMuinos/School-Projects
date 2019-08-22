
public class RandomNumberGenerator {

	public static void main(String[] args) {
		System.out.println("Random-Number Recursive Pseudocode: "
				+ "\nFound on page 482.\n");
		System.out.println("Printing every 50th random number...");
		
		// Initializing seed count
		double[] seeds = new double[1000];
		
		// Initializing random numbers array
		double[] randomNums = new double[1000];
		
		double k = Math.pow(7, 5);
		double j = Math.pow(2, 31) - 1;
		
		// Starting seed is greater than 1 and less than 2^31 - 1
		seeds[0] = 123456.0;
		randomNums[0] = ((k * seeds[0]) % j) / j;
		
		// Fill arrays and compute random numbers
		for(int i = 1; i < randomNums.length; i++) {
			seeds[i] = (k * seeds[i - 1]) % j;
			randomNums[i] = seeds[i] / j;
		}
		
		printEveryFifty(randomNums);
	}
	
	private static void printEveryFifty(double[] arr) {
		for(int i = 0; i < arr.length; ++i) {
			if(i % 50 == 0) {
				System.out.println(i + ": " + arr[i]);
			}
		}
	}
	
}
