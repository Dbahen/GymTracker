import java.util.ArrayList;
import java.util.List;

public class WorkoutService {
    private List<Workout> workouts = new ArrayList<>();

    public List<Workout> findWorkoutsByExercise(String name) {
        List<Workout> result = new ArrayList<>();
        for (Workout w: workouts) {
            if (w.getExercise().getName().equalsIgnoreCase(name)) {
                result.add(w);
            }
        }
        return result;
    }

    public void addWorkout(Workout workout) {
        workouts.add(workout);
    }

    public List<Workout> getWorkouts() {
        return workouts;
    }

    public boolean deleteWorkoutByName(String name) {
        for (int i = 0; i < workouts.size(); i++){
            Workout w = workouts.get(i);

            if (w.getExercise().getName().equalsIgnoreCase(name)) {
                workouts.remove(i);
                return true;
            }
        }
        return false;
    }

    public void createWorkout(
            String exerciseName,
            String muscleGroup,
            String date,
            List<SetEntry> sets
    ) {
        Exercise exercise = new Exercise(exerciseName, muscleGroup);
        Workout workout = new Workout(date, exercise);

        for (SetEntry set : sets) {
            workout.addSet(set);
        }

        workouts.add(workout);
    }

    public boolean updateWorkoutDate(int id, String newDate) {
        for (Workout w : workouts) {
            if(w.getId() == id) {
                w.setDate(newDate);
                return true;
            }
        }
        return false;
    }


    public boolean updateSet(int workoutId, int setIndex, int newReps, int newWeight) {
        for (Workout w : workouts) {
            if (w.getId() == workoutId) {
                List<SetEntry> sets = w.getSets();

                if (setIndex >= 0 && setIndex < sets.size()) {
                    SetEntry set = sets.get(setIndex);

                    set.setReps(newReps);
                    set.setReps(newWeight);

                    return true;
                }
            }
        }
        return false;
    }
}
