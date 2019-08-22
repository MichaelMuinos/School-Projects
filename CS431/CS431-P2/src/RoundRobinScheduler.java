import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class RoundRobinScheduler implements Scheduler {
	
	@Override
	public void run(List<Pair<Integer, Integer>> processes) {
		System.out.println("Running round robin scheduler with quantum 50.");
		runHelper(50, processes, processes.size());
		System.out.println("Running round robin scheduler with quantum 100.");
		runHelper(100, processes, processes.size());
	}
	
	private void runHelper(int quantum, List<Pair<Integer, Integer>> processes, int size) {
		Queue<Pair<Integer,Integer>> queue = new LinkedList<>();
		for(Pair<Integer,Integer> pair : processes) queue.add(pair.clone());
		int totalSum = 0;
		int cycleSum = 0;
		while(!queue.isEmpty()) {
			Pair<Integer,Integer> pair = queue.remove();
			int id = pair.getFirst();
			int cycles = pair.getSecond();
			if(cycles > quantum) {
				pair.setSecond(cycles - quantum);
				cycleSum += quantum;
				queue.add(pair);
			} else {
				// cycles is less than or equal to our quantum
				cycleSum += cycles;
				totalSum += cycleSum;
				System.out.println("Process " + id + " finishes on cycle " + cycleSum + ".");
			}
		}
		double avg = totalSum / size;
		System.out.println("Average turnaround time: " + avg + ".\n");
	}

}
