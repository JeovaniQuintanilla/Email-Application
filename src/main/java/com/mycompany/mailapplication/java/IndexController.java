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
import javafx.scene.input.MouseEvent;
import service.FirebaseInitialize;

/**
 * FXML Controller class
 *
 * @author jeovani Quiintanilla
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
    private ListView<String> listView;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Email> emails = retreiveEmails();
        if (emails != null && !emails.isEmpty()) {
            //ObservableList<Email> observableEmails = FXCollections.observableArrayList(emails);
            for (Email email : emails) {
                listView.getItems().addAll("New email from - " + email.getSender() +"\n" +
                email.getMessage());
            }
        } else {
            System.out.println("No emails found.");
        }
    }

    @FXML
    void createEmail(ActionEvent event) {

    }
   
    @FXML
    void displayDrafts(MouseEvent event) {

    }

    @FXML
    void displayInbox(MouseEvent event) {

    }

    @FXML
    void displaySent(MouseEvent event) {

    }

    @FXML
    void goToSettingPage(MouseEvent event) {

    }

    @FXML
    void logOut(MouseEvent event) throws IOException {
        SignUpController.toSignInScreen();
    }
   
    /**
     * User defined methods
     * @return 
     */
    private List<Email> retreiveEmails() {
        FirebaseInitialize.initializeFB();
        String email = SignInController.currentUser.getEmailAddr();
        //System.out.println("current user on index: "+ email);
        List<Email> emails = FirebaseInitialize.getInstance().loadEmailsFromDB(email);
        return emails;
  
    }
}
