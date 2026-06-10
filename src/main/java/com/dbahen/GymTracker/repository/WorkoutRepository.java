package com.dbahen.GymTracker.repository;

import com.dbahen.GymTracker.model.Workout;
import com.dbahen.GymTracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface WorkoutRepository extends JpaRepository<Workout, Long> {
    List <Workout> findByDate(LocalDate date);
    List<Workout> findByUser(User user);
    List<Workout> findByUserAndDate(User user, LocalDate date);
}
