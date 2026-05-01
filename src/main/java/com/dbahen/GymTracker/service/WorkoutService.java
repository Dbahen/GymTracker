package com.dbahen.GymTracker.service;

import com.dbahen.GymTracker.model.Workout;
import com.dbahen.GymTracker.repository.WorkoutRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutService {

    private final WorkoutRepository workoutRepository;

    public WorkoutService(WorkoutRepository workoutRepository) {
        this.workoutRepository = workoutRepository;
    }

    public List<Workout> getWorkouts() {
        return workoutRepository.findAll();
    }

    public Workout addWorkout(Workout workout) {
        return workoutRepository.save(workout);
    }

    public boolean deleteWorkoutById(Long id){
        if (workoutRepository.existsById(id)) {
            workoutRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean updateWorkout(Long id, Workout workout) {
        if (workoutRepository.existsById(id)) {
            workout.setId(id);
            workoutRepository.save(workout);
            return true;
        }
        return false;
    }

}
