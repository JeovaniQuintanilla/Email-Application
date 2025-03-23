package com.mycompany.mailapplication.java;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

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

    }

    @FXML
    void ReturnToSignIn(MouseEvent event) throws IOException {
        App.setRoot("SignIn");
    }
}