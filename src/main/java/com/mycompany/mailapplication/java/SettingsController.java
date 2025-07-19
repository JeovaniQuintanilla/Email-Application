/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.mailapplication.java;

import java.io.IOException;
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
    private TextField cpwordField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField fnField;

    @FXML
    private TextField lnField;


    @FXML
    private TextField pwordField;
    @FXML
    private AnchorPane setting;
    @FXML
    private ImageView profilePic2;
    @FXML
    private ImageView profilePic1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        fnField.setText(SignInController.currentUser.getfName());
        lnField.setText(SignInController.currentUser.getlName());
        emailField.setText(SignInController.currentUser.getEmailAddr());
        pwordField.setText(SignInController.currentUser.getPassword());
    }

    void editSettings(ActionEvent event) {

    }

    @FXML
    void logOut(MouseEvent event) {

    }

    @FXML
    void saveSettings(ActionEvent event) {

    }

    @FXML
    private void exitSettings(MouseEvent event) throws IOException {
        App.setRoot("Index");
        App.getStage().sizeToScene();
    }

    @FXML
    private void saveSettings(MouseEvent event) {
    }

    @FXML
    private void editSettings(MouseEvent event) {
    }
    
    
}
