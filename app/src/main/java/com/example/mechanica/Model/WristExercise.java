package com.example.mechanica.Model;

public class WristExercise {

    private String exercise;
    private int exerciseImg;

    public WristExercise(String exercise, int exerciseImg) {
        this.exercise = exercise;
        this.exerciseImg = exerciseImg;
    }

    public WristExercise() {

    }

    public String getExercise() {
        return exercise;
    }

    public void setExercise(String exercise) {
        this.exercise = exercise;
    }

    public int getExerciseImg() {
        return exerciseImg;
    }

    public void setExerciseImg(int exerciseImg) {
        this.exerciseImg = exerciseImg;
    }
}
