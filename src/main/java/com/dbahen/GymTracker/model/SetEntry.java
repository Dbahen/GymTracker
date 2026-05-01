package com.dbahen.GymTracker.model;

import jakarta.validation.constraints.*;
import jakarta.persistence.*;

@Entity
public class SetEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(1)
    private int reps;

    @Min(1)
    private int weight;

    public SetEntry() {}

    public SetEntry(int reps, int weight) {
        this.reps = reps;
        this.weight = weight;
    }

    public Long getId() {return id; }
    public int getReps() { return reps; }
    public int getWeight() { return weight; }

    public void setIde(Long id) { this.id = id; }
    public void setReps(int reps) { this.reps = reps; }
    public void setWeight(int weight) { this.weight = weight; }
}
