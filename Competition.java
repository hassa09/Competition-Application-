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
public class Competition{
    String Id;
    String startDateTime;
    String endDateTime;
    List<Participant> ParticipantList = new ArrayList<>();
    List<Result> ResultList = new ArrayList<>();
    String Status;
    boolean isValidCompetition = false;

    
    public Competition(String id, String startDateTime, String endDateTime, String status) {
        this.Id             = id;
        this.startDateTime  = startDateTime;
        this.endDateTime    = endDateTime;
        this.Status         = status;
    }
    public Competition(String id) {
        this.Id             = id;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }
    public String getId() {
        return Id;
    }
    public void setId(String Id) {
        this.Id = Id;
    }

    public String getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(String startDateTime) {
        this.startDateTime = startDateTime;
    }

    public String getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(String endDateTime) {
        this.endDateTime = endDateTime;
    }

    public List<Participant> getParticipantList() {
        return ParticipantList;
    }

    public void setParticipantList(List<Participant> ParticipantList) {
        ParticipantList = ParticipantList;
    }

    public List<Result> getResultList() {
        return ResultList;
    }

    public void setResultList(List<Result> ResultList) {
        this.ResultList = ResultList;
    }

    void createCompetition() {
        try {
            Path    currentRelativePath = Paths.get("");
            String filePath = currentRelativePath.toAbsolutePath().toString()+"\\Competition"+getId()+".txt";
            FileWriter myWriter = new FileWriter(filePath, false);
            myWriter.write(getId()+"\n"+getStartDateTime()+"\n"+getEndDateTime()+"\n"+getStatus());
            myWriter.close();
        } 
        catch (IOException e) 
        {
            JOptionPane.showMessageDialog(null, "Failed to execute the command");
        }
    }
    void addCompetitionList() {
        try {
            Path    currentRelativePath = Paths.get("");
            String filePath = currentRelativePath.toAbsolutePath().toString()+"\\CompetitionList.txt";
            FileWriter myWriter = new FileWriter(filePath, true);
            myWriter.write(getId()+" - "+getStatus()+"\n");
            myWriter.close();
        } 
        catch (IOException e) 
        {
            JOptionPane.showMessageDialog(null, "Failed to execute the command");
        }
    }

    void fillDetails() {
        List<String> Datalist = new ArrayList<>();
        try {
            Path    currentRelativePath = Paths.get("");
            File myObj = new File(currentRelativePath.toAbsolutePath().toString()+"\\Competition"+getId()+".txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
              Datalist.add(myReader.nextLine());
            }
            myReader.close();
            setId(Datalist.get(0));
            setStartDateTime(Datalist.get(1));
            setEndDateTime(Datalist.get(2));
            setStatus(Datalist.get(3));
            
        } catch (FileNotFoundException e) {
            System.out.println("Data not found");
        } 
        
    }

    void updateCompetitionList(String oldStatus) {
        List<String> Datalist = new ArrayList<>();
        try {
            Path    currentRelativePath = Paths.get("");
            File myObj = new File(currentRelativePath.toAbsolutePath().toString()+"\\CompetitionList.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
              Datalist.add(myReader.nextLine());
            }
            myReader.close();
        } catch (FileNotFoundException e) {
          
        } 
        if (Datalist.contains(getId()+" - "+oldStatus))
        {
            int index = Datalist.indexOf(getId()+" - "+oldStatus);
            Datalist.set(index,getId()+" - "+ getStatus());
            try {
                Path    currentRelativePath = Paths.get("");
                String filePath = currentRelativePath.toAbsolutePath().toString()+"\\CompetitionList.txt";
                FileWriter myWriter = new FileWriter(filePath, false);
                for (String s:Datalist)
                {
                    myWriter.write(s+"\n");
                }
                myWriter.close();
            } 
            catch (IOException e) 
            {
                JOptionPane.showMessageDialog(null, "Failed to execute the command");
            }
        }
    }

    public void deleteCompetition() {
        Path    currentRelativePath = Paths.get("");
        File myObj = new File(currentRelativePath.toAbsolutePath().toString()+"\\Competition"+getId()+".txt");
        if (myObj.delete()) { 
          System.out.println("Staff removed successfully");
        } else {
          System.out.println("Failed to execute the command");
        } 
    }

