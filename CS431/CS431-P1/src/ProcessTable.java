import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ProcessTable {
	private static final int RUNNING = 0;
	private static final int READY = 1;
	private static final int BLOCKED = 2;
	
	private static final String FORMAT = "%10s%17s%17s%15s%22s%22s%22s%22s%22s%22s\n";
	
	private static final int DEFAULT_PID = 1;
	private static final String DEFAULT_USER = "root";
	private static final String DEFAULT_PROGRAM_NAME = "init";
	
	private static final String FORK_COMMAND = "fork";
	private static final String BLOCK_COMMAND = "block";
	private static final String YIELD_COMMAND = "yield";
	private static final String EXIT_COMMAND = "exit";
	private static final String PRINT_COMMAND = "print";
	private static final Pattern KILL_COMMAND = Pattern.compile("kill [0-9]+");
	private static final Pattern UNBLOCK_COMMAND = Pattern.compile("unblock [0-9]+");
	private static final Pattern EXEC_COMMAND = Pattern.compile("execve .+ .+");
	
	private static Map<Integer,ProcessControlBlock> processTable = new HashMap<>();
	private static CPU cpu = new CPU();
	private static Scanner sc = new Scanner(System.in);
	private static Random rand = new Random();
	private static int runningProcessPid = DEFAULT_PID;
	
	public static void main(String[] args) {
		initializeDefaultProcess();
		startUserInput();
	}
	
	/*
	 * Adds the default process to the table upon instantiation
	 */
	private static void initializeDefaultProcess() {
		ProcessControlBlock pcb = new ProcessControlBlock(DEFAULT_PID,
				DEFAULT_PROGRAM_NAME, DEFAULT_USER, RUNNING,
				cpu.getRandomRegisterValues());
		// load cpu register values
		cpu.setRegisterValues(pcb.getRegisters());
		processTable.put(DEFAULT_PID, pcb);
	}
	
	/*
	 * Starts the user input to be able to enter commands
	 */
	private static void startUserInput() {
		while(true) {
			System.out.print("> ");
			String command = sc.nextLine();
			if(command.equals(FORK_COMMAND)) {
				fork();
			} else if(command.equals(BLOCK_COMMAND)) {
				block();
			} else if(command.equals(YIELD_COMMAND)) {
				yield();
			} else if(command.equals(EXIT_COMMAND)) {
				exit();
			} else if(command.equals(PRINT_COMMAND)) {
				print();
			} else if(command.matches(KILL_COMMAND.pattern())) {
				kill(splitCommandPid(command));
			} else if(command.matches(UNBLOCK_COMMAND.pattern())) {
				unblock(splitCommandPid(command));
			} else if(command.matches(EXEC_COMMAND.pattern())) {
				// index 1 = program name
				// index 2 = user name
				String[] splitCommand = command.split(" ");
				execCommand(splitCommand[1], splitCommand[2]);
			} else {
				System.out.println("Error: Bad Command\n");
			}
		}
	}
	
	/*
	 * helper method for startUserInput() method to split the pid
	 * from the commands KILL and UNBLOCK
	 */
	private static int splitCommandPid(String command) {
		String[] splitCommand = command.split(" ");
		return Integer.parseInt(splitCommand[1]);
	}
	
	/*
	 * make a copy of current running process and set the status to READY
	 */
	private static void fork() {
		// Get running process
		ProcessControlBlock runningProcess = processTable.get(runningProcessPid);
		if(runningProcess != null) {
			// create new process by copying the one that is running
			ProcessControlBlock newPcb = new ProcessControlBlock(runningProcess, determineUniquePid());
			processTable.put(newPcb.getPid(), newPcb);
		} else
			System.out.println("Error: No Running Process");
	}
	
	/*
	 * helper method for FORK
	 * determine unique pid by cycling through numbers 1 to number of processes
	 * if number is missing in sequence, use that number
	 * else the new pid will be 1 + number of processes
	 */
	private static int determineUniquePid() {
		List<Integer> keys = new ArrayList<>(processTable.keySet());
		Collections.sort(keys);
		for(int i = 0; i < keys.get(keys.size() - 1); i++)
			if(!keys.contains((i+1))) return i + 1;
		return keys.size() + 1;
	}
	
	/*
	 * move blocked process with pid to the READY state
	 */
	private static void unblock(int pid) {
		ProcessControlBlock blockedBlock = processTable.get(pid);
		if(blockedBlock != null) {
			// make sure that the pid they enter is actually blocked
			if(blockedBlock.getCurrentStatus() != BLOCKED) 
				System.out.println("Error: Process Block Is Not Blocked\n");
			else {
				// set status to ready and save back to table
				blockedBlock.setCurrentStatus(READY);
				processTable.put(blockedBlock.getPid(), blockedBlock);
			}
		} else {
			System.out.println("Error: Incorrect Process ID\n");
		}
	}
	
	/*
	 * put current running process to blocked state 
	 * and set new process to be running
	 */
	private static void block() {
		yieldAndBlockHelper(BLOCKED);
	}
	
	/*
	 * put current running process to ready state
	 * and set new process to be running
	 */
	private static void yield() {
		yieldAndBlockHelper(READY);
	}
	
	/*
	 * helper method for block and yield
	 */
	private static void yieldAndBlockHelper(int state) {
		ProcessControlBlock runningBlock = processTable.get(runningProcessPid);
		if(runningBlock != null) {
			// set new running block into cpu registers and table
			ProcessControlBlock newRunningBlock = processTable.get(determineNewRunningBlock());
			// set current running process to blocked
			runningBlock.setCurrentStatus(state);
			processTable.put(runningBlock.getPid(), runningBlock);
			if(newRunningBlock != null) {
				runningProcessPid = newRunningBlock.getPid();
				newRunningBlock.setCurrentStatus(RUNNING);
				cpu.setRegisterValues(newRunningBlock.getRegisters());
				processTable.put(newRunningBlock.getPid(), newRunningBlock);
			} else {
				runningProcessPid = -1;
				cpu.resetRegisters();
				System.out.println("Error: No Processes In Ready State");
			}
		} else {
			System.out.println("Error: No Running Process");
		}
	}
	
	/*
	 * kill process with specified pid if
	 * 1) current running process has user root
	 * 2) running process user is equal to killed process user
	 */
	private static void kill(int pid) {
		ProcessControlBlock killProcess = processTable.get(pid);
		if(killProcess != null) {
			ProcessControlBlock runningProcess = processTable.get(runningProcessPid);
			if(runningProcess != null) {
				if(runningProcess.getUsername().equals(DEFAULT_USER) || killProcess.getUsername().equals(runningProcess.getUsername()))
					processTable.remove(pid);
				else
					System.out.println("Error: Access Not Granted, Not Killing Process");
			} else
				System.out.println("Error: No Running Process");
		} else 
			System.out.println("Error: Incorrent Process ID");
	}
	
	/*
	 * swap the program name and user name for the current running process
	 * new register values should be set for cpu
	 */
	private static void execCommand(String programName, String username) {
		ProcessControlBlock runningProcess = processTable.get(runningProcessPid);
		if(runningProcess != null) {
			// set our new program name and user name
			runningProcess.setExecutingProgramName(programName);
			runningProcess.setUsername(username);
			// get new random register values and assign them to both
			// the running process registers and cpu registers
			runningProcess.setRegisters(cpu.getRandomRegisterValues());
			cpu.setRegisterValues(runningProcess.getRegisters());
		} else {
			System.out.println("Error: No Running Process");
		}
	}
	
	/*
	 * remove current running process completely
	 * select a random new ready process
	 */
	private static void exit() {
		// remove running process
		processTable.remove(runningProcessPid);
		if(processTable.size() != 0) {
			ProcessControlBlock newRunningBlock = processTable.get(determineNewRunningBlock());
			if(newRunningBlock != null) {
				runningProcessPid = newRunningBlock.getPid();
				newRunningBlock.setCurrentStatus(RUNNING);
				// load new running block registers to cpu
				cpu.setRegisterValues(newRunningBlock.getRegisters());
			} else {
				runningProcessPid = -1;
				cpu.resetRegisters();
				System.out.println("Error: No Processes In Ready State");
			}
		} else {
			cpu.resetRegisters();
			System.out.println("Error: All Processes Have Been Terminated");
		}
	}
	
	/*
	 * determine randomly what the new running block is
	 * if -1 is returned, there were no ready processes
	 */
	private static int determineNewRunningBlock() {	
		List<Integer> readyBlocks = new ArrayList<>();
		for(Map.Entry<Integer,ProcessControlBlock> entry : processTable.entrySet()) {
			if(entry.getValue().getCurrentStatus() == READY) 
				readyBlocks.add(entry.getKey());
		}
		if(readyBlocks.size() != 0)
			return readyBlocks.get(rand.nextInt(readyBlocks.size()));
		else
			return -1;
	}
	
	/*
	 * print out cpu and process table
	 */
	private static void print() {
		System.out.println("CPU:");
		System.out.println(cpu.toString());
		System.out.println("Process Table:");
		System.out.format(FORMAT, "PID", "Program", "User", "Status", "PC",
				"SP", "R0", "R1", "R2", "R3");
		for(Map.Entry<Integer,ProcessControlBlock> entry : processTable.entrySet()) {
			ProcessControlBlock block = entry.getValue();
			String[] reg = block.toHexStrings();
			System.out.format(FORMAT, block.getPid(), block.getExecutingProgramName(),
					block.getUsername(), block.getCurrentStatus(), reg[0], reg[1],
					reg[2], reg[3], reg[4], reg[5]);
		}
	}
}
