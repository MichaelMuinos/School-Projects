import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ShortestFirstScheduler implements Scheduler {
	
	private List<Pair<Integer, Integer>> copyProcesses;
	
	public ShortestFirstScheduler() {
		copyProcesses = new ArrayList<>();
	}

	@Override
	public void run(List<Pair<Integer, Integer>> processes) {
		System.out.println("Running shortest first scheduler.");
		for(Pair<Integer,Integer> pair : processes) copyProcesses.add(pair.clone());
		Collections.sort(copyProcesses, new Comparator<Pair<Integer,Integer>>() {
			@Override
			public int compare(Pair<Integer, Integer> o1,
					Pair<Integer, Integer> o2) {
				return o1.getSecond() - o2.getSecond();
			}
			
		});
		int totalSum = 0;
		int cycleSum = 0;
		for(int i = 0; i < copyProcesses.size(); i++) {
			cycleSum += copyProcesses.get(i).getSecond();
			totalSum += cycleSum;
			System.out.println("Process " +  copyProcesses.get(i).getFirst() + " finishes on cycle " + cycleSum + ".");
		}
		double avg = totalSum / copyProcesses.size();
		System.out.println("Average turnaround time: " + avg + ".");
	}

}
