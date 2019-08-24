package StringPalindrome;

import java.util.Scanner;

public class StringPalindrome {

	public static boolean isPalindrome(String string) {
		String reverse = "";
		for(int i = (string.length()-1); i >= 0; i--) {
			reverse = reverse + string.charAt(i);
		}
		if(string.equals(reverse))
			return true;
		
		return false;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter A String");
		String string = sc.next();
		System.out.println(StringPalindrome.isPalindrome(string));
	}
}
