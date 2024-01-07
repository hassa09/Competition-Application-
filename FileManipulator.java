/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package competitionmanagementsystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 *
 */
public class FileManipulator {
    
    static List<String> DataList = new ArrayList<>();
    
    public static void writeOrUpdateFile(boolean isUpdate, boolean isAppend, String textToWrite, String filePath, String SuccessMessage) {
        try {
            FileWriter myWriter = new FileWriter(filePath, isAppend);
            myWriter.write(textToWrite);
            myWriter.close();
            System.out.println(SuccessMessage);
        } 
        catch (IOException e) 
        {
            System.out.println("Failed to execute the command");
        }
    }

    /**
     * Writes or updates a file with the provided text.
     * @param isUpdate Whether to update an existing file.
     * @param isAppend Whether to append data to an existing file.
     * @param textToWrite The text to be written to the file.
     * @param filePath The path of the file to be written or updated.
     */
    public static void writeOrUpdateFile(boolean isUpdate, boolean isAppend, String textToWrite, String filePath) {
        try {
            FileWriter myWriter = new FileWriter(filePath, isAppend);
            myWriter.write(textToWrite);
            myWriter.close();
        } 
        catch (IOException e) 
        {
            JOptionPane.showMessageDialog(null, "Failed to execute the command");
        }
    }

    /**
     * Deletes a file at the specified path.
     * @param path The path of the file to be deleted.
     * @param SuccessMessage The success message to be displayed.
     */
    public static void deleteFile(String path, String SuccessMessage) {
        File myObj = new File(path); 
        if (myObj.delete()) { 
          System.out.println(SuccessMessage);
        } else {
          System.out.println("Failed to execute the command");
        } 
    }

    /**
     * Reads data from a file and populates the DataList.
     * @param path The path of the file to be read.
     */
    public static void getData(String path) {
        try {
            File myObj = new File(path);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
              DataList.add(myReader.nextLine());
            }
        } catch (FileNotFoundException e) {
          
        } 
    }
}
