/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;
import com.google.cloud.Service;
import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import java.io.FileInputStream;

import com.google.firebase.*;
import com.google.firebase.cloud.FirestoreClient;
import com.mycompany.mailapplication.java.Email;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;




/**
 *
 * @author jeova
 */

public class FirebaseInitialize {
    private static FirebaseInitialize instance;
    private static Firestore db;// Initialize Firestore instance
    
    
    private FirebaseInitialize() {
        try {
            if (FirebaseApp.getApps().isEmpty()) {
                FileInputStream serviceAccount = new FileInputStream("EAServiceKey.json");

                FirebaseOptions options = new FirebaseOptions.Builder()
                        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                        .build();

                FirebaseApp.initializeApp(options);
                System.out.println("Firebase initialized!");
            } else {
                System.out.println("Firebase already initialized.");
            }
            db = FirestoreClient.getFirestore();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Firebase initialization failed.");
        }
    }
    
    
    public static void initializeFB() {
          if (instance == null) {
            instance = new FirebaseInitialize();
        }
    }
    
    public static FirebaseInitialize getInstance() {
        if (instance == null) {
            instance = new FirebaseInitialize();
        }
        return instance;
    }
    
    public Boolean readFromFirebase(String email, String password) {
        Boolean flag = false;
        
        try{
        ApiFuture<QuerySnapshot> query = db.collection("users").get();// Fetch all documents from 'users' collection
        QuerySnapshot querySnapshot = query.get();
        for (QueryDocumentSnapshot document : querySnapshot.getDocuments()) {// Iterate over documents and print out the email and password fields
            
            System.out.println("Document - email: " + document.getString("email") +", password: "+document.getString("pword"));
            
            if(email.equals(document.getString("email")) && password.equals(document.getString("pword"))){
                flag = true;
                break;
            }
            //System.out.println("Email: " + email);
           //System.out.println("Password: " + password);
        }
       }catch (ExecutionException | InterruptedException ex) {
                ex.getMessage();
                return false;
               
         } System.out.println(flag);
        return flag;  
    }
    
    
    public List<Email> loadEmailsFromDB(String email) {
         List<Email> emailList = new ArrayList<>();

        try {
            // Fetch the user document based on email
            ApiFuture<QuerySnapshot> userQuery = db.collection("users").whereEqualTo("email", email).get();
            QuerySnapshot userSnapshot = userQuery.get();

            if (!userSnapshot.isEmpty()) {
                String userId = userSnapshot.getDocuments().get(0).getId(); // Get user ID
                System.out.println("User ID: " + userId);

                // Fetch emails from the user's 'emails' subcollection
                ApiFuture<QuerySnapshot> emailQuery = db.collection("users").document(userId).collection("emails").get();
                QuerySnapshot emailSnapshot = emailQuery.get();

                for (QueryDocumentSnapshot emailDoc : emailSnapshot.getDocuments()) {
                    String sender = emailDoc.getString("sender");
                    String recipient = emailDoc.getString("recipient");
                    String subject = emailDoc.getString("subject");
                    String message = emailDoc.getString("message");

                    Email emailObj = new Email(sender, recipient, subject, message);
                    emailList.add(emailObj);
                }
            } else {
                System.out.println("No user found with email: " + email);
            }
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        return emailList;
    }
    
    
    
/**
 * 
 * @param args 
 
    public static void main(String[] args) {
        try {
            // Initialize Firebase
            FirebaseInitialize firebaseInitialize = new FirebaseInitialize();
            firebaseInitialize.initialize();

            // Read data from Firebase
            //firebaseInitialize.readFromFirebase();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
}

    