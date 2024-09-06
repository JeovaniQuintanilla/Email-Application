module com.mycompany.mailapplication.java {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.mailapplication.java to javafx.fxml;
    exports com.mycompany.mailapplication.java;
}
