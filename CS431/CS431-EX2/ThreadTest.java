
public class ThreadTest {

	public static void main(String[] args) {
		FileReadThread fileReadThread = new FileReadThread();
		ProcessingThread processingThread = new ProcessingThread(fileReadThread);
		IOThread ioThread = new IOThread(fileReadThread, processingThread);
		// start all threads
		ioThread.start();
		fileReadThread.start();
		processingThread.start();
	}

}
