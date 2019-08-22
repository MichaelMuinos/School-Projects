import java.util.List;

public interface Scheduler {
	void run(List<Pair<Integer,Integer>> processes);
}
