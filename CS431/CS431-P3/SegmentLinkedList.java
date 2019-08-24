
public class SegmentLinkedList implements LinkedListInterface {
	
	private static final int INITIAL_SIZE = 100;
	private Segment start;
	private Segment lastInsertedSegment;
	
	private enum Allocation {
		FF,
		NF,
		BF,
		WF
	}
	
	public SegmentLinkedList() {
		start = new Segment(0, 0, INITIAL_SIZE, null);
		lastInsertedSegment = start;
	}

	// still need to check if size is = to length. if thats the case, there is no more hole
	// i would just need to replace the hole with the job pid
	@Override
	public boolean addFirstFit(Job job) {
		Segment parent = null;
		Segment current = start;
		// start from head and traverse until we find a hole that will
		// fit our new segment
		while(current != null) {
			if(isHole(current) && job.getSize() <= current.getLength())
				return addHelper(job, current, parent, Allocation.FF);
			// set our current segment to be the parent
			parent = current;
			// shift current segment over by 1
			current = current.getNext();
		}
		return false;
	}

	@Override
	public boolean addNextFit(Job job) {
		Segment parent = null;
		Segment current = lastInsertedSegment;
		// start from head and traverse until we find a hole that will
		// fit our new segment
		System.out.println("Starting segment ID: " + current.getPid() + " " + current.getLength());
		while(current != null) {
			if(isHole(current) && job.getSize() <= current.getLength())
				return addHelper(job, current, parent, Allocation.NF);
			// set our current segment to be the parent
			parent = current;
			// shift current segment over by 1
			current = current.getNext();
		}
		// traverse from the start to the lastInsertedSegment
		parent = null;
		current = start;
		while(current != lastInsertedSegment) {
			if(isHole(current) && job.getSize() <= current.getLength())
				return addHelper(job, current, parent, Allocation.NF);
			// set our current segment to be the parent
			parent = current;
			// shift current segment over by 1
			current = current.getNext();
		}
		return false;
	}

	@Override
	public boolean addBestFit(Job job) {
		Segment smallestHoleParent = null;
		Segment smallestHole = null;
		Segment currentParent = null;
		Segment current = start;
		int minDifference = Integer.MAX_VALUE;
		// the purpose of this traversal is to find the smallest hole
		// that will fill our new segment
		while(current != null) {
			if(isHole(current) && job.getSize() <= current.getLength() 
					&& (current.getLength() - job.getSize()) < minDifference) {
				// we found our smallest hole at this point thus far
				smallestHoleParent = currentParent;
				smallestHole = current;
			}
			// set current segment to be the parent
			currentParent = current;
			// shift current segment over by 1
			current = current.getNext();
		}
		// if found smallest hole, add new segment
		// otherwise return false and do nothing
		if(smallestHole != null)
			return addHelper(job, smallestHole, smallestHoleParent, Allocation.BF);
		else
			return false;
	}

	@Override
	public boolean addWorstFit(Job job) {
		Segment largestHoleParent = null;
		Segment largestHole = null;
		Segment currentParent = null;
		Segment current = start;
		int maxDifference = Integer.MIN_VALUE;
		// the purpose of this traversal is to find the largest hole
		// that will fill our new segment
		while(current != null) {
			if(isHole(current) && job.getSize() <= current.getLength() 
					&& (current.getLength() - job.getSize()) > maxDifference) {
				// we found our largest hole at this point thus far
				largestHoleParent = currentParent;
				largestHole = current;
			}
			// set current segment to be the parent
			currentParent = current;
			// shift current segment over by 1
			current = current.getNext();
		}
		// if found largest hole, add new segment
		// otherwise return false and do nothing
		if(largestHole != null)
			return addHelper(job, largestHole, largestHoleParent, Allocation.WF);
		else
			return false;
	}

	// case 1: remove head and tail (1 segment in the list)
	// case 2: remove head w/ hole on right
	// case 3: remove head w/ no hole on right
	// case 4: remove tail w/ hole on left
	// case 5: remove tail w/ no hole on left
	// case 6: remove w/ no hole on either side
	// case 7: remove w/ holes on both sides
	// case 8: remove w/ hole on left only
	// case 9: remove w/ hole on right only
	// case 10: remove pid that is not there (return false)
	@Override
	public boolean remove(Job job) {
		Segment parent = null;
		Segment current = start;
		while(current != null) {
			if(current.getPid() == job.getPid()) {
				// restart last inserted segment if its being deleted
				if(current.getPid() == lastInsertedSegment.getPid()) lastInsertedSegment = start;
				// case 1
				if(parent == null && current.getNext() == null) {
					current.setPid(0);
				// case 2
				} else if(parent == null && isHole(current.getNext())) {
					current.setLength(current.getLength() + current.getNext().getLength());
					current.setNext(current.getNext().getNext());
					current.setPid(0);
				// case 3
				} else if(parent == null && !isHole(current.getNext())) {
					current.setPid(0);
				// case 4
				} else if(current.getNext() == null && isHole(parent)) {
					parent.setLength(parent.getLength() + current.getLength());
					parent.setNext(null);
				// case 5
				} else if(current.getNext() == null && !isHole(parent)) {
					current.setPid(0);
				// case 6
				} else if(!isHole(parent) && !isHole(current.getNext())) {
					current.setPid(0);
				// case 7
				} else if(isHole(parent) && isHole(current.getNext())) {
					parent.setLength(parent.getLength() + current.getLength() + current.getNext().getLength());
					parent.setNext(current.getNext().getNext());
				// case 8
				} else if(isHole(parent) && !isHole(current.getNext())) {
					parent.setLength(parent.getLength() + current.getLength());
					parent.setNext(current.getNext());
				// case 9
				} else {
					current.getNext().setLength(current.getNext().getLength() + current.getLength());
					current.getNext().setStart(current.getStart());
					parent.setNext(current.getNext());
				}
				return true;
			}
			parent = current;
			current = current.getNext();
		}
		// case 10
		return false;
	}

	@Override
	public String toString() {
		String linkedListStr = "";
		Segment current = start;
		while(current != null) {
			linkedListStr += current.toString() + " ";
			current = current.getNext();
		}
		return linkedListStr;
	}
	
	private boolean addHelper(Job job, Segment current, Segment parent, Allocation type) {
		// if our job size fits inside the hole perfectly, just
		// reset the pid of the hole to be the job pid
		if(job.getSize() == current.getLength()) {
			current.setPid(job.getPid());
			if(type == Allocation.NF) lastInsertedSegment = current;
		// otherwise, set a new segment into the list
		} else {
			Segment newSegment = new Segment(job.getPid(), current.getStart(), job.getSize(), current);
			current.setStart(newSegment.getStart() + newSegment.getLength());
			current.setLength(current.getLength() - newSegment.getLength());
			if(current == start) {
				lastInsertedSegment = newSegment;
				start = newSegment;
			} else 
				parent.setNext(newSegment);
			if(type == Allocation.NF) lastInsertedSegment = newSegment;
		}
		return true;
	}
	
	private boolean isHole(Segment segment) {
		return segment.getPid() == 0 ? true : false;
	}

}
