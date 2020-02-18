/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentmanager;

import java.io.File;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author William
 */
public class LogicTest {
    
    File folder = new File("E:\\OneDrive - Edge Hill University\\CIS2158 - Software Engineering\\Assignment 1\\StudentManager\\StudentManager\\StudentsCSV");
    Logic instance = new Logic();
    JLabel lblFeedback = new JLabel();
    
    /**
     * Test of readFolder method, of class Logic.
     */
    @Test
    public void testReadFolder() {
        System.out.println("readFolder");
        instance.readFolder(folder);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of LoadTeamsToList method, of class Logic.
     */
    @Test
    public void testLoadTeamsToList() {
        System.out.println("LoadTeamsToList");
        JTextArea area = new JTextArea();
        instance.LoadTeamsToList(area, lblFeedback);
    } 
    
    /**
     * Test of calcTeamSize method, of class Logic.
     */
    @Test
    public void testCalcTeamSize() {
        System.out.println("calcTeamSize");
        assertEquals(8, instance.calcTeamSize(30, 4, 3));        
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of sortStudents method, of class Logic.
     */
    @Test
    public void testSortStudents() {
        System.out.println("sortStudents");
        int fir = 4;
        instance.sortStudents(fir, lblFeedback);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
}
