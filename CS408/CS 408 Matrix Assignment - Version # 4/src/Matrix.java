import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class Matrix {
	
	private int n;
	private double[][] matrix;
	
	public Matrix(int n) {
		this.n = n;
		matrix = new double[n][n];
	}
	
	public void fillMatrix(File file) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(file));
		
		String line = null;
		int i = 0;
		int j = 0;
		while((line = br.readLine()) != null) {
			if(j == this.n) {
				++i;
				j = 0;
			}
			matrix[i][j] = Double.parseDouble(line.trim());
			++j;
		}
		
		br.close();
	}
	
	public void fillMatrix() {
		Random rand = new Random();
		for(int i = 0; i < this.n; i++) {
			for(int j = 0; j < this.n; j++) {
				this.matrix[i][j] = rand.nextInt(10);
			}
		}
	}
	
	public Matrix subtract(Matrix mat) {
		Matrix subtractMatrix = new Matrix(n);
		for(int i = 0; i < this.n; i++) {
			for(int j = 0; j < this.n; j++) {
				subtractMatrix.matrix[i][j] = this.matrix[i][j] - mat.matrix[i][j];
			}
		}
		return subtractMatrix;
	}
	
	public Matrix add(Matrix mat) {
		Matrix addMatrix = new Matrix(n);
		for(int i = 0; i < this.n; i++) {
			for(int j = 0; j < this.n; j++) {
				addMatrix.matrix[i][j] = this.matrix[i][j] + mat.matrix[i][j];
			}
		}
		return addMatrix;
	}
	
	public Matrix multiply(Matrix mat) {
		Matrix multMatrix = new Matrix(n);
		for (int i = 0; i < this.n; i++) {
           for (int j = 0; j < mat.n; j++) {
               for (int k = 0; k < this.n; k++) {
                   multMatrix.matrix[i][j] += this.matrix[i][k] * mat.matrix[k][j];
               }
           }
		}
		return multMatrix;
	}
	
	public void printMatrix(String name) {
		System.out.println("-----" + name + "-----");
		for(int i = 0; i < this.n; i++) {
			for(int j = 0; j < this.n; j++) {
				System.out.print(this.matrix[i][j] + "\t");
			}
			System.out.println("\n");
		}
	}
	
}
