
public class TestCoefEval {

	public static void main(String[] args) {
		int j = 0, k = 0, n = 0, jMax = 0;
		double e = 0, h = 0, p = 0, t = 0, eMax = 0, pMax = 0, tMax = 0;
		
		n = 9;
		h = 1.6875 / n;
		
		double[] xi = new double[n];
		double[] yi = new double[n];
		double[] ai = new double[n];
		
		for(k = 0; k < n; ++k) {
			xi[k] = k * h;
			yi[k] = Math.sin(xi[k]);
		}
		
		coef(n, xi, yi, ai);
		
		for(int i = 0; i < ai.length; ++i) {
			System.out.println("Index " + i + ": " + ai[i]);
		}
		
		eMax = 0;
		
		for(j = 0; j < (4 * n); ++j) {
			t = (j * h) / 4;
			p = eval(n, xi, ai, t);
			e = Math.abs(Math.sin(t) - p);
			System.out.println("j: " + j + " t: " + t + " p: " + p + " e: " + e);
			if(e > eMax) {
				jMax = j;
				tMax = t;
				pMax = p;
				eMax = e;
			}
		}
		
		System.out.println("jMax: " + jMax + " tMax: " + tMax + " pMax: " + pMax + " eMax: " + eMax);
	}
	
	public static double eval(int n, double[] xi, double[] ai, double t) {
		int i = 0;
		double temp = 0;
		
		temp = ai[n - 1];
		
		for(i = (n-1); i >= 0; --i) {
			temp = (temp * (t - xi[i])) + ai[i];
		}
		
		return temp;
	}
	
	public static void coef(int n, double[] xi, double[] yi, double[] ai) {
		int i = 0, j = 0;
		
		for(i = 0; i < n; ++i) {
			ai[i] = yi[i];
		}
		
		for(j = 1; j < n; ++j) {
			for(i = n; i <= j; --i) {
				ai[i] = (ai[i] - ai[i - 1]) / (xi[i] - xi[i - j]);
			}
		}
		
	}

}
