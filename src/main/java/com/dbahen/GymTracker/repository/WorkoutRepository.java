package com.dbahen.GymTracker.repository;

import com.dbahen.GymTracker.model.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface WorkoutRepository extends JpaRepository<Workout, Long> {
    List <Workout> findByDate(LocalDate date);
}
