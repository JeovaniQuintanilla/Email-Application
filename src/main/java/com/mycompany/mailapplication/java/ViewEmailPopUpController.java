/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.mailapplication.java;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author jeova
 */
public class ViewEmailPopUpController implements Initializable {

    @FXML
    private TextArea msgLabel;
    @FXML
    private Label sndrLabel;
    @FXML
    private Label subjLabel;
    @FXML
    private Label recLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        // TODO
    } 
   
    void setSndrLabel(String s){
        sndrLabel.setText(s);
    }
    void setRecvrLabel(String s){
        recLabel.setText(s);
    }
    void setSubjLabel(String s){
        subjLabel.setText(s);
    }
    void setTextArea(String s){
        msgLabel.setText(s);
        msgLabel.setEditable(false);
    }
    
    
}
