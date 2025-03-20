module com.mycompany.mailapplication.java {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.mycompany.mailapplication.java to javafx.fxml;
    exports com.mycompany.mailapplication.java;
}
