import OperatingSystem.Scheduler;
import OperatingSystem.Process;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Scheduler scheduler = new Scheduler();
        System.out.println("Enter the number of processes: ");


        int numberOfProcesses = input.nextInt();
        List<Process> processes = new ArrayList<>(numberOfProcesses);

        for (int i = 0; i < numberOfProcesses; i++) {
            System.out.println("Enter the arrival time of process " + (i + 1) + ": ");
            int arrivalTime = input.nextInt();
            System.out.println("Enter the CPU time of process " + (i + 1) + ": ");
            int cpuTime = input.nextInt();
            System.out.println("Enter the priority of process " + (i + 1) + ": ");
            int priority = input.nextInt();
            processes.add(new Process(i + 1, arrivalTime, cpuTime, priority));
        }

        System.out.println("Choose the algorithm you want to run: ");
        System.out.println("1. FCFS");
        System.out.println("2. SJF");
        System.out.println("3. RR");
        System.out.println("4. Non-Preemptive Priority");
        System.out.println("5. Preemptive Priority");
        System.out.println("6. All");

        int choice = input.nextInt();
        switch (choice) {
            case 1:
                scheduler.runFCFS(processes);
                break;
            case 2:
                scheduler.runSJF(processes);
                break;
            case 3:
                System.out.println("Enter the quantum time: ");
                int quantumTime = input.nextInt();
                scheduler.runRR(processes,quantumTime);
                break;
            case 4:
                scheduler.runPriorityNonPreemptive(processes);
                break;
            case 5:
                scheduler.runPreemptivePriority(processes);
                break;
            case 6:
                System.out.println("Enter the quantum time: ");
                int quantum_Time = input.nextInt();
                scheduler.runFCFS(processes);
                scheduler.runSJF(processes);
                scheduler.runRR(processes,quantum_Time);
                scheduler.runPriorityNonPreemptive(processes);
                scheduler.runPreemptivePriority(processes);
                break;
            default:
                System.out.println("Invalid choice!");
        }

//        List<Process> processes = new ArrayList<>();
        // Test Case from YouTube Video FCFS
//        processes.add(new Process(1,2,2,0));
//        processes.add(new Process(2,0,1,0));
//        processes.add(new Process(3,2,3,0));
//        processes.add(new Process(4,3,5,0));
//        processes.add(new Process(5,4,4,0));
//        scheduler.runFCFS(processes);

        //Test Case from YouTube Video SJF
//        processes.add(new Process(1,2,1,0));
//        processes.add(new Process(2,1,5,0));
//        processes.add(new Process(3,4,1,0));
//        processes.add(new Process(4,0,6,0));
//        processes.add(new Process(5,2,3,0));
//        scheduler.runSJF(processes);

        // Test Case from YouTube Video RR
//        processes.add(new Process(1,0,8,0));
//        processes.add(new Process(2,5,2,0));
//        processes.add(new Process(3,1,7,0));
//        processes.add(new Process(4,6,3,0));
//        processes.add(new Process(5,8,5,0));
//        scheduler.runRR(processes,3);

        // Test Case from YouTube Video Non-Preemtive Priority
//        processes.add(new Process(1,0,8,3));
//        processes.add(new Process(2,1,2,4));
//        processes.add(new Process(3,3,4,4));
//        processes.add(new Process(4,4,1,5));
//        processes.add(new Process(5,5,6,2));
//        processes.add(new Process(6,6,5,6));
//        processes.add(new Process(7,10,1,1));
//        scheduler.runPriorityNonPreemptive(processes);

        // Test Case from YouTube Video Preemtive Priority
//        processes.add(new Process(1,0,8,3));
//        processes.add(new Process(2,1,2,4));
//        processes.add(new Process(3,3,4,4));
//        processes.add(new Process(4,4,1,5));
//        processes.add(new Process(5,5,6,2));
//        processes.add(new Process(6,6,5,6));
//        processes.add(new Process(7,10,1,1));
//        scheduler.runPreemptivePriority(processes);


//======================================================================================================================
//======================================================================================================================


        //Test Case from Lecture 11
//        processes.add(new Process(1,0,8,0));
//        processes.add(new Process(2,1,4,0));
//        processes.add(new Process(3,2,9,0));
//        processes.add(new Process(4,3,5,0));
//        scheduler.runFCFS(processes);


        //Test Case from Lecture 11
//        processes.add(new Process(1,0,24,0));
//        processes.add(new Process(2,0,3,0));
//        processes.add(new Process(3,0,3,0));
//        scheduler.runRR(processes,4);

//        Test Case from Lecture 12
//        processes.add(new Process(1,0,53,0));
//        processes.add(new Process(2,0,17,0));
//        processes.add(new Process(3,0,68,0));
//        processes.add(new Process(4,0,24,0));
//        scheduler.runRR(processes,20);

//        Test Case from Lecture 12
//        processes.add(new Process(1,0,8,0));
//        processes.add(new Process(2,1,4,0));
//        processes.add(new Process(3,2,9,0));
//        processes.add(new Process(4,3,5,0));
//        scheduler.runRR(processes,4);


//        Test Case from Lecture 12
//        processes.add(new Process(1,0,10,3));
//        processes.add(new Process(2,0,1,1));
//        processes.add(new Process(3,0,2,4));
//        processes.add(new Process(4,0,1,5));
//        processes.add(new Process(5,0,5,2));
//        scheduler.runPriorityNonPreemptive(processes);

        // Test Case from Lecture 12
//        processes.add(new Process(1,0,3,5));
//        processes.add(new Process(2,1,7,3));
//        processes.add(new Process(3,4,2,4));
//        processes.add(new Process(4,2,3,3));
//        processes.add(new Process(5,6,4,1));
//        scheduler.runPriorityNonPreemptive(processes);

        // Test Case from Lecture 12
//        processes.add(new Process(1,0,3,5));
//        processes.add(new Process(2,1,7,3));
//        processes.add(new Process(3,4,2,4));
//        processes.add(new Process(4,2,3,3));
//        processes.add(new Process(5,6,4,1));
//        scheduler.runPreemptivePriority(processes);
    }
}
