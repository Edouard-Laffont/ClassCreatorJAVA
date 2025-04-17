package com.example.classcreator;

public class Stats {

    public Stats(String name, int points) {
        this.name = name;
        this.points = points;
    }

    private String name;

    private int points;

    public void increasePoints() {
        points++;
    }

    public void resetPoints(){
        points = 0;
    }

    public int getPoints() {
        return points;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Stats{" +
                "name='" + name + '\'' +
                ", points=" + points +
                '}';
    }
}
