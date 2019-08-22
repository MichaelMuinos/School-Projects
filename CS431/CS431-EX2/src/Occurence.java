
public class Occurence {
	
	private static final int SIZE = 3;
	// 0 = lower, 1 = upper, 2 = digit
	private int[] chars;
	
	public Occurence() {
		chars = new int[SIZE];
	}
	
	public void addLowercaseLetter() {
		this.chars[0]++;
	}
	
	public void addUppercaseLetter() {
		this.chars[1]++;
	}
	
	public void addDigit() {
		this.chars[2]++;
	}

	@Override
	public String toString() {
		return "Lowercase Letters: " + this.chars[0] + "\n" +
				"Uppercase Letters: " + this.chars[1] + "\n" +
				"Digits: " + this.chars[2] + "\n";
	}
	
}
