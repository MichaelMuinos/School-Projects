import java.util.Scanner;


public class Main {
	
	private static Scanner sc = new Scanner(System.in);
	
	private static int age = 21;
	private static int ageS = age * age;
	private static int ageH = age / 2;
	
	public static void main(String[] args) {
		System.out.println("Age: " + age);
		System.out.println("Age Squared: " + ageS);
		System.out.println("Age Halved: " + ageH);
		
		System.out.println(multiply(age,ageH));
		System.out.println(divide(ageS,ageH));
		System.out.println(remainder(ageS,ageH));
		System.out.println(subtract(ageH,age));
		System.out.println(divideSecond(age,ageS));
		System.out.println(add(ageS,age));
		System.out.println(mean(age,ageS,ageH));
	}
	
	private static int multiply(int age, int ageH) {
		int total = age * ageH;
		return total;
	}
	
	private static int divide(int ageS, int ageH) {
		int total = ageS / ageH;
		return total;
	}
	
	private static int remainder(int ageS, int ageH) {
		int remainder = ageS % ageH;
		return remainder;
	}
	
	private static int subtract(int ageH, int age) {
		int total = ageH - age;
		return total;
	}
	
	private static int divideSecond(int age, int ageS) {
		int total = age / ageS;
		return total;
	}
	
	private static int add(int ageS, int age) {
		int total = ageS + age;
		return total;
	}
	
	private static int mean(int age, int ageS, int ageH) {
		int total = (age + ageS + ageH) / 3;
		return total;
	}
	
}
