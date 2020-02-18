/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentmanager;

import java.util.ArrayList;
import javax.swing.JTextField;

/**
 * Verifys the UI
 * @author William
 */
public class VerifyUI {
    
    /**
     * Detects if the string is not a complete number
     * @param field Field to check
     * @return Returns false if it isn't a complete number
     */
    
    private String error = "";
    
    /**
     * Gets the error that has happened.
     * @return A String that holds the error.
     */
    public String getError()
    {
        return error;
    }
    
    /**
     * 
     * @param field
     * @return
     */
    private boolean digitVerify(String field)
    {
        for(char c : field.toCharArray())
        {
            if(!Character.isDigit(c)) // ID only use numbers, can only be 8 Characters no more of less
            {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Detects if the number is out of range
     * @param field Field to check
     * @param min Minimum value
     * @param max Maximum value
     * @return Returns false if it isn't in rage.
     */
    private boolean rangeVeriy(String field, int min, int max)
    {
        if(Integer.parseInt(field) > max || Integer.parseInt(field) < min)
        {
            return false;
        }
        return true;
    }
    
    /**
     *
     * @param ID
     * @param name
     * @param grade
     * @param lead
     * @param prog
     * @param write
     * @param students
     * @return
     */
    public boolean verifyFields(String ID, String name, String grade, String lead, String prog, String write, ArrayList<Student> students)
    {
        if(!digitVerify(String.valueOf(ID)))    //ID Must be Digits
        {
            error = "Please enter a valid ID (Only Digits)";
            return false;
        }
        else if(String.valueOf(ID).toCharArray().length != 8)
        {
            error = "Please enter a valid ID (8 Characters)";
            return false;
        }
        else if(!digitVerify(String.valueOf(grade)))  //Grade must be digits
        {
            error = "Please enter a valid target grade (Only Digits)";
            return false;
        }
        else if(!rangeVeriy(String.valueOf(grade), 1, 100)) //Grade must be between 1 and 100
        {
            error = "Please enter a valid target grade (In between 1 and 100)";
            return false;
        }
        else if(!digitVerify(String.valueOf(lead)))
        {
            error = "Please enter a valid Leadership grade (Only digits)";
            return false;
        }
        else if(!rangeVeriy(String.valueOf(lead), 1, 10))
        {
            error = "Please enter a valid Leadership grade (Between 1 and 10)";
            return false;
        }
        else if(!digitVerify(String.valueOf(prog)))
        {
            error = "Please enter a valid Programmer grade (Only digits)";
            return false;
        }
        else if(!rangeVeriy(String.valueOf(prog), 1, 10))
        {
            error = "Please enter a valid Programmer grade (Between 1 and 10)";
            return false;
        }
        else if(!digitVerify(String.valueOf(write)))
        {
            error = "Please enter a valid Writing Grade (Only digits)";
            return false;
        }
        else if(!rangeVeriy(String.valueOf(write), 1, 10))
        {
            error = "Please enter a valid Writing grade (Between 1 and 10)";
            return false;
        }
        else if(name.isEmpty())
        {
            error = "Please enter a valid name (More than 1 character)";
            return false;
        }
        else if(!verifyID(students, ID))
        {
            error = "This student already exists";
            return false;
        }
        return true;
    }
    
    private boolean verifyID(ArrayList<Student> students, String ID)
    {
        return students.stream().noneMatch((stu) -> (stu.getID() == Integer.parseInt(ID)));
    }
}
