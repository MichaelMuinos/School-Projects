
public class Test_NGE {

	public static void main(String[] args) {
		int m = 11;
		float[][] a = new float[m][m];
		float[] b = new float[m];
		float[] x = new float[m];
		
		// 4 to 10
		for(int n = 4; n <= 10; n++) {
			System.out.println("Value: " + n);
			for(int i = 1; i < n + 1; i++) {
				for(int j = 1; j < n + 1; j++) {
					a[i][j] = (float) Math.pow(i + 1, j - 1);
				}
				b[i] = (float) ((Math.pow(i + 1, n) - 1) / i);
			}
			System.out.println("Performing Gauss Elimination...");
			naiveGauss(n,a,b,x);
			System.out.print("n: " + n + "  x: " + x[0] + "\n");
		}
	}
	
	private static void naiveGauss(int n, float[][] a, float[] b, float[] x) {
		float sum = 0, xmult = 0;
		for(int k = 1; k < n; k++) {
			if(k == 1) {
				System.out.println("Calculating '1' diagonal form...");
			}
			for(int i = k + 1; i < n + 1; i++) {
				xmult = a[i][k] / a[k][k];
				a[i][k] = xmult;
				for(int j = k + 1; j < n + 1; j++) {
					a[i][j] = a[i][j] - (xmult * a[k][j]);
				}
				b[i] = b[i] - (xmult * b[k]);
			}
		}
		x[n] = b[n] / a[n][n];
		for(int i = n - 1; i < 2; i++) {
			sum = b[i];
			for(int j = i + 1; j < n + 1; j++) {
				sum = sum - (a[i][j] * x[j]);
			}
			x[i] = sum / a[i][i];
			System.out.println("Found x[" + i + "]");
		}
	}

}
