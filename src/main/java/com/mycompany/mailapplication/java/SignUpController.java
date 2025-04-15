package com.mycompany.mailapplication.java;

import com.google.api.core.ApiFuture;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import service.FirebaseInitialize;

public class SignUpController {
    
    @FXML
    private AnchorPane SignUp;

    @FXML
    private TextField emailField;

    @FXML
    private TextField fnameField;

    @FXML
    private TextField lnameField;

    @FXML
    private TextField pwordField;

    @FXML
    private Label signinText;

    @FXML
    private Button signupBtn;
    
    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("SignIn");
    }
    
    @FXML
    void CreateAccount(ActionEvent event) {
 
           
        String fName = fnameField.getText();
        String lName = lnameField.getText();
        String emailAddr = emailField.getText();
        String password = pwordField.getText();
        
        User new_user = new User(fName,lName,emailAddr,password);
        FirebaseInitialize.initializeFB(); 
        FirebaseInitialize fb = FirebaseInitialize.getInstance();
        fb.addToFirebase(new_user);
        //System.out.println("New User Created: " + new_user.getfName()+" "+ new_user.getlName());
      
    }

    @FXML
    void ReturnToSignIn(MouseEvent event) throws IOException {
        App.setRoot("SignIn");
    }
}