/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mailapplication.java;

/**
 *
 * @author jeovani quintanilla
 * Purpose: this class defines a user
 */
public class User {
    protected String fName;
    protected String lName;
    protected String emailAddr;
    protected String password;
 
    /**
     * @param fName
     * @param lName
     * @param emailAddr
     * @param password
     * user Constructor 
     */
    public User(String fName, String lName, String emailAddr, String password) {
        this.fName = fName;
        this.lName = lName;
        this.emailAddr = emailAddr;
        this.password = password;
    }

    /**
     * user Constructor
     */
    public User() {
    }
    
    /**
     * First Name Setter 
     * @param fName
     */
    public void setfName(String fName) {
        this.fName = fName;
    }

    /**
     * First Name Getter
     * @return
     */
    public String getfName() {
        return fName;
    }

    /**
     * Last Name Setter
     * @param lName
     */
    public void setlName(String lName) {
        this.lName = lName;
    }

    /**
     * Last Name Getter
     * @return
     */
    public String getlName() {
        return lName;
    }

    /**
     * Email Address Setter
     * @param emailAddr
     */
    public void setEmailAddr(String emailAddr) {
        this.emailAddr = emailAddr;
    } 

    /**
     * Email Address Getter
     * @return
     */
    public String getEmailAddr() {
        return emailAddr;
    }

    /**
     * Password Setter
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }    

    /**
     * Password Getter
     * @return
     */
    public String getPassword() {
        return password;
    }
}
