package org.techtown.myapplication;

public class Counselor {
    private String name;
    private String location;
    private String experience;

    public Counselor(String name, String location, String experience) {
        this.name = name;
        this.location = location;
        this.experience = experience;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getExperience() {
        return experience;
    }
}


