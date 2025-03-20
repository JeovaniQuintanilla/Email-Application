package com.mycompany.mailapplication.java;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class SignInController {

     @FXML
    private Button SignInBtn;

    @FXML
    private TextField emailField;

    @FXML
    private TextField pwordField;

    @FXML
    private Label signupText;

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("SignUp");
    }
    
    @FXML
    void GoToSignUp(MouseEvent event) {

    }

    @FXML
    void SignIn(ActionEvent event) {

    }
}
