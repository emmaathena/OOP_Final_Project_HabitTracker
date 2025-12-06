public class Main {
    public static void main(String[] args) {
        HabitManager manager = new HabitManager();
        HabitTrackerCLI app = new HabitTrackerCLI(manager);
        app.run();
    }
}