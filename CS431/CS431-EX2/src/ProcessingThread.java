public class ProcessingThread implements Runnable {
	
	private volatile boolean stop = false;

	private Thread processingThread;
	private FileReadThread fileReadThread;
	private Occurence occurence;
	
	public ProcessingThread(FileReadThread fileReadThread) {
		this.fileReadThread = fileReadThread;
		occurence = new Occurence();
	}
	
	public void start() {
		if(processingThread == null) {
			processingThread = new Thread(this);
			processingThread.start();
		}
	}
	
	public void run() {
		while(!stop) {
			String line = removeLine();
			countOccurences(line);
		}
	}
	
	public Occurence getOccurence() {
		return occurence;
	}
	
	public void killProcessingThread() {
		stop = true;
	}
	
	private void countOccurences(String line) {
		for(char c : line.toCharArray()) {
			if(Character.isLowerCase(c)) occurence.addLowercaseLetter();
			else if(Character.isUpperCase(c)) occurence.addUppercaseLetter();
			else if(Character.isDigit(c)) occurence.addDigit();
		}
	}
	
	private String removeLine() {
		String line = null;
		try {
			line = fileReadThread.getFileLinesQueue().take();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return line;
	}
	
}
