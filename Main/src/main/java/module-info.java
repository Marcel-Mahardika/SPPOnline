module com.mycompany.main {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.main to javafx.fxml;
    exports com.mycompany.main;
}
