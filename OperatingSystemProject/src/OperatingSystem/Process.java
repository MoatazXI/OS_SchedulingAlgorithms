package OperatingSystem;
public class Process implements Comparable<Process> {
    private final int processNumber;
    private final int arrivalTime;
    private final int cpuTime;
    private final int priority;
    private String state;
    private int remaining_time;
    public Process(int processNumber, int arrivalTime, int cpuTime, int priority) {
        this.processNumber = processNumber;
        this.arrivalTime = arrivalTime;
        this.cpuTime = cpuTime;
        this.priority = priority;
        this.state = "ready";
        this.remaining_time = cpuTime;
    }

    public int getProcessNumber() {
        return this.processNumber;
    }

    public int getArrivalTime() {
        return this.arrivalTime;
    }
    public int getCpuTime() {
        return this.cpuTime;
    }
    public int getPriority() {
        return this.priority;
    }

    public String getState() {
        return this.state;
    }
    public void setState(String state) {
        this.state = state;
    }

    public int getRemainingTime() {
        return this.remaining_time;
    }
    public void setRemainingTime(int remaining_time) {
        this.remaining_time = remaining_time;
    }
    public int compareTo(Process that) {
        return Integer.compare(this.getArrivalTime(),that.getArrivalTime());
    }
}