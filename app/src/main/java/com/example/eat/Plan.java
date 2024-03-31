package com.example.eat;

import java.io.Serializable;

public class Plan implements Serializable {
    private String description;
    private int spots;
    private String mealType; //breakfast, lunch, dinner
    private String date; //format mm/dd/yy

    private String location;
    private String time; //format 00:00
    private String Id;

    public Plan() {
        // Default constructor required for Firebase serialization
    }
    public Plan(String desc, int spots, String mealType, String location, String date, String time)
    {
        this.description = desc;
        this.spots = spots;
        this.mealType = mealType;
        this.location = location;
        this.date = date;
        this.time = time;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String desc) {description = desc;}
    public int getSpots()
    {
        return spots;
    }
    public void setSpots(int spots) {this.spots = spots;}
    public String getMealType()
    {
        return mealType;
    }
    public void setMealType(String mealType) {this.mealType = mealType;}

    public String getDate()
    {
        return this.date;
    }
    public void setDate(String date) {this.date = date;}

    public String getTime()
    {
        return time;
    }
    public void setTime(String time) {this.time = time;}

    public String getLocation()
    {
        return location;
    }
    public void setLocation(String loc) {location = loc;}
    public String getId() {return Id;}
    public void setId(String id) {this.Id = id;}
}
