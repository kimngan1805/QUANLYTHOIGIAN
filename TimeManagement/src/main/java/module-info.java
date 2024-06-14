module com.example.timemanagement {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires java.desktop;

    opens com.example.timemanagement to javafx.fxml;
    exports com.example.timemanagement.CONTROLLER;
    exports com.example.timemanagement;

}