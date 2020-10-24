public class Process implements Comparable<Process>{
	private int processID;
	private int arrivalTime;
	private int burstTime;
	private int startingBurstTime;
	private int completionTime;
	private int startTime;
	private int turnAroundTime;
	private int waitTime;
	private int priority;
	private int queueTime;
	private static int firstArrival = 9999;
	private static int sumBurstTime;
	private boolean completionFlag = false;
	
	public Process(){}
	public Process(int processID, int arrivalTime, int burstTime){
		this.processID = processID;
		this.arrivalTime = arrivalTime;
		this.burstTime = burstTime;
		this.startingBurstTime = burstTime; 
	}
	
	public Process(int processID, int arrivalTime, int burstTime, int priority){
		this.processID = processID;
		this.arrivalTime = arrivalTime;
		this.burstTime = burstTime;
		this.priority = priority;
		this.startingBurstTime = burstTime; 
		sumBurstTime +=burstTime;
		if(arrivalTime < firstArrival)
			firstArrival = arrivalTime;
	}
	
	public int getArrivalTime(){
		return arrivalTime;
		
	}
	public int getQueueTime(){
		return queueTime;
		
	}
	public void setQueueTime(int queueTime){
		this.queueTime = queueTime;
	}
	public int getBurstTime(){
		return burstTime;
		
	}
	public int getStartingBurstTime(){
		return startingBurstTime;
		
	}
	public void setBurstTime(int burstTime){
		this.burstTime = burstTime;
	}
	
	public boolean getCompletionFlag(){
		return completionFlag;
		
	}
	public void setCompletionTime(int currentTime){
		this.completionTime = currentTime + burstTime;
		turnAroundTime = completionTime  - arrivalTime;
		waitTime = turnAroundTime - startingBurstTime;
		startTime = completionTime - burstTime;
	}
	
	public void setCompletionFlag(){
		completionFlag = true;
	}
	
	public int getTurnAroundTime(){
		return turnAroundTime;
		
	}
	
	public int getWaitTime(){
		return waitTime;
		
	}
	
	public int getStartTime(){
		return startTime;
	}
	
	public int getCompletionTime(){
		return completionTime;
	}
	
	public int getProcessID(){
		return processID;
	}
	public int getPriority(){
		return priority;
		
	}
	public static int getSumBurstTime(){
		return sumBurstTime;
	}
	public static int getFirstArrival(){
		return firstArrival;
	}
	@Override
	public String toString(){
		return "" +  processID;
	}
	
	public int compareTo(Process p){
		return processID - p.getProcessID();
	}
	
}