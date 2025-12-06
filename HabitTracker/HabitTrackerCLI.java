import java.util.List;
import java.util.Scanner;

public class HabitTrackerCLI {
    private HabitManager manager;
    private Scanner scanner;
    private boolean running;

    public HabitTrackerCLI(HabitManager manager) {
        this.manager = manager;
        this.scanner = new Scanner(System.in);
        this.running = true;
    }

    public void run() {
        System.out.println("Welcome to Simple Habit Tracker!");
        while (running) {
            manager.checkNewDayAndReset();
            showMainMenu();
            int choice = readIntWithValidation("Enter your choice: ", 1, 4);
            switch (choice) {
                case 1 -> handleAddHabit();
                case 2 -> handleMarkHabitDone();
                case 3 -> handleViewStatus();
                case 4 -> handleExit();
            }
        }
    }

    private void showMainMenu() {
        System.out.println("\nMain Menu");
        System.out.println("1) Add Habit");
        System.out.println("2) Mark Habit as Done");
        System.out.println("3) View Habit Status");
        System.out.println("4) Exit");
    }

    private void handleAddHabit() {
        String name = readNonEmptyLine("Enter habit name: ");
        manager.addHabit(name);
        System.out.println("Habit added: " + name);
    }

    private void handleMarkHabitDone() {
        List<Habit> habits = manager.getHabits();
        if (habits.isEmpty()) {
            System.out.println("No habits yet. Please add a habit first.");
            return;
        }
        System.out.println("Your habits:");
        for (int i = 0; i < habits.size(); i++) {
            System.out.println((i + 1) + ") " + habits.get(i).getName());
        }
        int index = readIntWithValidation("Select habit index: ", 1, habits.size());
        boolean ok = manager.markHabitDone(index - 1);
        if (ok) {
            System.out.println("Habit marked as done for today.");
        } else {
            System.out.println("Invalid index.");
        }
    }

    private void handleViewStatus() {
        List<String> lines = manager.getStatusLines();
        if (lines.isEmpty()) {
            System.out.println("No habits yet.");
        } else {
            System.out.println("Habit status for today:");
            for (String line : lines) {
                System.out.println(line);
            }
        }
    }

    private void handleExit() {
        System.out.println("Goodbye!");
        running = false;
    }

    private int readIntWithValidation(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine();
            try {
                int value = Integer.parseInt(line.trim());
                if (value < min || value > max) {
                    System.out.println("Please enter a number between " + min + " and " + max + ".");
                } else {
                    return value;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid number, please try again.");
            }
        }
    }

    private String readNonEmptyLine(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine();
            if (!line.trim().isEmpty()) {
                return line.trim();
            }
            System.out.println("Input cannot be empty.");
        }
    }
}
