package com.dbahen.GymTracker.model;

import jakarta.validation.constraints.*;
import jakarta.validation.Valid;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

@Entity
public class Workout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private LocalDate date;

    @Valid
    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    private Exercise exercise;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Valid
    @NotEmpty
    @OneToMany(cascade = CascadeType.ALL)
    private List<SetEntry> sets = new ArrayList<>();

    //Empty Constructor for JSON
    public Workout() {}

        public Workout(LocalDate date, Exercise exercise) {
        this.date = date;
        this.exercise = exercise;
    }

    public Long getId(){
        return id;
    }

    public LocalDate getDate() {
        return date;
    }
    public Exercise getExercise() {
        return exercise;
    }
    public List<SetEntry> getSets() {
        return sets;
    }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public void setSets(List<SetEntry> sets) {
        this.sets = sets;
    }
}
