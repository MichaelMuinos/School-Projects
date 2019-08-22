import java.util.Random;

public class ProbabilityQuestion {
	private static final int NORTH = 1;
	private static final int EAST = 2;
	private static final int SOUTH = 3;
	private static final int WEST = 4;
	
	private static final int MAX = 50;
	private static final int MIN = 0;
	
	private static int xPos = 25;
	private static int yPos = 25;
		
	private static Random rand = new Random();
	
	public static void main(String[] args) {
		System.out.println("***Each direction has a 25 percent chance***\n");
		System.out.println("Moving drunkard 50 steps randomly...");
		System.out.println("Starting Position: [25][25]");
		
		for(int i = 1; i <= 50; i++) {
			int num = rand.nextInt(4) + 1;
			changePosition(num);
			System.out.println("Step " + i + " ---> Position: [" + xPos + "][" + yPos + "]");
		}
		
		int distance = (int) calculateDistance();
		System.out.println("\nDistance: " + distance + " units");
		
		System.out.println("\nConclusion: The probability for there to be a distance of"
				+ " more than 20 units would be less than 1 percent.");
	}
	
	private static double calculateDistance() {
		return Math.sqrt(Math.pow(xPos - 25, 2) + Math.pow(yPos - 25, 2));
	}
	
	private static void changePosition(int num) {
		switch(num) {
		case NORTH:
			if(!((yPos + 1) > MAX)) {
				++yPos;
			}
			break;
		case EAST:
			if(!((xPos + 1) > MAX)) {
				++xPos;
			}
			break;
		case SOUTH:
			if(!((yPos - 1) < MIN)) {
				--yPos;
			}
			break;
		case WEST:
			if(!((xPos - 1) < MIN)) {
				--xPos;
			}
			break;
		}
	}

}
