import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HabitManager {
    private List<Habit> habits;
    private LocalDate currentDate;

    public HabitManager() {
        this.habits = new ArrayList<>();
        this.currentDate = LocalDate.now();
    }

    public void checkNewDayAndReset() {
        LocalDate today = LocalDate.now();
        if (!today.equals(currentDate)) {
            for (Habit h : habits) {
                h.resetForNewDay();
            }
            currentDate = today;
        }
    }

    public void addHabit(String name) {
        habits.add(new Habit(name));
    }

    public List<Habit> getHabits() {
        return habits;
    }

    public boolean markHabitDone(int index) {
        if (index < 0 || index >= habits.size()) {
            return false;
        }
        Habit h = habits.get(index);
        h.markCompletedToday();
        return true;
    }

    public List<String> getStatusLines() {
        List<String> lines = new ArrayList<>();
        for (int i = 0; i < habits.size(); i++) {
            Habit h = habits.get(i);
            lines.add((i + 1) + ". " + h.toString());
        }
        return lines;
    }
}
