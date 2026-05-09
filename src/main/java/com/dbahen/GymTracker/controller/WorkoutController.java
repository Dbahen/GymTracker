package com.dbahen.GymTracker.controller;

import jakarta.validation.Valid;
import com.dbahen.GymTracker.model.Workout;
import com.dbahen.GymTracker.service.WorkoutService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import javax.smartcardio.ResponseAPDU;
import java.util.List;

@RestController
@RequestMapping("/workouts")
public class WorkoutController {
    private final WorkoutService workoutService;

    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    @GetMapping
    public List<Workout> getAllWorkouts() {
        return workoutService.getWorkouts();
    }

    @PostMapping
    public Workout createWorkout(@Valid @RequestBody Workout workout) {
        return workoutService.addWorkout(workout);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteWorkout(@PathVariable Long id) {
        boolean deleted = workoutService.deleteWorkoutById(id);

        if (deleted) {
            return ResponseEntity.ok("Workout deleted");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateWorkout(@PathVariable Long id, @RequestBody Workout workout) {
        boolean updated = workoutService.updateWorkout(id, workout);

        if (updated) {
            return ResponseEntity.ok("Workout updated");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
