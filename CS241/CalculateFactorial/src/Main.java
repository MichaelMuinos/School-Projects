import java.util.Scanner;


public class Main {

	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		System.out.println("Enter Number...\n");
		double number = sc.nextInt();
		System.out.println("Number: " + number);
		for(int i = (int) (number-1); i > 0; i--) {
			number = number * i;
		}
		System.out.println("Factorial: " + number);
	}
}
