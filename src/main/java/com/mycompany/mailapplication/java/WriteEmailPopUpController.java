/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.mailapplication.java;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import service.FirebaseInitialize;

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
    
    //private static Firestore db;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //FirebaseInitialize.initializeFB();
    }   
    
    @FXML
    void drafts(ActionEvent event) {

    }

    @FXML
    void reply(ActionEvent event) {

    }

    @FXML
    void send(ActionEvent event) {
         try{
            ApiFuture<QuerySnapshot> query = FirebaseInitialize.db.collection("users").get();// Fetch all documents from 'users' collection
            QuerySnapshot querySnapshot = query.get();
            for (QueryDocumentSnapshot document : querySnapshot.getDocuments()) {
                if (!document.getString("emailAddr").equals(receiverField.getText())){
                    System.out.println("There is no account that exists with this email - " + document.getString("emailAddr"));
                    
                }else{
                    System.out.println("Account: " + document.getString("emailAddr")+ " found.");
                    break;
                }
                
            }
        }catch (InterruptedException | ExecutionException ex) {
            System.err.println("Unable to reach Firebase, User not added");
        }
        

    
    }
    
}
