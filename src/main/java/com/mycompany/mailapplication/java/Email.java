/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mailapplication.java;

/**
 *
 * @author jeova
 */
public class Email {
    
    private String sender;
    private String recepient;
    private String subject;
    private String message;

    public Email(String sender, String recepient, String subject, String message) {
        this.sender = sender;
        this.recepient = recepient;
        this.subject = subject;
        this.message = message;
    }

    public Email() {
    }
    
    
    
}
