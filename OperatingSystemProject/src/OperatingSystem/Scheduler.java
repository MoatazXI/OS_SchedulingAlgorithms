package OperatingSystem;

import java.util.*;

public class Scheduler {
    // Implement the non-preemptive FCFS algorithm
    public void runFCFS(List<Process> processes) {
        System.out.println("--------------------------------------------------------FCFS Starts---------------------------------------------------------");
        runNonPreemptiveAlgorithm(processes,null);
        System.out.println("--------------------------------------------------------FCFS Finished---------------------------------------------------------");

    }

    // Implement non-preemptive SJF algorithm
    public void runSJF(List<Process> processes) {
        System.out.println("--------------------------------------------------------SJF Starts---------------------------------------------------------");
        runNonPreemptiveAlgorithm(processes,Comparator.comparingInt(Process::getCpuTime));
        System.out.println("--------------------------------------------------------SJF Finished---------------------------------------------------------");

    }

    // Implement the non-preemptive priority algorithm
    public void runPriorityNonPreemptive(List<Process> processes) {
        System.out.println("--------------------------------------------------------Priority Non Preemptive Starts---------------------------------------------------------");
        runNonPreemptiveAlgorithm(processes,Comparator.comparingInt(Process::getPriority));
        System.out.println("--------------------------------------------------------Priority Non Preemptive Finished---------------------------------------------------------");

    }

    private void runNonPreemptiveAlgorithm(List<Process> processes,Comparator<Process> readyQueueComparator) {

        // Taking copy of the processes and sort it by arrival time
        List<Process> processes_copy = new ArrayList<>(processes);

        Collections.sort(processes_copy);

        PriorityQueue<Process> readyQueue = new PriorityQueue<>(readyQueueComparator);

        // Calculate the average waiting time and turnaround time
        double averageWaitingTime = 0;
        double averageTurnAroundTime = 0;

        // Variable current_time keep track of time and start from 0
        int current_time = 0;

        //Index keep track of index we access from processes_copy ArrayList
        int index = 0;

        int numberOfProcesses = processes.size();

        while (index < numberOfProcesses || !readyQueue.isEmpty()) {
            // Add all processes that have arrived to the ready queue
            while (index < numberOfProcesses && processes_copy.get(index).getArrivalTime() <= current_time) {
                readyQueue.add(processes_copy.get(index));
                index++;
            }

            // If no processes have arrived yet, increment the time and continue
            if (readyQueue.isEmpty()) {
                current_time++;
                //current_time = processes_copy.get(index).getArrivalTime();
                continue;
            }

            Process current_process = readyQueue.poll();

            current_process.setState("running");
            System.out.println("Process Number " + current_process.getProcessNumber() + " is " + current_process.getState());

            System.out.println("start execution Time: " + current_time);

            current_time += current_process.getCpuTime();
            System.out.println("Completion Time (CT): " + current_time);

            current_process.setState("terminated");
            System.out.println("Process Number " + current_process.getProcessNumber() + " is " + current_process.getState());

            int turnAroundTime = current_time - current_process.getArrivalTime();
            System.out.println("Turn Around Time: " + turnAroundTime);
            averageTurnAroundTime += turnAroundTime;

            int waitingTime = turnAroundTime - current_process.getCpuTime();
            System.out.println("Waiting Time: " + waitingTime);
            averageWaitingTime += waitingTime;

            System.out.println("------------------------------------------------------------------------------------------------------------------------------");
        }
        averageWaitingTime /= numberOfProcesses;
        averageTurnAroundTime /= numberOfProcesses;

        System.out.println("Average waiting time " + averageWaitingTime);
        System.out.println("Average turnaround time " + averageTurnAroundTime);
    }

