/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;
import com.google.cloud.Service;
import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import java.io.FileInputStream;

import com.google.firebase.*;
import com.google.firebase.cloud.FirestoreClient;
import com.mycompany.mailapplication.java.Email;
import com.mycompany.mailapplication.java.User;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
            //Temporary fix - delete after fixing read and loading issues (emails and users)
            if(email.equals(document.getString("email")) && password.equals(document.getString("pword"))){
                flag = true;
                break;
            }
            if(email.equals(document.getString("emailAddr")) && password.equals(document.getString("password"))){
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
            ApiFuture<QuerySnapshot> userQuery = db.collection("users").whereEqualTo("emailAddr", email).get();
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
    
    public void addToFirebase(User user){
        boolean flag = true;
        try {
            //See if the account already exist
            ApiFuture<QuerySnapshot> query = db.collection("users").get();// Fetch all documents from 'users' collection
            QuerySnapshot querySnapshot = query.get();
            for (QueryDocumentSnapshot document : querySnapshot.getDocuments()) {
                if(user.getEmailAddr().equals(document.getString("emailAddr"))){
                    flag = false;
                }
                //System.out.println("Email: " + email);
               //System.out.println("Password: " + password);
            }
            if (flag == true){
                Map<String, Object> userMap = new HashMap<>();
                    userMap.put("fName", user.getfName());
                    userMap.put("lName", user.getlName());
                    userMap.put("emailAddr", user.getEmailAddr());
                    userMap.put("password", user.getPassword());

                ApiFuture<DocumentReference> result = db.collection("users").add(userMap);
                DocumentReference ref = result.get();
                System.out.print("New User Added to FireBase!");
                
                //create a email subcollection for this user
                Email greeting = new Email("JMail@organization.com", user.getEmailAddr(),"Welcome to Jmail!", 
                        "The team at JMail welcomes you to our application, we hope you enjoy :).");
                //send greeting
                Map<String, Object> emailMap = new HashMap<>();
                    emailMap.put("message", greeting.getMessage());
                    emailMap.put("recipient", greeting.getRecipient());
                    emailMap.put("sender", greeting.getSender());
                    emailMap.put("subject", greeting.getSubject());
                ApiFuture<DocumentReference> greet = ref.collection("emails").add(emailMap); 
                System.out.println("\nGreeting Sent!! ");
            }else{
                System.out.println("There exist an account already with this email.");
            }   
            
        } catch (InterruptedException | ExecutionException ex) {
            System.err.println("Unable to reach Firebase, User not added");
        }
    }
    
    public static void main(String[] args) {
            FirebaseInitialize.initializeFB();
            FirebaseInitialize fb = FirebaseInitialize.getInstance();
            User user = new User("Jeff", "Cuadra", "jeff2@gmail.com", "2what4u");
            fb.addToFirebase(user);
             
    }
}

    