
public class ProcessControlBlock {
	// process ID
	private int pid;
	private String executingProgramName;
	// refers to username running the program
	private String username;
	// 0 = program is running 
	// 1 = process is ready
	// 2 = process is blocked
	private int currentStatus;
	// int array to store registers
	private int[] registers;
	
	public ProcessControlBlock(int pid, String executingProgramName,
			String username, int currentStatus, int[] registers) {
		this.pid = pid;
		this.executingProgramName = executingProgramName;
		this.username = username;
		this.currentStatus = currentStatus;
		this.registers = registers;
	}
	
	// copy constructor for fork
	// status must be ready
	// pid must be unique
	public ProcessControlBlock(ProcessControlBlock copy, int pid) {
		this.pid = pid;
		this.executingProgramName = copy.executingProgramName;
		this.username = copy.username;
		// status is ready
		this.currentStatus = 1;
		this.registers = copy.registers;
	}
	
	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getExecutingProgramName() {
		return executingProgramName;
	}

	public void setExecutingProgramName(String executingProgramName) {
		this.executingProgramName = executingProgramName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(int currentStatus) {
		this.currentStatus = currentStatus;
	}
	
	public void setRegisters(int[] registers) {
		this.registers = registers;
	}
	
	public int[] getRegisters() {
		return registers;
	}
	
	public String[] toHexStrings() {
		String[] hexStrings = new String[this.registers.length];
		for(int i = 0; i < this.registers.length; i++)
			hexStrings[i] = String.format("0x%08X", this.registers[i]);
		return hexStrings;
	}
}
