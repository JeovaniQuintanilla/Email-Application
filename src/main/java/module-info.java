module com.mycompany.mailapplication.java {
    requires javafx.controls;
    requires javafx.fxml;
    requires firebase.admin;
    requires google.cloud.firestore;
    requires google.cloud.core;
    requires com.google.auth.oauth2;
    requires com.google.auth;
    requires com.google.api.apicommon;
 
    opens com.mycompany.mailapplication.java to javafx.fxml;
    exports com.mycompany.mailapplication.java; 
}
