import java.util.ArrayList;
import java.util.List;


public class FirstComeFirstServeScheduler implements Scheduler {
	
	private List<Pair<Integer, Integer>> copyProcesses;
	
	public FirstComeFirstServeScheduler() {
		copyProcesses = new ArrayList<>();
	}

	@Override
	public void run(List<Pair<Integer, Integer>> processes) {
		System.out.println("Running First-come, first-served scheduler.");
		for(Pair<Integer,Integer> pair : processes) copyProcesses.add(pair.clone());
		int totalSum = 0;
		int cycleSum = 0;
		for(int i = 0; i < copyProcesses.size(); i++) {
			cycleSum += copyProcesses.get(i).getSecond();
			totalSum += cycleSum;
			System.out.println("Process " + copyProcesses.get(i).getFirst() + " finishes on cycle " + cycleSum + ".");
		}
		double avg = totalSum / copyProcesses.size();
		System.out.println("Average turnaround time: " + avg + ".");
	}

}
