package com.mycompany.mailapplication.java;

import com.google.api.core.ApiFuture;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
    
    
    public void initialize(URL url, ResourceBundle rb) {
        FirebaseInitialize.initializeFB();
    }
    
    @FXML
    void CreateAccount(ActionEvent event) {
        
        String first = fnameField.getText();
        String last = lnameField.getText();
        String email = emailField.getText();
        String pword = pwordField.getText();
        User new_user = new User(first,last,email,pword);
        FirebaseInitialize.getInstance().addToFirebase(new_user);
        //System.out.println("New User Created: " + new_user.getfName()+" "+ new_user.getlName());
    }

    @FXML
    void ReturnToSignIn(MouseEvent event) throws IOException {
        toSignInScreen();
    }
    
    
    public static void toSignInScreen() throws IOException{
      App.setRoot("SignIn");
    }
    
      

}