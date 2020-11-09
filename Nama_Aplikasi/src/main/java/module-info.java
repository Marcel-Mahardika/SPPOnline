module com.mycompany.mavenproject4 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.logging;

    opens com.mycompany.mavenproject4 to javafx.fxml;
    exports com.mycompany.mavenproject4;
}
