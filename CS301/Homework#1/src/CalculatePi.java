import java.util.Scanner;

public class CalculatePi {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		double sum = 0;
		
		System.out.println("Enter Number:");
		int num = scanner.nextInt();
		
		for(int i = 1; i < num; i++) {
			sum += Math.pow(-1, i + 1) / (2 * i - 1);
		}
		
		sum *= 4;
		
		System.out.println(sum);
		scanner.close();
	}

}
