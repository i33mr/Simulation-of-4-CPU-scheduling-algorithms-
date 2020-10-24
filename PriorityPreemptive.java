import java.util.*;
import java.text.DecimalFormat;
public class PriorityPreemptive{
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
        System.out.print ("Enter number of processes: ");
        int numberOfProcesses = scan.nextInt();
		Process[] processes = new Process[numberOfProcesses];
		// Process[] ganttOrder = new Process[numberOfProcesses];
		// int ganttOrder[] = new int[numberOfProcesses];
		ArrayList<Process> order = new ArrayList<>();
		ArrayList<Process> ganttOrder = new ArrayList<>();
		ArrayList<Integer> inOutTimes = new ArrayList<>();
		for(int i = 0; i < numberOfProcesses; ++i){
            System.out.print ("Enter process " + (i+1) + " arrival time, burst time and priority: ");
			processes[i] = new Process((i+1), scan.nextInt(), scan.nextInt(), scan.nextInt());
	    } 
		scan.close();
		Process previousProcess = new Process();
		int currentTime = Process.getFirstArrival();
		java.util.Arrays.sort(processes, new ProcessCompartorByArrivalTime());
		while(currentTime < (Process.getSumBurstTime() + Process.getFirstArrival() + 1)){
			Process currentProcess = new Process();
			int priority = 9999;
			for(int i = 0; i < numberOfProcesses; ++i){
				if((processes[i].getArrivalTime() <= currentTime)  
					&& (processes[i].getPriority() < priority)
					&& processes[i].getBurstTime() > 0){
						
					priority = processes[i].getPriority();
					currentProcess = processes[i];
				}
			}
			
			if(!previousProcess.equals(currentProcess)){
				inOutTimes.add(currentTime);
				if(previousProcess.getBurstTime() != 0){
					previousProcess.setQueueTime(currentTime);
					java.util.Arrays.sort(processes, new ProcessCompartorSetQueue());
				}
				// if(currentProcess < numberOfProcesses && previousProcess != 9999 && processes[previousProcess].getProcessID() != lastId)
				// processes[previousProcess].setArrivalTime(currentTime);
			}
			if(currentTime == (Process.getSumBurstTime() + Process.getFirstArrival())){ // no processes in the current time which means that the last loop was skipped
				order.add(new Process(100,100,100)); // flag to indicate that the no more processes after this 
				++currentTime;
			}
			else{
				currentProcess.setBurstTime((currentProcess.getBurstTime() - 1));
				++currentTime;
				order.add(currentProcess);
				previousProcess = currentProcess;
				if(currentProcess.getBurstTime() == 0)
					currentProcess.setCompletionTime(currentTime);
			}
		}
		for(int i = 0; i < Process.getSumBurstTime(); ++i){
			if(!order.get(i+1).equals(order.get(i)))
				ganttOrder.add(order.get(i));
		}
		displayQueue(processes, ganttOrder, inOutTimes);
	}


	static void displayQueue(Process processes[], ArrayList<Process> ganttOrder, ArrayList<Integer> inOutTimes){
		java.util.Arrays.sort(processes);
		System.out.println("\n\n\t\t\t\t\tPreemptive Priority");
		for(int i = 0; i < 7; ++i){
			System.out.print("+-----------------");
		}
		System.out.print("+");
		System.out.println();
		System.out.print("|   Process ID.   ");
		System.out.print("|  Arrival Time   ");
		System.out.print("|   Burst Time    ");
		System.out.print("|    Priority     ");
		System.out.print("| Completion Time ");
		System.out.print("| Turnaround Time ");
		System.out.print("|   Waiting Time  |");
		System.out.println();
		for(int i = 0; i < 7; ++i){
			System.out.print("+-----------------");
		}
		System.out.print("+");
		System.out.println();
		DecimalFormat xFormat = new DecimalFormat("00");
		for(int i = 0; i < processes.length; ++i){
			System.out.println("|      P" + processes[i].getProcessID() + "         |        " + xFormat.format(processes[i].getArrivalTime()) + "       |        " + xFormat.format(processes[i].getStartingBurstTime())+ "       |       " + xFormat.format(processes[i].getPriority())+ "        |       " + xFormat.format(processes[i].getCompletionTime()) +"        |       " + xFormat.format(processes[i].getTurnAroundTime()) + "        |        " +xFormat.format(processes[i].getWaitTime()) + "       |");

		}
		for(int i = 0; i < 7; ++i){
			System.out.print("+-----------------");
		}
		System.out.print("+");
		System.out.println();
		System.out.println();
		
		double avgTAT = 0;
		double avgWT = 0;
		double avgCT = 0;
		for(int i=0; i < processes.length; i++)
        {
			
            avgTAT += processes[i].getTurnAroundTime();
            avgWT += processes[i].getWaitTime();
            avgCT += processes[i].getCompletionTime();
			
        }
		System.out.println("Total completion time : " + avgCT);
		System.out.println("Average completion time : " + (int)(avgCT / processes.length * 100) / 100.0);
		
		System.out.println();
		System.out.println();
		
		System.out.println("Total turnaround time : " + avgTAT);
		System.out.println("Average turnaround time : " + (int)(avgTAT / processes.length * 100) / 100.0);
		
		System.out.println();
		System.out.println();
		
		System.out.println("Total waiting time : " + avgWT);
		System.out.println("Average waiting time : " + (int)(avgWT / processes.length * 100) / 100.0);
	
		
		for(int i = 0; i < ganttOrder.size(); ++i){
			System.out.print("+-----------");
		}
		System.out.print("+\n|");
		for(int i = 0; i < ganttOrder.size(); ++i)
			System.out.print("    P" + ganttOrder.get(i).getProcessID() + "     |"  );
		
		System.out.println();
		for(int i = 0; i < ganttOrder.size(); ++i){
			System.out.print("+-----------");
		}
		System.out.print("+\n");
		for(int i = 0; i < inOutTimes.size(); ++i)
			System.out.print(xFormat.format(inOutTimes.get(i))+ "          ");
		
		System.out.println();
		System.out.println();
		
		
	}
}