/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mailapplication.java;

/**
 * Purpose: this class defines a email
 * @author jeovani quintanilla
 */
public class Email {
    
    private String sender;
    private String recepient;
    private String subject;
    private String message;

    /**
     * Email Constructor 
     * @param sender
     * @param recepient
     * @param subject
     * @param message
     */
    public Email(String sender, String recepient, String subject, String message) {
        this.sender = sender;
        this.recepient = recepient;
        this.subject = subject;
        this.message = message;
    }

    /**
     * Email Constructor 
     */
    public Email() {
    }
    
    /**
     * Sender Setter
     * @param sender
     */
    public void setSender(String sender) {
        this.sender = sender;
    }

    /**
     * Sender Getter
     * @return
     */
    public String getSender() {
        return sender;
    }

    /**
     * Recipient Setter
     * @param recepient
     */
    public void setRecepient(String recepient) {
        this.recepient = recepient;
    }

    /**
     * Recipient Getter
     * @return
     */
    public String getRecepient() {
        return recepient;
    }

    /**
     * Subject Setter
     * @param subject
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * Subject Getter
     * @return
     */
    public String getSubject() {
        return subject;
    }
    
    /**
     * Message Setter
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Message Getter 
     * @return
     */
    public String getMessage() {
        return message;
    }

    
    
    
}