    public void removeFromCompetitionList() {
        List<String> Datalist = new ArrayList<>();
        try {
            Path    currentRelativePath = Paths.get("");
            File myObj = new File(currentRelativePath.toAbsolutePath().toString()+"\\CompetitionList.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
              Datalist.add(myReader.nextLine());
            }
            myReader.close();
        } catch (FileNotFoundException e) {
          
        } 
        if (Datalist.contains(getId()+" - "+getStatus()))
        {
            int index = Datalist.indexOf(getId()+" - "+getStatus());
            Datalist.remove(index);
            try {
                Path    currentRelativePath = Paths.get("");
                String filePath = currentRelativePath.toAbsolutePath().toString()+"\\CompetitionList.txt";
                FileWriter myWriter = new FileWriter(filePath, false);
                for (String s:Datalist)
                {
                    myWriter.write(s+"\n");
                }
                myWriter.close();
            } 
            catch (IOException e) 
            {
                JOptionPane.showMessageDialog(null, "Failed to execute the command");
            }
        }
    }

    void fillDetailsWithParitipants() {
        List<String> Datalist = new ArrayList<>();
        List<Participant> Participants = new ArrayList<>();
        try {
            Path    currentRelativePath = Paths.get("");
            File myObj = new File(currentRelativePath.toAbsolutePath().toString()+"\\"+getId()+"ParticipantList.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
              Datalist.add(myReader.nextLine());
            }
            myReader.close();
            for (int i = 0; i < Datalist.size(); i++) {
                ParticipantList.add(new Participant(Datalist.get(i)));
            }
            System.out.println(getParticipantList().size());
            
        } catch (FileNotFoundException e) {
            System.out.println("Data not found");
        } 
    }

    public boolean validCompetition() {
        List<String> Datalist = new ArrayList<>();
        try {
            Path    currentRelativePath = Paths.get("");
            File myObj = new File(currentRelativePath.toAbsolutePath().toString()+"\\CompetitionList.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
              Datalist.add(myReader.nextLine());
            }
            myReader.close();
        } catch (FileNotFoundException e) {
          
        } 
        if (Datalist.contains(getId()+" - "+"Open"))
        {
            isValidCompetition =  true;  
        }
        else
        {
            isValidCompetition = false;
        }
        return isValidCompetition;
    }

    public void addParticipantToCompetition(String loggedInUser) {
        try {
            Path    currentRelativePath = Paths.get("");
            String filePath = currentRelativePath.toAbsolutePath().toString()+"\\"+getId()+"ParticipantList.txt";
            FileWriter myWriter = new FileWriter(filePath, true);
            myWriter.write(loggedInUser+"\n");
            myWriter.close();
        } 
        catch (IOException e) 
        {
            JOptionPane.showMessageDialog(null, "Failed to execute the command");
        }
    }
    public Result getResult(String resultId) {
        for (Result result : ResultList) {
            if (result.getResultId().equals(resultId)) {
                return result;
            }
        }
        return null; // Result not found
    }

    public void printResult(String resultId) {
        Result result = getResult(resultId);
        if (result != null) {
            System.out.println("Result ID: " + result.getResultId());
            // Print other result details...
        } else {
            System.out.println("Result not found.");
        }
    }

    public void getReport() {
        
    }


    
    private List<String> convertObjectListToStringList(List<Result> objectList) {
        List<String> stringList = new ArrayList<>();
        for (Result person : objectList) {
            stringList.add(person.toString());
        }
        return stringList;
    }

    void addCompetitionResults(String CompId, String ResultId) {
        try {
            Path    currentRelativePath = Paths.get("");
            String filePath = currentRelativePath.toAbsolutePath().toString()+"\\CompetitionResults.txt";
            FileWriter myWriter = new FileWriter(filePath, true);
            myWriter.write(CompId+" - "+ResultId+"\n");
            myWriter.close();
        } 
        catch (IOException e) 
        {
            JOptionPane.showMessageDialog(null, "Failed to execute the command");
        }
    }
    
}