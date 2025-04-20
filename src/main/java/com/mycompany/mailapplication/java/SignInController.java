package com.mycompany.mailapplication.java;


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


public class SignInController implements Initializable{
    @FXML
    private AnchorPane SignIn;

     @FXML
    private Button SignInBtn;

    @FXML
    private TextField emailField;

    @FXML
    private TextField pwordField;

    @FXML
    private Label signupText;
    
    /**
     * Reference to the Current User Logged in
     */
    static protected User currentUser = new User();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        FirebaseInitialize.initializeFB();
    }
    
    @FXML
    void GoToSignUp(MouseEvent event) throws IOException {
        SignUpController.toSignUpScreen();
    }
    
    @FXML
    void SignIn(ActionEvent event) throws IOException {
        if(verifyCreds()){
            String email = emailField.getText();
            String pass = pwordField.getText();
            currentUser.setEmailAddr(email);
            currentUser.setPassword(pass);
            System.out.print("current user " + currentUser.getEmailAddr());
           App.setRoot("Index");
           
        }else{
            System.out.println("Credentials are invalid");
        }
    }
    
    private Boolean verifyCreds() {
        String email = emailField.getText();
        String password = pwordField.getText();
        if (emailField.getText().isBlank() || pwordField.getText().isBlank()){
            return false;
        }
            
        return FirebaseInitialize.getInstance().readFromFirebase(email, password);        
    }
}
