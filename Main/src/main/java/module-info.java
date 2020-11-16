module com.mycompany.main {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.graphics;
    requires java.base;

    opens com.mycompany.main to javafx.fxml;
    exports com.mycompany.main;
}
