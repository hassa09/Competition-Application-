/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package competitionmanagementsystem;

import java.util.ArrayList;
import java.util.List;

// Abstract class to represent a SystemUser
abstract class SystemUser {
    private String username;
    private String password;
    private String email;
    private String id;
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Constructor
    public SystemUser(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public SystemUser(String id) {
        this.id = id;
    }
    public SystemUser(String id, String name, String password, String email) {
        this.username = name;
        this.password = password;
        this.id       = id;
        this.email    = email;
    }

    // Getters and setters

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Abstract method for user role
    public abstract String getUserRole();
}
