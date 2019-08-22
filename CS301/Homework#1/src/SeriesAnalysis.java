
public class SeriesAnalysis {

	public static void main(String[] args) {
		double sum = 0;
		//For ln(1+x)
		for(int i = 1; i <= 8; i++) {
			sum += (Math.pow(-1, i + 1) * Math.pow(1, i)) / i;
		}
		double inside = (1.0 + (1.0/3.01)) / (1.0 - (1.0/3.01));
		double sumTwo = Math.log(inside);
		System.out.println(sum);
		System.out.println(sumTwo);
		
		double absoluteErrorSum = sumTwo - sum;
		System.out.println("Absolute Error: " + absoluteErrorSum);
		double relativeErrorSum = absoluteErrorSum * 100;
		System.out.println("Relative Error: " + relativeErrorSum);
		
		double absoluteErrorSumTwo = 0.69313 - sumTwo;
		System.out.println("Absolute Error: " + absoluteErrorSumTwo);
		double relativeErrorSumTwo = absoluteErrorSumTwo * 100;
		System.out.println("Relative Error: " + relativeErrorSumTwo);

	}

}
