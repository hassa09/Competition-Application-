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
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 *
 */
public class Result {

    String resultId;
    String name;
    String participantId;
    int[] scores;
    String status;
    String Level;

    

    public Result(String resultId, String participantId,String name, String Level , String status, int[] Scores)
    {
        this.resultId = resultId;
        this.participantId = participantId;
        this.status = status;
        this.scores = Scores;
        this.name=name;
        this.Level=Level;
    }

    Result(String resultId) {
        this.resultId = resultId;
    }

    public String getLevel() {
        return Level;
    }

    public void setLevel(String level) {
        this.Level = level;
    }
    
    public String getParticipantId() {
        return participantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void setParticipantId(String participantId) {
        this.participantId = participantId;
    }

    public int[] getScores() {
        return scores;
    }

    public void setScores(int[] scores) {
        this.scores = scores;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setResultId(String resultId) {
        this.resultId = resultId;
    }
    public String getResultId() {
        return resultId;
    }

    void addParticipantScores() {
        try {
            String text = "";
            double overallScore =0;
            for(int score: scores)
            {
                overallScore += score; 
            }
            overallScore = overallScore/5;
            Path    currentRelativePath = Paths.get("");
            String filePath = currentRelativePath.toAbsolutePath().toString()+"\\"+getResultId()+"Result.txt";
            FileWriter myWriter = new FileWriter(filePath, true);
            for(int i: getScores())
            {
                 text = text + i+" ";
            }
            myWriter.write(getParticipantId()+" - "+getName()+" - "+getLevel()+" - "+text+ " - "+overallScore+" - "+getStatus()+"\n");
            myWriter.close();
        } 
        catch (IOException e) 
        {
            JOptionPane.showMessageDialog(null, "Failed to execute the command");
        }
    }

    public String getReport(boolean isExport) {
        String dataResult="";
        Path    currentRelativePath = Paths.get("");
        try {
            
            File myObj = new File(currentRelativePath.toAbsolutePath().toString()+"\\"+getResultId()+"Result.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
              dataResult = dataResult + myReader.nextLine()+"\n";
            }
            myReader.close();
            if (isExport)
            {
                try {
                String filePath = currentRelativePath.toAbsolutePath().toString()+"\\ExportedReport.txt";
                FileWriter myWriter = new FileWriter(filePath, false);
                myWriter.write("Competitor  Name          Level       Scores        Overall \n"+dataResult);
                myWriter.close();
                JOptionPane.showMessageDialog(null, "Report exported at "+currentRelativePath.toAbsolutePath().toString()+"\\ExportedReport.txt");
                } 
                catch (IOException e) 
                {
                    JOptionPane.showMessageDialog(null, "Failed to execute the command");
                }
            }
            
        } catch (FileNotFoundException e) {
            System.out.println("Data not found");
        } 
        return dataResult;
    }

    void addAllResults() {
        try {
            String text = "";
            double overallScore =0;
            for(int score: scores)
            {
                overallScore += score; 
            }
            overallScore = overallScore/5;
            Path    currentRelativePath = Paths.get("");
            String filePath = currentRelativePath.toAbsolutePath().toString()+"\\Results.txt";
            FileWriter myWriter = new FileWriter(filePath, true);
            for(int i: getScores())
            {
                 text = text + i+" ";
            }
            myWriter.write("Result "+getResultId()+"\n"+getParticipantId()+" - "+getName()+" - "+getLevel()+" - "+text+ " - "+overallScore+"\n \n");
            myWriter.close();
        } 
        catch (IOException e) 
        {
            JOptionPane.showMessageDialog(null, "Failed to execute the command");
        }
    }
    public String getAllResults() {
        String dataResult="";
        try {
            Path    currentRelativePath = Paths.get("");
            File myObj = new File(currentRelativePath.toAbsolutePath().toString()+"\\CompetitionResults.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
              String data[]=myReader.nextLine().split(" - ");
              dataResult = dataResult+"Competition Id - "+data[0]+"\n"+" Competitor  Name          Level       Scores        Overall \n";
                
                File myObj1 = new File(currentRelativePath.toAbsolutePath().toString()+"\\"+data[1]+"Result.txt");
                Scanner myReader1 = new Scanner(myObj1);
                while (myReader1.hasNextLine()) {
                    dataResult = dataResult + myReader1.nextLine()+"\n";
                }
                myReader1.close();
            }
            dataResult = dataResult+"\n";
            myReader.close();
            
            
        } catch (FileNotFoundException e) {
            System.out.println("Data not found");
        } 
        return dataResult;
    }
    
    
   
}
