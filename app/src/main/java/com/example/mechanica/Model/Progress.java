package com.example.mechanica.Model;

public class Progress {

    int id;
    String progressDifficulties;
    String progressPull;
    String progressDate;

    public Progress(int id, String progressDifficulties, String progressPull, String progressDate) {
        this.id = id;
        this.progressDifficulties = progressDifficulties;
        this.progressPull = progressPull;
        this.progressDate = progressDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProgressDifficulties() {
        return progressDifficulties;
    }

    public void setProgressDifficulties(String progressDifficulties) {
        this.progressDifficulties = progressDifficulties;
    }

    public String getProgressPull() {
        return progressPull;
    }

    public void setProgressPull(String progressPull) {
        this.progressPull = progressPull;
    }

    public String getProgressDate() {
        return progressDate;
    }

    public void setProgressDate(String progressDate) {
        this.progressDate = progressDate;
    }


}
