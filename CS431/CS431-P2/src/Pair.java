public class Pair<F,S> {
	
	private F first;
	private S second;
	
	public Pair(F first, S second) {
		this.first = first;
		this.second = second;
	}
	
	public F getFirst() {
		return first;
	}
	
	public void setSecond(S second) {
		this.second = second;
	}
	
	public S getSecond() {
		return second;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		Pair<F,S> pair = ((Pair<F,S>) obj);
		return this.first == pair.first && this.second == pair.second ? true : false;
	}
	
	@Override
	protected Pair<F,S> clone() {
		return new Pair<F,S>(this.first, this.second);
	}

	@Override
	public String toString() {
		return "First: " + this.first + "\nSecond: " + this.second;
	}
	
}
