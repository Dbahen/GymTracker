import java.util.ArrayList;
import java.util.List;

public class Workout {
    private List<SetEntry> sets;
    private static int counter = 1;
    private int id;
    private String date;
    private Exercise exercise;

    public Workout(String date, Exercise exercise) {
        this.id = counter++;
        this.date = date;
        this.exercise = exercise;
        this.sets = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public List<SetEntry> getSets() {
        return sets;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void addSet(SetEntry set){
        sets.add(set);
    }

    public void setDate(String date) {
        this.date = date;
    }
}
