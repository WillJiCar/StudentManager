/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentmanager;

import java.util.ArrayList;

/**
 * Team object
 * @author 23727764
 */
public class Team {
    
    private ArrayList<Student> members = new ArrayList<>();
    private int max;
    private int avgGrade;
    private int currentAmount = 0;
    
    /**
     * Defines the team and the maximum team size
     * @param max
     */
    public Team(int max)
    {
        this.max = max;
    }
    
    /**
     * How many members are currently in the team
     * @return
     */
    public int getCurrent()
    {
        return currentAmount;
    }
    
    /**
     * Gets the maximum amount of members allowed.
     * @return
     */
    public int getMax()
    {
        return max;
    }

    /**
     * Returns the list of members
     * @return An arraylist of team members
     */
    public ArrayList<Student> getMembers() {
        return members;
    }
    
    /**
     * Adds a student to the members list
     * @param member Member to add
     */
    public void AddStudent(Student member)
    {
        members.add(member);
        currentAmount++;
        if(currentAmount == max)
        {
            setAverage();   //Sets the average grade for the team once the team is filled
        }
    }
    
    private void setAverage()
    {
        int sum = 0;
        for(Student stu : members)
        {
            sum += stu.getGrade();
        }
        avgGrade = sum / members.size();
    }
    
    /**
     * Returns the average grade
     * @return An int that holds the average grade of the team
     */
    public int getAverage()
    {
        return avgGrade;
    }
}
