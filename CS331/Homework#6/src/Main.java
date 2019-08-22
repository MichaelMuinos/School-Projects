
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int sum = recurrenceRelation(625);
		System.out.println(sum);
	}
	
	public static int recurrenceRelation(int n) {
		if(n == 1) {
			return 1;
		}
		return (7 * recurrenceRelation(n / 5)) + (10 * n);
	}

}
