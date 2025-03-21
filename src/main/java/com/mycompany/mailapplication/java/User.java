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
}
