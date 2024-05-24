package com.example.timemanagement;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.awt.Desktop;
import java.net.URI;
import javafx.application.Application;

public class thu extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Khởi tạo nút task_btn
        Button task_btn = new Button();
        task_btn.setText("Go to GeeksforGeeks");

        // Xử lý sự kiện khi nút được nhấn
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

        // Thêm nút vào giao diện
        StackPane root = new StackPane();
        root.getChildren().add(task_btn);

        // Tạo scene và hiển thị giao diện
        Scene scene = new Scene(root, 300, 250);
        primaryStage.setTitle("Open Web Page on Button Click");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
