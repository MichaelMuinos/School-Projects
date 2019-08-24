import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class BlockingQueue<T> {
	
	Semaphore mutex;
	Semaphore empty;
	Semaphore full;
	Queue<T> blockingQueue;
	
	public BlockingQueue(int n) {
		mutex = new Semaphore(1);
		empty = new Semaphore(n);
		full = new Semaphore(0);
		blockingQueue = new LinkedList<T>();
	}
	
	public void enqueue(T data) throws InterruptedException {
		empty.acquire();
		mutex.acquire();
		blockingQueue.add(data);
		mutex.release();
		full.release();
	}
	
	public T dequeue() throws InterruptedException {
		full.acquire();
		mutex.acquire();
		T data = blockingQueue.remove();
		mutex.release();
		empty.release();
		return data;
	}

	public static void main(String[] args) throws InterruptedException {
		BlockingQueue<Integer> queue = new BlockingQueue<>(100);
	    Runnable r = () -> { // replace lambda if you don’t have access to Java 8
	        for (int i = 0; i < 200; i++) {
	            try {
	                int n = queue.dequeue();
	                System.out.println(n + " removed");
	                Thread.sleep(500);
	            } catch (Exception e) {}
	        }
	    };
	    Thread t = new Thread(r);
	    t.start();
	    for (int i = 0; i < 200; i++) {
	        System.out.println("Adding " + i);
	        queue.enqueue(i);
	    }
	}

}
