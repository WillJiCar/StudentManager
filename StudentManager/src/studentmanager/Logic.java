/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentmanager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import sorting.*;

/**
 * Main class for software Logic and algorithms
 * MKYONG, 2013. How to read and parse CSV files in Java. [online]. Available from: https://www.mkyong.com/java/how-to-read-and-parse-csv-file-in-java/ [Accessed 13/02/2019]
 * @author 23727764
 */
public class Logic {
    ArrayList<Student> students = new ArrayList<>();    //List of Students
    ArrayList<Student> unStudents = new ArrayList<>();  //Unsorted students
    ArrayList<Student> tmpList = new ArrayList<>(); //Temporary list of students
    ArrayList<Team> teams = new ArrayList<>();  //List of Teams
    private int aTeamSize = 0;  //Preferred team size
    private int bTeamSize = 0;  //Secondary team size
    private int teamsAmount;    //Total amount of teams
    SortingStudent sStudent = new SortingStudent(); //Sorting by grade
    SortingLeadership sLead = new SortingLeadership();  //Sorting by Leadership
    SortingWriting sWrite = new SortingWriting();   //Sorting by writing
    SortingProgramming sProg = new SortingProgramming();    //Sorting by programming
    VerifyUI verify = new VerifyUI();
    private int stuCount = 0;
    private int notAdded = 0;
    
    /**
     * Reads all files inside a directory.
     * STACKOVERFLOW, 2009. How to read all files in a folder from Java. [online]. Available from: https://stackoverflow.com/questions/1844688/how-to-read-all-files-in-a-folder-from-java [Accessed 13/02/2019]     * 
     * @param folder Folder that contains the required files to be read.
     */
    public void readFolder(File folder)
    {
        for(File file : folder.listFiles())
        {
            readFile(file);
        }
    }
    
