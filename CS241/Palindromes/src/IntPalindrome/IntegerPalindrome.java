package IntPalindrome;

import java.util.Scanner;

public class IntegerPalindrome {

	public static boolean isPalindrome(int number) {
		int num = number;
		int temp = 0;
		int reversedNumber = 0;
		
		while(num > 0) {
            temp = num % 10;
            num = num / 10;
            reversedNumber = reversedNumber * 10 + temp;
		}
		
		if(number == reversedNumber) 
			return true;
		return false;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter A Number");
		int number = sc.nextInt();
		System.out.println(IntegerPalindrome.isPalindrome(number));
	}
}
