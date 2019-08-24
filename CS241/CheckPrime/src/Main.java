import java.util.Scanner;

public class Main {

	private static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		//userInput();
		System.out.println("yo");
		String d = sc.next();
		String s = sc.next();
	}
	
	public static void userInput() {
		System.out.println("Enter Number");
		int number = sc.nextInt();
		CheckIfPrime.checkPrime(number);	
	}
	
}

