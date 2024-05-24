package com.example.timemanagement;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.awt.*;
import java.net.URI;
import java.net.URL;
import java.util.ResourceBundle;

public class dashboardController implements Initializable{

    @FXML
    private Button cancelButton;
    @FXML
    private Button event_btn;

    @FXML
    private AnchorPane event_form;

    @FXML
    private Button home_btn;

    @FXML
    private AnchorPane home_form;

    @FXML
    private Button note_btn;

    @FXML
    private AnchorPane note_form;

    @FXML
    private PieChart pieChart;

    @FXML
    private Label quote;

    @FXML
    private Button task_btn;

    @FXML
    private AnchorPane task_form;

//    public void logout() {
//        try {
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public void switchform(ActionEvent event) {
        if (event.getSource() == home_btn) {
            home_form.setVisible(true);
            event_form.setVisible(false);
            task_form.setVisible(false);
            note_form.setVisible(false);

            home_btn.setStyle("-fx-background-color: linear-gradient(to bottom right, #46dddd,#fd427d);");
            event_btn.setStyle("-fx-background-color: transparent;");
//            task_btn.setStyle("-fx-background-color: transparent;");
            note_btn.setStyle("-fx-background-color: transparent;");
        } else if (event.getSource() == event_btn) {
            home_form.setVisible(false);
            event_form.setVisible(true);
            task_form.setVisible(false);
            note_form.setVisible(false);

            event_btn.setStyle("-fx-background-color: linear-gradient(to bottom right, #46dddd,#fd427d);");
            home_btn.setStyle("-fx-background-color: transparent;");
//            task_btn.setStyle("-fx-background-color: transparent;");
            note_btn.setStyle("-fx-background-color: transparent;");
        } else if (event.getSource() == task_btn) {
            home_form.setVisible(false);
            event_form.setVisible(false);
            task_form.setVisible(true);
            note_form.setVisible(false);

//            task_btn.setStyle("-fx-background-color: linear-gradient(to bottom right, #46dddd,#fd427d);");
            event_btn.setStyle("-fx-background-color: transparent;");
            home_btn.setStyle("-fx-background-color: transparent;");
            note_btn.setStyle("-fx-background-color: transparent;");
        } else if (event.getSource() == note_btn) {
            home_form.setVisible(false);
            event_form.setVisible(false);
            task_form.setVisible(false);
            note_form.setVisible(true);

            note_btn.setStyle("-fx-background-color: linear-gradient(to bottom right, #46dddd,#fd427d);");
            event_btn.setStyle("-fx-background-color: transparent;");
//            task_btn.setStyle("-fx-background-color: transparent;");
            home_btn.setStyle("-fx-background-color: transparent;");
        }
    }


    public void ExitButton(ActionEvent event){
        Stage stage= (Stage)cancelButton.getScene().getWindow();
        stage.close();

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void start(ActionEvent event) {
        task_btn.setText("Go to task");
        task_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String url = "http://localhost/schedule/";
                try {
                    Desktop.getDesktop().browse(new URI(url));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
