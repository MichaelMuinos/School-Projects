
public class SolveSystem {

	public static void main(String[] args) {
		float[][] a = new float[5][5];
		float[] b = new float[5];
		float[] x = new float[5];
		fillMatrices(a,b);
		naiveGauss(4,a,b,x);
		for(int i = 0; i < x.length - 1; ++i) {
			switch(i) {
			case 0:
				System.out.print("X1 = ");
				break;
			case 1:
				System.out.print("X2 = ");
				break;
			case 2:
				System.out.print("X3 = ");
				break;
			case 3:
				System.out.print("X4 = ");
			}
			System.out.println(x[i]);
		}
	}
	
	private static void fillMatrices(float[][] a, float[] b) {
		a[0][0] = 15; a[0][1] = -2; a[0][2] = -6; a[0][3] = 0;
		a[1][0] = -2; a[1][1] = 12; a[1][2] = -4; a[1][3] = -1;
		a[2][0] = -6; a[2][1] = -4; a[2][2] = 19; a[2][3] = -9;
		a[3][0] = 0; a[3][1] = -1; a[3][2] = -9; a[3][3] = 21;
		b[0] = 300; b[1] = 0; b[2] = 0; b[3] = 0;
	}
	
	private static void naiveGauss(int n, float[][] a, float[] b, float[] x) {
		float sum = 0, xmult = 0;
		for(int i = 0; i < n - 1; i++) {
			for(int j = i + 1; j < n + 1; j++) {
				xmult = a[j][i] / a[i][i];
				for(int k = i + 1; k < n; k++) {
					a[j][k] = a[j][k] - (xmult * a[i][k]);
				}
				b[j] = b[j] - (xmult * b[i]);
			}
		}
		x[n-1] = b[n-1] / a[n-1][n-1];
		for(int i = n - 2; i >= 0; i--) {
			sum = 0;
			for(int j = i + 1; j < n; j++) {
				sum = sum + (a[i][j] * x[j]);
			}
			x[i] = (b[i] - sum) / a[i][i];
		}
	}

}
