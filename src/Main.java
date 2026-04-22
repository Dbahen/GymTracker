import javax.xml.transform.Source;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        WorkoutService workoutService = new WorkoutService();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            printMenu();

            int option = scanner.nextInt();
            scanner.nextLine();

            handleOption(option, scanner, workoutService);
        }
    }



    public static void addWorkout(Scanner scanner, WorkoutService workoutService) {
        List<SetEntry> sets = new ArrayList<>();

        System.out.println("Which exercise will you do? : ");
        String exerciseElection = scanner.nextLine();
        System.out.println("Which muscle group will you workout? : ");
        String muscleGroup = scanner.nextLine();

        //Exercise selectedExercise = new Exercise(exerciseElection, muscleGroup);


        System.out.println("Gimme a date : ");
        String exerciseDate = scanner.nextLine();

        //Workout newWorkout = new Workout(exerciseDate, selectedExercise);

        System.out.println("How many sets? : ");
        int numberOfSets = scanner.nextInt();
        scanner.nextLine();

        for (int i = 1; i <= numberOfSets; i++) {
            System.out.println("Set " + i + " How many reps? : ");
            int reps = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Set " + i + " What weight? : ");
            int weight = scanner.nextInt();
            scanner.nextLine();

            sets.add(new SetEntry(reps, weight));
            //newWorkout.addSet(set);
        }

        //workoutService.addWorkout(newWorkout);
        workoutService.createWorkout(
                exerciseElection,
                muscleGroup,
                exerciseDate,
                sets
        );
        System.out.println("Workout Added!");
    }

    public static void viewWorkouts(List<Workout> workouts) {
        for (Workout w : workouts){
            System.out.println("ID: " + w.getId());
            System.out.println("Exercise: " + w.getExercise().getName());
            System.out.println("Date: " + w.getDate());

            List<SetEntry> sets = w.getSets();

            for (int i = 0; i < sets.size(); i++) {
                SetEntry set = sets.get(i);

                System.out.println("Set " +
                        (i + 1) + ": " + set.getReps() + " reps - " + set.getWeight() + "kg");
            }

            System.out.println();
        }
    }

    public static void searchWorkout(Scanner scanner, WorkoutService workoutService) {
        System.out.println("Enter exercise name to search: ");
        String name = scanner.nextLine();

        List<Workout> results = workoutService.findWorkoutsByExercise(name);

        if (results.isEmpty()) {
            System.out.println("No workouts found.");
        } else {
            viewWorkouts(results);
        }
    }

    public static void updateWorkout(Scanner scanner, WorkoutService workoutService) {
        System.out.println("Enter workout ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter new date: ");
        String newDate = scanner.nextLine();

        boolean updated = workoutService.updateWorkoutDate(id,newDate);

        if (updated) {
            System.out.println("Workout updated!");
        } else {
            System.out.println("Workout not found.");
        }
    }

    public static void updateSet(Scanner scanner, WorkoutService workoutService) {
        System.out.println("Enter workout ID: ");
        int workoutId = scanner.nextInt();

        System.out.println("Which set number?");
        int setNumber = scanner.nextInt();

        System.out.println("New reps: ");
        int reps = scanner.nextInt();

        System.out.println("New weight: ");
        int weight = scanner.nextInt();
        scanner.nextLine();

        boolean updated = workoutService.updateSet(
                workoutId,
                setNumber - 1,
                reps,
                weight
        );
        if (updated) {
            System.out.println("Set updated!");
        } else {
            System.out.println("Workout or set not found.");
        }
    }


    public static void deleteWorkout(Scanner scanner, WorkoutService workoutService) {
        System.out.println("Enter exercise name to delete: ");
        String name = scanner.nextLine();

        boolean deleted = workoutService.deleteWorkoutByName(name);

        if (deleted) {
            System.out.println("Workout deleted.");
        } else {
            System.out.println("Workout not found.");
        }
    }

    public static void printMenu() {
        System.out.println("\n==== Gym Tracker ====");
        System.out.println("1. Add Workout");
        System.out.println("2. View Workouts");
        System.out.println("3. Search Workout");
        System.out.println("4. Delete Workout");
        System.out.println("5. Update Workout Date");
        System.out.println("6. Update Set");
        System.out.println("7. Exit");
        System.out.println("Choose option: ");
    }

    public static void handleOption(int option, Scanner scanner, WorkoutService workoutService) {
        switch (option) {
            case 1:
                addWorkout(scanner, workoutService);
                break;
            case 2:
                viewWorkouts(workoutService.getWorkouts());
                break;
            case 3:
                searchWorkout(scanner, workoutService);
                break;
            case 4:
                deleteWorkout(scanner, workoutService);
                break;
            case 5:
                updateWorkout(scanner, workoutService);
                break;
            case 6:
                updateSet(scanner, workoutService);
                break;
            case 7:
                System.out.println("Goodbye!");
                System.exit(0);
            default:
                System.out.println("Invalid Option.");
        }
    }
}