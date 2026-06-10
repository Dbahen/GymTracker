package com.dbahen.GymTracker.service;

import com.dbahen.GymTracker.model.User;
import com.dbahen.GymTracker.model.Workout;
import com.dbahen.GymTracker.repository.UserRepository;
import com.dbahen.GymTracker.repository.WorkoutRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class WorkoutService {

    private final WorkoutRepository workoutRepository;
    private final UserRepository userRepository;

    public WorkoutService(WorkoutRepository workoutRepository, UserRepository userRepository) {
        this.workoutRepository = workoutRepository;
        this.userRepository = userRepository;
    }

    private User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    public List<Workout> getWorkouts() {
        return workoutRepository.findByUser(getCurrentUser());
    }

    public List<Workout> getWorkoutsByDate(LocalDate date) {
        return workoutRepository.findByUserAndDate(getCurrentUser(), date);
    }

    public Workout addWorkout(Workout workout) {
        workout.setUser(getCurrentUser());
        return workoutRepository.save(workout);
    }

    public boolean deleteWorkoutById(Long id) {
        User user = getCurrentUser();
        if (workoutRepository.existsById(id)) {
            Workout workout = workoutRepository.findById(id).get();
            if (workout.getUser().getId().equals(user.getId())) {
                workoutRepository.deleteById(id);
                return true;
            }
        }
        return false;
    }

    public boolean updateWorkout(Long id, Workout workout) {
        User user = getCurrentUser();
        if (workoutRepository.existsById(id)) {
            Workout existing = workoutRepository.findById(id).get();
            if (existing.getUser().getId().equals(user.getId())) {
                workout.setId(id);
                workout.setUser(user);
                workoutRepository.save(workout);
                return true;
            }
        }
        return false;
    }
}