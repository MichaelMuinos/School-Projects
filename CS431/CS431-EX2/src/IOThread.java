import java.io.File;
import java.util.Scanner;
import java.util.regex.Pattern;

public class IOThread implements Runnable {
	
	private volatile boolean stop = false;
	
	private static final Pattern READ_COMMAND = Pattern.compile("read .*\\.txt");
	private static final String COUNT_COMMAND = "counts";
	private static final String EXIT_COMMAND = "exit";
	
	private Thread ioThread;
	private Scanner sc;
	private FileReadThread fileReadThread;
	private ProcessingThread processingThread;
	
	public IOThread(FileReadThread fileReadThread, ProcessingThread processingThread) {
		sc = new Scanner(System.in);
		this.fileReadThread = fileReadThread;
		this.processingThread = processingThread;
	}
	
	public void start() {
		if(ioThread == null) { 
			ioThread = new Thread(this);
			ioThread.start();
		}
	}
	
	public void run() {
		while(!stop) askUserInput();
		System.out.println("Stopping all threads...");
	}
	
	private void askUserInput() {
		System.out.print("> ");
		String command = sc.nextLine();
		if(command.matches(READ_COMMAND.pattern())) {
			String[] splitCommand = command.split(" ");
			if(new File(splitCommand[1]).exists())
				fileReadThread.addNewFile(splitCommand[1]);
			else System.out.println("Bad file path.");
		} else if(command.equals(COUNT_COMMAND)) {
			System.out.println(processingThread.getOccurence().toString());
		} else if(command.equals(EXIT_COMMAND)) {
			killThreads();
		} else {
			System.out.println("Bad command. Try again!");
		}
	}
	
	private void killThreads() {
		stop = true;
		fileReadThread.killFileReadThread();
		processingThread.killProcessingThread();
		System.exit(0);
	}
	
}
