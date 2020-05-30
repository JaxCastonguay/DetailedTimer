package io.github.jaccastonguay.detailedtimer;

public class Exercise {
    private String name;
    private int seconds;
    private int workoutid;

    public Exercise(String name, int seconds, int workoutid) {
        this.name = name;
        this.seconds = seconds;
        this.workoutid = workoutid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public int getWorkoutid() {
        return workoutid;
    }

    public void setWorkoutid(int workoutid) {
        this.workoutid = workoutid;
    }
}
