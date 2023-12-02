/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package competitionmanagementsystem;

import static competitionmanagementsystem.FileManipulator.DataList;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

class Admin extends SystemUser {
    // Additional properties specific to Admin
    private String adminId,userName,Password;
    boolean isValid;

    // Constructor
    public Admin(String username, String password, String adminId) {
        super(username, password);
        this.adminId = adminId;
        this.userName = username;
        this.Password = password;
    }


    

    // Getter and setter for adminId

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    // Implementation of abstract method
    @Override
    public String getUserRole() {
        return "Admin";
    }


    boolean isValid() {
        try {
            Path    currentRelativePath = Paths.get("");
            File myObj = new File(currentRelativePath.toAbsolutePath().toString()+"\\Admin.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
              DataList.add(myReader.nextLine());
            }
        } catch (FileNotFoundException e) {
          
        } 
        if (DataList.contains(this.userName+" - "+this.Password+" - "+getUserRole()))
        {
            isValid = true;
        }
        else
        {
            isValid = false;
        }
        return isValid;
    }
}
