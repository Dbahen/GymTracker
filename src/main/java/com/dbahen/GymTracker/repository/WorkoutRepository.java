package com.dbahen.GymTracker.repository;

import com.dbahen.GymTracker.model.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkoutRepository extends JpaRepository<Workout, Long> {
}
