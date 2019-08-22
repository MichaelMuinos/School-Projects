
public class EulerTwo {

	public static void main(String[] args) {
		double x = 1.0;
		double s = 1.0;
		long start = System.nanoTime();
		for(double i = 1; i <= 5000.0; ++i) {
			x += 1;
			s += Math.log(i + 0.5) - 1.0 / x;
		}
		long end = System.nanoTime();
		System.out.println(end - start);
		System.out.println("Final S: " + s);
	}

}
