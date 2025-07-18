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
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author jeova
 */
public class SettingsController implements Initializable {
    
    @FXML
    private ImageView appIIMG;

    @FXML
    private TextField cpwordField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField fnField;

    @FXML
    private TextField lnField;

    @FXML
    private ImageView profilePic;

    @FXML
    private TextField pwordField;
    @FXML
    private AnchorPane setting;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    void editSettings(ActionEvent event) {

    }

    @FXML
    void logOut(MouseEvent event) {

    }

    @FXML
    void saveSettings(ActionEvent event) {

    }

    @FXML
    private void exitSettings(MouseEvent event) {
    }
    
}
