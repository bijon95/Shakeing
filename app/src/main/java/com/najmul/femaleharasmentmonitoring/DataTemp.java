package com.najmul.femaleharasmentmonitoring;

/**
 * Created by HP-PC on 11/7/2017.
 */

public class DataTemp {
    private int id;
    private String name;
    private String day;

    public DataTemp(String n, String d){
        name = n;
        day = d;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getDay() {
        return day;
    }
}
