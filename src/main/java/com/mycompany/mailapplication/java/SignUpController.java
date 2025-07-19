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
    @FXML
    private TextField pwordField1;
    
     /**TO-DO: - this is a list of commits I plan to implement into this section
     * 1 - edit with css
     * 2 - add logo image
     * 3 - add profile pic with every account
     * 
    */
    
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
        try{
            toSignInScreen();
        }catch(IOException e){
            System.err.println("Failed to return to Login-In Screen");
        }
    }

    @FXML
    void ReturnToSignIn(MouseEvent event) throws IOException {
        toSignInScreen();
    }
    
    
    public static void toSignInScreen() throws IOException{
      App.setRoot("SignIn");
    }
    
    public static void toSignUpScreen() throws IOException{
      App.setRoot("SignUp");
    }
    
    
      

}