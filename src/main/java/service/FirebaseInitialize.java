/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;
//import com.google.cloud.Service;
import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import java.io.FileInputStream;

import com.google.firebase.*;
import com.google.firebase.cloud.FirestoreClient;
import com.mycompany.mailapplication.java.Email;
import com.mycompany.mailapplication.java.User;
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
    public static Firestore db;// Initialize Firestore instance

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
    
    public User readFromFirebase(String email, String password) {
        try{
        ApiFuture<QuerySnapshot> query = db.collection("users").get();// Fetch all documents from 'users' collection
        QuerySnapshot querySnapshot = query.get();
        for (QueryDocumentSnapshot document : querySnapshot.getDocuments()) {// Iterate over documents and print out the email and password fields
            if(email.equals(document.getString("emailAddr")) && password.equals(document.getString("password"))){
                User userfound = new User();
                userfound.setfName(document.getString("fName"));
                userfound.setlName(document.getString("lName"));
                return userfound;
                //System.out.println("Found-> "+userfound.getfName());
                //flag = true;
                //break;
            }
        }
       }catch (ExecutionException | InterruptedException ex) {
                System.out.println("Failed to find User Locating...");
       } //System.out.println(flag);
        System.out.println("No User Found");  
        return null;
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
                Email greeting = new Email("JMail@organization.com", user.getEmailAddr(),"Welcome to Jmail!!", 
                        "The team at JMail welcomes you to our application, we hope you enjoy :).");
                //create inbox sub-collection
                Map<String, Object> inboxMap = new HashMap<>();
                    inboxMap.put("message", greeting.getMessage());
                    inboxMap.put("recipient", greeting.getRecipient());
                    inboxMap.put("sender", greeting.getSender());
                    inboxMap.put("subject", greeting.getSubject());
                ApiFuture<DocumentReference> greet = ref.collection("inbox").add(inboxMap); 
                
                Email greeting2 = new Email("JMail@organization.com", user.getEmailAddr(),"Welcome to Your Sent!!", 
                        "This is your sent section of JMail, where all your sent emails will appear.");
                //create sent sub-collection
                Map<String, Object> SentMap = new HashMap<>();
                    SentMap.put("message", greeting2.getMessage());
                    SentMap.put("recipient", greeting2.getRecipient());
                    SentMap.put("sender", greeting2.getSender());
                    SentMap.put("subject", greeting2.getSubject());
                ApiFuture<DocumentReference> sent = ref.collection("sent").add(SentMap);
                
                Email greeting3 = new Email("JMail@organization.com", user.getEmailAddr(),"Welcome to Your Drafts!!",
                        "This is your drafts section of JMail, where all your emails that you've started but aren't ready to send will appear.");
                //create drafts sub-collection
                Map<String, Object> draftsMap = new HashMap<>();
                    draftsMap.put("message", greeting3.getMessage());
                    draftsMap.put("recipient", greeting3.getRecipient());
                    draftsMap.put("sender", greeting3.getSender());
                    draftsMap.put("subject", greeting3.getSubject());
                ApiFuture<DocumentReference> drafts = ref.collection("drafts").add(draftsMap);
                
                System.out.println("\nGreeting Sent!! ");//send greeting
            }else{
                System.out.println("There exist an account already with this email.");
            }   
            
        } catch (InterruptedException | ExecutionException ex) {
            System.err.println("Unable to reach Firebase, User not added");
        }
    }
    
    public List<Email> loadInboxFromDB(String email) {
         List<Email> emailList = new ArrayList<>();

        try {
            // Fetch the user document based on email
            ApiFuture<QuerySnapshot> userQuery = db.collection("users").whereEqualTo("emailAddr", email).get();
            QuerySnapshot userSnapshot = userQuery.get();

            if (!userSnapshot.isEmpty()) {
                String userId = userSnapshot.getDocuments().get(0).getId(); // Get user ID
                System.out.println("User ID: " + userId);

                // Fetch emails from the user's 'emails' subcollection
                ApiFuture<QuerySnapshot> emailQuery = db.collection("users").document(userId).collection("inbox").get();
                QuerySnapshot emailSnapshot = emailQuery.get();

                for (QueryDocumentSnapshot emailDoc : emailSnapshot.getDocuments()) {
                    String sender = emailDoc.getString("sender");
                    String recipient = emailDoc.getString("recipient");
                    String subject = emailDoc.getString("subject");
                    String message = emailDoc.getString("message");
                    System.out.println(sender +"\n "+ recipient + "\n " + subject + "\n " + message);
                     
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
    
    public List<Email> loadSentFromDB(String email) {
         List<Email> emailList = new ArrayList<>();

        try {
            // Fetch the user document based on email
            ApiFuture<QuerySnapshot> userQuery = db.collection("users").whereEqualTo("emailAddr", email).get();
            QuerySnapshot userSnapshot = userQuery.get();

            if (!userSnapshot.isEmpty()) {
                String userId = userSnapshot.getDocuments().get(0).getId(); // Get user ID
                System.out.println("User ID: " + userId);

                // Fetch emails from the user's 'emails' subcollection
                ApiFuture<QuerySnapshot> emailQuery = db.collection("users").document(userId).collection("sent").get();
                QuerySnapshot emailSnapshot = emailQuery.get();

                for (QueryDocumentSnapshot emailDoc : emailSnapshot.getDocuments()) {
                    String sender = emailDoc.getString("sender");
                    String recipient = emailDoc.getString("recipient");
                    String subject = emailDoc.getString("subject");
                    String message = emailDoc.getString("message");
                    //System.out.println(sender +"\n "+ recipient + "\n " + subject + "\n " + message);

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
    
    public List<Email> loadDraftsFromDB(String email) {
         List<Email> emailList = new ArrayList<>();

        try {
            // Fetch the user document based on email
            ApiFuture<QuerySnapshot> userQuery = db.collection("users").whereEqualTo("emailAddr", email).get();
            QuerySnapshot userSnapshot = userQuery.get();

            if (!userSnapshot.isEmpty()) {
                String userId = userSnapshot.getDocuments().get(0).getId(); // Get user ID
                System.out.println("User ID: " + userId);

                // Fetch emails from the user's 'emails' subcollection
                ApiFuture<QuerySnapshot> emailQuery = db.collection("users").document(userId).collection("drafts").get();
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
   
    public static void main(String[] args) {
            FirebaseInitialize.initializeFB();
            FirebaseInitialize fb = FirebaseInitialize.getInstance();
            //User user = new User("Jeff", "Cuadra", "jeff2@gmail.com", "2what4u");
            //fb.addToFirebase(user);
            System.out.println(fb.readFromFirebase("jeo@gmail.com", "123abc").getfName());
             
    }
}

    