    // Implement the RR algorithm
    public void runRR(List<Process> processes,int timeQuantum) {
        System.out.println("--------------------------------------------------------Round Robin Starts---------------------------------------------------------");

        // Taking copy of the processes and sort it by arrival time
        List<Process> processes_copy = new ArrayList<>(processes);
        Collections.sort(processes_copy);

        Queue<Process> readyQueue = new LinkedList<>();

        // Calculate the average waiting time and turnaround time
        double averageWaitingTime = 0;
        double averageTurnAroundTime = 0;

        int current_time = 0;
        int index = 0;
        int numberOfProcesses = processes.size();

        while (index < numberOfProcesses || !readyQueue.isEmpty()) {
            // Add all processes that have arrived to the ready queue
            while (index < numberOfProcesses && processes_copy.get(index).getArrivalTime() <= current_time) {
                readyQueue.add(processes_copy.get(index));
                index++;
            }

            // If no processes have arrived yet, increment the time and continue
            if (readyQueue.isEmpty()) {
                //current_time++;
                current_time = processes_copy.get(index).getArrivalTime();
                continue;
            }

            Process process = readyQueue.poll();
            process.setState("running");
            System.out.println("Process Number " + process.getProcessNumber() + " is " + process.getState());
            System.out.println("start execution Time: " + current_time);

            // If the process has burst time greater than the time quantum, subtract the time quantum from the burst time and add the process back to the queue
            if (process.getRemainingTime() > timeQuantum) {
                current_time += timeQuantum;
                process.setRemainingTime(process.getRemainingTime() - timeQuantum);

                // Add all processes that have arrived to the ready queue
                while (index < numberOfProcesses && processes_copy.get(index).getArrivalTime() <= current_time) {
                    readyQueue.add(processes_copy.get(index));
                    index++;
                }

                readyQueue.add(process);
                process.setState("ready");
                System.out.println("Quantum expired: " + current_time + " Process Number "
                        + process.getProcessNumber() + " is preempted");

            } else {
                // If the process has burst time less than or equal to the time quantum, add the burst time to the time
                current_time += process.getRemainingTime();
                System.out.println("Completion Time: " + current_time);

                process.setState("terminated");
                System.out.println("Process Number " + process.getProcessNumber() + " is " + process.getState());

                int turnAroundTime = current_time - process.getArrivalTime();
                System.out.println("Turn Around Time: " + turnAroundTime);

                averageTurnAroundTime += turnAroundTime;

                int waitingTime = turnAroundTime - process.getCpuTime();
                System.out.println("Waiting Time: " + waitingTime);

                averageWaitingTime += waitingTime;
            }
            System.out.println("------------------------------------------------------------------------------------------------------------------------------");

        }
        averageWaitingTime /= numberOfProcesses;
        averageTurnAroundTime /= numberOfProcesses;

        System.out.println("Average waiting time " + averageWaitingTime);
        System.out.println("Average turnaround time " + averageTurnAroundTime);
        System.out.println("--------------------------------------------------------Round Robin Finished---------------------------------------------------------");
    }

    // Implement the preemptive priority algorithm
    public void runPreemptivePriority(List<Process> processes) {
        System.out.println("--------------------------------------------------------Preemptive Priority Starts---------------------------------------------------------");

        // Taking copy of the processes and sort it by arrival time
        List<Process> processes_copy = new ArrayList<>(processes);
        Collections.sort(processes_copy);

        PriorityQueue<Process> readyQueue = new PriorityQueue<>(Comparator.comparing(Process::getPriority).thenComparingInt(Process::getArrivalTime));

        // Calculate the average waiting time and turnaround time
        double averageWaitingTime = 0;
        double averageTurnAroundTime = 0;

        int current_time = 0;
        int index = 0;
        int numberOfProcesses = processes.size();
        Process current_process = null;

        while (index < numberOfProcesses || !readyQueue.isEmpty() || current_process != null) {

            // Add all processes that have arrived to the ready queue
            while (index < numberOfProcesses && processes_copy.get(index).getArrivalTime() <= current_time) {
                readyQueue.add(processes_copy.get(index));
                index++;
            }

            // If no processes have arrived yet, increment the time and continue
            if (readyQueue.isEmpty() && current_process == null) {
                current_time++;
                //current_time = processes_copy.get(index).getArrivalTime();
                continue;
            }

            // If there is a running process, check if it should be preempted
            if (current_process != null && !readyQueue.isEmpty() && current_process.getPriority() > readyQueue.peek().getPriority()) {
                current_process.setState("ready");

                System.out.println("Time: " + current_time + " Process Number " + current_process.getProcessNumber() + " is preempted");
                System.out.println("------------------------------------------------------------------------------------------------------------------------------");

                readyQueue.add(current_process);
                current_process = null;
            }

            // If there is no running process, take one from the ready queue
            if (current_process == null && !readyQueue.isEmpty()) {
                current_process = readyQueue.poll();
                current_process.setState("running");
                System.out.println("Process Number " + current_process.getProcessNumber() + " is " + current_process.getState());
                System.out.println("start execution Time: " + current_time);
            }

            // Run the current process for one time unit
            current_time++;
            current_process.setRemainingTime(current_process.getRemainingTime() - 1);

            // If the current process has finished, calculate its waiting time and turnaround time
            if (current_process.getRemainingTime() == 0) {
                System.out.println("Completion Time: " + current_time);

                current_process.setState("terminated");
                System.out.println("Process Number " + current_process.getProcessNumber() + " is " + current_process.getState());

                int turnAroundTime = current_time - current_process.getArrivalTime();
                System.out.println("Turn Around Time: " + turnAroundTime);

                averageTurnAroundTime += turnAroundTime;

                int waitingTime = turnAroundTime - current_process.getCpuTime();
                System.out.println("Waiting Time: " + waitingTime);

                averageWaitingTime += waitingTime;

                System.out.println("------------------------------------------------------------------------------------------------------------------------------");

                current_process = null;
            }
        }
        averageWaitingTime /= numberOfProcesses;
        averageTurnAroundTime /= numberOfProcesses;

        System.out.println("Average waiting time " + averageWaitingTime);
        System.out.println("Average turnaround time " + averageTurnAroundTime);
        System.out.println("--------------------------------------------------------Preemptive Priority Finished---------------------------------------------------------");
    }
}
