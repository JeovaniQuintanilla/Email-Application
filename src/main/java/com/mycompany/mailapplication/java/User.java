/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mailapplication.java;

/**
 *
 * @author jeova
 */
public class User {
    private String fName;
    private String lName;
    private String emailAddr;
    private String password;
 
 public User(String fName, String lName, String emailAddr, String password) {
        this.fName = fName;
        this.lName = lName;
        this.emailAddr = emailAddr;
        this.password = password;
    }

    public User() {
    }
    
    public void setfName(String fName) {
        this.fName = fName;
    }
    public String getfName() {
        return fName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }
    public String getlName() {
        return lName;
    }

    public void setEmailAddr(String emailAddr) {
        this.emailAddr = emailAddr;
    } 
    public String getEmailAddr() {
        return emailAddr;
    }

   
    public void setPassword(String password) {
        this.password = password;
    }    
     public String getPassword() {
        return password;
    }
}
