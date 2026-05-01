package com.dbahen.GymTracker.model;

import jakarta.validation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String muscleGroup;

    public Exercise() {}

    public Exercise(String name, String muscleGroup) {
        this.name = name;
        this.muscleGroup = muscleGroup;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getMuscleGroup() { return muscleGroup; }

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setMuscleGroup(String muscleGroup) { this.muscleGroup = muscleGroup; }
}
