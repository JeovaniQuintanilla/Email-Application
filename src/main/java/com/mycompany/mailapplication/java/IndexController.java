/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.mailapplication.java;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

/**
 * FXML Controller class
 *
 * @author jeova
 */
public class IndexController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private Button composeBtn;

    @FXML
    private Button hubBtn;

    @FXML
    private ListView<?> listView;

    @FXML
    void createEmail(ActionEvent event) {

    }

    @FXML
    void loadEmails(ActionEvent event) {

    }

    @FXML
    void logOut(ActionEvent event) throws IOException {
        App.setRoot("SignIn");
    }    
    
}
