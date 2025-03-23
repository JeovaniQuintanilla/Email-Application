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
      

    }
    
    @FXML
    void GoToSignUp(MouseEvent event) throws IOException {
        App.setRoot("SignUp");
    }
    
    private Boolean verifyCreds() {
       

        String email = emailField.getText();
        String password = pwordField.getText();
        
        String testemail = "jeo@gmail.com";
        String testPword = "123abc";
        boolean flag = true;
        if(!email.equals(testemail) || !password.equals(testPword)){
            flag = false;
        }
        System.out.println(flag);
        return flag;
    }
    
    
    @FXML
    void SignIn(ActionEvent event) throws IOException {
        if(verifyCreds() == true && (!emailField.getText().isBlank() || !pwordField.getText().isBlank())){
           App.setRoot("Index");
        }
    }
}
