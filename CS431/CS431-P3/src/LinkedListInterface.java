
public interface LinkedListInterface {
	
	public boolean addFirstFit(Job job);
	public boolean addNextFit(Job job);
	public boolean addBestFit(Job job);
	public boolean addWorstFit(Job job);
	public boolean remove(Job job);
	
}
