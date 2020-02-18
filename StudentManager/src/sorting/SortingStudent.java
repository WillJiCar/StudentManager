/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sorting;

import java.util.Comparator;
import studentmanager.Student;

/**
 * Sorts by their target grade
 * @author William
 */
public class SortingStudent implements Comparator<Student> {

    @Override
    public int compare(Student o1, Student o2) {
        return (o2.getGrade() - o1.getGrade());}
}
