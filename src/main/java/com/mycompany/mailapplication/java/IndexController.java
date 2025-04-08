/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.mailapplication.java;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import service.FirebaseInitialize;

/**
 * FXML Controller class
 *
 * @author jeova
 */
public class IndexController implements Initializable {

    /**
     * Initializes the controller class.
     */
   

    @FXML
    private Button composeBtn;

    @FXML
    private Button hubBtn;

    @FXML
    private ListView<Email> listView;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           FirebaseInitialize.initializeFB();
           String email = SignInController.currentUser.getEmailAddr();
           //System.out.println("current user on index: "+ email);
           List<Email> emails = FirebaseInitialize.getInstance().loadEmailsFromDB(email);

    // Convert to ObservableList and update ListView
   
    if (emails != null && !emails.isEmpty()) {
        ObservableList<Email> observableEmails = FXCollections.observableArrayList(emails);
        listView.setItems(observableEmails);
    } else {
        System.out.println("No emails found.");
    }
    
    }
       
       
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
