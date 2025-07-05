/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.mailapplication.java;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
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
import static service.FirebaseInitialize.db;

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
        
        try{
            String id = " ";
            ApiFuture<QuerySnapshot> query = FirebaseInitialize.db.collection("users").get();// Fetch all documents from 'users' collection
            QuerySnapshot querySnapshot = query.get();
            for (QueryDocumentSnapshot doc : querySnapshot.getDocuments()) {
                if (SignInController.currentUser.getEmailAddr().equals(doc.getString("emailAddr"))){
                    id = doc.getId();
                    System.out.println("ID for account was found. " + id);
                    break;
                }
                System.out.println("ID was not found"); 
            }
            
            if (!id.isEmpty()){
                DocumentReference ref =  db.collection("users").document(id); 
                Map<String, Object> draftEmail = new HashMap<>();
                draftEmail.put("message", messageField.getText());
                draftEmail.put("recipient", receiverField.getText());
                draftEmail.put("sender", senderField.getText());
                draftEmail.put("subject", subjField.getText());                    
                CollectionReference draftRef = ref.collection("drafts");
                ApiFuture<DocumentReference> draftResult = draftRef.add(draftEmail);
                IndexController.popUpStage.close();
            }else{
                System.out.println("Failed to save drafts.");
            }
            
        }catch (InterruptedException | ExecutionException ex) {
            System.err.println("Unable to reach Firebase, User not added");
        }

    }

    @FXML
    void send(ActionEvent event) {
         try{
            ApiFuture<QuerySnapshot> query = FirebaseInitialize.db.collection("users").get();// Fetch all documents from 'users' collection
            QuerySnapshot querySnapshot = query.get();
            for (QueryDocumentSnapshot doc : querySnapshot.getDocuments()) {
                if (!doc.getString("emailAddr").equals(receiverField.getText())){
                    System.out.println("There is no account that exists with this email - " + doc.getString("emailAddr"));
                    
                }else{
                    System.out.println("Account: " + doc.getString("emailAddr")+ " found.");
                    String id = doc.getId();
                    DocumentReference ref =  db.collection("users").document(id); 
                    Map<String, Object> sentEmail = new HashMap<>();
                        sentEmail.put("message", messageField.getText());
                        sentEmail.put("recipient", receiverField.getText());
                        sentEmail.put("sender", senderField.getText());
                        sentEmail.put("subject", subjField.getText());                    
                    CollectionReference sentRef1 = ref.collection("sent");
                    ApiFuture<DocumentReference> receiverResult = sentRef1.add(sentEmail);
                    IndexController.popUpStage.close();
                }
                
            }
        }catch (InterruptedException | ExecutionException ex) {
            System.err.println("Unable to reach Firebase, User not added");
        }
    }
    
    @FXML
    void reply(ActionEvent event) {

    }
    
}
