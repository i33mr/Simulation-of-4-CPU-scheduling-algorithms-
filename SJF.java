import java.util.*;
import java.text.DecimalFormat;
public class SJF{
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
        System.out.print ("Enter number of processes: ");
        int numberOfProcesses = scan.nextInt();
		Process[] processes = new Process[numberOfProcesses];
		Process[] ganttOrder = new Process[numberOfProcesses];
		// int ganttOrder[] = new int[numberOfProcesses];
		
		
		for(int i = 0; i < numberOfProcesses; ++i){
            System.out.print ("Enter process " + (i+1) + " arrival time and burst time: ");
			processes[i] = new Process((i+1), scan.nextInt(), scan.nextInt());
	    } 
		
		
		int counter = 0;
		int currentTime = 0;
		// java.util.Arrays.sort(processes, new ProcessCompartorByArrivalTime());
		while(counter < numberOfProcesses){
			int currentProcess = numberOfProcesses;
			int minTime = 99999;
			
			for(int i = 0; i < numberOfProcesses; ++i){
				if((processes[i].getArrivalTime() <= currentTime) 
					&& (processes[i].getCompletionFlag() == false) 
					&& (processes[i].getBurstTime() < minTime)){
					minTime = processes[i].getBurstTime();
					currentProcess = i;
				}
			}
			
			if(currentProcess == numberOfProcesses) // no processes in the current time which means that the last loop was skipped
				currentTime ++;
			
			else{
				processes[currentProcess].setCompletionTime(currentTime);
				currentTime += processes[currentProcess].getBurstTime();
				processes[currentProcess].setCompletionFlag();
				ganttOrder[counter] = processes[currentProcess];
				++counter;
			
			}
			
		}
		
		
		
		
		
		// System.out.println(avgWT);
		// System.out.println(avgTAT);
		 
		
		displayQueue(processes, ganttOrder);
	}
	
	
	static void displayQueue(Process processes[], Process ganttOrder[]){
		System.out.println("\n\n\t\t\t\t\tNon-Preemptive Shortest Job First");
		for(int i = 0; i < 6; ++i){
			System.out.print("+-----------------");
		}
		System.out.print("+");
		System.out.println();
		System.out.print("|   Process ID.   ");
		System.out.print("|  Arrival Time   ");
		System.out.print("|   Burst Time    ");
		System.out.print("| Completion Time ");
		System.out.print("| Turnaround Time ");
		System.out.print("|   Waiting Time  |");
		System.out.println();
		for(int i = 0; i < 6; ++i){
			System.out.print("+-----------------");
		}
		System.out.print("+");
		System.out.println();
		DecimalFormat xFormat = new DecimalFormat("00");
		for(int i = 0; i < processes.length; ++i){
			System.out.println("|      P" + processes[i].getProcessID() + "         |        " + xFormat.format(processes[i].getArrivalTime()) + "       |        " + xFormat.format(processes[i].getBurstTime())+ "       |       " + xFormat.format(processes[i].getCompletionTime()) +"        |       " + xFormat.format(processes[i].getTurnAroundTime()) + "        |        " +xFormat.format(processes[i].getWaitTime()) + "       |");

		}
		for(int i = 0; i < 6; ++i){
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
		// avgTAT /= numberOfProcesses;
		// avgWT /= numberOfProcesses;
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
	
		
		for(int i = 0; i < processes.length; ++i){
			System.out.print("+-----------");
		}
		System.out.print("+\n|");
		for(int i = 0; i < ganttOrder.length; ++i)
			System.out.print("    P" + ganttOrder[i].getProcessID() + "     |"  );
		
		System.out.println();
		for(int i = 0; i < processes.length; ++i){
			System.out.print("+-----------");
		}
		System.out.print("+\n");
		System.out.print(xFormat.format(ganttOrder[0].getStartTime()));
		for(int i = 0; i < ganttOrder.length; ++i)
			System.out.print("          " + xFormat.format(ganttOrder[i].getCompletionTime()));
		
		System.out.println();
		System.out.println();
		
		
	}
}