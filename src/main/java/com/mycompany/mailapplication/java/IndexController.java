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
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
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
    private Label drafts;

    @FXML
    private Label inbox;

    @FXML
    private ListView<String> listView;

    @FXML
    private VBox mailNav;

    @FXML
    private Label sent;
    
    public static Stage popUpStage;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        displayInbox();
    }
    
    /**
     * 
     * Purpose: Opens a popup window to create a email
    */
    @FXML
    void createEmail(ActionEvent event) throws IOException {
        popUpStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("writeEmailPopUp.fxml"));
        AnchorPane pane = loader.load();
        Scene s = new Scene(pane);
        popUpStage.setScene(s);
        popUpStage.show();
    }
   
    @FXML
    void displayDrafts(MouseEvent event) {
        int count = listView.getItems().size();
        if (count != 0){
            listView.getItems().clear();
        }
        displayDrafts();
        count = listView.getItems().size();
        System.out.println("Current Listview: " + count);
    }

    @FXML
    void displayInbox(MouseEvent event) {
        int count = listView.getItems().size();
        if (count != 0){
            listView.getItems().clear();
        }
        displayInbox();
        count = listView.getItems().size();
        System.out.println("Current Listview: " + count);

    }

    @FXML
    void displaySent(MouseEvent event) {
        int count = listView.getItems().size();
        if (count != 0){
            listView.getItems().clear();
        }
        displaySent();
        count = listView.getItems().size();
        System.out.println("Current Listview: " + count);

    }

    @FXML
    void goToSettingPage(MouseEvent event) {

    }

    @FXML
    void logOut(MouseEvent event) throws IOException {
        //Alert("You are about to log out, are you usre??");
        SignUpController.toSignInScreen();
    }
    
    /**
     * Displays the contents of inbox
    */
    private void displayInbox(){
        List<Email> emails = retrieveInbox();
        if (emails != null && !emails.isEmpty()) {
            //ObservableList<Email> observableEmails = FXCollections.observableArrayList(emails);
            for (Email email : emails) {
                listView.getItems().addAll("From: " + email.getSender() + "\n" +email.getMessage());
            }
        } else {
            System.out.println("No emails found.");
        }
    }
    
    /**
     * Displays the contents of Sent
    */
    private void displaySent(){
        List<Email> emails = retrieveSent();
        if (emails != null && !emails.isEmpty()) {
            //ObservableList<Email> observableEmails = FXCollections.observableArrayList(emails);
            for (Email email : emails) {
                listView.getItems().addAll("From: " + email.getSender() + "\n" +email.getMessage());
            }
        } else {
            System.out.println("No emails found.");
        }
    }
    
       /**
     * Displays the contents of Drafts
    */
    private void displayDrafts(){
        List<Email> emails = retrieveDrafts();
        if (emails != null && !emails.isEmpty()) {
            //ObservableList<Email> observableEmails = FXCollections.observableArrayList(emails);
            for (Email email : emails) {
                listView.getItems().addAll("From: " + email.getSender() + "\n" +email.getMessage());
            }
        } else {
            System.out.println("No emails found.");
        }
    }
   
    /**
     * retrieves inbox form firebase
     * @return 
     */
    private List<Email> retrieveInbox() {
        FirebaseInitialize.initializeFB();
        String email = SignInController.currentUser.getEmailAddr();
        //System.out.println("current user on index: "+ email);
        List<Email> emails = FirebaseInitialize.getInstance().loadInboxFromDB(email);
        return emails;
    }
    /**
     * retrieves sent from firebase
     * @return 
     */
     private List<Email> retrieveSent() {
        FirebaseInitialize.initializeFB();
        String email = SignInController.currentUser.getEmailAddr();
        //System.out.println("current user on index: "+ email);
        List<Email> emails = FirebaseInitialize.getInstance().loadSentFromDB(email);
        return emails;
    }
     /**
     * retrieves drafts from firebase 
     * (will need to be edited in the future)
     * @return 
     */
     private List<Email> retrieveDrafts() {
        FirebaseInitialize.initializeFB();
        String email = SignInController.currentUser.getEmailAddr();
        //System.out.println("current user on index: "+ email);
        List<Email> emails = FirebaseInitialize.getInstance().loadDraftsFromDB(email);
        return emails;
    }

    @FXML
    private void viewEmail(MouseEvent event) {
        
        
        
    }
}
