package com.example.mechanica.Model;

public class Progress {

    String Level, PullPercent;

    public Progress(String level, String pullPercent) {
        Level = level;
        PullPercent = pullPercent;
    }

    public Progress() {

    }

    public String getLevel() {
        return Level;
    }

    public void setLevel(String level) {
        Level = level;
    }

    public String getPullPercent() {
        return PullPercent;
    }

    public void setPullPercent(String pullPercent) {
        PullPercent = pullPercent;
    }
}
