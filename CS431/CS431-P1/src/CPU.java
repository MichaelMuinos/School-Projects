import java.util.Random;

public class CPU {
	private final int ARRAY_SIZE = 6;
	private final String[] registerNames = {"PC", "SP", "R0", "R1", "R2", "R3"};
	
	// 0 = PC, 1 = SP, 2 = R0, 3 = R1, 4 = R2, 5 = R3
	private int[] registerValues;
	private Random rand;
	
	public CPU() {
		registerValues = new int[ARRAY_SIZE];
		rand = new Random();
	}
	
	public void setRegisterValues(int[] registerValues) {
		this.registerValues = registerValues;
	}
	
	public int[] getRegisterValues() {
		return registerValues;
	}
	
	public int[] getRandomRegisterValues() {
		int[] randomValues = new int[ARRAY_SIZE];
		for(int i = 0; i < randomValues.length; i++)
			randomValues[i] = rand.nextInt(Integer.MAX_VALUE);
		return randomValues;
	}
	
	public void resetRegisters() {
		setRegisterValues(new int[] {0, 0, 0, 0, 0, 0});
	}

	@Override
	public String toString() {
		String cpuString = "";
		for(int i = 0; i < ARRAY_SIZE; i++) {
			if((i+1) % 2 != 0) 
				cpuString += " " + registerNames[i] + " = " + String.format("0x%08X", registerValues[i]) + "\t"; 
			else
				cpuString += registerNames[i] + " = " + String.format("0x%08X", registerValues[i]) + "\n";
		}
		return cpuString;
	}
}
