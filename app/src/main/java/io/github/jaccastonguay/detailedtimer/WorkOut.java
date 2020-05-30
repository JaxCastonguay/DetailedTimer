package io.github.jaccastonguay.detailedtimer;

public class WorkOut {
    private String name;
    private int workoutid;
    private String time;



    public WorkOut(String name, String time, int workoutid) {
        this.name = name;
        this.time = time;
        this.workoutid = workoutid;
    }
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWorkoutid() {
        return workoutid;
    }
}
