
public class Euler {

	public static void main(String[] args) {
		double x = 1.0;
		double s = 1.0;
		long start = System.nanoTime();
		for(double i = 1; i <= 100000000000.0; ++i) {
			x += 1;
			s += 1.0 / x;
			if(i % 100 == 0) {
				System.out.println(i);
				System.out.println("S: " + s);
			}
		}
		long end = System.nanoTime();
		System.out.println(end - start);
		System.out.println("Final S: " + s);
	}

}
