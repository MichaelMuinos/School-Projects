import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ProcessScheduler {
	
	private static final Pattern INPUT_PATTERN = Pattern.compile(".*\\.csv");
	private static final Scheduler[] schedulers = {
		new FirstComeFirstServeScheduler(),
		new ShortestFirstScheduler(),
		new RoundRobinScheduler(),
		new RandomScheduler()
	};
	
	public static void main(String[] args) {
		if(args.length == 1 && args[0].matches(INPUT_PATTERN.pattern()))
			startSchedulers(returnData(args[0]));
		else 
			System.out.println("Incorrent input.");
	}
	
	private static List<Pair<Integer,Integer>> returnData(String fileName) {
		List<Pair<Integer,Integer>> processes = new ArrayList<>();
		String line = "";
		try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			while((line = br.readLine()) != null) {
				String[] splitString = line.split(",");
				Pair<Integer,Integer> process = new Pair<>(Integer.parseInt(splitString[0]), Integer.parseInt(splitString[1]));
				processes.add(process);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return processes;
	}
	
	private static void startSchedulers(List<Pair<Integer,Integer>> processes) {
		for(int i = 0; i < schedulers.length; i++) {
			schedulers[i].run(processes);
			System.out.println();
		}
	}

}