    /**
     * Reads the file and gets data from the CSV to create a Student Object
     * BAELDUNG, 2010. How to turn a CSV file into an array. [online] Available from: https://www.baeldung.com/java-csv-file-array [Accessed 13/03/2019] 
     * @param csvFile The file that is to be read
     */
    private void readFile(File csvFile){
        BufferedReader br = null;
        String line = "";   //Line to be split
        String cvsSplitBy = ",";    //Determiner to split line
        int lineNum = 0;
        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {    //While there are still lines to be read
                lineNum++;
                String[] studentDataSet = line.split(cvsSplitBy);   //Data is split into a string array
                Student stu = new Student(Integer.parseInt(studentDataSet[1]), studentDataSet[0], Integer.parseInt(studentDataSet[2]), Integer.parseInt(studentDataSet[3]), Integer.parseInt(studentDataSet[4]), Integer.parseInt(studentDataSet[5]));
                if(!students.contains(stu) && 
                            verify.verifyFields(studentDataSet[1], studentDataSet[0], studentDataSet[2], studentDataSet[3], studentDataSet[4], studentDataSet[5], students)) //If the student isn't already in the list
                {
                    students.add(stu);  //Adds the student to the main list
                    stuCount++;
                }
                else
                {
                    notAdded++;
                    DisplayError("File: " + csvFile.getName() + " contains an error." + "\n"
                            + "Line: " + lineNum + " \n"
                                    + verify.getError());
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    
    
    private int calcTeamSize(int totalStudents, int firSize, int secSize)
    {
        teams.clear();  //Clears the teams to restart the team creation process
        int result = totalStudents % firSize;   //The remainder of people after filling teams of 4
        if (result == 0)    //If there is no remainder of people then the amount of teams is calculated below
        {
            aTeamSize = totalStudents / firSize;    //The total amount of students divided by the preferred team size is how many teams there are.
        }
        else
        {
            for(int i = totalStudents; i >= 0; i--) //A loop to decrement until 2 perfect modulo numbers are found.
            {
                if(i % firSize == 0)    //Checks to see if the current number modulo the preferred team size is 0.
                {
                    int dif = totalStudents - i;    //The difference between the start and the current number
                    if(dif % secSize == 0)  //Checks to see if the difference modulo the remainder team size is 0
                    {
                        aTeamSize = i / firSize;    // The current number divided by the preferred team size is how many main teams there are.
                        bTeamSize = dif / secSize;  //The difference divided by the remainder team size is how many small teams there are.
                        break;  //It will now stop looping as it's found all it needs to fine
                    }   // in another case it will keep on decrementing until 2 perfect numbers are found
                }
            }
        }
        for(int i = 0; i < aTeamSize; i++)
        {
            teams.add(new Team(firSize));   //A loop is made to create a number of main teams with the preferred team size being the max
        }
        for(int i = 0; i < bTeamSize; i++)
        {
            teams.add(new Team(secSize));   //Then another loop is made to create a number of small teams with the remainder team size being the max
        }
        teamsAmount = aTeamSize + bTeamSize;    //The two of these numbers combined equals the total number of teams.
        return teamsAmount;
    }
    
    private void sortStudents(int fir, JLabel lblFeedback)
    {
        for(Student stu : students) //The main list of students is duplicated into a list that will be manipulated
        {
            unStudents.add(stu);
        }        
        for(int i = 0; i < fir; i++)    //Each loop will add 1 student into each team, repeating as long as the preferred team size
        {
            switch (i) {
                case 0:
                    Collections.sort(unStudents, sLead);    //First the best leaders are found 
                    addStudentsToTeam(teamsAmount, "Leader");   //1 Leader is added into each team
                    break;
                case 1:
                    Collections.sort(unStudents, sProg);    //Then the programmers are found  
                    addStudentsToTeam(teamsAmount, "Programmer");   //1 Programmer is added into each team
                    break;
                case 2:
                    Collections.sort(unStudents, sWrite);   //..Next the writers
                    addStudentsToTeam(teamsAmount, "Writer");   
                    break;
                default:
                    Collections.sort(unStudents, sStudent); //..Then the rest of the students
                    addStudentsToTeam(aTeamSize, "Writer"); //The teams of 3 will have been filled in by now 
                    break;  //the remainding teams of 4 still need 1 more member
            }
        }  
        lblFeedback.setText("Sorted " + teams.size() + " Teams, click Save to store text file to system.");
    }
    
    private void addStudentsToTeam(int teamSize, String role)
    {
        for(int i = 0; i < teamSize; i++)   //A temp list is populated with the first 8 students in the sorted list
        {
            tmpList.add(unStudents.get(i));
        }
        tmpList.forEach((stu) -> {
            unStudents.remove(stu); //These students are removed from the sorting list so that they are not added again
        });
        Collections.sort(tmpList, sStudent);    //The temp list is sorted by their grades
        for(int i = 0; i < teamsAmount; i++)    //A Student in added into each team
        {
            if(teams.get(i).getCurrent() != teams.get(i).getMax())  //If the team has the max number, it will be skipped
            {
                tmpList.get(i).setPrefRole(role);
                teams.get(i).AddStudent(tmpList.get(i));  //A student is added with it's calculated role.
            }
        }
        tmpList.clear();    //The temp list is cleared so that the method can loop again.
    }
    
    /**
     * Loads students to the main text area
     * @param area The area that will be appended
     * @param lblFeedback Feedback label that will be used to feedback to the user
     */
    public void LoadStudentsToList(JTextArea area, JLabel lblFeedback)
    {
        area.setText("");
        students.forEach((stu) -> {
            area.append(stu.getName() + "\t\t Grade: " + stu.getGrade() + " \tPreffered Role: " + stu.getPrefRole() + " \tID: " + stu.getID() + "\n");
        });   
        String feedback = "Added " + stuCount + " New Stundets, click Sort to create Teams.";
        if(notAdded > 0) {
            feedback = feedback.concat(" Not Added " + notAdded + " " + verify.getError());
        }
        stuCount = 0;
        notAdded = 0;
        lblFeedback.setText(feedback);
    }
    
    /**
     * Loads teams to the main text area
     * @param area The area that will be appended
     * @param lblFeedback Feedback label that will be used to feedback to the user
     */
    public void LoadTeamsToList(JTextArea area, JLabel lblFeedback)
    {
        area.setText("");
        calcTeamSize(students.size(), 4, 3); //Calculates the teams
        sortStudents(4, lblFeedback);
        int i = 1;
        for(Team team : teams)
        {
            area.append("\n----- Team " + i + " ----- \tAverage Grade: " + team.getAverage() + "\n");
            team.getMembers().forEach((stu) -> {
                area.append(stu.getName() + " \t" + stu.getPrefRole() + " \tGrade: " + stu.getGrade() + " \tID: " + stu.getID() + "\n");
            });
            i++;
        }
    }
    
    /**
     * Adds a singular student to the main student list
     * STACKOVERFLOW, 2011. Writing Strings to a CSV file. [online] Available from: https://stackoverflow.com/questions/30073980/java-writing-strings-to-a-csv-file [Accessed 25/03/2019]
     * @param name Name
     * @param ID ID
     * @param grade Target Grade
     * @param lead Leadership grade
     * @param prog Programming grade
     * @param write Writing grade
     * @param lblFeedback Feedback label that will be used to feedback to the user
     */
    public void AddStudent(JTextField name, JTextField ID, JTextField grade, JTextField lead, JTextField prog, JTextField write, JLabel lblFeedback)
    {
        if(!verify.verifyFields(ID.getText(), name.getText(), grade.getText(), lead.getText(), prog.getText(), write.getText(), students))
        {
            DisplayError(verify.getError());
            return;
        }
        students.add(new Student(Integer.parseInt(ID.getText()), name.getText(), Integer.parseInt(grade.getText()), Integer.parseInt(lead.getText()), Integer.parseInt(prog.getText()), Integer.parseInt(write.getText())));
        try (PrintWriter writer = new PrintWriter(new File("StudentsCSV/" + ID.getText()+ ".csv"))) {
        StringBuilder sb = new StringBuilder();
        sb.append(name.getText() + "," + ID.getText() + "," + grade.getText() + "," + lead.getText() + "," + prog.getText() + "," + write.getText());
        writer.write(sb.toString());

      } catch (FileNotFoundException e) {
        System.out.println(e.getMessage());
      }
        lblFeedback.setText("Added " + name + " to Students, add more students or click sort.");
    }
    
    /**
     * Displays an error if the data verification is not correct
     * @param error Error message to show
     */
    public void DisplayError(String error)
    {
        JOptionPane.showMessageDialog(null, error, "Verification Error", JOptionPane.ERROR_MESSAGE);
    }
    
    /**
     * Returns the teams stored in the system.
     * @return ArrayList of Teams.
     */
    public ArrayList<Team> GetTeams()
    {
        return teams;
    }
}
