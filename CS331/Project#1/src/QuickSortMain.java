
public class QuickSortMain {
	
	private static int[] list = 
			{29,37,42,44,39,17,19,22,
			33,13,24,31,14,16,45,
			38,26,47,48,36};

	public static void main(String[] args) {
		printList();
		quickSort(list);
	}
	
	public static void printList() {
		System.out.println("Original List:");
		for(int i = 0; i < list.length; ++i) {
			if(i != 0 && i % 10 == 0) {
				System.out.println();
			}
			System.out.print(list[i] + " ");
		}
		System.out.println("\n");
	}
	
	
	
	public static int[] quickSort(int[] numbers) {
		quickSortHelper(numbers, 0, numbers.length - 1);
		return numbers;
	}
	
	private static void printPartialList(int start, int end) {
		System.out.println("Partial List:");
		for(int i = start; i < end + 1; ++i) {
			if(end - start >= 10) {
				if(i != 0 && i % 10 == 0) {
					System.out.println();
				}
			}
			System.out.print(list[i] + " ");
		}
		System.out.println("\n");
	}
	
	private static void quickSortHelper(int[] numbers, int init, int last) {
		int size = (last - init) + 1;
		
		if(last - init < 1 || last  == 0) {
			return;
		}
		
		if(size <= 5) {
			System.out.println("Starting Exchange Sort...");
			exchangeSort(numbers, init, last);
		}
		
		int mid = (init + last) / 2;
		if(numbers[init] > numbers[mid]) {
			swap(numbers,init,mid);
		}
		if(numbers[init] > numbers[last]) {
			swap(numbers,init,last);
		}
		if(numbers[mid] > numbers[last]) {
			swap(numbers,mid,last);
		}
		swap(numbers, mid, last);
		
		int pivot = partition(numbers, init, last);
		
		printPartialList(init,last);
		printList();
		
		quickSortHelper(numbers, init, pivot - 1);
		quickSortHelper(numbers, pivot + 1, last);
	}
	
	
	private static int partition(int[] numbers, int init, int last) {
		int lastPosition = init;
		
		System.out.println("Pivot: " + numbers[last] + "\n");
		
		for(int i = init; i < last; i++) {
			if(numbers[i] < numbers[last]) {
				swap(numbers, lastPosition, i);
				++lastPosition;
			}
		}
				
		swap(numbers, last, lastPosition);
		return lastPosition;
	}
	
	private static void exchangeSort(int[] numbers, int init, int last) {
		int temp = 0;
		for(int i = init; i < last; ++i) {
			for(int j = i + 1; j < last; ++j) {
				if(numbers[i] > numbers[j]) {
					temp = numbers[i];
					numbers[i] = numbers[j];
					numbers[j] = temp;
				}
			}
		}
	}
	
	private static void swap(int[] numbers, int num1, int num2) {
		int temp = numbers[num1];
		numbers[num1] = numbers[num2];
		numbers[num2] = temp; 
	}

}
