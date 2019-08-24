
public class CheckIfPrime {

	private static int count = 0;
	
	public static void checkPrime(int number) {
		for(int i=2; i < number; i++) {
			if(number % i == 0) {
				count++;
				break;
			}
		}
		if(count == 1) {
			System.out.println(number + " is NOT prime.");
		} else {
			System.out.println(number + " is prime.");
		}
	}
	
}
