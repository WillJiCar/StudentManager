/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentmanager;

import java.awt.Desktop;
import javax.swing.JFileChooser;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import javax.swing.JTextArea;

/**
 * Chooses files to open or write.
 * CODEJAVA, 2018. Show simple open file dialog using JFileChooser [online]. Available from: https://www.codejava.net/java-se/swing/show-simple-open-file-dialog-using-jfilechooser [Accessed: 15/03/2019]
 * @author William
 */
public class FileInfoChooser {
    
    private JFileChooser fileChooser = new JFileChooser();
    private File selectedFile;
    
    /**
     * Opens file chooser to select file directory that contains CSV files
     * STACKOVERFLOW, 2015. Selecting folder destinations in Java [online]. Available from: https://stackoverflow.com/questions/10083447/selecting-folder-destination-in-java [Accessed 16/03/2019]
     */
    public void ChooseFolder()
    {
        //
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setDialogTitle("Select the folder with all Student CSV");
        fileChooser.setCurrentDirectory(new File(".")); //Starts the directory at the root folder
        int result = fileChooser.showOpenDialog(fileChooser);
        if(result == JFileChooser.APPROVE_OPTION)
        {
            selectedFile = fileChooser.getSelectedFile();
        }
    }
    
    /**
     * Returns the folder containing the CSVs
     * @return File type that contains directory information
     */
    public File getSelectedFile()
    {
        return selectedFile;
    }
    
    /**
     * Saves the set of teams to a text file
     * @param text Text that contains team information
     */
    public void SaveFile(JTextArea text)
    {
        fileChooser.setDialogTitle("Save the teams to a text file");
        fileChooser.setCurrentDirectory(new File("."));
        int result = fileChooser.showSaveDialog(fileChooser);
        if(result == JFileChooser.APPROVE_OPTION)
        {
            File file = fileChooser.getSelectedFile();  //Stores the name entered into Filename box
            if (file == null) { //If blank, do nothing
              return;
            }
            if (!file.getName().toLowerCase().endsWith(".txt")) //If the file name doesn't end with .txt
            {
              file = new File(file.getParentFile(), file.getName() + ".txt");   //Adds .txt to the end of the file
                                                                                //using file name and parent file path
            }
            try //Try Catch exception handlet
            {
              text.write(new OutputStreamWriter(new FileOutputStream(file),
                  "utf-8"));    //Creates a output stream writie, using a file output stream and writes the Report text into file.
              Desktop.getDesktop().open(file);  //Loads up the file that was created.
            } catch (Exception e)   //Catches any exceptions
            {
              e.printStackTrace();  //Prints to console.
            }
        }
    }
}
