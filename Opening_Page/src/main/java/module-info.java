module com.mycompany.opening_page {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.opening_page to javafx.fxml;
    exports com.mycompany.opening_page;
}
