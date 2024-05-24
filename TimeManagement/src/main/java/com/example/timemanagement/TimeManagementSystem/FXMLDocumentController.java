package com.example.timemanagement.TimeManagementSystem;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */


import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author DELL
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Hyperlink lg_createAccount;

    @FXML
    private Button lg_loginBtn;

    @FXML
    private PasswordField lg_password;

    @FXML
    private TextField lg_username;

    @FXML
    private AnchorPane login_form;

    @FXML
    private AnchorPane main_form;

    @FXML
    private AnchorPane signup_form;

    @FXML
    private Hyperlink su_haveAccount;

    @FXML
    private PasswordField su_password;

    @FXML
    private Button su_signUpBtn;

    @FXML
    private TextField su_username;

    public void switchForm(ActionEvent event) {
        if(event.getSource() == lg_createAccount) {
            signup_form.setVisible(true);
            login_form.setVisible(false);
        } else if (event.getSource() == su_haveAccount){
            signup_form.setVisible(false);
            login_form.setVisible(true);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
