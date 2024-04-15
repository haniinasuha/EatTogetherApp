package com.example.eat;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

/**
 * by:Jason Fong
 */
public class UnitTest {
    @Test
    public void testUserGetUserName() {
        String ID = "a1b2b4ns";
        String firstName = "Josh";
        String lastName= "Dave";
        String email = "hashbrown@gmail.com";
        String password = "aaa555111";
        UserAccounts UA = new UserAccounts(ID,firstName,lastName,email,password);
        assertEquals(firstName,UA.getFirstName());
    }

    @Test
    public void testUserID() {
        String ID = "a1b2b4ns";
        String firstName = "Josh";
        String lastName= "Dave";
        String email = "hashbrown@gmail.com";
        String password = "aaa555111";
        UserAccounts UA = new UserAccounts(ID,firstName,lastName,email,password);
        assertEquals(ID,UA.getUserID());
    }

    @Test
    public void testUserEmail() {
        String ID = "a1b2b4ns";
        String firstName = "Josh";
        String lastName= "Dave";
        String email = "hashbrown@gmail.com";
        String password = "aaa555111";
        UserAccounts UA = new UserAccounts(ID,firstName,lastName,email,password);
        assertEquals(email,UA.getEmail());
    }
    @Test
    public void testPlanDescription(){
        String description = "Enjoy meal time!";
        int spots = 4;
        String mealType = "Breakfast";
        String location = "Curl Market";
        String date = "10/03/2024";
        String time = "13:01pm";
        ArrayList<String> members = new ArrayList<>();
        members.add("1235asd");
        Plan plan = new Plan(description,spots,mealType,location,date,time,members);
        assertEquals(description, plan.getDescription());
    }

    @Test
    public void testPlanMealType(){
        String description = "Enjoy meal time!";
        int spots = 4;
        String mealType = "Breakfast";
        String location = "Curl Market";
        String date = "10/03/2024";
        String time = "13:01pm";
        ArrayList<String> members = new ArrayList<>();
        members.add("1235asd");
        Plan plan = new Plan(description,spots,mealType,location,date,time,members);
        assertEquals(mealType, plan.getMealType());
    }

    @Test
    public void setPlanMealType(){
        String description = "Enjoy meal time!";
        int spots = 4;
        String mealType = "Breakfast";
        String location = "Curl Market";
        String date = "10/03/2024";
        String time = "13:01pm";
        ArrayList<String> members = new ArrayList<>();
        members.add("1235asd");
        Plan plan = new Plan(description,spots,mealType,location,date,time,members);
        String newMealType = "Lunch";
        plan.setMealType(newMealType);
        assertEquals(newMealType, plan.getMealType());
    }

}