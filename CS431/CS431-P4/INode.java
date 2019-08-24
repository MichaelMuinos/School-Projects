
public class INode {
	
	private String fileName;
	private int startingBlock;
	private int size;
	
	public INode(String fileName, int startingBlock, int size) {
		this.fileName = fileName;
		this.startingBlock = startingBlock;
		this.size = size;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getStartingBlock() {
		return startingBlock;
	}

	public void setStartingBlock(int startingBlock) {
		this.startingBlock = startingBlock;
	}
	
	public int getSize() {
		return size;
	}
	
	public void setSize(int size) {
		this.size = size;
	}

}
