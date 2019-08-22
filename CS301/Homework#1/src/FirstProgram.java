
public class FirstProgram {

	public static void main(String[] args) {
		int imin = 0, n = 30;
		double error = 0, y = 0, x = 0.5;
		double h = 1, emin = 1;
		for(int i = 1; i <= n; ++i) {
			h = h * 0.25;
			y = (Math.sin(x + h) - Math.sin(x)) / h;
			error = Math.abs(Math.cos(x) - y);
			System.out.println("i: " + i + "  h: " + h + "  y: " + y + "  error: " + error);
			if(error < emin) {
				emin = error;
				imin = i;
			}
		}
		System.out.println("imin: " + imin + "  emin: " + emin);
	}

}
