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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;




/**
 *
 * @author jeova
 */

public class FirebaseInitialize {
    
    public void initialize() throws FileNotFoundException, IOException{
        FileInputStream serviceAccount = new FileInputStream("./EAServiceKey.json");
        @SuppressWarnings("deprecation")
        //FirebaseOptions options = new FirebaseOptions.Builder().setCredentials(GoogleCredentials.fromStream(serviceAccount)).setDatabaseUrl("https://emailapplication-a589b.firebaseio.com").build();
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();
        FirebaseApp.initializeApp(options);
    }
    public void readFromFirebase() throws InterruptedException, ExecutionException{
       // Initialize Firestore instance
        Firestore db = FirestoreClient.getFirestore();

        // Fetch all documents from 'users' collection
        ApiFuture<QuerySnapshot> query = db.collection("users").get();
        QuerySnapshot querySnapshot = query.get();

        // Get list of documents
        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();

        // Iterate over documents and print out the email and password fields
        for (QueryDocumentSnapshot document : documents) {
            
            
            String email = document.getString("email");
            String password = document.getString("pword");

            System.out.println("Email: " + email);
            System.out.println("Password: " + password);
        }

    }
    
    
    public static void main(String[] args) {
        try {
            // Initialize Firebase
            FirebaseInitialize firebaseInitialize = new FirebaseInitialize();
            firebaseInitialize.initialize();

            // Read data from Firebase
            firebaseInitialize.readFromFirebase();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

 