public class Habit {
    private String name;
    private boolean completedToday;

    public Habit(String name) {
        this.name = name;
        this.completedToday = false;
    }

    public String getName() {
        return name;
    }

    public boolean isCompletedToday() {
        return completedToday;
    }

    public void markCompletedToday() {
        this.completedToday = true;
    }

    public void resetForNewDay() {
        this.completedToday = false;
    }

    @Override
    public String toString() {
        return name + (completedToday ? " (done today)" : " (not done)");
    }
}
