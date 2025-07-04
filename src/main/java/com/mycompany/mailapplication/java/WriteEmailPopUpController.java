/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.mailapplication.java;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author jeova
 */
public class WriteEmailPopUpController implements Initializable {
    
    @FXML
    private AnchorPane writeEmail;
    
    @FXML
    private Button draftBtn;

    @FXML
    private TextArea messageField;

    @FXML
    private TextField receiverField;

    @FXML
    private Button replyBtn;

    @FXML
    private Button sendBtn;

    @FXML
    private TextField senderField;

    @FXML
    private TextField subjField;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }   
    
    @FXML
    void drafts(ActionEvent event) {

    }

    @FXML
    void reply(ActionEvent event) {

    }

    @FXML
    void send(ActionEvent event) {

    }
    
}
