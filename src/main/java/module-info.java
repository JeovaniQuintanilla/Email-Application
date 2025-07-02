module com.mycompany.mailapplication.java {
    requires javafx.controls;
    requires javafx.fxml;
    requires firebase.admin;
    requires google.cloud.firestore;
    requires google.cloud.core;
    requires com.google.auth.oauth2;
    requires com.google.auth;
    requires com.google.api.apicommon;
    requires java.base;
 
    opens com.mycompany.mailapplication.java to javafx.fxml, com.google.cloud.firestore, com.google.gson;
    exports com.mycompany.mailapplication.java; 
}
