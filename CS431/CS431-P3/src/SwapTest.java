import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;


public class SwapTest {
	
	private static final Pattern ADD_COMMAND = Pattern.compile("add [0-9]+ [0-9]+");
	private static final Pattern FF_COMMAND = Pattern.compile("ff [0-9]+");
	private static final Pattern NF_COMMAND = Pattern.compile("nf [0-9]+");
	private static final Pattern BF_COMMAND = Pattern.compile("bf [0-9]+");
	private static final Pattern WF_COMMAND = Pattern.compile("wf [0-9]+");
	private static final Pattern DE_COMMAND = Pattern.compile("de [0-9]+");
	private static final String JOB_COMMAND = "jobs";
	private static final String LIST_COMMAND = "list";
	private static final String EXIT_COMMAND = "exit";
	
	private static final Scanner sc = new Scanner(System.in);
	private static Map<Integer, Job> jobMap = new HashMap<Integer, Job>();
	private static SegmentLinkedList linkedList = new SegmentLinkedList();
	
	public static void main(String[] args) {
		showCommandLineInterface();
	}
	
	private static void showCommandLineInterface() {
		while(true) {
			System.out.print("> ");
			String command = sc.nextLine();
			// split command based on space
			String[] splitCommand = command.split(" ");
			if(command.matches(ADD_COMMAND.pattern())) {
				addJobToMap(Integer.parseInt(splitCommand[1]), Integer.parseInt(splitCommand[2]));
			} else if(command.matches(FF_COMMAND.pattern())) {
				if(isValidJob(Integer.parseInt(splitCommand[1]))) {
					if(!linkedList.addFirstFit(jobMap.get(Integer.parseInt(splitCommand[1]))))
						System.out.println("Error: Could not allocate with First Fit!");
				}
			} else if(command.matches(NF_COMMAND.pattern())) {
				if(isValidJob(Integer.parseInt(splitCommand[1]))) {
					if(!linkedList.addNextFit(jobMap.get(Integer.parseInt(splitCommand[1]))))
						System.out.println("Error: Could not allocate with Next Fit!");
				}
			} else if(command.matches(BF_COMMAND.pattern())) {
				if(isValidJob(Integer.parseInt(splitCommand[1]))) {
					if(!linkedList.addBestFit(jobMap.get(Integer.parseInt(splitCommand[1]))))
						System.out.println("Error: Could not allocate with Best Fit!");
				}
			} else if(command.matches(WF_COMMAND.pattern())) {
				if(isValidJob(Integer.parseInt(splitCommand[1]))) {
					if(!linkedList.addWorstFit(jobMap.get(Integer.parseInt(splitCommand[1]))))
						System.out.println("Error: Could not allocate with Worst Fit!");
				}
			} else if(command.matches(DE_COMMAND.pattern())) {
				if(isValidJob(Integer.parseInt(splitCommand[1]))) {
					if(!linkedList.remove(jobMap.get(Integer.parseInt(splitCommand[1]))))
						System.out.println("Error: Could not deallocate job!");
					else
						jobMap.remove(Integer.parseInt(splitCommand[1]));
				}
			} else if(command.equals(JOB_COMMAND)) {
				printJobs();
			} else if(command.equals(LIST_COMMAND)) {
				printContents();
			} else if(command.equals(EXIT_COMMAND)) {
				killProgram();
			} else {
				System.out.println("Bad command!");
			}
		}
	}
	
	private static boolean isValidJob(int jobNumber) {
		return jobMap.containsKey(jobNumber);
	}
	
	private static void addJobToMap(int jobNumber, int size) {
		if(jobNumber == 0) {
			System.out.println("Error: You can't allocate a hole!");
		} else if(!jobMap.containsKey(jobNumber)) {
			jobMap.put(jobNumber, new Job(jobNumber, size));
		} else {
			System.out.println("Error: Job already exists!");
		}
	}
	
	private static void printJobs() {
		for(Map.Entry<Integer, Job> entry : jobMap.entrySet())
			System.out.print(entry.getValue().toString() + " ");
		System.out.println();
	}
	
	private static void printContents() {
		System.out.println(linkedList.toString());
	}
	
	private static void killProgram() {
		System.exit(0);
	}

}
