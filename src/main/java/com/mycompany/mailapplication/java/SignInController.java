package com.mycompany.mailapplication.java;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        FirebaseInitialize.initializeFB();

    }
    
    @FXML
    void GoToSignUp(MouseEvent event) throws IOException {
        App.setRoot("SignUp");
    }
    
    private Boolean verifyCreds() {
        //fb = new FirebaseInitialize();
        String email = emailField.getText();
        String password = pwordField.getText();
        if (emailField.getText().isBlank() || pwordField.getText().isBlank()){
            return false;
        }
            //String testemail = "jeo@gmail.com";
            //String testPword = "123abc";
            //boolean flag = true;
            //if(!email.equals(testemail) || !password.equals(testPword)){
            //flag = false;
            //}
            //System.out.println(flag);
            
        return FirebaseInitialize.getInstance().readFromFirebase(email, password);
            
    }
    
    
    @FXML
    void SignIn(ActionEvent event) throws IOException {
        if(verifyCreds()){
           App.setRoot("Index");
        }else{
            System.out.println("Credentials are invalid");
        }
    }
}
