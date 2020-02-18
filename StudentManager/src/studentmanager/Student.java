/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentmanager;

import java.util.Arrays;


/**
 * Student object
 * @author 23727764
 */
public class Student implements Comparable<Student>{
    private int ID;
    private String name;
    private int grade;
    private int programming;
    private int leadership;
    private int writing;
    private String prefRole;
    
    /**
     * Creates a student
     * @param ID ID
     * @param name Name
     * @param grade Target Grade
     * @param programming Programming grade
     * @param leadership Leadership grade
     * @param writing Writing grade
     */
    public Student(int ID, String name, int grade, int programming, int leadership, int writing)
    {
        this.ID = ID;
        this.name = name;
        this.grade = grade;
        this.programming = programming;
        this.leadership = leadership;
        this.writing = writing;
        setPrefRole();
    }
    
    private void setPrefRole()
    {
        int[] roles = {programming, leadership, writing};
        Arrays.sort(roles);
        if(roles[2] == programming)
        {
            prefRole = "Programmer";
        }
        else if(roles[2] == leadership)
        {
            prefRole = "Leader";
        }
        else
        {
            prefRole = "Writer";
        }
    }
    
    /**
     * Sets the preferred role of a student.
     * @param role The role to be set.
     */
    public void setPrefRole(String role)
    {
        prefRole = role;
    }
    
    /**
     * Gets the preferred role of the student based on their grading
     * @return A String that holds the preferred grade
     */
    public String getPrefRole()
    {
        return prefRole;
    }

    /**
     * The ID of the student
     * @return An int of the ID of the student
     */
    public int getID() {
        return ID;
    }

    /**
     * The Name of the student
     * @return A string of the name
     */
    public String getName() {
        return name;
    }

    /**
     * The Target Grade
     * @return An int of the target grade
     */
    public int getGrade() {
        return grade;
    }

    /**
     * Programming grade
     * @return an int of the programming grade
     */
    public int getProgramming() {
        return programming;
    }

    /**
     * The leadership grade
     * @return An int of the leadership grade
     */
    public int getLeadership() {
        return leadership;
    }

    /**
     * The writing grade
     * @return An int of the writing grade
     */
    public int getWriting() {
        return writing;
    }

    @Override
    public int compareTo(Student o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    //CARNEGIE MELLON UNIVERSITY, 2019. Classes that define an equals method [online]. Available from: https://wiki.sei.cmu.edu/confluence/display/java/MET09-J.+Classes+that+define+an+equals%28%29+method+must+also+define+a+hashCode%28%29+method [Accessed 11/03/2019]
    @Override
    public boolean equals(Object obj)
    {
        if(obj == this)
        {
            return true;
        }
        if(!(obj instanceof Student))
        {
            return false;
        }
        Student stu = (Student)obj;
        return stu.ID == ID;
    }
    
    @Override
    public int hashCode() {
            int result = 0;
            result = (ID / 10) * 31;
            return result;
    }
}
