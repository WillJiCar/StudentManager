/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sorting;

import java.util.Comparator;
import studentmanager.Student;

/**
 * Sorts by their programming grade
 * @author William
 */
public class SortingProgramming implements Comparator<Student>{

    @Override
    public int compare(Student o1, Student o2) {
        return (o2.getProgramming() - o1.getProgramming());
    }
    
}
