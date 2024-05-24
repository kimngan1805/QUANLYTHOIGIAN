package com.example.timemanagement;

import com.example.timemanagement.CONTROLLER.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

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
    // LOGIN METHOD
    //database toool
    private PreparedStatement preparedStatement;
    private Connection connect;
    private ResultSet resultSet;
    public void login(){
        String sql="select * from Login where username=? and password=?";
        DatabaseConnection connectNow= new DatabaseConnection();
        Connection connect=connectNow.getConnection();
        try{
            preparedStatement=connect.prepareStatement(sql);
            preparedStatement.setString(1,lg_username.getText());
            preparedStatement.setString(2,lg_password.getText());
            resultSet=preparedStatement.executeQuery();
            Alert alert;
            if(lg_username.getText().isEmpty()||lg_password.getText().isEmpty()){
                alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            }else{
                if (resultSet.next()){
                    alert=new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText(" Successfully Login !");
                    alert.showAndWait();
                    // ẩn trang login sau khi đăng nhập thành
                    lg_loginBtn.getScene().getWindow().hide();
                    // link để qua 1 trang mới sau khi login thành công
                    Parent root= FXMLLoader.load(getClass().getResource("timeController.fxml"));
                    Stage stage= new Stage();
                    Scene scene= new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }else{
                    alert=new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Wrong username/password");
                    alert.showAndWait();
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //tạo tài khoản mới
   private  Alert alert;
    public void create(){

        if(su_username.getText().isEmpty()||su_password.getText().isEmpty()){
            alert= new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();
        }else{
            String create="insert into Login (username, password)"+"values(?,?)";
            DatabaseConnection connectNow= new DatabaseConnection();
            Connection connect=connectNow.getConnection();
            try{
                preparedStatement=connect.prepareStatement(create);
                preparedStatement.setString(1,su_username.getText());
                preparedStatement.setString(2,su_password.getText());
                preparedStatement.executeUpdate();
                alert= new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information message");
                alert.setHeaderText(null);
                alert.setContentText("Create account is successfully!");
                alert.showAndWait();
                su_username.setText("");
                su_password.setText("");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}