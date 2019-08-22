import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomScheduler implements Scheduler {
	
	private final int QUANTUM = 50;
	private List<Pair<Integer,Integer>> copyProcesses;
	private Random random;
	
	public RandomScheduler() {
		copyProcesses = new ArrayList<>();
		random = new Random();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void run(List<Pair<Integer, Integer>> processes) {
		System.out.println("Running random scheduler with quantum 50.");
		int cycleSum = 0;
		int totalSum = 0;
		int size = processes.size();
		for(Pair<Integer,Integer> pair : processes) copyProcesses.add(pair.clone());
		while(!copyProcesses.isEmpty()) {
			int probabilitySum = 0;
			Pair<Integer,Integer>[] ranges = new Pair[copyProcesses.size()];
			// populate our array of ranges
			for(int i = 0; i < copyProcesses.size(); i++) {
				int previousSum = probabilitySum;
				probabilitySum += copyProcesses.get(i).getSecond();
				ranges[i] = new Pair<Integer,Integer>(previousSum + 1, probabilitySum);
			}
			int index = 0;
			// get random number to check within our ranges
			int randNum = random.nextInt(probabilitySum) + 1;
			// determine which pair to select based on the ranges
			for(int i = 0; i < ranges.length; i++)  {
				if(withinRange(ranges[i], randNum)) index = i;
			}
			Pair<Integer,Integer> pair = copyProcesses.get(index);
			int id = pair.getFirst();
			int cycles = pair.getSecond();
			if(cycles > QUANTUM) {
				pair.setSecond(cycles - QUANTUM);
				cycleSum += QUANTUM;
			} else {
				// cycles is less than or equal to our quantum
				cycleSum += cycles;
				totalSum += cycleSum;
				copyProcesses.remove(index);
				System.out.println("Process " + id + " finishes on cycle " + cycleSum + ".");
			}
		}
		double avg = totalSum / size;
		System.out.println("Average turnaround time: " + avg + ".\n");
	}
	
	private boolean withinRange(Pair<Integer,Integer> range, int num) {
		return num >= range.getFirst() && num <= range.getSecond() ? true : false;
	}
	
}
