
public class Main {

	private static int[] arr = {10,2,6,1,7,8,12};
	
	public static void main(String[] args) {
		System.out.println(findMax());
	}
	
	private static int findMax() {
		int max = arr[0];
		for(int i = 1; i < arr.length; ++i) {
			if(arr[i] > max) {
				max = arr[i];
			}
		}
		return max;
	}

}
