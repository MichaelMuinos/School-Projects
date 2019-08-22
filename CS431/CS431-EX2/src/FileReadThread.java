import java.io.BufferedReader;
import java.io.FileReader;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class FileReadThread implements Runnable {
	
	private volatile boolean stop = false;

	private Thread fileReadThread;
	private BlockingQueue<String> fileNameQueue;
	private BlockingQueue<String> fileLinesQueue;
	
	public FileReadThread() {
		fileNameQueue = new LinkedBlockingQueue<>();
		fileLinesQueue = new LinkedBlockingQueue<>();
	}
	
	public void start() {
		if(fileReadThread == null) { 
			fileReadThread = new Thread(this);
			fileReadThread.start();
		}
	}
	
	public void run() {
		while(!stop) {
			String fileName = readFileFromQueue();
			try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
				String line;
				while((line = br.readLine()) != null) 
					fileLinesQueue.add(line);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void killFileReadThread() {
		stop = true;
	}
	
	public void addNewFile(String fileName) {
		fileNameQueue.add(fileName);
	}
	
	public BlockingQueue<String> getFileNameQueue() {
		return fileNameQueue;
	}
	
	public BlockingQueue<String> getFileLinesQueue() {
		return fileLinesQueue;
	}
	
	private String readFileFromQueue() {
		String name = null;
		try {
			name = fileNameQueue.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return name;
	}
	
}
